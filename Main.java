import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        //System.out.print("Enter IP address: ");
        //String ip = input.nextLine();

        //System.out.print("Enter port: ");
        //int port = Integer.parseInt(input.nextLine());

        Server server = new Server("localhost", 5555);
        server.server();
    }
}