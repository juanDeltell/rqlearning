package practicaIC1.vista;


import algoritmo.Direcciones;
import algoritmo.NodoAstar;



public class NombreImagen 
{

	public static String   nombreFoto(NodoAstar n)
	{
		if(n==null)return null;
		
		String nombre="flecha_";
		

		if(n.direccionSig()== Direcciones.ARRIBA)			nombre+="arriba";
		else if(n.direccionSig()== Direcciones.ABAJO)		nombre+="abajo";
		else if(n.direccionSig()== Direcciones.IZQUIERDA)	nombre+="izquierda";
		else if(n.direccionSig()== Direcciones.DERECHA)		nombre+="derecha";
		
		
		
		String 				tipo="camino"; 
		if(n.isCamino())	tipo="camino";
		if(n.isInicio())	tipo="inicio";
		
		if(n.isCheckPoint())tipo="check";
		if(n.isFin())		tipo="final";
		if(n.isNormal())	tipo="";
		
		
		
		nombre+=tipo;
			
		
		nombre+=".png";
		return nombre;
		
	}
	
	
}
