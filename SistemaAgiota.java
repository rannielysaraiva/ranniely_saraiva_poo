package questaoAgiota;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Cliente{  
    String id;
    String nome;
    float divida;
    boolean vivo;
   
    public Cliente (String id, String nome){ // O QUE A CLASSE CLIENTE VAI RECEBER (CONSTRUTOR)
        this.id = id;
        this.nome = nome;
        this.divida = 0;               
        this.vivo = true;
    }
    @Override
    public String toString() {
        return this.id + ":" + this.nome + ":" + this.divida;
    }
}

class Sistema{
    float saldoAgiota;  //SALDO INICIAL DO AGIOTA
    int proxId;   // NUMERO DO ID QUE VAI FICAR ENTRANDO E MUDANDO CONFORME UM NOVO CLIENTE FOR CADASTRADO
    ArrayList <Cliente> cliente; // ARRAYLIST PRA PERCORRER A CLASSE CLIENTE
    ArrayList <Transacao> transacoes;  // ARRAYLIST PRA PERCORRER A CLASSE TRANSACAO
    
    
    public Sistema(float saldo){ // O QUE A CLASSE SISTEMA VAI FAZER (CONSTRUTOR)
        this.saldoAgiota = saldoAgiota;  
        this.cliente = new ArrayList <Cliente>();
        this.transacoes = new ArrayList <Transacao>();
        this.proxId = 0;  
    }
    Cliente procurar(String id){   // AQUI VAI PERCORRER A CLASSE E PROCURAR SE O CLIENTE ESTA CADASTRADO
        for (Cliente cli : cliente) {  // VAI PERCORRER A CLASSE CLIENTE O TIPO CLI, SE O QUE FOI ENCONTRADO EM CLI FOR IGUAL O ID
            if (cli.id.equals(id))
                return cli;   // MOSTRA CLI
        }
          
        throw new RuntimeException("falha: cliente não existe"); // EXCESSÃO (SE TIVER UM ERRO NO CÓDIGO, DISPARA UMA EXECSSÃO E NA CLASSE CONTROLE ELE VAI INDICAR O ERRO)
          }      
      
    
    void cadastrarcliente(Cliente cli){ // O QUE VAI SER PRECISO RECEBER PRA CADASTRAR O CLIENTE   
           try {
           this.procurar(cli.id); // VAI PROCURAR O CLIENTE PELO ID
           throw new RuntimeException("falha: cliente já existe");  
           }
           catch(RuntimeException mi){
                cliente.add(cli);
           }
      }
    
    void emprestar(String id,float valor){  // QUANDO FOR EMPRESTAR VAI PEDIR DO USUARIO UM ID
        Cliente cli = procurar(id);   // PROCURAR PELO ID
        addTransacao(valor, id); // 
        this.saldoAgiota -= valor; // DIMINUI DO SALDO DO AGIOTA
        cli.divida += valor;  // ADICIONA O DINHEIRO EMPRESTADO PELO AGIOTA NA "DIVIDA" DO CLIENTE
    }
    
    void receber(String id,Float valor){  // RECEBE A ID E O VALOR QUE VAI SER PAGO PELO CLIENTE
        Cliente cli = procurar(id);   
        if(cli.divida < valor){ // SE A DIVIDA FOR MENOR, VALOR DADO PELO CLIENTE NÃO RECEBE
            System.out.println("falha: pagamento maior do que a dívida");
            return;
        }else{
            addTransacao(valor, id); 
            cli.divida -= valor; // QUANDO O CLIENTE FAZ O PAGAMENTO DIMINUI DA DÍVIDA
            this.saldoAgiota += valor;
        }         
     }
    
    
      void addTransacao(float valor , String id){ 
          this.transacoes.add(new Transacao(proxId, valor, id)); 
          proxId +=1; // PRÓXIMA TRANSAÇÃO
      }
      
      ArrayList<Transacao> gethistorico(){ 
             return transacoes;
      }
      public String toString() {  
        String str = "";
        for(Cliente cliente : cliente)
            str += cliente + "\n";
        str += "saldo em caixa: " + this.saldoAgiota;
        return str;
    }     
}

class Transacao{ // VAI GERAR TODAS AS INFORMAÇÕES QUE SERÃO MOSTRADAS NO HISTÓRICO
    int id;
    float valor;
    String cliente;
                
    public Transacao(int id, float valor, String cliente){
        this.id = id;
        this.valor = valor;
        this.cliente = cliente;
     }

    @Override
    public String toString() {
       return "" + id + ": " + valor + ": " + cliente;
    }
}

public class SistemaAgiota {
    public static void main(String[] args) {
        Sistema sistema = new Sistema(0);
        Scanner scan = new Scanner(System.in);
      
        while(true){
            String line = scan.nextLine();
            String[] ui = line.split(" ");
            try{
                if (ui[0].equals("sair")){
                    break;
                }else if (ui[0].equals("init")){
                    sistema = new Sistema(Float.parseFloat(ui[1]));
                }else if (ui[0].equals("cadastrar")){
                    String id = ui[1];
                    String[] subarray = Arrays.copyOfRange (ui, 2, ui.length);
                    String fullname = String.join(" ", subarray);
                    sistema.cadastrarcliente(new Cliente(id, fullname));
                }else if (ui[0].equals("mostrar")){
                        System.out.println(sistema);
                }else if (ui[0].equals("emprestar")) {
                    sistema.emprestar(ui[1], Float.parseFloat(ui[2]));
                }else if (ui[0].equals("receber")) {
                    sistema.receber(ui[1], Float.parseFloat(ui[2]));    
                }else if (ui[0].equals("historico")) {
                    for (Transacao tr : sistema.gethistorico())
                        System.out.println(tr);
                }else{
                    System.out.println("falha: comando inválido");
                }
            }catch(RuntimeException mi){
                System.out.println(mi.getMessage());
            }
        }      
    }
    
}