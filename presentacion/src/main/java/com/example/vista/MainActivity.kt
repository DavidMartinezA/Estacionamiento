package com.example.vista

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.presentacion.databinding.ActivityMainBinding
import com.example.viewmodel.IngresoUsuariosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        const val PLACA_VEHICULO = "Placa Del Vehiculo"
    }

    private val viewModel: IngresoUsuariosViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dialogoExcepciones = AlertDialog.Builder(this)
        dialogoExcepciones.setTitle("Estacionamiento")

        lifecycleScope.launchWhenStarted {
            viewModel.ingresoVehiculo.collect { excepcion ->
                if (excepcion.isNotEmpty()) {
                    dialogoExcepciones.setMessage(excepcion)
                        .setPositiveButton("Aceptar", null)
                        .show()
                }
            }
        }

        binding.botonIngreso.setOnClickListener {
            botonIngresarUsuario()
        }
        binding.botonCobroTarifa.setOnClickListener {
            botonCobrarTarifa()
        }
    }

    private fun botonIngresarUsuario() {
        val textoPlaca = binding.ingresoPlacaVehiculoCalculoCobro.text.toString()
        when {
            binding.radioButtonCarro.isChecked -> {
                viewModel.ingresarUsuarioCarro(textoPlaca)
            }
            binding.radioButtonMoto.isChecked -> {
                viewModel.ingresarUsuarioMoto(textoPlaca, false)
            }
            binding.radioButtonMotoCilindraje.isChecked -> {
                viewModel.ingresarUsuarioMoto(textoPlaca, true)
            }
            else -> {
                viewModel.ingresarUsuarioCarro(textoPlaca)
            }
        }
    }

    private fun botonCobrarTarifa() {
        val textoPlaca = binding.ingresoPlacaVehiculoCalculoCobro.text.toString()
        if (textoPlaca.isNotEmpty()) {
            val intento = Intent(this, ServiciosEstacionamiento::class.java)
            intento.putExtra(PLACA_VEHICULO, textoPlaca)
            startActivity(intento)
        }
    }
}

