package com.example.infraestructura.accesodatos.basededatos

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.infraestructura.accesodatos.convertidortipo.ConvertidorDeTipoDatos
import com.example.infraestructura.accesodatos.dao.UsuarioCarroDao
import com.example.infraestructura.accesodatos.dao.UsuarioMotoDao
import com.example.infraestructura.accesodatos.entidadbasedatos.EntidadDatosUsuarioCarro
import com.example.infraestructura.accesodatos.entidadbasedatos.EntidadDatosUsuarioMoto

@Database(entities = [EntidadDatosUsuarioMoto::class, EntidadDatosUsuarioCarro::class], version = 1, exportSchema = false)
@TypeConverters(ConvertidorDeTipoDatos::class)
abstract class BaseDatosUsuarioVehiculo : RoomDatabase() {

    abstract fun usuarioMotoDao(): UsuarioMotoDao
    abstract fun usuarioCarroDao(): UsuarioCarroDao
}