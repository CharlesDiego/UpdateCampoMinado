package src.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Campo {
	
	private boolean minado;
	private boolean aberto;
	private boolean marcado;
	
	private final int linha;
	private final int coluna;
	
	private List<Campo> vizinhos = new ArrayList<>();
	private List<CampoObservador> observadores = new ArrayList<>();
	
	
	public Campo(int linha, int coluna) {
		super();
		this.linha = linha;
		this.coluna = coluna;
	}
		

	
	public void registrarObservador (CampoObservador observador) {
		observadores.add(observador);
	}
	

	private void notificarObservadores(CampoEvento evento) {
		observadores.stream()
		.forEach(o -> o.eventoOcorreu(this, evento));
		
	}
	
		
	public boolean adicionarVizinho(Campo vizinho) {
		boolean linhaDiferente = linha != vizinho.linha;
		boolean colunaDiferente = coluna != vizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;
		
		int deltaLinha = Math.abs(linha-vizinho.linha);
		int deltaColuna = Math.abs(coluna-vizinho.coluna);
		int detalGeral = deltaColuna +  deltaLinha;
		
		if (detalGeral ==1 && !diagonal) {
			vizinhos.add(vizinho);
			return true;
			
		} else if (detalGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		}else {
			return false;
		}
	}
	  void alternarMarcacao() {
		if (!aberto) {
			marcado = !marcado;
			if(marcado) {
				notificarObservadores(CampoEvento.MARCAR);
			}else {
				notificarObservadores(CampoEvento.DESMARCAR);
			}
		}
	}
	public boolean abrir() {
		if (!aberto && !marcado) {
			
			if(minado ) {
				notificarObservadores(CampoEvento.EXPLODIR);
				return true;
			}
			setAberto(true);
			
			if(vizinhaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}
			
			return true;
		} else {
		return false;
		}
	}
	public boolean vizinhaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	
	}
	public void minar() {
		minado = true;
	}
	public boolean isMinado() {
		return minado;
	}
	public boolean isMarcado() {
		return marcado;
	}
	void setAberto(boolean aberto) {
		this.aberto = aberto;
		if(aberto) {
		notificarObservadores(CampoEvento.ABRIR);
		}
	}
	
	public boolean isAberto () {
		return aberto;
	}
	public boolean isFechado () {
		return !isAberto();
	}
	public int getLinha() {
		return linha;
	}
	public int getColuna () {
		return coluna;
	}
	public boolean objetivoAlcancado () {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}
	public long minasNaVizinhanca() {
		return vizinhos.stream().filter( v -> v.minado).count();
	}
	public void reiniciar () {
		aberto = false;
		minado = false;
		marcado = false;
	}
}
