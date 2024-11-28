import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket s = new Socket("localhost", 1111);
             ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(s.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.println("Shape Circle Rectangle or Q:");
                String a = scanner.nextLine();

                if (a.equalsIgnoreCase("Q")) {
                    out.writeObject("Q");
                    break;
                }


                Main data = null;
                if (a.equalsIgnoreCase("Circle")) {
                    System.out.print("Radius: ");
                    double radius = scanner.nextDouble();
                    data = new Circle(radius);
                } else if (a.equalsIgnoreCase("Rectangle")) {
                    System.out.print("Width: ");
                    double width = scanner.nextDouble();
                    System.out.print("Height: ");
                    double height = scanner.nextDouble();
                    data = new Rectangle(width, height);
                } else {
                    System.out.println("Error");
                    continue;
                }

                scanner.nextLine();
                out.writeObject(data);
                String reply = (String) in.readObject();
                System.out.println(reply);
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
    }
}
