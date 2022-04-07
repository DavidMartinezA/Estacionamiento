package com.usuario.modelo

import com.usuario.excepcion.FormatoPlacaExcepcion
import java.time.LocalDateTime

abstract class UsuarioVehiculo(val placaVehiculo: String) {

    companion object {
        const val FORMATO_PLACA = "[a-zA-Z]{3}-?[0-9]{2}[a-zA-Z0-9]?"
    }

    init {
        if (!validacionDeFormatoPlaca()) throw FormatoPlacaExcepcion()
    }

    lateinit var horaFechaIngresoUsuario: LocalDateTime
    abstract val tipoDeVehiculo: String
    private fun validacionDeFormatoPlaca(): Boolean = placaVehiculo.matches(Regex(FORMATO_PLACA))

}