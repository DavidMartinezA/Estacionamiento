package com.estacionamiento.modelo

import com.usuario.modelo.UsuarioVehiculo

abstract class Estacionamiento(val usuarioVehiculo: UsuarioVehiculo) {

    companion object {

        private const val RESTRICCION_INGRESO_LETRA_INICIAL_PLACA = 'A'

    }

    abstract val capacidadEstacionamiento: Int

    private val diasPermitidos = arrayListOf(7, 1)

    abstract fun consultarCapacidad(): Boolean

    fun restriccionDeIngreso(diaDeLaSemana: Int): Boolean {

        var restringido = false
        if (usuarioVehiculo.placaVehiculo.uppercase()
                .first() == RESTRICCION_INGRESO_LETRA_INICIAL_PLACA
        ) {
            restringido = !diasPermitidos.contains(diaDeLaSemana)
        }
        return restringido
    }

}