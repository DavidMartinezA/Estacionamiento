package com.example.infraestructura.accesodatos.dao

import androidx.room.*
import com.example.infraestructura.accesodatos.entidadbasedatos.EntidadDatosUsuarioCarro

@Dao
interface UsuarioCarroDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarUsuarioVehiculo(entidadDatosUsuarioCarro: EntidadDatosUsuarioCarro)

    @Delete
    suspend fun borrarUsuarioVehiculo(entidadDatosUsuarioCarro: EntidadDatosUsuarioCarro)

    @Query("SELECT * FROM EntidadDatosUsuarioCarro")
    suspend fun listaUsuariosVehiculo(): List<EntidadDatosUsuarioCarro>

    @Query("SELECT EXISTS (SELECT * FROM EntidadDatosUsuarioCarro WHERE placaVehiculo == :placaVehiculo)")
    suspend fun usuarioExiste(placaVehiculo: String): Boolean
}
