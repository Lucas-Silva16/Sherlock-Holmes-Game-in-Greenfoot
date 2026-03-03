import greenfoot.*;

/**
 * Classe ef.
 * <p>
 * Representa uma imagem gráfica que indica algum controle ou elemento do HUD do jogo.
 * Herda de {@link Actor} do Greenfoot.
 * </p>
 * 
 * Funcionalidades principais:
 * <ul>
 *   <li>Redimensiona a imagem para 120x100 pixels ao ser criada.</li>
 *   <li>Não possui ações automáticas no método {@link Actor#act()}.</li>
 * </ul>
 */
public class ef extends Actor {

    /**
     * Construtor da classe ef.
     * <p>
     * Inicializa o ator e ajusta a imagem para 120x100 pixels.
     * </p>
     */
    public ef() {
        GreenfootImage img = getImage();
        img.scale(120, 100);
        setImage(img);
    }
}
