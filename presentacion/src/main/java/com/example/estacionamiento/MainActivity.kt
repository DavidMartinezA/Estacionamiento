package com.example.estacionamiento

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.presentacion.R
import com.example.presentacion.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.botonIngreso.setOnClickListener {
            when (true) {
                binding.radioButtonCarro.isChecked -> {
                    val intento1 = Intent(this, IngresoCarros::class.java)
                    startActivity(intento1)
                }
                binding.radioButtonMoto.isChecked -> {
                    val intento2 = Intent(this, IngresoMotos::class.java)
                    startActivity(intento2)
                }
                else -> {
                    Toast.makeText(this, "Seleccione el Tipo De Vehiculo", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.botonCobroTarifa.setOnClickListener {
            val intento3 = Intent(this, CalculoCobro::class.java)
            startActivity(intento3)

        }
    }
}