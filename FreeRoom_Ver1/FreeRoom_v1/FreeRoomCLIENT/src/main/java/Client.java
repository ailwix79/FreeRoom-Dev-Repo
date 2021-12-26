
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public class Client {
    private String host;
    private int port;
    private static final Logger log;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log = Logger.getLogger(Client.class.getName());
    }

    public static void main(String args[]) {
        //Configure connections
        String host = "127.0.0.1";
        int port = 8081;
        log.info("Host: "+host+" port "+port);
        //Create a cliente class
        Client cliente=new Client(host, port);

        HashMap<String,Object> session=new HashMap<String, Object>();
        //session.put("/getCustomer","");

        Message mensajeEnvio=new Message();
        Message mensajeVuelta=new Message();
        mensajeEnvio.setContext("/getRoom");
        mensajeEnvio.setSession(session);
        cliente.sent(mensajeEnvio,mensajeVuelta);


        switch (mensajeVuelta.getContext()) {
            case "/getRoomResponse":
                ArrayList<Room> roomList =(ArrayList<Room>)(mensajeVuelta.getSession().get("Room"));
                for (Room room : roomList) {
                    System.out.println("He leído el id: "+ room.getId()+" isFree: "+ room.getIsFree());
                }
                break;

            default:
                log.info("Option not found");
                System.out.println("\nError a la vuelta");
                break;

        }
        //System.out.println("3.- En Main.- El valor devuelto es: "+((String)mensajeVuelta.getSession().get("Nombre")));
    }

    public Client(String host, int port) {
        this.host=host;
        this.port=port;
    }

    public static ArrayList obtenerDatos(){
        //Configure connections
        String host = "127.0.0.1";
        int port = 8081;
        log.info("Host: "+host+" port "+port);
        //Create a cliente class
        Client cliente=new Client(host, port);

        HashMap<String,Object> session=new HashMap<String, Object>();
        //session.put("/getCustomer","");

        Message mensajeEnvio=new Message();
        Message mensajeVuelta=new Message();
        mensajeEnvio.setContext("/getRoom");
        mensajeEnvio.setSession(session);
        cliente.sent(mensajeEnvio,mensajeVuelta);


        switch (mensajeVuelta.getContext()) {
            case "/getRoomResponse":
                ArrayList<Room> roomList =(ArrayList<Room>)(mensajeVuelta.getSession().get("Room"));
                return roomList;

            default:
                log.info("Option not found");
                System.out.println("\nError a la vuelta");
                return null;

        }

    }

    public void sent(Message messageOut, Message messageIn) {
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