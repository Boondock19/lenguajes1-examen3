/**
 * @classdesc Representa a clases que heredan de una super clase.
 */
export class SimpleClass {
  constructor(name, methods, father) {
    (this.name = name), (this.methods = methods), (this.father = father);
  }

  getName() {
    return this.name;
  }

  getMethods() {
    return this.methods;
  }

  getFather() {
    return this.father;
  }

  getParms() {
    return {
      name: this.name,
      methods: this.methods,
      father: this.father,
    };
  }

  /**
   * Funcion que se invoca para imprimir los metodos
   */
  describrir(metodosPadre) {
    /**
     * los que tengan en comun deberan pertenecer a this.name
     * y los que no perteneceran a this.father.
     */

    metodosPadre.forEach((element) => {
      if (!this.methods.includes(element)) {
        console.log(`${element} -> ${this.father} :: ${element}`);
      }
    });
    this.methods.forEach((element) => {
      console.log(`${element} -> ${this.name} :: ${element}`);
    });
  }
}

/**
 * @classdesc Representa a clases de tipo super clase.
 */
export class SuperClass {
  constructor(name, methods) {
    (this.name = name), (this.methods = methods);
  }

  getName() {
    return this.name;
  }

  getMethods() {
    return this.methods;
  }

  getParms() {
    return {
      name: this.name,
      methods: this.methods,
    };
  }

  /**
   * Funcion que se invoca para imprimir los metodos
   */
  describrir() {
    this.methods.forEach((element) => {
      console.log(`${element} -> ${this.name} :: ${element}`);
    });
  }
}
