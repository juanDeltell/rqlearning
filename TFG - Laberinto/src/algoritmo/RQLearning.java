package algoritmo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class RQLearning implements Algoritmo{
	
	
	
	private Mapa map;
	
	//private double[][] tabla;
	
	private NodoAstar inicial, ultimo;
	
	private int pasos;
	
	private int iteracionesTotales=0;
	
	public RQLearning(int p)
	{
		this.setPasos(p);
		mapa();
		
		
		
		
	}
	
	
	
	public RQLearning(String s) throws IOException{
		mapa();
		//this.tabla= new double[100][4];		//Tabla con 100 estados y 4 acciones por cada 1
		
		FileReader fichero = new FileReader(Constantes.FICHERO_RECOMPENSA);
		BufferedReader entrada=new BufferedReader(fichero);
		
		for(int i = 0; i < Constantes.X; i++){
			for(int j = 0; j < Constantes.Y;  j++)
			{
				String linea = entrada.readLine();
				if(linea!=null)
					map.dameMatriz()[i][j].setRecompensa(Double.valueOf(linea));		//Inicializamos los valores de la tabla desde fichero
				
			}
		}
		fichero.close();
		entrada.close();
	}
	
	void mapa(){	
		int x= Constantes.X;
		this.map= new Mapa(Constantes.X,Constantes.Y);
		
		for(int i = 0; i < Constantes.X; i++){
			for(int j = 0; j < Constantes.Y;  j++){
				this.map.dameMatriz()[i][j].setTransitable(true);	//Transitables
			}
		}
		
		
		map.introduceInicial(0, 0);
		this.inicial =map.dameIniciales().get(0);
		
		map.introduceFinal(0, 9);
		this.ultimo =map.dameFinales().get(map.dameFinales().size()-1);
		
	
		
		//Paredes
		this.map.dameMatriz()[1][0].setTransitable(false);	
		this.map.dameMatriz()[1][1].setTransitable(false);
		this.map.dameMatriz()[1][2].setTransitable(false);
		this.map.dameMatriz()[1][3].setTransitable(false);
		this.map.dameMatriz()[1][4].setTransitable(false);
		this.map.dameMatriz()[1][5].setTransitable(false);
		this.map.dameMatriz()[1][6].setTransitable(false);
		this.map.dameMatriz()[1][7].setTransitable(false);
		this.map.dameMatriz()[1][8].setTransitable(false);
		this.map.dameMatriz()[1][9].setTransitable(false);
		
		
		for(int i = 0; i < Constantes.X; i++){
			for(int j = 0; j < Constantes.Y;  j++)
			{
				map.dameNodo(i, j).setRecompensa((double)randDouble(0,3));
			}
		}
		
	}
	
	void guardarFichero() throws IOException{
		@SuppressWarnings("resource")
		FileWriter fichero = new FileWriter(Constantes.FICHERO_RECOMPENSA);
		BufferedWriter salida=new BufferedWriter(fichero);
		
		/**
		 * casilla 0 :  arriba abajo  izq der
		 */
		
		for(int i = 0; i < Constantes.X; i++){
			for(int j = 0; j < Constantes.Y;  j++)
			{
				salida.write(map.dameMatriz()[i][j].getRecompensa()+"\n");
				
			}
			
		}
		salida.close();
		fichero.close();
		
	}
	
	boolean can(int x){
		if(x>=0 && x<=9)
			return true;
		else
			return false;
	}
	
	void ejecuta() throws IOException
	{
	
		
		for(int i = 0; i < this.pasos; i++)
		{
			
			NodoAstar actual = inicial;
			
			System.out.println("ejecuta el algoritmo rqlearnng " + iteracionesTotales);
			iteracionesTotales++;
			HashMap<Accion,NodoAstar>  sucesores = map.dameSucesores(actual);
			
			//calculamos el coste actual Q(s,a) para la fórmula
			double Q_sa = Double.MIN_VALUE;
			//calculamos el coste actual Q(s,a)' para la fórmula
    		double Q_sa_prima = Double.MIN_VALUE;
    		
    		NodoAstar sucesor_elegido;
    		NodoAstar suc=actual;
			for(Accion a: sucesores.keySet())
			{
				suc= sucesores.get(a);
				
				HashMap<Accion,NodoAstar>  sucesoresPrima = map.dameSucesores(suc);
				
				//Calculamos el coste max a'(Q(s', a')) para la formula
				for(Accion aPrima: sucesoresPrima.keySet())
				{
	    			
	    			
	    			//elige la accion con mayor recompensa Q en la tabla
					if(actual.getQ().get(aPrima) > Q_sa_prima)
					{
						//calculamos el coste actual Q(s,a) para la fórmula
						Q_sa_prima=actual.getQ().get(aPrima); 
						sucesor_elegido = sucesoresPrima.get(aPrima);
					}
	    			
	    			//calculamos con formula y vamos almacenando el mejor resultado
	    					
					
				}
				//una vezs calculado en max s'a' :
				//Q(s,a) = Q(s,a) + alpha( r + landa * max a'(Q(s', a')) - Q(s,a) )
				Q_sa= actual.getQ().get(a) +  Constantes.ALPHA*(actual.getRecompensa()+ Constantes.LANDA  * Q_sa_prima  );//- Q_sa
				actual.getQ().put(a, Q_sa) ;
			}
			
			actual=suc;
			//faltaria actualizar el nodo padre
		}
		guardarFichero();
	}
			
		

	
	
	
	public static int randInt(int min, int max) {
		Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    
	    return randomNum;
	}
	
	public static double randDouble(int min, int max) {
		Random rand = new Random();
	    int randomNum = randInt(min, max);
	    double d = randomNum + rand.nextDouble();
	    
	    return d;
	}


	
	
	public int getPasos() {
		return pasos;
	}

	public void setPasos(int pasos) {
		this.pasos = pasos;
	}



	@Override
	public void reejecuta() {

		for(int i = 0; i < Constantes.NUM_EJECUCIONES;i++)
		{
			try {
				ejecuta();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
	}



	@Override
	public ArrayList<ArrayList<NodoAstar>> dameCamino() 
	{
		ArrayList<ArrayList<NodoAstar>> l = new ArrayList<ArrayList<NodoAstar>>();
		
		ArrayList<NodoAstar> camino = construyeCamino();
		l.add(camino);
		return l;
	}



	



	private ArrayList<NodoAstar> construyeCamino()
	{
		ArrayList<NodoAstar> camino = new ArrayList<NodoAstar> ();
		

        NodoAstar nodoAuxiliar = ultimo;
        while (nodoAuxiliar != null)
        {
       	 camino.add(0, nodoAuxiliar);
            nodoAuxiliar = nodoAuxiliar.getNodoPadre();
        }
                  
        
        for(NodoAstar n : camino )
       	 n.setCamino(true);
       
      // NodoAstar nodoInicial = camino.get(0);   
       
      
        
       //compruebo choques
      /*for(int i=0; i<caminoAux.size();i++)
      {
       	NodoAstar n = caminoAux.get(i);
       	for(ArrayList<NodoAstar> caminos:camino)
       	{
       		if(!caminos.get(0).equals(nodoInicial))
       		{
           		try
           		{
           			if(caminos.get(i).equals(n))n.setChoque(true);
           		}
           		catch(Exception e)
           		{
           			//eso es que el camino actual es ams largo que el camino que revisamos
           		}
       		}
       	}
       }*/
        
        
       
       
      //  coste.put(mapa.dameIniciales().indexOf(nodoInicial)+1,caminoAux.get(caminoAux.size()-1).getF());  
      
        return camino;
	}



	@Override
	public Mapa dameMapa() {
		
		return map;
	}



	@Override
	public HashMap<Integer, Double> getCoste() {
		
		return null;
	}



	@Override
	public void reinicia()
	{
		// TODO Auto-generated method stub
		
	}



	@Override
	public Set<Integer> getInicialesSInCamino() {
		// TODO Auto-generated method stub
		return null;
	}
}
