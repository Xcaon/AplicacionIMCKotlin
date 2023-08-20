package com.example.aplicacionprimera.imcActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.LineHeightSpan
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.aplicacionprimera.R
import com.google.android.material.slider.RangeSlider
import org.w3c.dom.Text
import java.text.DecimalFormat

class ImcActivityCalculator : AppCompatActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView

    private lateinit var tvHeight: TextView
    private lateinit var srHeight: RangeSlider

    private lateinit var tvRestarPeso: TextView
    private lateinit var tvSumarPeso: TextView
    private lateinit var tvContadorPeso: TextView

    private lateinit var tvSumarEdad: TextView
    private lateinit var tvRestarEdad: TextView
    private lateinit var tvContadorEdad: TextView

    private lateinit var tvCalcular: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)
        initComponents()
        initListeners()
        initUI()
        calculate()
    }

    fun calculate(){
        tvCalcular.setOnClickListener {
            val intent = Intent(this, ResultadoActivity::class.java)
            if ( isMaleSelected == true){
                intent.putExtra("Gender", "Male")
            } else {
                intent.putExtra("Gender", "Female")
            }

            val formateadoAltura = tvHeight.text.toString().substring(0,3)
            intent.putExtra("Height", formateadoAltura)

            val peso = tvContadorPeso.text.toString()
            intent.putExtra("Weight", peso)

            val edad = tvContadorEdad.text.toString()
            intent.putExtra("Age", edad)

            startActivity(intent)
        }
    }

    fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        srHeight = findViewById(R.id.rsHeight)
        tvRestarPeso = findViewById(R.id.tvRestarPeso)
        tvSumarPeso = findViewById(R.id.tvSumarPeso)
        tvContadorPeso = findViewById(R.id.tvContadorPeso)
        tvSumarEdad = findViewById(R.id.tvSumarEdad)
        tvRestarEdad = findViewById(R.id.tvRestarEdad)
        tvContadorEdad = findViewById(R.id.tvContadorEdad)
        tvCalcular = findViewById(R.id.tvCalcular)
    }

    fun initListeners() {
        viewMale.setOnClickListener {
            if (isMaleSelected != true) {
                changeGender()
            }
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            if (isFemaleSelected != true) {
                changeGender()
            }
            setGenderColor()
        }

        srHeight.addOnChangeListener { slider, value, fromUser ->
            val df = DecimalFormat("#.##")
            val result = df.format(value)
            tvHeight.text = result + " cm"
        }

        tvRestarPeso.setOnClickListener {
            val contadorActual = tvContadorPeso.text.toString()
            val value = contadorActual.toInt()
            if (value != 0 || value < 0) {
                var contadorActualizado = value - 1
                tvContadorPeso.text = contadorActualizado.toString()
            } else {
                Toast.makeText(this, "El peso no puede ser negativo", Toast.LENGTH_SHORT).show()
            }
        }

        tvSumarPeso.setOnClickListener {
            val contadorActual = tvContadorPeso.text.toString()
            val value = contadorActual.toInt()
            var contadorActualizado = value + 1
            tvContadorPeso.text = contadorActualizado.toString()
        }

        tvRestarEdad.setOnClickListener {
            val contadorActual = tvContadorEdad.text.toString()
            val value = contadorActual.toInt()
            if (value != 0 || value < 0) {
                var contadorActualizado = value - 1
                tvContadorEdad.text = contadorActualizado.toString()
            } else {
                Toast.makeText(this, "La edad no puede ser negativa", Toast.LENGTH_SHORT).show()
            }
        }

        tvSumarEdad.setOnClickListener {
            val contadorActual = tvContadorEdad.text.toString()
            val value = contadorActual.toInt()
            var contadorActualizado = value + 1
            tvContadorEdad.text = contadorActualizado.toString()
        }

    }

    fun changeGender() {

        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    fun getBackgroundColor(isSelectedComponent: Boolean): Int {

        val colorReference = if (isSelectedComponent) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }

        return ContextCompat.getColor(this, colorReference)
    }

    fun initUI() {
        setGenderColor()
    }
}