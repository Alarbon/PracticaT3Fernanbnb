package models;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

public class Fernanbnb {
    private Usuario u1;
    private Usuario u2;
    private Propietario p1;
    private Propietario p2;
    private Administrador admin;

    public Fernanbnb() {

    }
    //Getter and Setters

    public Usuario getU1() {
        return u1;
    }

    public void setU1(Usuario u1) {
        this.u1 = u1;
    }

    public Usuario getU2() {
        return u2;
    }

    public void setU2(Usuario u2) {
        this.u2 = u2;
    }

    public Propietario getP1() {
        return p1;
    }

    public void setP1(Propietario p1) {
        this.p1 = p1;
    }

    public Propietario getP2() {
        return p2;
    }

    public void setP2(Propietario p2) {
        this.p2 = p2;
    }

    public Administrador getAdmin() {
        return admin;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }

    // Método para el login que devuelve, los 1 para usuario, los 2 para propietario y 3 para admin, -1 ninguno
    public int logging(String usuarioLogin, String claveLogin) {
        if (u1 != null && u1.login(usuarioLogin, claveLogin)) {
            return 1;
        }
        if (u2 != null && u2.login(usuarioLogin, claveLogin)) {
            return 11;
        }
        if (p1 != null && p1.login(usuarioLogin, claveLogin)) {
            return 2;
        }
        if (p2 != null && p2.login(usuarioLogin, claveLogin)) {
            return 22;
        }
        if (admin != null && admin.login(usuarioLogin, claveLogin)) {
            return 3;
        } else return -1;
    }

    public boolean nuevoUsuario(Usuario usuario) {
        if (this.u1 == null) {
            this.u1 = usuario;
            return true;
        }
        if (this.u2 == null) {
            this.u2 = usuario;
            return true;
        }
        return false;
    }

    public boolean nuevoPropietario(Propietario propietario) {
        if (this.p1 == null) {
            this.p1 = propietario;
            return true;
        }
        if (this.p2 == null) {
            this.p2 = propietario;
            return true;
        }
        return false;
    }

    public boolean nuevoAdmin(Administrador admin) {
        if (this.admin == null) {
            this.admin = admin;
            return true;
        }
        return false;
    }

    public String devuelveNombre(int num) {
        if (num == 1) {
            return u1.getNombre();
        }
        if (num == 11) {
            return u2.getNombre();
        }
        if (num == 2) {
            return p1.getNombre();
        }
        if (num == 22) {
            return p2.getNombre();
        }
        if (num == 3) {
            return admin.getNombre();
        }
        return null;
    }

    //Metodo para comprobar que el nombre de usuario es unico y está disponible
    public boolean compruebaUsuarioDisponible(String usuario) {
        return (u1 == null || !usuario.equals(u1.getNombreUsuario())) && (u2 == null || !usuario.equals(u2.getNombreUsuario())) && (p1 == null || !usuario.equals(p1.getNombreUsuario())) && (p2 == null || !usuario.equals(p2.getNombreUsuario())) && (admin == null || !usuario.equals(admin.getNombreUsuario()));
    }

    public boolean registrarUsuario(String nombre, String nombreUsuario, String clave, String numTlf) {
        if (u1 != null && u2 != null) return false;
        if (u1 == null) {
            this.u1 = new Usuario(nombre, nombreUsuario, clave, numTlf);
        } else {
            this.u2 = new Usuario(nombre, nombreUsuario, clave, numTlf);
        }
        return true;

    }

    public boolean registrarPropietario(String nombre, String nombreUsuario, String clave, String numTlf) {
        if (p1 != null && p2 != null) return false;
        if (p1 == null) {
            p1 = new Propietario(nombre, nombreUsuario, clave, numTlf);
        } else {
            p2 = new Propietario(nombre, nombreUsuario, clave, numTlf);
        }
        return true;

    }

    public boolean registrarAdmin(String nombre, String nombreUsuario, String clave, String numTlf) {
        if (admin != null) return false;
        else {
            admin = new Administrador(nombre, nombreUsuario, clave, numTlf);
            return true;
        }
    }

    //Metodo para validar número de telefono
    public boolean validaMovil(String movil) {
        return movil.length() == 9 && movil.matches("[0-9]+");
    }

    //Metodo para codificar la clave
    public static String codificaClave(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashText = number.toString(16);

            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }
            return hashText;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    //Metodos para cambiar el perfil
    public void modificaNombre(int num, String nombre) {
        switch (num) {
            case 1 -> u1.cambiaNombre(nombre);
            case 11 -> u2.cambiaNombre(nombre);
            case 2 -> p1.cambiaNombre(nombre);
            case 22 -> p2.cambiaNombre(nombre);
            case 3 -> admin.cambiaNombre(nombre);
        }
    }

    public void modificaNombreUsuario(int num, String nombreUsuario) {
        switch (num) {
            case 1 -> u1.cambiaNombreUsuario(nombreUsuario);
            case 11 -> u2.cambiaNombreUsuario(nombreUsuario);
            case 2 -> p1.cambiaNombreUsuario(nombreUsuario);
            case 22 -> p2.cambiaNombreUsuario(nombreUsuario);
            case 3 -> admin.cambiaNombreUsuario(nombreUsuario);
        }
    }

    public void modificaClave(int num, String clave) {
        switch (num) {
            case 1 -> u1.cambiaClave(clave);
            case 11 -> u2.cambiaClave(clave);
            case 2 -> p1.cambiaClave(clave);
            case 22 -> p2.cambiaClave(clave);
            case 3 -> admin.cambiaClave(clave);
        }
    }

    public void modificaNumeroTelefono(int num, String numTlf) {
        switch (num) {
            case 1 -> u1.cambiaNumeroTelefono(numTlf);
            case 11 -> u2.cambiaNumeroTelefono(numTlf);
            case 2 -> p1.cambiaNumeroTelefono(numTlf);
            case 22 -> p2.cambiaNumeroTelefono(numTlf);
            case 3 -> admin.cambiaNumeroTelefono(numTlf);
        }

    }

    // Método para devolver el nombre de los usuarios que se han registrado, devuelve 1 o 11 o -1
    public int devuelveUsuarios() {
        if (u1 != null && u2 == null) {
            return 1;
        }
        if (u1 != null && u2 != null) {
            return 11;

        } else return -1;
    }

    // Método para devolver el nombre de los usuarios Propietarios que se han registrado, devuelve 2 o 22 o -1
    public int devuelveUsuariosPropietarios() {
        if (p1 != null && p2 == null) {
            return 2;
        }
        if (p1 != null && p2 != null) {
            return 22;

        } else return -1;
    }

    //Método para añadir una vivienda
    public void aniadeVivienda(int num, Vivienda vivienda) {
        switch (num) {
            case 2 -> p1.setVivienda(vivienda);
            case 22 -> p2.setVivienda(vivienda);
        }
    }
    public Vivienda compruebaLocalidad(String localidad) {
        if (p1 != null && p1.getVivienda() != null && p1.getVivienda().getDireccion().getLocalidad().equals(localidad))
            return p1.getVivienda();
        if (p2 != null && p2.getVivienda() != null && p2.getVivienda().getDireccion().getLocalidad().equals(localidad))
            return p2.getVivienda();
        return null;
    }

    public Vivienda compruebaHuesped(int huespedes, String localidad) {
        if (p1 != null && p1.getVivienda() != null && compruebaLocalidad(localidad) != null && p1.getVivienda().getHuespedMax() >= huespedes)
            return p1.getVivienda();
        if (p2 != null && p2.getVivienda() != null && compruebaLocalidad(localidad) != null && p2.getVivienda().getHuespedMax() >= huespedes)
            return p2.getVivienda();
        return null;

    }

    public boolean compruebaFechaParaReserva(LocalDate fechaIniConsole, LocalDate fechaFinConsole, int huespedes, String localidad) {

        if (compruebaHuesped(huespedes, localidad) != null && u1 != null && u1.getReserva1() != null &&
                u1.getReserva1().getVivienda() == compruebaHuesped(huespedes, localidad) &&
                (!estaFueraDeRango(fechaIniConsole, fechaFinConsole, u1.getReserva1().getFechaInicio(), u1.getReserva1().getFechaFinal())) || p1 != null && p1.getVivienda().getReserva1() != null &&
                (!estaFueraDeRango(fechaIniConsole, fechaFinConsole, p1.getVivienda().getReserva1().getFechaInicio(), p1.getVivienda().getReserva1().getFechaFinal())))
            return false;


        if (compruebaHuesped(huespedes, localidad) != null && u1 != null && u1.getReserva2() != null &&
                u1.getReserva2().getVivienda() == compruebaHuesped(huespedes, localidad) &&
                (!estaFueraDeRango(fechaIniConsole, fechaFinConsole, u1.getReserva2().getFechaInicio(), u1.getReserva2().getFechaFinal())) || p1 != null && p1.getVivienda().getReserva2() != null &&
                (!estaFueraDeRango(fechaIniConsole, fechaFinConsole, p1.getVivienda().getReserva2().getFechaInicio(), p1.getVivienda().getReserva2().getFechaFinal())))
            return false;

        if (compruebaHuesped(huespedes, localidad) != null && u2 != null && u2.getReserva1() != null &&
                u2.getReserva1().getVivienda() == compruebaHuesped(huespedes, localidad) &&
                (!estaFueraDeRango(fechaIniConsole, fechaFinConsole, u2.getReserva1().getFechaInicio(), u2.getReserva1().getFechaFinal())) || p2 != null && p2.getVivienda().getReserva1() != null &&
                (!estaFueraDeRango(fechaIniConsole, fechaFinConsole, p2.getVivienda().getReserva1().getFechaInicio(), p2.getVivienda().getReserva1().getFechaFinal())))
            return false;

        if (compruebaHuesped(huespedes, localidad) != null && u2 != null && u2.getReserva2() != null &&
                u2.getReserva2().getVivienda() == compruebaHuesped(huespedes, localidad) &&
                (!estaFueraDeRango(fechaIniConsole, fechaFinConsole, u2.getReserva2().getFechaInicio(), u2.getReserva2().getFechaFinal())) || p2 != null && p2.getVivienda().getReserva2() != null &&
                (!estaFueraDeRango(fechaIniConsole, fechaFinConsole, p2.getVivienda().getReserva2().getFechaInicio(), p2.getVivienda().getReserva2().getFechaFinal())))
            return false;

        return true;


    }

    public static boolean estaFueraDeRango(LocalDate fecha, LocalDate fecha2, LocalDate inicio, LocalDate fin) {
        return (fecha.isBefore(inicio) && fecha2.isBefore(inicio)) || (fecha.isAfter(fin) && fecha2.isAfter(fin));

    }


    public boolean anioadeReserva(Reserva reserva, Vivienda vivienda, int numGuardaUsuario) {
        Propietario prop = null;
        Usuario user = null;
        boolean tipoPropietario = false;
        boolean tipoUsuario = false;
        if (p1 != null && vivienda.equals(p1.getVivienda())) {
            prop = p1;
            tipoPropietario = true;
        } else if (p2 != null && vivienda.equals(p2.getVivienda())) {
            prop = p2;
            tipoPropietario = false;
        }
        if (numGuardaUsuario == 1) {
            user = u1;
            tipoUsuario = true;
        } else if (numGuardaUsuario == 11) {
            user = u2;
            tipoUsuario = false;
        }
        if (prop != null && user != null) {
            if (user.getReserva1() == null) {
                user.setReserva1(reserva);
                prop.getVivienda().setReserva1(reserva);
            } else if (user.getReserva2() == null) {
                user.setReserva2(reserva);
                prop.getVivienda().setReserva2(reserva);
            }
            if (tipoPropietario == true) {
                p1 = prop;
            } else if (tipoPropietario == false) {
                p2 = prop;
            }
            if (tipoUsuario == true) {
                u1 = user;
            } else if (tipoUsuario == false) {
                u2 = user;
            }

            return true;
        }
        return false;
    }


}






