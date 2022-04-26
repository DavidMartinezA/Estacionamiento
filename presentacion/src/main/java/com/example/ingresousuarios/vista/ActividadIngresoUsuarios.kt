package com.example.ingresousuarios.vista

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.ingresousuarios.viewmodel.IngresoUsuariosViewModel
import com.example.presentacion.R
import com.example.presentacion.databinding.ActividadIngresoUsuarioBinding
import com.example.serviciosestacionamiento.vista.ActividadServicioEstacionamiento
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActividadIngresoUsuarios : AppCompatActivity() {

    companion object {
        const val PLACA_VEHICULO = "Placa Del Vehiculo"
    }

    private val viewModel: IngresoUsuariosViewModel by viewModels()
    private lateinit var binding: ActividadIngresoUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActividadIngresoUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        generarDialogoExcepciones()
        ingresarUsuario()
        cobrarTarifa()
    }

    private fun generarDialogoExcepciones() {
        val dialogoExcepciones = AlertDialog.Builder(this)
        dialogoExcepciones.setTitle(getString(R.string.app_name))
        lifecycleScope.launchWhenStarted {
            viewModel.ingresoVehiculo.collect { excepcion ->
                if (excepcion.isNotEmpty()) {
                    dialogoExcepciones.setMessage(excepcion)
                        .setPositiveButton(getString(R.string.boton_aceptar), null)
                        .show()
                }
            }
        }
    }

    private fun ingresarUsuario() {
        binding.botonIngreso.setOnClickListener {
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
    }

    private fun cobrarTarifa() {
        binding.botonCobroTarifa.setOnClickListener {
            val textoPlaca = binding.ingresoPlacaVehiculoCalculoCobro.text.toString()
            if (textoPlaca.isNotEmpty()) {
                val intento = Intent(this, ActividadServicioEstacionamiento::class.java)
                intento.putExtra(PLACA_VEHICULO, textoPlaca)
                startActivity(intento)
            }
        }
    }
}

