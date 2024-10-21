public class Proceso implements Comparable<Proceso> {
    private String nombre;
    private int prioridad;
    private int tiempoEjecucion;

    public Proceso(String nombre, int prioridad, int tiempoEjecucion) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.tiempoEjecucion = tiempoEjecucion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public int getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    @Override
    public int compareTo(Proceso otroProceso) {
        return Integer.compare(this.prioridad, otroProceso.getPrioridad());
    }

    @Override
    public String toString() {
        return "Proceso: " + nombre + " | Prioridad: " + prioridad + " | Tiempo de ejecución: " + tiempoEjecucion + "ms";
    }
}
