package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class ModificaDatos{

	public static void main(String[] args) {
		
		try {
			
			//Crear conexion
			
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/carmaniac", "root", "");
			
			//Crear Statement
			
			Statement statement = conexion.createStatement();
				
			
		}catch(Exception e) {
			System.out.println("No conecta");	
			e.printStackTrace();
		}
		
		
	}
	
	
	public void creaUser(String user,String password) throws SQLException {
				
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/carmaniac", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Statement statement = conexion.createStatement();
		
		String instruccionSql = "INSERT INTO usuarios (Nombre, Password) VALUES("+"'"+user+"'"+","+"'"+password+"'"+")";
		
		statement.executeUpdate(instruccionSql);
		
	}
	
	public boolean existeRegistro(String sql) throws SQLException {
		
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/carmaniac", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PreparedStatement ps = conexion.prepareStatement(sql);
		
	    ResultSet rs = ps.executeQuery();
	    
	    return rs.next();
		
	}
	
	public void agregaReparacion(String DNI, String Matricula, int Coste, int HorasTrabajo, String Total) throws SQLException {
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/carmaniac", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Statement statement = conexion.createStatement();
		
		String instruccionSql = "INSERT INTO reparaciones (DNI_Cliente, matricula, Coste_Materiales, Horas_Trabajo, Total) VALUES("+"'"+DNI+"'"
		+","+"'"+Matricula+"'"+","+"'"+Coste+"'"+","+"'"+HorasTrabajo+"'"+","+"'"+Total+"'"+")";
		
		statement.executeUpdate(instruccionSql);
	}
	
	public void agregaProducto(String Nombre, int Stock, double Precio, String categoria) throws SQLException {
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/carmaniac", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		Statement statement = conexion.createStatement();
		
		String instruccionSql = "INSERT INTO productos (Nombre, Stock, Precio, Categoria) VALUES("+"'"+Nombre+"'"
		+","+"'"+Stock+"'"+","+"'"+Precio+"'"+","+"'"+categoria+"'"+")";
		
		statement.executeUpdate(instruccionSql);
	}
	
	public void agregaCliente(String Nombre, String Apellidos, String DNI, int Telefono, String Poblacion, String Matricula) throws SQLException {
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/carmaniac", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		Statement statement = conexion.createStatement();
		
		String instruccionSql = "INSERT INTO clientes (Nombre, Apellidos, DNI, Telefono, Poblacion, Matricula) VALUES("+"'"+Nombre+"', '"+ Apellidos +	"', '" +DNI+"', '"+Telefono+"', '"+Poblacion+"', '"+Matricula+"');";					
		
		statement.executeUpdate(instruccionSql);
	}
	
	
	public void eliminaProducto(String Nombre) throws SQLException {
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/carmaniac", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Statement statement = conexion.createStatement();
		
		String instruccionSql = "DELETE FROM productos WHERE Nombre = " + "'" + Nombre + "'";
		
		statement.executeUpdate(instruccionSql);
	}
	
	public void eliminaReparacion(String DNI) throws SQLException {
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/carmaniac", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Statement statement = conexion.createStatement();
		
		String instruccionSql = "DELETE FROM reparaciones WHERE DNI_Cliente = " + "'" + DNI + "'";
		
		statement.executeUpdate(instruccionSql);
	}
	
	public void actualizarProducto(String Nombre, int Stock, double Precio, String categoria, JTable tabla) throws SQLException{
		
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/carmaniac", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Statement statement = conexion.createStatement();
		
		int filaSeleccionada = tabla.getSelectedRow();
		
		int codigo = Integer.parseInt((String) tabla.getValueAt(filaSeleccionada, 0)); 
		
		System.out.println(codigo);
		
		String instruccionSql = "UPDATE productos SET Nombre = " + "'" + Nombre + "'" +", Stock =" + "'"+ Stock +"'"+ ",Precio="
		+"'"+Precio+"'"+",categoria=" + "'"+categoria+"'"+"WHERE codigo=" + "'" + codigo +"'"  ;
		
		PreparedStatement pst=conexion.prepareStatement(instruccionSql);
		pst.execute();
		pst.close();
		
	}
	
	public void actualizarReparacion(String DNI, String Matricula, int Coste, int HorasTrabajo, String Total, JTable tabla) throws SQLException {
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/carmaniac", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Statement statement = conexion.createStatement();
		
		int filaSeleccionada = tabla.getSelectedRow();
		
		int codigo = Integer.parseInt((String) tabla.getValueAt(filaSeleccionada, 0)); 
		
		System.out.println(codigo);
		
		String instruccionSql = "UPDATE reparaciones SET DNI_Cliente = " + "'" + DNI + "'" +", matricula =" + "'"+ Matricula +"'"+ ",Coste_Materiales="
		+"'"+Coste+"'"+",Horas_Trabajo=" + "'"+HorasTrabajo+"'"+", Total=" +"'"+Total+"'"+"WHERE codigo=" + "'" + codigo +"'"  ;
		
		PreparedStatement pst=conexion.prepareStatement(instruccionSql);
		pst.execute();
		pst.close();		
		
	}
	
	public void actualizarCliente(String Nombre, String Apellidos, String DNI, int Telefono, String Poblacion, String Matricula, JTable tabla) throws SQLException {
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/carmaniac", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Statement statement = conexion.createStatement();
		
		int filaSeleccionada = tabla.getSelectedRow();
		
		String comprobarFila = (String) tabla.getValueAt(filaSeleccionada, 2); 
		
		String instruccionSql = "UPDATE clientes SET Nombre = " + "'" + Nombre + "'" +", Apellidos =" + "'"+ Apellidos +"'"+ ",DNI="
		+"'"+DNI+"'"+",Telefono=" + "'"+Telefono+"'"+", Poblacion=" +"'"+Poblacion+"'"+",Matricula=" + "'"+Matricula+"'"+"WHERE DNI=" + "'" + comprobarFila +"'"  ;
		
		PreparedStatement pst=conexion.prepareStatement(instruccionSql);
		pst.execute();
		pst.close();		
		
	}
	
	
	public void mostrarProductos(JTable tabla) throws SQLException{
		String [] columnas= {"Código","Nombre","Stock","Precio","Categoria"};
		DefaultTableModel modelo = new DefaultTableModel(null,columnas);
		tabla.setModel(modelo);
		
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabla.getTableHeader().setReorderingAllowed(false);
			
		String [] datos = new String [5];
		
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/carmaniac", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Statement statement = conexion.createStatement();
		
		ResultSet resultado = statement.executeQuery("SELECT * FROM productos");
		
		while(resultado.next()) {
			datos[0] = resultado.getString(1);
			datos[1] = resultado.getString(2);
			datos[2] = resultado.getString(3);
			datos[3] = resultado.getString(4);
			datos[4] = resultado.getString(5);
			modelo.addRow(datos);
		}
		
		TableColumnModel modeloColumna = tabla.getColumnModel();
		modeloColumna.getColumn(0).setPreferredWidth(150);
		modeloColumna.getColumn(1).setPreferredWidth(150);
		modeloColumna.getColumn(2).setPreferredWidth(130);
		modeloColumna.getColumn(3).setPreferredWidth(130);
		modeloColumna.getColumn(4).setPreferredWidth(135);
		
		tabla.setModel(modelo);
		modelo.fireTableDataChanged();
	}
	
	public void mostrarReparaciones(JTable tabla) throws SQLException {
		
		String [] columnas= {"Código","ID_Cliente","Matricula","Coste_Materiales", "Horas_Trabajo","TOTAL"};
		DefaultTableModel modelo = new DefaultTableModel(null,columnas);
		tabla.setModel(modelo);
		
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabla.getTableHeader().setReorderingAllowed(false);
			
		String [] datos = new String [6];
		
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/carmaniac", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Statement statement = conexion.createStatement();
		
		ResultSet resultado = statement.executeQuery("SELECT * FROM reparaciones");
		
		while(resultado.next()) {
			datos[0] = resultado.getString(1);
			datos[1] = resultado.getString(2);
			datos[2] = resultado.getString(3);
			datos[3] = resultado.getString(4);
			datos[4] = resultado.getString(5);
//			datos[5] = resultado.getString(6);
			modelo.addRow(datos);
		}
		TableColumnModel modeloColumna = tabla.getColumnModel();
		modeloColumna.getColumn(0).setPreferredWidth(100);
		modeloColumna.getColumn(1).setPreferredWidth(150);
		modeloColumna.getColumn(2).setPreferredWidth(110);
		modeloColumna.getColumn(3).setPreferredWidth(110);
		modeloColumna.getColumn(4).setPreferredWidth(110);
		modeloColumna.getColumn(5).setPreferredWidth(100);
		
		tabla.setModel(modelo);
		modelo.fireTableDataChanged();
		
	}
	
public void mostrarClientes(JTable tabla) throws SQLException {
		
		String [] columnas= {"ID_Cliente","Nombre", "Apellidos", "DNI", "Teléfono", "Población", "Matrícula"};
		DefaultTableModel modelo = new DefaultTableModel(null,columnas);
		tabla.setModel(modelo);
		
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabla.getTableHeader().setReorderingAllowed(false);
			
		String [] datos = new String [6];
		
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/carmaniac", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Statement statement = conexion.createStatement();
		
		ResultSet resultado = statement.executeQuery("SELECT * FROM clientes");
		
		while(resultado.next()) {
			datos[0] = resultado.getString(1);
			datos[1] = resultado.getString(2);
			datos[2] = resultado.getString(3);
			datos[3] = resultado.getString(4);
			datos[4] = resultado.getString(5);
			datos[5] = resultado.getString(6);
			
			modelo.addRow(datos);
		}
		TableColumnModel modeloColumna = tabla.getColumnModel();
		modeloColumna.getColumn(0).setPreferredWidth(70);
		modeloColumna.getColumn(1).setPreferredWidth(150);
		modeloColumna.getColumn(2).setPreferredWidth(110);
		modeloColumna.getColumn(3).setPreferredWidth(110);
		modeloColumna.getColumn(4).setPreferredWidth(110);
		modeloColumna.getColumn(5).setPreferredWidth(100);
		
		tabla.setModel(modelo);
		modelo.fireTableDataChanged();
		
	}
}


