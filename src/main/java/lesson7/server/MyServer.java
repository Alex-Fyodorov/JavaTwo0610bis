package lesson7.server;

import lesson7.constants.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Логика сервера.
 */
public class MyServer {

    /**
     * Сервис аутентификации.
     */
    private AuthService authServiсe;

    /**
     * Активные клиенты.
     */
    private List<ClientHandler> clients;



    public AuthService getAuthServiсe() {
        return authServiсe;
    }

    public MyServer(){
        try (ServerSocket server = new ServerSocket(Constants.SERVER_PORT)){
            authServiсe = new BaseAuthService();
            authServiсe.start();

            clients = new ArrayList<>();

            while (true){
                System.out.println("Сервер ожидает подключения");
                Socket socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);
            }

        } catch (IOException ex){
            System.out.println("Ошибка в работе сервера.");
            ex.printStackTrace();
        } finally {
            if (authServiсe != null) {
                authServiсe.stop();
            }
        }
    }

    public synchronized void broadcastMessage(String message) {

        clients.forEach(client -> client.sendMessage(message));

        /*for (ClientHandler client : clients) {
            client.sendMessage(message);
        }*/
    }

    public synchronized void sendPersonalMessage(String nick, String author, String message) {

        for (ClientHandler client : clients){
            if (client.getName().equals(nick)) {
                client.sendMessage(message);
                return;
            }
        }
        for (ClientHandler client : clients){
            if (client.getName().equals(author)) {
                client.sendMessage("Адресат не найден.");
                return;
            }
        }
    }

    public synchronized void subscribe(ClientHandler client){
        clients.add(client);
        broadcastMessage(printClientsList());
    }

    public synchronized void unsubscribe(ClientHandler client){
        clients.remove(client);
        broadcastMessage(printClientsList());
    }

    public boolean isNickBusy(String nick) {
        for (ClientHandler client : clients) {
            if (client.getName().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public synchronized String printClientsList(){
        StringBuilder nicksList = new StringBuilder(Constants.LIST_COMMAND);
        for (ClientHandler cl : clients){
            nicksList.append(" " + cl.getName());
        }
        return nicksList.toString();
    }
}