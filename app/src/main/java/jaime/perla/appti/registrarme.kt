package jaime.perla.appti

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.ClaseConexion
import java.util.UUID


class registrarme : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrarme)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val imgAtras =findViewById<ImageView>(R.id.imgAtras)
        val txtEmail =findViewById<TextView>(R.id.txtCorreo)
        val txtContrasenaR = findViewById<TextView>(R.id.txtContrasenaR)
        val txtConfirmarContra = findViewById<TextView>(R.id.txtRepiteContra)
        val imgVerContra = findViewById<ImageView>(R.id.imgVer1)
        val imgConfirmarContra = findViewById<ImageView>(R.id.imgVerConfirmarContrasena)
        val btncrearCuenta = findViewById<Button>(R.id.btnCrearCuenta)
        val btnVolverLogin = findViewById<Button>(R.id.btnIniciarSesion)

        //TODO:HAREMOS EL BOTON PARA CREAR CUENTA//
        btncrearCuenta.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO){
                val objconexion =ClaseConexion().cadenaConexion()
                val crearUser= objconexion?.prepareStatement("INSERT INTO tbUsuarios(UUID_usuario, correoElectronico, clave) VALUES (?, ?, ?)")!!
                crearUser.setString(1,UUID.randomUUID().toString())
                crearUser.setString(2,txtEmail.text.toString())
                crearUser.setString(3,txtContrasenaR.text.toString())
                crearUser.executeUpdate()
                withContext(Dispatchers.Main){
                    Toast.makeText(this@registrarme,"Usuario Creado",Toast.LENGTH_SHORT).show()
                    txtEmail.setText("")
                    txtContrasenaR.setText("")
                    txtConfirmarContra.setText("")
                }
            }
        }


        //TODO:ACA SE HARA LOS BOTONES PARA VER CONTRASEÑA ENTRE OTRAS COSAS//
imgVerContra.setOnClickListener {
    if (txtContrasenaR.inputType==InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
    {
        txtContrasenaR.inputType=   InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
    }
    else{
txtContrasenaR.inputType=
InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    }
}
        imgConfirmarContra.setOnClickListener {
        if (txtConfirmarContra.inputType==InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD) {
txtConfirmarContra.inputType =InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        }else {
txtConfirmarContra.inputType =
InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        }
imgAtras.setOnClickListener {
val pantallaInicio =Intent(this, MainActivity::class.java)
    startActivity(pantallaInicio)
}

btnVolverLogin.setOnClickListener {
    val pantallaLogin = Intent(this,MainActivity::class.java)
startActivity(pantallaLogin)
}
    }
}