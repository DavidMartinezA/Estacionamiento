package com.cobro.servicio

import com.cobro.modelo.CobroTarifa
import com.estacionamiento.servicio.ServicioEstacionamiento
import java.time.Duration
import java.time.LocalDateTime

abstract class ServicioCobroTarifa(
    protected val servicioEstacionamiento: ServicioEstacionamiento,
    protected val cobroTarifa: CobroTarifa,
) {

    protected abstract var tarifaCobroServicioEstacionamiento: Int
    protected abstract var horaFechaSalidaUsuarioUsuario: LocalDateTime

    abstract fun cobroDuracionServicio(): Int

    fun duracionServicioEstacionamiento(): Int {

        val horaIngreso = servicioEstacionamiento.estacionamiento.horaFechaIngresoUsuario
        val diferencia = Duration.between(horaIngreso,
            horaFechaSalidaUsuarioUsuario).dividedBy(60).dividedBy(60)

        var horas = diferencia.seconds
        if (diferencia.nano >= 0) {
            horas++
        }
        return horas.toInt()
    }

}