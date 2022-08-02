
import ve.usb.libGrafo.*

fun main(args: Array<String>) {
    
    var grafoNoDirigido = GrafoNoDirigido(7)

    // Agregando aristas al grafo
    // grafoNoDirigido.agregarArista(Arista(0,2))
    // grafoNoDirigido.agregarArista(Arista(2,3))
    // grafoNoDirigido.agregarArista(Arista(3,1))
    // grafoNoDirigido.agregarArista(Arista(3,6))
    // grafoNoDirigido.agregarArista(Arista(6,5))
    // grafoNoDirigido.agregarArista(Arista(3,4))


    grafoNoDirigido.agregarArista(Arista(0,3))
    grafoNoDirigido.agregarArista(Arista(0,4))
    grafoNoDirigido.agregarArista(Arista(1,3))
    grafoNoDirigido.agregarArista(Arista(1,5))
    grafoNoDirigido.agregarArista(Arista(2,4))
    grafoNoDirigido.agregarArista(Arista(4,5))
    
    
    
    
    
    println("Este es el grafo luego de la inserciones ${grafoNoDirigido.toString()}")
    // hay camino desde 0 a 5?
    /*
        BFS solicita el grafo, el vertice D y el vertice H
        comienza el BFS desde el vertice D 

     */
    var bfs = BFS(grafoNoDirigido, 0, 5)
    println("buscar con BFS ,  ${bfs.buscar(0,5)}")

    // hay camino desde 0 a 5?
    /*
        DFS solicita el grafo, el vertice D y el vertice H
        comienza el DFS desde d
     */

     var dfs = DFS(grafoNoDirigido, 0, 5)
     println("buscar con DFS ,  ${dfs.buscar(0,5)}")

}