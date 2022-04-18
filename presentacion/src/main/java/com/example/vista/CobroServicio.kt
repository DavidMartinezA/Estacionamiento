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
            val textoCobroTarifa = getString(R.string.Texto_Tarifa_Cobrada) + costoServicio
            binding.cobrosServicio.text = textoCobroTarifa
        }

        if (!placaVehiculo.isNullOrEmpty()) {
            try {
                viewModel.cobroServicio(placaVehiculo)
            } catch (e: UsuarioNoExisteExcepcion) {
                AlertDialog.Builder(this)
                    .setTitle(getString(R.string.Titulo_Usuario_No_Existe))
                    .setMessage(getString(R.string.Mensaje_Usuario_No_Existe_Excepcion))
                    .show()
            }
        } else {
            Toast.makeText(this, getString(R.string.Texto_Ingrese_Placa_Vehiculo), Toast.LENGTH_SHORT).show()
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