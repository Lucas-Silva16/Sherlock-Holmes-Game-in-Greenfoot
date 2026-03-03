import greenfoot.*;

/**
 * Classe awsd.
 * <p>
 * Representa uma imagem gráfica que indica os controles do jogador Watson (teclas W, A, S, D).
 * Herda de {@link Actor} do Greenfoot.
 * </p>
 * 
 * Funcionalidades principais:
 * <ul>
 *   <li>Redimensiona a imagem para 300x300 pixels no momento da criação.</li>
 *   <li>Não possui ações automáticas no método {@link Actor#act()}.</li>
 * </ul>
 */
public class awsd extends Actor {

    /**
     * Construtor da classe awsd.
     * <p>
     * Inicializa o ator e redimensiona a imagem para 300x300 pixels.
     * </p>
     */
    public awsd() {
        GreenfootImage img = getImage();        
        img.scale(300, 300);                     
        setImage(img);                          
    }
}
