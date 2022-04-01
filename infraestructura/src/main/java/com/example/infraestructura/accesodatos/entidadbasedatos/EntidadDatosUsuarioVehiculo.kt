package com.example.infraestructura.accesodatos.entidadbasedatos

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class EntidadDatosUsuarioVehiculo(@PrimaryKey val placaVehiculo: String) {

    var horaFechaIngresoUsuario: Date? = null
    var cilindrajeAlto: Boolean = false
}