package com.example.compartido.inyecciondependecias

import android.content.Context
import androidx.room.Room
import com.example.accesodatos.compartido.basededatos.BaseDatosUsuarioVehiculo
import com.example.accesodatos.usuario.repositorio.RepositorioUsuarioVehiculoRoom
import com.example.presentacion.R
import com.example.usuario.repositorio.RepositorioUsuarioVehiculo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module //en este modulo buscara las dependecias
@InstallIn(SingletonComponent::class)//comportamiento de los objetos // reutilizar la instancia (patron de diseño)
abstract class ModuloDependencias {

    companion object {

        @Provides
        @Singleton // reutilizar la misma instancia
        fun proveerBaseDatos(@ApplicationContext contexto: Context): BaseDatosUsuarioVehiculo {//anotacion para pasar el contexto de la aplicacion
            return Room.databaseBuilder(
                contexto, BaseDatosUsuarioVehiculo::class.java,
                R.string.nombre_base_datos.toString())
                .build()
        }

    }

    @Binds
    abstract fun proveerRepositorio(repositorio: RepositorioUsuarioVehiculoRoom): RepositorioUsuarioVehiculo
}
