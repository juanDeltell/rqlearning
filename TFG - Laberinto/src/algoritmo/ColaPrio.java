package algoritmo;
import java.util.TreeSet;


public class ColaPrio< T extends Comparable<T> > 
{
	
	TreeSet<T> s ;
	

	public ColaPrio()
	{
		
		s =  new TreeSet<T>();
	}
	
	
	public void inserta(T elem)
	{
		if(!contiene(elem))
		{
			s.add(elem);
		}
		else
		{
			reordena(elem);
		}
	}
	
	
	public void reordena(T elem)
	{
		if(contiene(elem))
		{
			s.remove(elem);
			s.add(elem);
		}
	}
	
	public boolean contiene(T elem)
	{
		return s.contains(elem);
	}
	
	public String toString()
	{
		return s.toString();
	}
	
	/*public static void main(String args[])
	{
		ColaPrio<ElemPrioridad<Integer,String>> cp = new ColaPrio<ElemPrioridad<Integer,String>>();
		
		ElemPrioridad e1 = new ElemPrioridad(1,"a");
		ElemPrioridad e2 = new ElemPrioridad(2,"b");
		ElemPrioridad e3 = new ElemPrioridad(3,"c");
		
		cp.inserta(e1);
		cp.inserta(e2);
		cp.inserta(e3);
		
		
		
		System.out.println(cp.toString());
		
		e2.actualizaPrioridad(4);
		cp.reordena(e2);
		
		System.out.println(cp.toString());
		
	}
	*/
}
