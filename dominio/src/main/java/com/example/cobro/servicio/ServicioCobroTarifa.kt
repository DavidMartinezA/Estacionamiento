package com.example.cobro.servicio

import com.example.cobro.modelo.CobroTarifa
import com.example.estacionamiento.excepcion.UsuarioNoExisteExcepcion
import com.example.usuario.modelo.UsuarioVehiculo
import com.example.usuario.repositorio.RepositorioUsuarioVehiculo
import java.time.Duration
import java.time.LocalDateTime
import javax.inject.Inject

class ServicioCobroTarifa @Inject constructor(private val repositorioUsuarioVehiculo: RepositorioUsuarioVehiculo) {

    private fun duracionServicioEstacionamiento(horaFechaIngresoUsuario: LocalDateTime): Int {
        val horaSalida = LocalDateTime.now()
        val calculoDuracionServicio = Duration.between(horaFechaIngresoUsuario, horaSalida).dividedBy(60).dividedBy(60)

        var horasServicioEstacionamiento = calculoDuracionServicio.seconds

        if (calculoDuracionServicio.nano >= 0) {
            horasServicioEstacionamiento++
        }
        return horasServicioEstacionamiento.toInt()
    }

    suspend fun cobroDuracionServicio(placaUsuario: String, cobroTarifa: CobroTarifa): Int {
        if (repositorioUsuarioVehiculo.usuarioExiste(placaUsuario)) {
            val vehiculo = repositorioUsuarioVehiculo.usuarioPorPlaca(placaUsuario)
            return cobroTarifa.cobroTarifa(vehiculo, duracionServicioEstacionamiento(vehiculo.horaFechaIngresoUsuario))
        } else {
            throw UsuarioNoExisteExcepcion()
        }
    }

    suspend fun eliminarUsuario(placaUsuario: String) {
        repositorioUsuarioVehiculo.eliminarUsuario(placaUsuario)
    }

    suspend fun obtenerVehiculoPorPlaca(placaUsuario: String): UsuarioVehiculo {
        if (repositorioUsuarioVehiculo.usuarioExiste(placaUsuario)) {
            return repositorioUsuarioVehiculo.usuarioPorPlaca(placaUsuario)
        } else {
            throw UsuarioNoExisteExcepcion()
        }
    }
}
