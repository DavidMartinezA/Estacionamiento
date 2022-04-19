package com.example.vista

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.presentacion.R
import com.example.presentacion.databinding.ActivityCobroEstacionamientoBinding
import com.example.viewmodel.UsuarioVehiculoViewModelCobro
import com.example.vista.MainActivity.Companion.PLACA_VEHICULO
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CobroEstacionamiento : AppCompatActivity() {

    private val viewModel: UsuarioVehiculoViewModelCobro by viewModels()
    private lateinit var binding: ActivityCobroEstacionamientoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cobro_estacionamiento)
        binding = ActivityCobroEstacionamientoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val placaVehiculo: String = intent.getStringExtra(PLACA_VEHICULO).toString()
        val dialogoExcepciones = AlertDialog.Builder(this)
        dialogoExcepciones.setTitle("Restriccion")
        val intento = Intent(this, MainActivity::class.java)

        lifecycleScope.launchWhenStarted {
            viewModel.cobroVehiculo.collect { costoServicio ->
                val textoCobroTarifa = getString(R.string.texto_tarifa_cobrada) + costoServicio
                binding.cobrosServicio.text = textoCobroTarifa
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.excepcionCobro.collect { excepcion ->
                dialogoExcepciones.setMessage(excepcion)
                    .show()
            }
        }

        viewModel.cobroServicio(placaVehiculo)

        binding.botonTarifa.setOnClickListener {
            botonTarifa(placaVehiculo)
            startActivity(intento)
        }
    }

    fun botonTarifa(placaVehiculo: String) {
        viewModel.eliminarUsuario(placaVehiculo)
    }
}