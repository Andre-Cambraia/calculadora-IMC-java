// Importa Scanner para permitir leitura de dados digitados pelo usuario.
import java.util.Scanner;

// Classe principal do programa
public class Main {

    // Metodo principal executado pela JVM ao iniciar o programa.
    public static void main(String[] args) {
        // Scanner responsavel por ler entradas do teclado.
        Scanner scanner = new Scanner(System.in);

        // SistemaIMC controla calculos e historico da sessao.
        SistemaIMC sistema = new SistemaIMC();

        // Guarda a ultima pessoa ou atleta cadastrado.
        Pessoa pessoaCadastrada = null;

        // Opcao inicia diferente de zero para manter o menu em loop.
        int opcao = -1;

        // Controle de repeticao: o menu continua ate o usuario escolher 0.
        while (opcao != 0) {
            // Limpa o terminal antes de exibir novamente o menu principal.
            limparTela();

            // Mostra as opcoes disponiveis para o usuario.
            exibirMenu();

            try {
                // Le a opcao digitada e valida se e um numero inteiro.
                opcao = lerInt(scanner, "opcao");

                // Executa uma acao diferente conforme a opcao escolhida.
                switch (opcao) {
                    case 1:
                        // Cadastra uma pessoa comum e guarda como ultima pessoa cadastrada.
                        pessoaCadastrada = cadastrarPessoa(scanner);

                        // Registra a pessoa no historico da sessao.
                        sistema.registrar(pessoaCadastrada);

                        System.out.println();
                        System.out.println("Pessoa cadastrada com sucesso.");
                        break;
                    case 2:
                        // Garante que exista alguem cadastrado antes de calcular.
                        validarPessoaCadastrada(pessoaCadastrada);

                        // Calcula, classifica e exibe o IMC da pessoa atual.
                        sistema.processar(pessoaCadastrada);
                        break;
                    case 3:
                        // Garante que exista alguem cadastrado antes de classificar.
                        validarPessoaCadastrada(pessoaCadastrada);

                        // Exibe perfil, IMC e classificacao da pessoa atual.
                        classificarIMC(pessoaCadastrada);
                        break;
                    case 4:
                        // Cadastra um atleta, que tambem e uma Pessoa.
                        pessoaCadastrada = cadastrarAtleta(scanner);

                        // Registra o atleta no historico da sessao.
                        sistema.registrar(pessoaCadastrada);

                        System.out.println("Atleta cadastrado com sucesso.");
                        break;
                    case 5:
                        // Exibe todos os registros armazenados no historico da sessao.
                        sistema.exibirHistorico();
                        break;
                    case 0:
                        // Encerra o loop porque opcao passa a ser zero.
                        System.out.println("Encerrando o sistema.");
                        break;
                    default:
                        // Qualquer numero fora das opcoes previstas gera excecao personalizada.
                        throw new EntradaInvalidaException("Opcao invalida.");
                }
            } catch (EntradaInvalidaException e) {
                // Captura erros de validacao e mostra uma mensagem amigavel ao usuario.
                System.out.println("Erro: " + e.getMessage());
            }

            // Antes de voltar ao menu, pausa a tela para o usuario ler o resultado.
            if (opcao != 0) {
                aguardarEnter(scanner);
            }
        }

        // Fecha o Scanner ao final do programa.
        scanner.close();
    }

    // Imprime o menu principal do sistema.
    private static void exibirMenu() {
        System.out.println("=== Sistema de Calculo de IMC === \n");
        System.out.println("1 - Cadastrar nova pessoa");
        System.out.println("2 - Calcular e exibir IMC");
        System.out.println("3 - Classificar IMC");
        System.out.println("4 - Cadastrar atleta");
        System.out.println("5 - Exibir historico");
        System.out.println("0 - Sair \n");
    }

    // Tenta limpar a tela antes de voltar ao menu principal.
    private static void limparTela() {
        // Codigo ANSI para limpar tela em terminais que suportam esse recurso.
        System.out.print("\033[H\033[2J");
        System.out.flush();

        try {
            // Comando especifico do Windows para limpar o prompt.
            new ProcessBuilder("cmd", "/c", "cls")
                    .inheritIO()
                    .start()
                    .waitFor();
        } catch (Exception e) {
            // Se o console nao permitir limpeza real, imprime linhas em branco como fallback.
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    // Pausa o programa ate o usuario pressionar Enter.
    private static void aguardarEnter(Scanner scanner) {
        System.out.println();
        System.out.print("Pressione Enter para voltar ao menu principal...");
        scanner.nextLine();
    }

    // Le os dados necessarios e cria uma Pessoa comum.
    private static Pessoa cadastrarPessoa(Scanner scanner) {
        // Le cada campo usando metodos auxiliares com validacao.
        String nome = lerTexto(scanner, "nome");
        int idade = lerInt(scanner, "idade");
        double peso = lerDouble(scanner, "peso (em kg)");
        double altura = lerDouble(scanner, "altura (em metros)");

        // Retorna o objeto Pessoa preenchido.
        return new Pessoa(nome, idade, peso, altura);
    }

    // Le os dados necessarios e cria um Atleta.
    private static Atleta cadastrarAtleta(Scanner scanner) {
        // Atleta possui os mesmos dados de Pessoa e mais a modalidade esportiva.
        String nome = lerTexto(scanner, "nome");
        int idade = lerInt(scanner, "idade");
        double peso = lerDouble(scanner, "peso");
        double altura = lerDouble(scanner, "altura");
        String modalidade = lerTexto(scanner, "modalidade");

        // Retorna o objeto Atleta preenchido.
        return new Atleta(nome, idade, peso, altura, modalidade);
    }

    // Calcula e exibe a classificacao da pessoa ou atleta atual.
    private static void classificarIMC(Pessoa pessoa) {
        // Calcula o IMC usando os dados do objeto recebido.
        double imc = calcularIMC(pessoa);

        // Chamada polimorfica: se for Atleta, usa a classificacao de Atleta.
        String classificacao = pessoa.classificarIMC(imc);

        // Mostra perfil, valor do IMC e classificacao textual.
        System.out.println(pessoa.exibirPerfil());
        System.out.printf("IMC: %.2f%n", imc);
        System.out.println("Classificacao: " + classificacao);
    }

    // Centraliza o calculo do IMC para evitar repeticao de codigo.
    private static double calcularIMC(Pessoa pessoa) {
        return pessoa.calcularIMC(pessoa.getPeso(), pessoa.getAltura());
    }

    // Verifica se ja existe pessoa ou atleta cadastrado antes de calcular/classificar.
    private static void validarPessoaCadastrada(Pessoa pessoa) {
        // Se a variavel ainda for null, nenhuma pessoa foi cadastrada.
        if (pessoa == null) {
            throw new EntradaInvalidaException("Cadastre uma pessoa ou atleta antes.");
        }
    }

    // Le campos de texto e impede entradas vazias.
    private static String lerTexto(Scanner scanner, String campo) {
        System.out.print("Digite " + campo + ": ");

        // trim remove espacos antes e depois do texto.
        String valor = scanner.nextLine().trim();

        // Texto vazio nao e aceito para campos obrigatorios.
        if (valor.isEmpty()) {
            throw new EntradaInvalidaException(campo + " nao pode ficar vazio.");
        }

        return valor;
    }

    // Le numeros inteiros, como idade e opcao do menu.
    private static int lerInt(Scanner scanner, String campo) {
        System.out.print("Digite " + campo + ": ");

        // Le a entrada como texto para permitir tratamento manual de erros.
        String entrada = scanner.nextLine().trim();

        try {
            // Converte o texto digitado para inteiro.
            int valor = Integer.parseInt(entrada);

            // Para campos diferentes de opcao, o numero precisa ser positivo.
            if (valor <= 0 && !"opcao".equals(campo)) {
                throw new EntradaInvalidaException(campo + " deve ser positivo.");
            }

            return valor;
        } catch (NumberFormatException e) {
            // Se a conversao falhar, informa que o campo exige inteiro valido.
            throw new EntradaInvalidaException("Digite um numero inteiro valido para " + campo + ".");
        }
    }

    // Le numeros decimais, como peso e altura.
    private static double lerDouble(Scanner scanner, String campo) {
        System.out.print("Digite " + campo + ": ");

        // Aceita tanto virgula quanto ponto como separador decimal.
        String entrada = scanner.nextLine().trim().replace(',', '.');

        try {
            // Converte o texto digitado para double.
            double valor = Double.parseDouble(entrada);

            // Peso e altura precisam ser maiores que zero.
            if (valor <= 0) {
                throw new EntradaInvalidaException(campo + " deve ser positivo.");
            }

            return valor;
        } catch (NumberFormatException e) {
            // Se a conversao falhar, informa que o campo exige numero valido.
            throw new EntradaInvalidaException("Digite um numero valido para " + campo + ".");
        }
    }
}
