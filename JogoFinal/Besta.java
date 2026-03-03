import greenfoot.*;
import java.util.List;

/**
 * Classe que representa a Besta, um inimigo avançado do jogo.
 * 
 * <p>Herda de {@link Inimigo} e define:
 * <ul>
 *   <li>Vida inicial, velocidade e delay de spawn;</li>
 *   <li>Sons específicos de spawn, hit e morte;</li>
 *   <li>Sprites de animação para spawn/idle e ação;</li>
 *   <li>Comportamento de perseguição aos jogadores vivos.</li>
 * </ul></p>
 * 
 * <p>Se não houver jogadores vivos, o jogo termina com o ecrã de Game Over.</p>
 * 
 * <p>Autor: Grupo 8</p>
 */
public class Besta extends Inimigo {

    /**
     * Construtor da Besta.
     * Inicializa vidas, velocidade, delay, sprites e sons específicos.
     */
    public Besta() {
        super(null, 7, 1 , 60); // target inicial nulo, 7 vidas, velocidade 1, delay de spawn 60 frames

        // Sons
        somSpawn = "besta_spawn.mp3";
        somHit   = "besta_hit.mp3";
        somMorte = "besta_morrer.mp3";

        // Sprites de spawn/idle
        appear = new GreenfootImage[6];
        for (int i = 0; i < appear.length; i++) {
            appear[i] = new GreenfootImage("besta" + (i+1) + ".png");
            appear[i].scale(180, 180);
        }

        // Sprites de ação (chase)
        action = new GreenfootImage[6];
        for (int i = 0; i < action.length; i++) {
            action[i] = new GreenfootImage("b" + (i+1) + ".png");
            action[i].scale(180, 180);
        }

        // Inicializa com a primeira sprite
        setImage(appear[0]);
    }

    /**
     * Comportamento específico da Besta.
     * 
     * <p>Persegue o jogador alvo. Se não houver jogadores vivos, termina o jogo.</p>
     */
    @Override
    protected void comportamento() {
        if (getWorld().getObjects(Jogador.class).isEmpty()) {
            Greenfoot.setWorld(new GameOverWorld());
            return;
        }

        moverParaPlayer();
    }
}
