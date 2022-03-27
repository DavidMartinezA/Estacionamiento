package com.dominio.estacionamiento.modelo

import com.dominio.usuario.modelo.Usuario

abstract class Estacionamiento (){

    companion object{

        const val RESTRICCION_INGRESO_LETRA_INICIAL_PLACA = 'A'

    }
    protected abstract val capacidaDelParqueadero :Int
    private val DIAS_PERMITIDOS = arrayListOf(7, 1)


    fun restriccionDeIngreso(usuario: Usuario,diaDeLaSemana: Int):Boolean{


        var restringido = false
        if (usuario.placaVehiculo.uppercase().first() == RESTRICCION_INGRESO_LETRA_INICIAL_PLACA) {
            restringido = !DIAS_PERMITIDOS.contains(diaDeLaSemana)
        }
        return restringido
    }

}