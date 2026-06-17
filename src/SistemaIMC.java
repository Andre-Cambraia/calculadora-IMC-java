// SistemaIMC TEM-UM Historico, demonstrando composicao.
// A classe SistemaIMC controla o processamento e usa Historico internamente.
public class SistemaIMC {

    // Objeto Historico que pertence ao SistemaIMC durante a sessao atual.
    private Historico historico = new Historico();

    // Registra uma pessoa ou atleta no historico.
    public void registrar(Pessoa pessoa) {
        historico.adicionar(criarLinhaHistorico(pessoa));
    }

    // Calcula e exibe o IMC da pessoa informada sem criar um novo cadastro.
    public void processar(Pessoa pessoa) {
        System.out.println(criarLinhaHistorico(pessoa));
    }

    // Solicita ao objeto Historico que exiba todos os registros armazenados.
    public void exibirHistorico() {
        historico.exibir();
    }

    // Monta a linha padronizada do historico com nome, IMC e classificacao.
    private String criarLinhaHistorico(Pessoa pessoa) {
        // Calcula o IMC usando o metodo implementado pela propria Pessoa.
        double imc = pessoa.calcularIMC(pessoa.getPeso(), pessoa.getAltura());

        // Chama classificarIMC de forma polimorfica.
        // Se o objeto for Atleta, a classificacao diferenciada sera usada.
        String classe = pessoa.classificarIMC(imc);

        // Retorna a linha pronta para exibicao ou armazenamento no historico.
        return pessoa.getNome() + " -> IMC: " + String.format("%.2f", imc) + " (" + classe + ")";
    }
}
