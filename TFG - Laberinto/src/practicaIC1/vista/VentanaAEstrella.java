package practicaIC1.vista;
//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;












//import java.awt.Canvas;
import java.util.ArrayList;


import java.util.Random;

import javax.swing.JLabel;

import algoritmo.Algoritmo;
import algoritmo.AlgoritmoA;
import algoritmo.Constantes;
import algoritmo.Mapa;
import algoritmo.NodoAstar;
import algoritmo.RQLearning;

import javax.swing.JTextField;

import practicaIC1.vista.oyente.EscuchaRaton;
import practicaIC1.vista.oyente.OyenteModo;

import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.JRadioButton;




public class VentanaAEstrella extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static int fila=1, columna=1;
	private static boolean elegirMapa=false;
	
	private JPanel contentPane;
	private CanvasAestrella canvas;
	
	//private JLabel lblPorcentaje;
	//private JLabel lblPorcentaje_1;
	//private JLabel lblP;
	
	/*
	private JTextField txtFila;
	private JTextField txtColumna;
	private JTextField txtCoste;
	*/
	
	
	private Mapa mapa;
	private Algoritmo aa;
	
	private JButton btnInsertaFin;
	private JButton btnInsertaPeligro;
	private JButton btnEjecuta;
	private JButton btnCosteMas;
	private JButton btnInicial;
	private JButton btnAlturaMas;
	private JLabel lblInsertando;
	private JLabel lblCoste;
	private JButton btnInfo;
	
	
	private static  VentanaAEstrella inst;
	private JLabel lblParaCosteY;
	private JLabel lblParaSaberInfo;
	private JLabel lblParaCambiarEl;
	private JButton btnRangoAltura;
	private JTextField txtAltmax;
	private JLabel lblAlturamaximaPermitida;
	private JLabel lblCamino;

	
	private JButton btnReiniciar;
	private JLabel lblNewLabel;
	private JButton btnCosteMenos;
	private JLabel lblAlturaDeCasilla;
	private JButton btnAlturaMenos;

	private JLabel lblPasoPorDiagonales;

	private JRadioButton RadioButtonDiagonalON;

	private JRadioButton RadioButtonDiagonalOFF;

	private ButtonGroup grupoDiagonal;
	
	public static  VentanaAEstrella dameInst()
	{
		if(inst ==null) inst = new VentanaAEstrella();
		return inst;
	}
	
	
	
	public void  contruyeMapaFijo()
	{
		//prueba:
		int fila=10;
		int columna=10;    	
    	mapa = new Mapa(fila,columna);
    	
    

    	
    	mapa.setPuedeIrDiagonal(true);
    	mapa.introduceInicial(0, 0);
    	mapa.introduceFinal(0, columna-1);
    	
    	
    	
    	//for(int i= 0 ; i < fila; i++)
    		for(int j= 0 ; j < columna; j++){
    			mapa.introducePeligro( 1, j);
    			//j++;
    		}
    	
    		
    	
    	
    	
    	
    	
    	//return mapa;
    	//mapa= mapa;
    	
	}
		
	
	public  void ponCanvas()
	{
		ArrayList<NodoAstar> cam= new ArrayList<NodoAstar> ();
		for(ArrayList<NodoAstar> c: aa.dameCamino())
		{
			cam.addAll(c);
		}
		canvas = new CanvasAestrella(aa.dameMapa(), cam);
		canvas.addMouseListener(new EscuchaRaton(canvas,aa ));
		canvas.setBounds(22, 27, 810, 818);
		contentPane.add(canvas);
	}
	
	public  void ponEtiquetas()
	{
		lblInsertando = new JLabel("Insertando : ");
		lblInsertando.setBounds(857, 27, 252, 27);
		contentPane.add(lblInsertando);
		
		
		
		lblCoste = new JLabel("Coste : ");
		lblCoste.setBounds(22, 851, 1113, 47);
		contentPane.add(lblCoste);
		
		
		lblParaCosteY = new JLabel("Para coste y altura, \r\ndar tantos clicks en la casilla, \r\n como unidades quiera subir");
		lblParaCosteY.setBounds(857, 186, 278, 59);
		contentPane.add(lblParaCosteY);
		
		lblParaSaberInfo = new JLabel("Para saber la  informaci\u00F3n de una casilla:");
		lblParaSaberInfo.setBounds(857, 530, 278, 14);
		contentPane.add(lblParaSaberInfo);
		
		

		lblParaCambiarEl = new JLabel("Para cambiar el rango de altura permitida:");
		lblParaCambiarEl.setBounds(857, 604, 252, 14);
		contentPane.add(lblParaCambiarEl);
		
		
		
		lblAlturamaximaPermitida = new JLabel("Altura:maxima Permitida : ");
		lblAlturamaximaPermitida.setBounds(857, 662, 219, 14);
		contentPane.add(lblAlturamaximaPermitida);
		
		
		lblCamino = new JLabel("Camino: ");
		lblCamino.setBounds(22, 912, 1113, 47);
		contentPane.add(lblCamino);
		
		
		/*
		lblPorcentaje = new JLabel("Porcentaje");
		lblPorcentaje.setBounds(850, 40, 46, 14);
		contentPane.add(lblPorcentaje);
		
		JLabel lblPorcentaje_1 = new JLabel("Porcentaje");
		lblPorcentaje_1.setBounds(1054, 40, 68, 14);
		contentPane.add(lblPorcentaje_1);
		*/
		
		//lblP.setBounds(1064, 75, 46, 14);
		//contentPane.add(lblP);
		
		
		/*
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(1054, 65, 46, 14);
		contentPane.add(lblNewLabel);*/
	}
	public  void ponCamposTexto()
	{
		
		txtAltmax = new JTextField();
		txtAltmax.setHorizontalAlignment(SwingConstants.CENTER);
		txtAltmax.setText("altura M\u00E1xima");
		txtAltmax.setBounds(1001, 629, 134, 20);
		contentPane.add(txtAltmax);
		txtAltmax.setColumns(10);
		
		/*txtFila = new JTextField();
		txtFila.setText("fila");
		txtFila.setBounds(953, 454, 86, 20);
		contentPane.add(txtFila);
		txtFila.setColumns(10);
		
		txtColumna = new JTextField();
		txtColumna.setText("columna");
		txtColumna.setBounds(953, 518, 86, 20);
		contentPane.add(txtColumna);
		txtColumna.setColumns(10);
		
		txtCoste = new JTextField();
		txtCoste.setText("coste");
		txtCoste.setBounds(953, 583, 86, 20);
		contentPane.add(txtCoste);
		txtCoste.setColumns(10);*/
	}
	
	public  void ponBotones()
	{
		btnInsertaFin = new JButton("insertar final");
		btnInsertaFin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInsertaFin.setBounds(857, 95, 180, 38);
		contentPane.add(btnInsertaFin);
		
		btnInsertaPeligro = new JButton("insertar inaccesible");
		btnInsertaPeligro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInsertaPeligro.setBounds(857, 144, 180, 38);
		contentPane.add(btnInsertaPeligro);
		
		btnEjecuta = new JButton("EJECUTAR");
		btnEjecuta.setBackground(Color.GRAY);
		btnEjecuta.setForeground(Color.RED);
		btnEjecuta.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnEjecuta.setBounds(842, 687, 205, 158);
		contentPane.add(btnEjecuta);
		
		btnCosteMas = new JButton("+");
		btnCosteMas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCosteMas.setBounds(857, 273, 46, 39);
		contentPane.add(btnCosteMas);
		
		btnInicial = new JButton("Insertar inicial");
		btnInicial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInicial.setBounds(857, 46, 180, 38);
		contentPane.add(btnInicial);
		
		btnAlturaMas = new JButton("+");
		btnAlturaMas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAlturaMas.setBounds(1001, 273, 52, 38);
		contentPane.add(btnAlturaMas);
		
		btnInfo = new JButton("Info");
		btnInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInfo.setBounds(857, 555, 180, 38);
		contentPane.add(btnInfo);
		
		btnRangoAltura = new JButton("Rango altura");
		btnRangoAltura.setBounds(857, 628, 134, 23);
		contentPane.add(btnRangoAltura);
		
		btnReiniciar = new JButton("REINICIAR MAPA");
		btnReiniciar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnReiniciar.setBounds(857, 460, 180, 59);
		contentPane.add(btnReiniciar);
		
		lblNewLabel = new JLabel("Coste de casilla");
		lblNewLabel.setBounds(857, 240, 126, 14);
		contentPane.add(lblNewLabel);
		
		btnCosteMenos = new JButton("-");
		btnCosteMenos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCosteMenos.setBounds(913, 273, 46, 39);
		contentPane.add(btnCosteMenos);
		
		lblAlturaDeCasilla = new JLabel("altura de casilla");
		lblAlturaDeCasilla.setBounds(1009, 240, 126, 14);
		contentPane.add(lblAlturaDeCasilla);
		
		btnAlturaMenos = new JButton("-");
		btnAlturaMenos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAlturaMenos.setBounds(1063, 274, 52, 38);
		contentPane.add(btnAlturaMenos);
		
		lblPasoPorDiagonales = new JLabel("Paso por diagonales:");
		lblPasoPorDiagonales.setBounds(857, 344, 146, 14);
		contentPane.add(lblPasoPorDiagonales);
		
		RadioButtonDiagonalON = new JRadioButton("ON",true);
		RadioButtonDiagonalON.setBounds(857, 386, 59, 23);
		contentPane.add(RadioButtonDiagonalON);
		
		RadioButtonDiagonalOFF = new JRadioButton("OFF",false);
		RadioButtonDiagonalOFF.setBounds(913, 386, 52, 23);
		contentPane.add(RadioButtonDiagonalOFF);
		
		
		
	}
	
	public void ponListeners()
	{
		btnInicial.addActionListener(new OyenteModo(Estado.INICIAL));
		btnInsertaFin.addActionListener(new OyenteModo(Estado.CHECK));
		btnInsertaPeligro.addActionListener(new OyenteModo(Estado.PELIGRO));
		btnEjecuta.addActionListener(new OyenteModo(Estado.REEJECUTA));
		btnCosteMas.addActionListener(new OyenteModo(Estado.COSTEMAS));
		btnAlturaMas.addActionListener(new OyenteModo(Estado.ALTURAMAS));
		btnInfo.addActionListener(new OyenteModo(Estado.INFO));
		btnRangoAltura.addActionListener(new OyenteModo(Estado.RANGO));
		btnReiniciar.addActionListener(new OyenteModo(Estado.REINICIAR));
		btnCosteMenos.addActionListener(new OyenteModo(Estado.COSTEMENOS));
		btnAlturaMenos.addActionListener(new OyenteModo(Estado.ALTURAMENOS));
		RadioButtonDiagonalON.addActionListener(new OyenteModo(Estado.DIAGONALES_ON));
		RadioButtonDiagonalOFF.addActionListener(new OyenteModo(Estado.DIAGONALES_OFF));
		
	}
	
	public void repaint()
	{
		super.repaint();
		
		
		ArrayList<NodoAstar> cam= new ArrayList<NodoAstar> ();
		if( aa.dameCamino()!=null)
		{
			for(ArrayList<NodoAstar> c: aa.dameCamino())
			{
				cam.addAll(c);
			}
		
		}
			canvas.setCamino(cam);
			
			
			canvas.repaint();
			
			
			if(aa.dameCamino()!=null && !aa.dameCamino().isEmpty() )
			{
				
				lblCamino.setText("Camino : \n" );
				for(ArrayList<NodoAstar> cami: aa.dameCamino())
				{
					lblCamino.setText(lblCamino.getText() +(aa.dameCamino().indexOf(cami)+1) +" --> "+ cami.toString() + " //   \n");
				}
				/*
				lblCoste.setText("Costes : ");
				for(Integer i:aa.getCoste().keySet())
				{
					Double d= aa.getCoste().get(i);
					if(d != new Double(Integer.MAX_VALUE))
						lblCoste.setText(lblCoste.getText() + i +" --> "+Redondeo.redondear(d, 2)  +"  //  ");
					else
						lblCoste.setText(lblCoste.getText() + "[]");
					
						
				}*/
			}
			else 
			{
				lblCamino.setText("Camino : ");
				lblCoste.setText("Coste : ");
			}
		
		lblAlturamaximaPermitida.setText("Altura maxima permitida "+ aa.dameMapa().getRangoAltura()[1]);
	}
	
		
	public Mapa getMapa() {
		return mapa;
	}

	public Algoritmo getAa() {
		return aa;
	}

	
	public JLabel getEstado () 
	{
		return lblInsertando;
	}
	public JLabel getLblCoste() {
		return lblCoste;
	}

	public JLabel getLblCamino() {
		return lblCamino;
	}



	public void setLblCamino(JLabel lblCamino) {
		this.lblCamino = lblCamino;
	}




	public JTextField getTxtAltmax() {
		return txtAltmax;
	}



	public boolean isDiagonable()
	{
		if(RadioButtonDiagonalON.isSelected())
			return true;
		else 
			return false;
			
	}
	public void setDiagonable(boolean b)
	{
		if(b)
		{
			RadioButtonDiagonalON.setSelected(true);
			RadioButtonDiagonalOFF.setSelected(false);
		}
		else
		{
			RadioButtonDiagonalON.setSelected(false);
			RadioButtonDiagonalOFF.setSelected(true);
		}
	}



	/**
	 * Create the frame.
	 */
	private VentanaAEstrella() //AlgoritmoA aa
	{
		this.setTitle("Recorrido de un avión ");
		
		/*
		if(!this.elegirMapa)
			mapa = new Mapa(fila,columna);
		else contruyeMapaFijo();
		*/
		
		//aa = new AlgoritmoA(mapa);
		
		try 
		{
			aa= new RQLearning(Constantes.NOMBRE_FICHERO);
		} catch (IOException e) 
		{
			int pasos= Constantes.NUM_EJECUCIONES;
			aa= new RQLearning(pasos);
		} 
		
		//aa.reejecuta();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1175, 1024);
		setBounds(0, 0, 1161, 1024);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		ponCanvas();
		ponEtiquetas();
		ponCamposTexto();
		ponBotones();
		ponListeners();
		
		
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		
		File f= new File(Constantes.NOMBRE_FICHERO);
		
		
		Algoritmo a;
		
		
		try 
		{
			a= new RQLearning(Constantes.NOMBRE_FICHERO);
		} catch (IOException e) 
		{
			int pasos= Constantes.NUM_EJECUCIONES;
			a= new RQLearning(pasos);
			
		} 
				
		
		
		/*if(JOptionPane.showConfirmDialog(null," Elegir mapa aleatorio?"," Elegir mapa:" ,2)==JOptionPane.YES_OPTION){
		
			VentanaAEstrella.elegirMapa=true;
			
			
		}
		else
		{
			while(fila<=1 )//|| columna==1
			{
	
				String s="";
				try{
					
					s = JOptionPane.showInputDialog(null, "Dame dimensión del mapa: ");
					if(s==null)throw new NullPointerException();
					
					fila = Integer.valueOf(s);
				if(fila<=1)
					JOptionPane.showMessageDialog(null, "Introduce una dimension mayor que 1");
				}
				catch(NullPointerException  e)
				{
					System.exit(0);
				}
				catch(java.lang.NumberFormatException e)
				{
					
					JOptionPane.showMessageDialog(null, "Introduce una dimension mayor que 1");
					
					
				}catch(Exception e)
				{
				
					System.exit(0);
				}
	
				
				
				
			}
			columna=fila;
		}
		
		*/
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Thread.sleep(50000);
					VentanaAEstrella frame = VentanaAEstrella.dameInst();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
