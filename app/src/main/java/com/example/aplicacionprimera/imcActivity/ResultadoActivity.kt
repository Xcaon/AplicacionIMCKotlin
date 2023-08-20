package com.example.aplicacionprimera.imcActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.aplicacionprimera.R
import java.text.DecimalFormat

class ResultadoActivity : AppCompatActivity() {



    private lateinit var tvResultadoFinal: TextView

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

        Log.i("fernando", calculoIMC.toString())
        tvResultadoFinal.text = calculoIMCFormateado.toString()
        Log.i("fernandoIMC",altura.toString())
        Log.i("fernando",peso.toString())
        Log.i("fernando",edad.toString())

    }

    private fun initComponents() {
        tvResultadoFinal = findViewById(R.id.tvResultadoFinal)


    }
}