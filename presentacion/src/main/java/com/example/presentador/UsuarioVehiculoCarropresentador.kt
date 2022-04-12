package com.example.presentador

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cobro.modelo.CobroTarifaMoto
import com.example.cobro.servicio.ServicioCobroTarifa
import com.example.estacionamiento.modelo.EstacionamientoCarro
import com.example.estacionamiento.servicio.ServicioEstacionamiento
import com.example.example.infraestructura.accesodatos.usuario.repositorio.RepositorioUsuarioVehiculoCarroRoom
import com.example.usuario.modelo.UsuarioVehiculoCarro
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class UsuarioVehiculoCarropresentador(contexto: Context) : ViewModel() {

    private var repositorioRoomCarro = RepositorioUsuarioVehiculoCarroRoom(contexto)

    fun setIngresarUsuario(usuarioIngresado: String) = viewModelScope.launch {

        val usuarioVehiculoCarro = UsuarioVehiculoCarro(usuarioIngresado)
        val servicioEstacionamiento =
            ServicioEstacionamiento(EstacionamientoCarro(usuarioVehiculoCarro), repositorioRoomCarro)

        servicioEstacionamiento.ingresoUsuarioEstacionamiento(LocalDateTime.now().dayOfWeek.value)

    }

    fun getCobroServicio(usuarioIngresado: String): Int {

        val usuarioVehiculoCarro = UsuarioVehiculoCarro(usuarioIngresado)
        val estacionamiento = EstacionamientoCarro(usuarioVehiculoCarro)
        val cobroServicio = CobroTarifaMoto(estacionamiento)
        var tarifa = 0
        val servicioTarifa =
            ServicioCobroTarifa(ServicioEstacionamiento(estacionamiento, repositorioRoomCarro), cobroServicio, LocalDateTime.now())

        viewModelScope.launch {
            tarifa = servicioTarifa.cobroDuracionServicio()
        }
        return tarifa
    }

    fun setEliminarUsuario(usuarioIngresado: String) = viewModelScope.launch {

        val usuarioVehiculoCarro = UsuarioVehiculoCarro(usuarioIngresado)
        val servicioEstacionamiento =
            ServicioEstacionamiento(EstacionamientoCarro(usuarioVehiculoCarro), repositorioRoomCarro)

        servicioEstacionamiento.eliminarUsuario()
    }
}