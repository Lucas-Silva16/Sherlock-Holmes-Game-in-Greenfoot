import greenfoot.*;
import java.util.List;

/**
 * Classe que representa o Olho, um inimigo do jogo.
 * 
 * <p>Herda de {@link Inimigo} e possui:
 * <ul>
 *   <li>3 vidas, velocidade de 2, delay inicial de spawn de 60 frames;</li>
 *   <li>Sons de spawn, hit e morte;</li>
 *   <li>Sprites de olho, que fazem tanto spawn/idle quanto ação;</li>
 *   <li>Comportamento: persegue jogadores vivos.</li>
 * </ul></p>
 * 
 * <p>Autor: Grupo 8</p>
 */
public class Olho extends Inimigo {

    /**
     * Construtor do Olho.
     * Inicializa vida, velocidade, delay, sprites e sons.
     */
    public Olho() {
        super(null, 3, 2, 60); // target inicial nulo, 3 vidas, velocidade 2, delay 60 frames

        // Sons
        somSpawn = "olho_spawn.mp3";
        somHit   = "olho_hit.mp3";
        somMorte = "olho_morrer.mp3";

        // Sprites do olho
        olho = new GreenfootImage[8];
        for (int i = 0; i < olho.length; i++) {
            olho[i] = new GreenfootImage("olho" + (i+1) + ".png");
            olho[i].scale(167, 167);
            olho[i].mirrorHorizontally(); // espelha horizontalmente
        }

        appear = olho;
        action = olho;  // mantém animação infinita
        setImage(appear[0]);
    }

    /**
     * Comportamento específico do Olho.
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
