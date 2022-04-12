package com.example.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.presentacion.R
import com.example.presentacion.databinding.ActivityMainBinding
import com.example.presentador.UsuarioVehiculoCarropresentador
import com.example.presentador.UsuarioVehiculoMotopresentador

class MainActivity : AppCompatActivity() {

    companion object {

        const val USUARIO_REGISTRADO = "Usuario Registrado"
        const val ERROR_USUARIO_REGISTRADO = "Seleccione el Tipo De Vehiculo"
        const val INGRESE_PLACA_VEHICULO = "Ingrese La Placa Del Vehiculo"
        const val PLACA_VEHICULO = "Placa Del Vehiculo"
        const val ALTO_CILINDRAJE = "Alto Cilindraje"
        const val TIPO_VEHICULO = "Tipo Vehiculo"
        const val CARRO = "Carro"
        const val MOTO = "Moto"
        const val MOTO_CC = "Moto cc"


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
                        carroPresentador.setIngresarUsuario(textoPlaca)
                        dialogoIngreso.show()
                        binding.ingresoPlacaVehiculoCalculoCobro.setText("")
                    }
                    binding.radioButtonMoto.isChecked -> {
                        motoPresentador.setIngresarUsuario(textoPlaca, false)
                        dialogoIngreso.show()
                        binding.ingresoPlacaVehiculoCalculoCobro.setText("")

                    }
                    binding.radioButtonMotoCilindraje.isChecked -> {
                        motoPresentador.setIngresarUsuario(textoPlaca, true)
                        dialogoIngreso.show()
                        binding.ingresoPlacaVehiculoCalculoCobro.setText("")

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

            if (textoPlaca.isNotEmpty()) {
                when (true) {
                    binding.radioButtonCarro.isChecked -> {
                        val intento = Intent(this, CobroServicio::class.java)
                        intento.putExtra(PLACA_VEHICULO, textoPlaca)
                        intento.putExtra(TIPO_VEHICULO, CARRO)
                        startActivity(intento)
                    }
                    binding.radioButtonMoto.isChecked -> {

                        val intento = Intent(this, CobroServicio::class.java)
                        intento.putExtra(ALTO_CILINDRAJE, false)
                        intento.putExtra(PLACA_VEHICULO, textoPlaca)
                        intento.putExtra(TIPO_VEHICULO, MOTO)

                        startActivity(intento)
                    }
                    binding.radioButtonMotoCilindraje.isChecked -> {

                        val intento = Intent(this, CobroServicio::class.java)
                        intento.putExtra(ALTO_CILINDRAJE, true)
                        intento.putExtra(PLACA_VEHICULO, textoPlaca)
                        intento.putExtra(TIPO_VEHICULO, MOTO_CC)

                        startActivity(intento)
                    }
                    else -> {
                        Toast.makeText(baseContext, ERROR_USUARIO_REGISTRADO, Toast.LENGTH_SHORT).show()
                    }
                }

            } else {
                Toast.makeText(baseContext, INGRESE_PLACA_VEHICULO, Toast.LENGTH_SHORT).show()
            }

        }
    }
}