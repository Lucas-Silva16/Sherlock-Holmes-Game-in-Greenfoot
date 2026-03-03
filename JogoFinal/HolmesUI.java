import greenfoot.*;

/**
 * Classe HolmesUI.
 * <p>
 * Representa o elemento de interface gráfica (UI) do personagem Holmes.
 * Herda de {@link UI} e pode ser adicionado ao mundo para exibir informações visuais
 * relacionadas a Holmes.
 * </p>
 * 
 * Funcionalidades principais:
 * <ul>
 *   <li>Redimensiona a imagem do Holmes para 500x300 pixels.</li>
 *   <li>Pode ser espelhado horizontalmente (se necessário).</li>
 *   <li>Herda métodos de UI, incluindo {@link #removerDoMundo()}.</li>
 * </ul>
 */
public class HolmesUI extends UI {

    /**
     * Construtor da classe HolmesUI.
     * <p>
     * Redimensiona a imagem do ator para 500x300 pixels e aplica-a.
     * </p>
     */
    public HolmesUI() {
        GreenfootImage img = getImage();
        img.scale(500, 300); // Redimensiona a imagem
        setImage(img);
    }

    /**
     * Método act do HolmesUI.
     * <p>
     * Atualmente vazio, pois o UI não precisa de ações contínuas.
     * </p>
     */
    @Override
    public void act() {
        // Nenhuma ação necessária no momento
    }
}
