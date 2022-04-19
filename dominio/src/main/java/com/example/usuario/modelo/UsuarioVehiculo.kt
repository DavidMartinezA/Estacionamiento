package com.example.usuario.modelo

import com.example.usuario.excepcion.FormatoPlacaExcepcion
import java.time.LocalDateTime

abstract class UsuarioVehiculo(val placaVehiculo: String) {

    companion object {
        const val FORMATO_PLACA = "[a-zA-Z]{3}-?[0-9]{2}[a-zA-Z0-9]?"
    }

    init {
        if (!validacionDeFormatoPlaca()) throw FormatoPlacaExcepcion()
    }

    abstract val horaFechaIngresoUsuario: LocalDateTime
    open var cilndrajeAlto: Boolean = false
    abstract val tipoDeVehiculo: String
    private fun validacionDeFormatoPlaca(): Boolean = placaVehiculo.matches(Regex(FORMATO_PLACA))

}