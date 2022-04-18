package com.example.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cobro.modelo.CobroTarifaCarro
import com.example.cobro.modelo.CobroTarifaMoto
import com.example.cobro.servicio.ServicioCobroTarifa
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsuarioVehiculoViewModelCobro @Inject constructor(private val servicioCobroTarifa: ServicioCobroTarifa) : ViewModel() {

    private val mutableCobro: MutableLiveData<Int> = MutableLiveData(0) // todo migrar a Flow
    val cobroVehiculo: LiveData<Int> = mutableCobro

    fun cobroServicio(placaUsuario: String) = viewModelScope.launch {
        if (servicioCobroTarifa.obtenerVehiculoPorPlaca(placaUsuario).tipoDeVehiculo == "Carro") {
            mutableCobro.value = servicioCobroTarifa.cobroDuracionServicio(placaUsuario, CobroTarifaCarro())
        } else {
            mutableCobro.value = servicioCobroTarifa.cobroDuracionServicio(placaUsuario, CobroTarifaMoto())
        }
    }

    fun eliminarUsuario(placaUsuario: String) {
        viewModelScope.launch {
            servicioCobroTarifa.eliminarUsuario(placaUsuario)
        }
    }
}
