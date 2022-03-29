package com.estacionamiento.modelo

import com.estacionamiento.servicio.ServicioEstacionamiento
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculoCarro
import com.usuario.modelo.UsuarioVehiculoMoto

class EstacionamientoCarro(
    usuarioVehiculoCarro: UsuarioVehiculoCarro,
    val servicioEstacionamiento: ServicioEstacionamiento,
) : Estacionamiento(usuarioVehiculoCarro) {

    override val capacidadEstacionamiento = 10

    override fun consultarCapacidad(): Boolean {
        val listaMotos: ArrayList<UsuarioVehiculo> =
            servicioEstacionamiento.consultarListaUsuarios()
        return listaMotos.filterIsInstance<UsuarioVehiculoMoto>().size < capacidadEstacionamiento
    }
}