package jaime.perla.appti

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import modelo.ClaseConexion

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtCorreoL = findViewById<EditText>(R.id.txtIdCorreo)
        val txtContrasenaL = findViewById<EditText>(R.id.txtContraLogin)
        val btnEntrar= findViewById<Button>(R.id.btnEntrar)
        val btnregistrarme = findViewById<Button>(R.id.btnRegistrarme)

        btnEntrar.setOnClickListener {
            val pantallaprinci =Intent(this,PantallaPrincipal::class.java)
            GlobalScope.launch(Dispatchers.IO){
                val objConexion = ClaseConexion().cadenaConexion()
                val comprobarUsuario = objConexion?.prepareStatement("SELECT * FROM tbUsuarios WHERE correoElectronico = ? AND clave = ?")!!
                comprobarUsuario.setString(1, txtCorreoL.text.toString())
                comprobarUsuario.setString(2, txtContrasenaL.text.toString())
                val resultado = comprobarUsuario.executeQuery()
                //Si encuentra un resultado
                if (resultado.next()) {
                    startActivity(pantallaprinci)}
                else{
                    println("Usuario no encontrado, verifique las credenciales")
                    }
            }
        }
btnregistrarme.setOnClickListener {
val pantallaregister = Intent(this, registrarme::class.java)
startActivity(pantallaregister)
}
    }


}