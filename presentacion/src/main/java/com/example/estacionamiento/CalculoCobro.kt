package com.example.estacionamiento

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.presentacion.R

class CalculoCobro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculo_cobro)

        val botonCalculoCobro = findViewById<Button>(R.id.boton_calcular_tarifa)

        botonCalculoCobro.setOnClickListener {

            val placaVehiculoIngresada = findViewById<EditText>(R.id.texto_ingreso_placa_vehiculo_calculo_cobro).text.toString()
            if (placaVehiculoIngresada.isNotEmpty()) {

                val intento = Intent(this, CobroServicio::class.java)
                startActivity(intento)

            } else {
                Toast.makeText(this, "Ingrese un Numero De Placa", Toast.LENGTH_SHORT).show()
            }
        }
    }

}