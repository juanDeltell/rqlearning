package algoritmo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Esta clase representa un mont�culo doble.
 * Permite insertar objetos (que deben implementar la interfaz "Comparable") y
 * extraerlos en orden creciente o decreciente; s�lo puede obtenerse el m�ximo o
 * el m�nimo del mont�culo en un determinado momento.
 */
public class Deap implements Iterable
{
        /**
         * Vector interno que contiene los elementos del mont�culo.
         */
        private ArrayList elementos;

        /**
         * Constructor por defecto.
         * Deja el mont�culo vac�o.
         */
        public Deap()
        {
                elementos = new ArrayList();
        }

        /**
         * Inserta un elemento en el mont�culo.
         * @param objeto Objeto que va a ser introducido. La clase del objeto
         * debe disponer del m�todo "compareTo".
         * @return Devuelve verdadero si ha introducido el elemento.
         */
        public boolean push(Comparable objeto)
        {
                boolean insertado = false;
                if (objeto != null)
                {
                        /*
                         * Evidentemente, �sta no es la implementaci�n de inserci�n de
                         * un mont�culo doble ya que lo �nico que hace es ordenar todos
                         * los elementos.
                         * Cuando se inserta un elemento en un mont�culo, se realiza una
                         * ordenaci�n parcial que mantiene el elemento m�ximo y m�nimo
                         * en la cima del mont�culo. En este caso, se realiza una ordenaci�n
                         * total (con su correspondiente p�rdida de eficiencia).
                         * El borrado tambi�n deber�a reestructurar el mont�culo cuando se
                         * mejore este m�todo.
                         */
                        elementos.add(objeto);
                        Collections.sort(elementos);
                        insertado = true;
                }
                return insertado;
        }

        
        public boolean pushAll(List<Comparable> col)
        {
        	boolean b=true;
        	for(Comparable objeto: col)
        	{
               boolean b1=this.push(objeto);
               if(!b1) b=false;
        	}
        	return b;
        }

        
        /**
         * Elimina el elemento m�ximo del mont�culo y lo devuelve.
         * @return Devuelve una referencia al objeto extraido del mont�culo. Si no
         * existe ning�n elemento, devuelve null.
         */
        public Object popTop()
        {
                if (!elementos.isEmpty())
                {
                        return elementos.remove(elementos.size()-1);
                }
                else
                {
                        return null;
                }
        }

        /**
         * Elimina el elemento m�nimo del mont�culo y lo devuelve.
         * @return Devuelve una referencia al objeto extraido del mont�culo. Si no
         * existe ning�n elemento, devuelve null.
         */
        public Object popBottom()
        {
                if (!elementos.isEmpty())
                {
                        return elementos.remove(0);
                }
                else
                {
                        return null;
                }
        }

        /**
         * Obtiene una referencia al elemento m�ximo sin extraerlo del mont�culo.
         * @return Devuelve una referencia al objeto. Si no existe ning�n elemento,
         * devuelve null.
         */
        public Object top()
        {
                if (!elementos.isEmpty())
                {
                        return elementos.get(elementos.size()-1);
                }
                else
                {
                        return null;
                }
        }

        /**
         * Obtiene una referencia al elemento m�nimo sin extraerlo del mont�culo.
         * @return Devuelve una referencia al objeto. Si no existe ning�n elemento,
         * devuelve null.
         */
        public Object bottom()
        {
                if (!elementos.isEmpty())
                {
                        return elementos.get(0);
                }
                else
                {
                        return null;
                }
        }

        /**
         * Comprueba si un objeto est� en el mont�culo.
         * @param objeto Objeto que va a ser comprobado.
         * @return Devuelve verdadero si est� en el mont�culo; falso en otro caso.
         */
        public boolean contains(Object objeto)
        {
                return elementos.contains(objeto);
        }

        /**
         * Fuerza la reordenaci�n del mont�culo. Si alg�n elemento del mont�culo
         * sufre una modificaci�n, ser� necesario forzar la reordenaci�n por si
         * ese elemento ha dejado de ser un m�ximo o un m�nimo, o por si ahora
         * es un m�ximo o un m�nimo.
         */
        public void reordenar()
        {
                Collections.sort(elementos);
        }

        /**
         * Deja el mont�culo vac�o.
         */
        public void clear()
        {
                elementos.clear();
        }

        /**
         * Obtiene el n�mero de elementos que hay en el mont�culo.
         * @return Devuelve un valor natural includo el cero.
         */
        public int size()
        {
                return elementos.size();
        }

        /**
         * Comprueba si el mont�culo est� vac�o.
         * @return Devuelve verdadero si el mont�culo est� vac�o.
         */
        public boolean isEmpty()
        {
                return elementos.isEmpty();
        }

        /**
         * Obtiene una cadena de caracteres con el contenido del mont�culo.
         * El formato coincide con el de un ArrayList (corchetes al principio y
         * al final y una coma y un espacio entre cada elemento). Los objetos
         * introducidos en el mont�culo deben sobreescribir el m�todo toString.
         * @return Devuelve una cadena de caracteres con los elementos del mont�culo.
         */
        @Override
        public String toString()
        {
                return elementos.toString();
        }

        /**
         * Obtiene los elementos en un vector, ordenados de menor a mayor.
         * @return Devuelve un vector del tama�o del mont�culo.
         */
        public Object[] toArray()
        {
                int tamano = elementos.size();
                Object[] vector = new Object[tamano];
                for (int i = 0; i < tamano; i++)
                {
                        vector[i] = elementos.get(i);
                }
                return vector;
        }

		@Override
		public Iterator iterator() {
			
			return elementos.iterator();
		}

}
