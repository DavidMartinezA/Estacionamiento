package com.example.inyecciondependecias

import android.content.Context
import androidx.room.Room
import com.example.example.infraestructura.accesodatos.compartido.basededatos.BaseDatosUsuarioVehiculo
import com.example.example.infraestructura.accesodatos.usuario.repositorio.RepositorioUsuarioVehiculoRoom
import com.example.usuario.repositorio.RepositorioUsuarioVehiculo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module //en este modulo buscara las dependecias
@InstallIn(SingletonComponent::class)//comportamiento de los objetos // reutilizar la instancia (patron de diseño)
class ModuloDependencias {

    @Provides
    @Singleton // reutilizar la misma instancia
    fun proveerBaseDatos(@ApplicationContext contexto: Context): BaseDatosUsuarioVehiculo {//anotacion para pasar el contexto de la aplicacion
        return Room.databaseBuilder(
            contexto, BaseDatosUsuarioVehiculo::class.java,
            "baseDatos")
            .build()
    }

    @Provides
    @Singleton
    fun proveerRepositorio(baseDeDatos: BaseDatosUsuarioVehiculo): RepositorioUsuarioVehiculo {
        return RepositorioUsuarioVehiculoRoom(baseDeDatos)
    }
}
