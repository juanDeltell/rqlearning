package algoritmo;

public class Casilla {
	private boolean transitable;
	private double gamma;
	
	Casilla(){
		this.transitable = true;
		this.gamma = 1;
	}

	public double getGamma() {
		return gamma;
	}

	public void setGamma(double gamma) {
		if(gamma!=0)
			this.gamma = gamma;
	}

	public boolean isTransitable() {
		return transitable;
	}

	public void setTransitable(boolean transitable) {
		this.transitable = transitable;
	}
}
