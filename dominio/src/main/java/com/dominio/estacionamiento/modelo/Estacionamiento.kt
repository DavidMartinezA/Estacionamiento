package com.dominio.estacionamiento.modelo

import com.dominio.excepciones.TipoDeUsuarioNoAdmitidoExcepcion
import com.dominio.usuario.modelo.Usuario
import com.dominio.usuario.modelo.UsuarioCarro
import com.dominio.usuario.modelo.UsuarioMoto

open class Estacionamiento() {

    companion object {

        const val CAPACIDAD_ESTACIONAMIENTO_CARROS = 20
        const val CAPACIDAD_ESTACIONAMIENTO_MOTOS = 10
        const val RESTRICCION_INGRESO_LETRA_INICIAL_PLACA = 'A'
    }

    private var capacidaDelParqueadero: Boolean = false
    private val diasPermitidos = arrayListOf(7, 1)

    fun consultarCapacidad(usuario: Usuario, listaUsuarios: List<Usuario>): Boolean {

        capacidaDelParqueadero = when (usuario) {
            is UsuarioCarro -> {
                CAPACIDAD_ESTACIONAMIENTO_CARROS >
                        listaUsuarios.filterIsInstance<UsuarioCarro>().size
            }
            is UsuarioMoto -> {
                CAPACIDAD_ESTACIONAMIENTO_MOTOS >
                        listaUsuarios.filterIsInstance<UsuarioMoto>().size
            }
            else -> {
                throw TipoDeUsuarioNoAdmitidoExcepcion()
            }
        }
        return capacidaDelParqueadero
    }

    fun restriccionDeIngreso(usuario: Usuario, diaDeLaSemana: Int): Boolean {

        var restringido = false
        if (usuario.placaVehiculo.uppercase().first() == RESTRICCION_INGRESO_LETRA_INICIAL_PLACA) {
            restringido = !diasPermitidos.contains(diaDeLaSemana)
        }
        return restringido
    }

}