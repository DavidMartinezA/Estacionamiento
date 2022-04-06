package com.example.infraestructura.accesodatos.usuario.dao

import androidx.room.*
import com.example.infraestructura.accesodatos.usuario.entidadbasedatos.EntidadDatosUsuarioCarro

@Dao
interface UsuarioVehiculoMotoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarUsuarioVehiculo(entidadDatosUsuarioMoto: EntidadDatosUsuarioCarro)

    @Delete
    suspend fun borrarUsuarioVehiculo(entidadDatosUsuarioMoto: EntidadDatosUsuarioCarro)

    @Query("SELECT * FROM EntidadDatosUsuarioMoto")
    suspend fun listaUsuariosVehiculo(): List<EntidadDatosUsuarioCarro>

    @Query("SELECT EXISTS (SELECT * FROM EntidadDatosUsuarioMoto WHERE placaVehiculo == :placaVehiculo)")
    suspend fun usuarioExiste(placaVehiculo: String): Boolean
}
