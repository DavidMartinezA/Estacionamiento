package com.example.infraestructura.accesodatos.usuario.dao

import androidx.room.*
import com.example.infraestructura.accesodatos.usuario.entidadbasedatos.EntidadDatosUsuarioVehiculoCarro

@Dao
interface UsuarioVehiculoDaoCarro {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarUsuarioVehiculo(entidadDatosUsuarioCarro: EntidadDatosUsuarioVehiculoCarro)

    @Delete
    suspend fun borrarUsuarioVehiculo(entidadDatosUsuarioCarro: EntidadDatosUsuarioVehiculoCarro)

    @Query("SELECT * FROM EntidadDatosUsuarioVehiculoCarro")
    suspend fun listaUsuariosVehiculo(): List<EntidadDatosUsuarioVehiculoCarro>

    @Query("SELECT EXISTS (SELECT * FROM EntidadDatosUsuarioVehiculoCarro WHERE placaVehiculo == :placaVehiculo)")
    suspend fun usuarioExiste(placaVehiculo: String): Boolean
}
