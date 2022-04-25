package com.example.vista

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.vista.paginas.ServiciosEstacionamientoPageObject
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class ServicioEstacionamientoTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(ActividadPrincipal::class.java)
    private val pageObject = ServiciosEstacionamientoPageObject()

    @Test
    fun generarInformacionCobroTest_usuarioVehiculoCarroExiste_informacionServicioCobro() {

        //Given
        pageObject
            .ingresarPlacaVehiculo("ggg555")
            .seleccionarRadioButtonCarro()
            .oprimirBotonIngreso()
            .presionarAceptarDialogoMensaje()

        //When
        pageObject
            .presionarBotonCobro()

        //Then
        pageObject.verificarTextoCobro("ggg555El Costo Del Servicio Es De=1000")
    }

    @Test
    fun generarInformacionCobroTest_usuarioVehiculoMotoExiste_informacionServicioCobro() {

        //Given
        pageObject
            .ingresarPlacaVehiculo("yyy666")
            .seleccionarRadioButtonMoto()
            .oprimirBotonIngreso()
            .presionarAceptarDialogoMensaje()

        //When
        pageObject
            .presionarBotonCobro()

        //Then
        pageObject.verificarTextoCobro("yyy666El Costo Del Servicio Es De=500")
    }

    @Test
    fun generarInformacionCobroTest_usuarioVehiculoMotoAltoCilindrajeExiste_informacionServicioCobro() {

        //Given
        pageObject
            .ingresarPlacaVehiculo("sYs222")
            .seleccionarRadioButtonMotoCc()
            .oprimirBotonIngreso()
            .presionarAceptarDialogoMensaje()

        //When
        pageObject
            .presionarBotonCobro()

        //Then
        pageObject.verificarTextoCobro("sYs222El Costo Del Servicio Es De=2500")
    }

    @Test
    fun generarInformacionCobroTest_usuarioVehiculoNoExiste_mensajeUsuarioNoSeEncuentraRegistrado() {

        //Given
        pageObject
            .ingresarPlacaVehiculo("fff555")
            .seleccionarRadioButtonCarro()

        //When
        pageObject
            .presionarBotonCobro()

        //Then
        pageObject
            .verifiacarDialogoMensaje("UsuarioVehiculo No Existe")
    }

}