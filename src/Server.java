import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1111)) {
            System.out.println("Server is working");

            while (true) {
                try (Socket socket = serverSocket.accept();
                     ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                     ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {

                    Object reply = input.readObject();
                    if (reply.equals("Q")) {
                        System.out.println("Client wants to quit");
                        break;
                    }

                    if (reply instanceof Main) {
                        Main main = (Main) reply;
                        double area = main.calculateArea();
                        output.writeObject("Area: " + area);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
