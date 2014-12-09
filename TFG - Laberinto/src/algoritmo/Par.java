package algoritmo;

public class Par {
	private int x,y;
	
	Par(){
		
		x =-1;
		y = -1;
	}
	
	Par(int x,int y){
		this.setX(x);
		this.setY(y);
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}
}
