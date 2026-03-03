import greenfoot.*;
import java.util.List;

/**
 * Classe que representa o Fantasma, um inimigo do jogo.
 * 
 * <p>Herda de {@link Inimigo} e possui:
 * <ul>
 *   <li>1 vida, velocidade de 3, delay inicial de spawn de 60 frames;</li>
 *   <li>Sons de spawn, hit e morte;</li>
 *   <li>Sprites de spawn/idle e de ação (perseguição do jogador);</li>
 *   <li>Comportamento: persegue jogadores vivos.</li>
 * </ul></p>
 * 
 * <p>Autor: Grupo 8</p>
 */
public class Fantasma extends Inimigo {

    /**
     * Construtor do Fantasma.
     * Inicializa vida, velocidade, delay, sprites e sons.
     */
    public Fantasma() {
        super(null, 1, 3, 60); // target inicial nulo, 1 vida, velocidade 3, delay 60 frames

        // Sons
        somSpawn = "fantasma_spawn.mp3";
        somHit   = "fantasma_hit.mp3";
        somMorte = "fantasma_morrer.mp3";

        // Sprites de spawn/idle
        appear = new GreenfootImage[6];
        for (int i = 0; i < appear.length; i++) {
            appear[i] = new GreenfootImage("appear" + (i+1) + ".png");
            appear[i].scale(167, 167);
        }

        // Sprites de ação (chase)
        action = new GreenfootImage[4];
        for (int i = 0; i < action.length; i++) {
            action[i] = new GreenfootImage("chase" + (i+1) + ".png");
            action[i].scale(167, 167);
        }

        // Inicializa com a primeira sprite
        setImage(appear[0]);
    }

    /**
     * Comportamento específico do Fantasma.
     * 
     * <p>Persegue o jogador alvo. Se não houver jogadores vivos, termina o jogo.</p>
     */
    @Override
    protected void comportamento() {
        if (getWorld().getObjects(Jogador.class).isEmpty()) {
            Greenfoot.setWorld(new GameOverWorld());
            return;
        }

        // Move em direção ao jogador
        moverParaPlayer();
    }
}
