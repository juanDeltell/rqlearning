package algoritmo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * Implementación del algoritmo A* con Java.
 * Dada una matriz de nodos (NodoAstar) e indicando cuál es el nodo de salida
 * y el nodo de llegada, calcula el mejor camino posible que hay que seguir
 * evitando los obstáculos (es decir, nodos no transitables).
 */
public class AlgoritmoA  implements Algoritmo
{
		private Mapa mapa;
		private NodoAstar nodoFinal;
		private NodoAstar nodoInicial;
		
		private  Deap listaAbierta; ///mas eficiente
		
		
		private ArrayList<ArrayList<NodoAstar>> camino ;  //private ArrayList< ArrayList<NodoAstar>> camino ;
		private ArrayList<NodoAstar> listaCerrada;
		private boolean caminoEncontrado;
		
		
		private HashMap<Integer,Double> coste;
		private ArrayList<NodoAstar> caminoAux;
		
		private TreeSet<Integer> inicialesSInCamino;
       
        public AlgoritmoA (Mapa m)
        {
                mapa = m;
                coste = new HashMap<Integer,Double>();
                camino = new   ArrayList<ArrayList<NodoAstar>>();
               
        }

        
        public  void reejecuta()
        {
        	inicialesSInCamino = new TreeSet<Integer>();;
        	camino = new   ArrayList<ArrayList<NodoAstar>>();//ArrayList< ArrayList<NodoAstar>>();
        	coste = new  HashMap<Integer,Double>();
        	 
        	caminoEncontrado = false;
        	mapa.limpiarCaminoTodo(camino);
        	NodoAstar aux=null;
        	
        	/*for(int i=0;i< mapa.dameIniciales().size();i++)
        		 coste.add((double)Integer.MAX_VALUE);*/
        	
            for(NodoAstar ini : mapa.dameIniciales())
            {
	        	caminoAux = new   ArrayList<NodoAstar>();
	        	aux=null;
	        	caminoEncontrado = false;
	        	
	        	mapa.limpiarPadres();
	        		
	        	nodoInicial=ini;
	            for(NodoAstar fin : mapa.dameFinales())
	            {
	            	//if(aux==null ) 	nodoInicial = ini;
	            	//else 			nodoInicial= aux;
	            	
	            	nodoFinal= fin;
	            	mapa.actalizaNodoIniFinal(nodoFinal);
	            	
	            	
	            	listaAbierta = new Deap();
	                listaCerrada = new ArrayList<NodoAstar>(); //ElemPrioridad<Integer,NodoAstar>
	                
	                listaCerrada.addAll(caminoAux);
	            	ejecuta();
	                //aux=nodoFinal; 
	            	nodoInicial=nodoFinal;
	                if(!this.caminoEncontrado)
	                	{
	                		
	                		mapa.limpiarCamino(null);
	                		camino = new   ArrayList<ArrayList<NodoAstar>>();
	                		coste = new  HashMap<Integer,Double>();
	                		break;
	                	
	                	}
	            }
	          //  if(!this.caminoEncontrado)break;
	            
	            // Si hemos llegado al nodo final, volvemos hacia atrás desde ese nodo extrayendo el camino hasta el nodo inicial.
	           /*
	            if (caminoEncontrado)
	            {
	            	System.out.println("Encuentra el camino");
	            	calculaCamino();  
	            }
	            else
	            {
	            	caminoAux = new ArrayList<NodoAstar>();
	            	System.out.println("no encuentra el camino aa");
	                
	            }*/
		           
            }
           
         	
        }
        
        /**
         * Ejecuta el algoritmo de A*, calculando el camino, si existe, desde el
         * punto de inicio hasta el punto final.
         * 
         */
        private  void ejecuta()
        {
        	//listaAbierta = new Deap();
           // listaCerrada = new ArrayList<NodoAstar>(); //ElemPrioridad<Integer,NodoAstar>
            NodoAstar nodoActual = null;
            caminoEncontrado = false;
                       
            
            // Añadimos el cuadro inicial a la lista abierta.
            listaAbierta.push(nodoInicial);

           
            // Buscamos el camino mientras queden nodos candidatos y no lo hayamos encontrado.
            while (!listaAbierta.isEmpty() && !caminoEncontrado)
            {
        		// Extraemos el nodo de menor F desde la lista abierta hacia la lista cerrada.
                nodoActual = (NodoAstar) listaAbierta.popBottom();
                listaCerrada.add(nodoActual);
                ArrayList<NodoAstar> nodosAdyacentes = mapa.dameAdyacentes(nodoActual);
                gestionarAdyacentes(nodosAdyacentes,nodoActual);
            }

            //QUITAR PARA QUE , SI AL HABER WAY POINTS, NO LO CUENTE COMO REPETIDO
            // Si hemos llegado al nodo final, volvemos hacia atrás desde ese nodo extrayendo el camino hasta el nodo inicial.
            if (caminoEncontrado)
            {
            	//System.out.println("Encuentra el camino");
            	calculaCamino();  
            }
            else
            {
            	caminoAux = new ArrayList<NodoAstar>();
            	//System.out.println("no encuentra el camino aa");
            	this.inicialesSInCamino.add(mapa.dameIniciales().indexOf(nodoInicial)+1);
                
            }
    }
        
        
        private void gestionarAdyacentes(ArrayList<NodoAstar> nodosAdyacentes,NodoAstar nodoActual  )
        {
        	// Para cada nodo encontrado, comprobamos si hemos llegado al punto de destino.
            while (!nodosAdyacentes.isEmpty() && !caminoEncontrado)
            {
                    NodoAstar nodoAdyacente = nodosAdyacentes.remove(0);
                    //no esta en la cerrada
                    if (!listaCerrada.contains(nodoAdyacente))
                    {
                    		//no esta en la abierta
                            if (!listaAbierta.contains(nodoAdyacente))
                            {
                                    nodoAdyacente.setNodoPadre(nodoActual);
                                   
                                    listaAbierta.push(nodoAdyacente);

                                    if (nodoFinal == nodoAdyacente)
                                    {
                                            caminoEncontrado = true;
                                    }
                            }
                          // esta en la abierta, pero no en la cerrada
                            else
                            {
                            		
                                    double nuevoG = nodoActual.getG();
                                    nuevoG +=Distancia.dameDistancia(nodoAdyacente, nodoActual);
                                    
                                    if (nuevoG < nodoAdyacente.getG())
                                    {
                                            nodoAdyacente.setNodoPadre(nodoActual);
                                            listaAbierta.reordenar();
                                    }
                            }
                    }
            }
        }
        
      
        public void reinicia()
        {
        	this.mapa.limpiarMapa();
        	//this.setCamino(null);
        	this.setCamino(new ArrayList<ArrayList<NodoAstar>>());
        }
        
        
        private void calculaCamino()
        {
        	
        	
             NodoAstar nodoAuxiliar = nodoFinal;
             while (nodoAuxiliar != null)
             {
            	 caminoAux.add(0, nodoAuxiliar);
                 nodoAuxiliar = nodoAuxiliar.getNodoPadre();
             }
                       
             
             for(NodoAstar n : caminoAux )
	        	 n.setCamino(true);
            
            NodoAstar nodoInicial = caminoAux.get(0);          
             
            //compruebo choques
           for(int i=0; i<caminoAux.size();i++)
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
            }
             
             
             camino.add(caminoAux);
            
             coste.put(mapa.dameIniciales().indexOf(nodoInicial)+1,caminoAux.get(caminoAux.size()-1).getF());  
           
            // double costeTemp = mapa.dameFinales().get( mapa.dameFinales().size()-1).getF();
           
             
             ///varios caminos
             /*
             double costeTemp = caminoAux.get( caminoAux.size()-1).getF();
             if(camino==null || camino.isEmpty()) 
             {
            	 camino = caminoAux;
            	 coste= caminoAux.get( caminoAux.size()-1).getF();
             }
             else if(coste > costeTemp)
            {
	        	 //mapa.limpiarCamino(camino);
	        	
	        	 camino = caminoAux;
	        	 coste = costeTemp;
	        		        	 	        	 
	        	 for(NodoAstar n : camino )
	        		 n.setCamino(true);
            }
	         
	         if(camino==null)
	        	 camino = caminoAux;
             */
             
             
             
             /* Para pintar flechas 
             for(int i=0; i< camino.size()-1;i++)
             {
            	 NodoAstar n= caminoAux.get(i);
            	 n.setCamino(true);
            	 NodoAstar sig= caminoAux.get(i+1);
            	 n.setNodoSig(sig);
            	 
             }*/
             
            
        }
        
        
       

		
        //ArrayList<ArrayList<NodoAstar>>   
        public ArrayList<ArrayList<NodoAstar>> dameCamino()
        {
        	return camino;
        }
        
        
        public Mapa dameMapa()
        {
        	return mapa;
        }

		public  HashMap<Integer,Double> getCoste() {
			return coste;
		}


		public ArrayList<ArrayList<NodoAstar>> getCamino() {
			return camino;
		}


		public void setCamino(ArrayList<ArrayList<NodoAstar>> camino) {
			this.camino = camino;
		}

		
        
		public TreeSet<Integer> getInicialesSInCamino() {
			return inicialesSInCamino;
		}
		
		
        
        

		

		
}
