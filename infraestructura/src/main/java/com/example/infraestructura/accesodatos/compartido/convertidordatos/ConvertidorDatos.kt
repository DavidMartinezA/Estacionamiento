package com.example.infraestructura.accesodatos.compartido.convertidordatos

import androidx.room.TypeConverter
import java.time.LocalDateTime

class ConvertidorDatos {

    @TypeConverter
    fun deTextoAFecha(value: String): LocalDateTime {
        if (value.isNotEmpty()) {
            return LocalDateTime.parse(value)
        } else throw Exception("Fecha de ingreso no valida")
    }

    @TypeConverter
    fun deFechaATexto(dateTime: LocalDateTime): String {
        return dateTime.toString()
    }
}