
package interfaz;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class descripcionTablas extends JPanel{
    public descripcionTablas(){
        setBackground(Color.red);
        setLayout(null);
        JLabel nombre = new JLabel("Nombre tabla");
        nombre.setBounds(10, 10, 100, 20);
        
        add(nombre);
}
}
