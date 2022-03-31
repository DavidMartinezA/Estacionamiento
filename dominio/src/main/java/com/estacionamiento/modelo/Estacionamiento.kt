package com.estacionamiento.modelo

import com.usuario.modelo.UsuarioVehiculo
import java.time.LocalDateTime

abstract class Estacionamiento(
    val usuarioVehiculo: UsuarioVehiculo,
    var horaFechaIngresoUsuario: LocalDateTime,
) {

    companion object {

        private const val RESTRICCION_INGRESO_PLACA = 'A'

    }

    private val diasPermitidos = arrayListOf(7, 1)
    abstract val capacidadEstacionamiento: Int

    fun restriccionDeIngreso(diaDeLaSemana: Int): Boolean {

        var restringido = false
        if (usuarioVehiculo.placaVehiculo.uppercase().first() == RESTRICCION_INGRESO_PLACA) {
            restringido = !diasPermitidos.contains(diaDeLaSemana)
        }
        return restringido
    }
}