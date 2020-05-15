package es.uca.hito4;

public class Asistente {
    private String _id;
    private String nombre;

    public Asistente(String _id, String nombre){
        this._id = _id;
        this.nombre = nombre;
    }

    public String getId() {
        return _id;
    }

    public String getNombre() {
        return nombre;
    }
}
