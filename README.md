# Challenge ONE | Java | Back-end | Hotel Alura

<p align="center" >
     <img width="300" heigth="300" src="https://user-images.githubusercontent.com/91544872/189419040-c093db78-c970-4960-8aca-ffcc11f7ffaf.png">
</p>

---
## 🖥️ Tecnologías Utilizadas:

- Java
- Eclipse
- Biblioteca JCalendar
- MySql
- Plugin WindowBuilder </br>

---

## Requisitos del proyecto:

1_ Sistema de autenticación de usuario para que solo usuarios pertenecientes al hotel consigan acceder al sistema.

2_ Permitir crear, editar y eliminar una reserva para los clientes.

3_ Buscar en la base de datos todas las informaciones tanto de los clientes como de las reservas.

4_ Registrar, editar y eliminar datos de los huéspedes.

5_ Calcular el valor de la reserva en base a la cantidades de días de la reserva y a una tasa diaria que puede ser asignada por ti y en la moneda local de tu país, por ejemplo si tenemos una reserva de 3 dias y el valor de nuestra diaria son 20$ debemos multiplicar esos 3 dias por el valor de la diaria, lo que serian 60$, todo esto deberá ser hecho automaticamente y mostrado al usuario antes de guardar la reserva.

6_ Base de datos para almacenar todos los datos pedidos anteriormente.

---
##Muestra del proyecto
---
imagen 1

El menú principal de nuestra aplicación es nuestra presentación, podemos ver que contiene diferentes elementos:

Imagen de wallpaper

Logo del Hotel

Botón Login que al ser clickado debe llevar a la ventana de iniciar sesión

Botón salir que debe preguntar al usuario si realmente desea salir de la aplicación.

---

Imagen 2

Esta ventana debe permitir al usuario ingresar su usuario y contraseña.
Por detrás de la cortina debemos verificar si los datos ingresados son correctos o incorrectos y notificar al usuario de este estatus, por ejemplo si los datos son incorrectos debe ser mostrado una mensaje al usuario diciendo "Usuario y Contraseña inválidos"

Para realizar esta tarea puedes crear usuarios predeterminados a través del código, que seria una opción más simple, o crear una tabla en tu base de datos para realizar esta autenticación e inclusive adicionar nuevos usuários.

---

imagen 3

En esta ventana se presentarán las opciones cuando el usuario autenticado está dentro del sistema, tienes las siguientes opciones:

Reservar: lleva a la ventana de Reservas.

Búsqueda: lleva a la ventana de Sistema de búsqueda.

Salir del Sistema: lleva a la ventana de Login.

---

imagen 4

La ventana de Reservas debe permitir al usuario registrar los siguientes datos:

Día de Entrada

Día de Salida

Valor de la reserva, que será calculado en base a un valor de diaria fijo que tú mismo podrás elegir.

Forma de Pago donde el usuario podrá elegir entre:

Tarjeta de crédito

Tarjeta de débito

Dinero en efectivo

Botón continuar

Es importante que al momento de guardar nuestros datos sea generado un número de reserva que será utilizado en la sección de registro de huéspedes de forma automática ya que será un campo que el usuario no podrá manipular.
