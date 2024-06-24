package RecyclerViewHelpers

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import jaime.perla.appti.R
import jaime.perla.appti.mostrarDatos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.ClaseConexion
import modelo.tbTicket

class Adaptador( private var Datos:List<tbTicket>) : RecyclerView.Adapter<ViewHolder>() {
    fun actualizarListado(nuevaLista:List<tbTicket>){
    Datos=nuevaLista
    notifyDataSetChanged()
}
    fun actualizarItem(uuid: String, nuevoTitulo: String ){
        val index = Datos.indexOfFirst { it.uuid == uuid  }
        Datos[index].estadoTicket=nuevoTitulo
        notifyItemChanged(index)
    }


    fun eliminarRegistro(tituloTicket:String,posicion: Int){
val listaDatos =Datos.toMutableList()
        listaDatos.removeAt(posicion)

GlobalScope.launch(Dispatchers.IO){
val objConexion =ClaseConexion().cadenaConexion()

val delteTicket =objConexion?.prepareStatement("delete Ticket where TituloTicket = ?")!!
    delteTicket.setString(1,tituloTicket)
    delteTicket.executeUpdate()

    val commit = objConexion.prepareStatement("commit")
    commit.executeUpdate()
}
Datos=listaDatos.toList()
notifyItemRemoved(posicion)
        notifyDataSetChanged()
    }

    fun ActualizarTicket(nuevoTitulo:String ,uuid:String){
        GlobalScope.launch(Dispatchers.IO){
            val objConexion = ClaseConexion().cadenaConexion()

            val updateTicket =objConexion?.prepareStatement("update Ticket set EstadoTicket = ? where uuid = ?")!!
            updateTicket.setString(1,nuevoTitulo)
            updateTicket.setString(2,uuid)
            updateTicket.executeUpdate()


            val commit = objConexion.prepareStatement("commit")
            commit.executeUpdate()


            withContext(Dispatchers.Main){
                actualizarItem(uuid,nuevoTitulo)
            }

        }




    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista =LayoutInflater.from(parent.context).inflate(R.layout.activity_item_card, parent, false)
        return ViewHolder(vista)
    }

    override fun getItemCount() = Datos.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = Datos[position]
        holder.txtNombrecard.text = item.tituloTicket
        holder.txtEstado.text = item.estadoTicket

        holder.imgBorrar.setOnClickListener {
            val context = holder.itemView.context

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Eliminar")
            builder.setMessage("¿Desea eliminar el Ticket?")

            builder.setPositiveButton("Si")
            { dialog, which ->
                eliminarRegistro(item.tituloTicket, position)
            }
            builder.setNegativeButton("No")
            { dialog, which ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()


        }

        holder.imgEditar.setOnClickListener {

            val context = holder.itemView.context

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Actualizar estado de su ticket")
            val cuadroTexto = EditText(context)
            cuadroTexto.setHint(item.estadoTicket)
            builder.setView(cuadroTexto)
            builder.setPositiveButton("Actualizar")
            { dialog, which ->
                ActualizarTicket(cuadroTexto.text.toString(), item.uuid)
            }
            builder.setNegativeButton("Cancelar")
            { dialog, which ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()


        }
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, mostrarDatos::class.java)
            intent.putExtra(
                "TikcetId",
                item.uuid)

                intent.putExtra(
                    "numeroTicket",
                    item.numeroTicket
                )
                intent.putExtra(
                    "tituloTicket",
                    item.tituloTicket
                )
                intent.putExtra(
                    "descripcionTicket",
                    item.descripcionTicket
                )
                intent.putExtra(
                    "responsableTicket",
                    item.responsableTicket
                )
                intent.putExtra(
                    "correoAutor",
                    item.correoAutor
                )
                intent.putExtra(
                    "telefonoAutor",
                    item.telefonoAutor
                )
                intent.putExtra(
                    "ubicacion",
                    item.ubicacion
                )
                intent.putExtra(
                    "estadoTicket",
                    item.estadoTicket
                )
            context.startActivity(intent)
        }
    }
}
