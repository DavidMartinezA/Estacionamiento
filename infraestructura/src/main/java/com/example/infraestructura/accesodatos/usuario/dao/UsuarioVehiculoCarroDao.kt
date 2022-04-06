package com.example.infraestructura.accesodatos.usuario.dao

import androidx.room.*
import com.example.infraestructura.accesodatos.usuario.entidadbasedatos.EntidadDatosUsuarioCarro

@Dao
interface UsuarioVehiculoCarroDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarUsuarioVehiculo(entidadDatosUsuarioCarro: EntidadDatosUsuarioCarro)

    @Delete
    suspend fun borrarUsuarioVehiculo(entidadDatosUsuarioCarro: EntidadDatosUsuarioCarro)

    @Query("SELECT * FROM entidadDatosUsuarioCarro")
    suspend fun listaUsuariosVehiculo(): List<EntidadDatosUsuarioCarro>

    @Query("SELECT EXISTS (SELECT * FROM EntidadDatosUsuarioCarro WHERE placaVehiculo == :placaVehiculo)")
    suspend fun usuarioExiste(placaVehiculo: String): Boolean
}
