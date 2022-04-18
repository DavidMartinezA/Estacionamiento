package com.example.vista

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.estacionamiento.excepcion.UsuarioNoExisteExcepcion
import com.example.presentacion.R
import com.example.presentacion.databinding.ActivityCobroServicioBinding
import com.example.usuario.excepcion.FormatoPlacaExcepcion
import com.example.viewmodel.UsuarioVehiculoViewModelCobro
import com.example.vista.MainActivity.Companion.INGRESE_PLACA_VEHICULO
import com.example.vista.MainActivity.Companion.PLACA_VEHICULO
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CobroServicio : AppCompatActivity() {

    private val viewModel: UsuarioVehiculoViewModelCobro by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cobro_servicio)
        val binding = ActivityCobroServicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val placaVehiculo: String? = intent.getStringExtra(PLACA_VEHICULO)

        viewModel.cobroVehiculo.observe(this) { costoServicio ->
            binding.cobrosServicio.text = "cancela = " + costoServicio
        }

        if (!placaVehiculo.isNullOrEmpty()) {
            viewModel.cobroServicio(placaVehiculo) //TODO: Agregar un try/Catch
        } else {
            Toast.makeText(this, INGRESE_PLACA_VEHICULO, Toast.LENGTH_SHORT).show()
        }

        binding.botonTarifa.setOnClickListener {

            val dialogoSalida = AlertDialog.Builder(this)
                .setTitle("Salida Usuario")

            if (!placaVehiculo.isNullOrEmpty()) {
                try {
                    viewModel.eliminarUsuario(placaVehiculo)
                    finish()
                } catch (e: FormatoPlacaExcepcion) {
                    dialogoSalida.show()
                } catch (e: UsuarioNoExisteExcepcion) {
                    dialogoSalida.setMessage("Usuario No Registrado").show()
                }
            } else {
                dialogoSalida.setMessage("llego la placa vacia").show()
            }


        }
    }
}
