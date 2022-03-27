package com.dominio.estacionamiento.servicio

import com.dominio.cobro.modelo.CobroTarifaCarro
import com.dominio.cobro.modelo.CobroTarifaMoto
import com.dominio.estacionamiento.modelo.Estacionamiento
import com.dominio.excepciones.IngresoNoPermitidoRestriccionExcepcion
import com.dominio.usuario.modelo.Usuario
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


    fun salidaDeUsuariosEstacionamiento(usuario: Usuario,
                                        cobroTarifaMoto: CobroTarifaMoto,
                                        cobroTarifaCarro: CobroTarifaCarro
    ): Int {
        val fechaSalida = LocalDateTime.now().toLocalDate()
        val registroIngreso= usuario.fechaIngreso
        val duracionServicioEstacionamiento= 8//Todo operacion para calcular las horas entre dos fechas

        cobroTarifa = if (usuario is UsuarioMoto){
            CobroTarifaMoto().cobroTarifa(duracionServicioEstacionamiento,usuario)
        }else{
            CobroTarifaCarro().cobroTarifa(duracionServicioEstacionamiento,usuario)
        }
        return cobroTarifa
    }
}