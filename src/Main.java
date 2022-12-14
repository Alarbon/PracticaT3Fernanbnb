import Componentes.Menus;
import Componentes.Utils;
import models.*;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Usuario u1 = null;
        Usuario u2 = null;
        Propietario p1 = null;
        Propietario p2 = null;
        Administrador admin = null;
        Fernanbnb f = new Fernanbnb();
        Reserva reserva = null;
        Vivienda vivienda = null;
        Direccion direccion = null;
        f.nuevoUsuario(u1);
        f.nuevoUsuario(u2);
        f.nuevoPropietario(p1);
        f.nuevoPropietario(p2);
        f.nuevoAdmin(admin);
        LocalDate fechaInicio = null;
        LocalDate fechaFinal = null;

        String nombreLog = ""; //nombre de la persona loggeada
        int numGuardaUsuario;//detecta el tipo de usuario que es, normal, propietario o admin
        int op = 0;
        String nombre, nombreUsuario, clave, claveRepetida, usuarioLogin, claveLogin, numTlf, claveAdministrador = "AdminFurioso1324", claveAdministradorTeclado;
        String calleTeclado, localidadTeclado, localidadBusqueda, fechaInicioTeclado, fechaFinalTeclado;
        double precioNocheTeclado;
        int numDireccionTeclado, numCamasTeclado, numDormitorio, numHuespedMaxTeclado, numHuespedBusqueda;
        int numGuardaTipoVivienda = 0; //Asignaremos 1 si es piso o 2 si es casa
        Scanner s = new Scanner(System.in);

        do {
            boolean decision = false;
            System.out.println();
            System.out.println(Menus.menuInicio());
            System.out.print("Seleccione una opción: ");
            op = Integer.parseInt(s.nextLine());
            switch (op) {
                case 1 -> {//inicio sesión
                    System.out.print("Escriba el nombre de usuario: ");
                    usuarioLogin = s.nextLine();
                    System.out.print("Escriba la contraseña: ");
                    claveLogin = s.nextLine();
                    Utils.inicioSesion();
                    numGuardaUsuario = f.logging(usuarioLogin, claveLogin); //Guarda el numero de tipo de usuario
                    if (numGuardaUsuario == 1 || numGuardaUsuario == 11) { //Usuarios normales
                        do {
                            nombreLog = f.devuelveNombre(numGuardaUsuario);//asigna el nombre del usuario logueado
                            System.out.println(Menus.menuUsuario(nombreLog)); //Muestra el menu de usuario
                            System.out.print("Seleccione una opción: ");
                            op = Integer.parseInt(s.nextLine());
                            switch (op) {
                                case 1://Búsqueda de alojamientos
                                    System.out.print("Introduce una localidad para buscar: ");
                                    localidadBusqueda = s.nextLine().toUpperCase();
                                    if (f.compruebaLocalidad(localidadBusqueda) == null) {
                                        System.out.println("No hay alojamientos en esa localidad en estos momentos");
                                        break;
                                    }
                                    System.out.print("Introduce el número de huéspedes: ");
                                    numHuespedBusqueda = Integer.parseInt(s.nextLine());
                                    if (f.compruebaHuesped(numHuespedBusqueda, localidadBusqueda) == null) {
                                        System.out.println("No existen alojamientos con ese número de huéspedes");
                                        break;
                                    }

                                    System.out.print("Introduzca una fecha, en formato (yyyy-mm-dd): ");
                                    fechaInicioTeclado = s.nextLine();
                                    fechaInicio = LocalDate.parse(fechaInicioTeclado);
                                    System.out.print("Introduzca una fecha de salida, en formato (yyyy-mm-dd): ");
                                    fechaFinalTeclado = s.nextLine();
                                    fechaFinal = LocalDate.parse(fechaFinalTeclado);
                                    Utils.esperar();

                                    if (f.compruebaFechaParaReserva(fechaInicio, fechaFinal, numHuespedBusqueda, localidadBusqueda)) {
                                        System.out.println("La(s) vivienda(s) disponible(s) es/son: ");
                                        if (p1 != null && p1.getVivienda() != null && p1.getVivienda().getDireccion().getLocalidad().equals(localidadBusqueda)) {
                                            System.out.println(p1.getVivienda().toString());
                                        }
                                        if (p2 != null && p2.getVivienda() != null && p2.getVivienda().getDireccion().getLocalidad().equals(localidadBusqueda)) {
                                            System.out.println(p2.getVivienda().toString());
                                        }
                                        System.out.println("¡Si te ha interesado toma nota del ID! :)");
                                        System.out.println("Pulse enter para continuar.");
                                        s.nextLine();

                                        System.out.println("¿Quieres realizar la reserva? (Y/N)");
                                        String elec = s.nextLine().toUpperCase();
                                        if (elec.equals("Y")) {
                                            System.out.print("Escribe el ID del alojamiento que quiere reservar: ");
                                            String idTeclado = s.nextLine();
                                            if (p1.getVivienda().getId().equals(idTeclado)) {
                                                if (numGuardaUsuario == 1)
                                                    reserva = new Reserva(fechaInicio, fechaFinal, p1.getVivienda(), u1);
                                                if (numGuardaUsuario == 11)
                                                    reserva = new Reserva(fechaInicio, fechaFinal, p1.getVivienda(), u2);
                                                Utils.esperar();
                                                System.out.println(f.anioadeReserva(reserva, p1.getVivienda(), numGuardaUsuario) ? "Reserva realizada con éxito. " : "La reserva no se ha podido realizar, intentelo más tarde.");

                                            } else if (p2.getVivienda().getId().equals(idTeclado)) {
                                                if (numGuardaUsuario == 1)
                                                    reserva = new Reserva(fechaInicio, fechaFinal, p2.getVivienda(), u1);
                                                if (numGuardaUsuario == 11)
                                                    reserva = new Reserva(fechaInicio, fechaFinal, p2.getVivienda(), u2);
                                                Utils.esperar();
                                                System.out.println(f.anioadeReserva(reserva, p2.getVivienda(), numGuardaUsuario) ? "Reserva realizada con éxito. " : "La reserva no se ha podido realizar, intentelo más tarde.");
                                            }
                                        }

                                    } else {
                                        System.out.println("Estas fechas no están disponibles. ¡Prueba otras! :)");
                                    }
                                    System.out.println("Pulse enter para continuar.");
                                    s.nextLine();


                                    break;
                                case 2://Ver mis reservas
                                    if (numGuardaUsuario == 1) {
                                        if (u1.getReserva1() == null && u1.getReserva2() == null) {
                                            System.out.println("Usted no tiene ninguna reserva.");
                                        }
                                        if (u1.getReserva1() != null && u1.getReserva2() == null) {
                                            System.out.println("Esta es la reserva 1.");
                                            System.out.println(u1.getReserva1().toString());
                                        }
                                        if (u1.getReserva1() != null && u1.getReserva2() != null) {
                                            System.out.println("Esta es la reserva 1.");
                                            System.out.println(u1.getReserva1().toString());
                                            System.out.println();
                                            System.out.println("Esta es la reserva 2.");
                                            System.out.println(u1.getReserva2().toString());
                                        }
                                    }
                                    if (numGuardaUsuario == 11) {
                                        if (u2.getReserva1() == null && u2.getReserva2() == null)
                                            System.out.println("Usted no tiene ninguna reserva.");
                                        if (u2.getReserva1() != null && u2.getReserva2() == null)
                                            System.out.println("Esta es la reserva 1.");
                                        System.out.println(u2.getReserva1().toString());
                                        if (u2.getReserva1() != null && u2.getReserva2() != null) {
                                            System.out.println("Esta es la reserva 1.");
                                            System.out.println(u2.getReserva1().toString());
                                            System.out.println();
                                            System.out.println("Esta es la reserva 2.");
                                            System.out.println(u2.getReserva2().toString());
                                        }
                                    }
                                    System.out.println("Pulse enter para continuar.");
                                    s.nextLine();

                                    break;
                                case 3://Modificar mis reservas
                                    if (numGuardaUsuario == 1) {
                                        if (u1.getReserva1() == null && u1.getReserva2() == null) {
                                            System.out.println("Usted no tiene ninguna reserva.");
                                        }
                                        if (u1.getReserva1() != null && u1.getReserva2() == null) {
                                            do {
                                                System.out.print("¿Quieres eliminar tu reserva? (Y/N): ");
                                                switch (s.nextLine().toUpperCase()) {
                                                    case "Y" -> {
                                                        u1.setReserva1(null);
                                                        System.out.println("¡Reserva eliminada! Vuelva a añadir su nueva reserva en 'Busqueda de alojamientos'. Pulse enter para continuar.");
                                                        s.nextLine();
                                                        op = 0;
                                                    }
                                                    case "N" -> {
                                                        System.out.println("La reserva no ha sido eliminada. Pulse enter para continuar.");
                                                        s.nextLine();
                                                        op = 0;
                                                    }
                                                    default ->
                                                            System.out.println("Por favor, seleccione \"Y\"  o \"N\".");
                                                }
                                            } while (op != 0);

                                        }
                                        if (u1.getReserva1() == null && u1.getReserva2() != null) {
                                            do {
                                                System.out.print("¿Quieres eliminar tu reserva? (Y/N): ");
                                                switch (s.nextLine().toUpperCase()) {
                                                    case "Y" -> {
                                                        u1.setReserva2(null);
                                                        System.out.println("¡Reserva eliminada! Vuelva a añadir su nueva reserva en 'Busqueda de alojamientos'. ");
                                                        op = 0;
                                                    }
                                                    case "N" -> {
                                                        System.out.println("La reserva no ha sido eliminada.");
                                                        op = 0;
                                                    }
                                                    default ->
                                                            System.out.println("Por favor, seleccione \"Y\"  o \"N\".");
                                                }
                                            } while (op != 0);

                                        }
                                        if (u1.getReserva1() != null && u1.getReserva2() != null) {
                                            do {
                                                System.out.print("¿Quieres eliminar tus reservas? (Y/N): ");
                                                switch (s.nextLine().toUpperCase()) {
                                                    case "Y" -> {
                                                        u1.setReserva1(null);
                                                        u1.setReserva2(null);
                                                        System.out.println("¡Reservaz eliminadaz! Vuelva a añadir sus nuevas reservas en 'Busqueda de alojamientos'.");
                                                        op = 0;
                                                    }
                                                    case "N" -> {
                                                        do {
                                                            System.out.print("¿Que reserva quieres eliminar? (1/2) o 'N' para ninguna: ");
                                                            switch (s.nextLine().toUpperCase()) {
                                                                case "1" -> {
                                                                    u1.setReserva1(null);
                                                                    System.out.println("¡Reserva eliminada! Vuelva a añadir su nueva reserva en 'Busqueda de alojamientos'.");
                                                                    op = 0;
                                                                }
                                                                case "2" -> {
                                                                    u1.setReserva2(null);
                                                                    System.out.println("¡Reserva eliminada! Vuelva a añadir su nueva reserva en 'Busqueda de alojamientos'.");
                                                                    op = 0;
                                                                }
                                                                case "N" -> {
                                                                    System.out.println("Las reservas no han sido eliminadas.");
                                                                    op = 0;
                                                                }
                                                                default ->
                                                                        System.out.println("Por favor, seleccione '1' o '2'.");
                                                            }
                                                        } while (op != 0);

                                                    }
                                                    default ->
                                                            System.out.println("Por favor, seleccione \"Y\"  o \"N\".");
                                                }
                                            } while (op != 0);

                                        }
                                    }
                                    if (numGuardaUsuario == 11) {
                                        if (u2.getReserva1() == null && u2.getReserva2() == null) {
                                            System.out.println("Usted no tiene ninguna reserva.");
                                        }
                                        if (u2.getReserva1() != null && u2.getReserva2() == null) {
                                            do {
                                                System.out.print("¿Quieres eliminar tu reserva? (Y/N): ");
                                                switch (s.nextLine().toUpperCase()) {
                                                    case "Y" -> {
                                                        u2.setReserva1(null);
                                                        System.out.println("¡Reserva eliminada! Vuelva a añadir su nueva reserva en 'Busqueda de alojamientos'. Pulse enter para continuar.");
                                                        s.nextLine();
                                                        op = 0;
                                                    }
                                                    case "N" -> {
                                                        System.out.println("La reserva no ha sido eliminada. Pulse enter para continuar.");
                                                        s.nextLine();
                                                        op = 0;
                                                    }
                                                    default ->
                                                            System.out.println("Por favor, seleccione \"Y\"  o \"N\".");
                                                }
                                            } while (op != 0);

                                        }
                                        if (u2.getReserva1() == null && u2.getReserva2() != null) {
                                            do {
                                                System.out.print("¿Quieres eliminar tu reserva? (Y/N): ");
                                                switch (s.nextLine().toUpperCase()) {
                                                    case "Y" -> {
                                                        u2.setReserva2(null);
                                                        System.out.println("¡Reserva eliminada! Vuelva a añadir su nueva reserva en 'Busqueda de alojamientos'. ");
                                                        op = 0;
                                                    }
                                                    case "N" -> {
                                                        System.out.println("La reserva no ha sido eliminada.");
                                                        op = 0;
                                                    }
                                                    default ->
                                                            System.out.println("Por favor, seleccione \"Y\"  o \"N\".");
                                                }
                                            } while (op != 0);

                                        }
                                        if (u2.getReserva1() != null && u2.getReserva2() != null) {
                                            do {
                                                System.out.print("¿Quieres eliminar tus reservas? (Y/N): ");
                                                switch (s.nextLine().toUpperCase()) {
                                                    case "Y" -> {
                                                        u2.setReserva1(null);
                                                        u2.setReserva2(null);
                                                        System.out.println("¡Reservaz eliminadaz! Vuelva a añadir sus nuevas reservas en 'Busqueda de alojamientos'.");
                                                        op = 0;
                                                    }
                                                    case "N" -> {
                                                        do {
                                                            System.out.print("¿Que reserva quieres eliminar? (1/2) o 'N' para ninguna: ");
                                                            switch (s.nextLine().toUpperCase()) {
                                                                case "1" -> {
                                                                    u2.setReserva1(null);
                                                                    System.out.println("¡Reserva eliminada! Vuelva a añadir su nueva reserva en 'Busqueda de alojamientos'.");
                                                                    op = 0;
                                                                }
                                                                case "2" -> {
                                                                    u2.setReserva2(null);
                                                                    System.out.println("¡Reserva eliminada! Vuelva a añadir su nueva reserva en 'Busqueda de alojamientos'.");
                                                                    op = 0;
                                                                }
                                                                case "N" -> {
                                                                    System.out.println("Las reservas no han sido eliminadas.");
                                                                    op = 0;
                                                                }
                                                                default ->
                                                                        System.out.println("Por favor, seleccione '1' o '2'.");
                                                            }
                                                        } while (op != 0);

                                                    }
                                                    default ->
                                                            System.out.println("Por favor, seleccione \"Y\"  o \"N\".");
                                                }
                                            } while (op != 0);


                                        }
                                    }
                                    System.out.println("Pulse enter para continuar.");
                                    s.nextLine();

                                    break;
                                case 4: //Muestra los datos del usuario
                                    if (numGuardaUsuario == 1) {
                                        System.out.println(u1.toString());
                                    } else if (numGuardaUsuario == 11) {
                                        System.out.println(u2.toString());
                                    }
                                    System.out.println("Pulse enter para continuar...");
                                    s.nextLine();
                                    break;
                                case 5: //Modifica el perfil Usuario
                                    do {
                                        System.out.println(Menus.menuModificaPerfilUsuario());
                                        System.out.print("Seleccione una opción: ");
                                        op = Integer.parseInt(s.nextLine());
                                        switch (op) {
                                            case 1://Cambia el nombre
                                                System.out.print("Por favor, indíquenos su nuevo nombre: ");
                                                nombre = s.nextLine();
                                                do {
                                                    System.out.println("¿Quiere guardar los datos? (Y/N): ");
                                                    switch (s.nextLine().toUpperCase()) {
                                                        case "Y" -> {
                                                            f.modificaNombre(numGuardaUsuario, nombre);
                                                            Utils.esperar();
                                                            System.out.println("¡Datos actualizados! Pulse enter para continuar.");
                                                            s.nextLine();
                                                            op = 0;
                                                        }
                                                        case "N" -> {
                                                            System.out.println("Los datos no han sido actualizados. Pulse enter para continuar.");
                                                            s.nextLine();
                                                            op = 0;
                                                        }
                                                        default ->
                                                                System.out.println("Por favor, seleccione \"Y\" para guardar los datos o \"N\" para no guardar los datos cambiados.");
                                                    }
                                                } while (op != 0);
                                                break;

                                            case 2: //Cambia nombre usuario
                                                System.out.print("Por favor, indíquenos su nuevo nombre de usuario: ");
                                                nombreUsuario = s.nextLine();
                                                do {
                                                    System.out.println("¿Quiere guardar los datos? (Y/N): ");
                                                    switch (s.nextLine().toUpperCase()) {
                                                        case "Y" -> {
                                                            f.modificaNombreUsuario(numGuardaUsuario, nombreUsuario);
                                                            Utils.esperar();
                                                            System.out.println("¡Datos actualizados! Pulse enter para continuar");
                                                            op = 0;
                                                        }
                                                        case "N" -> op = 0;
                                                        default ->
                                                                System.out.println("Por favor, seleccione \"Y\" para guardar los datos o \"N\" para no guardar los datos cambiados.");
                                                    }
                                                } while (op != 0);

                                                break;
                                            case 3://Cambia contraseña
                                                do {
                                                    System.out.print("Para cambiar la contraseña primero debe confirmar la contraseña actual: ");
                                                    claveLogin = s.nextLine();
                                                    if (numGuardaUsuario == 1) {
                                                        usuarioLogin = u1.getNombreUsuario();
                                                        f.logging(usuarioLogin, claveLogin);
                                                    } else if (numGuardaUsuario == 11) {
                                                        usuarioLogin = u2.getNombreUsuario();
                                                        f.logging(usuarioLogin, claveLogin);
                                                    }
                                                } while (f.logging(usuarioLogin, claveLogin) != 1 && f.logging(usuarioLogin, claveLogin) != 11);
                                                System.out.print("Por favor, indíquenos su nueva contraseña: ");
                                                clave = s.nextLine();
                                                do {
                                                    System.out.println("¿Quiere guardar los datos? (Y/N): ");
                                                    switch (s.nextLine().toUpperCase()) {
                                                        case "Y" -> {
                                                            f.modificaClave(numGuardaUsuario, clave);
                                                            Utils.esperar();
                                                            System.out.println("¡Datos actualizados! Pulse enter para continuar.");
                                                            s.nextLine();
                                                            op = 0;
                                                        }
                                                        case "N" -> {
                                                            System.out.println("Los datos no han sido actualizados. Pulse enter para continuar.");
                                                            s.nextLine();
                                                            op = 0;
                                                        }
                                                        default ->
                                                                System.out.println("Por favor, seleccione \"Y\" para guardar los datos o \"N\" para no guardar los datos cambiados.");
                                                    }
                                                } while (op != 0);
                                                break;
                                            case 4://Cambia numero de telefono
                                                do {
                                                    System.out.print("Por favor, indíquenos su nuevo teléfono móvil: ");
                                                    numTlf = s.nextLine();
                                                    if (!f.validaMovil(numTlf))
                                                        System.out.println("Por favor, escriba un número de teléfono correcto.");
                                                } while (!f.validaMovil(numTlf));
                                                do {
                                                    System.out.println("¿Quiere guardar los datos? (Y/N): ");
                                                    switch (s.nextLine().toUpperCase()) {
                                                        case "Y" -> {
                                                            f.modificaClave(numGuardaUsuario, numTlf);
                                                            Utils.esperar();
                                                            System.out.println("¡Datos actualizados! Pulse enter para continuar.");
                                                            s.nextLine();
                                                            op = 0;
                                                        }
                                                        case "N" -> {
                                                            System.out.println("Los datos no han sido actualizados. Pulse enter para continuar.");
                                                            s.nextLine();
                                                            op = 0;
                                                        }
                                                        default ->
                                                                System.out.println("Por favor, seleccione \"Y\" para guardar los datos o \"N\" para no guardar los datos cambiados.");
                                                    }
                                                } while (op != 0);

                                                break;
                                            case 5://Salir menu modifica perfil
                                                do {
                                                    System.out.println("¿Quiere usted salir? (Y/N): ");
                                                    switch (s.nextLine().toUpperCase()) {
                                                        case "Y" -> {
                                                            Utils.esperar();
                                                            op = 5;
                                                        }
                                                        case "N" -> op = 0;
                                                        default ->
                                                                System.out.println("Por favor, seleccione \"Y\"  o \"N\" salir.");
                                                    }
                                                } while (op != 0 && op != 5);
                                                break;
                                            default:
                                                System.out.println("Seleccione una opción correcta.");
                                                break;
                                        }
                                    } while (op != 5);

                                    break;
                                case 6: //cerrar sesión usuario
                                    do {
                                        System.out.print("¿Desea cerrar sesion? (Y/N): ");
                                        switch (s.nextLine().toUpperCase()) {
                                            case "Y" -> {
                                                Utils.cerrarSesion();
                                                decision = true;
                                            }
                                            case "N" -> {
                                                op = 0; //Pongo esto a 0 para no salir del programa
                                                decision = true;
                                            }
                                            default -> System.out.println("Seleccione una opción correcta.");
                                        }

                                    } while (!decision);
                                default:
                                    System.out.println("Seleccione una opción correcta.");
                                    break;
                            }
                        } while (op != 6);
                    }
                    if (numGuardaUsuario == 2 || numGuardaUsuario == 22) { //Usuario Propietario
                        do {
                            nombreLog = f.devuelveNombre(numGuardaUsuario);
                            System.out.println(Menus.menuPropietario(nombreLog)); //Muestro el menu de propietarios
                            System.out.print("Seleccione una opción: ");
                            op = Integer.parseInt(s.nextLine());
                            switch (op) {
                                case 1://Ver mis viviendas en alquiler
                                    if (p1.getVivienda() != null && numGuardaUsuario == 2)
                                        System.out.println(p1.getVivienda().toString());
                                    else if (p2.getVivienda() != null && numGuardaUsuario == 22)
                                        System.out.println(p2.getVivienda().toString());
                                    System.out.println("Pulse enter para continuar.");
                                    s.nextLine();
                                    break;
                                case 2://Añadir o editar mis viviendas
                                    if ((numGuardaUsuario == 2 && p1.getVivienda() == null) || (numGuardaUsuario == 22 && p2.getVivienda() == null)) {
                                        do {
                                            System.out.println("Usted no tiene ninguna vivienda que editar. ¿Quiere añadir una? (Y/N)");
                                            switch (s.nextLine().toUpperCase()) {
                                                case "Y" -> {
                                                    System.out.println("¡Vamos a añadir una dirección!");
                                                    System.out.print("Dime el nombre de la calle: ");
                                                    calleTeclado = s.nextLine();
                                                    System.out.print("Dime el número de tu dirección: ");
                                                    numDireccionTeclado = Integer.parseInt(s.nextLine());
                                                    System.out.print("Dime la localidad de su dirección: ");
                                                    localidadTeclado = s.nextLine().toUpperCase();
                                                    direccion = new Direccion(calleTeclado, numDireccionTeclado, localidadTeclado);
                                                    do {
                                                        System.out.print("Pulse '1' para seleccionar piso o '2' para seleccionar casa: ");
                                                        numGuardaTipoVivienda = Integer.parseInt(s.nextLine());
                                                        switch (numGuardaTipoVivienda) {
                                                            case 1 -> {
                                                                System.out.println("Su vivienda se ha asignado como piso. ");
                                                                System.out.println("Pulse enter para continuar.");
                                                                s.nextLine();
                                                            }
                                                            case 2 -> {
                                                                System.out.println("Su vivienda se ha asignado como casa. ");
                                                                System.out.println("Pulse enter para continuar.");
                                                                s.nextLine();
                                                            }
                                                            default ->
                                                                    System.out.println("Seleccione los números asignados");
                                                        }
                                                    } while (numGuardaTipoVivienda != 1 && numGuardaTipoVivienda != 2);

                                                    System.out.print("Dime el precio por noche que va a tener la vivienda: ");
                                                    precioNocheTeclado = Double.parseDouble(s.nextLine());
                                                    System.out.print("Dime el número de huéspedes máximos de esta vivienda: ");
                                                    numHuespedMaxTeclado = Integer.parseInt(s.nextLine());
                                                    vivienda = new Vivienda(direccion, precioNocheTeclado, numHuespedMaxTeclado, numGuardaTipoVivienda);
                                                    f.aniadeVivienda(numGuardaUsuario, vivienda);
                                                    Utils.esperar();
                                                    System.out.println("Su vivienda se ha creado correctamente. Pulse enter para continuar.");
                                                    s.nextLine();
                                                    op = 0;

                                                }
                                                case "N" -> {
                                                    System.out.println("Pulse enter para salir.");
                                                    s.nextLine();
                                                    op = 0;
                                                }
                                                default -> System.out.println("Por favor, seleccione \"Y\"  o \"N\".");
                                            }
                                        } while (op != 0);
                                    } else {
                                        do {
                                            System.out.println("¿Quiere editar su vivienda? (Y/N)");
                                            switch (s.nextLine().toUpperCase()) {
                                                case "Y" -> {
                                                    System.out.println("¡Vamos a editar la dirección!");
                                                    System.out.print("Dime el nombre de la calle: ");
                                                    calleTeclado = s.nextLine();
                                                    System.out.print("Dime el número de tu dirección: ");
                                                    numDireccionTeclado = Integer.parseInt(s.nextLine());
                                                    System.out.print("Dime la localidad de su dirección: ");
                                                    localidadTeclado = s.nextLine().toUpperCase();
                                                    direccion = new Direccion(calleTeclado, numDireccionTeclado, localidadTeclado);
                                                    do {
                                                        System.out.print("Pulse '1' para seleccionar piso o '2' para seleccionar casa: ");
                                                        numGuardaTipoVivienda = Integer.parseInt(s.nextLine());
                                                        switch (numGuardaTipoVivienda) {
                                                            case 1 -> {
                                                                System.out.println("Su vivienda se ha asignado como piso. ");
                                                                System.out.println("Pulse enter para continuar.");
                                                                s.nextLine();
                                                            }
                                                            case 2 -> {
                                                                System.out.println("Su vivienda se ha asignado como casa. ");
                                                                System.out.println("Pulse enter para continuar.");
                                                                s.nextLine();
                                                            }
                                                            default ->
                                                                    System.out.println("Seleccione los números asignados");
                                                        }
                                                    } while (numGuardaTipoVivienda != 1 && numGuardaTipoVivienda != 2);

                                                    System.out.print("Dime el precio por noche que va a tener la vivienda: ");
                                                    precioNocheTeclado = Double.parseDouble(s.nextLine());
                                                    System.out.print("Dime el número de huéspedes máximos de esta vivienda: ");
                                                    numHuespedMaxTeclado = Integer.parseInt(s.nextLine());
                                                    vivienda = new Vivienda(direccion, precioNocheTeclado, numHuespedMaxTeclado, numGuardaTipoVivienda);
                                                    f.aniadeVivienda(numGuardaUsuario, vivienda);
                                                    Utils.esperar();
                                                    System.out.println("Su vivienda se ha editado correctamente. Pulse enter para continuar.");
                                                    s.nextLine();
                                                    op = 0;

                                                }
                                                case "N" -> {
                                                    System.out.println("Pulse enter para salir.");
                                                    s.nextLine();
                                                    op = 0;
                                                }
                                                default -> System.out.println("Por favor, seleccione \"Y\"  o \"N\".");
                                            }
                                        } while (op != 0);
                                    }


                                    break;
                                case 3://Ver las reservas de mis viviendas
                                    if (numGuardaUsuario == 2) {
                                        if (p1.getVivienda().getReserva1() == null && p1.getVivienda().getReserva2() == null) {
                                            System.out.println("Usted no tiene ninguna reserva.");
                                        } else if (p1.getVivienda().getReserva1() != null && p1.getVivienda().getReserva2() == null) {
                                            System.out.println(p1.getVivienda().getReserva1().toString());
                                        } else if (p1.getVivienda().getReserva1() != null && p1.getVivienda().getReserva2() != null) {
                                            System.out.println(p1.getVivienda().getReserva1().toString());
                                            System.out.println();
                                            System.out.println(p1.getVivienda().getReserva2().toString());


                                        }

                                    } else if (numGuardaUsuario == 22) {
                                        if (p2.getVivienda().getReserva1() == null && p2.getVivienda().getReserva2() == null) {
                                            System.out.println("Usted no tiene ninguna reserva.");
                                        } else if (p2.getVivienda().getReserva1() != null && p2.getVivienda().getReserva2() == null) {
                                            System.out.println(p2.getVivienda().getReserva1().toString());
                                        } else if (p2.getVivienda().getReserva1() != null && p2.getVivienda().getReserva2() != null) {
                                            System.out.println(p2.getVivienda().getReserva1().toString());
                                            System.out.println();
                                            System.out.println(p2.getVivienda().getReserva2().toString());

                                        }

                                    }
                                    System.out.println("Pulse enter para continuar...");
                                    s.nextLine();
                                    break;
                                case 4://Establecer un periodo de no disponible de una vivienda
                                    do {
                                        System.out.print("¿Quiere poner unas fechas determinadas como no disponible en su vivienda? (Y/N): ");
                                        switch (s.nextLine().toUpperCase()) {
                                            case "Y" -> {
                                                System.out.print("Introduzca una fecha, en formato (yyyy-mm-dd): ");
                                                fechaInicioTeclado = s.nextLine();
                                                fechaInicio = LocalDate.parse(fechaInicioTeclado);
                                                System.out.print("Introduzca una fecha de salida, en formato (yyyy-mm-dd): ");
                                                fechaFinalTeclado = s.nextLine();
                                                fechaFinal = LocalDate.parse(fechaFinalTeclado);
                                                if (numGuardaUsuario == 2) {
                                                    if (p1.getVivienda() == null) {
                                                        System.out.println("Usted no ha añadido su vivienda aún.");
                                                    } else {
                                                        p1.getVivienda().setReserva1(new Reserva(fechaInicio, fechaFinal));
                                                        p1.getVivienda().setReserva2(new Reserva(fechaInicio, fechaFinal));

                                                        System.out.println("¡Datos actualizados! Pulse enter para continuar.");
                                                        s.nextLine();
                                                    }
                                                } else if (numGuardaUsuario == 22) {
                                                    if (p2.getVivienda() == null) {
                                                        System.out.println("Usted no ha añadido su vivienda aún.");
                                                    } else {
                                                        p2.getVivienda().setReserva1(new Reserva(fechaInicio, fechaFinal));
                                                        p2.getVivienda().setReserva2(new Reserva(fechaInicio, fechaFinal));
                                                        System.out.println("¡Datos actualizados! Pulse enter para continuar.");
                                                        s.nextLine();
                                                    }

                                                }
                                                op = 0;
                                            }
                                            case "N" -> {
                                                System.out.println("Pulse enter para continuar.");
                                                s.nextLine();
                                                op = 0;
                                                break;
                                            }
                                            default ->
                                                    System.out.println("Por favor, seleccione \"Y\" para guardar los datos o \"N\" para no guardar los datos cambiados.");
                                        }
                                    }
                                    while (op != 0);

                                    break;
                                case 5://Ver mi perfil
                                    if (numGuardaUsuario == 2) {
                                        System.out.println(p1.toString());
                                    } else if (numGuardaUsuario == 22) {
                                        System.out.println(p2.toString());
                                    }
                                    System.out.println("Pulse enter para continuar...");
                                    s.nextLine();
                                    break;
                                case 6://Modifica el perfil Usuario Propietario
                                    do {
                                        System.out.println(Menus.menuModificaPerfilUsuario());
                                        System.out.print("Seleccione una opción: ");
                                        op = Integer.parseInt(s.nextLine());
                                        switch (op) {
                                            case 1://Cambia el nombre
                                                System.out.print("Por favor, indíquenos su nuevo nombre: ");
                                                nombre = s.nextLine();
                                                do {
                                                    System.out.println("¿Quiere guardar los datos? (Y/N): ");
                                                    switch (s.nextLine().toUpperCase()) {
                                                        case "Y" -> {
                                                            f.modificaNombre(numGuardaUsuario, nombre);
                                                            Utils.esperar();
                                                            System.out.println("¡Datos actualizados! Pulse enter para continuar.");
                                                            s.nextLine();
                                                            op = 0;
                                                        }
                                                        case "N" -> {
                                                            System.out.println("Los datos no han sido actualizados. Pulse enter para continuar.");
                                                            s.nextLine();
                                                            op = 0;
                                                        }
                                                        default ->
                                                                System.out.println("Por favor, seleccione \"Y\" para guardar los datos o \"N\" para no guardar los datos cambiados.");
                                                    }
                                                } while (op != 0);
                                                break;
                                            case 2: //Cambia nombre usuario
                                                System.out.print("Por favor, indíquenos su nuevo nombre de usuario: ");
                                                nombreUsuario = s.nextLine();
                                                do {
                                                    System.out.println("¿Quiere guardar los datos? (Y/N): ");
                                                    switch (s.nextLine().toUpperCase()) {
                                                        case "Y" -> {
                                                            f.modificaNombreUsuario(numGuardaUsuario, nombreUsuario);
                                                            Utils.esperar();
                                                            System.out.println("¡Datos actualizados! Pulse enter para continuar");
                                                            op = 0;
                                                        }
                                                        case "N" -> op = 0;
                                                        default ->
                                                                System.out.println("Por favor, seleccione \"Y\" para guardar los datos o \"N\" para no guardar los datos cambiados.");
                                                    }
                                                } while (op != 0);
                                                break;
                                            case 3://Cambia contraseña
                                                do {
                                                    System.out.print("Para cambiar la contraseña primero debe confirmar la contraseña actual: ");
                                                    claveLogin = s.nextLine();
                                                    if (numGuardaUsuario == 2) {
                                                        usuarioLogin = p1.getNombreUsuario();
                                                        f.logging(usuarioLogin, claveLogin);
                                                    } else if (numGuardaUsuario == 22) {
                                                        usuarioLogin = p2.getNombreUsuario();
                                                        f.logging(usuarioLogin, claveLogin);
                                                    }
                                                } while (f.logging(usuarioLogin, claveLogin) != 2 && f.logging(usuarioLogin, claveLogin) != 22);
                                                System.out.print("Por favor, indíquenos su nueva contraseña: ");
                                                clave = s.nextLine();
                                                do {
                                                    System.out.println("¿Quiere guardar los datos? (Y/N): ");
                                                    switch (s.nextLine().toUpperCase()) {
                                                        case "Y" -> {
                                                            f.modificaClave(numGuardaUsuario, clave);
                                                            Utils.esperar();
                                                            System.out.println("¡Datos actualizados! Pulse enter para continuar.");
                                                            s.nextLine();
                                                            op = 0;
                                                        }
                                                        case "N" -> {
                                                            System.out.println("Los datos no han sido actualizados. Pulse enter para continuar.");
                                                            s.nextLine();
                                                            op = 0;
                                                        }
                                                        default ->
                                                                System.out.println("Por favor, seleccione \"Y\" para guardar los datos o \"N\" para no guardar los datos cambiados.");
                                                    }
                                                } while (op != 0);
                                                break;
                                            case 4://Cambia numero de telefono
                                                do {
                                                    System.out.print("Por favor, indíquenos su nuevo teléfono móvil: ");
                                                    numTlf = s.nextLine();
                                                    if (!f.validaMovil(numTlf))
                                                        System.out.println("Por favor, escriba un número de teléfono correcto.");
                                                } while (!f.validaMovil(numTlf));
                                                do {
                                                    System.out.println("¿Quiere guardar los datos? (Y/N): ");
                                                    switch (s.nextLine().toUpperCase()) {
                                                        case "Y" -> {
                                                            f.modificaClave(numGuardaUsuario, numTlf);
                                                            Utils.esperar();
                                                            System.out.println("¡Datos actualizados! Pulse enter para continuar.");
                                                            s.nextLine();
                                                            op = 0;
                                                        }
                                                        case "N" -> {
                                                            System.out.println("Los datos no han sido actualizados. Pulse enter para continuar.");
                                                            s.nextLine();
                                                            op = 0;
                                                        }
                                                        default ->
                                                                System.out.println("Por favor, seleccione \"Y\" para guardar los datos o \"N\" para no guardar los datos cambiados.");
                                                    }
                                                } while (op != 0);
                                                break;
                                            case 5://Salir menu modifica perfil
                                                do {
                                                    System.out.println("¿Quiere usted salir? (Y/N): ");
                                                    switch (s.nextLine().toUpperCase()) {
                                                        case "Y" -> {
                                                            Utils.esperar();
                                                            op = 5;
                                                        }
                                                        case "N" -> op = 0;
                                                        default ->
                                                                System.out.println("Por favor, seleccione \"Y\"  o \"N\" salir.");
                                                    }
                                                } while (op != 0 && op != 5);
                                                break;
                                            default:
                                                System.out.println("Seleccione una opción correcta.");
                                                break;
                                        }
                                    } while (op != 5);
                                    break;

                                case 7://cerrar sesión propietario
                                    do {
                                        System.out.print("¿Desea cerrar sesión? (Y/N): ");
                                        switch (s.nextLine().toUpperCase()) {
                                            case "Y" -> {
                                                Utils.cerrarSesion();
                                                decision = true;
                                            }
                                            case "N" -> {
                                                op = 0; //Pongo esto a 0 para no salir del programa
                                                decision = true;
                                            }
                                            default -> System.out.println("Seleccione una opción correcta.");
                                        }

                                    } while (!decision);
                                default:
                                    System.out.println("Seleccione una opción correcta.");
                                    break;
                            }
                        } while (op != 7);
                    }
                    if (numGuardaUsuario == 3) { //Usuario Admin
                        do {
                            nombreLog = f.devuelveNombre(numGuardaUsuario);
                            System.out.println(Menus.menuAdministrador(nombreLog));
                            System.out.print("Seleccione una opción: ");
                            op = Integer.parseInt(s.nextLine());
                            switch (op) {
                                case 1: //Ver todas las viviendas en alquiler
                                    if (p1.getVivienda() != null) {
                                        System.out.println("Esta vivienda le pertenece al usuario propietario " + p1.getNombreUsuario());
                                        System.out.println(p1.getVivienda().toString());
                                        System.out.println();
                                    }

                                    if (p2.getVivienda() != null) {
                                        System.out.println("Esta vivienda le pertenece al usuario propietario " + p2.getNombreUsuario());
                                        System.out.println(p2.getVivienda().toString());
                                    }
                                    if (p1.getVivienda() == null && p2.getVivienda() == null)
                                        System.out.println("Actualmente no hay viviendas en alquiler.");
                                    System.out.println("Pulse enter para continuar.");
                                    s.nextLine();

                                    break;
                                case 2://Ver todos los usuarios del sistema
                                    switch (f.devuelveUsuarios()) {
                                        case 1 -> System.out.println("" +
                                                "┌────────────────────┤ Usuarios registrados ├────────────────────┐\n" +
                                                "                -Nombre de usuario: " + u1.getNombreUsuario() + "\n" +
                                                "└────────────────────────────────────────────────────────────────┘");
                                        case 11 -> System.out.println("" +
                                                "┌────────────────────┤ Usuarios registrados ├────────────────────┐\n" +
                                                "                -Nombre de usuario: " + u1.getNombreUsuario() + "\n" +
                                                "                -Nombre de usuario: " + u2.getNombreUsuario() + "\n" +
                                                "└────────────────────────────────────────────────────────────────┘");
                                        case -1 -> System.out.println("No hay ningún usuario normal registrado.");
                                    }
                                    System.out.println();
                                    System.out.println();
                                    switch (f.devuelveUsuariosPropietarios()) {
                                        case 2 -> System.out.println("" +
                                                "┌─────────────┤ Usuarios propietarios registrados  ├─────────────┐\n" +
                                                "                -Nombre de usuario: " + p1.getNombreUsuario() + "\n" +
                                                "└────────────────────────────────────────────────────────────────┘");
                                        case 22 -> System.out.println("" +
                                                "┌─────────────┤ Usuarios propietarios registrados  ├─────────────┐\n" +
                                                "                -Nombre de usuario: " + p1.getNombreUsuario() + "\n" +
                                                "                -Nombre de usuario: " + p2.getNombreUsuario() + "\n" +
                                                "└────────────────────────────────────────────────────────────────┘");
                                        case -1 -> System.out.println("No hay ningún usuario propietario registrado.");
                                    }
                                    System.out.println("Pulse enter para continuar.");
                                    s.nextLine();
                                    break;
                                case 3://Ver todas las reservas de viviendas
                                    if (u1.getReserva1() == null && u1.getReserva2() == null) {
                                        System.out.println("Usted no tiene ninguna reserva.");
                                    }
                                    if (u1.getReserva1() != null && u1.getReserva2() == null) {
                                        System.out.println("Esta es la reserva 1 de " + u1.getNombreUsuario());
                                        System.out.println(u1.getReserva1().toString());
                                    }
                                    if (u1.getReserva1() != null && u1.getReserva2() != null) {
                                        System.out.println("Esta es la reserva 1 de " + u1.getNombreUsuario());
                                        System.out.println(u1.getReserva1().toString());
                                        System.out.println();
                                        System.out.println("Esta es la reserva 2 de " + u1.getNombreUsuario());
                                        System.out.println(u1.getReserva2().toString());
                                    }
                                    if (u2.getReserva1() == null && u2.getReserva2() == null)
                                        System.out.println("Usted no tiene ninguna reserva.");
                                    if (u2.getReserva1() != null && u2.getReserva2() == null)
                                        System.out.println("Esta es la reserva 1 de " + u2.getNombreUsuario());
                                    System.out.println(u2.getReserva1().toString());
                                    if (u2.getReserva1() != null && u2.getReserva2() != null) {
                                        System.out.println("Esta es la reserva 1 de " + u2.getNombreUsuario());
                                        System.out.println(u2.getReserva1().toString());
                                        System.out.println();
                                        System.out.println("Esta es la reserva 2 de " + u2.getNombreUsuario());
                                        System.out.println(u2.getReserva2().toString());
                                    }

                                    System.out.println("Pulse enter para continuar.");
                                    s.nextLine();

                                    break;
                                case 4: //Muestro los datos del usuario administrador
                                    System.out.println(admin.toString());
                                    System.out.println("Pulse enter para continuar...");
                                    s.nextLine();

                                    break;
                                case 5: //Modifico Perfil admin
                                    do {
                                        System.out.println(Menus.menuModificaPerfilUsuario());
                                        System.out.print("Seleccione una opción: ");
                                        op = Integer.parseInt(s.nextLine());
                                        switch (op) {
                                            case 1://Cambia el nombre
                                                System.out.print("Por favor, indíquenos su nuevo nombre: ");
                                                nombre = s.nextLine();
                                                do {
                                                    System.out.println("¿Quiere guardar los datos? (Y/N): ");
                                                    switch (s.nextLine().toUpperCase()) {
                                                        case "Y" -> {
                                                            f.modificaNombre(numGuardaUsuario, nombre);
                                                            Utils.esperar();
                                                            System.out.println("¡Datos actualizados! Pulse enter para continuar.");
                                                            s.nextLine();
                                                            op = 0;
                                                        }
                                                        case "N" -> {
                                                            System.out.println("Los datos no han sido actualizados. Pulse enter para continuar.");
                                                            s.nextLine();
                                                            op = 0;
                                                        }
                                                        default ->
                                                                System.out.println("Por favor, seleccione \"Y\" para guardar los datos o \"N\" para no guardar los datos cambiados.");
                                                    }
                                                } while (op != 0);
                                                break;
                                            case 2: //Cambia nombre usuario
                                                System.out.print("Por favor, indíquenos su nuevo nombre de usuario: ");
                                                nombreUsuario = s.nextLine();
                                                do {
                                                    System.out.println("¿Quiere guardar los datos? (Y/N): ");
                                                    switch (s.nextLine().toUpperCase()) {
                                                        case "Y" -> {
                                                            f.modificaNombreUsuario(numGuardaUsuario, nombreUsuario);
                                                            Utils.esperar();
                                                            System.out.println("¡Datos actualizados! Pulse enter para continuar");
                                                            op = 0;
                                                        }
                                                        case "N" -> op = 0;
                                                        default ->
                                                                System.out.println("Por favor, seleccione \"Y\" para guardar los datos o \"N\" para no guardar los datos cambiados.");
                                                    }
                                                } while (op != 0);
                                                break;
                                            case 3://Cambia contraseña
                                                do {
                                                    System.out.print("Para cambiar la contraseña primero debe confirmar la contraseña actual: ");
                                                    claveLogin = s.nextLine();
                                                    if (numGuardaUsuario == 3) {
                                                        usuarioLogin = admin.getNombreUsuario();
                                                        f.logging(usuarioLogin, claveLogin);
                                                    }
                                                } while (f.logging(usuarioLogin, claveLogin) != 3);
                                                System.out.print("Por favor, indíquenos su nueva contraseña: ");
                                                clave = s.nextLine();
                                                do {
                                                    System.out.println("¿Quiere guardar los datos? (Y/N): ");
                                                    switch (s.nextLine().toUpperCase()) {
                                                        case "Y" -> {
                                                            f.modificaClave(numGuardaUsuario, clave);
                                                            Utils.esperar();
                                                            System.out.println("¡Datos actualizados! Pulse enter para continuar.");
                                                            s.nextLine();
                                                            op = 0;
                                                        }
                                                        case "N" -> {
                                                            System.out.println("Los datos no han sido actualizados. Pulse enter para continuar.");
                                                            s.nextLine();
                                                            op = 0;
                                                        }
                                                        default ->
                                                                System.out.println("Por favor, seleccione \"Y\" para guardar los datos o \"N\" para no guardar los datos cambiados.");
                                                    }
                                                } while (op != 0);
                                                break;
                                            case 4://Cambia numero de telefono
                                                do {
                                                    System.out.print("Por favor, indíquenos su nuevo teléfono móvil: ");
                                                    numTlf = s.nextLine();
                                                    if (!f.validaMovil(numTlf))
                                                        System.out.println("Por favor, escriba un número de teléfono correcto.");
                                                } while (!f.validaMovil(numTlf));
                                                do {
                                                    System.out.println("¿Quiere guardar los datos? (Y/N): ");
                                                    switch (s.nextLine().toUpperCase()) {
                                                        case "Y" -> {
                                                            f.modificaClave(numGuardaUsuario, numTlf);
                                                            Utils.esperar();
                                                            System.out.println("¡Datos actualizados! Pulse enter para continuar.");
                                                            s.nextLine();
                                                            op = 0;
                                                        }
                                                        case "N" -> {
                                                            System.out.println("Los datos no han sido actualizados. Pulse enter para continuar.");
                                                            s.nextLine();
                                                            op = 0;
                                                        }
                                                        default ->
                                                                System.out.println("Por favor, seleccione \"Y\" para guardar los datos o \"N\" para no guardar los datos cambiados.");
                                                    }
                                                } while (op != 0);
                                                break;
                                            case 5://Salir menu modifica perfil
                                                do {
                                                    System.out.println("¿Quiere usted salir? (Y/N): ");
                                                    switch (s.nextLine().toUpperCase()) {
                                                        case "Y" -> {
                                                            Utils.esperar();
                                                            op = 5;
                                                        }
                                                        case "N" -> op = 0;
                                                        default ->
                                                                System.out.println("Por favor, seleccione \"Y\"  o \"N\" salir.");
                                                    }
                                                } while (op != 0 && op != 5);
                                                break;
                                            default:
                                                System.out.println("Seleccione una opción correcta.");
                                                break;
                                        }
                                    } while (op != 5);
                                    break;


                                case 6:
                                    do {
                                        System.out.print("¿Desea cerrar sesion? (Y/N): ");
                                        switch (s.nextLine().toUpperCase()) {
                                            case "Y" -> {
                                                Utils.cerrarSesion();
                                                decision = true;
                                            }
                                            case "N" -> {
                                                op = 0; //Pongo esto a 0 para no salir del programa
                                                decision = true;
                                            }
                                            default -> System.out.println("Seleccione una opción correcta.");
                                        }

                                    } while (!decision);
                                default:
                                    System.out.println("Seleccione una opción correcta.");
                                    break;
                            }

                        } while (op != 6);
                    }
                    if (numGuardaUsuario == -1) {
                        System.out.println("Nombre de usuario o contraseña inválidos.");
                        System.out.println("Pulse enter para continuar...");
                        s.nextLine();
                    }
                }
                case 2 -> {//Registrarse
                    System.out.println(Menus.menuTipoRegistro());
                    System.out.print("Seleccione una opción: ");
                    op = Integer.parseInt(s.nextLine());
                    switch (op) {
                        case 1: //usuario
                            if (f.getU1() != null && f.getU2() != null) {
                                System.out.println("No quedan usuarios disponibles para registrase, disculpe las molestias.");
                                System.out.println("Pulse enter para continuar...");
                                s.nextLine();
                            } else {
                                System.out.print("Escriba su nombre: ");
                                nombre = s.nextLine();
                                do {
                                    System.out.print("Escriba el nombre de usuario: ");
                                    nombreUsuario = s.nextLine();
                                    if (!f.compruebaUsuarioDisponible(nombreUsuario))
                                        System.out.println("Ese nombre de usuario no está disponible. Escribe otro distinto.");
                                } while (!f.compruebaUsuarioDisponible(nombreUsuario));
                                do {
                                    System.out.print("Escriba su contraseña: ");
                                    clave = s.nextLine();
                                    System.out.print("Repita su contraseña: ");
                                    claveRepetida = s.nextLine();
                                    if (!clave.equals(claveRepetida))
                                        System.out.println("Las contraseñas no coinciden.");
                                } while (!clave.equals(claveRepetida));
                                do {
                                    System.out.print("Escriba su número de teléfono: ");
                                    numTlf = s.nextLine();
                                    if (!f.validaMovil(numTlf))
                                        System.out.println("Por favor, escriba un número de teléfono correcto.");
                                } while (!f.validaMovil(numTlf));
                                f.registrarUsuario(nombre, nombreUsuario, clave, numTlf);
                                u1 = f.getU1();
                                u2 = f.getU2();
                                System.out.println();
                                System.out.println("Bienvenido " + nombre + ", su registro se ha completado correctamente.");
                                System.out.println("Pulse enter para continuar...");
                                s.nextLine();
                            }
                            break;
                        case 2://propietario
                            if (f.getP1() != null && f.getP2() != null) {
                                System.out.println("No quedan propietarios disponibles para registrase, disculpe las molestias.");
                                System.out.println("Pulse enter para continuar...");
                                s.nextLine();
                            } else {
                                System.out.print("Escriba su nombre: ");
                                nombre = s.nextLine();
                                do {
                                    System.out.print("Escriba el nombre de usuario: ");
                                    nombreUsuario = s.nextLine();
                                    if (!f.compruebaUsuarioDisponible(nombreUsuario))
                                        System.out.println("Ese nombre de usuario no está disponible. Escribe otro distinto.");
                                } while (!f.compruebaUsuarioDisponible(nombreUsuario));
                                do {
                                    System.out.print("Escriba su contraseña: ");
                                    clave = s.nextLine();
                                    System.out.print("Repita su contraseña: ");
                                    claveRepetida = s.nextLine();
                                    if (!clave.equals(claveRepetida))
                                        System.out.println("Las contraseñas no coinciden.");
                                } while (!clave.equals(claveRepetida));
                                do {
                                    System.out.print("Escriba su número de teléfono: ");
                                    numTlf = s.nextLine();
                                    if (!f.validaMovil(numTlf))
                                        System.out.println("Por favor, escriba un número de teléfono correcto.");
                                } while (!f.validaMovil(numTlf));
                                f.registrarPropietario(nombre, nombreUsuario, clave, numTlf);
                                p1 = f.getP1();
                                p2 = f.getP2();
                                System.out.println();
                                System.out.println("Bienvenido " + nombre + ", su registro se ha completado correctamente.");
                                System.out.println("Pulse enter para continuar...");
                                s.nextLine();
                            }
                            break;
                        case 3://Admin
                            if (f.getAdmin() != null) { //Si no queda administradores no le hago poner la contraseña
                                System.out.println("No quedan administradores disponibles para registrase, disculpe las molestias.");
                                System.out.println("Pulse enter para continuar...");
                                s.nextLine();
                                op = 0; //Pongo esto a 0 para no salir del programa
                                break;
                            }
                            System.out.print("Para acceder a necesita una contraseña de administrador: ");
                            claveAdministradorTeclado = s.nextLine();
                            if (claveAdministradorTeclado.equals(claveAdministrador)) {
                                if (f.getAdmin() != null) {
                                    System.out.println("No quedan administradores disponibles para registrase, disculpe las molestias.");
                                    System.out.println("Pulse enter para continuar...");
                                    s.nextLine();
                                    op = 0; //Pongo esto a 0 para no salir del programa
                                } else {
                                    System.out.println("Bienvenido administrador, por favor introduzca sus datos para el registro.");
                                    System.out.print("Escriba su nombre: ");
                                    nombre = s.nextLine();
                                    do {
                                        System.out.print("Escriba el nombre de usuario: ");
                                        nombreUsuario = s.nextLine();
                                        if (!f.compruebaUsuarioDisponible(nombreUsuario))
                                            System.out.println("Ese nombre de usuario no está disponible. Escribe otro distinto.");
                                    } while (!f.compruebaUsuarioDisponible(nombreUsuario));
                                    do {
                                        System.out.print("Escriba su contraseña: ");
                                        clave = s.nextLine();
                                        System.out.print("Repita su contraseña: ");
                                        claveRepetida = s.nextLine();
                                        if (!clave.equals(claveRepetida))
                                            System.out.println("Las contraseñas no coinciden.");
                                    } while (!clave.equals(claveRepetida));
                                    do {
                                        System.out.print("Escriba su número de teléfono: ");
                                        numTlf = s.nextLine();
                                        if (!f.validaMovil(numTlf))
                                            System.out.println("Por favor, escriba un número de teléfono correcto.");
                                    } while (!f.validaMovil(numTlf));
                                    f.registrarAdmin(nombre, nombreUsuario, clave, numTlf);
                                    admin = f.getAdmin();
                                    System.out.println();
                                    System.out.println("Bienvenido " + nombre + ", su registro se ha completado correctamente.");
                                    System.out.println("Pulse enter para continuar...");
                                    s.nextLine();
                                    op = 0; //Pongo esto a 0 para no salir del programa
                                }
                            } else {
                                System.out.println("La contraseña no es válida.");
                                op = 0; //Pongo esto a 0 para no salir del programa
                            }
                            break;

                        default:
                            System.out.println("Seleccione una opción correcta.");
                            break;

                    }
                }
                case 3 -> {
                    System.out.println("Apagando el sistema.");
                    Utils.esperar();
                }
                default -> System.out.println("Seleccione una opción correcta.");
            }

        } while (op != 3);


    }
}