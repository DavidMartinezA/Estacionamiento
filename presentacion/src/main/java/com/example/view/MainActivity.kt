package com.example.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.estacionamiento.excepcion.IngresoNoPermitidoRestriccionExcepcion
import com.example.estacionamiento.excepcion.UsuarioYaExisteExcepcion
import com.example.presentacion.R
import com.example.presentacion.databinding.ActivityMainBinding
import com.example.presentador.UsuarioVehiculoCarropresentador
import com.example.presentador.UsuarioVehiculoMotopresentador
import com.example.usuario.excepcion.FormatoPlacaExcepcion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    companion object {

        const val USUARIO_REGISTRADO = "Usuario Registrado"
        const val ERROR_USUARIO_REGISTRADO = "Seleccione el Tipo De Vehiculo"
        const val INGRESE_PLACA_VEHICULO = "Ingrese La Placa Del Vehiculo"
        const val PLACA_VEHICULO = "Placa Del Vehiculo"
        const val COSTO_SERVICIO = "Costo Servicio"
        const val EXCEPCION_PLACA = "Placa De Vehiculo No Valida"
        const val EXCECION_INGRESO = "No Esta Autorizado Ingresar"
        const val TIPO_VEHICULO = "Tipo De Vehiculo"
        const val TIPO_CARRO = "Carro"
        const val TIPO_MOTO = "Moto"
        const val TIPO_MOTO_CC = "Moto cc"
        const val EXCECION_USARIO_EXISTE = "El Usuario Ya Se Encuentra Registrado"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val carroPresentador = UsuarioVehiculoCarropresentador(this)
        val motoPresentador = UsuarioVehiculoMotopresentador(this)

        binding.botonIngreso.setOnClickListener {
            val textoPlaca = binding.ingresoPlacaVehiculoCalculoCobro.text.toString()
            val dialogoIngreso = AlertDialog.Builder(this)
                .setTitle("Ingreso Automovil")
                .setMessage(USUARIO_REGISTRADO)

            if (textoPlaca.isNotEmpty()) {
                when (true) {
                    binding.radioButtonCarro.isChecked -> {

                        CoroutineScope(Dispatchers.Main).launch {
                            try {
                                carroPresentador.setIngresarUsuario(textoPlaca)
                            } catch (e: FormatoPlacaExcepcion) {
                                dialogoIngreso.setMessage(EXCEPCION_PLACA)
                                    .show()
                            } catch (e: IngresoNoPermitidoRestriccionExcepcion) {
                                dialogoIngreso.setMessage(EXCECION_INGRESO)
                                    .show()
                            } catch (e: UsuarioYaExisteExcepcion) {
                                dialogoIngreso.setMessage(EXCECION_USARIO_EXISTE)
                                    .show()
                            }

                            dialogoIngreso.show()
                            binding.ingresoPlacaVehiculoCalculoCobro.setText("")
                        }
                    }
                    binding.radioButtonMoto.isChecked -> {
                        CoroutineScope(Dispatchers.Main).launch {

                            try {
                                motoPresentador.setIngresarUsuario(textoPlaca, false)
                            } catch (e: FormatoPlacaExcepcion) {
                                dialogoIngreso.setMessage(EXCEPCION_PLACA)
                                    .show()
                            } catch (e: IngresoNoPermitidoRestriccionExcepcion) {
                                dialogoIngreso.setMessage(EXCECION_INGRESO)
                                    .show()
                            } catch (e: UsuarioYaExisteExcepcion) {
                                dialogoIngreso.setMessage(EXCECION_USARIO_EXISTE)
                                    .show()
                            }

                            dialogoIngreso.show()
                            binding.ingresoPlacaVehiculoCalculoCobro.setText("")
                        }
                    }
                    binding.radioButtonMotoCilindraje.isChecked -> {
                        CoroutineScope(Dispatchers.Main).launch {

                            try {
                                motoPresentador.setIngresarUsuario(textoPlaca, true)
                            } catch (e: FormatoPlacaExcepcion) {
                                dialogoIngreso.setMessage(EXCEPCION_PLACA)
                                    .show()
                            } catch (e: IngresoNoPermitidoRestriccionExcepcion) {
                                dialogoIngreso.setMessage(EXCECION_INGRESO)
                                    .show()
                            } catch (e: UsuarioYaExisteExcepcion) {
                                dialogoIngreso.setMessage(EXCECION_USARIO_EXISTE)
                                    .show()
                            }

                            dialogoIngreso.show()
                            binding.ingresoPlacaVehiculoCalculoCobro.setText("")
                        }
                    }
                    else -> {
                        Toast.makeText(this, ERROR_USUARIO_REGISTRADO, Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(this, INGRESE_PLACA_VEHICULO, Toast.LENGTH_LONG).show()
            }

        }

        binding.botonCobroTarifa.setOnClickListener {

            val textoPlaca = binding.ingresoPlacaVehiculoCalculoCobro.text.toString()
            val intento = Intent(this, CobroServicio::class.java)
            intento.putExtra(PLACA_VEHICULO, textoPlaca)

            if (textoPlaca.isNotEmpty()) {
                when (true) {
                    binding.radioButtonCarro.isChecked -> {
                        intento.putExtra(TIPO_VEHICULO, TIPO_CARRO)
                        startActivity(intento)
                    }
                    binding.radioButtonMoto.isChecked -> {

                        intento.putExtra(TIPO_VEHICULO, TIPO_MOTO)
                        startActivity(intento)
                    }
                    binding.radioButtonMotoCilindraje.isChecked -> {

                        intento.putExtra(TIPO_VEHICULO, TIPO_MOTO_CC)
                        startActivity(intento)
                    }
                    else -> {
                        Toast.makeText(this, ERROR_USUARIO_REGISTRADO, Toast.LENGTH_SHORT).show()
                    }
                }

            } else {
                Toast.makeText(this, INGRESE_PLACA_VEHICULO, Toast.LENGTH_SHORT).show()
            }

        }
    }
}