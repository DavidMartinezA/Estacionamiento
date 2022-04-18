package com.example.usuario.repositorio

import com.example.usuario.modelo.UsuarioVehiculo

interface RepositorioUsuarioVehiculo {

    suspend fun usuarioPorPlaca(placa: String): UsuarioVehiculo

    suspend fun usuarioExiste(placa: String): Boolean

    suspend fun guardarUsuario(usuarioVehiculo: UsuarioVehiculo)

    suspend fun eliminarUsuario(placa: String)

    suspend fun listaUsuarios(): List<UsuarioVehiculo>

}