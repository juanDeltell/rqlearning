package algoritmo;

public class Distancia
{

	private  static double calculaDistanciaEuclidea(NodoAstar nodoActual,	NodoAstar nodoAdyacente)
    {
			
     	double  c1= Math.pow ((double)(nodoActual.getX()- nodoAdyacente.getX()), 2);
     	double  c2=Math.pow((double)(nodoActual.getY()- nodoAdyacente.getY()), 2);
		
     	return Math.sqrt( c1 +c2)   ;
	}
	
	
	/*private  static double calculaDistanciaManhatan(NodoAstar nodoActual,	NodoAstar nodoAdyacente)
    {
	  	return 0  ;
	}
	
	private  static double calculaDistanciaOtra(NodoAstar nodoActual,	NodoAstar nodoAdyacente)
    {
	   	return 0  ;
	}*/
	
	
	
	public static double dameDistancia(NodoAstar nodoActual,	NodoAstar nodoAdyacente)
    {
			
     	return calculaDistanciaEuclidea(nodoActual,nodoAdyacente)   ;
	}
	
	
	
}
