package com.example

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.presentacion.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonIngreso = findViewById<Button>(R.id.boton_ingreso)
        val botonCobro = findViewById<Button>(R.id.boton_cobro_tarifa)
        val radiobuttontipoMoto = findViewById<RadioButton>(R.id.radio_button_moto)
        val radiobuttontipoCarro = findViewById<RadioButton>(R.id.radio_button_carro)

        botonIngreso.setOnClickListener {
            if (radiobuttontipoCarro.isChecked()) {
                val intento1 = Intent(this, IngresoCarros::class.java)
                startActivity(intento1)
            } else if (radiobuttontipoMoto.isChecked()) {
                val intento2 = Intent(this, IngresoMotos::class.java)
                startActivity(intento2)
            } else {
                Toast.makeText(this, "No Selecciono Tipo De Vehiculo", Toast.LENGTH_SHORT).show()
            }

        }

        botonCobro.setOnClickListener {
            val intento3 = Intent(this, CalculoCobro::class.java)
            startActivity(intento3)

        }
    }
}