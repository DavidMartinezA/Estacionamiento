package com.example.infraestructura.accesodatos.usuario.dao

import androidx.room.*
import com.example.infraestructura.accesodatos.usuario.entidadbasedatos.EntidadDatosUsuarioVehiculo

@Dao
interface UsuarioVehiculoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarUsuarioVehiculo(entidadDatosUsuario: EntidadDatosUsuarioVehiculo)

    @Delete
    suspend fun borrarUsuarioVehiculo(entidadDatosUsuario: EntidadDatosUsuarioVehiculo)

    @Query("SELECT * FROM EntidadDatosUsuarioVehiculo")
    suspend fun listaUsuariosVehiculo(): List<EntidadDatosUsuarioVehiculo>

    @Query("SELECT EXISTS (SELECT * FROM EntidadDatosUsuarioVehiculo WHERE placaVehiculo == :placaVehiculo)")
    suspend fun usuarioExiste(placaVehiculo: String): Boolean
}
