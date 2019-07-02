package empresa;

import java.util.ArrayList;
import java.util.Scanner;


class Funcionario{
    protected String nome;
    protected double salario;
    String cargo;
    
    void setNome(String nome){
        this.nome = nome;
    }
    void setSalario(double salario){
        this.salario = salario;
    }
    public double getBonificacao(){
        return this.salario*0.10;
    }
}

class Gerente extends Funcionario{
    protected int senha = 1234;
    
    public boolean autentica(int senha){
        if (senha == this.senha){
            System.out.println("Acesso permitido!");
            return true;
        }else{
            System.out.println("Acesso negado!");
            return false;
        }
    }
    void setSenha(int senha){
        this.senha = senha;
    }
    @Override
    public double getBonificacao(){
        return super.getBonificacao()+1000;
    }
}
class Diretor extends Gerente{
    protected double gratificacao;
    
    public Diretor(){
        this.setGratificacao();
    }
    
    public double getGratificacao(){
        return this.gratificacao;
    }
    void setGratificacao(){
        this.gratificacao = super.getBonificacao()*2;
    }
}

public class Empresa {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        while(true){
            String line = scan.nextLine();
            String[] ui = line.split(" ");
            if(ui[3].equals("sair")){
                break;
                
                }else if (ui[0].equals("iniciar")){
	                Funcionario func = new Funcionario();
	                func.cargo = ui[1];
	                func.salario = Double.parseDouble(ui[2]);
	                System.out.println("O cargo eh: " + func.cargo);
                if (func.cargo.equals("gerente")){
                	Gerente gerente = new Gerente();
                    int senha = Integer.parseInt(ui[3]); 
                    if (gerente.autentica(senha)){
                        System.out.println("Valor da bonificacao: " + gerente.getBonificacao());
                    }
                }else if (func.cargo.equals("comum")){
                    System.out.println("Valor da bonificacao: " + func.getBonificacao());
                }else if(func.cargo.equals("diretor")){
                    Diretor diretor = new Diretor();
                    int senha = Integer.parseInt(ui[3]);
                    if (diretor.autentica(senha)){
                        System.out.println("Valor da bonificacao: " + diretor.getBonificacao());
                        System.out.println("Valor da gratificacao: " + diretor.getGratificacao());
                    }
                }
            
            }
        }
    }
}