package com.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dominio.estacionamiento.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

/*

        val usuario = UsuarioVehiculoCarro("hsu542")
        val estacionamiento = EstacionamientoCarro(usuario)
        val repositorio = RepositorioUsuarioVehiculoCarroRoom()
        val prueba = ServicioEstacionamiento(estacionamiento, repositorio)

        runBlocking {
            //prueba.ingresoUsuarioEstacionamiento(3,LocalDateTime.now().minusHours(3))
            //prueba.eliminarUsuario()

        }
*/


    }
}