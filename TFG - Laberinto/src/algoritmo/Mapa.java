package algoritmo;
import java.util.ArrayList;
import java.util.HashMap;


public class Mapa 
{
	
	private int filas;
	private int columnas;
	private NodoAstar[][] matriz;
	private ArrayList<NodoAstar> iniciales;
	private ArrayList<NodoAstar> finales; // si hay varios, la ultima es la meta y el resto son checkpoint
	private ArrayList<NodoAstar> peligros;  //negras
	private ArrayList<NodoAstar>  costosas; //grises
	private ArrayList<NodoAstar>  altas; //amarillas
	private  boolean puedeIrDiagonal ; 


	private int[] rangoAltura;
	 
	 
	 
	 
	public Mapa(int n , int m)
	{
		rangoAltura = new int [2];
		rangoAltura[0]= Integer.MIN_VALUE;
		rangoAltura[1]= Integer.MAX_VALUE;
		
		filas=n;
		columnas=m;
		inicializa();
	}
	
	private void inicializa()
	{
		rangoAltura[0]= Integer.MIN_VALUE;
		rangoAltura[1]= Integer.MAX_VALUE;
		matriz = new NodoAstar[filas][columnas];
		inicializaMatriz();
		iniciales = new ArrayList<NodoAstar>();
		finales = new ArrayList<NodoAstar>();
		peligros = new ArrayList<NodoAstar>();
		costosas = new ArrayList<NodoAstar>();
		altas = new ArrayList<NodoAstar>();
		puedeIrDiagonal=true;
	}
	
	private void inicializaMatriz()
	{
		
    	for(int i =0; i<filas;i++)
    	{
    		for(int j=0; j<columnas;j++)
        	{
        		
        		matriz[i][j]=new NodoAstar(i,j);
        	}	
    		
    	}
   }
	
	
	private  void limpiarCamino()
	{
		
    	for(int i =0; i<filas;i++)
    	{
    		for(int j=0; j<columnas;j++)
        	{
        		
        		matriz[i][j].setCamino(false);
        		//matriz[i][j].setNodoPadre(null);
        	}	
    		
    	}
   }
	
	public void limpiarCamino(ArrayList<NodoAstar> camino)
	{
		if(camino!=null)
	    	for(NodoAstar n : camino)
	    	{
	    		n.setCamino(false);
	    		///n.setNodoPadre(null);
	    	}
		limpiarCamino();
   }
	
	public void limpiarCaminoTodo(ArrayList<ArrayList<NodoAstar>> camino)
	{
		if(camino!=null)
			for(ArrayList<NodoAstar>  n : camino)
				limpiarCamino(n);
       else
			limpiarCamino();
   }
	
	/**
	 * 
	 */
	public void limpiarMapa()
	{
		
		this.inicializa();
		this.limpiarCamino();
		
   }
	
	public void introducePeligro(int i,int j)
	{
		if(i<filas && j< columnas)
		{
				
			// si ya es prohibido, al insertarla, se quita de prohibida
			if(peligros.contains(matriz[i][j]) ) 
			{
				matriz[i][j].setTransitable(true);
				peligros.remove(matriz[i][j]);
				
			}
			//si no, compruebo si la puedo poner como prohibida
			else if( matriz[i][j].isVacia())
			{
				peligros.add(matriz[i][j]);
				matriz[i][j].setTransitable(false);
			}
			
			
		}
		//else System.err.println("cas mal  pel");
	}
	
	/**
	 * Introduzco o quito la casilla ij, como inicial
	 * @param i
	 * @param j
	 */
	public void introduceInicial(int i,int j)
	{
		if(i<filas && j< columnas)
		{
			
			if(iniciales.contains(matriz[i][j]))
			{
				iniciales.remove(matriz[i][j]);
				matriz[i][j].setInicio(false);
			}
			
			else if( matriz[i][j].isVacia())
			{
				iniciales.add(matriz[i][j]);
				matriz[i][j].setInicio(true);
			}
			
			
		}
	}
	
	public void introduceFinal(int i,int j)
	{
		if(i<filas && j< columnas)
		{
			
			if(finales.contains(matriz[i][j]))
			{
				finales.remove(matriz[i][j]);
				matriz[i][j].setFin(false);
				matriz[i][j].setCheckPoint(false);
				
				if(!finales.isEmpty())
					finales.get(finales.size()-1).setFin(true);
				
			}
			
			else if( matriz[i][j].isVacia())
			{
				if(!finales.isEmpty())
					finales.get(finales.size()-1).setFin(false);
				finales.add(matriz[i][j]);
				matriz[i][j].setCheckPoint(true);
				matriz[i][j].setFin(true);	
			}
		}
	}
	
	public void introduceCoste(int i,int j,double coste)
	{
		if(i<filas && j< columnas)
		{
			if(!costosas.contains(matriz[i][j]))
				costosas.add(matriz[i][j]);
			matriz[i][j].setCoste(coste);
			
			if(matriz[i][j].getCoste()<0.1)
			{
				
				costosas.remove(matriz[i][j]);
					
				}
			
		}
	}
	
	
	public void introduceAltura(int i,int j,int altura)
	{
		if(i<filas && j< columnas)
		{
			if(!altas.contains(matriz[i][j]))altas.add(matriz[i][j]);
			matriz[i][j].setAltura(altura);
			
			
			
		}
	}
	

	public void setPuedeIrDiagonal(boolean puedeIrDiagonal) {
		this.puedeIrDiagonal = puedeIrDiagonal;
	}
	
	public NodoAstar[][] dameMatriz()
	{
		 return  matriz;
	}
	
	public  int dameFilas()
	{
		return filas;
	}
	
	public  int dameColumnas()
	{
		return columnas;
	}
	
	
	public boolean isDiagonable()
	{
		return this.puedeIrDiagonal;
	}
	
	
	public ArrayList<NodoAstar> dameIniciales()
	{
		return iniciales;
	}
	public ArrayList<NodoAstar> dameFinales()
	{
		return finales;
	}
	public ArrayList<NodoAstar> damePeligrosas()
	{
		return peligros;
	}
	public ArrayList<NodoAstar> dameCostosas()
	{
		return costosas;
	}
	
	public void actalizaNodoIniFinal(NodoAstar nodoFinal)
	{
		for(int i =0; i<filas;i++)
    	{
    		for(int j=0; j<columnas;j++)
        	{
        		
        		matriz[i][j].setNodoFinal(nodoFinal);
        		//matriz[i][j].setNodoInicial(nodoFinal);
        		//matriz[i][j].setCoste(matriz[i][j].getF());
        	}	
    		
    	}
	}
	
	
	public int[] getRangoAltura() {
		return rangoAltura;
	}

	public NodoAstar dameNodo(int i, int j)
	{
		NodoAstar n =null;
		try
		{
			n= matriz[i][j];
		}
		catch(Exception e)
		{
			//System.err.println("No hay nodo" + i +" " +j);
		}
		
		return n;
	}
	
	public NodoAstar dameNodo(Par p)
	{
		NodoAstar n =this.dameNodo(p.getX(), p.getY());
		
		return n;
	}
	
	public void limpiarPadres()
	{
		
		for(int i =0; i<filas;i++)
    	{
    		for(int j=0; j<columnas;j++)
        	{
        		
        		matriz[i][j].setNodoPadre(null);
        	}
    	}
	}
	
	/**
	 * Dado el rango de alturas a las que vuelva el aviion, comprueba si es capaz de sobrevolar la zona 
	 * @param nodoActual
	 * @return true Si el avion puede sobrevolar la zona
	 */
	private boolean vueltaSuficientementeAlto(NodoAstar nodoActual)
	{
		int alturaCasilla= nodoActual.getAltura();
		return alturaCasilla <= rangoAltura[1];
		//return altura >= this.rangoAltura[0] && altura <= this.rangoAltura[1];
	}
	
	
	public NodoAstar dameSucesor(NodoAstar n , Accion a)
	{
		
		return dameNodo(n.getX()+a.getX(),n.getY()+ a.getY());
		
	}
	
	
	public HashMap<Accion,NodoAstar> dameSucesores(NodoAstar n )
	{
		 HashMap<Accion,NodoAstar>  suc= new  HashMap<Accion,NodoAstar> ();
		 for(Accion a: Accion.values())
		 {
			 NodoAstar s=dameSucesor(n,a);
			 if(s!=null)
				 suc.put(a,s);
		 }
		 
		 return suc;
		
	}
	
	public ArrayList<NodoAstar> dameAdyacentes(NodoAstar nodoActual)
	{
	// Extraemos los nodos adyacentes al nodo actual.
    ArrayList<NodoAstar> nodosAdyacentes = new ArrayList<NodoAstar>();

    boolean derecha = false, izquierda = false, arriba = false, abajo = false;
    
    
    if (0 <= nodoActual.getX()+1 && nodoActual.getX()+1 < columnas && 0 <= nodoActual.getY() && nodoActual.getY() < filas)
    {
            if (matriz[nodoActual.getY()][nodoActual.getX()+1].isTransitable() && vueltaSuficientementeAlto(nodoActual))
            {
                    nodosAdyacentes.add(matriz[nodoActual.getY()][nodoActual.getX()+1]);
                    derecha = true;
            }
    }
    if (0 <= nodoActual.getX()-1 && nodoActual.getX()-1 < columnas && 0 <= nodoActual.getY() && nodoActual.getY() < filas)
    {
            if (matriz[nodoActual.getY()][nodoActual.getX()-1].isTransitable() && vueltaSuficientementeAlto(nodoActual))
            {
                    nodosAdyacentes.add(matriz[nodoActual.getY()][nodoActual.getX()-1]);
                    izquierda = true;
            }
    }
    if (0 <= nodoActual.getX() && nodoActual.getX() < columnas && 0 <= nodoActual.getY()-1 && nodoActual.getY()-1 < filas)
    {
            if (matriz[nodoActual.getY()-1][nodoActual.getX()].isTransitable() && vueltaSuficientementeAlto(nodoActual))
            {
                    nodosAdyacentes.add(matriz[nodoActual.getY()-1][nodoActual.getX()]);
                    arriba = true;
            }
    }
    if (0 <= nodoActual.getX() && nodoActual.getX() < columnas && 0 <= nodoActual.getY()+1 && nodoActual.getY()+1 < filas)
    {
            if (matriz[nodoActual.getY()+1][nodoActual.getX()].isTransitable() && vueltaSuficientementeAlto(nodoActual))
            {
                    nodosAdyacentes.add(matriz[nodoActual.getY()+1][nodoActual.getX()]);
                    abajo = true;
            }
    }

    // Sólo incluidos las diagonales si las ortogonales se han incluido previamente ya que para ser 8-conectado primero debe ser 4-conectado.
    if (0 <= nodoActual.getX()+1 && nodoActual.getX()+1 < columnas && 0 <= nodoActual.getY()-1 && nodoActual.getY()-1 < filas && (puedeIrDiagonal ))//|| (arriba && derecha)))
            if (matriz[nodoActual.getY()-1][nodoActual.getX()+1].isTransitable() && vueltaSuficientementeAlto(nodoActual))
                    nodosAdyacentes.add(matriz[nodoActual.getY()-1][nodoActual.getX()+1]);

    if (0 <= nodoActual.getX()-1 && nodoActual.getX()-1 < columnas && 0 <= nodoActual.getY()-1 && nodoActual.getY()-1 < filas && (puedeIrDiagonal))// || (arriba && izquierda)))
            if (matriz[nodoActual.getY()-1][nodoActual.getX()-1].isTransitable() && vueltaSuficientementeAlto(nodoActual))
                    nodosAdyacentes.add(matriz[nodoActual.getY()-1][nodoActual.getX()-1]);

    if (0 <= nodoActual.getX()+1 && nodoActual.getX()+1 < columnas && 0 <= nodoActual.getY()+1 && nodoActual.getY()+1 < filas && (puedeIrDiagonal ))//|| (abajo && derecha)))
            if (matriz[nodoActual.getY()+1][nodoActual.getX()+1].isTransitable() && vueltaSuficientementeAlto(nodoActual))
                    nodosAdyacentes.add(matriz[nodoActual.getY()+1][nodoActual.getX()+1]);
    
    if (0 <= nodoActual.getX()-1 && nodoActual.getX()-1 < columnas && 0 <= nodoActual.getY()+1 && nodoActual.getY()+1 < filas && (puedeIrDiagonal ))//|| (abajo && izquierda)))
            if (matriz[nodoActual.getY()+1][nodoActual.getX()-1].isTransitable() && vueltaSuficientementeAlto(nodoActual))
                    nodosAdyacentes.add(matriz[nodoActual.getY()+1][nodoActual.getX()-1]);
	
    
    
    return nodosAdyacentes;
	}
}
