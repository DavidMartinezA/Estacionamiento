package com.estacionamiento.modelo

import com.excepciones.TipoDeUsuarioNoAdmitidoExcepcion
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculoCarro
import com.usuario.modelo.UsuarioVehiculoMoto

class Estacionamiento() {

    companion object {

        const val CAPACIDAD_ESTACIONAMIENTO_CARROS = 20
        const val CAPACIDAD_ESTACIONAMIENTO_MOTOS = 10
        const val RESTRICCION_INGRESO_LETRA_INICIAL_PLACA = 'A'
    }

    private var capacidaDelParqueadero: Boolean = false
    private val diasPermitidos = arrayListOf(7, 1)

    fun consultarCapacidad(
        usuarioVehiculo: UsuarioVehiculo,
        listaUsuarioVehiculos: List<UsuarioVehiculo>,
    ): Boolean {

        capacidaDelParqueadero = when (usuarioVehiculo) {
            is UsuarioVehiculoCarro -> {
                CAPACIDAD_ESTACIONAMIENTO_CARROS >
                        listaUsuarioVehiculos.filterIsInstance<UsuarioVehiculoCarro>().size
            }
            is UsuarioVehiculoMoto -> {
                CAPACIDAD_ESTACIONAMIENTO_MOTOS >
                        listaUsuarioVehiculos.filterIsInstance<UsuarioVehiculoMoto>().size
            }
            else -> {
                throw TipoDeUsuarioNoAdmitidoExcepcion()
            }
        }
        return capacidaDelParqueadero
    }

    fun restriccionDeIngreso(usuarioVehiculo: UsuarioVehiculo, diaDeLaSemana: Int): Boolean {

        var restringido = false
        if (usuarioVehiculo.placaVehiculo.uppercase()
                .first() == RESTRICCION_INGRESO_LETRA_INICIAL_PLACA
        ) {
            restringido = !diasPermitidos.contains(diaDeLaSemana)
        }
        return restringido
    }

}