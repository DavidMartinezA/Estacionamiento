package com.example.example.infraestructura.accesodatos.usuario.dao

import androidx.room.*
import com.example.example.infraestructura.accesodatos.usuario.entidadbasedatos.EntidadDatosUsuarioVehiculo

@Dao
interface UsuarioVehiculoDao {

    @Query("SELECT * FROM EntidadDatosUsuarioVehiculo")
    suspend fun listaUsuarios(): List<EntidadDatosUsuarioVehiculo>

    @Query("SELECT * FROM EntidadDatosUsuarioVehiculo WHERE placaVehiculo == :placaVehiculo")
    suspend fun usuarioPorPlaca(placaVehiculo: String): EntidadDatosUsuarioVehiculo

    @Query("SELECT EXISTS (SELECT * FROM EntidadDatosUsuarioVehiculo WHERE placaVehiculo == :placaVehiculo)")
    suspend fun comprobacionUsuarioExiste(placaVehiculo: String): Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertar(entidadDatosUsuario: EntidadDatosUsuarioVehiculo)

    @Delete
    suspend fun borrar(entidadDatosUsuario: EntidadDatosUsuarioVehiculo)

}
