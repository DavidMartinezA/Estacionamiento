package com.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.estacionamiento.modelo.EstacionamientoCarro
import com.estacionamiento.servicio.ServicioEstacionamiento
import com.example.infraestructura.accesodatos.compartido.basededatos.BaseDatosUsuarioVehiculo
import com.example.infraestructura.accesodatos.usuario.repositorio.RepositorioUsuarioVehiculoImplRoom
import com.usuario.modelo.UsuarioVehiculoCarro
import dominio.estacionamiento.R
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            this, BaseDatosUsuarioVehiculo::class.java,
            "baseDatos")
            .build()


        val usuario = UsuarioVehiculoCarro("hsu542")
        val estacionamiento = EstacionamientoCarro(usuario)
        val repositorio = RepositorioUsuarioVehiculoImplRoom(db.usuarioVehiculoDao())
        val prueba = ServicioEstacionamiento(estacionamiento, repositorio)

        runBlocking {
            //prueba.ingresoUsuarioEstacionamiento(3,LocalDateTime.now().minusHours(3))
            //prueba.eliminarUsuario()

        }


    }
}