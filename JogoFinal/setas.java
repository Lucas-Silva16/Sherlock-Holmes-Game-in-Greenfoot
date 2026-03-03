import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe setas.
 * <p>
 * Representa um indicador visual de setas no ecrã (HUD ou tutorial de controles).
 * Herda de {@link Actor} do Greenfoot.
 * </p>
 * 
 * Funcionalidades principais:
 * <ul>
 *   <li>Redimensiona a imagem do ator para 400x400 pixels ao ser criada.</li>
 *   <li>Não possui ações automáticas no método {@link Actor#act()}.</li>
 * </ul>
 */
public class setas extends Actor {

    /**
     * Construtor da classe setas.
     * <p>
     * Inicializa o ator e ajusta o tamanho da imagem para 400x400 pixels.
     * </p>
     */
    public setas() {
        GreenfootImage img = getImage();
        img.scale(400, 400);
        setImage(img);
    }
}
