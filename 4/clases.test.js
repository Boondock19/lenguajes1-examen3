

import { findDuplicates, defType,arrayOfClasses,arrayOfSuperClass} from "./clases.js";
import { SimpleClass, SuperClass } from "./typesClasses.js";

Describre("Test de funciones de clases.js", () => {

    Describe("Creando clases simples y super", () => {
        test('Creando una super clase', () => {
            const sampleSimpleClass = new SimpleClass("A", ["f", "l"]);
            
            const entrada = 'CLASS A f l'
            const dataString = entrada.toString();
            const dataArray = dataString.split(" ");
            defType(dataArray)

        })
    })
})