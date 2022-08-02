package ve.usb.libGrafo

 /*
     descripcion: Clase  que retorna el DFS de un grafo g 
         
       precondiciones: que sea invocado por un objeto de la clase Grafo 

        postcondiciones: DFS generado por el grafo g y desde el vertice s

        tiempo de la operacion: O(V+E) porque se inicializan todos los vertices al principio y
        se recorren los adyacentes  en la ejecucion del mismo.

     */
public class DFS(val g: Grafo, val orden: MutableList<Int> = mutableListOf<Int>()) {
    
    var tiempo = 0
    var vertices = mutableListOf<Int>()  
    var listaDeLados : MutableList<MutableList<Lado>> = mutableListOf() // idea para el arbol dps
    var numVertices = g.obtenerNumeroDeVertices()
    var predecesores = Array(numVertices) {(Integer.MAX_VALUE)}
    var predecesoresNull = arrayOfNulls <Int?>(numVertices)
    var tiempoInicial = Array(numVertices) {(Integer.MAX_VALUE)}
    var tiempoFinal = Array(numVertices) {(Integer.MAX_VALUE)}
    var colores = Array(numVertices) {Color.BLANCO}
    var contadorComponenteConexa = Array(numVertices) {mutableListOf<Int>()}
    var contador = 0
    var ordenTopologicoLista = mutableListOf<Int>()
    var contadorComponenteConexaVertice = Array(numVertices) {0} // obtener contador de componente conexa directamente
     
    init {
	// Se ejecuta DFS
        for( i in 0..this.numVertices-1) {
            this.vertices.add(i)
        }


        if (orden.size > 0 ) {
            this.vertices.forEachIndexed {index,v -> 
                if (this.colores[v] === Color.BLANCO) {
                    dfsVisit(g,orden[index])
                }
            }
        } else {

            this.vertices.forEach { v ->
            if (this.colores[v] === Color.BLANCO) {
                dfsVisit(g,v)
                this.contador ++ // cada vez que entre en este if, corresponde a una componenteConexa
            }
            }
        } 
    }

    private fun dfsVisit(g: Grafo, u: Int) {

        this.tiempo = this.tiempo + 1
        this.tiempoInicial[u] = tiempo
        this.colores[u] = Color.GRIS
        this.contadorComponenteConexa[this.contador].add(u)
        this.contadorComponenteConexaVertice[u] = this.contador
        g.adyacentes(u).forEach { l ->
            var v = l.elOtroVertice(u)
            if (this.colores[v] === Color.BLANCO) {
              //  this.predecesores[v] = u
                this.predecesoresNull[v] = u
                dfsVisit(g,v)
            }
        }

        this.colores[u] = Color.NEGRO // se termina explorar V
        this.tiempo = this.tiempo + 1
        this.tiempoFinal[u] = this.tiempo
        // Lista de orden topologico invertida (El primer que termina se agrega a la lista,pero la podemor voltear)
        this.ordenTopologicoLista.add(u)

       // println("Predecesores con infinitos : ${this.predecesores.joinToString()} \n")
       // println("Predecesores con nulls : ${this.predecesoresNull.joinToString()} \n")
    }

      /*
        descripcion: Funcion que retorna al predecesor del vertice v
         
       precondiciones: que sea invocado por un objeto de la clase DFS 

        postcondiciones: int que representa al predecesor de v

        tiempo de la operacion: O(1) porque son comparaciones y asignaciones.

     */ 

    fun obtenerPredecesor(v: Int) : Int? { 
        if (!(v in this.vertices)) {
            throw RuntimeException("El vertice ${v} no se encuentra en el grafo")
        }

        return this.predecesoresNull[v]
    }

    /*
        descripcion: Funcion que retorna la el ti y tf de v
         
       precondiciones: que sea invocado por un objeto de la clase DFS y el
       vertice v pertenezca a G

        postcondiciones: Par de int que indica el ti y tf de v

        tiempo de la operacion: O(1) porque son comparaciones y asignaciones.

     */
    fun obtenerTiempos(v: Int) : Pair<Int, Int> {  
        if (!(v in this.vertices)) {
            throw RuntimeException("El vertice ${v} no se encuentra en el grafo")
        }

        var tiempoPar = Pair(this.tiempoInicial[v],this.tiempoFinal[v])

        return tiempoPar
    }

      /*
        descripcion: Funcion que retorna el vertice que contiene al otro segun el teorema de los parentesis
         
       precondiciones: que sea invocado por un objeto de la clase DFS 

        postcondiciones: vertice que contiene al otro (ancestro).

        tiempo de la operacion: O(1) porque son comparaciones y asignaciones.

     */
   fun  comparacionTiempo(a:Int,b:Int): Int? {
       val tiempoIa = this.tiempoInicial[a]
       val tiempoFa = this.tiempoFinal[a]
       val tiempoIb = this.tiempoInicial[b]
       val tiempoFb = this.tiempoFinal[b]
       if (tiempoIa < tiempoIb && tiempoFb < tiempoFa) return a // b esta contenido en a, es decir,b es descendiente de a
        else if (tiempoIb < tiempoIa && tiempoFa < tiempoFb) return b // a esta contenido en b, es decir,a es descendiente de b
        else return null
   }

   
    /*
        descripcion: Funcion que retorna un boleano para saber si un hay un camino
        desde s hasta un vertice v
         
       precondiciones: que sea invocado por un objeto de la clase DFS

        postcondiciones: booleano que indica si hay un camino hasta v desde u.

        tiempo de la operacion: O(1) porque son comparaciones y asignaciones.

     */ 
    fun hayCamino(u: Int, v: Int) : Boolean { 
        if (!(v in this.vertices)) {
            throw RuntimeException("El vertice ${v} no se encuentra en el grafo")
        }

        var camino = false // predefinido false 
        // Si comparacicon Tiempo no es null quiere decir que u es descendiente de v o viceversa
        // por el colorario 22.8, SI ES GRAFO DIRIGIDO tiene que revisar si es a o b
        if (this.comparacionTiempo(u,v) != null) {
            camino = true
        }
        
        return camino
     }

     /*
        descripcion: Funcion que retorna el camino  desde un vertice u hasta v
         
       precondiciones: que sea invocado por un objeto de la clase DFS 

        postcondiciones: lista que representa al camino  desde u hasta v

        tiempo de la operacion: O(v) ya que se recorren los vertices para obtener el camino.

     */
    
    fun caminoDesdeHasta(u: Int, v: Int) : Iterable<Int>  {  
        if (!(v in this.vertices) || !(u in this.vertices)) {
            throw RuntimeException("El vertice ${v} o $u no se encuentra en el grafo")
        }
        

        var camino = mutableListOf<Int>()
        var direccionBusqueda = this.comparacionTiempo(u,v)
       
        if (!(this.hayCamino(u,v))) throw RuntimeException("No existe un camino desde $u hasta $v")

        
        // si es U entonces el sucesor/hijo es v
        if (direccionBusqueda === u ) {
            var verticeBuscarPredecesor = v
            camino.add(v)
            while (this.obtenerPredecesor(verticeBuscarPredecesor) != u) {
            camino.add(this.obtenerPredecesor(verticeBuscarPredecesor) as Int)
            verticeBuscarPredecesor = this.obtenerPredecesor(verticeBuscarPredecesor) as Int
            }
            // al salir del while debemos agregar a u
            camino.add(u)
        } 

        // si es v entonces el sucesor/hijo es u
        if (direccionBusqueda === v) {
            var verticeBuscarPredecesor = u
            camino.add(u)
            while (this.obtenerPredecesor(verticeBuscarPredecesor) != v) {
            camino.add(this.obtenerPredecesor(verticeBuscarPredecesor) as Int)
            verticeBuscarPredecesor = this.obtenerPredecesor(verticeBuscarPredecesor) as Int
            }
            // al salir del while debemos agregar a v
            camino.add(v)
        }
       
        return camino.reversed() 


    }
    

       /*
        descripcion: Funcion que retorna el bosque generado por el DFS
         
       precondiciones: que sea invocado por un objeto de la clase DFS

        postcondiciones: Array en el que cada slot que representa a un arbol del DFS (componente Conexa)

        tiempo de la operacion: O(1) ya que es una asignacion

     */

   fun depthFirstForest() :List<MutableList<Int>>{ 
       // La unica manera que se me ocurre para poder mostrar todos los Arboles
        // es a traves de un array de listas mutables donde cada lista representa a
        // una componente conexa y que por lo tanto es un arbol producto del DFS

        // filter devuelve una Lista
       return this.contadorComponenteConexa.filter { it.size > 0 }
   }

   fun obtenerOrdenTopologico() : Iterable<Int> {
       var ordenTopologicoListaInversa = this.ordenTopologicoLista.reversed()
       return ordenTopologicoListaInversa
   }

}
