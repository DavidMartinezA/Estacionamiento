package com.usuario.repositorio

import com.usuario.modelo.UsuarioVehiculoCarro

interface RepositorioUsuarioVehiculoCarro {

    fun usuarioExiste(usuarioVehiculo: UsuarioVehiculoCarro): Boolean

    suspend fun guardarUsuario(usuarioVehiculo: UsuarioVehiculoCarro)

    suspend fun eliminarUsuario(usuarioVehiculo: UsuarioVehiculoCarro)

    suspend fun listaUsuarios(): List<UsuarioVehiculoCarro>
}