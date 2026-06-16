// Classe auxiliar para demonstrar uso de recursividade.
public class CalculadoraRecursiva {

    // Calcula base elevada a exp sem usar Math.pow().
    // Exemplo: potencia(altura, 2) pode ser usada para calcular altura ao quadrado.
    public static double potencia(double base, int exp) {
        // Caso-base da recursao: qualquer numero elevado a zero resulta em 1.
        if (exp == 0) return 1;

        // Passo recursivo: multiplica a base pelo resultado da potencia com expoente menor.
        return base * potencia(base, exp - 1);
    }
}
