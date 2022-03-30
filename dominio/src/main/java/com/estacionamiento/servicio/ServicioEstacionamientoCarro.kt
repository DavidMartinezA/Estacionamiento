package com.estacionamiento.servicio

import com.estacionamiento.modelo.Estacionamiento
import com.estacionamiento.repositorio.RepositorioEstacionamiento
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculoMoto

class ServicioEstacionamientoCarro(
    estacionamiento: Estacionamiento,
    repositorioEstacionamiento: RepositorioEstacionamiento,
) : ServicioEstacionamiento(estacionamiento, repositorioEstacionamiento) {


    override fun consultaDisponibilidadEstacionamiento(): Boolean {
        var existeEspacio = false
        val listaMotos: ArrayList<UsuarioVehiculo> =
            repositorioEstacionamiento.listaUsuarios()
        if (listaMotos.filterIsInstance<UsuarioVehiculoMoto>().size < estacionamiento.capacidadEstacionamiento) {
            existeEspacio = true
        }
        return existeEspacio
    }
}