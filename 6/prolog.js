
// Enter any texts ( User input)




console.log("Bienvenido al programa de interprete de prolog");
console.log('Luego de cada accion, se pedira una siguiente')

// Creando un array para cada uno de los programas a definir

/**
 * atomics serian expresiones con def y la primera letra 
 * es miniscula.
 * 
 * hola
 * quickSort
 */
let arrayOfAtomics = []

/**
 * variables serian expresiones con def y la primera letra 
 * es mayuscula.
 * 
 * Hola
 * QuickSort
 */

let arrayOfVariables = []

/**
 * estructuras serian expresiones con def y la primera letra 
 * es minuscula, que representa un atomo, seguida de una secuencia,
 * parantizada y separadas con comas, de otras expresiones.
 * 
 * f(x,y)
 * quickSort(entrada,Salida)
 */

let arrayOfStructs = []





process.stdin.on('data', data => {

    const dataString = (data).toString()
    const dataArray =dataString.split(" ")
    

    /* 
        Creacion de tipos atomicos
    */
    if(dataArray[0] == "DEF") {
        console.log("Entro en DEF")
        let expression = dataArray[1].trim()
        defType(expression)
        
    }
        
    

        
    

    /* 
        Creacion de structs
    */
    if(dataArray[0] == "ASK") {

        console.log("Entro en ASK")        

    }

 
    console.log("Siguiente accion: ")

    // Salimos del programa
    if (dataArray[0].trim() === 'SALIR') {
        console.log('Terminando el programa ...')
        process.exit()
    }
    
});

/**
 * Funcion para crear una estructura
 * se encarga de manejar la insercion en atomics,
 * variables y structs.
 */

const defType = (types) => {
    console.log("Entro en defType")
    let splitedType = types.split("")
    const originalChar = splitedType[0]
    let compareChar = originalChar.toUpperCase()

    console.log('orignalChar: ' + originalChar)
    console.log('compareChar: ' + compareChar)

    /**
     * Si originalChar y compareChar son iguales,
     * quiere decir que la primera letra es mayuscula 
     * y por lo tanto es una variable. en caso contrario
     * es un atomico o estructura.
     */

    if (originalChar === compareChar) {
        console.log("Es una variable")
        arrayOfVariables.push(types)
    } else {
        
        /**
         * Si la primera letra es minuscula,
         * puede ser un atomico o una estructura.
         * para descartar si es una estructura, verificamos
         * que contenga ( y ). 
         */
        if (types.includes('(') && types.includes(')')) {
            console.log("Es una estructura")
            arrayOfStructs.push(types)
        } else {
            console.log("Es un atomico")
            arrayOfAtomics.push(types)
        }
    }

    console.log('Array de atomico' + " " + arrayOfAtomics)
    console.log('Array de variable' + " " + arrayOfVariables)
    console.log('Array de estructuras' + " " + arrayOfStructs)
    

    
}
