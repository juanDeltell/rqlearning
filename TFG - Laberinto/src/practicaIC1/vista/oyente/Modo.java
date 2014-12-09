package practicaIC1.vista.oyente;

public enum Modo {

	
	
	INICIAL(0)	,	
	CHECK(1)	,	
	PELIGRO(2)	,	
	COSTEMAS(3)	,
	ALTURAMAS(4)	,
	COSTEMENOS(5)	,
	ALTURAMENOS(6)	,	
		
	
	REEJECUTA(777);	
	
	
	Modo(int i)
	{
		num=i;
	}
	
	private int num;

	public int getNum() {
		return num;
	}

	
	/*
	public boolean equals(Object o)
	{
		Modo m = (Modo )o;
		return num==m.num; 
	}*/
	
	
	
	
	
}
