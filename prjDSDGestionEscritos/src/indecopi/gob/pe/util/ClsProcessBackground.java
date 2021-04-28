package indecopi.gob.pe.util;


public class ClsProcessBackground extends Thread {
  
  private static ClsProcessBackground instance;

      static synchronized ClsProcessBackground getInstance(String name) {
          if (instance == null) {
              instance = new ClsProcessBackground(name);
          }
          return instance;
      }

      public ClsProcessBackground(String name) {
          super(name);
      }

      @Override
      public void run() {
          System.out.println("Inicio de " + Thread.currentThread().getName());
          motodoSincronizado();
      }

      private synchronized void motodoSincronizado() {
          System.out.println("Ejecucion");
      }
}
