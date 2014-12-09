package practicaIC1.vista;
public class Estado
{

	public static  int estado;
	
	
	
	public static final int INICIAL		= 0;
	public static final int PELIGRO		= 1;
	public static final int ALTURAMAS		= 2;
	public static final int COSTEMAS		= 3;
	public static final int CHECK		= 4;
	public static final int INFO		= 8;
	public static final int RANGO		= 9;
	public static final int REINICIAR	= 10;
	public static final int DIAGONALES_ON	= 11;
	public static final int DIAGONALES_OFF	= 14;
	public static final int COSTEMENOS	= 12;
	public static final int ALTURAMENOS	= 13;
	
	public static final int REEJECUTA	= 777;
	
	//public static final int FINAL=1;
	
	public static final String s_INICIAL	= "INICIAL";
	public static final String s_PELIGRO	= "PELIGRO";
	public static final String s_ALTURAMAS		= "ALTURA POSITIVA";
	public static final String s_COSTEMAS		= "COSTE POSITIVO";
	public static final String s_CHECK		= "CHECK";
	public static final String s_REEJECUTA	= "REEJECUTA";
	public static final String s_REINICIAR	= "REINICIAR";
	public static final String s_DIAGONALES_ON	= "DIAGONALES ON";
	public static final String s_DIAGONALES_OFF	= "DIAGONALES OFF";
	public static final String s_ALTURAMENOS		= "ALTURA NEGATIVA";
	public static final String s_COSTEMENOS		= "COSTE NEGATIVO";
	
	
	
	
	public static String nombre()
	{
		int i = estado;
		if(i== INICIAL) return s_INICIAL;
		if(i== PELIGRO) return s_PELIGRO;
		if(i== ALTURAMAS) return s_ALTURAMAS;
		if(i== COSTEMAS) return s_COSTEMAS;
		if(i== REINICIAR) return s_REINICIAR;
		if(i== CHECK) return s_CHECK;
		if(i== REEJECUTA) return s_REEJECUTA;
		if(i== DIAGONALES_ON) return s_DIAGONALES_ON;
		if(i== DIAGONALES_OFF) return s_DIAGONALES_OFF;
		if(i== COSTEMENOS) return s_COSTEMENOS;
		if(i== ALTURAMENOS) return s_ALTURAMENOS;
		
		
		return "";
	}
	
}


