import java.util.PriorityQueue;

public class GestorProcesos {
    private PriorityQueue<Proceso> colaDeProcesos;

    public GestorProcesos() {
        colaDeProcesos = new PriorityQueue<>();
    }

    public void agregarProceso(Proceso proceso) {
        colaDeProcesos.add(proceso);
    }

    public PriorityQueue<Proceso> getColaDeProcesos() {
        return colaDeProcesos;
    }
}
