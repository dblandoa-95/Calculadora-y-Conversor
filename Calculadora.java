import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// extends JFrame: la clase ES una ventana. implements ActionListener: puede "escuchar" clics.
public class Calculadora extends JFrame implements ActionListener {

    private final double TASA = 3200; // tasa fija del taller (final = no cambia)

    
    private JTextField txtNum1 = new JTextField(), txtNum2 = new JTextField();
    private JTextField txtGrados = new JTextField(), txtValor = new JTextField();

    // B
    private JButton btnSumar = new JButton("Sumar"), btnRestar = new JButton("Restar");
    private JButton btnMultiplicar = new JButton("Multiplicar"), btnDividir = new JButton("Dividir");
    private JButton btnCaF = new JButton("°C a °F"), btnFaC = new JButton("°F a °C");
    private JButton btnUsdCop = new JButton("USD a COP"), btnCopUsd = new JButton("COP a USD");

    
    private JLabel lblResultado = new JLabel(""); //etiq

    public Calculadora() {
        setTitle("Calculadora y Conversor");
        setSize(450, 480);
        setLayout(new GridLayout(12, 2, 5, 5));  // tabla de 12 filas y 2 columnas
        setLocationRelativeTo(null);             // ctv
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // cerrar 

        // add() agrega los componentes en orden: de izquierda a derecha, fila por fila
        add(new JLabel("OPERACIONES MATEMÁTICAS")); add(new JLabel(""));
        add(new JLabel("Número 1:"));               add(txtNum1);
        add(new JLabel("Número 2:"));               add(txtNum2);
        add(btnSumar);                              add(btnRestar);
        add(btnMultiplicar);                        add(btnDividir);

        add(new JLabel("CONVERSIÓN DE TEMPERATURA")); add(new JLabel(""));
        add(new JLabel("Grados:"));                   add(txtGrados);
        add(btnCaF);                                  add(btnFaC);

        add(new JLabel("CONVERSIÓN DE MONEDA ($3200)")); add(new JLabel(""));
        add(new JLabel("Valor:"));                       add(txtValor);
        add(btnUsdCop);                                  add(btnCopUsd);

        add(new JLabel("Resultado:"));                   add(lblResultado);

        // "this" = esta clase. Así cada botón le avisa a actionPerformed cuando lo presionan
        btnSumar.addActionListener(this);
        btnRestar.addActionListener(this);
        btnMultiplicar.addActionListener(this);
        btnDividir.addActionListener(this);
        btnCaF.addActionListener(this);
        btnFaC.addActionListener(this);
        btnUsdCop.addActionListener(this);
        btnCopUsd.addActionListener(this);
    }

    // Se ejecuta solo cada vez que se presiona un botón
    public void actionPerformed(ActionEvent e) {
        Object b = e.getSource();   // cuál botón fue presionado
        double r;                   // aquí guardamos el resultado
        String unidad = "";         // texto que se muestra después del número

        try {
            if (b == btnSumar || b == btnRestar || b == btnMultiplicar || b == btnDividir) {
                
                double n1 = Double.parseDouble(txtNum1.getText().trim());
                double n2 = Double.parseDouble(txtNum2.getText().trim());
                if (b == btnSumar) {
                    r = n1 + n2;
                } else if (b == btnRestar) {
                    r = n1 - n2;
                } else if (b == btnMultiplicar) {
                    r = n1 * n2;
                } else {
                    if (n2 == 0) {
                        JOptionPane.showMessageDialog(this, "Error: no se puede dividir por cero.");
                        return;     // sale del método sin mostrar resultado
                    }
                    r = n1 / n2;
                }
            } else if (b == btnCaF || b == btnFaC) {
                double g = Double.parseDouble(txtGrados.getText().trim());
                if (b == btnCaF) {
                    r = g * 9 / 5 + 32;
                    unidad = " °F";
                } else {
                    r = (g - 32) * 5 / 9;
                    unidad = " °C";
                }
            } else {
                double v = Double.parseDouble(txtValor.getText().trim());
                if (b == btnUsdCop) {
                    r = v * TASA;
                    unidad = " COP";
                } else {
                    r = v / TASA;
                    unidad = " USD";
                }
            }
            // %.2f = mostrar el número con 2 decimales
            lblResultado.setText(String.format("%.2f", r) + unidad);

        } catch (NumberFormatException ex) {
            // Llegamos aquí si el campo estaba vacío o tenía letras/símbolos
            JOptionPane.showMessageDialog(this, "Error: escriba solo números y no deje campos vacíos.");
        }
    }

    
    public static void main(String[] args) {
        new Calculadora().setVisible(true);   // crea la ventana y la muestra
    }
}