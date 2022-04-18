package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.estacionamiento.modelo.EstacionamientoCarro
import com.example.estacionamiento.modelo.EstacionamientoMoto
import com.example.estacionamiento.servicio.ServicioEstacionamiento
import com.example.usuario.modelo.UsuarioVehiculoCarro
import com.example.usuario.modelo.UsuarioVehiculoMoto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class UsuarioVehiculoViewModelPrincipal @Inject constructor(private val servicioEstacionamiento: ServicioEstacionamiento) :
    ViewModel() {

    fun ingresarUsuarioCarro(usuarioIngresado: String) {
        viewModelScope.launch {
            servicioEstacionamiento.ingresoUsuarioEstacionamiento(
                EstacionamientoCarro(UsuarioVehiculoCarro(usuarioIngresado)),
                LocalDateTime.now().dayOfWeek.value)
        }
    }

    fun ingresarUsuarioMoto(usuarioIngresado: String, altoCilindraje: Boolean) {
        viewModelScope.launch {
            servicioEstacionamiento.ingresoUsuarioEstacionamiento(
                EstacionamientoMoto(UsuarioVehiculoMoto(usuarioIngresado, altoCilindraje)),
                LocalDateTime.now().dayOfWeek.value)
        }
    }
}