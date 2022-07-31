import estructruras.secuencias.*


fun main(args: Array<String>) {
    println("Ejecutando ejemplos de Secuencia")

    // Crearemos una pila de strings
    var pila = Pila<String>()
    pila.agregar("Hola")
    pila.agregar("Mundo")
    pila.agregar("!")
    println(pila.arrayOfElements)
    // removeremos uno a uno e imprimiremos 
    pila.remover()
    println(pila.arrayOfElements)
    pila.remover()
    println(pila.arrayOfElements)
    pila.remover()
    println(pila.arrayOfElements)
    println("\nEsta la pila vacia? ${pila.vacio()}")
    println("\nFin de ejemplos de pila\n")

    // Creamos una cola con Strings

    var cola = Cola<String>()
    cola.agregar("Hola")
    cola.agregar("Mundo")
    cola.agregar("No Es lo mismo!")
    println(cola.arrayOfElements)
    // removeremos uno a uno e imprimiremos 
    cola.remover()
    println(cola.arrayOfElements)
    cola.remover()
    println(cola.arrayOfElements)
    cola.remover()
    println(cola.arrayOfElements)
    println("\nEsta la cola vacia? ${cola.vacio()}")
    println("\nFin de ejemplos de cola\n")

}