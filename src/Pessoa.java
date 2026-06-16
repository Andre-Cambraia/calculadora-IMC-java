// Pessoa herda nome e idade de PessoaBase e implementa o contrato CalculadoraIMC.
public class Pessoa extends PessoaBase implements CalculadoraIMC {

    // Atributo privado para aplicar encapsulamento.
    private double peso;

    // Atributo privado que guarda a altura em metros.
    private double altura;

    // Indica se a pessoa esta ativa no sistema.
    private boolean ativo;

    // Construtor usado para criar uma pessoa com os dados principais.
    public Pessoa(String nome, int idade, double peso, double altura) {
        // Envia nome e idade para o construtor da classe abstrata PessoaBase.
        super(nome, idade);

        // Inicializa os atributos especificos de Pessoa.
        this.peso = peso;
        this.altura = altura;
        this.ativo = true;
    }

    // Retorna o peso cadastrado.
    public double getPeso() {
        return peso;
    }

    // Retorna a altura cadastrada.
    public double getAltura() {
        return altura;
    }

    // Retorna se a pessoa esta ativa.
    public boolean isAtivo() {
        return ativo;
    }

    // Atualiza o peso com validacao para impedir valores invalidos.
    public void setPeso(double peso) {
        // Peso igual ou menor que zero nao e valido para calculo de IMC.
        if (peso <= 0) throw new IllegalArgumentException("Peso deve ser positivo!");

        // Se passou pela validacao, atualiza o atributo.
        this.peso = peso;
    }

    // Implementa o calculo definido pela interface CalculadoraIMC.
    @Override
    public double calcularIMC(double peso, double altura) {
        // Formula do IMC: peso dividido pela altura ao quadrado.
        return peso / (altura * altura);
    }

    // Implementa a classificacao padrao de IMC para uma pessoa comum.
    @Override
    public String classificarIMC(double imc) {
        // Cada faixa retorna uma classificacao diferente.
        if      (imc < 18.5) return "Abaixo do peso";
        else if (imc < 25.0) return "Peso normal";
        else if (imc < 30.0) return "Sobrepeso";
        else if (imc < 35.0) return "Obesidade grau I";
        else if (imc < 40.0) return "Obesidade grau II";
        else                   return "Obesidade grau III";
    }

    // Implementa o metodo abstrato herdado de PessoaBase.
    @Override
    public String exibirPerfil() {
        // Retorna uma descricao textual dos dados da pessoa.
        return "Pessoa: " + nome + " | Idade: " + idade +
                " | Peso: " + peso + "kg | Altura: " + altura + "m";
    }
}
