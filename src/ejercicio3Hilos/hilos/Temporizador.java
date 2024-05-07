    package ejercicio3Hilos.hilos;

    import ejercicio3Hilos.datos.Contador;

    public class Temporizador extends Thread{
        private static final int TIEMPO_LIMITE = 60;
        private Contador c;

        public Temporizador(Contador puntos) {
            this.c = puntos;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(TIEMPO_LIMITE * 100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("PUNTUACIÓN FINAL: " + c.value());
            System.exit(0);
        }
    }
