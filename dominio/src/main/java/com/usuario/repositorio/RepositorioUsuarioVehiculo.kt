package com.usuario.repositorio

import com.usuario.modelo.UsuarioVehiculo

interface RepositorioUsuarioVehiculo {

    suspend fun usuarioExiste(usuarioVehiculo: UsuarioVehiculo): Boolean

    suspend fun guardarUsuario(usuarioVehiculo: UsuarioVehiculo)

    suspend fun eliminarUsuario(usuarioVehiculo: UsuarioVehiculo)

    suspend fun listaUsuarios(): List<UsuarioVehiculo>

    suspend fun listaUsuariosMotos(): List<UsuarioVehiculo>

    suspend fun listaUsuariosCarros(): List<UsuarioVehiculo>

}