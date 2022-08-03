// Enter any texts ( User input)

console.log("Bienvenido al programa de interprete de prolog");
console.log("Luego de cada accion, se pedira una siguiente");

// Creando un array para cada uno de los programas a definir

/**
 * atomics serian expresiones con def y la primera letra
 * es miniscula.
 *
 * hola
 * quickSort
 */
let arrayOfAtomics = [];

/**
 * variables serian expresiones con def y la primera letra
 * es mayuscula.
 *
 * Hola
 * QuickSort
 */

let arrayOfVariables = [];

/**
 * estructuras serian expresiones con def y la primera letra
 * es minuscula, que representa un atomo, seguida de una secuencia,
 * parantizada y separadas con comas, de otras expresiones.
 *
 * f(x,y)
 * quickSort(entrada,Salida)
 */

let arrayOfStructs = [];

process.stdin.on("data", (data) => {
  const dataString = data.toString();
  const dataArray = dataString.split(" ");

  /* 
        Creacion de tipos atomicos
    */
  if (dataArray[0] == "DEF") {
    let spliceData = dataArray.splice(1);
    spliceData[spliceData.length - 1] =
      spliceData[spliceData.length - 1].trim();
    defType(spliceData);
  }

  /* 
        Creacion de structs
    */
  if (dataArray[0] == "ASK") {
    console.log("Entro en ASK , no se implemento");
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
  let firstPredicate = types[0];
  let splitedType = firstPredicate.split("");
  const originalChar = splitedType[0];
  let compareChar = originalChar.toUpperCase();

  /**
   * Si originalChar y compareChar son iguales,
   * quiere decir que la primera letra es mayuscula
   * y por lo tanto es una variable. en caso contrario
   * es un atomico o estructura.
   */

  if (originalChar === compareChar) {
    if (types.includes("(") && types.includes(")")) {
      console.log("ERROR: formato incorrecto para estructura");
    } else {
      /**
       * En esta seccion debemos definir los valores internos
       * de la estructura para saber si son atomos o variables
       */
      arrayOfVariables.push(types);
    }
  } else {
    /**
     * Si la primera letra es minuscula,
     * puede ser un atomico o una estructura.
     * para descartar si es una estructura, verificamos
     * que contenga ( y ).
     */
    if (validParenthesis(types.join(" "))) {
      arrayOfStructs.push(types[0]);
    } else {
      console.log("ERROR: el formato ingresado se encuentra mal parentizado");
    }
  }
};

/**
 * Funcion para verificar si una estructura
 * esta bien parentizada.
 */

const validParenthesis = (expression) => {
  let stack = [];

  const characters = expression.split("");
  for (let i = 0; i < characters.length; i++) {
    if (characters[i] == "(") {
      stack.push(characters[i]);
    } else if (characters[i] == ")") {
      let last = stack.pop();
      if (last !== "(") {
        return false;
      }
    }
  }

  if (stack.length > 0) {
    return false;
  } else {
    return true;
  }
};
