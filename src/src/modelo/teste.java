package src.modelo;

public class teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Tabuleiro tabuleiro = new Tabuleiro(3, 3, 9);
		tabuleiro.altenarMarcacao(0, 0);
		tabuleiro.altenarMarcacao(0, 1);
		tabuleiro.altenarMarcacao(0, 2);
		tabuleiro.altenarMarcacao(1, 0);
		tabuleiro.altenarMarcacao(1, 1);
		tabuleiro.altenarMarcacao(1, 2);
		tabuleiro.altenarMarcacao(2, 0);
		tabuleiro.altenarMarcacao(2, 1);
		
		tabuleiro.altenarMarcacao(2, 2);
		
	}

}