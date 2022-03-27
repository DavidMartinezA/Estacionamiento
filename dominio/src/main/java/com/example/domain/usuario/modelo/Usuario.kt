package com.example.domain.usuario.modelo

import com.example.domain.usuario.Excepciones.FormatoPlacaExcepcion

abstract class Usuario(val placaVehiculo: String) {

    companion object {
        const val FORMATO_PLACA = "[a-zA-Z]{3}-?[0-9]{2}[a-zA-Z0-9]?"// debe limitar que solo sean 6 caracteres
    }

    init {
        if (!validacionDeFormatoPlaca()) throw FormatoPlacaExcepcion()
    }

    private fun validacionDeFormatoPlaca(): Boolean = placaVehiculo.matches(Regex(FORMATO_PLACA))

}