package es.uca.hito4;

import java.util.ArrayList;
import java.util.Date;

public class Asistente {
    private String nombre;
    private String dni;
    private String telefono;
    private Date f_nac;
    private Date f_ins;

    public Asistente(String nombre, String dni, String telefono, Date f_nac, Date f_ins){
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.f_nac = f_nac;
        this.f_ins = f_ins;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getF_nac() {
        return f_nac;
    }

    public void setF_nac(Date f_nac) {
        this.f_nac = f_nac;
    }

    public Date getF_ins() {
        return f_ins;
    }

    public void setF_ins(Date f_ins) {
        this.f_ins = f_ins;
    }

    public static ArrayList<Asistente> getData()
    {
        return new ArrayList<Asistente>();
    }
}
