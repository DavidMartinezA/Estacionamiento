package com.example.infraestructura.accesodatos.compartido.basededatos

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.infraestructura.accesodatos.usuario.anticorrupcion.TraductorUsuarioVehiculoCarro
import com.usuario.modelo.UsuarioVehiculoCarro
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class UsuarioVehiculoCarroDaoTest {

    private lateinit var baseDatosUsuarioVehiculo: BaseDatosUsuarioVehiculo

    @Before
    fun initDb() {
        baseDatosUsuarioVehiculo = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
            baseDatosUsuarioVehiculo::class.java).build()
    }

    @After
    fun closeDb() {
        baseDatosUsuarioVehiculo.close()
    }

    @Test
    @Throws(Exception::class)
    fun guardarUsuario_usuarioCarro_usuarioGuardado() {

        val usuario = UsuarioVehiculoCarro("hsu531")
        val usuarioTraducido = TraductorUsuarioVehiculoCarro().desdeDominioUnUsuarioCarro(usuario)

        runBlocking {
            val guardar = baseDatosUsuarioVehiculo.usuarioVehiculoCarroDao().insertarUsuarioVehiculo(usuarioTraducido)

            //verify(baseDatosUsuarioVehiculo.usuarioVehiculoCarroDao(),times(1)).insertarUsuarioVehiculo(usuarioTraducido)
        }


    }

}