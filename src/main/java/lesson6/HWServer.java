package lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HWServer {

    public static void main(String[] args) {
        Socket socket = null;

        try (ServerSocket serverSocket = new ServerSocket(8089)) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Сервер ожидает подключения... ");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился!");
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            Thread thread = new Thread(() -> {
                while (true){
                    try {
                        dataOutputStream.writeUTF(scanner.nextLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
            while (true){
                String message = dataInputStream.readUTF();
                System.out.println("Cl: " + message);
                if (message.equals("/end")) {
                    dataOutputStream.writeUTF("/end");
                    break;
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}