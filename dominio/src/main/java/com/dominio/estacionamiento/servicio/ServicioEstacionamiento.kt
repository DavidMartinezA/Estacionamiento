package com.dominio.estacionamiento.servicio

import com.dominio.estacionamiento.modelo.Estacionamiento
import com.dominio.excepciones.IngresoNoPermitidoRestriccionExcepcion
import com.dominio.usuario.servicio.ServicioUsuario
import java.time.LocalDateTime

class ServicioEstacionamiento(var estacionamiento: Estacionamiento) {

    fun ingresoUsuarioEstacionamiento(servicioUsuario: ServicioUsuario) {

        val capacidad = estacionamiento.consultarCapacidad(servicioUsuario.usuario,servicioUsuario.consultarLista())
        val diaDeLaSemana = LocalDateTime.now().dayOfWeek.value
        val fechaIngreso = LocalDateTime.now().toLocalDate()
        val restriccion = estacionamiento.restriccionDeIngreso(servicioUsuario.usuario, diaDeLaSemana)
        servicioUsuario.usuario.fechaIngreso = fechaIngreso

        if (capacidad && !restriccion && servicioUsuario.repositorioUsuario.usuarioExiste(servicioUsuario.usuario)) {
            servicioUsuario.guardar(servicioUsuario.usuario)
        }else{
            throw IngresoNoPermitidoRestriccionExcepcion()
        }
    }
}