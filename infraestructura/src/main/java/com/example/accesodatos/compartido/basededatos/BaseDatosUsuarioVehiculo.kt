package com.example.accesodatos.compartido.basededatos

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.accesodatos.compartido.convertidordatos.ConvertidorDatos
import com.example.accesodatos.usuario.dao.UsuarioVehiculoDao
import com.example.accesodatos.usuario.entidadbasedatos.EntidadDatosUsuarioVehiculo

@Database(entities = [EntidadDatosUsuarioVehiculo::class], version = 1, exportSchema = false)
@TypeConverters(ConvertidorDatos::class)
abstract class BaseDatosUsuarioVehiculo : RoomDatabase() {

    abstract fun usuarioVehiculoDao(): UsuarioVehiculoDao
}
