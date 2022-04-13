package com.example.vista

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.estacionamiento.excepcion.UsuarioNoExisteExcepcion
import com.example.presentacion.R
import com.example.presentacion.databinding.ActivityCobroServicioBinding
import com.example.presentador.UsuarioVehiculoCarropresentador
import com.example.presentador.UsuarioVehiculoMotopresentador
import com.example.usuario.excepcion.FormatoPlacaExcepcion
import com.example.vista.MainActivity.Companion.ERROR_USUARIO_REGISTRADO
import com.example.vista.MainActivity.Companion.INGRESE_PLACA_VEHICULO
import com.example.vista.MainActivity.Companion.PLACA_VEHICULO
import com.example.vista.MainActivity.Companion.TIPO_CARRO
import com.example.vista.MainActivity.Companion.TIPO_MOTO
import com.example.vista.MainActivity.Companion.TIPO_MOTO_CC
import com.example.vista.MainActivity.Companion.TIPO_VEHICULO
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

        val placaVehiculo: String? = intent.getStringExtra(PLACA_VEHICULO)
        val tipoVehiculo: String? = intent.getStringExtra(TIPO_VEHICULO)
        val carroPresentador = UsuarioVehiculoCarropresentador(this)
        val motoPresentador = UsuarioVehiculoMotopresentador(this)
        var costoServicio: Int
        val dialogoSalida = AlertDialog.Builder(this)
            .setTitle("Salida Usuario")
            .setMessage("Pago Satisfactorio")

        if (!placaVehiculo.isNullOrEmpty() && !tipoVehiculo.isNullOrEmpty()) {
            when (tipoVehiculo) {
                TIPO_CARRO -> {

                    CoroutineScope(Dispatchers.IO).launch {
                        costoServicio = carroPresentador.getCobroServicio(placaVehiculo)
                        binding.cobrosServicio.text = TIPO_CARRO + placaVehiculo + "\n cancela = " + costoServicio
                    }
                }
                TIPO_MOTO -> {

                    CoroutineScope(Dispatchers.IO).launch {
                        costoServicio = motoPresentador.getCobroServicio(placaVehiculo, false)
                        binding.cobrosServicio.text = TIPO_MOTO + placaVehiculo + "\n cancela = " + costoServicio
                    }
                }
                TIPO_MOTO_CC -> {

                    CoroutineScope(Dispatchers.IO).launch {
                        costoServicio = motoPresentador.getCobroServicio(placaVehiculo, true)
                        binding.cobrosServicio.text = TIPO_MOTO_CC + placaVehiculo + "\n cancela = " + costoServicio
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

            CoroutineScope(Dispatchers.IO).launch {

                if (placaVehiculo != null) {

                    try {
                        carroPresentador.setEliminarUsuario(placaVehiculo)
                        dialogoSalida.show()
                    } catch (e: FormatoPlacaExcepcion) {
                        dialogoSalida.show()
                    } catch (e: UsuarioNoExisteExcepcion) {
                        dialogoSalida.setMessage("Usuario No Registrado")
                        dialogoSalida.show()
                    }
                } else {
                    dialogoSalida.setMessage("llego la placa vacia")
                }

            }
            val intento = Intent(this, CobroServicio::class.java)
            startActivity(intento)
        }

    }

}