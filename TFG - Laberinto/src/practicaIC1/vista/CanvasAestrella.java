package practicaIC1.vista;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;





import java.util.TreeSet;

import javax.swing.ImageIcon;

import algoritmo.Constantes;
import algoritmo.Mapa;
import algoritmo.NodoAstar;


public class CanvasAestrella extends Canvas
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private int ancho;
	private int alto;
	private int casAlto;
	
	private int ancho;
	private int casAncho;
	
	int filas;
	int columnas;
	
	private Mapa mapa;
	private ArrayList<NodoAstar> camino;
	
	

	
	VentanaAEstrella v ;
	//JLabel e;
	
	public CanvasAestrella(Mapa m , ArrayList<NodoAstar> c)
	{
		mapa=m;
		filas= m.dameFilas();
		columnas = m.dameColumnas();
		camino=c;
			
		
		alto=800;
		ancho=800;
		
		casAlto= alto/filas;
		casAncho= ancho/columnas;
		
	}
	
	
	public void paint(Graphics g)
	{
		/*
		pintarCuadricula(g);
		pintarCamino(g);
		pintarPeligros(g);
		pintarCostosas(g);
		pintarOrigen(g);
		pintarDestinoYCheckPoint(g);*/
		
		
		
		for(int f=0;f< filas;f++)
		{
			for(int c=0;c< columnas;c++)
			{
				g.setColor(Color.black);
				
				pintarNodo(g,f,c,mapa.dameNodo(f, c));
				
				//g.setColor(Color.black);
				//g.drawRect(casAncho*c, casAlto*f, casAncho, casAlto);
				
				//g.fillRect(casAncho*c, casAlto*f, casAncho, casAlto);
			}
		}
		pintarCuadricula(g);
		
		
		//System.out.println("pinta canvas");
	}
	
	public void pintarNodo(Graphics g, int x, int y,NodoAstar n)
	{
		g.setColor(Color.white);
		
		//depende  de la altura tendra una tonalidad de amarillo
		if(n.isAlta())	
		{
			g.setColor(Color.yellow);
			if(n.getAltura()< Constantes.MINIMA_ALTURA) g.setColor(Color.yellow.brighter());
			if(n.getAltura()> Constantes.MAXIMA_ALTURA) g.setColor(Color.yellow.darker());
					
			
		}
		//depende  del coste tendra una tonalidad de gris
		if(n.isCostosa())	
		{
			if(n.getCoste()>0)g.setColor(Color.gray);
			
			if(n.getCoste()< Constantes.MINIMO_COSTE) g.setColor(Color.LIGHT_GRAY);
			if(n.getCoste()> Constantes.MAXIMO_COSTE) g.setColor(Color.DARK_GRAY);
					
		}
		
		
		if(n.isNormal())	g.setColor(Color.white);
		if(n.isCamino())	g.setColor(Color.magenta);
		if(n.isChoque())	g.setColor(Color.orange);
		if(n.isCheckPoint())
		{
			g.setColor(Color.GREEN);
			
			
		}
		if(n.isFin())		g.setColor(Color.red);
		if(n.isInicio())	g.setColor(Color.CYAN);
		if(!n.isTransitable())	g.setColor(Color.black);
		
		
		
		
		
		//el mejor
		//g.fillRect( x*casAlto, y*casAncho,casAlto,casAncho);
		
		//de los mejores
		g.fillRect( y*casAncho, x*casAlto,casAncho,casAlto);
		//posible
		//g.fillRect( x*casAlto, y*casAncho,casAlto,casAlto);
		//g.fillRect( y*casAncho, x*casAlto,casAlto,casAlto);
		//g.fillRect( y*casAlto, x*casAlto,casAlto,casAlto);
		
		
		//g.fillRect( x*casAncho, y*casAlto,casAlto,casAncho);
		//g.fillRect( x*casAncho, y*casAlto,casAlto,casAlto);
		//g.fillRect( x*casAncho, y*casAlto,casAncho,casAncho);
		//g.fillRect( x*casAncho, y*casAlto,casAncho,casAlto);
		//g.fillRect( x*casAncho, y*casAncho,casAlto,casAncho);
		//g.fillRect( x*casAncho, y*casAncho,casAlto,casAlto);
		//g.fillRect( x*casAncho, y*casAncho,casAncho,casAncho);
		//g.fillRect( x*casAncho, y*casAncho,casAncho,casAlto);
		
		//g.fillRect( x*casAlto, y*casAlto,casAlto,casAncho);
		//g.fillRect( x*casAlto, y*casAlto,casAlto,casAlto);
		//g.fillRect( x*casAlto, y*casAlto,casAncho,casAncho);
		//g.fillRect( x*casAlto, y*casAlto,casAncho,casAlto);
		//g.fillRect( x*casAlto, y*casAncho,casAlto,casAncho);
		//g.fillRect( x*casAlto, y*casAncho,casAlto,casAlto);
		//g.fillRect( x*casAlto, y*casAncho,casAncho,casAncho);
		//g.fillRect( x*casAlto, y*casAncho,casAncho,casAlto);
		
		//g.fillRect( y*casAncho, x*casAlto,casAlto,casAncho);
		//g.fillRect( y*casAncho, x*casAlto,casAlto,casAlto);
		//g.fillRect( y*casAncho, x*casAlto,casAncho,casAncho);
		//g.fillRect( y*casAncho, x*casAlto,casAncho,casAlto);
		//g.fillRect( y*casAncho, x*casAncho,casAlto,casAncho);
		//g.fillRect( y*casAncho, x*casAncho,casAlto,casAlto);
		//g.fillRect( y*casAncho, x*casAncho,casAncho,casAncho);
		//g.fillRect( y*casAncho, x*casAncho,casAncho,casAlto);
		
		//g.fillRect( y*casAlto, x*casAlto,casAlto,casAncho);
		//g.fillRect( y*casAlto, x*casAlto,casAlto,casAlto);
		//g.fillRect( y*casAlto, x*casAlto,casAncho,casAncho);
		//g.fillRect( y*casAlto, x*casAlto,casAncho,casAlto);
		//g.fillRect( y*casAlto, x*casAncho,casAlto,casAncho);
		//g.fillRect( y*casAlto, x*casAncho,casAlto,casAlto);
		//g.fillRect( y*casAlto, x*casAncho,casAncho,casAncho);
		//g.fillRect( y*casAlto, x*casAncho,casAncho,casAlto);

		
		
		Toolkit t= Toolkit.getDefaultToolkit();
		Image img=null;
		
		String path = new File("").getAbsolutePath();
	
		
		if(n.isChoque())
		{
			
			URL urlDeLaImagen = this.getClass().getClassLoader().getResource("explosion.png");
			ImageIcon icono = new ImageIcon(urlDeLaImagen);
			
			// o bien, si queremos la imagen
			img = icono.getImage();
			
			g.drawImage(img,y*casAncho, x*casAlto,casAncho,casAlto, this);
		}
		
		if(n.isFin())
		{
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			
		
			
			URL urlDeLaImagen = this.getClass().getClassLoader().getResource("meta.jpg");
			ImageIcon icono = new ImageIcon(urlDeLaImagen);
			
			// o bien, si queremos la imagen
			img = icono.getImage();
			
			
			
			g.drawImage(img,y*casAncho, x*casAlto,casAncho,casAlto, this);
			//System.out.println(resource.toString());
		}
			
		
		if(n.isCheckPoint() && !n.isFin())
		{
			
			
			g.setColor(Color.black);
			
			int i= mapa.dameFinales().indexOf(n) +1;
			g.drawString(Integer.toString(i) , y*casAncho+10, x*casAlto+10);
			
		}
		
		if(n.isInicio())
		{
			
			
			g.setColor(Color.black);
			
			int i= mapa.dameIniciales().indexOf(n) +1;
			g.drawString(Integer.toString(i) , y*casAncho+10, x*casAlto+10);
			
		}
		
	}
	
	
	public void pintarCuadricula(Graphics g)
	{
		g.setColor(Color.BLACK);
		for(int f=0;f<=filas;f++)
			g.drawLine(0, casAlto*f, ancho, casAlto*f); //lineas horizonales
		
		for(int c=0;c<=columnas;c++)
			g.drawLine(casAncho*c, 0, casAncho*c,alto);  //lineas verticales
			
	}
	
	
	public void pintarImagenNodo(Graphics g, int x, int y,NodoAstar n)
	{	
		Toolkit t= Toolkit.getDefaultToolkit();
		Image img=t.getImage(NombreImagen.nombreFoto(n));
	

		//g.drawImage(img, x*casAncho, y*casAlto,casAlto,casAncho, this);
		//g.drawImage(img, x*casAncho, y*casAlto,casAlto,casAlto, this);
		//g.drawImage(img, x*casAncho, y*casAlto,casAncho,casAncho, this);
		//g.drawImage(img, x*casAncho, y*casAlto,casAncho,casAlto, this);
		//g.drawImage(img, x*casAncho, y*casAncho,casAlto,casAncho, this);
		//g.drawImage(img, x*casAncho, y*casAncho,casAlto,casAlto, this);
		//g.drawImage(img, x*casAncho, y*casAncho,casAncho,casAncho, this);
		//g.drawImage(img, x*casAncho, y*casAncho,casAncho,casAlto, this);
		
		//g.drawImage(img, x*casAlto, y*casAlto,casAlto,casAncho, this);
		//g.drawImage(img, x*casAlto, y*casAlto,casAlto,casAlto, this);
		//g.drawImage(img, x*casAlto, y*casAlto,casAncho,casAncho, this);
		//g.drawImage(img, x*casAlto, y*casAlto,casAncho,casAlto, this);
		//g.drawImage(img, x*casAlto, y*casAncho,casAlto,casAncho, this);
		//g.drawImage(img, x*casAlto, y*casAncho,casAlto,casAlto, this);
		//g.drawImage(img, x*casAlto, y*casAncho,casAncho,casAncho, this);
		//g.drawImage(img, x*casAlto, y*casAncho,casAncho,casAlto, this);
		
		//g.drawImage(img, y*casAncho, x*casAlto,casAlto,casAncho, this);
		//g.drawImage(img, y*casAncho, x*casAlto,casAlto,casAlto, this);
		//g.drawImage(img, y*casAncho, x*casAlto,casAncho,casAncho, this);
		//g.drawImage(img, y*casAncho, x*casAlto,casAncho,casAlto, this);
		//g.drawImage(img, y*casAncho, x*casAncho,casAlto,casAncho, this);
		//g.drawImage(img, y*casAncho, x*casAncho,casAlto,casAlto, this);
		//g.drawImage(img, y*casAncho, x*casAncho,casAncho,casAncho, this);
		//g.drawImage(img, y*casAncho, x*casAncho,casAncho,casAlto, this);
		
		//g.drawImage(img, y*casAlto, x*casAlto,casAlto,casAncho, this);
		//g.drawImage(img, y*casAlto, x*casAlto,casAlto,casAlto, this);
		//g.drawImage(img, y*casAlto, x*casAlto,casAncho,casAncho, this);
		//g.drawImage(img, y*casAlto, x*casAlto,casAncho,casAlto, this);
		//g.drawImage(img, y*casAlto, x*casAncho,casAlto,casAncho, this);
		//g.drawImage(img, y*casAlto, x*casAncho,casAlto,casAlto, this);
		//g.drawImage(img, y*casAlto, x*casAncho,casAncho,casAncho, this);
		//g.drawImage(img, y*casAlto, x*casAncho,casAncho,casAlto, this);
		
		g.drawImage(img, x*casAncho, y*casAlto,casAncho,casAlto, this);
	}
	
	
	
	
	
	
	
	/*private void pintarCasilla(Graphics g,int i, int j, Color color)
	{
		g.setColor(color);
		g.fillRect(casAncho*i, casAncho*j, casAncho, casAncho);
	}
	
	*/
	
	/*
	
	public void pintarPeligros(Graphics g)
	{
		for(NodoAstar n:mapa.damePeligrosas())
		{
			pintarCasilla(g, n.getX(), n.getY(), Color.BLACK);
		}
	}
	
	public void pintarCostosas(Graphics g)
	{
		for(NodoAstar n:mapa.dameCostosas())
		{
			pintarCasilla(g, n.getX(), n.getY(), Color.GRAY);
		}
	}
	
	public void pintarOrigen(Graphics g)
	{
		for(NodoAstar n:mapa.dameIniciales())
		{
			pintarCasilla(g, n.getX(), n.getY(), Color.blue);
		}
	}
	
	public void pintarCamino(Graphics g)
	{
		for(NodoAstar n:camino)
		{
			pintarCasilla(g, n.getX(), n.getY(), Color.MAGENTA);
		}
	}
	
	public void pintarDestinoYCheckPoint(Graphics g)
	{
		for(int i=0;i< mapa.dameFinales().size()-1;i++)
		{
			NodoAstar n = mapa.dameFinales().get(i);
			pintarCasilla(g, n.getX(), n.getY(), Color.green);
		}
		
		NodoAstar destino = mapa.dameFinales().get(mapa.dameFinales().size()-1);
		pintarCasilla(g, destino.getX(), destino.getY(), Color.red);
	}
	
	
	*/
	
	
	
	
	
	
	
	
	public int dameCasAncho()
	{
		return casAncho;
	}
	public int dameCasAlto()
	{
		return casAncho;
	}

	public ArrayList<NodoAstar> getCamino() {
		return camino;
	}
	public void setCamino(ArrayList<NodoAstar> camino) {
		this.camino = camino;
	}


	
	
	
	
	
}
