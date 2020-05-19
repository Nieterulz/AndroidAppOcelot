package es.uca.hito4.fechas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Fecha {
    private Date fecha;
    private String evento;

    public Fecha(int dia, int mes, int anno, String evento) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, anno);
        cal.set(Calendar.MONTH, mes-1);
        cal.set(Calendar.DAY_OF_MONTH, dia);
        fecha = cal.getTime();
        this.evento = evento;
    }

    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(fecha);
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public boolean validarFecha()
    {
        Date today = new Date();
        return today.before(fecha);
    }

    public int getDifDias()
    {
        Date today = new Date();
        return (int)((fecha.getTime() - today.getTime())/86400000);
    }
}
