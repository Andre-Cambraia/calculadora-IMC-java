// Importa ArrayList, a implementacao usada para guardar os registros.
import java.util.ArrayList;

// Importa List, que representa a estrutura de lista de forma mais generica.
import java.util.List;

// Classe responsavel por armazenar e exibir o historico da sessao.
public class Historico {

    // Lista que guarda as linhas de historico geradas durante a execucao.
    private List<String> registros = new ArrayList<>();

    // Adiciona uma nova linha ao historico.
    public void adicionar(String r) {
        registros.add(r);
    }

    // Exibe todos os registros salvos na sessao atual.
    public void exibir() {
        // Se nao houver registros, informa o usuario e encerra o metodo.
        if (registros.isEmpty()) {
            System.out.println();
            System.out.println("Nenhum(a) pessoa/atleta registrado(a).");
            return;
        }

        // Percorre a lista e imprime cada registro armazenado.
        for (String r : registros) {
            System.out.println("  -> " + r);
        }
    }
}

// SistemaIMC TEM-UM Historico, demonstrando composicao.
// A classe SistemaIMC controla o processamento e usa Historico internamente.
class SistemaIMC {

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
