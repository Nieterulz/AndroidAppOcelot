package es.uca.hito4;

public class UrlServer {
    private static String SERVER = "http://192.168.8.102:8080/asistentes";

    public static String getSERVER() {
        return SERVER;
    }

    public static void setSERVER(String SERVER) {
        UrlServer.SERVER = SERVER;
    }
}
