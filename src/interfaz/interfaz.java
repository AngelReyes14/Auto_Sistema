package interfaz;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import javax.swing.ImageIcon;
import Conector.Conexion;
public class interfaz extends JFrame {
    private JPanel panelIzquierdo;
    private JPanel panelDerecho;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private CardLayout cardLayout;

    public interfaz() {
    	         cardLayout = new CardLayout(); // Inicializa cardLayout

        setTitle("Mi propio gestor de BD");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un JMenuBar
        JMenuBar menuBar = new JMenuBar();

        // Crear un JMenu
        JMenu menu = new JMenu("Menú");

        // Crear JMenuItem (opciones del menú)
        JMenuItem menuItem1 = new JMenuItem("Inicio");
        JMenuItem menuItem2 = new JMenuItem("Conexión BD");
        JMenuItem menuItem3 = new JMenuItem("Crear BD");
        JMenuItem menuItem4 = new JMenuItem("Crear tabla");
        JMenuItem menuItem5 = new JMenuItem("Descripcion Tablas");

        // Agregar ActionListener para cambiar entre paneles al hacer clic en las opciones del menú
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelDerecho, "Panel1");
            }
        });



        menuItem3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelDerecho, "Panel3");
			}
		});
        
        menuItem4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelDerecho, "Panel4");
			}
		});
        
        
        menu.add(menuItem1);
        menu.add(menuItem2);
        menuBar.add(menu);
        menu.add(menuItem3);
        menu.add(menuItem4);
        menu.add(menuItem5);

        setJMenuBar(menuBar);

        // Crear el panel izquierdo y el panel derecho
        panelIzquierdo = new JPanel();
        getContentPane().add(panelIzquierdo, BorderLayout.WEST);
        panelIzquierdo.setBackground(Color.GREEN); // Color del panel izquierdo
        panelIzquierdo.setPreferredSize(new Dimension(200, 0)); // Ancho fijo
        JLabel datosBDLabel = new JLabel("Datos de la BD");
        panelIzquierdo.setLayout(new GridLayout(0, 1));
JScrollPane scrollPane = new JScrollPane(panelIzquierdo);
getContentPane().add(scrollPane, BorderLayout.WEST);
        panelIzquierdo.add(datosBDLabel);

        panelDerecho = new JPanel();
        cardLayout = new CardLayout();
        panelDerecho.setLayout(cardLayout);
        
        panel1 = new JPanel();
        panel1.setBackground(Color.YELLOW); // Color del panel 1

        // Cargar la imagen y cambiar su tamaño
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\angel\\Downloads\\_39bb7668-267d-446b-bdd8-b379d2a2a6ff.jpg"); // Reemplaza "ruta_de_la_imagen.png" con la ruta de tu imagen
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(500, 500, Image.SCALE_SMOOTH); // Cambia el tamaño a 200x200
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel imagenLabel = new JLabel(scaledIcon);
        panel1.add(imagenLabel);
        
        
        panel2 = new JPanel(); // Agregar esta línea
        panel2.setLayout(new BorderLayout()); // Agregar esta línea

        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conexionBD conexionPanel = new conexionBD(panelIzquierdo,panelDerecho);
                panel2.add(conexionPanel);
                panelDerecho.add(panel2, "Panel2"); // Agrega panel2 al panelDerecho
                cardLayout.show(panelDerecho, "Panel2");
            }
        });

       panel3 = new JPanel();
       panel3.setLayout(new BorderLayout());
       menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarBD conexionPanel = new seleccionarBD();
                panel3.add(conexionPanel);
                panelDerecho.add(panel3, "Panel2"); // Agrega panel2 al panelDerecho
                cardLayout.show(panelDerecho, "Panel2");
            }
        });
        panel5 = new JPanel();
       panel5.setLayout(new BorderLayout());
       menuItem5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                descripcionTablas conexionPanel = new descripcionTablas();
                panel5.add(conexionPanel);
                panelDerecho.add(panel5, "Panel5"); // Agrega panel2 al panelDerecho
                cardLayout.show(panelDerecho, "Panel5");
            }
        });
       
       
        panel4 = new JPanel();
        panel4.setBackground(Color.CYAN);
        panel4.setLayout(null);
        JLabel nombretablalabel = new JLabel("Nombre de la tabla:");
        nombretablalabel.setBounds(30, 10, 200, 50);
        JTextField nombretablatextField = new JTextField(30);
        nombretablatextField.setBounds(30, 50, 200, 30);
        JLabel columnalabel = new JLabel("Columnas");
        columnalabel.setBounds(30, 100, 100, 20);
        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
        JSpinner spinner = new JSpinner(spinnerModel);
        spinner.setBounds(30, 120, 70, 20);
        JButton botoncrear = new JButton("Crear");
        botoncrear.setBounds(250, 250, 100, 30);
        
        
        botoncrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int numeroColumnas = (int) spinner.getValue(); // Obtén el valor del JSp

                // Mostrar la ventana "datosTabla"
                cardLayout.show(panelDerecho, "DatosTabla");
            }
        });

        // Agrega el botón a tu JFrame principal u otro contenedor
        getContentPane().add(botoncrear);
    
        panel4.add(nombretablalabel);
        panel4.add(nombretablatextField);
        panel4.add(columnalabel);
        panel4.add(spinner);
        panel4.add(botoncrear);
     
       
 

        // Agregar el panel izquierdo y el panel derecho al contenido del JFrame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelIzquierdo, BorderLayout.WEST);
        getContentPane().add(panelDerecho, BorderLayout.CENTER);

        // Agregar los paneles al CardLayout
        panelDerecho.add(panel1, "Panel1");
        panelDerecho.add(panel2, "Panel2");
        panelDerecho.add(panel3, "Panel3");
        panelDerecho.add(panel4, "Panel4");
        
       
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                interfaz frame = new interfaz();
                frame.setVisible(true);
            }
        });
    }
}
