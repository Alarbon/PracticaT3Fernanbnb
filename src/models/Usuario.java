package models;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Usuario {
    private static int numId = 1;
    private String id;
    private String nombre;
    private String nombreUsuario;
    private String clave;
    private String numTlf;
    private Reserva reserva1;
    private Reserva reserva2;

    //constructor
    public Usuario(String nombre, String nombreUsuario, String clave, String numTlf) {
        this.id = "001" + numId;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.clave = Fernanbnb.codificaClave(clave);
        this.numTlf = numTlf;
        this.reserva1 = null;
        this.reserva2 = null;
        numId++;
    }
    //GS


    public static int getNumId() {
        return numId;
    }

    public static void setNumId(int numId) {
        Usuario.numId = numId;
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


