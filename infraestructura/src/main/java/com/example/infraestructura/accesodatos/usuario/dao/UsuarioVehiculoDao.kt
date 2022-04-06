package com.example.infraestructura.accesodatos.usuario.dao

import androidx.room.*
import com.example.infraestructura.accesodatos.usuario.entidadbasedatos.EntidadDatosUsuarioEstacionamiento

@Dao
interface UsuarioVehiculoDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarUsuarioVehiculo(entidadDatosUsuarioCarro: EntidadDatosUsuarioEstacionamiento)

    @Delete
    suspend fun borrarUsuarioVehiculo(entidadDatosUsuarioCarro: EntidadDatosUsuarioEstacionamiento)

    @Query("SELECT * FROM EntidadDatosUsuarioEstacionamiento")
    suspend fun listaUsuariosVehiculo(): List<EntidadDatosUsuarioEstacionamiento>

    @Query("SELECT EXISTS (SELECT * FROM EntidadDatosUsuarioEstacionamiento WHERE placaVehiculo == :placaVehiculo)")
    suspend fun usuarioExiste(placaVehiculo: String): Boolean
}
