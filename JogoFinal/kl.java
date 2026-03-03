import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe kl.
 * <p>
 * Representa um indicador visual para controles ou HUD do jogador.
 * Herda de {@link Actor} do Greenfoot.
 * </p>
 * 
 * Funcionalidades principais:
 * <ul>
 *   <li>Redimensiona a imagem para 100x80 pixels ao ser criada.</li>
 *   <li>Não possui ações automáticas no método {@link Actor#act()}.</li>
 * </ul>
 */
public class kl extends Actor {

    /**
     * Construtor da classe kl.
     * <p>
     * Inicializa o ator e ajusta a imagem para 100x80 pixels.
     * </p>
     */
    public kl() {
        GreenfootImage img = getImage();
        img.scale(100, 80);
        setImage(img);
    }
}
