package com.example.infraestructura.accesodatos.usuario.entidadbasedatos

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class EntidadDatosUsuarioVehiculoCarro(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var placaVehiculo: String,
    var tipoDeVehiculo: String,
    var horaFechaIngresoUsuario: LocalDateTime,
)
