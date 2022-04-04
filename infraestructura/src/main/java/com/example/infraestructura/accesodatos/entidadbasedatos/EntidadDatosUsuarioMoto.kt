package com.example.infraestructura.accesodatos.entidadbasedatos

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
class EntidadDatosUsuarioMoto(
    @PrimaryKey var placaVehiculo: String,
    var horaFechaIngresoUsuario: LocalDateTime,
    var altoCilindraje: Boolean,
)