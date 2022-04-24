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
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)
    private val pageObject = ServiciosEstacionamientoPageObject()

    @Test
    fun generarInformacionCobroTest_usuarioVehiculoCarroExiste_informacionServicioCobro() {
        pageObject
            .ingresarPlacaVehiculo("ggg555")
            .seleccionarRadioButtonCarro()
            .oprimirBotonIngreso()
            .presionarAceptarDialogoMensaje()
        pageObject
            .presionarBotonCobro()
            .verificarTextoCobro("ggg555El Costo Del Servicio Es De=1000")
    }

    @Test
    fun generarInformacionCobroTest_usuarioVehiculoMotoExiste_informacionServicioCobro() {
        pageObject
            .ingresarPlacaVehiculo("yyy666")
            .seleccionarRadioButtonMoto()
            .oprimirBotonIngreso()
            .presionarAceptarDialogoMensaje()
        pageObject
            .presionarBotonCobro()
            .verificarTextoCobro("yyy666El Costo Del Servicio Es De=500")
    }

    @Test
    fun generarInformacionCobroTest_usuarioVehiculoMotoAltoCilindrajeExiste_informacionServicioCobro() {
        pageObject
            .ingresarPlacaVehiculo("sYs222")
            .seleccionarRadioButtonMotoCc()
            .oprimirBotonIngreso()
            .presionarAceptarDialogoMensaje()
        pageObject
            .presionarBotonCobro()
            .verificarTextoCobro("sYs222El Costo Del Servicio Es De=2500")
    }

    @Test
    fun generarInformacionCobroTest_usuarioVehiculoNoExiste_mensajeUsuarioNoSeEncuentraRegistrado() {
        pageObject
            .ingresarPlacaVehiculo("fff555")
            .seleccionarRadioButtonCarro()
        pageObject
            .presionarBotonCobro()
            .verifiacarDialogoMensaje("UsuarioVehiculo No Existe")
    }

}
