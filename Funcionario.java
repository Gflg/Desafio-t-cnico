package emailfundec;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Gustavo
 */
public class Funcionario {
    String nome;
    String codigo;
    String telefone;
    String email;
    String institucional;
    String status;
    Funcionario proximo;
    
    //Construtor da classe
    public Funcionario(String nome, String codigo, String telefone, String email, String institucional, String status, Funcionario prox){
        this.nome = nome;
        this.codigo = codigo;
        this.telefone = telefone;
        this.email = email;
        this.institucional = institucional;
        this.status = status;
        proximo = prox;
    }
    
    //Retorna o funcionário correspondente ao código, podendo retornar null quando esse funcionário não existe na lista lida do arquivo.
    public Funcionario busca(String codigo){
        if(this.codigo.equals(codigo)) return this;
        Funcionario aux = this.proximo;
        while((aux != null) && (!aux.codigo.equals(codigo))){
            aux = aux.proximo;
        }
        if(aux == null) return null;
        return aux;
    }
    
    //Retorna 1 para quando o e-mail existe no arquivo e -1 para quando não existe.
    public int buscaEmail(String email, Funcionario inicio){
        if(this.institucional == email) return 1;
        Funcionario aux = inicio;
        while((aux != null) && (!aux.institucional.equals(email))){
            aux = aux.proximo;
        }
        if(aux == null) return -1;
        return 1;
    }
    
    //Função que fornece as sugestões de e-mail e atualiza o arquivo.
    public void criaEmail(Funcionario inicio, String[] cabecalho) throws FileNotFoundException{
        
        //Casos em que o usuário é impedido de criar um e-mail.
        if(this.status.equals("Inativo")){
            System.out.println("Não pudemos criar seu e-mail pois você está inativo!");
            return;
        }
        if(!this.institucional.equals("email@fundec.org.br") && !this.institucional.equals("")){
            System.out.println("Você já possui um e-mail institucional!");
            return;
        }
        
        String[] linha = nome.split(" "); //Separa o nome inicial dos sobrenomes.
        System.out.println(linha[0] + ", por favor escolha uma das opções abaixo para o seu e-mail institucional:"); //Envia a mensagem com o nome inicial no começo da mensagem
        ArrayList<String> sugestoes = new ArrayList<String>();
        
        //Primeira sugestão
        String aux = linha[0].toLowerCase() + "@fundec.org.br";
        //Nome inicial + e-mail
        if (buscaEmail(aux,inicio) == -1) sugestoes.add(aux);
        
        //Segunda sugestão
        aux = linha[0].toLowerCase() + linha[1].toLowerCase() + "@fundec.org.br";
        //Nome inicial + primeiro sobrenome + e-mail
        if (buscaEmail(aux,inicio) == -1) sugestoes.add(aux);
        
        //Terceira sugestão
        aux = linha[0].toLowerCase() + linha[2].toLowerCase() + "@fundec.org.br";
        //Nome inicial + segundo sobrenome + e-mail
        if (buscaEmail(aux,inicio) == -1) sugestoes.add(aux);
        
        //Quarta sugestão
        aux = linha[0].toLowerCase() + "_" + linha[1].toLowerCase() + "@fundec.org.br";
        //Nome inicial + _ + primeiro sobrenome + e-mail
        if (buscaEmail(aux,inicio) == -1) sugestoes.add(aux);
        
        //Quinta sugestão
        aux = linha[0].toLowerCase() + "_" + linha[2].toLowerCase() + "@fundec.org.br";
        //Nome inicial + _ + segundo sobrenome + e-mail
        if (buscaEmail(aux,inicio) == -1) sugestoes.add(aux);
        
        //Sexta sugestão
        aux = (linha[0].toLowerCase()).charAt(0) + linha[1].toLowerCase() + linha[2].toLowerCase() + "@fundec.org.br";
        //Primeira letra do nome inicial + primeiro sobrenome + segundo sobrenome + e-mail
        if (buscaEmail(aux,inicio) == -1) sugestoes.add(aux);
        
        //Sétima sugestão
        aux = linha[0].toLowerCase() + (linha[1].toLowerCase()).charAt(0) + (linha[2].toLowerCase()).charAt(0) + "@fundec.org.br";
        //Nome inicial + primeira letra do primeiro sobrenome + primeira letra do segundo sobrenome + e-mail
        if (buscaEmail(aux,inicio) == -1) sugestoes.add(aux);
        
        //Oitava sugestão
        aux = (linha[0].toLowerCase()).charAt(0) + linha[1].toLowerCase() + "@fundec.org.br";
        //Primeira letra do nome inicial + primeiro sobrenome + e-mail
        if (buscaEmail(aux,inicio) == -1) sugestoes.add(aux);
        
        //Nona sugestão
        aux = (linha[0].toLowerCase()).charAt(0) + linha[2].toLowerCase() + "@fundec.org.br";
        //Primeira letra do nome inicial + segundo sobrenome + e-mail
        if (buscaEmail(aux,inicio) == -1) sugestoes.add(aux);
        
        System.out.println("Temos " + sugestoes.size() + " sugestões para você!");
        
        //Lista todas as sugestões na tela
        int cont = 1;
        for (String atual: sugestoes) {
            System.out.println(cont + ". " + atual);
            cont++;
        }
        
        System.out.println("Escolha uma sugestão digitando o número ao lado da sugestão!");
        Scanner leitor = new Scanner(System.in); //Sugestão escolhida pelo usuário.
        int resp = leitor.nextInt();
        
        //Caso a sugestão seja inválida, sendo um número menor que 1 ou um número maior que o número de sugestões
        while(resp < 1 || resp > sugestoes.size()){
            System.out.println("Insira um número válido!");
            System.out.println("Escolha uma opção digitando o número ao lado da sugestão:");
            resp = leitor.nextInt();
        }
        
        //Procura a sugestão escolhida.
        cont = 1;
        for (String atual: sugestoes) {
            if(cont == resp) this.institucional = atual;
            cont++;
        }
        
        //Atualiza o arquivo.
        escreveArquivo("funcionarios.csv", inicio, cabecalho);
        //Mensagens de sucesso ao criar o e-mail.
        System.out.println("Seu e-mail será " + this.institucional + "!");
        System.out.println("Seu e-mail será criado em instantes!");
        System.out.println("Um SMS foi enviado para " + this.telefone + " com a sua senha de acesso.");
    }
    
    //Retorna a quantidade de funcionários na lista obtida através da leitura do arquivo.
    private int tamanho(){
        Funcionario aux = this;
        int cont = 1;
        while(aux != null){
            aux = aux.proximo;
            cont++;
        }
        return cont;
    }
    
    //Função utilizada para inverter de posição todos os elementos de uma lista
    public void inverte(){
        int cont = tamanho()/2;
        Funcionario aux = this;
        while(aux.proximo != null){
            aux = aux.proximo;
        }
        Funcionario aux2,aux3 = this;
        while(cont > 0){
            String intermediario;
            
            intermediario = aux3.nome;
            aux3.nome = aux.nome;
            aux.nome = intermediario;
            
            intermediario = aux3.codigo;
            aux3.codigo = aux.codigo;
            aux.codigo = intermediario;
            
            intermediario = aux3.telefone;
            aux3.telefone = aux.telefone;
            aux.telefone = intermediario;
            
            intermediario = aux3.email;
            aux3.email = aux.email;
            aux.email = intermediario;
            
            intermediario = aux3.institucional;
            aux3.institucional = aux.institucional;
            aux.institucional = intermediario;
            
            intermediario = aux3.status;
            aux3.status = aux.status;
            aux.status = intermediario;
            
            //aux2 vai ser o limite de aux
            aux2 = aux;
            aux = this;
            while(aux.proximo != aux2) aux = aux.proximo;
            aux3 = aux3.proximo;
            cont--;
        }
    }
    
    //Função utilizada para escrever no arquivo de funcionários
    private void escreveArquivo(String nome, Funcionario inicio, String[] cabecalho) throws FileNotFoundException{
        File file = new File(nome);
        file.delete();
        PrintWriter pw = new PrintWriter(new File(nome));
        StringBuilder sb = new StringBuilder();
                
        for(int i=0;i<5;i++){
            sb.append(cabecalho[i]);
            sb.append(",");
        }
        sb.append(cabecalho[5]);
        sb.append('\n');
                
        Funcionario aux = inicio;
        while(aux != null){
            sb.append(aux.nome);
            sb.append(",");
            sb.append(aux.codigo);
            sb.append(",");
            sb.append(aux.telefone);
            sb.append(",");
            sb.append(aux.email);
            sb.append(",");
            sb.append(aux.institucional);
            sb.append(",");
            sb.append(aux.status);
            sb.append('\n');
                    
            aux = aux.proximo;
        }
                
        pw.write(sb.toString());
        pw.close();
    }
}
