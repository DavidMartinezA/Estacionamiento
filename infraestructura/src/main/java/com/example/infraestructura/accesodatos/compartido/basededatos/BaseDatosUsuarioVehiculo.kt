package com.example.infraestructura.accesodatos.compartido.basededatos

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.infraestructura.accesodatos.compartido.convertidordatos.ConvertidorDatos
import com.example.infraestructura.accesodatos.usuario.dao.UsuarioVehiculoDaoCarro
import com.example.infraestructura.accesodatos.usuario.dao.UsuarioVehiculoDaoMoto
import com.example.infraestructura.accesodatos.usuario.entidadbasedatos.EntidadDatosUsuarioVehiculoCarro
import com.example.infraestructura.accesodatos.usuario.entidadbasedatos.EntidadDatosUsuarioVehiculoMoto

@Database(entities = [EntidadDatosUsuarioVehiculoCarro::class, EntidadDatosUsuarioVehiculoMoto::class],
    version = 1,
    exportSchema = false)
@TypeConverters(ConvertidorDatos::class)
abstract class BaseDatosUsuarioVehiculo : RoomDatabase() {

    abstract fun usuarioVehiculoCarroDao(): UsuarioVehiculoDaoCarro
    abstract fun usuarioVehiculoMotoDao(): UsuarioVehiculoDaoMoto
}
