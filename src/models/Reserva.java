package models;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.MONTHS;

public class Reserva {
    private static int numId = 1;
    private String id;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private long numNoches;
    private Vivienda vivienda;
    private Usuario usuario;


    public Reserva(LocalDate fechaInicio, LocalDate fechaFinal, Vivienda vivienda, Usuario usuario) {
        this.id = "696" + numId;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.numNoches = MONTHS.between(fechaFinal, fechaInicio);
        this.vivienda = vivienda;
        this.usuario = usuario;
        numId++;
    }

    public static int getNumId() {
        return numId;
    }

    public static void setNumId(int numId) {
        Reserva.numId = numId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public long getNumNoches() {
        return numNoches;
    }

    public void setNumNoches(long numNoches) {
        this.numNoches = numNoches;
    }

    public Vivienda getVivienda() {
        return vivienda;
    }

    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int alquilarEntreDias(LocalDate fechaInicio, LocalDate fechaFinal){
        if (fechaInicio.isBefore(fechaFinal)) return 1;
        if (fechaInicio.isEqual(fechaFinal)) return 2;
        return -1;
    }



    @Override
    public String toString() {
        return
                " ┌───────────────────────┤ Reserva con ID:" + id + " ├────────────────────────┐\n" +
                        "   -Usuario: " + usuario.getNombre() + "\n" +
                        "   -Vivienda: " + vivienda.getDireccion().getLocalidad() + " (ID: "+vivienda.getId()+")\n" +
                        "   -Noches: " + numNoches + "\n" +
                        "   -Fecha de entrada: " + fechaInicio + "\n" +
                        "   -Fecha de salida: " + fechaFinal + "\n" +
                        "   -Precio por noche: " + vivienda.getPrecioNoche() + "E; Precio total: " + vivienda.getPrecioNoche()*numNoches + "E\n" +
                        " └────────────────────────────────────────────────────────────────────────┘\n";

    }
}

