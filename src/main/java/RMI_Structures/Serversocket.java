/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI_Structures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import views.History;

/**
 *
 * @author Autumn
 */
public class Serversocket {

    private static ServerSocket serverSocket;
    private static ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();

    public static void main(String[] args) throws IOException {
        int port = 5000;
        serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());

            ClientHandler clientHandler = new ClientHandler(clientSocket, clients);
            clients.add(clientHandler);
            clientHandler.start();
        }
    }

    public static void broadcast(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }
}

class ClientHandler extends Thread {

    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<ClientHandler> clients;

    public ClientHandler(Socket socket, ArrayList<ClientHandler> clients) {
        this.clientSocket = socket;
        this.clients = clients;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received message from " + clientSocket.getInetAddress().getHostAddress() + ": " + inputLine);
                Serversocket.broadcast(inputLine);
                try {
                    String[] incomingString = inputLine.split(",");
                    RMIinterface Obj = (RMIinterface) Naming.lookup("rmi://localhost:1040/KGF");
                    boolean hasUpdate = Obj.change_order_status(incomingString[0], incomingString[1]);
                    if(hasUpdate){
                          System.out.println("Successfully updated an order status from client: " + clientSocket.getInetAddress().getHostAddress() + ": OrderID: " + incomingString[0] + ", Status: "+ incomingString[1]);
                    }else{
                         System.out.println("Failed to update an order status from client: " + clientSocket.getInetAddress().getHostAddress());
                    }
                } catch (NotBoundException | MalformedURLException | RemoteException ex) {
                      System.out.println("Error closing client " + clientSocket.getInetAddress().getHostAddress() + ": " + ex.getMessage());
                }

            }
        } catch (IOException e) {
            System.out.println("Error handling client " + clientSocket.getInetAddress().getHostAddress() + ": " + e.getMessage());
        } finally {
            try {
                in.close();
                out.close();
                clientSocket.close();
                clients.remove(this);
                System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " disconnected");
            } catch (IOException e) {
                System.out.println("Error closing client " + clientSocket.getInetAddress().getHostAddress() + ": " + e.getMessage());
            }
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}
