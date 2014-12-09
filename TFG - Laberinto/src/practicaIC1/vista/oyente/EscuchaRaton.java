package practicaIC1.vista.oyente;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import algoritmo.*;
import practicaIC1.vista.*;



public class EscuchaRaton implements MouseListener {

	
	CanvasAestrella canvas;
	JTextField txtFila;
	JTextField txtColumna;
	Algoritmo aa;
	
	public EscuchaRaton(CanvasAestrella cv)
	{
		canvas=cv;
	}
	
	
	public EscuchaRaton(CanvasAestrella c, JTextField tf,JTextField tc, Algoritmo a) 
	{
		canvas=c;
		txtFila=tf;
		txtColumna=tc;
		aa=a;
		
	}
	
	
	public EscuchaRaton(CanvasAestrella c, Algoritmo a) 
	{
		canvas=c;
		
		aa=a;
		
	}


	public void accion(MouseEvent arg0)
	{
		Point p = arg0.getPoint();
		
		
		int col  = p.x/canvas.dameCasAlto();
		int fila= p.y/canvas.dameCasAncho();
		
		
		/*
		int col  = p.x/canvas.dameCasAncho();
		int fila= p.y/canvas.dameCasAlto();
		*/
		
		/*
		int col  = p.y/canvas.dameCasAlto();
		int fila= p.x/canvas.dameCasAncho();
		*/
		/*
		int col  = p.y/canvas.dameCasAncho();
		int fila= p.x/canvas.dameCasAlto();
		*/
		
		//int fila  = p.x/canvas.dameCasAlto();
		//int col= p.y/canvas.dameCasAncho();
		
		
		
		//g.fillRect( x*casAlto, y*casAncho,casAlto,casAncho);
		
	
		
		if(aa.dameMapa().dameNodo(fila, col)!=null)
		{
			
			if(Estado.estado == Modo.REEJECUTA.getNum())
				{
					aa.reejecuta();
					VentanaAEstrella v = VentanaAEstrella.dameInst();
					v.repaint();
					//v.getLblCoste().setText(Double.toString(aa.getCoste()));
					//v.getLblCamino().setText(aa.dameCamino().toString());
					
				//System.out.println("Ejecuta en mouse event accion 1");
				}
			
			else if(Estado.estado== Estado.PELIGRO)
				aa.dameMapa().introducePeligro(fila, col);
			
			else if(Estado.estado== Estado.INICIAL)
				aa.dameMapa().introduceInicial(fila, col);
			
			else if(Estado.estado== Estado.ALTURAMAS)
				aa.dameMapa().introduceAltura(fila, col,aa.dameMapa().dameNodo(fila, col).getAltura() + Constantes.INCREMENTO_ALTURA );//cada vez que pulos eleva la alura 1
			
			else if(Estado.estado== Estado.COSTEMAS)
				aa.dameMapa().introduceCoste(fila, col,aa.dameMapa().dameNodo(fila, col).getCoste() + Constantes.INCREMENTO_COSTE );//cada vez que pulos eleva la alura 1
			else if(Estado.estado== Estado.ALTURAMENOS)
				aa.dameMapa().introduceAltura(fila, col,aa.dameMapa().dameNodo(fila, col).getAltura() - Constantes.INCREMENTO_ALTURA );//cada vez que pulos eleva la alura 1
			
			else if(Estado.estado== Estado.COSTEMENOS)
				aa.dameMapa().introduceCoste(fila, col,aa.dameMapa().dameNodo(fila, col).getCoste() - Constantes.INCREMENTO_COSTE );//cada vez que pulos eleva la alura 1
			
			
			else if(Estado.estado== Estado.CHECK)
				aa.dameMapa().introduceFinal(fila, col);
			
			
			else if(Estado.estado== Estado.INFO)
			{
				NodoAstar n = aa.dameMapa().dameNodo(fila, col);
				String msg= n.dameCaracteristicas(); //n.toString();
				JOptionPane.showMessageDialog(null, msg , "Info de casilla "+n.getX()+" "+n.getY()  ,1);
			}
			
			else if(Estado.estado== Estado.RANGO)
			{
				try
				{
					VentanaAEstrella v = VentanaAEstrella.dameInst(); 
					int altura = Integer.valueOf(v.getTxtAltmax().getText());
					v.getAa().dameMapa().getRangoAltura()[1]=altura;
				}
				catch(Exception e)
				{
					
				}
			}
			
			
		}
		else System.err.println("casilla incorrecta");
		
			
	
		VentanaAEstrella.dameInst().repaint();
		//canvas.repaint();
	}
	
	
	
	public void infomracion()
	{
		
	}
	
	public void accionSwitch()
	{
		/*
		switch(Estado.estado.getNum())
		{
			case Modo.PELIGRO.getNum() : aa.dameMapa().introducePeligro(fila, col); ;break;
			case Modo.INICIAL.getNum() : aa.dameMapa().introduceInicial(fila, col);;break;
			case Modo.ALTURA .getNum() : aa.dameMapa().introduceAltura(fila, col,aa.dameMapa().dameNodo(fila, col).getAltura()+1 );//cada vez que pulos eleva la alura 1 ;break;
			case Modo.CHECK.getNum()   : aa.dameMapa().introduceFinal(fila, col);;break;
			case Modo.REEJECUTA.getNum() : System.out.println("ejecuta");aa.reejecuta(); ;break;
			default:	break;
			
			
		}*/
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		//accion(arg0);

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		//accion(arg0);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		//accion(arg0);

	}

	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		
		accion(arg0);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		//accion(arg0);

	}

}
