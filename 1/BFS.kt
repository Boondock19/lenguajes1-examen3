package ve.usb.libGrafo

import java.util.LinkedList
import java.util.Queue

 /*
     descripcion: Clase  que retorna el BFS de un grafo g desde un vertice s
         
       precondiciones: que sea invocado por un objeto de la clase Grafo 

        postcondiciones: BFS generado por el grafo g y desde el vertice s

        tiempo de la operacion: O(V+E) porque se inicializan todos los vertices al principio y
        se recorren los lados en la ejecucion del mismo.

     */
public class BFS(val g: Grafo, val D: Int, H: Int): Busqueda<Int>() {

    var vertices = mutableListOf<Int>()  
    var listaDeLados : MutableList<MutableList<Lado>> = mutableListOf() // idea para el arbol dps
    var numVertices = g.obtenerNumeroDeVertices()
    var predecesores = Array(numVertices) {mutableListOf<Int>()}
    var distancias = Array(numVertices) {(Integer.MAX_VALUE)}
    var colores = Array(numVertices) {Color.BLANCO}
    var nodosExplorados = 0
    var contar = true
    var encontroH = false

    init {
	// Se ejecuta BFS
        // Creamos la cola, u , iniciamos la distancia de s en 0 y la lista de vertices.
        var queue : Queue<Int> = LinkedList<Int>()
        var u = 0
        this.distancias[D] = 0
        for (i in 0..this.numVertices-1) {
            this.vertices.add(i)
        }

        // encolamos a s
        queue.add(D)

        while (!queue.isEmpty()) {
            // desencolamos los elementos en el orden en que fueron ingresados
            u = queue.peek()
            if (this.contar) this.nodosExplorados++
            queue.remove()
            g.adyacentes(u).forEach { l ->
               var v = l.elOtroVertice(u) // el otro vertice de U
                if (v == H) this.contar = false
                if (this.colores[v] === Color.BLANCO) {
                    this.colores[v] = Color.GRIS
                    this.distancias[v] = this.distancias[u] + 1
                    this.predecesores[v].add(u)
                    queue.add(v)
                }
            }
            this.colores[u] = Color.NEGRO 
        }

       
    }

     /*
        descripcion: Funcion que retorna al predecesor del vertice v
         
       precondiciones: que sea invocado por un objeto de la clase BFS 

        postcondiciones: int que representa al predecesor de v

        tiempo de la operacion: O(1) porque son comparaciones y asignaciones.

     */
    fun obtenerPredecesor(v: Int) : Int? {  
        if (!(v in this.vertices)) {
            throw RuntimeException("El vertice ${v} no se encuentra en el grafo")
        }

        return this.predecesores[v].first()
    }

 
    /*
        descripcion: Funcion que retorna la distancia  desde s hasta v
         
       precondiciones: que sea invocado por un objeto de la clase BFS 

        postcondiciones: int que representa distancia desde s hasta v

        tiempo de la operacion: O(1) porque son comparaciones y asignaciones.

     */
    fun obtenerDistancia(v: Int) : Int {  
        if (!(v in this.vertices)) {
            throw Exception("El vertice ${v} no se encuentra en el grafo")
        }

        return this.distancias[v]
    }

    /*
        descripcion: Funcion que retorna un boleano para saber si un hay un camino
        desde s hasta un vertice v
         
       precondiciones: que sea invocado por un objeto de la clase BFS

        postcondiciones: booleano que indica si hay un camino hasta v desde s.

        tiempo de la operacion: O(1) porque son comparaciones y asignaciones.

     */ 
    fun hayCaminoHasta(v: Int) : Boolean {  
        if (!(v in this.vertices)) {
            throw Exception("El vertice ${v} no se encuentra en el grafo")
        }

        var camino = false // predefinido false

        // si la distancia es distinta a 0 quiece decir que hay un camino de s hasta v
        if (this.distancias[v] != Integer.MAX_VALUE && this.distancias[v] != 0) {
            camino = true
        }

        return camino
    }

      /*
        descripcion: Funcion que retorna el camino de menor tamaño desde un vertice s hasta v
         
       precondiciones: que sea invocado por un objeto de la clase BFS 

        postcondiciones: lista que representa al camino mas corto desde s hasta v

        tiempo de la operacion: O(v) ya que se recorren los vertices para obtener el camino.

     */
    fun caminoConMenosLadosHasta(v: Int) : Iterable<Int>  {  
        if (!(v in this.vertices)) {
            throw Exception("El vertice ${v} no se encuentra en el grafo")
        }

        if (!(this.hayCaminoHasta(v))) throw RuntimeException("No existe un camino desde $D hasta $v")
        
        var caminoCortoSinS = mutableListOf<Int>()
        if (v != D) {
            caminoCortoSinS.add(v)
        }
        
        var verticeBuscarPredecesor = v
        while (this.obtenerPredecesor(verticeBuscarPredecesor) != D) {
          caminoCortoSinS.add(this.obtenerPredecesor(verticeBuscarPredecesor) as Int)
          verticeBuscarPredecesor = this.obtenerPredecesor(verticeBuscarPredecesor) as Int
        }
        // al salir del while debemos agregar a S
        caminoCortoSinS.add(D)

        return caminoCortoSinS.reversed() // verificar si van desde s hasta v o de v hasta s
    }

  override fun buscar(d:Int,h:Int) : Int {
        if (this.hayCaminoHasta(h)) {
            return this.nodosExplorados
        } else {
            return -1
        }
    }
  
   fun breadthFirstTree() : String{
       var filtroInf = this.distancias.filter {it != Integer.MAX_VALUE}
       var maxDistancia  = filtroInf.maxOrNull() as Int
       var verticesPorNivel = Array(maxDistancia+1) {mutableListOf<Int>()}

       this.vertices.forEach {v ->
        if (this.distancias[v] != Integer.MAX_VALUE)  verticesPorNivel[this.distancias[v]].add(v)
        
       }
        
        var string ="\n"

        verticesPorNivel.forEachIndexed {index,nivel ->
            string = string + "$index : $nivel \n"
        } 

        return string
   }
}
