package com.cobro.servicio

import com.estacionamiento.modelo.Estacionamiento
import com.estacionamiento.repositorio.RepositorioEstacionamiento
import java.time.Duration
import java.time.LocalDate
import kotlin.math.ceil

abstract class ServicioCobroTarifa(
    open val estacionamiento: Estacionamiento,
    open val horaFechaSalidaUsuarioUsuario: LocalDate,
    protected open val repositorioEstacionamiento: RepositorioEstacionamiento,
) {

    protected var duracionServicioEstacionamiento: Int = 0
    abstract var tarifaCobroServicioEstacionamiento: Int

    abstract fun registroCobroDuracionServicio(): Int

    fun duracionServicioEstacionamiento(): Int {

        duracionServicioEstacionamiento =
            ceil(Duration.between(horaFechaSalidaUsuarioUsuario,
                estacionamiento.horaFechaIngresoUsuario)
                .toHours()
                .toString()
                .toDouble()).toInt()
        return duracionServicioEstacionamiento
    }


}