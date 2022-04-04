package com.example.infraestructura.accesodatos.convertidortipo

import androidx.room.TypeConverter
import java.time.LocalDateTime

class ConvertidorDeTipoDatos {

    @TypeConverter
    fun fromStringADateTime(value: String): LocalDateTime {
        if (value.isNotEmpty()) {
            return LocalDateTime.parse(value)
        } else throw Exception("Fecha de ingreso no valida")
    }

    @TypeConverter
    fun fromDateTimesAString(dateTime: LocalDateTime): String {
        return dateTime.toString()
    }
}
