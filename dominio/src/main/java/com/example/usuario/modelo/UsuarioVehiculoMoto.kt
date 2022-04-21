package com.example.usuario.modelo

import java.time.LocalDateTime

class UsuarioVehiculoMoto(placaVehiculo: String, var cilindrajeAlto: Boolean) :
    UsuarioVehiculo(placaVehiculo) {

    override var tipoDeVehiculo: String = "Moto"
    override var cilndrajeAlto = cilindrajeAlto
    override var horaFechaIngresoUsuario = LocalDateTime.now()
}