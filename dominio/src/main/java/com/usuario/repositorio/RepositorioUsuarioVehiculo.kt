package com.usuario.repositorio

import com.usuario.modelo.UsuarioVehiculo

interface RepositorioUsuarioVehiculo {

    fun usuarioExiste(usuarioVehiculo: UsuarioVehiculo): Boolean

    suspend fun guardarUsuario(usuarioVehiculo: UsuarioVehiculo)

    suspend fun eliminarUsuario(usuarioVehiculo: UsuarioVehiculo)

    suspend fun listaUsuarios(): List<UsuarioVehiculo>
}