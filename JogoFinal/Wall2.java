import greenfoot.*;

/**
 * Representa uma parede ou obstáculo estático alternativo no mundo do jogo.
 * 
 * <p>Herda de {@link Colisoes}, permitindo ser utilizada nos sistemas
 * de deteção de colisão com jogadores, inimigos ou outros objetos.</p>
 * 
 * <p>Funcionalidades principais:</p>
 * <ul>
 *   <li>Serve como obstáculo físico no mundo do jogo;</li>
 *   <li>Não possui ações próprias no ciclo do jogo ({@link #act()});</li>
 *   <li>Integra-se com o sistema de colisões pixel-perfect de {@link Colisoes}.</li>
 * </ul>
 * 
 * <p>Esta classe é utilizada para criar barreiras ou limites nos níveis, podendo diferir visualmente de {@link Wall}.</p>
 * 
 * @author Grupo 8
 */
public class Wall2 extends Colisoes {

    /**
     * Método chamado continuamente pelo ciclo do jogo.
     * 
     * <p>Não possui implementação pois a parede é estática.</p>
     */
    public void act() {
        // A parede não executa ações, serve apenas como obstáculo
    }
}
