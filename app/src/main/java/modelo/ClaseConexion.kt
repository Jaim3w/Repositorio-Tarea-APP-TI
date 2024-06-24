package modelo

import java.sql.DriverManager

class ClaseConexion {
    fun cadenaConexion():java.sql.Connection?{

        try {
            val url ="jdbc:oracle:thin:@192.168.3.4:1521:xe"
            val usuario ="system"
            val contrasena ="lili"

            val conexion = DriverManager.getConnection(url, usuario, contrasena)
            return conexion
        }catch (e:Exception)
        {
            println("Algo no salio como se esperaba :C $e")
            return null
        }

    }
}