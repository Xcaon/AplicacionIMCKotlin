package com.example.aplicacionprimera.imcActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.aplicacionprimera.R
import java.text.DecimalFormat

class ResultadoActivity : AppCompatActivity() {



    private lateinit var tvResultadoFinal: TextView
    private lateinit var tvBienestar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val altura = intent.getStringExtra("Height")
        val peso = intent.getStringExtra("Weight")
        val edad = intent.getStringExtra("Age")

        initComponents()
        calculoIMC(altura!!, peso!! , edad!!)
    }

    private fun calculoIMC(altura : String, peso : String, edad : String) {


        var df = DecimalFormat("#.##")
        var alturaFormateado : Double = altura.toDouble()
        var pesoFormateado : Int = peso.toInt()


        var calculoIMC = pesoFormateado / (alturaFormateado.toDouble() / 100 * alturaFormateado.toDouble() / 100)
        var calculoIMCFormateado = df.format(calculoIMC).toDouble()


        tvResultadoFinal.text = calculoIMCFormateado.toString()



        if ( calculoIMCFormateado < 20 ){
            tvBienestar.text = "Tienes un buen IMC"
        } else if ( calculoIMCFormateado > 20 && calculoIMCFormateado < 30) {
            tvBienestar.text = "Tu IMC esta en la media"
        } else {
            tvBienestar.text = "Tienes sobrepeso"
        }

    }

    private fun initComponents() {
        tvResultadoFinal = findViewById(R.id.tvResultadoFinal)
        tvBienestar = findViewById(R.id.tvBienestar)

    }
}