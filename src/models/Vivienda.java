package models;

public class Vivienda {
    private static int numId = 1;
    private String id;
    private Direccion direccion;
    private double precioNoche;
    private int huespedMax;
    private String tipoVivienda;
    private Reserva reserva1;
    private Reserva reserva2;

    public Vivienda(Direccion direccion, double precioNoche, int huespedMax, int numTipoVivienda) {
        this.id = "111" + numId;
        this.direccion = direccion;
        this.precioNoche = precioNoche;
        this.huespedMax = huespedMax;
        if (numTipoVivienda == 1) this.tipoVivienda = "Piso";
        else if (numTipoVivienda == 2) this.tipoVivienda = "Casa";
        numId++;
    }

    public static int getNumId() {
        return numId;
    }

    public static void setNumId(int numId) {
        Vivienda.numId = numId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }


    public int getHuespedMax() {
        return huespedMax;
    }

    public void setHuespedMax(int huespedMax) {
        this.huespedMax = huespedMax;
    }

    public String getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(String tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    public Reserva getReserva1() {
        return reserva1;
    }

    public void setReserva1(Reserva reserva1) {
        this.reserva1 = reserva1;
    }

    public Reserva getReserva2() {
        return reserva2;
    }

    public void setReserva2(Reserva reserva2) {
        this.reserva2 = reserva2;
    }

    @Override
    public String toString() {
        return
                " \n ┌───────────────────┤ Alojamiento con ID: " + id + " ├───────────────────┐\n" +
                        "   -Vivienda: " + tipoVivienda + " en " + direccion.getLocalidad() + "\n" +
                        "   -Dirección: " + direccion.toString() + "\n" +
                        "   -Número de huéspedes máximos: " + huespedMax + "\n" +
                        "   -Precio por noche: " + precioNoche + "E\n" +
                " └──────────────────────────────────────────────────────────────────┘\n";

    }

}
