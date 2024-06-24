package RecyclerViewHelpers

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jaime.perla.appti.R

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val txtNombrecard: TextView =view.findViewById(R.id.txtTicketDato)
    val txtEstado: TextView = view.findViewById(R.id.txtEstado)
    val imgBorrar: ImageView = view.findViewById(R.id.imgBorrar)
    val imgEditar: ImageView = view.findViewById(R.id.imgEditar)

}