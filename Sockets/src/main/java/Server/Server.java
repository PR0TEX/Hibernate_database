package Server;

import controller.IncomingSocketConnectionHandler;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) throws IOException{
        int port = 999;
        int nbOfClients = 1;

        try(ServerSocket serverSocket = new ServerSocket(port)){
            while(true){
                new Thread(new IncomingSocketConnectionHandler(serverSocket.accept(),nbOfClients++)).start();
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
