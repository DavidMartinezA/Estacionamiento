package com.example.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.presentacion.R
import com.example.presentacion.databinding.ActivityCobroServicioBinding
import com.example.presentador.UsuarioVehiculoCarropresentador
import com.example.presentador.UsuarioVehiculoMotopresentador
import com.example.view.MainActivity.Companion.ERROR_USUARIO_REGISTRADO
import com.example.view.MainActivity.Companion.INGRESE_PLACA_VEHICULO
import com.example.view.MainActivity.Companion.PLACA_VEHICULO
import com.example.view.MainActivity.Companion.TIPO_CARRO
import com.example.view.MainActivity.Companion.TIPO_MOTO
import com.example.view.MainActivity.Companion.TIPO_MOTO_CC
import com.example.view.MainActivity.Companion.TIPO_VEHICULO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CobroServicio : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cobro_servicio)
        val binding = ActivityCobroServicioBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val placaVehiculo = intent.getStringExtra(PLACA_VEHICULO)
        val tipoVehiculo = intent.getStringExtra(TIPO_VEHICULO)

        val carroPresentador = UsuarioVehiculoCarropresentador(this)
        val motoPresentador = UsuarioVehiculoMotopresentador(this)
        var costoServicio = 0

        if (!placaVehiculo.isNullOrEmpty() && !tipoVehiculo.isNullOrEmpty()) {
            when (tipoVehiculo) {
                TIPO_CARRO -> {

                    CoroutineScope(Dispatchers.IO).launch {
                        costoServicio = carroPresentador.getCobroServicio(PLACA_VEHICULO)
                    }
                }
                TIPO_MOTO -> {

                    CoroutineScope(Dispatchers.IO).launch {
                        costoServicio = motoPresentador.getCobroServicio(PLACA_VEHICULO, false)
                    }
                }
                TIPO_MOTO_CC -> {

                    CoroutineScope(Dispatchers.IO).launch {
                        costoServicio = motoPresentador.getCobroServicio(PLACA_VEHICULO, true)
                    }
                }
                else -> {
                    Toast.makeText(baseContext, ERROR_USUARIO_REGISTRADO, Toast.LENGTH_SHORT).show()
                }
            }

        } else {
            Toast.makeText(baseContext, INGRESE_PLACA_VEHICULO, Toast.LENGTH_SHORT).show()
        }


        binding.botonTarifa.setOnClickListener {

            CoroutineScope(Dispatchers.Main).launch {
                carroPresentador.setEliminarUsuario(PLACA_VEHICULO)
            }
            Toast.makeText(this, "pago realizado satisfactoriamente", Toast.LENGTH_SHORT).show()
        }



        binding.cobrosServicio.text = "Vehiculo :" + placaVehiculo + "\n cancela = " + costoServicio

    }

}