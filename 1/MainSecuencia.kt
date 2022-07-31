import estructruras.secuencias.*


fun main(args: Array<String>) {
    println("Secuencia")

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
    println("Esta la pila vacia? ${pila.vacio()}")
}