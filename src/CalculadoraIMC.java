// Interface que define o contrato para classes capazes de calcular e classificar IMC.
// Quem implementa esta interface precisa obrigatoriamente implementar os dois metodos abaixo.
public interface CalculadoraIMC {

    // Calcula o valor numerico do IMC usando peso e altura.
    double calcularIMC(double peso, double altura);

    // Recebe um IMC ja calculado e retorna a classificacao textual correspondente.
    String classificarIMC(double imc);
}
