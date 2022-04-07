package com.usuario.modelo

class UsuarioVehiculoMoto(placaVehiculo: String, var cilindrajeAlto: Boolean = false) :
    UsuarioVehiculo(placaVehiculo) {

    override val tipoDeVehiculo: String = "Moto"
}