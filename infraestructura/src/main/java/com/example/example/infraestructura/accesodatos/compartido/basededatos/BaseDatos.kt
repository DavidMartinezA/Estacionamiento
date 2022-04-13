package com.example.example.infraestructura.accesodatos.compartido.basededatos

import android.content.Context
import androidx.room.Room

class BaseDatos(private val contexto: Context) {

    fun obtenerInstancia(): BaseDatosUsuarioVehiculo {

        return Room.databaseBuilder(
            contexto, BaseDatosUsuarioVehiculo::class.java,
            "baseDatos")
            .build()
    }
}