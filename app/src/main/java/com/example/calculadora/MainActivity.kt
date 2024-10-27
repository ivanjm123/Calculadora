package com.example.calculadora

import android.os.Bundle
import android.view.TextureView
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // 0 -> Nada (por defecto), 1 -> suma, 2 -> resta, 3 -> mult, 4 -> div
    /*
    private lateinit var num2: String
    lateinit solo recomendado para variables tipo objeto como TextView.
    Por eso se me cierra sola -> updateDisplay intenta agregar un valor a num2 sin que esta esté inicializada aun
    */
    private var num2: String = ""
    private var op: Int = 0
    private var numero1: Double = 0.0
    lateinit var tv_num1: TextView
    lateinit var tv_num2: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        tv_num1 = findViewById(R.id.tv_num1)
        tv_num2 = findViewById(R.id.tv_num2)
        val btnBorrarTodo: Button = findViewById(R.id.btn_clear)
        val btnResult: Button = findViewById(R.id.btn_result)

        btnResult.setOnClickListener{
            var numero2: Double = tv_num2.text.toString().toDouble()
            var result: Double = 0.0

            when (op){
                1 -> result = numero1 + numero2
                2 -> result = numero1 - numero2
                3 -> result = numero1 * numero2
                4 -> {
                    if (numero2 != 0.0) result = numero1 / numero2
                    else result = Double.NaN
                }
            }

            tv_num2.setText(result.toString())
            tv_num1.setText("")
            num2 = ""
        }

        btnBorrarTodo.setOnClickListener{
            tv_num1.setText("")
            tv_num2.setText("")
            numero1 = 0.0
            op = 0
        }


    }


    fun updateDisplay(buttonValue: String) {

        num2 += buttonValue
        tv_num2.text = num2
    }


    fun presionarDigito(view: View) {

        when (view.id) {
            R.id.btn_num0 -> updateDisplay("0")
            R.id.btn_num1 -> updateDisplay("1")
            R.id.btn_num2 -> updateDisplay("2")
            R.id.btn_num3 -> updateDisplay("3")
            R.id.btn_num4 -> updateDisplay("4")
            R.id.btn_num5 -> updateDisplay("5")
            R.id.btn_num6 -> updateDisplay("6")
            R.id.btn_num7 -> updateDisplay("7")
            R.id.btn_num8 -> updateDisplay("8")
            R.id.btn_num9 -> updateDisplay("9")
            R.id.btn_decimal -> {
                if (!num2.contains(".")) {
                    updateDisplay(".")
                }
            }
        }
    }


    fun clicOperacion(view: View){
        if (tv_num2.text.isNotEmpty()){
            numero1 = tv_num2.text.toString().toDouble()
            val num2_text: String = tv_num2.text.toString()
            tv_num2.setText("")
            num2 = ""

            when (view.id){
                R.id.btn_suma -> {
                    tv_num1.text = "$num2_text+"
                    // ALT PEOR: tv_num1.setText(num2_text + "+")
                    op = 1
                }
                R.id.btn_resta -> {
                    tv_num1.text = "$num2_text-"
                    op = 2
                }
                R.id.btn_mult -> {
                    tv_num1.text = "$num2_text*"
                    op = 3
                }
                R.id.btn_div -> {
                    tv_num1.text = "$num2_text/"
                    op = 4
                }
                R.id.btn_result -> {

                }
            }
        }

    }


}