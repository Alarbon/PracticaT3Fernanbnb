package Componentes;

public class Menus {
    public static String menuUsuario(String nombre) {
        return String.format("""
                                Bienvenido %s, busque un alojamiento para sus próximas vacaciones.
                                
                                ┌───────────┤ MENÚ DE USUARIO ├───────────┐
                                │     1. Búsqueda de alojamientos         │
                                │     2. Ver mis reservas                 │
                                │     3. Modificar mis reservas           │
                                │     4. Ver mi perfil                    │
                                │     5. Modificar mi perfil              │
                                │     6. Cerrar sesión                    │
                                └─────────────────────────────────────────┘
                        """
                , nombre);
    }

    public static String menuPropietario(String nombre) {
        return String.format("""
                         Bienvenido %s, gestione sus viviendas en alquiler.
                         
                        ┌──────────────────────┤ MENÚ DE PROPIETARIO ├─────────────────────┐
                        │   1. Ver mis viviendas en alquiler                               │
                        │   2. Añadir o editar mis viviendas                               │
                        │   3. Ver las reservas de mis viviendas                           │
                        │   4. Establecer un periodo de no disponible para una vivienda    │
                        │   5. Ver mi perfil                                               │
                        │   6. Modificar mi perfil                                         │
                        │   7. Cerrar sesión                                               │
                        └──────────────────────────────────────────────────────────────────┘
                        """
                , nombre);
    }

    public static String menuAdministrador(String nombre) {
        return String.format("""
                        Bienvenido %s, perfil de administración.
                         
                        ┌──────────┤ MENÚ DE ADMINISTRADOR ├──────────┐
                        │   1. Ver todas las viviendas en alquiler    │
                        │   2. Ver todos los usuarios del sistema     │
                        │   3. Ver todas las reservas de viviendas    │
                        │   4. Ver mi perfil                          │
                        │   5. Modificar mi perfil                    │
                        │   6. Cerrar sesión                          │
                        └─────────────────────────────────────────────┘
                        """
                , nombre);
    }

    public static String menuInicio() {
        return """
                        ┌─────────────┤ FERNANbnb ├─────────────┐
                        │   1. Inicio de sesión                 │
                        │   2. Registrarse                      │
                        │   3. Salir                            │
                        └───────────────────────────────────────┘
                """;
    }

    public static String menuTipoRegistro() {
        return """
                        ┌────────┤ Registro de FERNANbnb ├────────┐
                        │   1. Registro de Usuario                │
                        │   2. Registro de Propietario            │
                        │   3. Registro de Admin                  │
                        │   4. Salir                              │
                        └─────────────────────────────────────────┘
                """;
    }

    public static String menuModificaPerfilUsuario() {
        return """
                        ┌──────────────┤ FERNANbnb ├──────────────┐
                        │   1. Cambiar nombre                     │
                        │   2. Cambiar nombre de usuario          │
                        │   3. Cambiar contraseña                 │
                        │   4. Cambiar número de teléfono         │
                        │   5. Salir                              │
                        └─────────────────────────────────────────┘
                """;
    }

}


