package com.example.vista

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.estacionamiento.excepcion.IngresoNoPermitidoRestriccionExcepcion
import com.example.estacionamiento.excepcion.UsuarioYaExisteExcepcion
import com.example.presentacion.R
import com.example.presentacion.databinding.ActivityMainBinding
import com.example.usuario.excepcion.FormatoPlacaExcepcion
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


        //todo refactorizar codigo duplicado
        binding.botonIngreso.setOnClickListener {

            val dialogoIngreso = AlertDialog.Builder(this)
                .setTitle(getString(R.string.Titulo_Ingreso_Vehiculo))
                .setMessage(getString(R.string.Texto_Usuario_Registrado))

            val textoPlaca = binding.ingresoPlacaVehiculoCalculoCobro.text.toString()
            if (textoPlaca.isNotEmpty()) {
                when {
                    binding.radioButtonCarro.isChecked -> {
                        try {
                            viewModel.ingresarUsuarioCarro(textoPlaca)
                        } catch (e: FormatoPlacaExcepcion) {
                            dialogoIngreso.setMessage(getString(R.string.Texto_Placa_Invalida))
                        } catch (e: IngresoNoPermitidoRestriccionExcepcion) {
                            dialogoIngreso.setMessage(getString(R.string.Texto_No_Autorizado_Ingresar))
                        } catch (e: UsuarioYaExisteExcepcion) {
                            dialogoIngreso.setMessage(getString(R.string.Texto_Usuario_Ya_Registrado))
                        }
                        dialogoIngreso.show()
                    }

                    binding.radioButtonMoto.isChecked -> {
                        try {
                            viewModel.ingresarUsuarioMoto(textoPlaca, false)
                        } catch (e: FormatoPlacaExcepcion) {
                            dialogoIngreso.setMessage(getString(R.string.Texto_Placa_Invalida))
                        } catch (e: IngresoNoPermitidoRestriccionExcepcion) {
                            dialogoIngreso.setMessage(getString(R.string.Texto_No_Autorizado_Ingresar))
                        } catch (e: UsuarioYaExisteExcepcion) {
                            dialogoIngreso.setMessage(getString(R.string.Texto_Usuario_Ya_Registrado))
                        }
                    }

                    binding.radioButtonMotoCilindraje.isChecked -> {
                        try {
                            viewModel.ingresarUsuarioMoto(textoPlaca, true)
                        } catch (e: FormatoPlacaExcepcion) {
                            dialogoIngreso.setMessage(getString(R.string.Texto_Placa_Invalida))
                        } catch (e: IngresoNoPermitidoRestriccionExcepcion) {
                            dialogoIngreso.setMessage(getString(R.string.Texto_No_Autorizado_Ingresar))
                        } catch (e: UsuarioYaExisteExcepcion) {
                            dialogoIngreso.setMessage(getString(R.string.Texto_Usuario_Ya_Registrado))
                        }
                    }
                    else -> {
                        dialogoIngreso.setMessage(getString(R.string.Texto_Seleccione_Vehiculo))
                    }
                }

                binding.ingresoPlacaVehiculoCalculoCobro.setText("")
            } else {
                dialogoIngreso.setMessage(getString(R.string.texto_ingrese_paca_vehiculo))
            }

            dialogoIngreso.show()
        }

        binding.botonCobroTarifa.setOnClickListener {

            val textoPlaca = binding.ingresoPlacaVehiculoCalculoCobro.text.toString()

            if (textoPlaca.isNotEmpty()) {


                val intento = Intent(this, CobroEstacionamiento::class.java)
                intento.putExtra(PLACA_VEHICULO, textoPlaca)
                startActivity(intento)
            } else {
                AlertDialog.Builder(this)
                    .setTitle(getString(R.string.Texto_Titulo_Cobro_Vehiculo))
                    .setMessage(getString(R.string.texto_ingrese_paca_vehiculo))
                    .show()
            }
        }
    }
}