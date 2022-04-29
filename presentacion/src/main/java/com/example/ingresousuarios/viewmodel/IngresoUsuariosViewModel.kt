package com.example.ingresousuarios.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compartido.excepciones.EstacionamientoExcepcion
import com.example.estacionamiento.modelo.EstacionamientoCarro
import com.example.estacionamiento.modelo.EstacionamientoMoto
import com.example.estacionamiento.servicio.ServicioEstacionamiento
import com.example.usuario.modelo.UsuarioVehiculoCarro
import com.example.usuario.modelo.UsuarioVehiculoMoto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class IngresoUsuariosViewModel @Inject constructor(private val servicioEstacionamiento: ServicioEstacionamiento) : ViewModel() {
    private val excepcionMutable: MutableStateFlow<String> = MutableStateFlow("")
    var ingresoVehiculo: StateFlow<String> = excepcionMutable

    fun ingresarUsuarioCarro(usuarioIngresado: String) = viewModelScope.launch {
        try {
            servicioEstacionamiento.ingresoUsuarioEstacionamiento(
                EstacionamientoCarro(UsuarioVehiculoCarro(usuarioIngresado)), LocalDateTime.now().dayOfWeek.value)
            excepcionMutable.value = "Usuario Registrado"
        } catch (excepcion: EstacionamientoExcepcion) {
            excepcion.message?.let { excepcionMutable.value = excepcion.message!! }
        }
    }

    fun ingresarUsuarioMoto(usuarioIngresado: String, altoCilindraje: Boolean) = viewModelScope.launch {
        try {
            servicioEstacionamiento.ingresoUsuarioEstacionamiento(
                EstacionamientoMoto(UsuarioVehiculoMoto(usuarioIngresado, altoCilindraje)),
                LocalDateTime.now().dayOfWeek.value)
            excepcionMutable.value = "Usuario Registrado"
        } catch (excepcion: EstacionamientoExcepcion) {
            excepcion.message?.let { excepcionMutable.value = excepcion.message!! }
        }
    }
}