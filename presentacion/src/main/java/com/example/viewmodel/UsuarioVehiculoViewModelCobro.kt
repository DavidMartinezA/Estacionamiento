package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cobro.modelo.CobroTarifaCarro
import com.example.cobro.modelo.CobroTarifaMoto
import com.example.cobro.servicio.ServicioCobroTarifa
import com.example.estacionamiento.excepcion.UsuarioNoExisteExcepcion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsuarioVehiculoViewModelCobro @Inject constructor(private val servicioCobroTarifa: ServicioCobroTarifa) : ViewModel() {

    private val mutableCobro: MutableStateFlow<Int> = MutableStateFlow(0)
    val cobroVehiculo: StateFlow<Int> = mutableCobro

    fun cobroServicio(placaUsuario: String) { // todo mejorar la funcion
        viewModelScope.launch {
            val usuario = try {
                servicioCobroTarifa.obtenerVehiculoPorPlaca(placaUsuario).tipoDeVehiculo
            } catch (e: UsuarioNoExisteExcepcion) {
                ""
            }
            when (usuario) {
                "Carro" -> {
                    mutableCobro.value = servicioCobroTarifa.cobroDuracionServicio(placaUsuario, CobroTarifaCarro())
                }
                "Moto" -> {
                    mutableCobro.value = servicioCobroTarifa.cobroDuracionServicio(placaUsuario, CobroTarifaMoto())
                }
                else -> {
                    throw UsuarioNoExisteExcepcion()
                }
            }

        }
    }


    fun eliminarUsuario(placaUsuario: String) {
        viewModelScope.launch {
            servicioCobroTarifa.eliminarUsuario(placaUsuario)
        }
    }
}
