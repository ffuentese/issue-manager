# issue-manager

Es un sistema multipropósito de seguimiento de incidencias construido en Java (Swing, para escritorio).
También es un ejemplo sencillo del patrón MVC (modelo vista controlador).

Issue Manager is a multipurpose issue tracking system built in Java

## Instrucciones de instalación

Para hacer funcionar este programa, luego de descargar los archivos se debe ejecutar el script de base de datos (**db.sql**) en **MySQL**.

Los datos de conexión de la base de datos están parametrizados en el archivo **connection.properties**. Si algo no funciona o hay que cambiar algún parámetro de la conexión se edita directamente en ese archivo de texto. 

También es necesario agregar el usuario de connection.properties a MySQL y darle privilegios para la base de datos del programa. Esto se puede hacer desde PHPMyAdmin o con el script crear_usuario.sql (ejecutar primero db.sql y luego crear_usuario.sql).

Si Netbeans reclama la falta de commons-codec-1.10 hay que hacer clic en Resolve Problems y seleccionar el archivo commons-codec-1.10 y agregarlo al proyecto (está en la carpeta).

El usuario por defecto es admin y la contraseña 123456. Se puede cambiar en "Manage Users".
