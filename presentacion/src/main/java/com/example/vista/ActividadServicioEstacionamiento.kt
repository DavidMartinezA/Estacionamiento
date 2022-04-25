package com.example.vista

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.presentacion.R
import com.example.presentacion.databinding.ActivityServiciosEstacionamientoBinding
import com.example.viewmodel.ServiciosEstacionamientoViewModel
import com.example.vista.ActividadPrincipal.Companion.PLACA_VEHICULO
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ActividadServicioEstacionamiento : AppCompatActivity() {

    private val viewModel: ServiciosEstacionamientoViewModel by viewModels()
    private lateinit var binding: ActivityServiciosEstacionamientoBinding
    private lateinit var placaVehiculo: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicios_estacionamiento)
        binding = ActivityServiciosEstacionamientoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        placaVehiculo = intent.getStringExtra(PLACA_VEHICULO).toString()
        generarInformacionCobro()
        generarDialogoExcepciones()
        botonTarifa()
        viewModel.cobroServicio(placaVehiculo)
    }

    private fun generarDialogoExcepciones() {
        val dialogoExcepciones = AlertDialog.Builder(this)
        dialogoExcepciones.setTitle(getString(R.string.usuario_no_registrado))
        lifecycleScope.launch {
            viewModel.excepcionCobro.collect { excepcion ->
                if (excepcion.isNotEmpty()) {
                    dialogoExcepciones.setMessage(excepcion)
                        .setPositiveButton(getString(R.string.boton_aceptar)) { dialog, which -> finish() }
                        .show()
                }
            }
        }
    }

    private fun generarInformacionCobro() {
        lifecycleScope.launchWhenStarted {
            viewModel.cobroVehiculo.collect { costoServicio ->
                val textoCobroTarifa = placaVehiculo + getString(R.string.texto_tarifa_cobrada) + costoServicio
                binding.cobrosServicio.text = textoCobroTarifa
            }
        }
    }

    private fun botonTarifa() {
        binding.botonTarifa.setOnClickListener {
            viewModel.salidaUsuarioEstacionamiento(placaVehiculo)
            finish()
        }

    }
}