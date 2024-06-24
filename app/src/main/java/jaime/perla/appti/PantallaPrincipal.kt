package jaime.perla.appti

import RecyclerViewHelpers.Adaptador
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.ClaseConexion
import modelo.tbTicket
import java.util.UUID

class PantallaPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pantalla_principal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtNumeroTicket = findViewById<TextView>(R.id.txtNumeroTicket)
        val txtTituloTicket=findViewById<TextView>(R.id.txtTituloTicket)
        val txtDescripcion = findViewById<TextView>(R.id.txtDescripcionTicket)
        val txtResponsableTicket = findViewById<TextView>(R.id.txtResponsable)
        val txtEmailTicket = findViewById<TextView>(R.id.txtCorreoAutor)
        val txtTelefono = findViewById<TextView>(R.id.txtTelefono)
        val txtUbicacion = findViewById<TextView>(R.id.txtUbicacion)
        val txtEstado = findViewById<TextView>(R.id.txtEstadoTicket)
        val btnCrearTicket = findViewById<Button>(R.id.btnCrearTicket)


        val rcvTicket = findViewById<RecyclerView>(R.id.rcvTicket)
        rcvTicket.layoutManager=LinearLayoutManager(this)

                //TODO:Mostramos datos
        fun obtenerTicket (): List<tbTicket>{
            val objConexion = ClaseConexion().cadenaConexion()

                    val statement = objConexion?.createStatement()
                    val resultSet = statement?.executeQuery("SELECT * FROM Ticket")!!

                    val listaTicket = mutableListOf<tbTicket>()

                    while (resultSet.next()){
                        val uuid = resultSet.getString("uuid")
                        val numeroTicket = resultSet.getInt("NumeroTicket")
                        val tituloTicket = resultSet.getString("TituloTicket")
                        val descripcionTicket =resultSet.getString("DescripcionTicket")
                        val responsableTicket = resultSet.getString("ResponsableTicket")
                        val correoAutor = resultSet.getString("CorreoAutor")
                        val telefonoAutor = resultSet.getString("TelefonoAutor")
                        val ubicacion = resultSet.getString("Ubicacion")
                        val estadoTicket = resultSet.getString("EstadoTicket")

                        val valoresTodos= tbTicket(uuid, numeroTicket, tituloTicket, descripcionTicket, responsableTicket, correoAutor, telefonoAutor, ubicacion, estadoTicket)

                        listaTicket.add(valoresTodos)
                    }
                    return listaTicket
        }

        CoroutineScope(Dispatchers.IO).launch {
          val TicketBD = obtenerTicket()
            withContext(Dispatchers.Main){
                val miadapter = Adaptador(TicketBD)
                rcvTicket.adapter = miadapter
            }
        }

        btnCrearTicket.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val objConexion =ClaseConexion().cadenaConexion()

                val agregarTicket = objConexion?.prepareStatement("insert into Ticket (uuid, NumeroTicket, TituloTicket, DescripcionTicket, ResponsableTicket, CorreoAutor, TelefonoAutor, Ubicacion, EstadoTicket) values (?, ?, ?, ?, ?, ?, ?, ?, ?)")!!
                agregarTicket.setString(1,UUID.randomUUID().toString())
                agregarTicket.setInt(2,txtNumeroTicket.text.toString().toInt())
                agregarTicket.setString(3,txtTituloTicket.text.toString())
                agregarTicket.setString(4,txtDescripcion.text.toString())
                agregarTicket.setString(5,txtResponsableTicket.text.toString())
                agregarTicket.setString(6,txtEmailTicket.text.toString())
                agregarTicket.setString(7,txtTelefono.text.toString())
                agregarTicket.setString(8,txtUbicacion.text.toString())
                agregarTicket.setString(9,txtEstado.text.toString())
                agregarTicket.executeUpdate()

                val nuevoTicket =obtenerTicket()
                withContext(Dispatchers.Main){
                        (rcvTicket.adapter as? Adaptador)?.actualizarListado(nuevoTicket)
                    txtNumeroTicket.setText("")
                    txtTituloTicket.setText("")
                    txtDescripcion.setText("")
                    txtResponsableTicket.setText("")
                    txtEmailTicket.setText("")
                    txtTelefono.setText("")
                    txtUbicacion.setText("")
                    txtEstado.setText("")
                }
            }
        }
    }
}