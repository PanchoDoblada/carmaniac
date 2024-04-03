package datos;

public class Utilidades {

	String DNI;
	
	public boolean compruebaDni(String DNI) {
		
		String letra="";
		
		if(DNI.length()!=9 || Character.isLetter(DNI.charAt(8))==false) {
			return false;
		}
		
		letra = (DNI.substring(8)).toUpperCase();
		
		if(soloNumeros(DNI)) {
			return true;
		}else {
			return false;
		}
	}
	
	private boolean soloNumeros(String DNI) {
		String numero = "";
		String miDni = "";
		String [] arrayNumeros = {"1","2","3","4","5","6","7","8","9","0"};
		
		for(int i=0; i<DNI.length(); i++) {
			numero = DNI.substring(i,i+1);
			
			for(int j=0; j<arrayNumeros.length; j++) {
				if(numero.equals(arrayNumeros[j])) {
					miDni += arrayNumeros[j];
				}
			}
		}
		
		if(miDni.length() != 8) {
			return false;
		}else {
			return true;
		}
	}
}
