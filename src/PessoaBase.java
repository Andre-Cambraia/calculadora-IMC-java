// Classe abstrata que concentra dados comuns de pessoas no sistema.
// Nao pode ser instanciada diretamente, apenas herdada por subclasses.
public abstract class PessoaBase {

    // protected permite acesso direto pelas subclasses, como Pessoa.
    protected String nome;

    // Idade tambem fica disponivel para subclasses.
    protected int idade;

    // Construtor que inicializa os dados comuns.
    public PessoaBase(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    // Metodo abstrato: toda subclasse deve implementar sua propria exibicao de perfil.
    public abstract String exibirPerfil();

    // Retorna o nome armazenado.
    public String getNome() {
        return nome;
    }

    // Retorna a idade armazenada.
    public int getIdade() {
        return idade;
    }
}
