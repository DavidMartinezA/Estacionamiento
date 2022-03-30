package com.cobro.servicio

import com.estacionamiento.servicio.ServicioEstacionamiento
import java.time.Duration
import java.time.LocalDate
import kotlin.math.ceil

abstract class ServicioCobroTarifa(
    open val servicioEstacionamiento: ServicioEstacionamiento,
) {

    abstract var tarifaCobroServicioEstacionamiento: Int
    abstract var horaFechaSalidaUsuarioUsuario: LocalDate

    abstract fun registroCobroDuracionServicio(): Int

    fun duracionServicioEstacionamiento(): Int {

        return ceil(Duration.between(horaFechaSalidaUsuarioUsuario,
            servicioEstacionamiento.estacionamiento.horaFechaIngresoUsuario)
            .toHours()
            .toString()
            .toDouble()).toInt()
    }

}