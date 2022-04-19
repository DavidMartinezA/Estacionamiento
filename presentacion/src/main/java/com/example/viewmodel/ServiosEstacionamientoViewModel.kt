package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cobro.modelo.CobroTarifaCarro
import com.example.cobro.modelo.CobroTarifaMoto
import com.example.cobro.servicio.ServicioCobroTarifa
import com.example.estacionamiento.excepcion.UsuarioNoExisteExcepcion
import com.example.usuario.excepcion.FormatoPlacaExcepcion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServiosEstacionamientoViewModel @Inject constructor(private val servicioCobroTarifa: ServicioCobroTarifa) : ViewModel() {

    private val mutableCobro: MutableStateFlow<Int> = MutableStateFlow(0)
    private val mutableExcepcion: MutableStateFlow<String> = MutableStateFlow("")
    var cobroVehiculo: StateFlow<Int> = mutableCobro
    val excepcionCobro: StateFlow<String> = mutableExcepcion

    fun cobroServicio(placaUsuario: String) = viewModelScope.launch {
        try {
            if (servicioCobroTarifa.obtenerVehiculoPorPlaca(placaUsuario).tipoDeVehiculo == "Carro") {
                mutableCobro.value = servicioCobroTarifa.cobroDuracionServicio(placaUsuario, CobroTarifaCarro())
            } else {
                mutableCobro.value = servicioCobroTarifa.cobroDuracionServicio(placaUsuario, CobroTarifaMoto())
            }
        } catch (e: UsuarioNoExisteExcepcion) {
            mutableExcepcion.value = "Usuario No Existe"
        }
    }

    fun eliminarUsuario(placaUsuario: String) = viewModelScope.launch {
        try {
            servicioCobroTarifa.eliminarUsuario(placaUsuario)
        } catch (e: FormatoPlacaExcepcion) {
            mutableExcepcion.value = "Placa Vehiculo No Permitida"
        } catch (e: UsuarioNoExisteExcepcion) {
            mutableExcepcion.value = "Usuario No Existe"
        }

    }
}
