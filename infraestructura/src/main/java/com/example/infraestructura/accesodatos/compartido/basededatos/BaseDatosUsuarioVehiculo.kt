package com.example.infraestructura.accesodatos.compartido.basededatos

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.infraestructura.accesodatos.compartido.convertidordatos.ConvertidorDatos
import com.example.infraestructura.accesodatos.usuario.dao.UsuarioVehiculoDao
import com.example.infraestructura.accesodatos.usuario.entidadbasedatos.EntidadDatosUsuarioVehiculo


@Database(entities = [EntidadDatosUsuarioVehiculo::class], version = 1, exportSchema = false)
@TypeConverters(ConvertidorDatos::class)
abstract class BaseDatosUsuarioVehiculo : RoomDatabase() {

    abstract fun usuarioVehiculoDao(): UsuarioVehiculoDao
}