package questaotamagosh;

import java.util.Scanner;

public class Pet {
	String nome;
	boolean vida;
	private int energia;
	private int energiaMax;
	private int saciedade;
	private int saciedadeMax;
	private int limpeza;
	private int limpezaMax;
	private int diamantes;
	private int idade;
	
	public Pet (String nome, int energia, int saciedade , int limpeza) { // receber os atributos iniciais
		this.nome = nome;
		this.energia = energia;
		this.saciedade = saciedade;
		this.limpeza = limpeza;
		this.energiaMax = energia;
		this.saciedadeMax = saciedade;
		this.limpezaMax = limpeza;
		this.diamantes = 0;
		this.idade = 0;
		this.vida = true;
	}
	
	public int getEnergia () { // retorna o estado da energia
		return energia;
	}
	
	public void setEnergia (int energia) { // mudar o estado da enegia
		if (energiaMax < energia) { // compara a energia atual com a máxima
			this.energia = this.energiaMax; // substitui pelo valor da nova energia
		}else if (energia <= 0) { // ver se Pet tá vivo
			this.energia = 0;
			this.vida = false;
		}else {
			this.energia = energia; // deixa a energia com o valor atual
		}		
  }
	
	
	public int getSaciedade () {
		return this.saciedade;
	}
	
	public void setSaciedade (int saciedade) {
		if (saciedadeMax < saciedade) {  //compara o valor da saciedade
			this.saciedade = this.saciedadeMax; // muda o valor atual pelo novo
		}else if (saciedade <= 0) { // ver se tem saciedade
			this.saciedade  = 0; // se não tiver
			this.vida = false; // pet morre
		}else {
			this.saciedade = saciedade; // valor não modifica
		}
	}
	
	public int getLimpeza () {
		return this.limpeza;
	}
	
	public void setLimpeza (int limpeza) {
		if (limpezaMax < limpeza) {
			this.limpeza = this.limpezaMax;
		}else if (limpeza <= 0) {
			this.limpeza = 0;
			this.vida = false;
		}else {
			this.limpeza = limpeza;
		}
	}
	
	public int getEnergiaMax () {
		return this.energiaMax;
	}
	public int getSaciedadeMax () {
		return this.saciedadeMax;
	}
	public int getLimpezaMax () {
		return this.limpezaMax;
	}
		 
	
	
	public boolean testarVida () { //ver se pet está vivo
		if (this.vida) 
			return true;
		System.out.println("Seu Pet está morto! ");
		return false;
	}

	public void comer () {
		if (!this.testarVida())
			return;
		this.setEnergia(this.getEnergia()-1);
		this.setSaciedade(this.getSaciedade() + 4);
		this.setLimpeza(this.getLimpeza()-2);
	}
	
	
	public void jogar () {
		if (!this.testarVida())
			return;
		this.setEnergia (this.getEnergia() -1);   //primeiro acessa o objeto para modificá-lo
		this.setSaciedade(this.getSaciedade() + 4);
		this.setLimpeza(this.getLimpeza() - 3);
	}
	
	public void dormir () {
		if (!this.testarVida()) // primeiro testa se pet tá vivo
			return;
		if (this.energiaMax - energia < 5) { // ver se pet tá com sono
			System.out.println("Seu Pet não está com sono! ");
			return;
		}
		this.idade += energiaMax - this.energia;  //diminui idade do pet
		this.setEnergia(this.getEnergiaMax());
	}
	
	public void banho () {
		if (!this.testarVida())
			return;
		this.setEnergia(this.getEnergia()- 3);
		this.setSaciedade(this.getSaciedade() -1);
		this.setLimpeza(this.getLimpezaMax());
		this.idade += 2;
	}
	
	@Override
	
	public String toString () {    // Organizar a chamada dos comandos
		return "[" + this.nome + "]" +
				"E: " + this.energia + "/" + this.energiaMax +
				"S:" + this.saciedade + "/" + this.saciedadeMax +
				"L:" + this.limpeza + "/" + this.limpezaMax +
				"I: " + this.idade + "D: " + this.diamantes;
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Pet pet =  new Pet ("", 0, 0,0 );
		
		while (true) {
			String line = scan.nextLine();
			String ui [];
			ui = line.split (" ");
			if (ui[0].equals("fim")) {
				break;
			}else if (ui[0].equals("init")) {
				pet = new Pet (ui[1], Integer.parseInt(ui[2]), Integer.parseInt(ui[3]), Integer.parseInt(ui[4]));
			}else if (ui[0].equals("jogar")) {
				pet.jogar();
			}else if (ui[0].equals("comer")) {
				pet.jogar();
			}else if (ui[0].equals("dormir")) {
				pet.dormir();
			}else if (ui[0].equals("mostrar")) {
				System.out.println(pet);
			}else {
				System.out.println("Comandoinvalido! ");
				}
			}	
		}
	}
