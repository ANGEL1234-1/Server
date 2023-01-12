import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

public class ServerThread extends Thread {
    private String uname = "";

    private Socket socket;
    private ArrayList<ServerThread> clients;
    private BufferedReader input;
    private PrintWriter output;

    public ServerThread(Socket socket, ArrayList<ServerThread> clients) {
        this.socket = socket;
        this.clients = clients;
    }

    @Override
    public void run() {
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            uname = input.readLine();

            try {
                String msg = "";
                broadcast(uname + " has just connected, say hi [" + clients.size() + " user connected as of right now]");

                while (!msg.equals("\\exit")) {
                    msg = input.readLine();
                    if (!msg.equals("\\exit")) {
                        broadcast(uname, msg);
                    }
                }
            } catch (IOException e) {

            } catch (NullPointerException e) {

            } finally {
                clients.remove(this);
                broadcast("> User " + uname + " disconected [" + clients.size() + " user remaining]");
                try {
                    socket.close();
                } catch (IOException e) {

                }
            }
        } catch (IOException e) {
            clients.remove(this);
            try {
                socket.close();
            } catch (IOException f) {
                System.exit(0);
            }
        }

    }

    public void broadcast(String uname, String msg) {
        System.out.println(uname + ": " + msg);
        for (ServerThread client: clients) {
            if (!client.equals(this)) {
                client.output.println(uname + ": " + msg);
            }
        }
    }

    public void broadcast(String msg) {
        System.out.println(msg);
        for (ServerThread client: clients) {
            client.output.println(msg);
        }
    }
}
