package com.example.vista

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.vista.paginas.MainPageObject
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDateTime

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)
    private val pageObject = MainPageObject()

    @Test
    fun ingresoCarroTest_placaValidaUsuarioNoExiste_usuarioGuardado() {
        pageObject
            .ingresarPlacaVehiculo("ccc987")
            .seleccionarRadioButtonCarro()
            .oprimirBotonIngreso()
            .verifiacarDialogoMensaje("Usuario Registrado")
    }

    @Test
    fun ingresoMotoTest_placaValidaUsuarioNoExiste_usuarioGuardado() {
        pageObject
            .ingresarPlacaVehiculo("ccc988")
            .seleccionarRadioButtonMoto()
            .oprimirBotonIngreso()
            .verifiacarDialogoMensaje("Usuario Registrado")
    }

    @Test
    fun ingresoMotoAltoCilindrajeTest_placaValidaUsuarioNoExiste_usuarioGuardado() {
        pageObject
            .ingresarPlacaVehiculo("ccc989")
            .seleccionarRadioButtonMotoCc()
            .oprimirBotonIngreso()
            .verifiacarDialogoMensaje("Usuario Registrado")
    }

    @Test
    fun ingresoCarroTest_placaTextoVacio_mensajeFormatoPlacaNoValido() {
        pageObject
            .ingresarPlacaVehiculo("")
            .seleccionarRadioButtonCarro()
            .oprimirBotonIngreso()
            .verifiacarDialogoMensaje("Formato De Placa No Valido")
    }

    @Test
    fun ingresoMotoTest_placaTextoVacio_mensajeFormatoPlacaNoValido() {
        pageObject
            .ingresarPlacaVehiculo("")
            .seleccionarRadioButtonMoto()
            .oprimirBotonIngreso()
            .verifiacarDialogoMensaje("Formato De Placa No Valido")
    }

    @Test
    fun ingresoMotoAltoCilindrajeTest_placaTextoVacio_mensajeFormatoPlacaNoValido() {
        pageObject
            .ingresarPlacaVehiculo("")
            .seleccionarRadioButtonMotoCc()
            .oprimirBotonIngreso()
            .verifiacarDialogoMensaje("Formato De Placa No Valido")
    }

    @Test
    fun ingresoUsuarioVehiculoTest_tipoVehiculoNoSelecionado_mensajeUsuarioRegistradoComoCarro() {
        pageObject
            .ingresarPlacaVehiculo("ccc990")
            .oprimirBotonIngreso()
            .verifiacarDialogoMensaje("Usuario Registrado")
    }

    @Test
    fun ingresoUsuarioVehiculoTest_placaRestriccionMartesASabado_mensajeNoEstaAutorizadoIngresar() {
        pageObject
            .ingresarPlacaVehiculo("Acc990")
            .seleccionarRadioButtonCarro()
            .oprimirBotonIngreso()
        if (LocalDateTime.now().dayOfWeek.value == 1 || LocalDateTime.now().dayOfWeek.value == 7) {
            pageObject.verifiacarDialogoMensaje("Usuario Registrado")
        } else {
            pageObject.verifiacarDialogoMensaje("No Esta Autorizado A Ingresar")
        }
    }


    @Test
    fun ingresoUsuarioVehiculoTest_placaTextoNull_mensajeFormatoPlacaNoValido() {
        pageObject
            .oprimirBotonIngreso()
            .verifiacarDialogoMensaje("Formato De Placa No Valido")
    }

    @Test
    fun ingresoUsuarioVehiculoTest_usuarioYaRegistrado_mensajeUsuarioYaSeEncuentraRegistrado() {
        pageObject
            .ingresarPlacaVehiculo("uuu777")
            .seleccionarRadioButtonCarro()
            .oprimirBotonIngreso()
            .presionarAceptarDialogoMensaje()
            .oprimirBotonIngreso()
            .verifiacarDialogoMensaje("UsuarioVehiculo Ya Existe")
    }

}
