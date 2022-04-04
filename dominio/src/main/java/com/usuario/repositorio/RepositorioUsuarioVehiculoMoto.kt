package com.usuario.repositorio

import com.usuario.modelo.UsuarioVehiculoMoto

interface RepositorioUsuarioVehiculoMoto {

    fun usuarioExiste(usuarioVehiculo: UsuarioVehiculoMoto): Boolean

    suspend fun guardarUsuario(usuarioVehiculo: UsuarioVehiculoMoto)

    suspend fun eliminarUsuario(usuarioVehiculo: UsuarioVehiculoMoto)

    suspend fun listaUsuarios(): List<UsuarioVehiculoMoto>
}