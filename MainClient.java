import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) throws Exception{
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = input.nextLine();

        //System.out.print("Enter IP address: ");
        //String ip = input.readLine();

        //System.out.print("Enter port: ");
        //int port = Integer.parseInt(input.readLine());

        Client client = new Client("localhost", 5555, name);
        client.client();
    }
}