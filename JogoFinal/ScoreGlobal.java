import greenfoot.*;

/**
 * Classe responsável por gerir a pontuação global do jogo.
 * 
 * <p>Todos os métodos e variáveis são estáticos, permitindo que a pontuação
 * seja partilhada entre diferentes classes e mundos.</p>
 * 
 * <p>Funcionalidades principais:</p>
 * <ul>
 *   <li>Adicionar ou remover pontos;</li>
 *   <li>Garantir que a pontuação nunca é negativa;</li>
 *   <li>Reiniciar a pontuação;</li>
 *   <li>Obter o valor atual da pontuação.</li>
 * </ul>
 * 
 * @author Grupo 8
 */
public class ScoreGlobal {
    
    /** Pontuação total atual. */
    private static int pontos = 0;

    /**
     * Adiciona ou remove pontos ao total da pontuação.
     * 
     * @param valor Número de pontos a adicionar (ou negativo para remover)
     */
    public static void adicionar(int valor) {
        pontos += valor;
        if (pontos < 0) pontos = 0;
    }

    /** Reinicia a pontuação para zero. */
    public static void reset() {
        pontos = 0;
    }

    /**
     * Retorna o valor atual da pontuação.
     * 
     * @return Pontuação atual
     */
    public static int get() {
        return pontos;
    }
}
