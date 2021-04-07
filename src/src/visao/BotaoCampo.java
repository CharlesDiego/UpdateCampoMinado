package src.visao;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import src.modelo.Campo;
import src.modelo.CampoEvento;
import src.modelo.CampoObservador;

public class BotaoCampo extends JButton implements CampoObservador, MouseListener{
		
	private static final long serialVersionUID = 1L;
	
	private final Color BG_PADRAO = new Color (184, 184, 184);
	private final Color BG_MARCAR = new Color (8, 174, 247);
	private final Color BG_EXPLODIR = new Color (188, 66, 68);
	private final Color TEXTO_VERDE = new Color (0, 110, 0);
	
	
	private Campo campo;
	
	
	public BotaoCampo(Campo campo) {
		
		this.campo = campo;
		setBorder(BorderFactory.createBevelBorder(0));
		setBackground(BG_PADRAO);
		
		addMouseListener(this);
		campo.registrarObservador(this);
		
				
	}

	@Override
	public void eventoOcorreu(Campo campo, CampoEvento evento) {
		switch(evento) {
		case ABRIR:
			aplicarEstiloAbrir();
			break;
		case MARCAR:	
			aplicarEstiloMarcar();
			break;
		case EXPLODIR:	
			aplicarEstiloExplodir();
			break;
		default:
			aplicarEstiloPadrao();
		}
		
	}

	private void aplicarEstiloPadrao() {
		setBackground(BG_PADRAO);
		setBorder(BorderFactory.createBevelBorder(0));
		setText("");
	}

	private void aplicarEstiloExplodir() {
		setBackground(BG_EXPLODIR);
		setText("x");
	}

	private void aplicarEstiloMarcar() {
		setBackground(BG_MARCAR);
		setForeground(Color.BLACK);
		setText("M");
		
	}

	private void aplicarEstiloAbrir() {
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		if(campo.isMinado()) {
			setBackground(BG_EXPLODIR);
			return;
		}
		
		setBackground(BG_PADRAO);
		
		switch (campo.minasNaVizinhanca()){
		
		case 1: 
			setForeground(TEXTO_VERDE);
			
			break;
		case 2:
			setForeground(Color.BLUE);
			break;
		case 3:
			setForeground(Color.YELLOW);
			break;
		case 4:
			setForeground(Color.RED);
			break;
		case 5:
			setForeground(Color.RED);
			break;
		case 6:
			setForeground(Color.RED);
			break;
		default:
			setForeground(Color.PINK);
		}
		String valor = !campo.vizinhaSegura()?
				campo.minasNaVizinhanca() + "" : "";
		setText(valor);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
 @Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton()==1) {
			campo.abrir();
			
			System.out.println("esquerdo");
		}else if (e.getButton() ==2) {
			System.out.println("Bolinha mouse");
		}else if (e.getButton() ==3) {
			campo.alternarMarcacao();
			System.out.println("Direito");
		}else {
			System.out.println("outro botao");
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
