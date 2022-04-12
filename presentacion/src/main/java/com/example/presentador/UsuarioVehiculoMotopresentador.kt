package com.example.presentador

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cobro.modelo.CobroTarifaMoto
import com.example.cobro.servicio.ServicioCobroTarifa
import com.example.estacionamiento.modelo.EstacionamientoMoto
import com.example.estacionamiento.servicio.ServicioEstacionamiento
import com.example.example.infraestructura.accesodatos.usuario.repositorio.RepositorioUsuarioVehiculoMotoRoom
import com.example.usuario.modelo.UsuarioVehiculoMoto
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class UsuarioVehiculoMotopresentador(contexto: Context) : ViewModel() {

    private var repositorioRoomMoto = RepositorioUsuarioVehiculoMotoRoom(contexto)

    fun setIngresarUsuario(usuarioIngresado: String, altoCilindraje: Boolean) = viewModelScope.launch {

        val usuarioVehiculoMoto = UsuarioVehiculoMoto(usuarioIngresado, altoCilindraje)
        val servicioEstacionamiento =
            ServicioEstacionamiento(EstacionamientoMoto(usuarioVehiculoMoto), repositorioRoomMoto)

        servicioEstacionamiento.ingresoUsuarioEstacionamiento(LocalDateTime.now().dayOfWeek.value)

    }

    fun getCobroServicio(usuarioIngresado: String, altoCilindraje: Boolean): Int {

        val usuarioVehiculoMoto = UsuarioVehiculoMoto(usuarioIngresado, altoCilindraje)
        val estacionamiento = EstacionamientoMoto(usuarioVehiculoMoto)
        val cobroServicio = CobroTarifaMoto(estacionamiento)
        var tarifa = 0
        val servicioTarifa =
            ServicioCobroTarifa(ServicioEstacionamiento(estacionamiento, repositorioRoomMoto), cobroServicio, LocalDateTime.now())

        viewModelScope.launch {
            tarifa = servicioTarifa.cobroDuracionServicio()
        }
        return tarifa
    }

    fun setEliminarUsuario(usuarioIngresado: String, altoCilindraje: Boolean) = viewModelScope.launch {

        val usuarioVehiculoMoto = UsuarioVehiculoMoto(usuarioIngresado, altoCilindraje)
        val servicioEstacionamiento =
            ServicioEstacionamiento(EstacionamientoMoto(usuarioVehiculoMoto), repositorioRoomMoto)

        servicioEstacionamiento.eliminarUsuario()
    }
}