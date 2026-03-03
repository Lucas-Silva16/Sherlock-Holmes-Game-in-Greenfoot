import greenfoot.*;

/**
 * Classe abstrata base para elementos de interface gráfica (UI) do jogo.
 * <p>
 * Serve como base para todos os elementos de UI, como indicadores de vida, barras, ou ícones.
 * Fornece funcionalidades comuns, como remoção do mundo e manipulação de imagem.
 * Não deve ser instanciada diretamente; outras classes como {@link VidaUI}, {@link WatsonUI} e {@link HolmesUI} herdam desta classe.
 * </p>
 */
public abstract class UI extends Actor {

    /**
     * Construtor da classe UI.
     * <p>
     * Permite que subclasses inicializem elementos de interface.
     * </p>
     */
    public UI() {
        // Construtor vazio, inicialização feita em subclasses
    }

    /**
     * Remove este ator do mundo.
     * <p>
     * Verifica se o ator está atualmente em um mundo e, se sim, remove-o.
     * Útil para limpar elementos de UI dinamicamente.
     * </p>
     */
    public void removerDoMundo() {
        if (getWorld() != null) {
            getWorld().removeObject(this);
        }
    }
}
