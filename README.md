Documentação
O desafio da geração de um e-mail institucional baseado em um nome de funcionário foi implementado na linguagem Java, utilizando a IDE Netbeans em sua 
versão 8.1. Esse projeto foi realizado no sistema operacional Windows 10. As versões 1.8.0 do JDK e do JRE foram utilizados na 
programação do software.

Arquivos e suas funcionalidades
O projeto possui, no total, três arquivos: EmailFundec.java, Funcionario.java e funcionarios.csv. 

O arquivo EmailFundec.java é responsável por conter a função main. Em resumo, a main se responsabiliza por ler as informações de funcionarios.csv, criar as instâncias das classes de Funcionario, realizar a leitura do código do funcionário que deseja criar seu e-mail e invocar a função de criar o e-mail institucional.

Funcionario.java é a classe implementada para representar cada funcionário lido do arquivo funcionarios.csv. Essa classe possui seus atributos, seu construtor e seus métodos. Seus atributos são os campos especificados em funcionarios.csv com a adição de um atributo que referencia a outra instância de Funcionario, permitindo a criação de uma lista simplesmente encadeada. O construtor guarda os valores iniciais para cada atributo da classe. Há seis métodos em Funcionario.java. O método busca é responsável por procurar a instância de Funcionario na LSE, baseando-se no código recebido. Caso a busca seja sucedida, retorna a instância da classe. Caso não seja, retorna o valor null. A função buscaEmail possui os mesmos princípios da função anterior se diferenciando em sua base, que procura o funcionário baseado em seu e-mail institucional. Outra diferença é que o retorno da função é um inteiro, sendo 1 para caso de sucesso e -1 para caso de falha. O método criaEmail é um dos principais do projeto, pois é aqui que as sugestões são realizadas baseadas no nome do funcionário que foi encontrado pela função busca na função main do arquivo EmailFundec.java. Há a chamada de buscaEmail para checar se a sugestão pode ser feita, baseando-se na idéia de que cada e-mail é único. Foi implementada uma ArrayList para poder alocar dinamicamente as possíveis sugestões de e-mail. Após armazenar as sugestões, um menu de escolha é apresentado ao usuário que precisa escolher uma dessas para efetuar a criação de seu e-mail institucional. Uma proteção é realizada para impedir que o usuário escolha opções inválidas. Por fim, a LSE e o arquivo funcionarios.csv são atualizados de acordo com a opção escolhida. O método tamanho possui a responsabilidade de retornar quantos elementos a LSE possui. A função inverte é responsável por inverter a LSE de funcionários, permitindo com que a ordem de escrita dos funcionários seja a mesma ordem lida em funcionarios.csv. Finalmente, o método escreveArquivo deleta o arquivo funcionarios.csv para, assim, criar um novo funcionarios.csv com as informações atualizadas.

O arquivo funcionarios.csv possui todos os elementos que são lidos na função main e implementados em uma LSE. Seus elementos são subdivididos em seis categorias: nome, código, telefone, e-mail, institucional e status. Há, em algumas linhas, registros que deixaram o campo institucional em branco.

Instruções de uso
O projeto se baseia na interação do usuário com o terminal da IDE Netbeans. Ao executar o projeto pelo arquivo EmailFundec.java, obtemos uma tela com os seguintes dizeres:

Seja bem-vindo(a)!
Seu e-mail será criado conforme seu status!
Entre com o seu codigo abaixo:

O usuário digitará um código que corresponda aos funcionários lidos do arquivo csv. Caso a inserção do código não corresponda a nenhum 
dos funcionários, aparece na tela:

Código inválido!
Insira um código válido!
Entre com o seu codigo abaixo:

Assim, o usuário ficará preso nessa tela enquanto ele estiver digitando um código que não esteja compatível com os códigos 
guardados. Caso o usuário digite, por exemplo, a matricula de número 105794, ele obterá as seguintes informações:

Luiza, por favor escolha uma das opções abaixo para o seu e-mail institucional:
Temos 9 sugestões para você!
1. luiza@fundec.org.br
2. luizafernandes@fundec.org.br
3. luizaferreira@fundec.org.br
4. luiza_fernandes@fundec.org.br
5. luiza_ferreira@fundec.org.br
6. lfernandesferreira@fundec.org.br
7. luizaff@fundec.org.br
8. lfernandes@fundec.org.br
9. lferreira@fundec.org.br
Escolha uma sugestão digitando o número ao lado da sugestão!

A primeira linha é uma saudação do sistema ao usuário, variando o nome do funcionário conforme diferentes códigos recebidos. As 
sugestões são mostradas de acordo com a disponibilidade delas na LSE e no arquivo, se baseando no conceito de que cada e-mail é único. 
O máximo de sugestões é 9, podendo diminuir conforme os e-mails já cadastrados no sistema. O usuário precisará digitar uma das opções 
exibidas na tela para poder criar seu e-mail. Caso o usuário digite uma opção inválida, ele obterá:

Insira um número válido!
Escolha uma opção digitando o número ao lado da sugestão:

Assim, o usuário ficará preso nesse passo enquanto ele não digitar uma opção válida. Quando uma opção válida for inserida(no exemplo, 
a opção 1 foi escolhida), as frases seguintes aparecerão:

Seu e-mail será luiza@fundec.org.br!
Seu e-mail será criado em instantes!
Um SMS foi enviado para 99999-9950 com a sua senha de acesso.

A sugestão que o usuário escolheu é exibida na tela. Um aviso de que o e-mail será criado e de que um SMS foi enviado para o celular do 
funcionário também compartilham a tela. Por fim, a LSE e o arquivo funcionarios.csv são atualizados de modo com que a próxima Luiza não consiga obter o e-mail institucional luiza@fundec.org.br.
