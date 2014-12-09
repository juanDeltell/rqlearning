package algoritmo;

import java.util.HashMap;

import practicaIC1.vista.Redondeo;


/**
 * Esta clase representa un nodo del mapa de celdas del algoritmo del A*.
 * Indica cuál es su posición (x,y) en ese mapa, así como su valor F, G, H.
 */
public class NodoAstar implements Comparable<NodoAstar>//extends ElemPrioridad<Integer,NodoAstar> //implements Comparable<NodoAstar> 
{
        /**
         * Componente x (columna) de la posición del nodo en el mapa.
         */
        private int x;

        /**
         * Componente y (fila) de la posición del nodo en el mapa.
         */
        private int y;

        /**
         * Indica si el nodo puede ser visitado o accedido.
         */
        private boolean transitable;
        
        
        /**
         * Indica si el nodo es un lugar de paso obligado.
         */
        private boolean inicio;
        
        /**
         * Indica si el nodo es un lugar de paso obligado.
         */
        private boolean checkPoint;
        

        /**
         * Indica si el nodo es un lugar de paso obligado.
         */
        private boolean fin;
        
        /**
         * Indica si el nodo forma parte del camino (uno de los caminos).
         */
        private boolean camino;

        /**
         * Coste extra del nodo. Por ejemplo, sería más costoso caminar sobre el
         * barro que sobre tierra firme.
         */
        private double coste;
        
        
        /**
         * Recompensa   del nodo. Por ejemplo, sería  va a dar prioridad  a ir a un nodo mas recompensado
         */
        private double recompensa;
        
        
        /**
         * Altura del obstaculo: si es un avion ,debera volar por encima 
         * barro que sobre tierra firme.
         */
        private int altura;
        
        /**
         * Indica si por el nodo pasan dos aviones a la vez.
         */
        private boolean choque;
        

        


		/**
         * Valor total del nodo.
         * F = G + H + coste
         */
        private double F;  //int

        /**
         * Valor desde el nodo hasta el nodo inicial.
         * Las diagonales suman 14 y las ortogonales 10.
         */
        private double G; //int

        /**
         * Valor desde el nodo hasta el nodo final.
         * Se considera el peor caso, en el no se pueden hacer diagonales, por lo
         * tanto, H + 10*(diferencia de filas + diferencia de columnas entre el nodo
         * y el nodo final).
         */
        private double H; //int
        
        private HashMap<Accion,Double> Q;
        
        /**
         * Valor desde el nodo hasta el nodo final.
         * Se considera el peor caso, en el no se pueden hacer diagonales, por lo
         * tanto, H + 10*(diferencia de filas + diferencia de columnas entre el nodo
         * y el nodo final).
         */
        private double gamma; //int
        

        /**
         * Referencia al nodo padre. Necesario para calcular G.
         */
        private NodoAstar nodoPadre;

        /**
         * Referencia al nodo final. Necesario para calcular H.
         */
        private NodoAstar nodoFinal;
        
        
        /**
         * Referencia al nodo padre. Necesario para dibujar.
         */
        private NodoAstar nodoSig;

                      
        
        /**
         * Constructor por defecto.
         * Inicializa a unos valores neutros del nodo.
         */
        public NodoAstar()
        {
        	//super(0,this);      	
            x = -1;
            y = -1;

            transitable = true;
            coste = 0;

            F = 0;
            G = 0;
            H = 0;

            nodoPadre = null;
            nodoFinal = null;
            gamma=Constantes.GAMMA_INICIAL;
            
            
            recompensa= Constantes.RECOMPENSA_BASE;
            
            Q = new HashMap<Accion,Double>();
            rellenaQ();
        }
        
        public NodoAstar(int i , int j)
        {
                x = j;
                y = i;

                transitable = true;
                coste = 0;

                F = 0;
                G = 0;
                H = 0;

                nodoPadre = null;
                nodoFinal = null;
                
                recompensa= Constantes.RECOMPENSA_BASE;
                gamma=Constantes.GAMMA_INICIAL;
                
                Q = new HashMap<Accion,Double>();
                rellenaQ();
        }

        
        private void rellenaQ(){
        	
        	for(Accion a: Accion.values())
        		Q.put(a, Double.MIN_VALUE);
        	
        		
        }
        
        /**
         * Compara dos nodos según su valor de F.
         * @param objeto Nodo con el que se va a comparar el nodo que invocó el
         * método.
         * @return Devuelve 1 si el nodo que invocó el método tiene menor F,
         * devuelve 0 si son iguales o -1 en otro caso.
         */
        public int compareTo(NodoAstar objeto)
        {
               /* if (F > ((NodoAstar) objeto).F) return 1;
                else if (F < ((NodoAstar) objeto).F) return -1;
                else return 0;*/
        	return Double.compare(this.recompensa, objeto.recompensa);
        	
        }

        /**
         * Obtiene la componente x de la posición del nodo.
         * @return Devuelve un valor entero con el número de columna de la posición
         * del nodo.
         */
        public int getX()
        {
                return x;
        }
        
        public HashMap<Accion, Double> getQ() {
			return Q;
		}

		

		/**
         * Establece un nuevo valor para la componente x (columna).
         * @param x Nuevo valor para la componente x.
         * @return Devuelve verdadero si se ha establecido el nuevo valor.
         */
        public boolean setX(int x)
        {
                if (x >= 0)
                {
                        this.x = x;
                        return true;
                }
                return false;
        }

        /**
         * Obtiene la componente y de la posición del nodo.
         * @return Devuelve un valor entero con el número de fila de la posición
         * del nodo.
         */
        public int getY()
        {
                return y;
        }

        /**
         * Establece un nuevo valor para la componente y (fila).
         * @param y Nuevo valor para la componente y.
         * @return Devuelve verdadero si se ha establecido el nuevo valor.
         */
        public boolean setY(int y)
        {
                if (y >= 0)
                {
                        this.y = y;
                        return true;
                }
                return false;
        }

        /**
         * Recalcula el valor de F. Normalmente, cuando G, H o coste han cambiado.
         */
        private void recalcularF()
        {
                F = G + H + coste;
        }

        /**
         * Recalcula el valor de G. Normalmente, cuando el padre se ha modificado.
         */
        private void recalcularG()
        {
        		if(nodoPadre!=null)
        		{
	                G = nodoPadre.G;
	                G+= Distancia.dameDistancia(nodoPadre, this);
	                G+=coste;
        		}
        		else G =0;
                recalcularF();
        }

        /**
         * Recalcula el valor de H. Normalmente, cuando el nodo final ha cambiado.
         */
        private void recalcularH()
        {
        	
                if (nodoFinal != null)
                {
                        //H = (Math.abs(x-nodoFinal.x) + Math.abs(y-nodoFinal.y))*10;
                		H = Distancia.dameDistancia(this,nodoFinal);
                }
                else
                {
                        H = 0;
                }
                recalcularF();
        }

        /**
         * Obtiene el valor F.
         * @return Devuelve un número entero que es el valor F del nodo.
         */
        public double getF()
        {
                return F;
        }

        /**
         * Obtiene el valor G.
         * @return Devuelve un número entero que es el valor G del nodo.
         */
        public double getG()
        {
                return G;
        }

        /**
         * Obtiene el valor H.
         * @return Devuelve un número entero que es el valor H del nodo.
         */
        public double getH()
        {
                return H;
        }

        
        
        
        public double getGamma() {
			return gamma;
		}

		public void setGamma(double gamma) {
			this.gamma = gamma;
		}

		/**
         * Obtiene el nodo padre.
         * @return Devuelve una referencia al nodo padre.
         */
        public NodoAstar getNodoPadre()
        {
                return nodoPadre;
        }

        /**
         * Establece un nuevo nodo padre. Recalcula G (y F) forzadamente.
         * @param nodoPadre Referencia al nodo padre que se va a asignar.
         */
        public void setNodoPadre(NodoAstar nodoPadre)
        {
                this.nodoPadre = nodoPadre;
                recalcularG();
        }

        
        /**
         * Establece un nuevo nodo padre. Recalcula G (y F) forzadamente.
         * @param nodoPadre Referencia al nodo padre que se va a asignar.
         */
        public void setNodoSig(NodoAstar nodoS)
        {
                this.nodoSig = nodoS;
                
        }
        
        
        /**
         * Obtiene el nodo final.
         * @return Devuelve una referencia al nodo final.
         */
        public NodoAstar getNodoFinal()
        {
                return nodoFinal;
        }

        /**
         * Establece un nuevo nodo final. Recalcula H (y F) forzadamente.
         * @param nodoFinal Referencia al nodo final que se va a asignar.
         */
        public void setNodoFinal(NodoAstar nodoFinal)
        {
                this.nodoFinal = nodoFinal;
                recalcularH();
        }

        /**
         * Comprueba si el nodo es transitable.
         * @return Devuelve verdadero si es transitable o falso en otro caso.
         */
        public boolean isTransitable()
        {
                return transitable;
        }

        /**
         * Establece si el nodo es transitable.
         * @param transitable Nuevo valor para la transitibilidad del nodo.
         */
        public void setTransitable(boolean transitable)
        {
                this.transitable = transitable;
        }

        /**
         * Obtiene el coste del nodo.
         * @return Devuelve un valor entero con el coste del nodo.
         */
        public double getCoste()
        {
                return coste;
        }

        /**
         * Establece un nuevo valor para el coste. Recalcula F forzadamente.
         * @param coste Nuevo valor para el coste del nodo.
         * @return Devuelve verdadero si ha podido modificar el coste. Sólo si es mayor o igual que 0.
         */
        public boolean setCoste(double coste)
        {
            if (coste >= 0)
            {
                this.coste = coste;
                recalcularG();
                return true;
            }
            return false;
        }

        
        public boolean isCostosa()
        {
        	return coste>=0.1;
        }
        
       
        
        
        public double getRecompensa() {
			return recompensa;
		}

		public void setRecompensa(double recompensa) {
			this.recompensa = recompensa;
		}

		/**
         * Devuelve si dos nodos están pegados
         * @param n
         * @return
         */
       /*
        public boolean estanPegadas(NodoAstar n)
        {
        	
        }
        */
        
        
        public int direccionSig()
        {
        	int direccion=0;
        	if(nodoSig ==null)				        								    direccion = Direcciones.NINGUNA;
        	
        	else if(nodoSig.getX()- this.getX()==1  && nodoSig.getY()- this.getY()==1)  direccion = Direcciones.ARRIBA_DERECHA;
        	else if(nodoSig.getX()- this.getX()==-1 && nodoSig.getY()- this.getY()==1)  direccion = Direcciones.ARRIBA_IZQUIERDA;
        	else if(nodoSig.getX()- this.getX()==1  && nodoSig.getY()- this.getY()==-1) direccion = Direcciones.ABAJO_DERECHA;
        	else if(nodoSig.getX()- this.getX()==-1 && nodoSig.getY()- this.getY()==-1) direccion = Direcciones.ABAJO_IZQUIERDA;
        	
        	else if(nodoSig.getX()- this.getX()==-1 && nodoSig.getY()- this.getY()==0) 	direccion = Direcciones.IZQUIERDA;
        	else if(nodoSig.getY()- this.getY()==-1 && nodoSig.getY()- this.getY()==0)	direccion = Direcciones.ABAJO;
        	else if(nodoSig.getX()- this.getX()==1  && nodoSig.getY()- this.getY()==0)	direccion = Direcciones.DERECHA;
        	else if(nodoSig.getY()- this.getY()==1  && nodoSig.getY()- this.getY()==0) 	direccion = Direcciones.ARRIBA;
        	
        	
        	return direccion;
        	//else if(Math.abs(nodoPadre.getX()- this.getX())==1) direccion =  1;
        }

		public boolean isCheckPoint() {
			return checkPoint;
		}

		public void setCheckPoint(boolean checkPoint) {
			this.checkPoint = checkPoint;
		}

		public boolean isFin() {
			return fin;
		}

		public void setFin(boolean fin) {
			this.fin = fin;
		}

		public boolean isCamino() {
			return camino;
		}

		public void setCamino(boolean camino) {
			this.camino = camino;
		}

		public boolean isInicio() {
			return inicio;
		}

		public void setInicio(boolean inicio) {
			this.inicio = inicio;
		}
       
		/**
		 * Si no tiene nada de especial (casilla en blanco)
		 * @return
		 */
		public boolean isNormal() {
			return !inicio && ! camino && !fin && !checkPoint && !this.isCostosa() && !isAlta() && isTransitable() && !this.isChoque();
		}

		/**
		 * Si le puedo añadir carcateristicas:
		 * por ejemplo  si ya es inicial, no le puedo poner que tambien es final
		 * lo unico que no restringe es que sea costosa o alta
		 * @return
		 */
		public boolean isVacia() {
			return !inicio &&  !fin && !checkPoint && isTransitable();
		}
		
		public boolean isAlta()
		{
			return altura>0;
		}
		
		public int getAltura() {
			return altura;
		}

		public void setAltura(int altura) {
			this.altura = altura;
		}
		
		
		public boolean isChoque() {
			return choque;
		}

		public void setChoque(boolean choque) {
			this.choque = choque;
		}
		
		public boolean equals(Object o)
		{
			NodoAstar na = (NodoAstar)o;
			return this.getX()== na.getX() && this.getY() == na.getY();  
		}
        
		
		
		 /**
         * Genera una cadena de caracteres con la posición del nodo (fila, columna).
         * @return Devuelve una cadena con la posición (fila, columna).
         */
        @Override
        public String toString()
        {
                return "(" + y + ", " + x + ")";
        }
		
        
        /**
         * Genera una cadena de caracteres con las caracteristicas del nodo: posicion, coste, altura,
         * si es casilla inical final, transitable,...
         * @return Devuelve las caracteristicas del nodo.
         */
		public String dameCaracteristicas()
		{
			StringBuffer sb= new StringBuffer();
			
			sb.append("Fila: "); sb.append(x);sb.append("\n");
			sb.append("Col: "); sb.append(y);sb.append("\n");
			if(this.isInicio())
				{sb.append("Inicial");sb.append("\n"); }
			if(this.isFin()){sb.append("Final");sb.append("\n");} 
			if(!this.isTransitable()){sb.append("PROHIBIDA");sb.append("\n");} 
			if(this.isCamino()){sb.append("Camino");sb.append("\n");}
			if(this.isCostosa()){
				sb.append("Coste: ");
				sb.append(Redondeo.redondear(coste, 2));
				sb.append("\n");
				}
			if(this.isAlta()){sb.append("Altura: "); sb.append(altura);sb.append("\n");}
			if(this.isChoque()){sb.append("CHOQUE"); sb.append("\n");}
			
			
			return sb.toString();
		}
		
}