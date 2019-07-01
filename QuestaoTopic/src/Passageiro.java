import java.util.ArrayList;
import java.util.Scanner;

	class Passageiro {
	private String nome;
	private int idade;
	

	public void Passageiro (String nome, int idade) {
		this.nome = nome;
		this.idade = idade;
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	
	
	
}	



class topic {
	ArrayList <Passageiro> assentos = new ArrayList <Passageiro> () ;
		int lotacaoMax;
		int qtdPreferencial;
		
		public topic (int lotacaoMax, int qtdPreferencial) {
			this.lotacaoMax = lotacaoMax;
			this.qtdPreferencial = qtdPreferencial;
			
			for (int i = 0; i< this.lotacaoMax; i++) {
				this.assentos.add (null);
			}			
		}
	
		
		private boolean inserirPref (Passageiro pref) {
			for (int i = 0; i< this.qtdPreferencial; i++) {
				if(this.assentos.get(i) == null) {
					this.assentos.set(i, pref);
					return true;
				}
			}
			return false;
		}
		
		private boolean inserirComum (Passageiro comum) {
			for (int i = this.qtdPreferencial; i<this.assentos.size(); i++) {
				if(this.assentos.get(i) == null) {
					this.assentos.set(i, comum);
					return true;
				}
			
			}
			return false;
		}
		
		public boolean inserir (Passageiro p) {
			if (p.getIdade() <= 60) {
				if(this.inserirPref(p)) {
					return true;
				
				
			}else if (this.inserirComum(p)) {
				return true;
				
			}else {
				System.out.println("Topic lotada");
			}
			
			
			return false;
			
		}else if(this.inserirComum (p)) {
			return true;
			
		}else if (this.inserirPref(p)) {
			return true;
			
		}else {
			System.out.println("Topic lotada");
		}
		return false;
	}
		
		public boolean remover (String nome) {
			for (int i = 0; i < assentos.size(); i++) {
				if (assentos.get(i) != null) {
					if (assentos.get(i).getNome().equals(nome)) {
						assentos.set(i, null);
						return true;
					}else {
						System.out.println("Passageiro nao encontrado");
					}
				}
			}
			return false;
		}
		@Override
		public String toString() {
			String str = "[";
			for (int i = 0; i < this.assentos.size(); i++) {
				if (i < this.qtdPreferencial) {
					str += "@";
					
				}else {
					str += "=";
				}
				
				if (this.assentos.get(i) != null) {
					str += this.assentos.get(i).toString();
				}
				
			}
			return str += "j";
		}
		

public class Principal {
	public void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		topic top = new topic (0,0);
		
		while(true) {
			String line = scan.nextLine();
			String [] ui;
			ui =  line.split(" ");
			if (ui[0].equals ("end")) {
				break;
			}
			
			else if (ui[0].equals ("init")) {				
				top = new topic(Integer.parseInt(ui[1]),Integer.parseInt(ui[2]));
			}
			else if (ui[0].equals ("show")) {
				System.out.println(top);
			}
			else if (ui[0].equals("in")) {
				Passageiro pas = new Passageiro();
				
				top.inserir(pas);
			}
			else if (ui[0].equals ("remove")) {
				top.remover(ui[1]);
			}
			else {
				System.out.println("Opcao invalida");
			}
		}	
	}
}
	
}
