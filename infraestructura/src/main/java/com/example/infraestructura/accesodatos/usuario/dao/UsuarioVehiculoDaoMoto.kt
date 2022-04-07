package com.example.infraestructura.accesodatos.usuario.dao

import androidx.room.*
import com.example.infraestructura.accesodatos.usuario.entidadbasedatos.EntidadDatosUsuarioVehiculoMoto

@Dao
interface UsuarioVehiculoDaoMoto {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarUsuarioVehiculo(entidadDatosUsuarioMoto: EntidadDatosUsuarioVehiculoMoto)

    @Delete
    suspend fun borrarUsuarioVehiculo(entidadDatosUsuarioMoto: EntidadDatosUsuarioVehiculoMoto)

    @Query("SELECT * FROM EntidadDatosUsuarioVehiculoMoto")
    suspend fun listaUsuariosVehiculo(): List<EntidadDatosUsuarioVehiculoMoto>

    @Query("SELECT EXISTS (SELECT * FROM EntidadDatosUsuarioVehiculoMoto WHERE placaVehiculo == :placaVehiculo)")
    suspend fun usuarioExiste(placaVehiculo: String): Boolean
}
