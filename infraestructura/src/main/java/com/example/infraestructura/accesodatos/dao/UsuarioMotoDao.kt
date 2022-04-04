package com.example.infraestructura.accesodatos.dao

import androidx.room.*
import com.example.infraestructura.accesodatos.entidadbasedatos.EntidadDatosUsuarioMoto

@Dao
interface UsuarioMotoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarUsuarioVehiculo(entidadDatosUsuarioMoto: EntidadDatosUsuarioMoto)

    @Delete
    suspend fun borrarUsuarioVehiculo(entidadDatosUsuarioMoto: EntidadDatosUsuarioMoto)

    @Query("SELECT * FROM EntidadDatosUsuarioMoto")
    suspend fun listaUsuariosVehiculo(): List<EntidadDatosUsuarioMoto>
}
