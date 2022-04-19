package com.example.usuario.modelo

import java.time.LocalDateTime

class UsuarioVehiculoMoto(placaVehiculo: String, var cilindrajeAlto: Boolean) :
    UsuarioVehiculo(placaVehiculo) {

    private val hora = LocalDateTime.now()
    override val tipoDeVehiculo: String = "Moto"
    override val horaFechaIngresoUsuario: LocalDateTime = hora
}