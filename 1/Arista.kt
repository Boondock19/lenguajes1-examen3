package ve.usb.libGrafo

public open class Arista(val v: Int, val u: Int) : Lado(v, u) {

     /*
        descripcion: Funcion que retorna la representacion en string de una arista
         
        precondiciones: que el objeto que invoca al metodo sea un arista

        postcondiciones: string que representa a una arista

        tiempo de la operacion: O(1)

     */
     
    override fun toString() : String {
        return "($v,$u)"
    }

} 
