package com.estacionamiento.modelo

import com.estacionamiento.excepcion.IngresoNoPermitidoRestriccionExcepcion
import com.usuario.modelo.UsuarioVehiculo
import java.time.LocalDateTime

abstract class Estacionamiento(
    val usuarioVehiculo: UsuarioVehiculo,
    var horaFechaIngresoUsuario: LocalDateTime,
) {

    companion object {
        private const val RESTRICCION_INGRESO_PLACA = 'A'
        private val DIAS_PERMITIDOS = arrayListOf(7, 1)
    }

    init {
        if (!validacionDeCreacionEstacionamiento()) throw IngresoNoPermitidoRestriccionExcepcion()

    }

    //formato regex  para fecha y hora [0-2]{1}[0-9]{3}-[0-1]{1}[0-9]-[0-3]{1}[0-2]{1} [0-9]{2}:[0-5]{1}[0-9]{1}:[0-5]{1}[0-9]{1}
    private fun validacionDeCreacionEstacionamiento(): Boolean = !horaFechaIngresoUsuario.toString().isNullOrEmpty()


    abstract val capacidadEstacionamiento: Int

    fun restriccionDeIngreso(diaDeLaSemana: Int): Boolean {

        var restringido = false
        if (usuarioVehiculo.placaVehiculo.uppercase().first() == RESTRICCION_INGRESO_PLACA) {
            restringido = !DIAS_PERMITIDOS.contains(diaDeLaSemana)
        }
        return restringido
    }
}