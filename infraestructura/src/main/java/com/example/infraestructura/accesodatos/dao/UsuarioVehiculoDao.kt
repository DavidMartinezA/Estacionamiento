package com.example.infraestructura.accesodatos.dao

import androidx.room.*
import com.example.infraestructura.accesodatos.entidadbasedatos.EntidadDatosUsuarioVehiculo

@Dao
interface UsuarioVehiculoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertVehiculoCarro(carro: EntidadDatosUsuarioVehiculo)

    @Delete
    suspend fun deleteVehiculoCarro(carro: EntidadDatosUsuarioVehiculo)

    @Query("SELECT * FROM EntidadDatosUsuarioVehiculo")
    suspend fun getAllCarros(): List<EntidadDatosUsuarioVehiculo>

}