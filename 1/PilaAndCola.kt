
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

    override fun agregar(element: T): Boolean {
        arrayOfElements.add(element)
        sizeOfStack++
        top++
        return true
    }

   override fun remover(): Boolean {

        if (vacio()) {
            return false
        } else {
            if (top == 0) {
                arrayOfElements.removeAt(top)
            } else {
                top--
                arrayOfElements.removeAt(top)
            }
            sizeOfStack--
            return true
        }
    }

   override fun vacio(): Boolean {
        if (sizeOfStack == 0) {
            return true
        } else {
            return false
        }
    }

    // override fun toString(): String {
        
    // }

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
    var head = 0
    var tail = 0

    override fun agregar(element: T): Boolean {
        if (vacio()) {
            arrayOfElements.add(element)
            sizeOfQueue++
            head++
            tail++
        } else {
            arrayOfElements.add(element)
            sizeOfQueue++
            tail++
        }
        
        return true
    }

    override fun remover(): Boolean {
        if (vacio()) {
            return false
        } else {
            arrayOfElements.removeAt(head)
            head++
            sizeOfQueue--
        }
        return true
    }

    override fun vacio(): Boolean {
        if (sizeOfQueue == 0) {
            return true
        } else {
            return false
        }
    }
}