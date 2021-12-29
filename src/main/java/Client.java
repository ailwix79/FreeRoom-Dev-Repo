import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public class Client {
    private final String host;
    private final int port;
    private static final Logger log;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log = Logger.getLogger(Client.class.getName());
    }

    public Client(String host, int port) {
        this.host=host;
        this.port=port;
    }

    // getDATA FUNCTION. EMPLOYED FOR ALL COMMUNICATION PURPOSES WITH SOCKETSERVER. IN CHARGE OF RESERVING, OBTAINING DATA, LOGIN...
    public static Object getData(String dataClass, Object firstFactor, Object secondFactor, Object thirdFactor, Object fourthFactor, Object fifthFactor){
        //Configure connections
        String host = "127.0.0.1";
        int port = 8081;
        log.info("Host: "+host+" port "+port);
        Client client=new Client(host, port);

        HashMap<String,Object> session=new HashMap<String, Object>();

        Message mensajeEnvio=new Message();
        Message mensajeVuelta=new Message();

        if (dataClass.equals("roomData")) {
            mensajeEnvio.setContext("/getRoom");
        }
        if (dataClass.equals("roomDataSingle")) {
            mensajeEnvio.setContext("/getRoom");
        }
        if (dataClass.equals("roomDataDayTime")) {
            mensajeEnvio.setContext("/getRoomDayTime");
            session.put("Day",firstFactor);
            session.put("Time",secondFactor);
        }
        if (dataClass.equals("roomSingle")) {
            mensajeEnvio.setContext("/getSingleRoom");
            session.put("RoomID",firstFactor);
        }
        else if (dataClass.equals("loginData") && (firstFactor != null) && (secondFactor != null) && (thirdFactor != null)) {
            mensajeEnvio.setContext("/getLogin");
            session.put("Username",firstFactor);
            session.put("Password",secondFactor);
            session.put("Role",thirdFactor);
        }
        else if (dataClass.equals("reserveData")) {
            mensajeEnvio.setContext("/getReserve");
            session.put("RoomID",firstFactor);
            session.put("StartTime",secondFactor);
            session.put("EndTime",thirdFactor);
            session.put("Date",fourthFactor);
            session.put("UserID",fifthFactor);
        }
        mensajeEnvio.setSession(session);
        client.send(mensajeEnvio,mensajeVuelta);

        switch (mensajeVuelta.getContext()) {
            case "/getRoomResponse":
                ArrayList<Room> roomList =(ArrayList<Room>)(mensajeVuelta.getSession().get("Room"));
                for (Room room:roomList
                     ) {
                    System.out.println(room);
                }
                return roomList;

            case "/getRoomDayTimeResponse":
                ArrayList<Room> roomListDayTime =(ArrayList<Room>)(mensajeVuelta.getSession().get("Room"));
                /*
                for (Room room:roomListDayTime
                ) {
                    System.out.println(room);
                }
                 */
                return roomListDayTime;

            case "/getSingleRoomResponse":
                ArrayList<Room> roomListSingle =(ArrayList<Room>)(mensajeVuelta.getSession().get("Room"));
                /*
                for (Room room:roomListSingle
                ) {
                    System.out.println(room);
                }
                */
                return roomListSingle;

            case "/getReserveResponse":
                Boolean reserveVerdict = (Boolean) (mensajeVuelta.getSession().get("Reserve"));
                return reserveVerdict;

            case "/getLoginResponse":
                Boolean loginVerdict = (Boolean) (mensajeVuelta.getSession().get("Login"));
                return loginVerdict;

            default:
                log.info("Option not found");
                System.out.println("\nError a la vuelta");
                return null;

        }

    }

    public void send(Message messageOut, Message messageIn) {
        try {

            System.out.println("Connecting to host " + host + " on port " + port + ".");

            Socket echoSocket = null;
            OutputStream out = null;
            InputStream in = null;

            try {
                echoSocket = new Socket(host, port);
                in = echoSocket.getInputStream();
                out = echoSocket.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);

                //Create the objetct to send
                objectOutputStream.writeObject(messageOut);

                // create a DataInputStream so we can read data from it.
                ObjectInputStream objectInputStream = new ObjectInputStream(in);
                Message msg=(Message)objectInputStream.readObject();
                messageIn.setContext(msg.getContext());
                messageIn.setSession(msg.getSession());
		        /*System.out.println("\n1.- El valor devuelto es: "+messageIn.getContext());
		        String cadena=(String) messageIn.getSession().get("Nombre");
		        System.out.println("\n2.- La cadena devuelta es: "+cadena);*/

            } catch (UnknownHostException e) {
                System.err.println("Unknown host: " + host);
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Unable to get streams from server");
                System.exit(1);
            }

            /** Closing all the resources */
            out.close();
            in.close();
            echoSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}