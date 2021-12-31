package server;

import controller.LoginController;
import controller.ReserveController;
import controller.RoomControler;
import domain.Login;
import domain.Room;
import server.dao.DBConnectionApp;
import server.dao.LoginDAO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/** Clase principal del SERVIDOR. Gestiona la comunicación entre el servidor y los clientes. */

public class SocketServer extends Thread {
	public static final int PORT_NUMBER = 8081;

	protected Socket socket;

	public SocketServer(Socket socket) {
		this.socket = socket;
		System.out.println("New client connected from " + socket.getInetAddress().getHostAddress());
		start();
	}

	public void run() {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = socket.getInputStream();
			out = socket.getOutputStream();
			
			//first read the object that has been sent
			ObjectInputStream objectInputStream = new ObjectInputStream(in);
		    Message mensajeIn= (Message)objectInputStream.readObject();
		    //Object to return informations 
		    ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
		    Message mensajeOut=new Message();
			HashMap<String,Object> session=new HashMap<String, Object>();
		    switch (mensajeIn.getContext()) {
		    	case "/getRoom":
		    		ArrayList<Room> lista=new ArrayList<Room>();
					RoomControler.getRoom(lista);
		    		mensajeOut.setContext("/getRoomResponse");
		    		session.put("Room",lista);
		    		mensajeOut.setSession(session);
		    		objectOutputStream.writeObject(mensajeOut);
					break;

				case "/getRoomDayTime":
					String dia = (String)(mensajeIn.getSession().get("Day"));
					int hora = (int) (mensajeIn.getSession().get("Time"));
					ArrayList<Room> listaDayTime = new ArrayList<Room>();
					RoomControler.getRoomDayTime(listaDayTime,dia,hora);
					mensajeOut.setContext("/getRoomDayTimeResponse");
					session.put("Room",listaDayTime);
					mensajeOut.setSession(session);
					objectOutputStream.writeObject(mensajeOut);
					break;

				case "/getSingleRoom":
					String roomId = (String)(mensajeIn.getSession().get("RoomID"));
					ArrayList<Room> listaSingle = new ArrayList<Room>();
					RoomControler.getSingleRoom(listaSingle,roomId);
					mensajeOut.setContext("/getSingleRoomResponse");
					session.put("Room",listaSingle);
					mensajeOut.setSession(session);
					objectOutputStream.writeObject(mensajeOut);
					break;

				case "/getLogin":
					String userName = (String)(mensajeIn.getSession().get("Username"));
					String passwd = (String)(mensajeIn.getSession().get("Password"));
					String role = (String)(mensajeIn.getSession().get("Role"));
					mensajeOut.setContext("/getLoginResponse");
					session.put("Login", LoginController.checkLogin(userName,passwd,role));
					mensajeOut.setSession(session);
					objectOutputStream.writeObject(mensajeOut);
					break;

				case "/getReserve":
					String roomID = (String)(mensajeIn.getSession().get("RoomID"));
					String startTime = (String)(mensajeIn.getSession().get("StartTime"));
					String endTime = (String)(mensajeIn.getSession().get("EndTime"));
					String date = (String)(mensajeIn.getSession().get("Date"));
					String userID = (String)(mensajeIn.getSession().get("UserID"));
					mensajeOut.setContext("/getReserveResponse");
					session.put("Reserve", ReserveController.checkReservation(roomID,startTime,endTime,date,userID));
					mensajeOut.setSession(session);
					objectOutputStream.writeObject(mensajeOut);
					break;

				case "/getUsers":
					ArrayList<Login> users=new ArrayList<Login>();
					LoginDAO.getUsers(users);
					mensajeOut.setContext("/getUsersResponse");
					session.put("Users",users);
					mensajeOut.setSession(session);
					objectOutputStream.writeObject(mensajeOut);
					break;

				case "/addUser":
					String username = (String)(mensajeIn.getSession().get("Username"));
					passwd = (String)(mensajeIn.getSession().get("Password"));
					role = (String)(mensajeIn.getSession().get("Role"));
					mensajeOut.setContext("/addUserResponse");
					session.put("Confirmation", DBConnectionApp.addUser(username,passwd,role));
					mensajeOut.setSession(session);
					objectOutputStream.writeObject(mensajeOut);
					break;

				case "/deleteUser":
					username = (String)(mensajeIn.getSession().get("Username"));
					mensajeOut.setContext("/deleteUserResponse");
					session.put("Confirmation", DBConnectionApp.deleteUser(username));
					mensajeOut.setSession(session);
					objectOutputStream.writeObject(mensajeOut);
					break;

				case "/addRoom":
					roomID = (String)(mensajeIn.getSession().get("roomID"));
					int planta = (int)(mensajeIn.getSession().get("planta"));
					mensajeOut.setContext("/addRoomResponse");
					session.put("Confirmation", RoomControler.addRoom(roomID,planta));
					mensajeOut.setSession(session);
					objectOutputStream.writeObject(mensajeOut);
					break;

				case "/deleteRoom":
					roomID = (String)(mensajeIn.getSession().get("roomID"));
					mensajeOut.setContext("/deleteRoomResponse");
					session.put("Confirmation", DBConnectionApp.deleteClassData(roomID));
					mensajeOut.setSession(session);
					objectOutputStream.writeObject(mensajeOut);
					break;

		    	default:
		    		System.out.println("\nParámetro no encontrado");
		    		break;
		    }
		    
		    //Lógica del controlador 
		    //System.out.println("\n1.- He leído: "+mensaje.getContext());
		    //System.out.println("\n2.- He leído: "+(String)mensaje.getSession().get("Nombre"));
		    
		    
		    
		    //Prueba para esperar
		    /*try {
		    	System.out.println("Me duermo");
				Thread.sleep(15000);
				System.out.println("Me levanto");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			// create an object output stream from the output stream so we can send an object through it
			/*ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
			
			//Create the object to send
			String cadena=((String)mensaje.getSession().get("Nombre"));
			cadena+=" añado información";
			mensaje.getSession().put("Nombre", cadena);
			//System.out.println("\n3.- He leído: "+(String)mensaje.getSession().get("Nombre"));
			objectOutputStream.writeObject(mensaje);*
			*/

		} catch (IOException ex) {
			System.out.println("Unable to get streams from client");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
				socket.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		//This two lines resets the database with data included in datos.csv
		//DBConnectionApp.createDBSchema("login_data.sql");
		//DBConnectionApp.LoadCSVData();
		System.out.println("FreeRoom SocketServer started");
		ServerSocket server = null;
		try {
			server = new ServerSocket(PORT_NUMBER);
			while (true) {
				/**
				 * create a new {@link SocketServer} object for each connection
				 * this will allow multiple client connections
				 */
				new SocketServer(server.accept());
			}
		} catch (IOException ex) {
			System.out.println("Unable to start server.");
		} finally {
			try {
				if (server != null)
					server.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}