// A classe Atleta especializa Pessoa.
// Ela herda os dados basicos de Pessoa e altera a forma de classificar o IMC.
public class Atleta extends Pessoa {

    // Modalidade representa o esporte praticado pelo atleta.
    private String modalidade;

    // Construtor usado para criar um atleta com nome, idade, peso, altura e modalidade.
    public Atleta(String nome, int idade, double peso,
                  double altura, String modalidade) {
        // Chama o construtor da superclasse Pessoa para reaproveitar a inicializacao.
        super(nome, idade, peso, altura);

        // Armazena a modalidade especifica do atleta.
        this.modalidade = modalidade;
    }

    // Sobrescreve o metodo de Pessoa para demonstrar polimorfismo.
    // A chamada pessoa.classificarIMC(imc) pode executar esta versao quando o objeto for Atleta.
    @Override
    public String classificarIMC(double imc) {
        // Atletas usam faixas de classificacao diferentes das pessoas comuns.
        if      (imc < 20.0) return "Abaixo do ideal para atleta";
        else if (imc < 27.0) return "Ideal para atleta";
        else                   return "Acima do ideal para atleta";
    }

    // Sobrescreve o metodo de exibicao para incluir tambem a modalidade.
    @Override
    public String exibirPerfil() {
        // Reaproveita o texto da classe Pessoa e adiciona a informacao do atleta.
        return super.exibirPerfil() + " | Modalidade: " + modalidade;
    }
}
