package modelo

data class tbTicket(
    var uuid: String,
    var numeroTicket: Int,
    var tituloTicket: String,
    var descripcionTicket: String,
    var responsableTicket: String,
    var correoAutor: String,
    var telefonoAutor: String,
    var ubicacion: String,
    var estadoTicket: String
)
