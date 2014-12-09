package practicaIC1.vista.oyente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.util.Set;

import javax.swing.JOptionPane;

import practicaIC1.vista.*;

public class OyenteModo implements ActionListener
{

	//private Modo ii;
	private int ii;
	
	public OyenteModo(int i)
	{
		ii=i;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		
		comportamiento(arg0);
	}

	public void comportamiento(ActionEvent arg0)
	{
		
		Estado.estado=ii;
		
		//JOptionPane.showMessageDialog(null, "Estas insertando "+Estado.nombre() , "Estado " ,1);
		VentanaAEstrella.dameInst().getEstado().setText("Insertando : " + Estado.nombre());
		
		if(Estado.estado == Estado.REEJECUTA) 
		{
			VentanaAEstrella.dameInst().getAa().reejecuta(); 
			VentanaAEstrella.dameInst().repaint();
			
			Set<Integer> nocamino=VentanaAEstrella.dameInst().getAa().getInicialesSInCamino();
			if(nocamino !=null && !nocamino.isEmpty())
			{
				/*StringBuffer sb = new StringBuffer();
				sb.append("No tienen camino : ");
				for(Integer i: nocamino)
				{
					sb.append(i);
					sb.append(" ");
				}*/
				
				JOptionPane.showMessageDialog(null, "No tienen camino : " + nocamino.toString());
				
			}
			
			
		}
		
		if(Estado.estado == Estado.REINICIAR) 
		{
			VentanaAEstrella.dameInst().setDiagonable(true);
			VentanaAEstrella.dameInst().getAa().dameMapa().setPuedeIrDiagonal(true);
			VentanaAEstrella.dameInst().getAa().reinicia();
			VentanaAEstrella.dameInst().repaint(); 
		}
		
		
		if(Estado.estado == Estado.DIAGONALES_ON) 
		{
			VentanaAEstrella.dameInst().setDiagonable(true);
			VentanaAEstrella.dameInst().getAa().dameMapa().setPuedeIrDiagonal(true);
		
		}
		
		if(Estado.estado == Estado.DIAGONALES_OFF) 
		{
			VentanaAEstrella.dameInst().setDiagonable(false);
			VentanaAEstrella.dameInst().getAa().dameMapa().setPuedeIrDiagonal(false);
		}
		
		
		if(Estado.estado == Estado.RANGO) 
		{
			try
			{
				VentanaAEstrella v = VentanaAEstrella.dameInst(); 
				int altura = Integer.valueOf(v.getTxtAltmax().getText());
				v.getAa().dameMapa().getRangoAltura()[1]=altura;
				VentanaAEstrella.dameInst().repaint();
			}
			catch(Exception e)
			{
				VentanaAEstrella v = VentanaAEstrella.dameInst();
				v.getTxtAltmax().setText(Integer.toString(v.getAa().dameMapa().getRangoAltura()[1])); 
				
			}
		}
		
		//System.err.println("Esta en modo " + Estado.nombre(ii));
	}
	
	
		
	
	
}
