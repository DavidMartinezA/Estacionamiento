package com.example.infraestructura.accesodatos.convertidortipo

import androidx.room.TypeConverter
import java.util.*

class ConvertidorDeTipoDatos {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}