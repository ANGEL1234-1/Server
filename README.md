# Server

Programa sencillo en Java que actua como servidor multicliente y permite la comunicación entre estos.

# Cómo usar

Abrir la consola de terminal y escribir ```java -jar Server.jar```

# Cómo funciona

Al lanzarse el programa crea un socket en el puerto ```54321``` y escucha solicitudes de conexión, al recibir una crea un hilo que maneja la conexión del cliente tras lo cual repite el proceso.

# Special Thanks to

Mercedes por enseñarnos que se puede hacer esto y Stack Overflow por hacer el proceso de manejar los hilos más fácil.
