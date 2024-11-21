import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1111);
             ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.println("Enter shape like Circle, Rectangle, or Q to quit");
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("Q")) {
                    output.writeObject("Q");
                    break;
                }

                Main main = null;
                if (choice.equalsIgnoreCase("Circle")) {
                    System.out.print("Enter radius: ");
                    double radius = scanner.nextDouble();
                    main = new Circle(radius);
                } else if (choice.equalsIgnoreCase("Rectangle")) {
                    System.out.print("Enter width: ");
                    double width = scanner.nextDouble();
                    System.out.print("Enter height: ");
                    double height = scanner.nextDouble();
                    main = new Rectangle(width, height);
                } else {
                    System.out.println("Error");
                    continue;
                }

                scanner.nextLine();
                output.writeObject(main);
                String response = (String) input.readObject();
                System.out.println(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
