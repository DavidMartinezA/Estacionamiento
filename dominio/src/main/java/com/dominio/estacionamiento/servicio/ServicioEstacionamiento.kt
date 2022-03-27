package com.dominio.estacionamiento.servicio

import com.dominio.cobro.CobroTarifaCarro
import com.dominio.cobro.CobroTarifaMoto
import com.dominio.estacionamiento.modelo.Estacionamiento
import com.dominio.excepciones.IngresoNoPermitidoRestriccionExcepcion
import com.dominio.usuario.modelo.UsuarioMoto
import com.dominio.usuario.servicio.ServicioUsuario
import java.time.LocalDateTime

class ServicioEstacionamiento: Estacionamiento() {

    var cobroTarifa= 0

    fun ingresoUsuarioEstacionamiento(servicioUsuario: ServicioUsuario) {

        val capacidad = Estacionamiento().consultarCapacidad(servicioUsuario.usuario,servicioUsuario.consultarLista())
        val diaDeLaSemana = LocalDateTime.now().dayOfWeek.value
        val fechaIngreso = LocalDateTime.now().toLocalDate()
        val restriccion = Estacionamiento().restriccionDeIngreso(servicioUsuario.usuario, diaDeLaSemana)
        servicioUsuario.usuario.fechaIngreso = fechaIngreso

        if (capacidad && !restriccion && servicioUsuario.repositorioUsuario.usuarioExiste(servicioUsuario.usuario)) {
            servicioUsuario.guardar(servicioUsuario.usuario)
        }else{
            throw IngresoNoPermitidoRestriccionExcepcion()
        }
    }

    fun salidaDeUsuariosEstacionamiento(servicioUsuario: ServicioUsuario,
                                        cobroTarifaMoto: CobroTarifaMoto,
                                        cobroTarifaCarro: CobroTarifaCarro): Int {
        val fechaSalida = LocalDateTime.now().toLocalDate()
        val registroIngreso= servicioUsuario.usuario.fechaIngreso
        val duracionServicioEstacionamiento= 8//Todo operacion para calcular las horas entre dos fechas

        if (servicioUsuario.usuario is UsuarioMoto){
            cobroTarifa = CobroTarifaMoto().cobroTarifa(duracionServicioEstacionamiento,servicioUsuario)
        }

        return cobroTarifa

    }
}