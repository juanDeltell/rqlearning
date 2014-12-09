package algoritmo;

public enum Accion {
	
	ARRIBA(0,-1),
	ABAJO(0,1),
	IZQUIERDA(-1,0),
	DERECHA(1,0);
	
	
	
	private Accion(int x, int y) {
		this.x = x;
		this.y = y;
	}
	private int x;
	private int y;
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	
	
}
