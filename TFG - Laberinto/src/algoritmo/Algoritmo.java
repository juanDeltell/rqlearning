package algoritmo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public interface Algoritmo
{
	public void reejecuta();
	public ArrayList<ArrayList<NodoAstar>> dameCamino();
	public Mapa dameMapa();
	public HashMap<Integer,Double> getCoste();
	public void reinicia();
	public Set<Integer> getInicialesSInCamino();
}
