package com.example

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.cobro.modelo.CobroTarifaMoto
import com.cobro.servicio.ServicioCobroTarifa
import com.estacionamiento.modelo.EstacionamientoMoto
import com.estacionamiento.servicio.ServicioEstacionamiento
import com.example.infraestructura.accesodatos.compartido.basededatos.BaseDatosUsuarioVehiculo
import com.example.infraestructura.accesodatos.usuario.repositorio.RepositorioUsuarioVehiculoImplRoom
import com.usuario.modelo.UsuarioVehiculoMoto
import dominio.estacionamiento.R
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}