package Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;

    public static int countClient = 0;
    public static int n =1;
    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void openServer() {

        System.out.println("Server is open");
        try{

            while (!serverSocket.isClosed()){

                Socket socket = serverSocket.accept();

                System.out.print("player "+n+" connect ");
                n++;
                ClientHandler clientHandler = new ClientHandler(socket);

                // add each client to server for multi client
                Thread thread = new Thread(clientHandler);
                thread.start();

            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9999);

        // constructor server
        Server server = new Server(serverSocket);
        server.openServer();

    }

}
