package com.robe.corrutinas

import kotlinx.coroutines.*
import java.lang.Thread.sleep
import java.util.*

fun main() {
    /* Ejemplo de Bloqueo */
//    println("Tarea 1 " + Thread.currentThread().name + " -- " + Date().seconds)
//    tareaPesada("Tarea 2")
//    println("Tarea 3 " + Thread.currentThread().name + " -- " + Date().seconds)

    /* Ejemplo de Suspend */
    //otraFuncion()

    /* Ejemplo de dispatcher */
    //dispatchersEjemplos()

    /* Launch */
    funcionLaunch()
}

fun funcionLaunch() {
    println("Tarea 1 " + Thread.currentThread().name + " -- " + Date().seconds)
    GlobalScope.launch {
        corrutina("☻")
    }
    GlobalScope.launch(newSingleThreadContext("Mi Hilito")) {
        corrutina("♥")
    }
    println("Tarea 3 " + Thread.currentThread().name + " -- " + Date().seconds)
    for (number in 1..10) {
        sleep(100)
        print(" m" + number)
    }
}

fun corrutina(s: String) {
    for (number in 1..9) {
        sleep(100)
        print(" "+s+number)
    }
    println("\n"+s+ " -- " + Thread.currentThread().name)
}

fun dispatchersEjemplos() {
    // sin dispatcher -> hilo donde se encuentra
    runBlocking {
        //println("Hilo 1 : 0 + ${Thread.currentThread().name}")
        tareaPesada3("Pesada con SUSPEND -- ")
    }

    tareaPesada2("Pesada con runBlocking -- ")

    // Le dices a Kotlin haz lo que quieras (Cualquiera)
    runBlocking(Dispatchers.Unconfined) {
        println("Hilo 1 : 1 + ${Thread.currentThread().name}")
    }

    // Donde Estoy , basicamente que es igual al primero (sin parametros)
    runBlocking(Dispatchers.Default) {
        println("Hilo 1 : 2 + ${Thread.currentThread().name}")
    }

    //Wen Services y bases de datos
    runBlocking(Dispatchers.IO) {
        println("Hilo 1 : 3 + ${Thread.currentThread().name}")
    }

    // No abusar y parece ser que esta obsoleto
    //@ObsoleteCoroutinesApi
    runBlocking(newSingleThreadContext("Mi hilo")) {
        println("Hilo 1 : 4 + ${Thread.currentThread().name}")
    }

//    runBlocking(Dispatchers.Main) {
//        println("Hilo 1 : 0 + ${Thread.currentThread().name}")
//    }

}


fun otraFuncion() {
    println("Tarea 1 " + Thread.currentThread().name + " -- " + Date().seconds)

    // Otra forma de hacer el RunBlocking
    /*runBlocking{
        tareaPesada2("Tarea 2")
    }*/

    tareaPesada2("Tarea 2")
    println("Tarea 3 " + Thread.currentThread().name + " -- " + Date().seconds)
}

fun tareaPesada(msg: String) {
    println("cargando... ${msg} " + Thread.currentThread().name + " -- " + Date().seconds)
    Thread.sleep(5000)
    println("dejo de cargar.... ${msg} " + Thread.currentThread().name + " -- " + Date().seconds)
}

fun tareaPesada2(msg: String) = runBlocking {
    println("cargando... ${msg} " + Thread.currentThread().name + " -- " + Date().seconds)
    Thread.sleep(5000)
    println("dejo de cargar.... ${msg} " + Thread.currentThread().name + " -- " + Date().seconds)
}

suspend fun tareaPesada3(msg: String) {
    println("cargando... ${msg} " + Thread.currentThread().name + " -- " + Date().seconds)
    sleep(5000)
    println("dejo de cargar.... ${msg} " + Thread.currentThread().name + " -- " + Date().seconds)
}