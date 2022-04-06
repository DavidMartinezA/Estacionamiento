package com.example.infraestructura.accesodatos.usuario.entidadbasedatos

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
class EntidadDatosUsuarioMoto(
    @PrimaryKey var placaVehiculo: String,
    var cilindrajeAlto: Boolean,
    var horaFechaIngresoUsuario: LocalDateTime,
)


