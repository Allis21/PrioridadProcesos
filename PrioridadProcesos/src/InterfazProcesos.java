import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.PriorityQueue;
import java.util.Random;

public class InterfazProcesos extends JFrame {
    private GestorProcesos gestor;
    private JTextArea areaProcesos;

    public InterfazProcesos() {
        gestor = new GestorProcesos();
        initUI();
    }

    private void initUI() {
        setTitle("Simulación de Procesos por Prioridad");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel para agregar procesos
        JPanel panelAgregar = new JPanel(new GridLayout(4, 2, 5, 5));
        JLabel etiquetaNombre = new JLabel("Nombre del Proceso:");
        JTextField campoNombre = new JTextField();
        JLabel etiquetaPrioridad = new JLabel("Prioridad (menor número es mayor prioridad):");
        JTextField campoPrioridad = new JTextField();
        JLabel etiquetaTiempo = new JLabel("Tiempo de Ejecución (ms):");
        JTextField campoTiempo = new JTextField();
        JButton botonAgregar = new JButton("Agregar Proceso");
        botonAgregar.setBackground(new Color(52, 152, 219)); // Azul
        botonAgregar.setForeground(Color.WHITE);

        panelAgregar.add(etiquetaNombre);
        panelAgregar.add(campoNombre);
        panelAgregar.add(etiquetaPrioridad);
        panelAgregar.add(campoPrioridad);
        panelAgregar.add(etiquetaTiempo);
        panelAgregar.add(campoTiempo);
        panelAgregar.add(botonAgregar);

        // Area para mostrar los procesos agregados
        areaProcesos = new JTextArea();
        areaProcesos.setEditable(false);
        areaProcesos.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollProcesos = new JScrollPane(areaProcesos);

        // Botón para ejecutar los procesos
        JButton botonEjecutar = new JButton("Ejecutar Procesos");
        botonEjecutar.setBackground(new Color(46, 204, 113)); // Verde
        botonEjecutar.setForeground(Color.WHITE);

        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = campoNombre.getText();
                int prioridad = Integer.parseInt(campoPrioridad.getText());
                int tiempo = Integer.parseInt(campoTiempo.getText());

                Proceso proceso = new Proceso(nombre, prioridad, tiempo);
                gestor.agregarProceso(proceso);

                // Mostrar los procesos agregados
                mostrarProcesosAgregados();
            }
        });

        botonEjecutar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaEjecucion();
            }
        });

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.add(botonEjecutar);

        add(panelAgregar, BorderLayout.NORTH);
        add(scrollProcesos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void mostrarProcesosAgregados() {
        PriorityQueue<Proceso> cola = gestor.getColaDeProcesos();
        StringBuilder sb = new StringBuilder();
        sb.append("Procesos agregados:\n");

        for (Proceso p : cola) {
            sb.append("Nombre: ").append(p.getNombre())
                    .append(", Prioridad: ").append(p.getPrioridad())
                    .append(", Tiempo: ").append(p.getTiempoEjecucion()).append(" ms\n");
        }

        areaProcesos.setText(sb.toString());
    }

    private void mostrarVentanaEjecucion() {
        JFrame ventanaEjecucion = new JFrame("Ejecución de Procesos");
        ventanaEjecucion.setSize(800, 600);
        ventanaEjecucion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelEjecucion = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarProcesos(g);
            }
        };

        panelEjecucion.setPreferredSize(new Dimension(800, 600));
        ventanaEjecucion.add(panelEjecucion);
        ventanaEjecucion.setVisible(true);
    }

    // Dibuja los procesos en la interfaz gráfica
    private void dibujarProcesos(Graphics g) {
        PriorityQueue<Proceso> cola = new PriorityQueue<>(gestor.getColaDeProcesos());

        int x = 50;  // Posición inicial en x
        int y = 50;  // Posición inicial en y
        int alturaBarra = 40;  // Altura fija de cada barra
        int separacion = 20;   // Separación entre las barras
        int escalaDuracion = 10;  // Escala de duración para hacer más grandes las barras

        Random random = new Random();

        while (!cola.isEmpty()) {
            Proceso proceso = cola.poll();
            int anchoBarra = proceso.getTiempoEjecucion() * escalaDuracion; // Escalamos la duración

            // Color aleatorio para cada proceso
            Color colorProceso = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            g.setColor(colorProceso);
            g.fillRect(x, y, anchoBarra, alturaBarra);

            // Dibujar el nombre del proceso y tiempo sobre la barra
            g.setColor(Color.BLACK);
            g.drawString(proceso.getNombre() + " (" + proceso.getTiempoEjecucion() + " ms)", x + 5, y + 25);

            y += alturaBarra + separacion; // Moverse a la siguiente fila
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfazProcesos interfaz = new InterfazProcesos();
            interfaz.setVisible(true);
        });
    }
}
