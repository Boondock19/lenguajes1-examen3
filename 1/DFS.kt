package ve.usb.libGrafo
import estructruras.secuencias.*

 /*
     descripcion: Clase  que retorna el DFS de un grafo g 
         
       precondiciones: que sea invocado por un objeto de la clase Grafo 

        postcondiciones: DFS generado por el grafo g y desde el vertice s

        tiempo de la operacion: O(V+E) porque se inicializan todos los vertices al principio y
        se recorren los adyacentes  en la ejecucion del mismo.

     */
public class DFS(val g: Grafo, val d:Int,val h:Int) : Busqueda<Int>() {
    
    var tiempo = 0
    var vertices = mutableListOf<Int>()  
    var numVertices = g.obtenerNumeroDeVertices()
    var predecesores = Array(numVertices) {(Integer.MAX_VALUE)}
    var predecesoresNull = arrayOfNulls <Int?>(numVertices)
    var tiempoInicial = Array(numVertices) {(Integer.MAX_VALUE)}
    var tiempoFinal = Array(numVertices) {(Integer.MAX_VALUE)}
    var colores = Array(numVertices) {Color.BLANCO}
    var stack = Pila<Int>()
    var contar = true
    var verticesExplorados = 0


    init {
	// Se ejecuta DFS
        for( i in 0..this.numVertices-1) {
            this.vertices.add(i)
        }
        this.tiempo = this.tiempo + 1
        this.tiempoInicial[d] = this.tiempo 
        this.stack.agregar(d)
        this.colores[d] = Color.NEGRO

        while (!this.stack.vacio()) {
            val u = this.stack.remover()
            if (this.contar) this.verticesExplorados++
            g.adyacentes(u).forEach { l -> 
                var v = l.elOtroVertice(u)
                if (v == h) this.contar = false
                if (this.colores[v] === Color.BLANCO) {
                    this.stack.agregar(v)
                    this.predecesoresNull[v] = u
                    this.colores[v] = Color.NEGRO
                }
            }
        }

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

    
    override fun buscar(d:Int,h:Int) : Int {
        if (this.contar) {
            return -1
        } else {
            return this.verticesExplorados
        }
    }

}
