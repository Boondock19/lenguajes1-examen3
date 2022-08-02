package ve.usb.libGrafo

abstract class Lado(val a: Int, val b: Int) {

    val primerV = a
    val segundoV = b

    /*
        descripcion: funcion que retorna un int, que es un vertice
        
        precondiciones: que sea invocado sobre un objeto de tipo Lado

        postcondiciones: int que representa a un vertice

        tiempo de la operacion: O(1)

     */

    fun cualquieraDeLosVertices() : Int {
        return primerV
    }

    /*
        descripcion: funcion que retorna un int, que es un vertice, dependiendo de cual vertice se le haya pasado.
        
        precondiciones: que sea invocado sobre un objeto de tipo Lado

        postcondiciones: int que representa a un vertice

        tiempo de la operacion: O(1)

     */
    fun elOtroVertice(w: Int) : Int {
        if (w == primerV) {
            return segundoV
        } else if (w == segundoV) {
            return primerV
        } else {
            throw Exception("El vertice ${w} no pertenece a este lado")
        }
    }
}
