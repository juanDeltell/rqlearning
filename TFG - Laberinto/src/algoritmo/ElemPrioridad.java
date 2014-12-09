package algoritmo;

public class ElemPrioridad<T extends Comparable<T>,S> implements Comparable<ElemPrioridad<T,S>>
{
	private T prioridad;
	private S elem;
	
	
	
	public ElemPrioridad(T t,S s)
	{
		this.prioridad=t;
		this.elem=s;
	}



	public T getPrioridad() {
		return prioridad;
	}



	public void actualizaPrioridad(T prioridad) {
		this.prioridad = prioridad;
	}



	public S getElem() {
		return elem;
	}

	public boolean equals(Object o)
	{
		@SuppressWarnings("unchecked")
		ElemPrioridad<T,S> e = (ElemPrioridad<T,S>)o;
		return elem.equals(e.elem);
	}

	
	public String toString()
	{
		//return prioridad.toString() + " " + elem.toString();
		return "("+prioridad.toString() + ")" + elem.toString();
	}


	@Override
	public int compareTo(ElemPrioridad<T, S> o) 
	{
		
		return this.prioridad.compareTo(o.prioridad);
	}



	
	
	
	
	

}
