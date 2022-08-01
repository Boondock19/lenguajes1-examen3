public class Main {

    public static void main(String[] args) throws InterruptedException {
        /**
            Los primeros dos arreglos representan a los vectores que
            sera multiplicados por el producto punto.
            El tercer arreglo sera utilizado como un contendor de los
            resultados de las multiplicaciones para finalmente poder sumarlos.
         */
        int[] vector1 = {7, -4, -1};
        int[] vector2 = {3, -5, 2};
        int[] vector3 = {0, 0, 0};


        ProductoPunto productoPunto = new ProductoPunto(vector1, vector2, vector3, 0);
        productoPunto.multiplicar(vector1, vector2);
    }
}
