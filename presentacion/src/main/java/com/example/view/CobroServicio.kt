package com.example.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.presentacion.R
import com.example.presentacion.databinding.ActivityCobroServicioBinding
import com.example.presentador.UsuarioVehiculoCarropresentador
import com.example.presentador.UsuarioVehiculoMotopresentador
import com.example.view.MainActivity.Companion.CARRO
import com.example.view.MainActivity.Companion.MOTO
import com.example.view.MainActivity.Companion.MOTO_CC
import com.example.view.MainActivity.Companion.PLACA_VEHICULO
import com.example.view.MainActivity.Companion.TIPO_VEHICULO


class CobroServicio : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cobro_servicio)
        val binding = ActivityCobroServicioBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val carroPresentador = UsuarioVehiculoCarropresentador(this)
        val motoPresentador = UsuarioVehiculoMotopresentador(this)

        val bundle = intent.extras
        val placaVehiculo = bundle?.getString(PLACA_VEHICULO)
        val tipoVehiculo = bundle?.getString(TIPO_VEHICULO)
        val costoServicio: Int

        when (tipoVehiculo) {
            CARRO -> {
                costoServicio = carroPresentador.getCobroServicio(PLACA_VEHICULO)
                binding.cobrosServicio.text = "Vehiculo : $placaVehiculo \n cancela = $costoServicio "
            }
            MOTO -> {
                costoServicio = motoPresentador.getCobroServicio(PLACA_VEHICULO, false)
                binding.cobrosServicio.text = "Vehiculo : $placaVehiculo \n cancela = $costoServicio "
            }
            MOTO_CC -> {
                costoServicio = motoPresentador.getCobroServicio(PLACA_VEHICULO, true)
                binding.cobrosServicio.text = "Vehiculo : $placaVehiculo \n cancela = $costoServicio "
            }
            else -> {
                Toast.makeText(baseContext, MainActivity.ERROR_USUARIO_REGISTRADO, Toast.LENGTH_SHORT).show()
            }
        }

        binding.botonTarifa.setOnClickListener {
            when (tipoVehiculo) {
                "Carro" -> {
                    carroPresentador.setEliminarUsuario(PLACA_VEHICULO)
                }
                "Moto" -> {
                    motoPresentador.setEliminarUsuario(PLACA_VEHICULO, false)

                }
                "Moto cc" -> {
                    motoPresentador.setEliminarUsuario(PLACA_VEHICULO, true)

                }
                else -> {
                    Toast.makeText(baseContext, MainActivity.ERROR_USUARIO_REGISTRADO, Toast.LENGTH_SHORT).show()
                }
            }

            val intento5 = Intent(this, MainActivity::class.java)
            Toast.makeText(this, "pago realizado satisfactoriamente", Toast.LENGTH_SHORT).show()
            startActivity(intento5)
        }
    }
}