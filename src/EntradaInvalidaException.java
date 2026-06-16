// Excecao personalizada usada quando o usuario informa dados invalidos.
// RuntimeException permite lancar a excecao sem obrigar tratamento em todos os metodos.
public class EntradaInvalidaException extends RuntimeException {

    // Recebe uma mensagem especifica e envia essa mensagem para a classe RuntimeException.
    public EntradaInvalidaException(String mensagem) {
        super(mensagem);
    }
}
