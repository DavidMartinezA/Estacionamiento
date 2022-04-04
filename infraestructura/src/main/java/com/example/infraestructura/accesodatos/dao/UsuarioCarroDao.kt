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
}
