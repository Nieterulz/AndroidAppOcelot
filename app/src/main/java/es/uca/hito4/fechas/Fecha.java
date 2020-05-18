package es.uca.hito4.fechas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fecha {
    private Date fecha;
    private Date fechaIniEvento;

    public Fecha(int dia, int mes, int anno) {
        fecha = new Date(dia, mes, anno);
        fechaIniEvento = new Date(15, 8, 2020);
    }

    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(fecha);
        return strDate;
    }

    public boolean validarFecha()
    {
        return fechaIniEvento.before(fecha);
    }
}
