package models;

import Componentes.Utils;

public class Propietario {
    private static int numId = 1;
    private String id;
    private String nombre;
    private String nombreUsuario;
    private String clave;
    private String numTlf;
    private Vivienda vivienda;

    public Propietario(String nombre, String nombreUsuario, String clave, String numTlf) {
        this.id = "002" + numId;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.clave = Fernanbnb.codificaClave(clave);
        this.numTlf = numTlf;
        this.vivienda = null;
        numId++;
    }

    //Getter and Setters

    public static int getNumId() {
        return numId;
    }

    public static void setNumId(int numId) {
        Propietario.numId = numId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNumTlf() {
        return numTlf;
    }

    public void setNumTlf(String numTlf) {
        this.numTlf = numTlf;
    }

    public Vivienda getVivienda() {
        return vivienda;
    }

    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }


    //metodos

    public String alquilar(Reserva reserva) {
        if (vivienda.getReserva1() == null) {
            vivienda.setReserva1(reserva);
        }
        if (vivienda.getReserva2() == null) vivienda.setReserva2(reserva);
        return null;
    }

    //Metodo para combrobar el login
    public boolean login(String usuarioTemp, String claveTemp) {
        return this.nombreUsuario.equals(usuarioTemp) && this.clave.equals(Fernanbnb.codificaClave(claveTemp));
    }
    //Metodos para cambiar el perfil
    public void cambiaNombre(String nombre) {
        this.nombre = nombre;
    }

    public void cambiaNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void cambiaClave(String clave) {
        this.clave = Fernanbnb.codificaClave(clave);
    }
    public void cambiaNumeroTelefono(String numTlf) {
        this.numTlf = numTlf;
    }



    @Override
    public String toString() {
        return "" +
                " \n┌───────────────────┤ ID " + id + " ├───────────────────┐\n" +
                "   -Nombre: " + nombre + "\n" +
                "   -Nombre de Usuario: " + nombreUsuario + "\n" +
                "   -Contraseña: " + clave + "\n" +
                "   -Móvil: " + numTlf + "\n" +
                " └─────────────────────────────────────────────────┘\n";

    }
}


