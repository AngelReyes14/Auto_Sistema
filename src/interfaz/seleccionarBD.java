

package interfaz;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class seleccionarBD extends JPanel{
  public seleccionarBD(){
  setBackground(Color.white);
   setLayout(null);
        JLabel nombreBDlabel = new JLabel("Nombre de la base de datos: ");
        JTextField nombreBDtextField = new JTextField(30);
        JLabel charsetlabel = new JLabel("Char_set: ");
        nombreBDlabel.setBounds(10, 10, 200, 20); // Establece la posición y el tamaño de nombreBDlabel
        nombreBDtextField.setBounds(200, 10, 250, 20);
        charsetlabel.setBounds(10, 40, 100, 20);
     // Crear un arreglo de elementos para el JComboBox
        String[] opciones = {"utf8_general_ci", "utf8mb4_general_ci", "latin1_swedish_ci", "utf8_bin","utf8mb4_bin","utf8_unicode_ci","utf8mb4_unicode_ci","latin1_bin"};
        // Crear un JComboBox y agregar las opciones
        JComboBox<String> comboBox = new JComboBox<>(opciones);
        comboBox.setBounds(70,40,200,20);
        JButton crearButton = new JButton("Crear");
        crearButton.setBounds(250, 150, 100, 30);

        add(nombreBDlabel);
        add(nombreBDtextField);
        add(charsetlabel);
        add(comboBox);
        add(crearButton);
  
  
  
  }
        
}
