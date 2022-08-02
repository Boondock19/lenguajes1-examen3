package ve.usb.libGrafo

import java.io.File

public class GrafoNoDirigido: Grafo {

    var listaArchivo = mutableListOf<String>()
    var listaLados =  mutableListOf<String>()
    var listaVertices = mutableListOf<Int>()
    var listaAristas = mutableListOf<Arista>()
    var numVertices =  0
    var numLados = 0
    var listaAdyacencias = Array(numVertices) {mutableSetOf<Int>()}
    var grafo = Array(numVertices) {mutableListOf<Arista>()}

    /*
        descripcion: Constructor de un GrafoNoDirigido a partir de un txt
        
        precondiciones: --

        postcondiciones: grafo resultante 

        tiempo de la operacion: O(n) siendo n la cantidad de vertices

     */
    constructor(numDeVertices: Int) {
        this.numVertices = numDeVertices
        this.listaAdyacencias = Array(this.numVertices){mutableSetOf<Int>()}
        this.grafo = Array(this.numVertices) {mutableListOf<Arista>()}
        for (i in 0..this.numVertices-1) {
            this.listaVertices.add(i)
        }
    }

   /*
        descripcion: Constructor de un GrafoNoDirigido a partir de un txt
        
        precondiciones: --

        postcondiciones: grafo resultante 

        tiempo de la operacion: O(n) siendo n el size de los numeros de lado

     */

    constructor(nombreArchivo: String) {
        //fun para leer y almacenar data de txts en forma de lista
        
        File(nombreArchivo).useLines{ lines -> lines.forEach { this.listaArchivo.add(it) }}
        
        // Almacenamos data del grafo como cantidad de vertices y lados
        
       this.numVertices = listaArchivo[0].toInt()
       this.numLados = listaArchivo[1].toInt()
       this.listaAdyacencias = Array(this.numVertices) {mutableSetOf<Int>()}
       for ( i in 0..numVertices-1){
            this.listaVertices.add(i)
        }
        
        // Creamos una subLista para poder trabajar con los lados
        
        this.listaLados = listaArchivo.subList(2, listaArchivo.size)
        
        

        /** 
            Creamos las Aristas y los agregamos en la lista de adyacencia
        */ 

        this.listaLados.forEach { 
        val ladoSinSeparador = it.split(" ") 
        val primerVertice = ladoSinSeparador[0].toInt()
        val segundoVertice = ladoSinSeparador[1].toInt()
        val newArista = Arista(primerVertice,segundoVertice)
        this.listaAristas.add(newArista)

        // Se agrega en ambas lista por ser un grafo no dirigido
        this.listaAdyacencias[primerVertice].add(segundoVertice)
        this.listaAdyacencias[segundoVertice].add(primerVertice)

    }

    this.grafo = Array(this.numVertices) {mutableListOf<Arista>()}
     this.grafo.forEachIndexed { index, _ ->
            var aristasFiltrada = this.listaAristas.filter {it.primerV == index }
            this.grafo.set(index, aristasFiltrada.toMutableList()) 
        }
}

    /*
        descripcion: Funcion que retorna un boleano al agregar satisfactoriamente la arista
        
         
        precondiciones: que el objeto que invoca al metodo sea un GrafoNoDirigido y se le pase una arista,

        postcondiciones: grafo resultante luego de la insercion 

        tiempo de la operacion: O(n) siendo n el size de los numeros de lado

     */

    fun agregarArista(a: Arista) : Boolean {
        
        var newArista = a
        var aristasFiltrados = mutableListOf<Arista>()
        
        this.listaAristas.forEach { arista ->
            if ((newArista.primerV == arista.primerV && newArista.segundoV == arista.segundoV) || (newArista.primerV == arista.segundoV && newArista.segundoV == arista.primerV)) {
               return false
            }
        }

        this.listaAristas.add(newArista)
        aristasFiltrados.add(newArista)
        
        // Se agrega en ambas lista por ser un grafo no dirigido
        this.listaAdyacencias[newArista.primerV].add(newArista.segundoV)
        this.listaAdyacencias[newArista.segundoV].add(newArista.primerV)
        this.grafo.set(newArista.primerV,(this.grafo[newArista.primerV]+aristasFiltrados).toMutableList())
        this.numLados += 1 
        
        return true

    }

    /*
        descripcion: Funcion que retorna un int que representa la cantidad de lados del GrafoNoDirigido
         
        precondiciones: que el objeto que invoca al metodo sea un GrafoNoDirigido

        postcondiciones: int que representa la cantidad de lados o arista de un GrafoNoDirigido

        tiempo de la operacion: O(1) 

     */
    override fun obtenerNumeroDeLados() : Int {
        return this.numLados
    }

   /*
        descripcion: Funcion que retorna un int que representa la cantidad de vertices del GrafoNoDirigido
         
        precondiciones: que el objeto que invoca al metodo sea un GrafoNoDirigido

        postcondiciones: int que representa la cantidad de vertices  de un GrafoNoDirigido

        tiempo de la operacion: O(1) 

     */

    override fun obtenerNumeroDeVertices() : Int {
        return this.numVertices
    }

      /*
        descripcion: Funcion que retorna una lista que contiene las arista que 
        contengan como vertice inicial a v. 
         
        precondiciones: que el objeto que invoca al metodo sea un GrafoNoDirigido y se le pase un int

        postcondiciones: lista que contiene las arista que 
        contengan como vertice inicial a v

        tiempo de la operacion: O(n) siendo n el size de los numeros de lados

     */

    override fun adyacentes(v: Int) : Iterable<Arista> {
        
        if (!(v in this.listaVertices)) {
            throw Exception ("El vertice $v no pertenece al grafo")
        }

        var  adyacencias = mutableSetOf<Arista>()
        
        this.listaAristas.forEach {arista ->
            if (v == arista.primerV || v == arista.segundoV) {
                adyacencias.add(arista) 
            }
        }

        return adyacencias.asIterable()
    }

      /*
        descripcion: Funcion que retorna un iterator de la lista que contiene las arista
         
        precondiciones: que el objeto que invoca al metodo sea un GrafoNoDirigido

        postcondiciones: lista que contiene las arista que 
        

        tiempo de la operacion: O(1) 

     */

     override operator fun iterator() : Iterator<Arista> {
         return this.listaAristas.iterator()
     }

      /*
        descripcion: Funcion que retorna el grado  de un vertice en un GrafoNoDirigido
        
         
        precondiciones: que el objeto que invoca al metodo sea un GrafoNoDirigido y se le pase vertice,

        postcondiciones: entero que representa al grado del vertice

        tiempo de la operacion: O(1) 

     */
    override fun grado(v: Int) : Int {
        if (!(v in this.listaVertices)) {
            throw Exception("El vertice $v no pertecene al grafo")
        }

        return this.listaAdyacencias[v].size
    }

  /*
        descripcion: Funcion que retorna el la representacion en string de un GrafoNoDirigido
         
        precondiciones: que el objeto que invoca al metodo sea un GrafoNoDirigido

        postcondiciones: string que representa a un GrafoNoDirigido

        tiempo de la operacion: O(n) siendo n el size del grafo

     */


    override fun toString() : String {
       var representacionGrafo = ""
        this.grafo.forEachIndexed { index, _ ->
            
            representacionGrafo += "${index} : ${this.grafo[index]} \n"
            
         }
            
        return " ${representacionGrafo}"
    }
}
