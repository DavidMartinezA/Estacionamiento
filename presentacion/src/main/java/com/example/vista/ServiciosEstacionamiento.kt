package com.example.vista

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.presentacion.R
import com.example.presentacion.databinding.ActivityCobroEstacionamientoBinding
import com.example.viewmodel.ServiosEstacionamientoViewModel
import com.example.vista.MainActivity.Companion.PLACA_VEHICULO
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ServiciosEstacionamiento : AppCompatActivity() {

    private val viewModel: ServiosEstacionamientoViewModel by viewModels()
    private lateinit var binding: ActivityCobroEstacionamientoBinding
    private lateinit var placaVehiculo: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cobro_estacionamiento)
        binding = ActivityCobroEstacionamientoBinding.inflate(layoutInflater)
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