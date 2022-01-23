package lesson7.constants;

public class Constants {
    /**
     * Адрес сервера.
     */
    public static final String SERVER_ADDRESS = "localhost";
    /**
     * Порт сервера.
     */
    public static final int SERVER_PORT = 8189;

    /**
     * Команда конца связи.
     */
    public static final String END_COMMAND = "/end";
    /**
     * Команда аутентификации.
     */
    public static final String AUTH_COMMAND = "/auth";
    /**
     * Команда подтверждения аутентификации.
     */
    public static final String AUTH_OK_COMMAND = "/authok";
    /**
     * Команда для отправки личных сообщений.
     */
    public static final String PERSONAL_COMMAND = "/w";
    /**
     * Команда для отправки списка подключенных клиентов.
     */
    public static final String LIST_COMMAND = "/l";
    /**
     * Время для авторизации.
     */
    public static final long AUTH_TIME = 10000;
}