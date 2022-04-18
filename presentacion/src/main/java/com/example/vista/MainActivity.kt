package com.example.vista

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.estacionamiento.excepcion.IngresoNoPermitidoRestriccionExcepcion
import com.example.estacionamiento.excepcion.UsuarioYaExisteExcepcion
import com.example.presentacion.databinding.ActivityMainBinding
import com.example.usuario.excepcion.FormatoPlacaExcepcion
import com.example.viewmodel.UsuarioVehiculoViewModelPrincipal
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        const val USUARIO_REGISTRADO = "Usuario Registrado"
        const val ERROR_USUARIO_REGISTRADO = "Seleccione el Tipo De Vehiculo"
        const val INGRESE_PLACA_VEHICULO = "Ingrese La Placa Del Vehiculo"
        const val PLACA_VEHICULO = "Placa Del Vehiculo"
        const val EXCEPCION_PLACA = "Placa De Vehiculo No Valida"
        const val EXCECION_INGRESO = "No Esta Autorizado Ingresar"
        const val EXCECION_USARIO_EXISTE = "El Usuario Ya Se Encuentra Registrado"
    }

    private val viewModel: UsuarioVehiculoViewModelPrincipal by viewModels()


    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //todo refactorizar codigo duplicado
        binding.botonIngreso.setOnClickListener {

            val dialogoIngreso = AlertDialog.Builder(this)
                .setTitle("Ingreso Automovil")
                .setMessage(USUARIO_REGISTRADO)

            val textoPlaca = binding.ingresoPlacaVehiculoCalculoCobro.text.toString()
            if (textoPlaca.isNotEmpty()) {
                when {
                    binding.radioButtonCarro.isChecked -> {
                        try {
                            viewModel.ingresarUsuarioCarro(textoPlaca)
                        } catch (e: FormatoPlacaExcepcion) {
                            dialogoIngreso.setMessage(EXCEPCION_PLACA)
                        } catch (e: IngresoNoPermitidoRestriccionExcepcion) {
                            dialogoIngreso.setMessage(EXCECION_INGRESO)
                        } catch (e: UsuarioYaExisteExcepcion) {
                            dialogoIngreso.setMessage(EXCECION_USARIO_EXISTE)
                        }
                        dialogoIngreso.show()
                    }

                    binding.radioButtonMoto.isChecked -> {
                        try {
                            viewModel.ingresarUsuarioMoto(textoPlaca, false)
                        } catch (e: FormatoPlacaExcepcion) {
                            dialogoIngreso.setMessage(EXCEPCION_PLACA)
                        } catch (e: IngresoNoPermitidoRestriccionExcepcion) {
                            dialogoIngreso.setMessage(EXCECION_INGRESO)
                        } catch (e: UsuarioYaExisteExcepcion) {
                            dialogoIngreso.setMessage(EXCECION_USARIO_EXISTE)
                        }
                    }

                    binding.radioButtonMotoCilindraje.isChecked -> {
                        try {
                            viewModel.ingresarUsuarioMoto(textoPlaca, true)
                        } catch (e: FormatoPlacaExcepcion) {
                            dialogoIngreso.setMessage(EXCEPCION_PLACA)
                        } catch (e: IngresoNoPermitidoRestriccionExcepcion) {
                            dialogoIngreso.setMessage(EXCECION_INGRESO)
                        } catch (e: UsuarioYaExisteExcepcion) {
                            dialogoIngreso.setMessage(EXCECION_USARIO_EXISTE)
                        }
                    }
                    else -> {
                        dialogoIngreso.setMessage(ERROR_USUARIO_REGISTRADO)
                    }
                }

                binding.ingresoPlacaVehiculoCalculoCobro.setText("")
            } else {
                dialogoIngreso.setMessage(INGRESE_PLACA_VEHICULO)
            }

            dialogoIngreso.show()
        }

        binding.botonCobroTarifa.setOnClickListener {

            val textoPlaca = binding.ingresoPlacaVehiculoCalculoCobro.text.toString()

            if (textoPlaca.isNotEmpty()) {
                val intento = Intent(this, CobroServicio::class.java)
                intento.putExtra(PLACA_VEHICULO, textoPlaca)
                startActivity(intento)
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Cobro Automovil")
                    .setMessage(INGRESE_PLACA_VEHICULO)
                    .show()
            }
        }
    }
}