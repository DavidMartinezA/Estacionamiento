package com.example.infraestructura.accesodatos.compartido.basededatos

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.infraestructura.accesodatos.usuario.anticorrupcion.TraductorUsuarioVehiculo
import com.example.infraestructura.accesodatos.usuario.dao.UsuarioVehiculoDao
import com.usuario.modelo.UsuarioVehiculoMoto
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class BaseDatosUsuarioVehiculoTest {

    private lateinit var usuarioVehiculoDao: UsuarioVehiculoDao
    private lateinit var db: BaseDatosUsuarioVehiculo

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, BaseDatosUsuarioVehiculo::class.java).build()
        usuarioVehiculoDao = db.usuarioVehiculoDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertarUsuarioEnLaBaseDeDatos() = runBlocking {
        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu531", true)
        val entidad = TraductorUsuarioVehiculo().desdeDominioUnUsuario(usuario)

        usuarioVehiculoDao.insertarUsuarioVehiculo(entidad)

        val lista = usuarioVehiculoDao.listaUsuariosVehiculo()

        assertTrue(lista.contains(entidad))
    }
}