package com.robe.corrutinas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    lateinit var button: Button
    lateinit var button2: Button
    lateinit var txt : String
    val valor  = MutableLiveData<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        incializar()

        valor.observe(this, {
            dato -> Toast.makeText(this, dato,Toast.LENGTH_LONG).show()
        })
        button.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO) {
                corrutina()
            }
        }
        button2.setOnClickListener {
            mensaje(R.string.mensaje2)
        }

    }

    suspend fun corrutina() {
        for (number in 1..20) {
            delay(100)
            println(" ☺"+number)
        }
        valor.postValue("valor para despues de corrutina")
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
