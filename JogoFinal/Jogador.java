import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Classe base para todos os jogadores do jogo ({@link Sherlock}, {@link Watson}).
 *
 * <p>Funcionalidades principais:</p>
 * <ul>
 *   <li>Controla movimento, animações e disparo do jogador através de {@link ControlsPlayers};</li>
 *   <li>Permite ativar/desativar movimento, salto e disparo;</li>
 *   <li>Gerencia vida do jogador e interface de corações ({@link VidaUI});</li>
 *   <li>Implementa recarga de munição e limite de balas restantes;</li>
 *   <li>Detecta colisões pixel-perfect com inimigos ({@link Inimigo}) e paredes ({@link Wall}, {@link Wall2});</li>
 *   <li>Trata remoção do jogador e transição para {@link GameOverWorld} caso perca todas as vidas;</li>
 *   <li>Permite reiniciar o nível e ajustar pontuação quando toca em paredes.</li>
 * </ul>
 * 
 * <p>Esta classe serve como base para os personagens jogáveis e fornece
 * toda a lógica de interação com o mundo do jogo.</p>
 * 
 * @author Grupo 8
 */
public class Jogador extends Colisoes {

    /** Controlador de animações, movimento e disparo do jogador. */
    protected ControlsPlayers controller;

    /** Indica se o jogador pode mover-se. */
    protected boolean canMove = true;

    /** Indica se o jogador pode disparar. */
    protected boolean canShoot = true;

    /** Número de vidas do jogador. */
    protected int vidas = 3;

    /** Interface gráfica de corações representando as vidas na tela. */
    protected List<VidaUI> coracoes = new ArrayList<>();

    /** Balas restantes do jogador. */
    private int balasRestantes = 10;

    /** Indica se está em recarga de munição. */
    private boolean recarregando = false;

    /** Tempo necessário para recarregar (ms). */
    private long tempoRecarga = 2000;

    /** Timestamp do início da recarga. */
    private long inicioRecarga = 0;

    /** Posição inicial do jogador (X) ao tocar nas paredes. */
    private int startX;

    /** Posição inicial do jogador (Y) ao tocar nas paredes. */
    private int startY;

    /**
     * Construtor da classe {@code Jogador}.
     *
     * @param walkPrefix prefixo para animação de andar
     * @param walkFrames número de frames da animação de andar
     * @param shootPrefix prefixo para animação de disparo
     * @param shootFrames número de frames da animação de disparo
     * @param leftKey tecla de movimento para a esquerda
     * @param rightKey tecla de movimento para a direita
     * @param jumpKey tecla de salto
     * @param shootKey tecla de disparo
     * @param upKey tecla para movimento vertical para cima
     * @param downKey tecla para movimento vertical para baixo
     * @param shootSound som de disparo
     * @param speed velocidade do jogador
     * @param jumpForce força do salto
     * @param groundLevel nível do chão no mundo
     */
    public Jogador(
        String walkPrefix, int walkFrames,
        String shootPrefix, int shootFrames,
        String leftKey, String rightKey, String jumpKey, String shootKey,
        String upKey, String downKey,
        String shootSound, int speed, int jumpForce, int groundLevel
    ) {
        controller = new ControlsPlayers(
            walkPrefix, walkFrames,
            shootPrefix, shootFrames,
            leftKey, rightKey, jumpKey, shootKey,
            upKey, downKey,
            shootSound, speed, jumpForce, groundLevel
        );
        setImage(controller.getInitialImage());
    }

    /**
     * Define a posição inicial do jogador para reaparecer ao tocar em paredes.
     *
     * @param x coordenada X inicial
     * @param y coordenada Y inicial
     */
    public void setStartPosition(int x, int y) {
        this.startX = x;
        this.startY = y;
    }

    /** Ativa ou desativa o movimento do jogador. */
    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
        if (controller != null) controller.setCanMove(canMove);
    }

    /** Ativa ou desativa a capacidade de disparar. */
    public void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
        if (controller != null) controller.setCanShoot(canShoot);
    }

    /** Ativa ou desativa o movimento vertical. */
    public void setVerticalMovement(boolean allowVertical) {
        if (controller != null) controller.setVerticalMovement(allowVertical);
    }

    /** Ativa ou desativa o salto. */
    public void setCanJump(boolean allowJump) {
        if (controller != null) controller.setCanJump(allowJump);
    }

    /** Define os corações a exibir na interface do jogador. */
    public void setCoracoes(List<VidaUI> coracoes) {
        this.coracoes = coracoes;
    }

    /**
     * Método executado continuamente pelo ciclo de jogo.
     *
     * <p>Controla colisão com inimigos, movimento, animações e colisão com paredes.
     * Trata perda de vida, atualização da interface de corações e transição de nível ou Game Over.</p>
     */
    @Override
    public void act() {
        BaseWorld world = (BaseWorld) getWorld();
        if (world == null || world.isGamePaused()) return;

        // Colisão com inimigos
        if (pixelPerfectTouching(Inimigo.class) && vidas > 0) {
            Inimigo inimigo = (Inimigo) getOneIntersectingObject(Inimigo.class);
            if (inimigo != null) {
                vidas--;
                if (!coracoes.isEmpty()) {
                    VidaUI ultimo = coracoes.get(coracoes.size() - 1);
                    world.removeObject(ultimo);
                    coracoes.remove(ultimo);
                }
                world.removeObject(inimigo);
                if (vidas <= 0) {
                    world.removeObject(this);
                    if (this instanceof Sherlock) {
                        Greenfoot.setWorld(new GameOverWorld());
                    }
                    return;
                }
            }
        }

        // Movimento e animação
        if (controller != null) {
            if (canMove) controller.movePlayer(this);
            controller.updateAnimation(this);
        }

        // Colisão com paredes
        if (pixelPerfectTouching(Wall.class) || pixelPerfectTouching(Wall2.class)) {
            setLocation(startX, startY);
            ScoreGlobal.adicionar(-150);
            Score scoreActor = (Score) getWorld().getObjects(Score.class).get(0);
            if (scoreActor != null) scoreActor.atualizar();
            if (ScoreGlobal.get() <= 0) {
                Greenfoot.setWorld(new CLevel2());
            }
        }
    }

    /**
     * Mata instantaneamente o jogador, removendo corações e exibindo Game Over.
     */
    public void morrerInstantaneamente() {
        vidas = 0;
        BaseWorld world = (BaseWorld) getWorld();
        for (VidaUI coracao : coracoes) {
            world.removeObject(coracao);
        }
        coracoes.clear();
        world.removeObject(this);
        if (this instanceof Sherlock) {
            Greenfoot.setWorld(new GameOverWorld());
        }
    }

    /** Retorna o número de vidas restantes do jogador. */
    public int getVidas() {
        return vidas;
    }
}
