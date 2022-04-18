package com.example.usuario.modelo

import java.time.LocalDateTime

class UsuarioVehiculoMoto(placaVehiculo: String, var cilindrajeAlto: Boolean) :
    UsuarioVehiculo(placaVehiculo) {

    override val tipoDeVehiculo: String = "Moto"
    override var horaFechaIngresoUsuario = LocalDateTime.now()
}