package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.estacionamiento.excepcion.IngresoNoPermitidoRestriccionExcepcion
import com.example.estacionamiento.excepcion.UsuarioYaExisteExcepcion
import com.example.estacionamiento.modelo.EstacionamientoCarro
import com.example.estacionamiento.modelo.EstacionamientoMoto
import com.example.estacionamiento.servicio.ServicioEstacionamiento
import com.example.usuario.excepcion.FormatoPlacaExcepcion
import com.example.usuario.modelo.UsuarioVehiculoCarro
import com.example.usuario.modelo.UsuarioVehiculoMoto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class UsuarioVehiculoViewModelPrincipal @Inject constructor(private val servicioEstacionamiento: ServicioEstacionamiento) :
    ViewModel() {
    private val mutableExcepcion: MutableStateFlow<String> = MutableStateFlow("")
    var ingresoVehiculo: StateFlow<String> = mutableExcepcion

    fun ingresarUsuarioCarro(usuarioIngresado: String) {
        try {
            viewModelScope.launch {
                servicioEstacionamiento.ingresoUsuarioEstacionamiento(
                    EstacionamientoCarro(UsuarioVehiculoCarro(usuarioIngresado)), LocalDateTime.now().dayOfWeek.value)
            }
        } catch (e: FormatoPlacaExcepcion) {
            mutableExcepcion.value = "Placa Usuario No Permitida"
        } catch (e: IngresoNoPermitidoRestriccionExcepcion) {
            mutableExcepcion.value = "Ingreso No Permitido"
        } catch (e: UsuarioYaExisteExcepcion) {
            mutableExcepcion.value = "Usuario Ya Se Encuentra Registrado"
        }
    }

    fun ingresarUsuarioMoto(usuarioIngresado: String, altoCilindraje: Boolean) {
        try {
            viewModelScope.launch {
                servicioEstacionamiento.ingresoUsuarioEstacionamiento(
                    EstacionamientoMoto(UsuarioVehiculoMoto(usuarioIngresado, altoCilindraje)),
                    LocalDateTime.now().dayOfWeek.value)
            }
        } catch (e: FormatoPlacaExcepcion) {
            mutableExcepcion.value = "Placa Usuario No Permitida"
        } catch (e: IngresoNoPermitidoRestriccionExcepcion) {
            mutableExcepcion.value = "Ingreso No Permitido"
        } catch (e: UsuarioYaExisteExcepcion) {
            mutableExcepcion.value = "Usuario Ya Se Encuentra Registrado"
        }
    }
}