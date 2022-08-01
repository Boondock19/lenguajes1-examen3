
/* 
    Clase que representa el producto punto de dos vectores
    a su vez implementar la interfaz Runnable de Java
    que permite el uso de threads para poder realizar el calculo
    de manera concurrente.
*/
public class ProductoPunto implements Runnable {
    
    /**
        La clase necesita como parametros de entrada los 3 arreglos
        mas un entero que presenta el indice de la posicion en el arreglo
        para poder realizar los calculos de manera correcta, soportando
        concurrencia.
     */
    private int i;
    private int[] a, b, c;
    public ProductoPunto(int[] a, int[] b, int[] c, int i) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.i = i;
    }

    /**
        Metodo que es ejecutado al crear un threard y aplicar la funcion start()
     */
    public void run() {
        c[i] = a[i] * b[i];
    }

    public void multiplicar(int[] a, int[] b) throws InterruptedException {
        Thread[] threads = new Thread[3];
        for (int i = 0; i < 3; i++) {
            // Creando un nuevo thread con la clase ProductoPunto
            threads[i] = new Thread (new ProductoPunto(a, b, c, i));
            // Iniciamos el thread para que ejecute la funcion run()
            threads[i].start();
        }

        for (int i = 0; i < 3; i++) {
            // Esperamos a que el thread termine de ejecutar la funcion run()
            threads[i].join();
        }
        
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            // Sumamos los resultados de las multiplicaciones
            sum += c[i];        
        }

        System.out.println(sum);
    }
}