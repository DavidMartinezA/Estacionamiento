package com.example.cobro.servicio

import com.example.cobro.modelo.CobroTarifa
import com.example.estacionamiento.excepcion.UsuarioNoExisteExcepcion
import com.example.estacionamiento.servicio.ServicioEstacionamiento
import java.time.Duration
import java.time.LocalDateTime

class ServicioCobroTarifa(
    private val servicioEstacionamiento: ServicioEstacionamiento,
    private val cobroTarifa: CobroTarifa,
) {

    private var tarifaCobroServicioEstacionamiento = 0


    fun duracionServicioEstacionamiento(): Int {

        val calculoDuracionServicio =
            Duration.between(servicioEstacionamiento.estacionamiento.usuarioVehiculo.horaFechaIngresoUsuario,
                LocalDateTime.now()).dividedBy(60).dividedBy(60)

        var horasServicioEstacionamiento = calculoDuracionServicio.seconds
        if (calculoDuracionServicio.nano >= 0) {
            horasServicioEstacionamiento++
        }
        return horasServicioEstacionamiento.toInt()
    }

    suspend fun cobroDuracionServicio(): Int {

        val usuarioExiste =
            servicioEstacionamiento.repositorioUsuarioVehiculo.usuarioExiste(servicioEstacionamiento.estacionamiento.usuarioVehiculo)

        if (usuarioExiste) {
            tarifaCobroServicioEstacionamiento =
                cobroTarifa.cobroTarifa(servicioEstacionamiento.estacionamiento.usuarioVehiculo, duracionServicioEstacionamiento())
        } else {
            throw UsuarioNoExisteExcepcion()
        }
        return tarifaCobroServicioEstacionamiento
    }

}
