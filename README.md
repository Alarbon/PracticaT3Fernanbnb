

# <img src="https://cdn.discordapp.com/attachments/1052443807112773724/1052443996825333780/logotipo.png" alt="presentacion" style="zoom:60%;" />



# Tabla de contenido

1. [**Prerequisitos e instalación**](#id1)

   1.1. [Cosas a tener en cuenta](#id1.1)

2. [**Menú Principal**](#id2)

   2.1[Menú de navegación de las opciones del menú principal](#id2.1)

3. [**Menú Usuario**](#id4)

   3.1[Menú de navegación de las opciones de los usuarios normales](#id3.1)

4. [**Menú Usuario Propietario**](#id4)

   4.1[Menú de navegación de las opciones de usuarios propietarios](#id4.1)

5. [**Menú  Usuario Administrador**](#id5)

   5.1[Menú de navegación del usuario Administrador](#id5.1)

   

   # Prerrequisitos e instalación 

   <div id='id1' />

   Para instalar y ejecutar este programa necesitaremos tener instalado una version de jdk. Es recomendable para que no haya fallos tener el jdk 19.   [Descarga aqui](https://www.oracle.com/es/java/technologies/downloads/#jdk19-windows"Descarga aqui")

   Tambien es recomendable hacer todos los procedimientos en windows.

   Para la instalación se hará un pull y se veran los siguientes documentos:

   <img src="https://cdn.discordapp.com/attachments/1052443807112773724/1052447449219866634/image.png" style="zoom:75%;" />

   A nosotros nos interese el "Ejecutable_Fernanbnb", es un acceso directo por lo que puedes llevarlo a la dirección que quiera. Sólo manipular ese archivo. Una vez hecho le daremos doble click para abrirlo y ya estará nuestro software en funcionamiento.

   ## Cosas a tener en cuenta

   La aplicación no tiene ningún dato por defecto incorporado. Perfecto para incorporardo. Eso incluye también al usuario administrador. Para el registro del usuario administrador es necesaria una clave, ya que sin ella no podremos registrarnos. La clave es: "AdminFurioso1324". Una vez anotada la clave explicaremos todo al detalle a continuación.  Para recrear este software se ha seguido el siguiente UML:

   <img src="https://cdn.discordapp.com/attachments/1052443807112773724/1052458455358767114/FernanBB.drawio.png" style="zoom:75%;" />

   

   

Cada clase viene también con sus Getters & Setters.



# Menú Principal 📖

<div id='id2' />

_El menú principal constará de 3 apartados. El primero inicio sesión, donde introduciremos nuestro nombre de usuario (el cual es único) y la contraseña. El segundo es el registro, en el cual hay un submenú que veremos más adelante, aquí deberemos rellenar los campos que nos pide.

<img src="https://cdn.discordapp.com/attachments/1052443807112773724/1052459988532088892/image.png" style="zoom:80%;" />

En el inicio de sesion le pedirá un nombre, un nombre de usuario que deberá ser unico, si ya hay un usuario registrado con ese username nos obligará a cambiarlo por otro distinto. Posteriormente introduciremos la clave y una confirmación en la cual si la clave secundaria no es igual a la primera no podrás seguir con el registro hasta que sean iguales. Por último añadineros un móvil, este movil solo aceptara números de 9 caracteres. Ej: 638754987.

Aquí un ejemplo, este caso es especial ya que para registrar al administrador necesitaremos una clave especial dicha anteriormente.

<img src="https://cdn.discordapp.com/attachments/1052443807112773724/1052460866664136735/image.png" style="zoom:80%;" />



Posteriormente estaremos listos para realizar las distintas funcionalidades que tiene el menú del usuario registrado, los cuales veremos a continuación.



# Menú Usuario Normal 📖

<div id='id3' />

<img src="https://cdn.discordapp.com/attachments/1052443807112773724/1052468976157995048/image.png" style="zoom:80%;" />

Tras acceder con nuestro usuario y contraseña de usuario normal nos aparecerá el menu anterior.

En la opción 1 podremos buscar alojamientos, para ello nos pedirá la localidad en la que queremos buscar, el numero de huéspedes, la fecha de entrada y la fecha de salida. Las fechas deberán ser escritas en el siguiente formato: (YYYY-MM-dd). Ej: "2022-12-24".

![image-20221214074252785](C:\Users\Adrian\AppData\Roaming\Typora\typora-user-images\image-20221214074252785.png)

Cuando nos salgan la vivienda o viviendas elegiremos escribiendo el número de ID del alojamiento deseado, en este caso seria "1111".

![image-20221214074449715](C:\Users\Adrian\AppData\Roaming\Typora\typora-user-images\image-20221214074449715.png)

Confirmaremos la reserva y si todo ha ido correctamente nos saldra un mensaje como el anterior.

En la opción 2 podremos ver los datos de nuestra reserva.

![image-20221214075708599](C:\Users\Adrian\AppData\Roaming\Typora\typora-user-images\image-20221214075708599.png)

En la opción 3 podremos modificar la reserva, ahi nos dira si la queremos eliminar y posteriormente nos dirá que si queremos añadir otra reserva hagamos una búsqueda de nuevo.

![image-20221214075809058](C:\Users\Adrian\AppData\Roaming\Typora\typora-user-images\image-20221214075809058.png)

En la opcion 4 podremos ver nuestro perfil, el cual de una forma cifrada nos mostrará nuestra contraseña. 

¡No te alarmes! Esa contraseña tan larga no es la tuya,  simplemente es para que tengas constacia por si la deseas cambiar en cualquier momento. Te recomendamos guardar tu contraseña en un lugar seguro.

![image-20221214075921940](C:\Users\Adrian\AppData\Roaming\Typora\typora-user-images\image-20221214075921940.png)

En la opción 5 podremos modificar nuestro perfil, ahi tendremos un submenú el cual nos indicará que queremos modificar. El procedimiento es parecido al del registro.

![image-20221214080023838](C:\Users\Adrian\AppData\Roaming\Typora\typora-user-images\image-20221214080023838.png)

Por último tendremos la opción 6 en el cual podremos cerrar sesión volviendo al menu principal donde deberemos iniciar sesión de nuevo si queremos modificar nuestro perfil.

![image-20221214080153009](C:\Users\Adrian\AppData\Roaming\Typora\typora-user-images\image-20221214080153009.png)



# Menú Usuario Propietario 📖

<div id='id4' />

<img src="https://cdn.discordapp.com/attachments/1052443807112773724/1052469575867977830/image.png" style="zoom:80%;" />

En la opción 1 podremos ver las viviendas que tenemos, si no tenemos ninguna nos lanzará un mensaje diciendo que no tenemos ninguna disponible.

![image-20221214073128753](C:\Users\Adrian\AppData\Roaming\Typora\typora-user-images\image-20221214073128753.png)

En la opcion 2 si no tenemos ninguna vivienda podremos añadirla y si no la editaremos. Para editarla tendremos que meter todos los datos nuevamente ya actualizados.

<img src="https://cdn.discordapp.com/attachments/1052443807112773724/1052472253717172254/image.png" style="zoom:80%;" />

En la opción 3 podremos ver las reservas que los usuarios han hecho de la vivienda.

![image-20221214080258504](C:\Users\Adrian\AppData\Roaming\Typora\typora-user-images\image-20221214080258504.png)

En la opción 4 estableceremos un periodo de no disponible en su vivienda. Esto sirve por ejemplo por si quiere ir tranquilamente a su propiedad y que la gente no lo reserve en ese periodo. Se pedira la fecha inicial y fecha de salida, entre esas fechas no habrá ninguna reserva.

![image-20221214073415253](C:\Users\Adrian\AppData\Roaming\Typora\typora-user-images\image-20221214073415253.png)

En la opcion 5 podremos ver nuestro perfil como ya hemos dicho anteriormente.

En la opción 6 podremos modificar nuestro perfil como ya hemos dicho anteriormente.

En la opción 7 podremos cerrar sesión como ya hemos dicho anteriormente.



# Menú  Usuario Administrador 📖

<div id='id5' />

El menu de administrador se compone por las siguientes opciones:

![image-20221214082946026](C:\Users\Adrian\AppData\Roaming\Typora\typora-user-images\image-20221214082946026.png)

La opción 1 veremos todas las viviendas que esten en alquiler. En su defecto si no hay ninguna nos avisará.

![image-20221214083043248](C:\Users\Adrian\AppData\Roaming\Typora\typora-user-images\image-20221214083043248.png)

La opción 2 podremos ver a los usuarios registrados, en caso de que no hubiese ninguno nos avisaría.

![image-20221214083151992](C:\Users\Adrian\AppData\Roaming\Typora\typora-user-images\image-20221214083151992.png)

La opcion 3 podremos ver todas las reservas que haya en el sistema

![image-20221214083331798](C:\Users\Adrian\AppData\Roaming\Typora\typora-user-images\image-20221214083331798.png)

Y las demás opciones son iguales que los usuarios normales y propietarios.



Por último quedaría salirse del programa, para ello selecciona la opcion de salir en el menú principal.

ADVERTENCIA --- SI SALES DEL PROGRAMA LOS DATOS INTRODUCIDOS SE PERDERÁN ---



# Autor ✒️

<div id='id7' />

* **Adrián Lara Bonilla** -  [@Alarbon - GitHub](https://github.com/Alarbon "@Alarbon - GitHub")

  
