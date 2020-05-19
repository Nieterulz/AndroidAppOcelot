package es.uca.hito4.operaciones;

public class UrlServer {
    private static String SERVER = "http://192.168.8.101:8080/asistentes";

    public static String getSERVER() {
        return SERVER;
    }

    public static void setSERVER(String SERVER) {
        UrlServer.SERVER = SERVER;
    }
}
