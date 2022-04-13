package com.example.estacionamiento.modelo

import com.example.usuario.modelo.UsuarioVehiculo

abstract class Estacionamiento(
    val usuarioVehiculo: UsuarioVehiculo,
) {

    companion object {
        private const val RESTRICCION_INGRESO_PLACA = 'A'
        private val DIAS_PERMITIDOS = arrayListOf(7, 1)
    }

    abstract val capacidadEstacionamiento: Int
    open val tipoDeUsuario = usuarioVehiculo.tipoDeVehiculo

    fun restriccionDeIngreso(diaDeLaSemana: Int): Boolean {
        var restringido = false
        if (usuarioVehiculo.placaVehiculo.uppercase().first() == RESTRICCION_INGRESO_PLACA) {
            restringido = !DIAS_PERMITIDOS.contains(diaDeLaSemana)
        }
        return restringido
    }
}