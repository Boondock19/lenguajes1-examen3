



console.log("Bienvenido al programa manejador de clases");
console.log('Luego de cada accion, se pedira una siguiente')

// Creando un array para cada uno de los programas a definir

/**
 * atomics serian expresiones con def y la primera letra 
 * es miniscula.
 * 
 * hola
 * quickSort
 */
let arrayOfClasses = []

/**
 * variables serian expresiones con def y la primera letra 
 * es mayuscula.
 * 
 * Hola
 * QuickSort
 */

let arrayOfSuperClass = []



process.stdin.on('data', data => {

    const dataString = (data).toString()
    const dataArray =dataString.split(" ")
    

    /* 
        Creacion de tipos atomicos
    */
    if(dataArray[0] == "CLASS") {
        console.log("Entro en CLASS")
        let expression = dataArray[1].trim()
        //defType(expression)
        
    }
        
    

        
    

    /* 
        Creacion de structs
    */
    if(dataArray[0] == "DESCRIBIR") {

        console.log("Entro en DESCRIBIR")        

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
    return " "
}
