package com.example.infraestructura.accesodatos.entidadbasedatos

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
class EntidadDatosUsuarioCarro(@PrimaryKey var placaVehiculo: String, var horaFechaIngresoUsuario: LocalDateTime) {
}