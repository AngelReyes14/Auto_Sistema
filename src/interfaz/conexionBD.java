
package interfaz;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Conector.Conexion;
import Tablas.Tablas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;


public class conexionBD extends JPanel {
private CardLayout cardLayout;
private String selectedDatabaseName;
private Connection selectedDatabaseConnection;
    private JPanel cardPanel;
private JPopupMenu popupMenu;
    private JMenuItem agregarTablaItem;
    private JTextField puertoTextField;
    private JTextField hostTextField;
    private JTextField usuarioTextField;
    private JPasswordField contrasenaField;
      private JPanel panelIzquierdo;
    DefaultListModel<String> databaseListModel; // Modelo para la lista de bases de datos
    JList<String> databaseList;
    String host;
    String puerto;
    String usuario;
    String passw;
    String base;
    
    JTextArea displayArea = new JTextArea(20,40);
    
    public conexionBD(JPanel panelIzquierdo, JPanel panelDerecho) {

        host="";
        puerto="";
        usuario="";
        passw="";
        setBackground(Color.gray);
        setLayout(null);
         this.panelIzquierdo = panelIzquierdo;
         
        // Crea un JScrollPane para la lista (permite desplazarse si hay muchas bases de datos)
        JScrollPane scrollPane = new JScrollPane(databaseList);

        // Agrega la lista al panel izquierdo
        add(scrollPane);

        JLabel puertoLabel = new JLabel("Puerto:");
        puertoLabel.setBounds(10, 50, 200, 10);
        puertoTextField = new JTextField(10);
        puertoTextField.setBounds(10, 70, 100, 20);

        JLabel hostLabel = new JLabel("Host:");
        hostLabel.setBounds(10, 95, 100, 10);
        hostTextField = new JTextField(10);
        hostTextField.setBounds(10, 110, 100, 20);

        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioLabel.setBounds(10, 140, 200, 10);
        usuarioTextField = new JTextField(10);
        usuarioTextField.setBounds(10, 160, 100, 20);

        JLabel contrasenaLabel = new JLabel("Contraseña:");
        contrasenaLabel.setBounds(10, 190, 100, 10);
        contrasenaField = new JPasswordField(10);
        contrasenaField.setBounds(10, 210, 100, 20);

        JButton testButton = new JButton("Test");
        testButton.setBounds(200, 320, 200, 50);

        JButton conectarButton = new JButton("Conectar");
        conectarButton.setBounds(200, 400, 200, 50);
        
        
        // Crear un menú contextual
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem agregarTablaItem = new JMenuItem("Agregar tabla");
            this.popupMenu = popupMenu;


agregarTablaItem.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Aquí debes agregar la lógica para agregar una tabla a la base de datos seleccionada

        // Crea un nuevo JPanel para mostrar la tabla o contenido adicional
        JPanel nuevoPanel = new JPanel();
        nuevoPanel.setBackground(Color.WHITE); // Establece el color de fondo según tus preferencias
        nuevoPanel.setLayout(new BorderLayout());

        // Puedes agregar componentes a este nuevo panel, como etiquetas, campos de texto, etc.
        JLabel label = new JLabel("Contenido de la nueva tabla");
        nuevoPanel.add(label, BorderLayout.CENTER);
        JButton btnConfirmar = new JButton("Confirmar");
    btnConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
//                // Crear una lista de atributos
//                List<Atributo> atributos = new ArrayList<>();
//
//                for (int i = 0; i < numeroDeAtributos; i++) {
//                    String nombreAtributo = txtNombreAtributo[i].getText(); // Obtén el nombre del atributo
//                    String tipoDato = (String) cboTipoDato[i].getSelectedItem(); // Obtén el tipo de dato
//                    int longitud = (int) spinnerLongitud[i].getValue(); // Obtén la longitud
//                    boolean llavePrimaria = chkLlavePrimaria[i].isSelected(); // Obtén si es llave primaria
//                    boolean notNull = chkNotNull[i].isSelected(); // Obtén si es NOT NULL
//
//                    // Crea un objeto Atributo y agrégalo a la lista
//                    Atributo atributo = new AtributoImpl(nombreAtributo, tipoDato, longitud, llavePrimaria, notNull);
//                    atributos.add(atributo);
//                }
//
//                // Llama al método de tu librería para crear la tabla
//                 // Crea una instancia de tu DatabaseManager
//                try {
//                	miLib.cambiarBaseDeDatos(bdSelected);
//                    miLib.crearTabla(nombreTabla, atributos);
//                    JOptionPane.showMessageDialog(null,"Tabla creada exitosamente");
//                    panelDerecho.removeAll();
//                    frame.revalidate();
//                    frame.repaint();
//                    // Código a ejecutar después de crear la tabla exitosamente
//                } catch (SQLException ex) {
//                    ex.printStackTrace(); // Imprimir la traza de la excepción (puedes manejarla de otra manera)
//                    JOptionPane.showMessageDialog(null, "Error al crear la tabla: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                }
//
//            }
//        });
    

        // Borra el contenido anterior del panelDerecho y agrega el nuevo panel
        panelDerecho.removeAll();
        panelDerecho.add(nuevoPanel, "TablaNueva"); // Añade el nuevo panel con un nombre, por ejemplo, "TablaNueva"
        panelDerecho.revalidate();
        panelDerecho.repaint();
    }
});



        // ...
    
        



conectarButton.addActionListener((ActionEvent e) -> {
                    setHost(hostTextField.getText());
                    setPuerto(puertoTextField.getText());
                    setUsuario(usuarioTextField.getText());
                    setContrasena(contrasenaField.getText());
                    // Verifica que los campos no estén vacíos
                    if (host.isEmpty() || puerto.isEmpty() || usuario.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
                        return;  // No intentes conectarte si faltan datos
                    } else{
                        initDatabaseConnection();
                    }
                    
                    // Intenta establecer la conexión
                    Conexion con = new Conexion();
                    con.connect(host, puerto, usuario, passw);
                });


        add(puertoLabel);
        add(puertoTextField);
        add(hostLabel);
        add(hostTextField);
        add(usuarioLabel);
        add(usuarioTextField);
        add(contrasenaLabel);
        add(contrasenaField);
        add(testButton);
        add(conectarButton);
        popupMenu.add(agregarTablaItem);

        // Agregar un MouseListener para mostrar el menú contextual en clic derecho
        

        add(popupMenu);
    
    }
 private void initDatabaseConnection() {
    try {
        // Establece la conexión con el servidor MySQL
        Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + puerto + "/", usuario, passw);
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet databases = metaData.getCatalogs();

        // Limpia el contenido anterior en el panel izquierdo
        panelIzquierdo.removeAll();
        panelIzquierdo.setLayout(new GridLayout(0, 1));

        displayArea.setText("");
        displayArea.append("Bases de Datos:\n");

        while (databases.next()) {
            String dbName = databases.getString("TABLE_CAT");
            JButton dbButton = new JButton(dbName);
            dbButton.addActionListener(e -> {
                displayArea.setText("");
                JOptionPane.showMessageDialog(null, "Base de datos seleccionada: " + dbName + "\n");
                displayTablesOfDatabase(dbName);
                selectedDatabaseName = dbName;  // Almacena el nombre de la base de datos seleccionada
                selectedDatabaseConnection = establishDatabaseConnection(dbName);  // Establece la conexión con la base de datos
                System.out.println(selectedDatabaseConnection);
                Conexion con = new Conexion();
                con.setConnection(selectedDatabaseConnection);
            });
            dbButton.addActionListener(e -> describeDatabase());
                   dbButton.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.isPopupTrigger()) {
                // Mostrar el menú contextual en el clic derecho
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    });
            

            
            panelIzquierdo.add(dbButton);
        }

        // Cierra la conexión con el servidor MySQL
        connection.close();

        panelIzquierdo.revalidate();
        panelIzquierdo.repaint();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
private void describeDatabase() {
    try {
        if (selectedDatabaseConnection != null) {
            DatabaseMetaData metaData = selectedDatabaseConnection.getMetaData();

            // Obtener el nombre de la base de datos seleccionada
            String dbName = selectedDatabaseName;

            displayArea.setText("Descripción de la base de datos " + dbName + ":\n");

            // Obtener tablas de la base de datos
            ResultSet tables = metaData.getTables(dbName, null, "%", new String[] { "TABLE" });
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                displayArea.append("\nTabla: " + tableName + "\n");

                // Obtener columnas de la tabla
                ResultSet columns = metaData.getColumns(dbName, null, tableName, "%");
                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    String columnType = columns.getString("TYPE_NAME");
                    displayArea.append("  Columna: " + columnName + " - Tipo: " + columnType + "\n");
                }
                displayArea.append("\n");
            }
        } else {
            displayArea.setText("No se ha seleccionado una base de datos.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private JButton createBackButton() {
    JButton backButton = new JButton("Volver al listado de bases");
    backButton.addActionListener(e -> displayDatabaseList());
    return backButton;
}
private void displayTablesOfDatabase(String dbName) {
    try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + puerto + "/" + dbName, usuario, passw);
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(dbName, null, "%", new String[] { "TABLE" });

        JPanel tablesPanel = new JPanel();
        tablesPanel.setLayout(new GridLayout(0, 1));

        displayArea.append("Tablas en la base de datos " + dbName + ":\n");
        while (tables.next()) {
            String tableName = tables.getString("TABLE_NAME");
            JLabel tableLabel = new JLabel(tableName);
            tablesPanel.add(tableLabel);
        }

        // Agrega el botón "Volver al listado de bases"
        JButton backButton = createBackButton();
        tablesPanel.add(backButton);

        // Limpia el displayArea y agrega el panel con los nombres de las tablas
        displayArea.removeAll();  
        displayArea.setLayout(new BorderLayout());
        displayArea.add(new JScrollPane(tablesPanel), BorderLayout.CENTER);
        displayArea.revalidate(); 

        // Cierra la conexión
        connection.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private void displayDatabaseList() {
    try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + puerto + "/", usuario, passw);
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet databases = metaData.getCatalogs();

        JPanel databaseButtonsPanel = new JPanel();
        databaseButtonsPanel.setLayout(new GridLayout(0, 1));

        displayArea.removeAll();  // Limpia el displayArea
        displayArea.setLayout(new BorderLayout());
        displayArea.add(new JScrollPane(databaseButtonsPanel), BorderLayout.CENTER);

        while (databases.next()) {
            String dbName = databases.getString("TABLE_CAT");
            JButton dbButton = new JButton(dbName);
            dbButton.addActionListener(e -> {
                displayArea.setText("");  // Limpiar el contenido anterior
                displayArea.append("Base de datos seleccionada: " + dbName + "\n");
                displayTablesOfDatabase(dbName);
            });
            databaseButtonsPanel.add(dbButton);
        }

        // Agrega el botón "Volver al listado de bases"
        JButton backButton = createBackButton();
        databaseButtonsPanel.add(backButton);

        // Cierra la conexión
        connection.close();

        // Repinta la ventana para reflejar los cambios
        displayArea.revalidate();  

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
private Connection establishDatabaseConnection(String databaseName) {
    Connection connection = null;
    try {
        // Establece la conexión con la base de datos seleccionada
        connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + puerto + "/" + databaseName, usuario, passw);
        // Aquí puedes realizar configuraciones adicionales de la conexión si es necesario
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return connection;
}

    public void setHost(String host) {
        this.host = host;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasena(String contrasena) {
        this.passw = contrasena;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }
    public void setBase(String base) {
        this.base = base;
    }

    public String getPuerto() {
        return puerto;
    }

    public String getContrasena() {
        return passw;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getHost() {
        return host;
    }
    public String getBase() {
        return base;
    }
 private void mostrarInfoBaseDatos(String nombreBD) {
    // Aquí puedes agregar la lógica para mostrar información sobre la base de datos
    JOptionPane.showMessageDialog(null, "Información de la base de datos: " + nombreBD);
}
 
private void mostrarInfoTabla(String nombreTabla) {
    try {
        // Crear una consulta SQL
        String consulta = "SELECT * FROM " + nombreTabla;

        // Crear una sentencia SQL y ejecutarla
        Statement statement = selectedDatabaseConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(consulta);

        // Procesar los resultados y mostrarlos
        while (resultSet.next()) {
            // Puedes acceder a los datos de cada fila utilizando los métodos getXXX, por ejemplo:
            int id = resultSet.getInt("id");
            String nombre = resultSet.getString("nombre");

            // Haz algo con los datos, como mostrarlos en un JTextArea o en una ventana de diálogo
            displayArea.append("ID: " + id + ", Nombre: " + nombre + "\n");
        }

        // Cierra la conexión
        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

   


}



