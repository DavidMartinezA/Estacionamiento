package com.estacionamiento.servicio

import com.estacionamiento.modelo.Estacionamiento
import com.estacionamiento.repositorio.RepositorioEstacionamiento
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculoCarro

class ServicioEstacionamientoMoto(
    estacionamiento: Estacionamiento,
    repositorioEstacionamiento: RepositorioEstacionamiento,
) : ServicioEstacionamiento(estacionamiento, repositorioEstacionamiento) {


    override fun consultaDisponibilidadEstacionamiento(): Boolean {
        var existeEspacio = false
        val listaCarros: ArrayList<UsuarioVehiculo> =
            repositorioEstacionamiento.listaUsuarios()
        if (listaCarros.filterIsInstance<UsuarioVehiculoCarro>().size < estacionamiento.capacidadEstacionamiento) {
            existeEspacio = true
        }
        return existeEspacio
    }
}

