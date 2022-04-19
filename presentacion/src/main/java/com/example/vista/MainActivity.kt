package com.example.vista

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.presentacion.databinding.ActivityMainBinding
import com.example.viewmodel.UsuarioVehiculoViewModelPrincipal
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        const val PLACA_VEHICULO = "Placa Del Vehiculo"
    }

    private val viewModel: UsuarioVehiculoViewModelPrincipal by viewModels()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dialogoExcepciones = AlertDialog.Builder(this)
        dialogoExcepciones.setTitle("Ingreso")

        lifecycleScope.launchWhenStarted {
            viewModel.ingresoVehiculo.collect { excepcion ->
                dialogoExcepciones.setMessage(excepcion)
                    .show()
            }
        }

        binding.botonIngreso.setOnClickListener {
            botonIngresarUsuario()
            dialogoExcepciones.setMessage("Usuario Registrado")

        }
        binding.botonCobroTarifa.setOnClickListener {
            botonCobrarTarifa()
        }
    }

    fun botonIngresarUsuario() {
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

    fun botonCobrarTarifa() {
        val textoPlaca = binding.ingresoPlacaVehiculoCalculoCobro.text.toString()
        if (!textoPlaca.isNullOrEmpty()) {
            val intento = Intent(this, CobroEstacionamiento::class.java)
            intento.putExtra(PLACA_VEHICULO, textoPlaca)
            startActivity(intento)
        }

    }
}

