import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    ArrayList<ServerThread> clients = new ArrayList<>();
    ServerSocket serverSocket;

    public Server() {

    }

    public void start() {
        try {
            serverSocket = new ServerSocket(54321);
            System.out.println("> Server online");
            while (true) {
                Socket socket = serverSocket.accept();
                clients.add(new ServerThread(socket, clients));
                clients.get(clients.size() - 1).start();
            }
        } catch (IOException e) {
            System.out.println("Server error " + e);
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.out.println("Ups...");
            }
        }
    }
}
