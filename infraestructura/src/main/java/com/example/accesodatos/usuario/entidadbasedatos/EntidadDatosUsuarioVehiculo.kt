package com.example.accesodatos.usuario.entidadbasedatos

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class EntidadDatosUsuarioVehiculo(

    @PrimaryKey
    var placaVehiculo: String,
    var tipoDeVehiculo: String,
    var cilindrajeAlto: Boolean,
    var horaFechaIngresoUsuario: LocalDateTime,
)
