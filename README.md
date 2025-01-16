# Reserve

Este proyecto ser basa en un sistema que permite gestionar reservaciones ya sea para pedir/crear una reservación, asi como poder consultarla, actualizarla y cancelarla/borrarla.
El proyecto esta desarrollado con el lenguaje Java 17 junto con Spring boot 3.4.1 , thymeleaf, Spring boot jpa y la base datos MySQL.

## Para empezar


### Prerequisitos

Asegurese de correr MySQL. Despues configure los siguentes parametros en el archivo application.properties (src/main/resources/application.properties)

![image](https://github.com/user-attachments/assets/2707afe5-6b16-4c82-b73a-6de3c9c75944)

Si no utiliza el puerto por defecto de MySQL asegurese de cambiarlo en el spring.datasource.url. Adicionalmente cambie los parametros user y password por los que utilice ya que sin estos el proyecto no tendra acceso para crear y gestionar la base de datos. 

Nota: no es necesario crear un schema antes de correr el proyecto. al realizar el primer run el proyecto se encargara de crear la base de datos con los parametros asigandos.

## Running

Asegurese de compilar el proyecto y ejecutar el archivo ReserveApplication.java
```
src/main/java/com/prueba/reserve/ReserveApplication.java
```
![image](https://github.com/user-attachments/assets/04549c98-110c-4fcb-b39c-3489bad0f443)

![image](https://github.com/user-attachments/assets/b0382c5c-c50d-43c9-9522-c54ed4fe16c9)

Al ver el log "Started ReserveApplication... " ingrese al navegador de si preferencia y accederemos a la aplicacion por la url http://localhost:8080/.
Asegurese de que en la primera ejecucion ingresar esta url ya que esto permite inicializar algunos valores de prueba en la base de datos.

![image](https://github.com/user-attachments/assets/ed074248-c2f1-498d-a613-7d5357c6399b)

Al ver la siguiente ventana podemos proceder dando click en "Iniciar" lo que nos lleva a la pagina principal.

![image](https://github.com/user-attachments/assets/7b5cc0bc-a572-4336-ae86-1c34983dfd6f)

Nota: despues de la primera ejecución podremos acceder a la aplicación con el url http://localhost:8080/index .

A continuación empiece a navegar por la aplicacion por medio de los botones "Reservar" "Consultar Reserva" y "Listar Reserva por Dia".


### Crear/Pedir una reservación

Si ingresamos a "Reservar" navegaremos por el proceso para crear/pedir una reservación. Iniciando por pedir la fecha en la que queremos la reservación.

![image](https://github.com/user-attachments/assets/3c652ae3-e649-4c27-9964-b1f6d21d5a94)

Al seleccionar la fecha y dar en el boton "Buscar Horario" se procede a ver los horarios disponibles para esa fecha y la seccion donde se diligencia la información del cliente que va a reservar.

![image](https://github.com/user-attachments/assets/94df11e1-c4eb-4429-aebf-fb54e5575312)

Nota: Al no poder realizar dos reservaciones en el mismo horario la lista desplegable de horarios disponibles solo mostrara los horarios que no han sido asignados a una reserva. Al desplegarla  podras ver el resto de horarios disponibles y en caso de que no haya horarios disponibles solo deberia aparecer un mensaje indicando "No hay reservas disponibles para este dia!".

Al diligenciar los datos y dar clic en el boton "Reservar" volveremos a la pagina principal y veremos un mensaje indicando que la reservacion se ha creado exitosamente y nos devuelve el id de la reserva que acabamos de crear.

![image](https://github.com/user-attachments/assets/10202834-d06b-4838-95c2-8fdbf788c4a1)

Recuerde guardar el id de la reservacion para poder consultarla mas adelante. (En este caso "06a466e1-7b90-4ebf-989e-a548b1a95379")

### Consultar una reservación

Si ingresamos a "Consultar Reserva" podremos buscar la reservacion que creamos anteriormente a travez del id que nos entregaron.

![image](https://github.com/user-attachments/assets/d27050a0-950c-4fa6-90a3-6471f1af0559)

Al dar "Buscar" podremos ver todos lo datos relacionador a la reservación, como la fecha, hora y los datos del cliente

![image](https://github.com/user-attachments/assets/10b3b58c-a30c-4684-9e79-4a1a70ef89c1)

### Modificar y Cancelar una reservación

En la parte inferir de la aterior pestaña tenemos las opciones de modificar y cancelar la reservación. Actualmete tenemos dos opciones de modificación la primera que nos permite modificar la hora y la fecha de la reservación siempre y cuando estos esten disponibles y la segunda nos permite modificar algunos datos del cliente como sus datos de contacto. (A futuro podrian implementarse mas opciones como el estado de la resevación.

Al cancelar una reservación llegaremos a la pagina principal con un mensaje que nos indica que fue cancelada con exito.

![image](https://github.com/user-attachments/assets/d0ef5e87-e60a-4227-938e-13f9818e7f32)


### Listar Reservas por día

Al ingresar por esta opción se selecciona la fecha de la cual se quiere consultar las reservas que hay para ese dia y en caso de que no haya ninguna sera notificado de ello. Esta sección esta pensada para los restaurante que deseen consultar las reservaciónes que haya para el dia que elijan.

![image](https://github.com/user-attachments/assets/83ee8c5a-5070-4c12-8821-e95f0e59eef5)

Se podra ver una tabla con los datos de las diferentes reservas que se puedan generar.

![image](https://github.com/user-attachments/assets/10dc6ab4-dc94-4bb1-8a3c-38032b49b0d2)

![image](https://github.com/user-attachments/assets/a6c90bd2-6d74-47da-adb6-7e0309656a14)

