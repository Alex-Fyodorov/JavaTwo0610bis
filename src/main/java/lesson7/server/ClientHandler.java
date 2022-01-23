package lesson7.server;

import lesson7.constants.Constants;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Обработчик для конкретного клиента.
 */
public class ClientHandler {

    private MyServer server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String name = "";

    public String getName() {
        return name;
    }

    public ClientHandler(MyServer server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    new Thread(() ->{
                        try {
                            Thread.sleep(Constants.AUTH_TIME);
                            if (name.equals("")) {
                                closeConnection();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }).start();
                    authentification();
                    readMessage();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException ex) {
            throw new RuntimeException("Проблемы при создании обработчика.");
        }
    }

    private void authentification() throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.startsWith(Constants.AUTH_COMMAND)) {
                String[] tokens = str.split("\\s+");
                String nick = server.getAuthServiсe().getNickByLoginAndPass(tokens[1], tokens[2]);
                if (nick != null) {
                    if (server.isNickBusy(nick)) {
                        sendMessage("Пользователь уже в чате.");
                    } else {
                        name = nick;
                        sendMessage(Constants.AUTH_OK_COMMAND + " " + nick);
                        server.broadcastMessage(nick + " вошёл в чат.");
                        server.subscribe(this);
                        return;
                    }
                } else {
                    sendMessage("Неверные логин/пароль.");
                }
            }
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMessage() throws IOException {
        while (true) {
            String messageFromClient = in.readUTF();
            System.out.println("Сообщение от " + name + ": " + messageFromClient);
            if (messageFromClient.equals(Constants.END_COMMAND)) {
                server.sendPersonalMessage(name, name, Constants.END_COMMAND);
                break;
            }
            if (messageFromClient.startsWith(Constants.PERSONAL_COMMAND)) {
                String[] words = messageFromClient.split("\\s+");
                String newMessage = "";
                for (int i = 2; i < words.length; i++){
                    newMessage = newMessage + " " + words[i];
                }
                server.sendPersonalMessage(words[1], name, name +
                        " (личн.): " + newMessage);
            } else {
                server.broadcastMessage(name + ": " + messageFromClient);
            }
        }
    }

    private void closeConnection() {
        server.unsubscribe(this);
        server.broadcastMessage(name + "вышел из чата.");
        try {
            in.close();
        } catch (IOException ex) {

        }
        try {
            out.close();
        } catch (IOException ex) {

        }
        try {
            socket.close();
        } catch (IOException ex) {

        }
    }
}