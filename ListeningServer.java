import java.io.*;
import java.net.Socket;
import java.util.*;

public class ListeningServer extends Thread {

    private Socket socket;
    private String name;
    private DataOutputStream out;
    private DataInputStream in;
    private Map<ListeningServer, String> socketList;
    private ListeningServer listeningServer;

    public ListeningServer(Socket socket, HashMap<ListeningServer, String> socketList) throws IOException {
        this.socket = socket;
        this.socketList = socketList;
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
        start();
    }

    public void setListeningServer(ListeningServer listeningServer) {
        this.listeningServer = listeningServer;
    }

    @Override
    public void run() {
        try {
            try {
                name = in.readUTF();
                socketList.put(listeningServer, name);

                sender(name + " joined the chat", null, 0);

                while (true) {
                    String str = in.readUTF();
                    
                    
                    if (str.startsWith("@name ")) {
                        String prevName = name;
                        name = str.substring(6);
                        sender(prevName + " -> " + name, null, 0);
                        continue;
                    } else if (str.startsWith("@senduser ")) {
                        String user = str.substring(10,str.indexOf(" ", 10));
                        str = str.substring(str.indexOf(" ", 10) + 1);
                        sender(name + ": " + str, user, 1);
                        continue;
                    }

                    sender(name + ": " + str, null, 0);
                }
            } catch (IOException ex) {
                sender(name + " was disconnected", null, 0);
                this.closeService();
            }
        } catch (Exception ex) {
        }
    }

    private void send(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException ignored) {
        }
    }

    private void sender(String str, String toWhom, int flag) {

        for (Map.Entry<ListeningServer, String> entry : socketList.entrySet()) {
            ListeningServer key = (ListeningServer) ((Map.Entry) entry).getKey();
            String value = entry.getValue();

            switch (flag) {
            case 1:
                System.out.println("Msg to " + toWhom);
                if(value.equals(toWhom)){
                    key.send(str);
                    break;
                }
                break;
            default:
                System.out.println("Msg to everyone: " + str);
                key.send(str);
                break;
            }
        }
    }

    private void closeService(){
        try {
            if(!socket.isClosed()){
                socket.close();
                in.close();
                out.close();
            }
            for (ListeningServer vr : socketList.keySet()) {
                if(vr.equals(this)) vr.interrupt();
                socketList.remove(this);
            }
        }
        catch (Exception ignored){}
    }
}