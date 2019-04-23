package Calc;

public class Calculadora {
	int bateria;
	int bateriaMax;
	
	
	public Calculadora (int bateria, int bateriaMax1) { //Constructor
		this.bateria = bateria;
		this.bateriaMax = bateriaMax1;
	}
	
	public void recarregar (int carga) {
		this.bateria += carga;
		if (this.bateria > this.bateriaMax) {
			this.bateria = this.bateriaMax;
			
		}
			
	}
		
		
	public void soma (int n1, int n2) {
		if (this.bateria > 0) {
			System.out.println("Resultado da soma: " + (n1 + n2));
			userBattery();
		}else {
			System.out.println("ERRO! Bateria insuficiente");
		}
	}
	
	public void subtracao (int n1, int n2) {
		if (this.bateria >0) {
			System.out.println("Resultado da subtração: " + (n1 - n2));
			userBattery();
		}else {
			System.out.println("ERRO! Bateria insuficiente");
		}
	}
	
	public void divisao (int num, int den) {
		if (this.bateria >0 && den !=0) {
			System.out.println("Resultado da divisão: " + (num/den));
				userBattery();
		}else if (den == 0) {
				System.out.println("ERRO! Divisão por zero");
		}else if (this.bateria == 0) {
			System.out.println("Bateria insuficiente");
		}
		
	}
	
	
	public void userBattery () {
		this.bateria -=1;
		
	}
	
	
	
	public void getShowBateria() {
		System.out.println ("bateria: " + bateria);
	}

	public static void main(String[] args) {
		Calculadora ranny = new Calculadora (0,6);
		ranny.recarregar(6);
		ranny.soma(2, 4);
		ranny.divisao(6, 3);
		ranny.subtracao(8, 6);
	
		ranny.getShowBateria();
	}

}
		


