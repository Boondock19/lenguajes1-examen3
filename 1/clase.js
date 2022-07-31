
class Abra {
    constructor(a,b) {
        this.a = a;
        this.b = b;
    }

    cus(x) {
        let a  =  this.b + x 
        return this.pide(a)
    } 

    pide(y) {
        return this.a - y*this.b
    }
}

let abra = new Abra(6,2)

console.log(abra.cus(1))