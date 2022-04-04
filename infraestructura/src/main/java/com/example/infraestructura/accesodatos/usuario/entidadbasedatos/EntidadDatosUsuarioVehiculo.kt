package com.example.infraestructura.accesodatos.usuario.entidadbasedatos

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
class EntidadDatosUsuarioVehiculo(@PrimaryKey val placaVehiculo: String) {

    lateinit var horaFechaIngresoUsuario: LocalDateTime
    var cilindrajeAlto: Boolean = false
}