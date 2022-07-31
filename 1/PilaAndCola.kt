
package estructruras.secuencias

/* 
    Clase Pila que hereda abstracta Secuencia que implementa 3 metodos:
    - agregar un elemento a la pila
    - remover remueve el ultimo elemento ingresado en la pila
    - vacio (Devuelve un booleano indicando si la pila esta vacia)
 */

class Pila<T>(): Secuencia<T>() {

    var arrayOfElements = mutableListOf<T>()
    var sizeOfStack = 0
    var top = 0

    override fun agregar(element: T) {
        arrayOfElements.add(element)
        sizeOfStack++
        top++
    }

   override fun remover(): T {
        var element : T
        if (vacio()) {
            throw Exception("Pila vacia")
        } else {
            if (top == 0) {
                element = arrayOfElements.removeAt(top)
                
            } else {
                top--
                element = arrayOfElements.removeAt(top)
            }

            sizeOfStack--
            return element
        }
    }

   override fun vacio(): Boolean {
        
        if (sizeOfStack == 0) {
            return true
        } else {
            return false
        }
    }

}

/* 
    Clase Cola que hereda abstracta Secuencia que implementa 3 metodos:
    - agregar un elemento a la cola
    - remover remueve el primer elemento de la cola
    - vacio (Devuelve un booleano indicando si la cola esta vacia)
 */

class Cola<T>(): Secuencia<T>() {
    
    var arrayOfElements = mutableListOf<T>()
    var sizeOfQueue = 0

    override fun agregar(element: T) {
        if (vacio()) {
            arrayOfElements.add(element)
            sizeOfQueue++
            
        } else {
            arrayOfElements.add(element)
            sizeOfQueue++
            
        }
        
    }

    override fun remover(): T {
        var element : T
        if (vacio()) {
            throw Exception("Cola vacia")
        } else {
            element = arrayOfElements.removeAt(0)
            sizeOfQueue--
        }
        return element
    }

    override fun vacio(): Boolean {
        if (sizeOfQueue == 0) {
            return true
        } else {
            return false
        }
    }
}