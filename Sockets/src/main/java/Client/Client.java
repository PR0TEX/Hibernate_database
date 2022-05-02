package Client;

import Message.Message;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
            int port = 999;
            Socket socket = new Socket("localhost",port);
            Scanner read = new Scanner(System.in);

            try (ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())){

                String response = (String) inputStream.readObject();
                System.out.println(response);
                String userCommand = read.nextLine();
                Integer n = Integer.parseInt(userCommand);
                outputStream.writeObject(n);

                response = (String) inputStream.readObject();
                System.out.println(response);

                for(int i=1;i<=n;i++){
                    System.out.println("Enter new message "+i);
                    String x = read.nextLine();
                    outputStream.writeObject(new Message(i,x));
                    response = (String) inputStream.readObject();
                    System.out.println(response);
                }

                response = (String) inputStream.readObject();
                System.out.println(response);

            }catch (IOException ex){
                System.out.format("Error Client",ex.getMessage());
            }catch (ClassNotFoundException ex){
                ex.printStackTrace();
            }
    }
}
