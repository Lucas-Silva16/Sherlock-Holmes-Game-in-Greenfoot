import greenfoot.*;

/**
 * Classe VidaUI.
 * <p>
 * Representa um coração na interface do jogador, indicando que ele possui vida.
 * Herda de {@link UI} e pode ser adicionado ao mundo para exibir visualmente as vidas restantes.
 * </p>
 * 
 * Funcionalidades principais:
 * <ul>
 *   <li>Exibe a imagem de um coração ("heartChar.png").</li>
 *   <li>Permite remover o coração do mundo quando o jogador perde vida.</li>
 *   <li>Permite consultar se o coração ainda está ativo/visível.</li>
 * </ul>
 */
public class VidaUI extends UI {

    /** Indica se este coração ainda está visível na interface */
    private boolean ativa = true;

    /**
     * Construtor da classe VidaUI.
     * <p>
     * Inicializa a imagem do coração.
     * </p>
     */
    public VidaUI() {
        setImage("heartChar.png");
    }

    /**
     * Marca este coração como perdido e o remove do mundo.
     * <p>
     * Só remove se ainda estiver ativo.
     * </p>
     */
    public void perderVida() {
        if (ativa) {
            removerDoMundo();
            ativa = false;
        }
    }

    /**
     * Verifica se o coração ainda está ativo/visível.
     * 
     * @return true se o coração ainda está visível, false caso contrário
     */
    public boolean isAtiva() {
        return ativa;
    }
}
