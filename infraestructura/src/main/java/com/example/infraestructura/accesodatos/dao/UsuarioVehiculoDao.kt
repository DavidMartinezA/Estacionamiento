package com.example.infraestructura.accesodatos.dao

import androidx.room.*
import com.example.infraestructura.accesodatos.entidadbasedatos.EntidadDatosUsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculo

@Dao
interface UsuarioVehiculoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarUsuarioVehiculo(entidadDatosUsuarioVehiculo: EntidadDatosUsuarioVehiculo)

    @Delete
    suspend fun borrarUsuarioVehiculo(entidadDatosUsuarioVehiculo: EntidadDatosUsuarioVehiculo)

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM EntidadDatosUsuarioVehiculo")
    suspend fun listaUsuariosVehiculo(): List<UsuarioVehiculo>

}
