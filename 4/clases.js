import { SimpleClass, SuperClass } from "./typesClasses.js";

console.log("Bienvenido al programa manejador de clases");
console.log("Luego de cada accion, se pedira una siguiente");

// Creando un array para cada uno de los programas a definir

/**
 * Array de clases que heredan de una super clase
 *
 */
let arrayOfClasses = [];

/**
 * Array de clases que no heredan de una super clase
 *
 */

let arrayOfSuperClass = [];

let arrayOfNames = [];

process.stdin.on("data", (data) => {
  const dataString = data.toString();
  const dataArray = dataString.split(" ");

  /* 
        Creacion de tipos atomicos
    */
  if (dataArray[0].trim() == "CLASS") {
    if (dataArray.length < 3) {
      console.log("Error: faltan datos");
    } else {
      console.log("Entro en CLASS");
      console.log(dataArray);
      let expression = dataArray[2].trim();
      if (expression == ":" && dataArray.length < 5) {
        console.log(
          "Error: faltan datos para declarar una herencia, recuerde que la clase debe definir al menos un metodo"
        );
      } else {
        defType(dataArray);
        console.log("Array of clases", arrayOfClasses);
        console.log("array of superClases", arrayOfSuperClass);
      }
    }
  }

  /* 
        Creacion de structs
    */
  if (dataArray[0].trim() == "DESCRIBIR") {
    if (dataArray.length < 2) {
      console.log("Error: faltan datos");
    } else {
      console.log("Entro en DESCRIBIR");
      console.log(dataArray);
    }
  }

  console.log("Siguiente accion: ");

  // Salimos del programa
  if (dataArray[0].trim() === "SALIR") {
    console.log("Terminando el programa ...");
    process.exit();
  }
});

/**
 * Funcion para crear una estructura
 * se encarga de manejar la insercion en atomics,
 * variables y structs.
 */

const defType = (types) => {
  console.log(types);
  let methods = [];
  let name = "";
  if (types.includes(":")) {
    name = types[1];
    if (nameExists(name, arrayOfNames)) {
      console.log("Error: el nombre ya existe");
    } else {
      let father = types[3];
      if (!fatherExists(father, arrayOfSuperClass)) {
        console.log("Error: la super clase no existe");
      } else {
        types[types.length - 1] = types[types.length - 1].trim();
        methods = types.slice(4);
        if (findDuplicates(methods)) {
          console.log("Error: hay metodos duplicados");
        } else {
          const newClass = new SimpleClass(name, methods, father);
          arrayOfClasses.push(newClass);
          arrayOfNames.push(name);
          // console.log("Contiene :, clase que hereda de una super clase")
        }
      }
    }
  } else {
    name = types[1];
    if (nameExists(name, arrayOfNames)) {
      console.log("Error: el nombre ya existe");
    } else {
      types[types.length - 1] = types[types.length - 1].trim();
      methods = types.slice(2);
      if (findDuplicates(methods)) {
        console.log("Error: hay metodos duplicados");
      } else {
        const newSuperClass = new SuperClass(name, methods);
        arrayOfSuperClass.push(newSuperClass);
        arrayOfNames.push(name);
        // console.log("No contiene : es una clase con metodos")
      }
    }
  }
};

/**
 * Funcion para verificar si un nombre de clase ya existe
 * @param {nombre a verificar} name
 * @param {array en donde buscar el nombre} array
 * @returns
 */

const nameExists = (name, array) => {
  let exist = array.find((element) => {
    if (element == name) {
      return name;
    } else {
      return undefined;
    }
  });

  return exist;
};

/**
 * Funcion que verifica si existe el nombre de una super clase
 * @param {nombre a buscar} name
 * @param {en donde buscar el nombre} array
 * @returns
 */

const fatherExists = (name, array) => {
  let exist = array.find((element) => {
    if (element.name == name) {
      return name;
    } else {
      return undefined;
    }
  });

  return exist;
};

/**
 * Funcion que verifica si hay metodos duplicados de una clase.
 * @param {array para verificar duplicados} array
 * @returns true si hay duplicados, false si no
 */
const findDuplicates = (array) => {
  // Al crear un Set JS elimina los duplicados
  const newSet = new Set(array);
  // Si el tamaño del array y el set es distinto, entonces existen duplicados.

  if (array.length !== newSet.size) {
    return true;
  } else {
    return false;
  }
};
