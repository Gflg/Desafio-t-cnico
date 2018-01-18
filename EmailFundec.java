package emailfundec;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Gustavo
 */
public class EmailFundec {

    public static void main(String[] args) throws FileNotFoundException{
        String csvFile = "funcionarios.csv";
        BufferedReader br = null;
        String line;
        String[] cabecalho = new String[6];
        Funcionario inicio = null;

        try {

            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            cabecalho = line.split(","); //Lê o cabeçalho, que não possui os dados de alunos.
            while ((line = br.readLine()) != null) {
                String[] linha = line.split(",");
                
                inicio = new Funcionario(linha[0],linha[1],linha[2],linha[3],linha[4],linha[5],inicio); //Cria um aluno na lista de alunos
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        inicio.inverte();//Inverte a lista de funcionários para deixar na mesma ordem do arquivo.
        Scanner leitor = new Scanner(System.in);
        //Menu de apresentação
        System.out.println("Seja bem-vindo(a)!");
        System.out.println("Seu e-mail será criado conforme seu status!");
        while(true){
            System.out.println("Entre com o seu codigo abaixo:");
            String resposta = leitor.nextLine();//Usuário insere o código.
            Funcionario retorno = inicio.busca(resposta);
            if(retorno != null){ //Se o funcionário existe no arquivo.
                retorno.criaEmail(inicio, cabecalho);
                System.out.println();
                break;
            }
            System.out.println("Código inválido!");
            System.out.println("Insira um código válido!");
        }
    }
    
}
