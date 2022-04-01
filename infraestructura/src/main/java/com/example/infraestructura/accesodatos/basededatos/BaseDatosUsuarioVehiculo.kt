package com.example.infraestructura.accesodatos.basededatos

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.infraestructura.accesodatos.convertidortipo.ConvertidorDeTipoDatos
import com.example.infraestructura.accesodatos.dao.UsuarioVehiculoDao
import com.example.infraestructura.accesodatos.entidadbasedatos.EntidadDatosUsuarioVehiculo


@Database(entities = [EntidadDatosUsuarioVehiculo::class], version = 1, exportSchema = false)
@TypeConverters(ConvertidorDeTipoDatos::class)
abstract class BaseDatosUsuarioVehiculo : RoomDatabase() {

    abstract fun usuarioVehiculoDao(): UsuarioVehiculoDao
}