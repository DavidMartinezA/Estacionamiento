package com.example.serviciosestacionamiento.vista

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.ingresousuarios.vista.ActividadIngresoUsuarios.Companion.PLACA_VEHICULO
import com.example.presentacion.R
import com.example.presentacion.databinding.ActividadServiciosEstacionamientoBinding
import com.example.serviciosestacionamiento.viewmodel.ServiciosEstacionamientoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ActividadServicioEstacionamiento : AppCompatActivity() {

    private val viewModel: ServiciosEstacionamientoViewModel by viewModels()
    private lateinit var binding: ActividadServiciosEstacionamientoBinding
    private lateinit var placaVehiculo: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_servicios_estacionamiento)
        binding = ActividadServiciosEstacionamientoBinding.inflate(layoutInflater)
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
                        .setPositiveButton(R.string.boton_aceptar) { _, _ -> finish() }
                        .show()
                }
            }
        }
    }

    private fun generarInformacionCobro() {
        lifecycleScope.launchWhenStarted {
            viewModel.cobroVehiculo.collect { costoServicio ->
                val textoCobroTarifa = getString(R.string.texto_tarifa_cobrada) + costoServicio
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