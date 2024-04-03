package presentacion;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;

import datos.ModificaDatos;
import datos.Utilidades;

public class Principal{

	public static void main(String[] args) {
		
		miMarco miMarco1=new miMarco();

		miMarco1.setVisible(true);
		
		miMarco1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

class miMarco extends JFrame implements ActionListener{
	
	CardLayout vista;
	Lamina miLamina = new Lamina();
	panelFondo panelfondo = new panelFondo();
	panelCentral panelcentral = new panelCentral();
	panelReparaciones panelreparaciones = new panelReparaciones();
	panelProductos panelproductos = new panelProductos();
	panelClientes panelclientes = new panelClientes();
	JToggleButton botonProductos = new JToggleButton();	
	JToggleButton botonReparaciones = new JToggleButton();
	JToggleButton botonClientes = new JToggleButton();	
	
	public miMarco() {
		setVisible(true);
		setBounds(500,200,700,520);
		setResizable(false);
		setTitle("Logueate!");
		
		add(miLamina);		
	}
	//Usaremos un JToogleButton para acceder a las diferentes pantallas del crud
	
	public miMarco(int ancho, int alto) {
		setVisible(true);
		setBounds(200,100,ancho,alto);
		setResizable(false);
		setTitle("Aplicacion Java");
		setBackground(Color.red);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		vista = (CardLayout) panelcentral.getLayout();
		
		
		botonProductos.setBounds(70, 40, 150, 150);
		ImageIcon productosImg = new ImageIcon("src/presentacion/Images/producto.PNG");
		ImageIcon productosEscala = new ImageIcon(productosImg.getImage().getScaledInstance(130, 100, java.awt.Image.SCALE_DEFAULT));
		botonProductos.setIcon(productosEscala);
		botonProductos.addActionListener(this);
		botonProductos.setActionCommand("botonProductos");
		add(botonProductos);
	
			
		botonReparaciones.setBounds(70, 230, 150, 150);
		ImageIcon reparacionesImg = new ImageIcon("src/presentacion/Images/reparacion.PNG");
		ImageIcon reparacionesEscala = new ImageIcon(reparacionesImg.getImage().getScaledInstance(130, 130, java.awt.Image.SCALE_DEFAULT));

		botonReparaciones.setIcon(reparacionesEscala);
		botonReparaciones.addActionListener(this);
		botonReparaciones.setActionCommand("botonReparaciones");
		add(botonReparaciones);
		
		
		botonClientes.setBounds(70, 420, 150, 150);
		ImageIcon clientesImg = new ImageIcon("src/presentacion/Images/clientes.PNG");
		ImageIcon clientesEscala = new ImageIcon(clientesImg.getImage().getScaledInstance(130, 130, java.awt.Image.SCALE_DEFAULT));
		botonClientes.setIcon(clientesEscala);
		botonClientes.addActionListener(this);
		botonClientes.setActionCommand("botonClientes");
		add(botonClientes);
		
		JButton botonSalir = new JButton();	
		botonSalir.setBounds(80, 620, 130, 130);
		ImageIcon salirImg = new ImageIcon("src/presentacion/Images/exit.PNG");
		ImageIcon salirEscala = new ImageIcon(salirImg.getImage().getScaledInstance(130, 130, java.awt.Image.SCALE_DEFAULT));
		botonSalir.setIcon(salirEscala);
		botonSalir.addActionListener(this);
		botonSalir.setActionCommand("btnSalir");
		add(botonSalir);
		
		
		add(panelcentral);
		add(panelfondo);
				
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if("btnSalir".equals(e.getActionCommand())) {
			int input = JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?",
					null, JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
			if(input==0) {
				System.exit(0);
			}
		}
		
		else if("botonProductos".equals(e.getActionCommand())) {
			if(botonProductos.isSelected()) {
				
				botonClientes.setSelected(false);
				botonReparaciones.setSelected(false);
				panelcentral.add(panelproductos,"productos");
				vista.show(panelcentral, "productos");
				SwingUtilities.updateComponentTreeUI(this);
				this.repaint();
				
			}else {
				
				panelcentral.removeAll();
				SwingUtilities.updateComponentTreeUI(this);
				this.repaint();
			}
			
			
		}
		else if("botonReparaciones".equals(e.getActionCommand())) {
			if(botonReparaciones.isSelected()) {
				
				botonClientes.setSelected(false);
				botonProductos.setSelected(false);
				panelcentral.add(panelreparaciones,"reparaciones");
				vista.show(panelcentral, "reparaciones");
				SwingUtilities.updateComponentTreeUI(this);
				this.repaint();
				
							
			}else {

				panelcentral.removeAll();
				SwingUtilities.updateComponentTreeUI(this);
				this.repaint();
			}
			
		}
		else if("botonClientes".equals(e.getActionCommand())) {
			if(botonClientes.isSelected()) {
				botonReparaciones.setSelected(false);
				botonProductos.setSelected(false);
				panelcentral.add(panelclientes,"clientes");
				vista.show(panelcentral, "clientes");
				SwingUtilities.updateComponentTreeUI(this);
				this.repaint();
			}else {

				panelcentral.removeAll();
				SwingUtilities.updateComponentTreeUI(this);
				this.repaint();
			}
			
		}
	}

}


class Lamina extends JPanel implements ActionListener{
	
	private Image logo;
	private Image imgUser;	
	private Image imgPass;
	Font fuente = new Font("Calibri", 3, 19);
	JTextField inputUser = new JTextField(); 
	JPasswordField inputPass = new JPasswordField();
	private ModificaDatos modifica = new ModificaDatos();
	
	
	public Lamina() {	
		
		setLayout(null);
		
		Font fuente = new Font("Calibri", 3, 19);
		
		inputUser.setFont(fuente);
		
		inputUser.setBounds(235, 215, 230, 42);
		
		inputUser.setBorder(BorderFactory.createTitledBorder("Usuario"));
			
		this.add(inputUser);
		
		inputPass.setBounds(235, 275, 230, 42);
		
		inputPass.setBorder(BorderFactory.createTitledBorder("Password"));
		
		inputPass.setFont(fuente);
				
		this.add(inputPass);	
			
		JButton btnLogin = new JButton("LOGIN");	
		
		btnLogin.setForeground(Color.white);
		btnLogin.setBackground(new Color(240,67,24));
		
		btnLogin.setBounds(275, 340, 150, 50);
		btnLogin.setActionCommand ("btnLogin");
		btnLogin.addActionListener(this);
		
		this.add(btnLogin);
		
		JButton btnLogueo = new JButton("CREAR USUARIO");
		
		btnLogueo.setBounds(275, 400, 150, 50);
		
		btnLogueo.setForeground(Color.white);
		btnLogueo.setBackground(Color.blue);
		btnLogueo.addActionListener(this);
		btnLogueo.setActionCommand ("btnLogueo");
		
		this.add(btnLogueo);
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		Rectangle2D rectanguloExt = new Rectangle2D.Double(0,0,800,800);
		
		g2.draw(rectanguloExt);
		
		g2.setColor(new Color(255,255,255));
		
		g2.fill(rectanguloExt);	
		
		Rectangle2D rectanguloBorde = new Rectangle2D.Double(140,60,420,420);
		
		g2.draw(rectanguloBorde);
		
		g2.setColor(new Color(0,0,0));
		
		g2.fill(rectanguloBorde);	
		
		Rectangle2D rectangulo = new Rectangle2D.Double(0,70,800,600);
		
		g2.draw(rectangulo);
		
		g2.setColor(new Color(230,132,80));
		
		g2.fill(rectangulo);
		
		Rectangle2D rectanguloInt = new Rectangle2D.Double(0,0,800,180);
		
		g2.draw(rectanguloInt);
		
		g2.setColor(new Color(255,255,255));
		
		g2.fill(rectanguloInt);
		
		File rutaLogo = new File ("src/presentacion/Images/coche.PNG");
		
		try {
			logo = ImageIO.read(rutaLogo);
		} catch (IOException e) {
			System.out.println("La imagen no se encuentra");
		}
		
		g.drawImage(logo, 235, 50, null);
		
		File rutaUser = new File ("src/presentacion/Images/user.PNG");		
		
		try {
			imgUser = ImageIO.read(rutaUser);
		} catch (IOException e) {
			System.out.println("La imagen no se encuentra");
		}
		
		g.drawImage(imgUser, 190, 220, null);
		
		File rutaPass = new File ("src/presentacion/Images/pass.PNG");		
		
		try {
			imgPass = ImageIO.read(rutaPass);
		} catch (IOException e) {
			System.out.println("La imagen no se encuentra");
		}
		
		g.drawImage(imgPass, 195, 280, null);
				
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if("btnLogueo".equals(e.getActionCommand())){
			if (!inputUser.getText().equals("") && !inputPass.getPassword().equals("")) {
				
	            String user = inputUser.getText(); 
	            char[] pass = inputPass.getPassword();
	            String password = new String (pass); 
	            String sql= "Select * from usuarios where nombre='"+user+"'";
	            
	            try {
					if(modifica.existeRegistro(sql)) {
						JOptionPane.showMessageDialog(null,"Usuario ya existente, introduzca otro por favor");
						
					}else {
						modifica.creaUser(user,password);
						JOptionPane.showMessageDialog(null,"Usuario creado");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	                  
	            inputUser.setText("");
	          	inputPass.setText("");
	         }else {
	            JOptionPane.showMessageDialog(null,"Introduce usuario y password");
	         	inputUser.setText("");
	         	inputPass.setText("");
	         }
			
		}else if("btnLogin".equals(e.getActionCommand())) {
			if (!inputUser.getText().equals("") && !inputPass.getPassword().equals("")) {
				
	            String user = inputUser.getText(); 
	            char[] pass = inputPass.getPassword();
	            String password = new String (pass); 
	            String sql= "Select * from usuarios where nombre='"+user+"' and password= '"+password+"';";
	                  
	            try {
					if(modifica.existeRegistro(sql)) { 	
						JOptionPane.showMessageDialog(null,"¡Bienvenido!");
						miMarco frame = (miMarco) SwingUtilities.getWindowAncestor(this);
						frame.dispose();
						miMarco marco = new miMarco(1200,700);
						
					}else {
						JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrecta");
						inputUser.setText("");
						inputPass.setText("");
					}
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}            
	            
	         }else {
	            JOptionPane.showMessageDialog(null,"Introduce un usuario y password registrado previamente");
	         	inputUser.setText("");
	         	inputPass.setText("");
	         }	
		}
	}	
}


class panelFondo extends JPanel{
	
	public panelFondo() {
		setBounds(0,0,100,30);
		setBackground(new Color(199, 247, 243));		
		
	}	
}

class panelCentral extends JPanel{
	
	public panelCentral() {
		setLayout(new CardLayout());
		
		setBounds(350,20,1150,780);
		setBackground(new Color(199, 247, 243));		
		
	}
}

class panelReparaciones extends JPanel implements ActionListener{
	
	JTextField inputDniCliente = new JTextField();
	JTextField inputMatricula = new JTextField();
	JTextField inputCosteMateriales = new JTextField();
	JTextField inputHorasTrabajo = new JTextField();
	JCheckBox checkLimpieza = new JCheckBox("Limpieza 20€");
	JCheckBox checkAceite = new JCheckBox("Cambio Aceite 60€");
	JCheckBox checkAire = new JCheckBox("Reponer Aire Acondicionado 200€");
	JButton btnCoste = new JButton("Calcular Coste");
	JTextField inputTotal = new JTextField();
	JButton btnAgregar = new JButton("AGREGAR");
	JButton btnListar = new JButton("LISTAR");
	JButton btnActualizar = new JButton("ACTUALIZAR");
	JButton btnEliminar = new JButton("ELIMINAR");	
	panelClientes panelclientes = new panelClientes();
	String columnas[] = {"codigo","dni","total"};
	JButton btnLimpiar = new JButton();
	JTable tablaReparaciones = new JTable (){   
        @Override
        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {   
            Component result = super.prepareRenderer(renderer, row, column);   
            if (result instanceof JComponent) {   
                ((JComponent) result).setBorder(new LineBorder(Color.gray));   
            }   
            return result;   
        }   
    }; 
    
	private int cantidadTotal;
	private int costeHoras;
	private int limpieza;
	private int aceite;
	private int aireAcondicionado;
	private Image mecanico;
	private Image columna;
	ModificaDatos modifica = new ModificaDatos();
	Utilidades util = new Utilidades();
	
	public panelReparaciones() {		
		
		Font fuente = new Font("Calibri", 3, 60);
		Font fuente2 = new Font("Calibri", 3, 20);
		Font fuente3 = new Font("Calibri", 1, 17);
		Font fuente4 = new Font("Arial", 3, 15);
		
		
		setLayout(null);
		setBackground(new Color(105,163,165,60));
		setBounds(350,20,1150,780);
		setFont(fuente2);
		
		try {
			modifica.mostrarReparaciones(tablaReparaciones);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel textoReparaciones = new JLabel("REPARACIONES");
		textoReparaciones.setFont(fuente);
		textoReparaciones.setBounds(370, 0, 500, 100);	
		
		JLabel txtDniCliente = new JLabel("DNI Cliente:");	
		txtDniCliente.setFont(fuente2);
		txtDniCliente.setBounds(30, 150, 100, 50);
		
		
		inputDniCliente.setFont(fuente2);
		inputDniCliente.setBounds(180, 160, 200, 30);
		
		JLabel txtMatricula = new JLabel("Matricula:");	
		txtMatricula.setFont(fuente2);
		txtMatricula.setBounds(30, 210, 200, 50);
		
		
		inputMatricula.setFont(fuente2);
		inputMatricula.setBounds(180, 220, 200, 30);
		
		JLabel txtCoste = new JLabel("Coste");	
		txtCoste.setFont(fuente2);
		txtCoste.setBounds(400, 140, 100, 50);
		
		JLabel txtMateriales = new JLabel("Materiales:");
		txtMateriales.setFont(fuente2);
		txtMateriales.setBounds(400, 160, 100, 50);
			
		
		inputCosteMateriales.setFont(fuente2);
		inputCosteMateriales.setBounds(530, 160, 200, 30);
		
		JLabel txtHoras = new JLabel("Horas");
		txtHoras.setFont(fuente2);
		txtHoras.setBounds(400, 200, 100, 50);
		
		JLabel txtTrabajo = new JLabel("Trabajo:");
		txtTrabajo.setFont(fuente2);
		txtTrabajo.setBounds(400, 220, 100, 50);
			
		inputHorasTrabajo.setFont(fuente2);
		inputHorasTrabajo.setBounds(530, 220, 200, 30);
		
		JLabel txtCosteHora = new JLabel("*El coste por hora de trabajo es de 15€");
		txtCosteHora.setForeground(new Color(56,120,122));
		txtCosteHora.setFont(fuente2);
		txtCosteHora.setBounds(280, 270, 350, 50);
		
		JLabel txtServicios = new JLabel("Otros Servicios:");	
		txtServicios.setFont(fuente3);
		txtServicios.setBounds(30, 330, 200, 50);
		
		checkLimpieza.setBounds(180, 330, 100, 50);
		checkAceite.setBounds(280, 330, 130, 50);
		checkAire.setBounds(180, 380, 230, 50);	
		
		btnCoste.setActionCommand ("btnCoste");
		btnCoste.addActionListener(this);
		btnCoste.setBounds(510, 320, 200, 50);
		
		JLabel txtTotal = new JLabel("TOTAL:");
		txtTotal.setFont(fuente2);
		txtTotal.setBounds(480, 385, 100, 50);	
		
		inputTotal.setFont(fuente2);
		inputTotal.setBounds(550, 390, 120, 30);
		
		btnAgregar.setActionCommand ("btnAgregar");
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(780, 150, 120, 50);
		
		btnListar.setActionCommand ("btnListar");
		btnListar.addActionListener(this);
		btnListar.setBounds(910, 150, 120, 50);
		
		btnActualizar.addActionListener(this);
		btnActualizar.setActionCommand ("btnActualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(780, 210, 250, 50);
		
		btnEliminar.setActionCommand ("btnEliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBackground(new Color(242,50,60));
		btnEliminar.setBounds(780, 270, 250, 50);
		
		btnLimpiar.setActionCommand ("btnLimpiar");
		btnLimpiar.addActionListener(this);
		ImageIcon productosImg = new ImageIcon("src/presentacion/Images/borrar.png");
		ImageIcon productosEscala = new ImageIcon(productosImg.getImage().getScaledInstance(80, 80, java.awt.Image.SCALE_DEFAULT));
		btnLimpiar.setIcon(productosEscala);
		btnLimpiar.setBounds(840, 330, 120, 120);
		
		
		tablaReparaciones.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = tablaReparaciones.rowAtPoint(new Point(e.getPoint()));
				
				inputDniCliente.setText(tablaReparaciones.getValueAt(filaSeleccionada,1).toString());
				inputMatricula.setText(tablaReparaciones.getValueAt(filaSeleccionada, 2).toString());
				inputCosteMateriales.setText(tablaReparaciones.getValueAt(filaSeleccionada, 3).toString());
				inputHorasTrabajo.setText(tablaReparaciones.getValueAt(filaSeleccionada, 4).toString());
				inputTotal.setText(tablaReparaciones.getValueAt(filaSeleccionada, 5).toString());
			
			}
		});
		
		tablaReparaciones.setBounds(180, 500, 800, 250);
		tablaReparaciones.setBorder(new LineBorder(Color.gray));
		tablaReparaciones.setBackground(new Color(249,248,187));
		tablaReparaciones.setForeground(new Color(199,100,100));
		tablaReparaciones.setRowHeight(25);
		tablaReparaciones.setFont(fuente3);
		tablaReparaciones.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaReparaciones.doLayout();
		JScrollPane scroll = new JScrollPane(tablaReparaciones);
		scroll.setBounds(220, 480, 700, 270);
		
		add(textoReparaciones);
		add(txtMatricula);
		add(inputMatricula);
		add(txtDniCliente);
		add(inputDniCliente);
		add(txtHoras);
		add(txtTrabajo);
		add(inputHorasTrabajo);
		add(txtCosteHora);
		add(txtCoste);
		add(txtMateriales);
		add(inputCosteMateriales);
		add(txtTotal);
		add(inputTotal);
		add(txtServicios);
		add(checkLimpieza);
		add(checkAceite);
		add(checkAire);
		add(btnCoste);
		add(btnAgregar);
		add(btnListar);
		add(btnActualizar);
		add(btnEliminar);
		add(scroll);
		add(btnLimpiar);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		File rutaMecanico = new File ("src/presentacion/Images/mecanico.PNG");		
				
		try {
			mecanico = ImageIO.read(rutaMecanico);
		} catch (IOException e) {
			System.out.println("La imagen no se encuentra");
		}
				
		g.drawImage(mecanico, 200, 250, null);
				  
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
		if("btnCoste".equals(e.getActionCommand())) {
			actualizaCoste();
		}
		
		else if("btnAgregar".equals(e.getActionCommand())) {
			if(!inputDniCliente.getText().equals("") && !inputMatricula.getText().equals("")
					&& !inputTotal.getText().equals("")) {
						
				String Dni_Cliente = inputDniCliente.getText();
				String matricula = inputMatricula.getText();
				String total = inputTotal.getText();
				int costeMateriales = Integer.parseInt(inputCosteMateriales.getText());
				int horasTrabajo = Integer.parseInt(inputHorasTrabajo.getText());	
				
				try {	
					if(util.compruebaDni(Dni_Cliente)) {
						modifica.agregaReparacion(Dni_Cliente, matricula,costeMateriales, horasTrabajo, total);
						JOptionPane.showMessageDialog(null, "Todos los datos han sido añadidos correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Introduce un DNI correcto");
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				try {
					modifica.mostrarReparaciones(tablaReparaciones);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				limpiaInputs();
					
			}else {			
				JOptionPane.showMessageDialog(null, "Rellene todos los datos");
			}
			
		//Quizas haya que añadir mas parametros al metodo eliminar
			
		}else if("btnEliminar".equals(e.getActionCommand())) {
			String Dni_Cliente = inputDniCliente.getText();
			
			if(!Dni_Cliente.equals("")) {
				try {
					modifica.eliminaReparacion(Dni_Cliente);
					JOptionPane.showMessageDialog(null, "Registro Eliminado");
					try {
						modifica.mostrarReparaciones(tablaReparaciones);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					limpiaInputs();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else{
				JOptionPane.showMessageDialog(null, "Introduzca un DNI para borrar el registro");
			}		
			
		}else if("btnActualizar".equals(e.getActionCommand())) {
			actualizaCoste();
			String Dni_Cliente = inputDniCliente.getText();
			String matricula = inputMatricula.getText();
			String total = inputTotal.getText();
			int costeMateriales = Integer.parseInt(inputCosteMateriales.getText());
			int horasTrabajo = Integer.parseInt(inputHorasTrabajo.getText());
					
			try {
				modifica.actualizarReparacion(Dni_Cliente, matricula, costeMateriales,horasTrabajo, total, tablaReparaciones);
				JOptionPane.showMessageDialog(null, "Registro Actualizado");
				modifica.mostrarReparaciones(tablaReparaciones);
				limpiaInputs();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "No se ha podido actualizar la lista");
			}
		}else if("btnLimpiar".equals(e.getActionCommand())) {
			limpiaInputs();
		}
			
	}
	
	public void actualizaCoste() {
		limpieza = 0;
		aceite = 0;
		aireAcondicionado = 0;
		
		if(inputHorasTrabajo.getText().equals("") ) {
			inputHorasTrabajo.setText("0");
		}
		
		if(inputCosteMateriales.getText().equals("")) {
			inputCosteMateriales.setText("0");
		}
			
		if(checkLimpieza.isSelected()) {
			limpieza += 20;
		}
			
		if(checkAire.isSelected()) {
			aireAcondicionado += 200;
		}
		
		if(checkAceite.isSelected()) {
			aceite += 60;
		}
			
		int numHoras = Integer.parseInt(inputHorasTrabajo.getText());
		costeHoras = numHoras * 15;
		cantidadTotal = Integer.parseInt(inputCosteMateriales.getText());
		cantidadTotal += costeHoras + limpieza + aireAcondicionado + aceite;
		inputTotal.setText(String.valueOf(cantidadTotal) + "€");
	}
	
	public void limpiaInputs() {
		inputDniCliente.setText("");
		inputCosteMateriales.setText("");
		inputMatricula.setText("");
		inputHorasTrabajo.setText("");
		inputTotal.setText("");
	}
}

class panelProductos extends JPanel implements ActionListener{
	
	JTable tablaProductos = new JTable (){   
        @Override
        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {   
            Component result = super.prepareRenderer(renderer, row, column);   
            if (result instanceof JComponent) {   
                ((JComponent) result).setBorder(new LineBorder(Color.gray));   
            }   
            return result;   
        }   
    }; 
    JLabel textoProductos = new JLabel("PRODUCTOS");
    JLabel txtProducto = new JLabel("Nombre:");
    JTextField inputProducto = new JTextField();
    JLabel txtCategoria = new JLabel("Categoria:");	
    JComboBox categorias = new JComboBox <String>();
    JLabel txtStock = new JLabel("Stock:");
    JTextField inputStock = new JTextField();
    JLabel txtPrecio = new JLabel("Precio:");
    JTextField inputPrecio = new JTextField();
    JButton btnAgregar = new JButton("AGREGAR");
    JButton btnListar = new JButton("LISTAR");
    JButton btnActualizar = new JButton("ACTUALIZAR");
    JButton btnEliminar = new JButton("ELIMINAR");
    ModificaDatos modifica = new ModificaDatos();
    JButton btnLimpiar = new JButton();
    
	public panelProductos() {	
		
		Font fuente = new Font("Calibri", 3, 60);
		Font fuente2 = new Font("Calibri", 3, 20);
		Font fuente3 = new Font("Calibri", 1, 17);
		Font fuente4 = new Font("Arial", 3, 15);
		
		try {
			modifica.mostrarProductos(tablaProductos);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setLayout(null);
		setBounds(350,20,1150,780);
		setBackground(new Color(105,163,165,60));
		setFont(fuente2);
		
		textoProductos.setFont(fuente);
		textoProductos.setBounds(370, 0, 500, 100);	
		
		txtProducto.setFont(fuente2);
		txtProducto.setBounds(30, 150, 100, 50);
		
		inputProducto.setFont(fuente2);
		inputProducto.setBounds(180, 160, 200, 30);
		
		txtCategoria.setFont(fuente2);
		txtCategoria.setBounds(30, 210, 100, 50);
		
		categorias.addItem("Limpieza");
		categorias.addItem("Mantenimiento");
		categorias.addItem("Promoción");
		categorias.addActionListener(this);
		categorias.setBounds(180, 220, 150, 30);
		
		txtStock.setFont(fuente2);
		txtStock.setBounds(400, 150, 100, 50);
			
		inputStock.setFont(fuente2);
		inputStock.setBounds(530, 160, 200, 30);
			
		txtPrecio.setFont(fuente2);
		txtPrecio.setBounds(400, 210, 100, 50);
		
		inputPrecio.setFont(fuente2);
		inputPrecio.setBounds(530, 220, 200, 30);		
		
		btnAgregar.setBounds(780, 150, 120, 50);
		btnAgregar.addActionListener(this);
		btnAgregar.setActionCommand("btnAgregar");
		
		btnListar.setBounds(910, 150, 120, 50);
		
		btnActualizar.setBounds(780, 210, 250, 50);
		btnActualizar.addActionListener(this);
		btnActualizar.setActionCommand("btnActualizar");
		
		btnEliminar.setBackground(new Color(242,50,60));
		btnEliminar.setBounds(780, 270, 250, 50);
		btnEliminar.addActionListener(this);
		btnEliminar.setActionCommand("btnEliminar");
		
		btnLimpiar.setActionCommand ("btnLimpiar");
		btnLimpiar.addActionListener(this);
		ImageIcon productosImg = new ImageIcon("src/presentacion/Images/borrar.png");
		ImageIcon productosEscala = new ImageIcon(productosImg.getImage().getScaledInstance(80, 80, java.awt.Image.SCALE_DEFAULT));
		btnLimpiar.setIcon(productosEscala);
		btnLimpiar.setBounds(840, 330, 120, 120);
		
		tablaProductos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = tablaProductos.rowAtPoint(new Point(e.getPoint()));
				
				inputProducto.setText(tablaProductos.getValueAt(filaSeleccionada,1).toString());
				inputStock.setText(tablaProductos.getValueAt(filaSeleccionada, 2).toString());
				inputPrecio.setText(tablaProductos.getValueAt(filaSeleccionada, 3).toString());		
			}
		});
		
		tablaProductos.setBorder(new LineBorder(Color.gray));
		tablaProductos.setBackground(new Color(249,248,187));
		tablaProductos.setForeground(new Color(199,100,100));
		tablaProductos.setRowHeight(25);
		tablaProductos.setFont(fuente3);
		tablaProductos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaProductos.doLayout();
		JScrollPane scroll = new JScrollPane(tablaProductos);
		scroll.setBounds(220, 480, 700, 270);
		
		add(textoProductos);
		add(txtProducto);
		add(inputProducto);
		add(txtPrecio);
		add(inputPrecio);
		add(txtCategoria);
		add(categorias);
		add(txtStock);
		add(inputStock);
		add(btnAgregar);
		add(btnListar);
		add(btnActualizar);
		add(btnEliminar);	
		add(scroll);
		add(btnLimpiar);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if("btnAgregar".equals(e.getActionCommand())) {
			if(!inputProducto.getText().equals("") && !inputStock.getText().equals("")
					&& !inputPrecio.getText().equals("")) {
						
				String nombreProducto = inputProducto.getText();
				int stock = Integer.parseInt(inputStock.getText());
				double precio =  Double.parseDouble(inputPrecio.getText());
				String categoriaSeleccionada = (String) categorias.getSelectedItem();
							
				try {	
					modifica.agregaProducto(nombreProducto,stock,precio,categoriaSeleccionada);
					JOptionPane.showMessageDialog(null, "Producto añadido");
							
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				try {
					modifica.mostrarProductos(tablaProductos);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				limpiaInputs();
					
			}else {			
				JOptionPane.showMessageDialog(null, "Rellene todos los datos");
			}
			
		}else if("btnEliminar".equals(e.getActionCommand())) {
			String nombreProducto = inputProducto.getText();
			int stockProducto = Integer.parseInt(inputStock.getText());
			double precio = Double.parseDouble(inputPrecio.getText());
			String categoria = (String) categorias.getSelectedItem();
			
			if(!nombreProducto.equals("") && !inputProducto.equals("") && !inputPrecio.equals("") && !categoria.equals("")) {
				try {
					modifica.eliminaProducto(nombreProducto);
					JOptionPane.showMessageDialog(null, "Producto Eliminado");
					try {
						modifica.mostrarProductos(tablaProductos);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					limpiaInputs();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else{
				JOptionPane.showMessageDialog(null, "Rellene todos los campos para borrar el producto");
			}		
		}else if("btnActualizar".equals(e.getActionCommand())) {
			String nombreProducto = inputProducto.getText();
			int stockProducto = Integer.parseInt(inputStock.getText());
			double precio = Double.parseDouble(inputPrecio.getText());
			String categoria = (String) categorias.getSelectedItem();
					
			try {
				modifica.actualizarProducto(nombreProducto,stockProducto,precio,categoria,tablaProductos);
				JOptionPane.showMessageDialog(null, "Registro Actualizado");
				modifica.mostrarProductos(tablaProductos);
				limpiaInputs();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "No se ha podido actualizar la lista");
				System.out.println(e1.getMessage());
			}
		}else if("btnLimpiar".equals(e.getActionCommand())) {
			limpiaInputs();
		}
			
	}
		
	
	
	private void limpiaInputs() {
		inputPrecio.setText("");
		inputStock.setText("");
		inputProducto.setText("");
	}

}

class panelClientes extends JPanel implements ActionListener{
	
	JComboBox categorias = new JComboBox <String>();
	JLabel textoClientes = new JLabel("CLIENTES");
	JLabel txtNombre = new JLabel("Nombre:");
	JTextField inputNombre = new JTextField();
	JLabel txtApellidos = new JLabel("Apellidos:");	
	JTextField inputApellidos = new JTextField();
	JLabel txtDNI = new JLabel("DNI:");	
	JTextField inputDNI = new JTextField();
	JLabel txtTelefono = new JLabel("Teléfono:");	
	JTextField inputTelefono = new JTextField();
	JLabel txtPoblacion = new JLabel("Población:");	
	JTextField inputPoblacion = new JTextField();
	JLabel txtMatricula = new JLabel("Matrícula:");
	JTextField inputMatricula = new JTextField();
	JButton btnAgregar = new JButton("AGREGAR");
	JButton btnListar = new JButton("LISTAR");
	JButton btnActualizar = new JButton("ACTUALIZAR");
	JButton btnEliminar = new JButton("ELIMINAR");
	JTable tablaClientes = new JTable();
	ModificaDatos modifica = new ModificaDatos();
	
	public panelClientes() {		
		Font fuente2 = new Font("Calibri", 3, 20);
		Font fuente = new Font("Calibri", 3, 60);
		Font fuente3 = new Font("Calibri", 1, 17);
		
		try {
			modifica.mostrarClientes(tablaClientes);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("No se ha podido mostrar la tabla clientes" + e1.getMessage());
		}
		
		setLayout(null);
		setBounds(350,20,1150,780);
		setBackground(new Color(105,163,165,60));
		setFont(fuente2);
		
		textoClientes.setFont(fuente);
		textoClientes.setBounds(450, 0, 500, 100);
		
		txtNombre.setFont(fuente2);
		txtNombre.setBounds(30, 150, 100, 50);
		
		inputNombre.setFont(fuente2);
		inputNombre.setBounds(130, 160, 200, 30);
		
		txtApellidos.setFont(fuente2);
		txtApellidos.setBounds(30, 210, 100, 50);
		
		inputApellidos.setFont(fuente2);
		inputApellidos.setBounds(130, 220, 200, 30);
		
		txtDNI.setFont(fuente2);
		txtDNI.setBounds(30, 270, 100, 50);
		
		inputDNI.setFont(fuente2);
		inputDNI.setBounds(130, 280, 200, 30);
		
		txtTelefono.setFont(fuente2);
		txtTelefono.setBounds(360, 150, 100, 50);
		
		inputTelefono.setFont(fuente2);
		inputTelefono.setBounds(460, 160, 200, 30);
		
		txtPoblacion.setFont(fuente2);
		txtPoblacion.setBounds(360, 210, 100, 50);
		
		categorias.setFont(fuente2);
		categorias.setBounds(460, 220, 200, 30);
		
		txtMatricula.setFont(fuente2);
		txtMatricula.setBounds(360, 270, 100, 50);
		
		inputMatricula.setFont(fuente2);
		inputMatricula.setBounds(460, 280, 200, 30);
		
		btnAgregar.setBounds(780, 150, 120, 50);
		btnAgregar.addActionListener(this);
		btnAgregar.setActionCommand("btnAgregar");
		
		btnListar.setBounds(910, 150, 120, 50);
		btnListar.addActionListener(this);
		btnListar.setActionCommand("btnListar");
		
		btnActualizar.setBounds(780, 210, 250, 50);
		btnActualizar.addActionListener(this);
		btnActualizar.setActionCommand("btnActualizar");
		
		btnEliminar.setBackground(new Color(242,50,60));
		btnEliminar.setBounds(780, 270, 250, 50);
		btnEliminar.addActionListener(this);
		btnEliminar.setActionCommand("btnEliminar");
		
		tablaClientes.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = tablaClientes.rowAtPoint(new Point(e.getPoint()));
				
				inputNombre.setText(tablaClientes.getValueAt(filaSeleccionada,0).toString());
				inputApellidos.setText(tablaClientes.getValueAt(filaSeleccionada, 1).toString());
				inputDNI.setText(tablaClientes.getValueAt(filaSeleccionada, 2).toString());		
				inputTelefono.setText(tablaClientes.getValueAt(filaSeleccionada,3).toString());
				categorias.setSelectedItem(tablaClientes.getValueAt(filaSeleccionada,4).toString());
				inputMatricula.setText(tablaClientes.getValueAt(filaSeleccionada, 5).toString());	
			}
		});
		
		tablaClientes.setBorder(new LineBorder(Color.gray));
		tablaClientes.setBackground(new Color(249,248,187));
		tablaClientes.setForeground(new Color(199,100,100));
		tablaClientes.setRowHeight(25);
		tablaClientes.setFont(fuente3);
		tablaClientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaClientes.doLayout();
		JScrollPane scroll = new JScrollPane(tablaClientes);
		scroll.setBounds(220, 480, 700, 270);
		
		String [] comunidades = {"Andalucía","Aragón","Asturias","Islas Baleares","Canarias","Cantabria","Castilla-León","Castilla-La Mancha","Cataluña","Comunidad Valenciana","Extremadura","Galicia","Comunidad de Madrid","Murcia","Navarra","Pais Vasco","La Rioja","Ceuta","Melilla"};
		
		for(int i=0; i<comunidades.length; i++) {
			categorias.addItem(comunidades[i]);
		}
		
		add(inputNombre);
		add(inputApellidos);
		add(txtDNI);
		add(txtTelefono);
		add(inputTelefono);
		add(inputDNI);
		add(txtNombre);
		add(txtApellidos);
		add(textoClientes);
		add(txtPoblacion);
		add(inputPoblacion);
		add(txtMatricula);
		add(inputMatricula);
		add(btnAgregar);
		add(btnListar);
		add(btnActualizar);
		add(btnEliminar);
		add(categorias);
		add(scroll);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if("btnAgregar".equals(e.getActionCommand())) {
			if(!inputNombre.getText().equals("") && !inputApellidos.getText().equals("")
					&& !inputDNI.getText().equals("") && !inputTelefono.getText().equals("") && !inputMatricula.getText().equals("")) {
						
				String nombre = inputNombre.getText();
				String apellidos = inputApellidos.getText();
				String DNI = inputDNI.getText();
				int telefono = Integer.parseInt(inputTelefono.getText());
				String poblacion = (String) categorias.getSelectedItem();
				String matricula = inputMatricula.getText();
				
				try {	
					modifica.agregaCliente(nombre, apellidos, DNI, telefono, poblacion, matricula);
					JOptionPane.showMessageDialog(null, "Cliente añadido");
							
				} catch (SQLException e1) {
					e1.printStackTrace();
					System.out.println("Fallo al agregar cliente " + e1.getMessage());
				}
				
				try {
					modifica.mostrarClientes(tablaClientes);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				limpiaInputs();
					
			}else {			
				JOptionPane.showMessageDialog(null, "Rellene todos los datos");
			}
		}else if("btnActualizar".equals(e.getActionCommand())) {
			
			String nombre = inputNombre.getText();
			String apellidos = inputApellidos.getText();
			String DNI = inputDNI.getText();
			int telefono = Integer.parseInt(inputTelefono.getText());
			String poblacion = (String) categorias.getSelectedItem();
			String matricula = inputMatricula.getText();
			
			try {
				modifica.actualizarCliente(nombre, apellidos, DNI, telefono, poblacion, matricula, tablaClientes);
				JOptionPane.showMessageDialog(null, "Cliente Actualizado");
				modifica.mostrarClientes(tablaClientes);
				limpiaInputs();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "No se ha podido actualizar la lista");
				System.out.println(e1.getMessage());
			}
			
		}else if("btnEliminar".equals(e.getActionCommand())) {
			
		}
		
	}
	
	public void limpiaInputs() {
		inputNombre.setText("");
		inputApellidos.setText("");
		inputTelefono.setText("");
		inputDNI.setText("");
		inputMatricula.setText("");
	}
}

