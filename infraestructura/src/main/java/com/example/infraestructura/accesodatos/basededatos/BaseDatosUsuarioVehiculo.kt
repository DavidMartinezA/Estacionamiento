package com.example.infraestructura.accesodatos.basededatos

import androidx.room.Database
import com.example.infraestructura.accesodatos.dao.UsuarioVehiculoDao
import com.example.infraestructura.accesodatos.entidadbasedatos.EntidadDatosUsuarioVehiculo


@Database(entities = [EntidadDatosUsuarioVehiculo::class], version = 1, exportSchema = false)
abstract class BaseDatosUsuarioVehiculo {

    abstract fun usuarioVehiculoDao(): UsuarioVehiculoDao
}