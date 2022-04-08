package com.example.infraestructura.accesodatos.compartido.basededatos

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.infraestructura.accesodatos.usuario.anticorrupcion.TraductorUsuarioVehiculo
import com.example.infraestructura.accesodatos.usuario.dao.UsuarioVehiculoDao
import com.usuario.modelo.UsuarioVehiculoCarro
import com.usuario.modelo.UsuarioVehiculoMoto
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
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
    fun insertar_usuarioCarro_listaContieneUsuarioCarro() {

        //Arrange
        val usuarioCarro = UsuarioVehiculoCarro("hsu531")
        val traductor = TraductorUsuarioVehiculo().desdeDominioUnUsuario(usuarioCarro)

        //Act
        runTest {
            usuarioVehiculoDao.insertar(traductor)

            //Assert
            Assert.assertTrue(usuarioVehiculoDao.listaUsuarios().contains(traductor))

        }

    }

    @Test
    @Throws(Exception::class)
    fun insertar_usuarioMoto_listaContieneUsuarioMoto() {

        //Arrange
        val usuarioMoto = UsuarioVehiculoMoto("hsu532", true)
        val traductor = TraductorUsuarioVehiculo().desdeDominioUnUsuario(usuarioMoto)

        //Act
        runTest {
            usuarioVehiculoDao.insertar(traductor)

            //Assert
            Assert.assertTrue(usuarioVehiculoDao.listaUsuarios().contains(traductor))

        }

    }

    @Test
    @Throws(Exception::class)
    fun eliminar_usuarioMoto_listaNoContieneUsuarioMoto() {

        //Arrange
        val usuarioMoto = UsuarioVehiculoMoto("hsu533", true)
        val traductor = TraductorUsuarioVehiculo().desdeDominioUnUsuario(usuarioMoto)

        //Act
        runTest {
            usuarioVehiculoDao.insertar(traductor)
            usuarioVehiculoDao.borrar(traductor)

            //Assert
            Assert.assertFalse(usuarioVehiculoDao.listaUsuarios().contains(traductor))

        }

    }

    @Test
    @Throws(Exception::class)
    fun eliminar_usuarioCarro_listaNoContieneUsuarioCarro() {

        //Arrange
        val usuarioCarro = UsuarioVehiculoMoto("hsu533")
        val traductor = TraductorUsuarioVehiculo().desdeDominioUnUsuario(usuarioCarro)

        //Act
        runTest {
            usuarioVehiculoDao.insertar(traductor)
            usuarioVehiculoDao.borrar(traductor)

            //Assert
            Assert.assertFalse(usuarioVehiculoDao.listaUsuarios().contains(traductor))

        }

    }

    @Test
    @Throws(Exception::class)
    fun comprobacionUsuarioExiste_placaVehiculoExiste_usuarioExiste() {

        //Arrange
        val usuarioCarro = UsuarioVehiculoCarro("hsu534")
        val traductor = TraductorUsuarioVehiculo().desdeDominioUnUsuario(usuarioCarro)

        //Act
        runTest {
            usuarioVehiculoDao.insertar(traductor)
            val respuesta = usuarioVehiculoDao.comprobacionUsuarioExiste("hsu534")

            //Assert
            Assert.assertTrue(respuesta)

        }

    }

    @Test
    @Throws(Exception::class)
    fun comprobacionUsuarioExiste_placaVehiculoNoExiste_usuarioNoExiste() {

        //Arrange
        val usuarioCarro = UsuarioVehiculoCarro("hsu534")
        val traductor = TraductorUsuarioVehiculo().desdeDominioUnUsuario(usuarioCarro)

        //Act
        runTest {
            usuarioVehiculoDao.insertar(traductor)
            val respuesta = usuarioVehiculoDao.comprobacionUsuarioExiste("hsu535")

            //Assert
            Assert.assertFalse(respuesta)

        }

    }

    @Test
    @Throws(Exception::class)
    fun listaUsuarios_consultarListaUsuarios_listaUsuarios() {

        //Arrange
        val usuarioCarro = UsuarioVehiculoCarro("hsu534")
        val traductor = TraductorUsuarioVehiculo().desdeDominioUnUsuario(usuarioCarro)

        //Act
        runTest {
            usuarioVehiculoDao.insertar(traductor)
            val respuesta = usuarioVehiculoDao.listaUsuarios()

            //Assert
            Assert.assertTrue(respuesta.isNotEmpty())

        }

    }

}