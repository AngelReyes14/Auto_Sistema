
package interfaz;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class datosTabla extends JPanel {
	  private CardLayout cardLayout;
	  private int numeroColumnas;
    public datosTabla(CardLayout cardLayout, int numeroColumnas, String selectedDatabaseName) {
    	this.numeroColumnas = numeroColumnas;
    	 this.cardLayout = cardLayout;
    	setBackground(Color.gray);
    	setLayout(null);
    	
        JButton crear = new JButton("Crear");
        crear.setBounds(200, 480, 80, 30);
        JButton regresar = new JButton("Regresar");
        regresar.setBounds(300, 480, 100, 30);

        regresar.addActionListener(new ActionListener() {
			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				cardLayout.show(getParent(), "Panel4");
    				
    			}
    		});
 
        JLabel[] nombreAtributoLabels = new JLabel[numeroColumnas];
        JTextField[] nombreAtributoTextFields = new JTextField[numeroColumnas];
        JLabel[] tipoDatoLabels = new JLabel[numeroColumnas];
        JTextField[] tipoDatoTextFields = new JTextField[numeroColumnas];
        JLabel[] tamanoLabels = new JLabel[numeroColumnas];
        JLabel[] pk = new JLabel[numeroColumnas];
        JLabel[] fk = new JLabel[numeroColumnas];
        JLabel[] nl = new JLabel[numeroColumnas];
        JSpinner[] tamanoSpinners = new JSpinner[numeroColumnas];
        JCheckBox[] checkBoxPK = new JCheckBox[numeroColumnas];
        JCheckBox[] checkBoxFK = new JCheckBox[numeroColumnas];
        JCheckBox[] checkBoxNL = new JCheckBox[numeroColumnas];

        for (int i = 0; i < numeroColumnas; i++) {
            nombreAtributoLabels[i] = new JLabel("Atributo");
            nombreAtributoLabels[i].setBounds(10, 50 + i * 0, 100, 20);

            nombreAtributoTextFields[i] = new JTextField(140);
            nombreAtributoTextFields[i].setBounds(10, 70 + i * 30, 120, 20);

            tipoDatoLabels[i] = new JLabel("Tipo de dato");
            tipoDatoLabels[i].setBounds(150, 50 + i * 0, 100, 20);

            pk[i] = new JLabel("PK");
            pk[i].setBounds(390, 50 + i * 0, 100, 20);
            
            fk[i] = new JLabel("FK");
            fk[i].setBounds(460, 50 + i * 0, 100, 20);
            
            nl[i] = new JLabel("Null");
            nl[i].setBounds(530, 50 + i * 0, 100, 20);
            
            tipoDatoTextFields[i] = new JTextField(100);
            tipoDatoTextFields[i].setBounds(150, 70 + i * 30, 120, 20);

            tamanoLabels[i] = new JLabel("Long");
            tamanoLabels[i].setBounds(300, 50 + i * 0, 100, 20);

            SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
            tamanoSpinners[i] = new JSpinner(spinnerModel);
            tamanoSpinners[i].setBounds(300, 70 + i * 30, 70, 20);

            checkBoxPK[i] = new JCheckBox("");
            checkBoxPK[i].setBounds(390, 70 + i * 30, 20, 20);

            checkBoxFK[i] = new JCheckBox("");
            checkBoxFK[i].setBounds(460, 70 + i * 30, 20, 20);

            checkBoxNL[i] = new JCheckBox("");
            checkBoxNL[i].setBounds(530, 70 + i * 30, 20, 20);

            // Agregar los componentes al JPanel
            add(nombreAtributoLabels[i]);
            add(nombreAtributoTextFields[i]);
            add(tipoDatoLabels[i]);
            add(tipoDatoTextFields[i]);
            add(tamanoLabels[i]);
            add(tamanoSpinners[i]);
            add(checkBoxPK[i]);
            add(checkBoxFK[i]);
            add(checkBoxNL[i]);
            add(pk[i]);
            add(fk[i]);
            add(nl[i]);
        }
    	add(crear);
    	add(regresar);
    	
    	
          }
}
