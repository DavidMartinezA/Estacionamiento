package com.example.infraestructura.accesodatos.usuario.dao

import androidx.room.*
import com.example.infraestructura.accesodatos.usuario.entidadbasedatos.EntidadDatosUsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculo

@Dao
interface UsuarioVehiculoDao {

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insertarUsuarioVehiculo(entidadDatosUsuario: EntidadDatosUsuarioVehiculo)

        @Delete
        suspend fun borrarUsuarioVehiculo(entidadDatosUsuario: EntidadDatosUsuarioVehiculo)

        @RewriteQueriesToDropUnusedColumns
        @Query("SELECT * FROM EntidadDatosUsuarioVehiculo WHERE tipoDeVehiculo == :tipoDeVehiculo ")
        suspend fun listaUsuariosVehiculoMotos(tipoDeVehiculo: String = "Moto"): List<UsuarioVehiculo>

        @RewriteQueriesToDropUnusedColumns
        @Query("SELECT * FROM EntidadDatosUsuarioVehiculo WHERE tipoDeVehiculo == :tipoDeVehiculo ")
        suspend fun listaUsuariosVehiculoCarros(tipoDeVehiculo: String = "Carro"): List<UsuarioVehiculo>

        @RewriteQueriesToDropUnusedColumns
        @Query("SELECT * FROM EntidadDatosUsuarioVehiculo")
        suspend fun listaUsuarios(): List<UsuarioVehiculo>

        @RewriteQueriesToDropUnusedColumns
        @Query("SELECT EXISTS (SELECT * FROM EntidadDatosUsuarioVehiculo WHERE placaVehiculo == :placaVehiculo)")
        suspend fun usuarioExiste(placaVehiculo: String): Boolean
}
