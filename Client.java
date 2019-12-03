import java.io.*;
import java.net.Socket;
import java.util.Scanner;

class Client {
    private String name;
    private int port;
    private String ip;

    // private BufferedReader str = new BufferedReader(new
    // InputStreamReader(System.in));
    private DataInputStream in;
    private DataOutputStream out;

    Client(String ip, int port, String name) {
        this.ip = ip;
        this.port = port;
        this.name = name;
    }

    void client() throws IOException {
        try {
            Socket socket = new Socket(ip, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            send(name);
            while (!socket.isClosed()) {

                System.out.println(in.readUTF());
                InnerWrite innerWrite = new InnerWrite();
                InnerRead innerRead = new InnerRead();
                // String message = str.nextLine();
                // send(message);

            }
        } catch (Exception ex) {
        } finally {
            out.close();
            // str.close();
            System.out.println("Client " + name + " was disconnected");
        }
    }

    private void send(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException ignored) {
        }
    }

    private class InnerRead extends Thread {
        private Scanner str = new Scanner(System.in);

        InnerRead() {
            start();
        }

        @Override
        public void run() {
            String msg = str.nextLine();
            send(msg);
        }
    }

    private class InnerWrite extends Thread {
        InnerWrite() {
            start();
        }

        @Override
        public void run() {
            try {
                System.out.println(in.readUTF());
            } catch (IOException e) { }
        }
    }

}