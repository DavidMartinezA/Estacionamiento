package com.usuario.modelo

import com.excepciones.FormatoPlacaExcepcion
import java.time.LocalDate

abstract class UsuarioVehiculo(val placaVehiculo: String) {

    companion object {
        const val FORMATO_PLACA = "[a-zA-Z]{3}-?[0-9]{2}[a-zA-Z0-9]?"
    }

    lateinit var fechaIngreso: LocalDate

    init {
        if (!validacionDeFormatoPlaca()) throw FormatoPlacaExcepcion()
    }

    private fun validacionDeFormatoPlaca(): Boolean = placaVehiculo.matches(Regex(FORMATO_PLACA))

}