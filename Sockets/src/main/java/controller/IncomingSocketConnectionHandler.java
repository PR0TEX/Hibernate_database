package controller;

import Message.Message;
import Protocol.Protocol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class IncomingSocketConnectionHandler implements Runnable{

    private final Socket socket;
    private int clientID;
    private int n;
    private String outMsg;
    public IncomingSocketConnectionHandler(Socket socket, int clientID){
        this.socket = socket;
        this.clientID = clientID;
    }

    @Override
    public void run() {
        try(ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream())){
            System.out.println("Welcome "+clientID);
            Protocol protocol = new Protocol();

            outMsg = protocol.accessInput(null);
            System.out.println(outMsg+" to client"+clientID);
            output.writeObject(outMsg);

            n = (Integer) input.readObject();
            outMsg = protocol.accessInput(Integer.toString(n));
            System.out.println(outMsg+" to client"+clientID);
            output.writeObject(outMsg);

            for(int i=0;i<n;i++){
                Message message = (Message) input.readObject();
                outMsg = protocol.accessMessages(message);
                System.out.println(outMsg+" to client"+clientID);
                output.writeObject(outMsg);
            }

            outMsg = protocol.accessInput(null);
            System.out.println(outMsg+" to client"+clientID);
            output.writeObject(outMsg);
        }catch (IOException ex){
            ex.printStackTrace();
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }finally{
            try{
                if(!socket.isClosed()){ socket.close(); }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

}
