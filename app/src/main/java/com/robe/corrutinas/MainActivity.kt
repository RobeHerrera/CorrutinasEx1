package com.robe.corrutinas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var button: Button
    lateinit var button2: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        incializar()
        button.setOnClickListener{
            GlobalScope.launch {
                corrutina()
            }
        }
        button2.setOnClickListener {
            mensaje(R.string.mensaje2)
        }

    }

    suspend fun corrutina() {
        delay(2_000)
        mensaje(R.string.mensaje)
    }

    private fun incializar() {
        button = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
    }

    private fun mensaje(mensaje: Int) {
//            Toast.makeText(applicationContext, R.string.mensaje2,Toast.LENGTH_LONG).show()
        println(getString(mensaje))
    }
}
