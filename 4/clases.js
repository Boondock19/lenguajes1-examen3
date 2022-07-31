
import { SimpleClass , SuperClass} from './typesClasses.js'


console.log("Bienvenido al programa manejador de clases");
console.log('Luego de cada accion, se pedira una siguiente')

// Creando un array para cada uno de los programas a definir

/**
 * Array de clases que heredan de una super clase
 * 
 */
let arrayOfClasses = []

/**
 * Array de clases que no heredan de una super clase
 * 
 */

let arrayOfSuperClass = []

let arrayOfNames = []

process.stdin.on('data', data => {

    const dataString = (data).toString()
    const dataArray =dataString.split(" ")
   
    /* 
        Creacion de tipos atomicos
    */
    if(dataArray[0].trim() == "CLASS") {
        if (dataArray.length < 3 ) {
            console.log("Error: faltan datos")
        } else {
            console.log("Entro en CLASS")
            console.log(dataArray)
            let expression = dataArray[2].trim()
            if (expression == ':' && dataArray.length < 5) {
                console.log("Error: faltan datos para declarar una herencia, recuerde que la clase debe definir al menos un metodo")
            } else {
                defType(dataArray)
                console.log('Array of clases', arrayOfClasses)
                console.log('array of superClases',arrayOfSuperClass)
            }
           
        }
       
        
    }
        
    

        
    

    /* 
        Creacion de structs
    */
    if(dataArray[0].trim() == "DESCRIBIR") {
        if (dataArray.length < 2 ) {
            console.log("Error: faltan datos")        

        } else {
            console.log("Entro en DESCRIBIR")
            console.log(dataArray)     
        }
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
    console.log(types)
    let methods = []
    let name = ''
    if (types.includes(':')) {
        name = types[1]
        if (nameExists(name)) {
            console.log("Error: el nombre ya existe")
        } else { 
            let father = types[3]
            types[types.length - 1] = types[types.length - 1].trim()
            methods = types.slice(4)
            const newClass = new SimpleClass(name,methods,father)
            arrayOfClasses.push(newClass)
            arrayOfNames.push(name)
            // console.log("Contiene :, clase que hereda de una super clase")
        }
    } else {
        name = types[1]
        if (nameExists(name)) {
            console.log("Error: el nombre ya existe")
        } else {
            types[types.length - 1] = types[types.length - 1].trim()
            methods = types.slice(3)
            const newSuperClass = new SuperClass(name,methods)
            arrayOfSuperClass.push(newSuperClass)
            arrayOfNames.push(name)
            // console.log("No contiene : es una clase con metodos")
        }
    }
    
}


const nameExists = (name) => {
    console.log('NOmbre', name)
    console.log('Array of names', arrayOfNames)
    arrayOfNames.find((element) => {
        console.log('Element', element)
        console.log('Name', name)
        console.log('Element == Name', element == name)
        if (element == name) {
            return name
        } else {
            return undefined
        } 
    })
}