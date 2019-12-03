import java.net.*;
import java.util.HashMap;
import java.util.Map;

class Server {
    private int port;
    private String ip;
    private static Map<ListeningServer, String> socketList = new HashMap<>();

    Server(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    void server() throws Exception{
        InetAddress IpAdress = InetAddress.getByName(ip);
        ServerSocket server = new ServerSocket(port, 0, IpAdress);
        try {
            while (true) {
                Socket socket = server.accept();
                ListeningServer listeningServer = new ListeningServer(socket, (HashMap<ListeningServer, String>) socketList);
                listeningServer.setListeningServer(listeningServer);
            }
        }finally {
            System.out.println("Server was closed");
            server.close();
        }
    }
}