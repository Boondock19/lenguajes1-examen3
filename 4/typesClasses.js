
export class SimpleClass {
    constructor (name,methods,father) {
        this.name = name,
        this.methods = methods,
        this.father = father
    }

    getName() {
        return this.name
    }

    getMethods() {
        return this.methods
    }

    getFather() {
        return this.father
    }

    getParms() {
        return {
            name: this.name,
            methods: this.methods,
            father: this.father
        }
    }
}


export class SuperClass {
    constructor (name,methods) {
        this.name = name,
        this.methods = methods
    }

    getName() {
        return this.name
    }

    getMethods() {
        return this.methods
    }


    getParms() {
        return {
            name: this.name,
            methods: this.methods,
        }
    }
}

