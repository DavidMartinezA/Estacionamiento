package com.example.infraestructura.accesodatos.usuario.entidadbasedatos

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
class EntidadDatosUsuarioVehiculo(
    @PrimaryKey var placaVehiculo: String, var horaFechaIngresoUsuario: LocalDateTime,
    var tipoDeVehiculo: String,
    var cilindrajeAlto: Boolean = false,
)


