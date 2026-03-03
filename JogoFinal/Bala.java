import greenfoot.*;

/**
 * Representa os projéteis disparados pelos jogadores.
 *
 * <p>Esta classe herda de {@link Colisoes}, permitindo detecção de colisão
 * pixel-perfect se necessário.</p>
 *
 * <p>Funcionalidades principais:</p>
 * <ul>
 *   <li>Define a direção e velocidade do projétil;</li>
 *   <li>Move a bala na direção definida durante o jogo;</li>
 *   <li>Verifica colisão com inimigos ({@link Inimigo}) e aplica dano;</li>
 *   <li>Remove a bala caso atinja um inimigo ou a borda do mundo;</li>
 *   <li>Respeita o estado de pausa do jogo.</li>
 * </ul>
 * 
 * <p>Este actor é utilizado para representar as balas disparadas tanto por Sherlock como por Watson.</p>
 * 
 * @author Grupo 8
 */
public class Bala extends Colisoes {

    /** Velocidade da bala em pixels por frame. */
    private int speed = 20;

    /** Direção da bala: true = direita, false = esquerda. */
    private boolean facingRight;

    /**
     * Construtor da classe {@code Bala}.
     *
     * @param facingRight direção inicial da bala (true = direita, false = esquerda)
     */
    public Bala(boolean facingRight) {
        this.facingRight = facingRight;
        GreenfootImage img = new GreenfootImage("bullet.png");
        img.scale(15, 10); // redimensiona a imagem da bala
        setImage(img);
    }

    /**
     * Método executado continuamente pelo ciclo de jogo.
     *
     * <p>Move a bala, verifica colisão com inimigos e remove-a caso necessário,
     * respeitando o estado de pausa do jogo.</p>
     */
    @Override
    public void act() {
        BaseWorld world = (BaseWorld) getWorld();
        if (world != null && !world.isGamePaused()) {
            mover();
            verificarColisao();
            if (getWorld() != null) {
                removerBala();
            }
        }
    }

    /**
     * Move a bala na direção definida.
     */
    private void mover() {
        if (facingRight) {
            setLocation(getX() + speed, getY());
        } else {
            setLocation(getX() - speed, getY());
        }
    }

    /**
     * Verifica se a bala colidiu com algum inimigo.
     *
     * <p>Se ocorrer colisão, aplica dano ao inimigo e remove a bala do mundo.</p>
     */
    private void verificarColisao() {
        Inimigo inimigo = (Inimigo) getOneIntersectingObject(Inimigo.class);
        if (inimigo != null) {
            inimigo.levarDano();
            getWorld().removeObject(this);
        }
    }

    /**
     * Remove a bala se atingir a borda do mundo.
     */
    private void removerBala() {
        if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }
}
