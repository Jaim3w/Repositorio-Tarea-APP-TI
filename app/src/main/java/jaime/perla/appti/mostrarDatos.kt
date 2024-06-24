package jaime.perla.appti

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class mostrarDatos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mostrar_datos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val ticketId = intent.getStringExtra("TikcetId")
        val numeroTicket = intent.getIntExtra("numeroTicket",0)
        val tituloT = intent.getStringExtra("tituloTicket")
        val descripcionT = intent.getStringExtra("descripcionTicket")
        val responsableT = intent.getStringExtra("responsableTicket")
        val correoAu = intent.getStringExtra("correoAutor")
        val numeroAu = intent.getStringExtra("telefonoAutor")
        val ubicacionT = intent.getStringExtra("ubicacion")
        val estadoT = intent.getStringExtra("estadoTicket")

        val txtUUIDetalle = findViewById<TextView>(R.id.txtUUIDDetalle)
        val txtNumeroTicketDetalle = findViewById<TextView>(R.id.txtNumeroTicketDetalle)
        val txtTituloTicketDetalle = findViewById<TextView>(R.id.txtTituloTicketDetalle)
        val txtTicketDescripcionDetalle = findViewById<TextView>(R.id.txtDescripcionDetalle)
        val txtResponsableDetalle = findViewById<TextView>(R.id.txtResponsableDetalle)
        val txtCorreoAutorDetalle = findViewById<TextView>(R.id.txtCorreoAutorDetalle)
        val txtTelefonoAutorDetalle = findViewById<TextView>(R.id.txtTelefonoAutorDetalle)
        val txtUbicacionDetalle = findViewById<TextView>(R.id.txtUbicacionDetalle)
        val txtEstadoDetalle = findViewById<TextView>(R.id.txtEstadoDetalle)

        txtUUIDetalle.text = ticketId
        txtNumeroTicketDetalle.text = numeroTicket.toString()
        txtTituloTicketDetalle.text = tituloT
        txtTicketDescripcionDetalle.text = descripcionT
        txtResponsableDetalle.text = responsableT
        txtCorreoAutorDetalle.text = correoAu
        txtTelefonoAutorDetalle.text = numeroAu
        txtUbicacionDetalle.text = ubicacionT
        txtEstadoDetalle.text = estadoT

    }

}