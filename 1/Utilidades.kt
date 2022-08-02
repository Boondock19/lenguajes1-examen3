package ve.usb.libGrafo

 /*
        descripcion: Funcion que retorna el grafo inverso de un digrafo
         
        precondiciones: que el argumento a pasar sea un digrafo

        postcondiciones: digrafo inverso g^-1 

        tiempo de la operacion: O(V + E) siendo V el numero de vertices y E el numero de lados

     */

fun digrafoInverso(g: GrafoDirigido) : GrafoDirigido {
    var listaArcos = g.iterator()
    var numVertices = g.obtenerNumeroDeVertices()
    var gInversa = GrafoDirigido(numVertices)

    listaArcos.forEach { a ->
        var arcoInvertido = Arco(a.segundoV,a.primerV)
        gInversa.agregarArco(arcoInvertido)
    }

    return gInversa
}

