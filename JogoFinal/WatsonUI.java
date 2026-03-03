import greenfoot.*;

/**
 * Classe WatsonUI.
 * <p>
 * Representa o elemento de interface gráfica (UI) do personagem Watson.
 * Herda de {@link UI} e é usado para exibir o avatar ou informações gráficas de Watson no HUD do jogo.
 * </p>
 * 
 * Funcionalidades principais:
 * <ul>
 *   <li>Redimensiona a imagem para 500x300 pixels.</li>
 *   <li>Espelha horizontalmente a imagem, ajustando a direção para o display.</li>
 *   <li>Não possui ações automáticas no método {@link #act()}.</li>
 * </ul>
 */
public class WatsonUI extends UI {

    /**
     * Construtor da classe WatsonUI.
     * <p>
     * Inicializa a imagem de Watson, redimensionando e espelhando horizontalmente.
     * </p>
     */
    public WatsonUI() {
        GreenfootImage img = getImage();        
        img.scale(500, 300);
        img.mirrorHorizontally();
        setImage(img);  
    }

    /**
     * Método act() vazio.
     * <p>
     * Este ator não realiza ações automáticas no mundo do Greenfoot.
     * </p>
     */
    @Override
    public void act() {
        // Nenhuma ação automática necessária
    }
}
