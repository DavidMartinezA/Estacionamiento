package com.example.example.infraestructura.accesodatos.compartido.basededatos

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.example.infraestructura.accesodatos.usuario.anticorrupcion.TraductorUsuarioVehiculo
import com.example.example.infraestructura.accesodatos.usuario.dao.UsuarioVehiculoDao
import com.example.example.infraestructura.accesodatos.usuario.entidadbasedatos.EntidadDatosUsuarioVehiculo
import com.example.usuario.modelo.UsuarioVehiculoCarro
import com.example.usuario.modelo.UsuarioVehiculoMoto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class BaseDatosUsuarioVehiculoTest {

    private lateinit var usuarioVehiculoDao: UsuarioVehiculoDao
    private lateinit var baseDatosEntidades: BaseDatosUsuarioVehiculo

    @Before
    fun crecionBaseDatos() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        baseDatosEntidades = Room.inMemoryDatabaseBuilder(
            context, BaseDatosUsuarioVehiculo::class.java).build()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        baseDatosEntidades.close()
    }

    @Test
    @Throws(Exception::class)
    fun listaUsuarios_consultarListaUsuariosConInformacion_listaUsuarios() {

        //Arrange
        usuarioVehiculoDao = baseDatosEntidades.usuarioVehiculoDao()
        val usuarioCarro = UsuarioVehiculoCarro("hsu534")
        val entidadBaseDatosUsuarioVehiculo = TraductorUsuarioVehiculo().desdeDominioABaseDatos(usuarioCarro)

        //Act
        runTest {
            usuarioVehiculoDao.insertar(entidadBaseDatosUsuarioVehiculo)
            val respuesta = usuarioVehiculoDao.listaUsuarios()

            //Assert
            Assert.assertTrue(respuesta.isNotEmpty())

        }

    }

    @Test
    @Throws(Exception::class)
    fun listaUsuarios_consultarListaUsuariosVacia_listaUsuariosVacia() {

        //Arrange
        usuarioVehiculoDao = baseDatosEntidades.usuarioVehiculoDao()
        //Act
        runTest {
            val respuesta: List<EntidadDatosUsuarioVehiculo> = usuarioVehiculoDao.listaUsuarios()

            //Assert
            Assert.assertTrue(respuesta.isEmpty())

        }

    }

    @Test
    @Throws(Exception::class)
    fun comprobacionUsuarioExiste_placaVehiculoExisteBaseDatos_usuarioExiste() {

        //Arrange
        usuarioVehiculoDao = baseDatosEntidades.usuarioVehiculoDao()
        val usuarioCarro = UsuarioVehiculoCarro("hsu534")
        val traductor = TraductorUsuarioVehiculo().desdeDominioABaseDatos(usuarioCarro)

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
    fun comprobacionUsuarioExiste_placaVehiculoNoExisteBaseDatos_usuarioNoExiste() {

        //Arrange
        usuarioVehiculoDao = baseDatosEntidades.usuarioVehiculoDao()
        val usuarioCarro = UsuarioVehiculoCarro("hsu534")
        val traductor = TraductorUsuarioVehiculo().desdeDominioABaseDatos(usuarioCarro)

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
    fun insertar_usuarioCarro_listaContieneUsuarioCarro() {

        //Arrange
        usuarioVehiculoDao = baseDatosEntidades.usuarioVehiculoDao()
        val usuarioCarro = UsuarioVehiculoCarro("hsu531")
        val traductor = TraductorUsuarioVehiculo().desdeDominioABaseDatos(usuarioCarro)

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
        usuarioVehiculoDao = baseDatosEntidades.usuarioVehiculoDao()
        val usuarioMoto = UsuarioVehiculoMoto("hsu532", true)
        val traductor = TraductorUsuarioVehiculo().desdeDominioABaseDatos(usuarioMoto)

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
        usuarioVehiculoDao = baseDatosEntidades.usuarioVehiculoDao()
        val usuarioMoto = UsuarioVehiculoMoto("hsu533", true)
        val traductor = TraductorUsuarioVehiculo().desdeDominioABaseDatos(usuarioMoto)

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
        usuarioVehiculoDao = baseDatosEntidades.usuarioVehiculoDao()
        val usuarioCarro = UsuarioVehiculoMoto("hsu533")
        val traductor = TraductorUsuarioVehiculo().desdeDominioABaseDatos(usuarioCarro)

        //Act
        runTest {
            usuarioVehiculoDao.insertar(traductor)
            usuarioVehiculoDao.borrar(traductor)

            //Assert
            Assert.assertFalse(usuarioVehiculoDao.listaUsuarios().contains(traductor))

        }

    }

}