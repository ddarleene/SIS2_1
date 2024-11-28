import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1111)) {
            System.out.println("Server is working");

            while (true) {
                try (Socket s = serverSocket.accept();
                     ObjectInputStream in = new ObjectInputStream(s.getInputStream());
                     ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream())) {

                    Object reply = in.readObject();
                    if (reply.equals("Q")) {
                        System.out.println("Exit");
                        break;
                    }

                    if (reply instanceof Main) {
                        Main data = (Main) reply;
                        double area = data.calculateArea();
                        out.writeObject("Area: " + area);
                    }
                }
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
    }
}
