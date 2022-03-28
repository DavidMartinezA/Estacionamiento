package com.dominio.estacionamiento.servicio

import com.dominio.cobro.modelo.CobroTarifaCarro
import com.dominio.cobro.modelo.CobroTarifaMoto
import com.dominio.estacionamiento.modelo.Estacionamiento
import com.dominio.estacionamiento.repositorio.RepositorioEstacionamiento
import com.dominio.excepciones.IngresoNoPermitidoRestriccionExcepcion
import com.dominio.excepciones.UsuarioNoExisteExcepcion
import com.dominio.usuario.modelo.Usuario
import com.dominio.usuario.modelo.UsuarioMoto
import java.time.Duration
import java.time.LocalDateTime
import kotlin.math.ceil

class ServicioEstacionamiento(
    var usuario: Usuario,
    var repositorioEstacionamiento: RepositorioEstacionamiento,
) : Estacionamiento() {

    fun ingresoUsuarioEstacionamiento() {

        val diaDeLaSemana = LocalDateTime.now().dayOfWeek.value
        val capacidad = if (repositorioEstacionamiento.usuarioExiste(usuario)) {
            Estacionamiento().consultarCapacidad(usuario,
                repositorioEstacionamiento.listaUsuarios())
        } else {
            false
        }
        val restriccion =
            Estacionamiento().restriccionDeIngreso(usuario, diaDeLaSemana)

        if (capacidad && !restriccion) {
            usuario.fechaIngreso = LocalDateTime.now().toLocalDate()
            repositorioEstacionamiento.guardarUsuario(usuario)
        } else {
            throw IngresoNoPermitidoRestriccionExcepcion()
        }
    }

    fun salidaDeUsuariosEstacionamiento(usuario: Usuario): Int {

        val cobroTarifa: Int
        val fechaSalida = LocalDateTime.now().toLocalDate()
        if (repositorioEstacionamiento.usuarioExiste(usuario)) {

            val registroIngreso = usuario.fechaIngreso
            val duracionServicioEstacionamiento =
                ceil(Duration.between(registroIngreso, fechaSalida).toHours().toString()
                    .toDouble()).toInt()

            if (usuario is UsuarioMoto) {
                cobroTarifa =
                    CobroTarifaMoto().cobroTarifa(duracionServicioEstacionamiento, usuario)
                repositorioEstacionamiento.eliminarUsuario(usuario)
            } else {
                cobroTarifa =
                    CobroTarifaCarro().cobroTarifa(duracionServicioEstacionamiento, usuario)
                repositorioEstacionamiento.eliminarUsuario(usuario)
            }
        } else {
            throw UsuarioNoExisteExcepcion()
        }
        return cobroTarifa
    }
}