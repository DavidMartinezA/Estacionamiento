package com.example.vista

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.estacionamiento.excepcion.UsuarioNoExisteExcepcion
import com.example.presentacion.R
import com.example.presentacion.databinding.ActivityCobroServicioBinding
import com.example.usuario.excepcion.FormatoPlacaExcepcion
import com.example.viewmodel.UsuarioVehiculoViewModelCobro
import com.example.vista.MainActivity.Companion.PLACA_VEHICULO
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.FlowCollector

@AndroidEntryPoint
class CobroEstacionamiento : AppCompatActivity() {

    private val viewModel: UsuarioVehiculoViewModelCobro by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cobro_servicio)
        val binding = ActivityCobroServicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val placaVehiculo: String? = intent.getStringExtra(PLACA_VEHICULO)

        // homologo del observer en liveData
        lifecycleScope.launchWhenStarted {
            viewModel.cobroVehiculo.collect(FlowCollector { costoServicio ->
                val textoCobroTarifa = getString(R.string.texto_tarifa_cobrada) + costoServicio
                binding.cobrosServicio.text = textoCobroTarifa
            })
        }

        if (!placaVehiculo.isNullOrEmpty()) {

            val dialogoUsuarioNoExiste = AlertDialog.Builder(this)
                .setTitle(getString(R.string.titulo_usuario_no_existe))
                .setMessage(getString(R.string.Mensaje_Usuario_No_Existe_Excepcion))

            try {
                viewModel.cobroServicio(placaVehiculo)
            } catch (e: UsuarioNoExisteExcepcion) {
                dialogoUsuarioNoExiste.show()
            }
        } else {
            Toast.makeText(this, getString(R.string.texto_ingrese_paca_vehiculo), Toast.LENGTH_SHORT).show()
        }

        binding.botonTarifa.setOnClickListener {

            val dialogoSalida = AlertDialog.Builder(this)
                .setTitle(getString(R.string.titulo_salida_usuario))

            if (!placaVehiculo.isNullOrEmpty()) {
                try {
                    viewModel.eliminarUsuario(placaVehiculo)
                } catch (e: FormatoPlacaExcepcion) {
                    dialogoSalida.show()
                } catch (e: UsuarioNoExisteExcepcion) {
                    dialogoSalida.setMessage(getString(R.string.usuario_no_registrado)).show()
                }
            } else {
                dialogoSalida.setMessage(getString(R.string.texto_ingreso_placa_vehiculo)).show()
                finish()
            }

        }

    }
}