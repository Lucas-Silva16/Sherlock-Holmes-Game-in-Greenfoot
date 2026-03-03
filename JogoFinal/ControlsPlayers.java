import greenfoot.*;

/**
 * Classe responsável por controlar o comportamento dos jogadores.
 * 
 * <p>Esta classe implementa:</p>
 * <ul>
 *   <li>Movimento horizontal e vertical;</li>
 *   <li>Salto e gravidade;</li>
 *   <li>Sistema de tiros com munição limitada e recarregamento;</li>
 *   <li>Animação de sprites para andar e disparar;</li>
 *   <li>Colisão com obstáculos como paredes e plataformas.</li>
 * </ul>
 * 
 * <p>O objeto {@link ControlsPlayers} deve ser instanciado para cada jogador, fornecendo
 * teclas de controlo, sprites de animação e parâmetros de física.</p>
 * 
 * @author Grupo 8
 */
public class ControlsPlayers {

    /** Sprites de disparo. */
    private GreenfootImage[] framesShoot;

    /** Sprites de andar. */
    private GreenfootImage[] framesWalk;

    /** Índice do frame atual da animação. */
    private int frameIndex = 0;

    /** Delay entre frames da animação. */
    private int delay = 5;

    /** Contador interno para controlar o delay da animação. */
    private int delayCounter = 0;

    /** Velocidade horizontal do jogador. */
    private int speed;

    /** Força do salto do jogador. */
    private int jumpStrength;

    /** Gravidade aplicada verticalmente. */
    private int gravity = 1;

    /** Velocidade vertical atual do jogador. */
    public int velocityY = 0;

    /** Indica se o jogador está no chão. */
    public boolean onGround = true;

    /** Direção para a qual o jogador está virado (true = direita). */
    private boolean facingRight = true;

    /** Estado de disparo. */
    private boolean isShooting = false;
    private boolean hasShot = false;

    /** Flags de permissão de ações. */
    private boolean canMove = true;
    private boolean canShoot = true;
    private boolean canVerticalMove = false;
    private boolean canJump = true;

    /** Teclas configuradas para controlo do jogador. */
    private String leftKey, rightKey, jumpKey, shootKey, upKey, downKey;

    /** Nome do ficheiro de som do disparo. */
    private String shootSound;

    /** Nível do chão para o jogador. */
    private int groundLevel;

    /** Sistema de balas e recarregamento. */
    private int balasRestantes = 10;
    private boolean recarregando = false;
    private long inicioRecarga = 0;
    private long tempoRecarga = 2000;

    /**
     * Construtor da classe ControlsPlayers.
     * 
     * @param walkPrefix Prefixo das imagens de animação de andar
     * @param walkFrames Número de frames de andar
     * @param shootPrefix Prefixo das imagens de animação de disparo
     * @param shootFrames Número de frames de disparo
     * @param leftKey Tecla para mover para a esquerda
     * @param rightKey Tecla para mover para a direita
     * @param jumpKey Tecla para saltar
     * @param shootKey Tecla para disparar
     * @param upKey Tecla para movimento vertical para cima
     * @param downKey Tecla para movimento vertical para baixo
     * @param shootSound Nome do ficheiro de som do disparo
     * @param speed Velocidade horizontal do jogador
     * @param jumpStrength Força do salto
     * @param groundLevel Posição do chão
     */
    public ControlsPlayers(
        String walkPrefix, int walkFrames,
        String shootPrefix, int shootFrames,
        String leftKey, String rightKey, String jumpKey, String shootKey,
        String upKey, String downKey,
        String shootSound,
        int speed, int jumpStrength, int groundLevel
    ) {
        framesWalk = new GreenfootImage[walkFrames];
        for (int i = 0; i < framesWalk.length; i++) {
            framesWalk[i] = new GreenfootImage(walkPrefix + (i + 1) + ".png");
            framesWalk[i].scale(110, 110);
        }

        framesShoot = new GreenfootImage[shootFrames];
        for (int i = 0; i < framesShoot.length; i++) {
            framesShoot[i] = new GreenfootImage(shootPrefix + (i + 1) + ".png");
            framesShoot[i].scale(110, 110);
        }

        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.jumpKey = jumpKey;
        this.shootKey = shootKey;
        this.upKey = upKey;
        this.downKey = downKey;
        this.shootSound = shootSound;
        this.speed = speed;
        this.jumpStrength = jumpStrength;
        this.groundLevel = groundLevel;
    }

    /** Define se o jogador pode mover-se. */
    public void setCanMove(boolean canMove) { this.canMove = canMove; }

    /** Define se o jogador pode disparar. */
    public void setCanShoot(boolean canShoot) { this.canShoot = canShoot; }

    /** Define se o jogador pode mover-se verticalmente. */
    public void setVerticalMovement(boolean canVerticalMove) { this.canVerticalMove = canVerticalMove; }

    /** Define se o jogador pode saltar. */
    public void setCanJump(boolean canJump) { this.canJump = canJump; }

    /**
     * Atualiza a animação do jogador, tratando disparo e andar.
     * 
     * @param actor Actor do jogador
     */
    public void updateAnimation(Actor actor) {
        if (recarregando) {
            if (System.currentTimeMillis() - inicioRecarga >= tempoRecarga) {
                balasRestantes = 10;
                recarregando = false;
                canShoot = true;
            }
            return;
        }

        if (isShooting) {
            animate(actor, framesShoot);

            if (!hasShot && canShoot) {
                if (balasRestantes > 0) disparar(actor);
                else iniciarReload();
                hasShot = true;
            }

            if (frameIndex == framesShoot.length - 1 && delayCounter == 0) {
                isShooting = false;
                hasShot = false;
                frameIndex = 0;
            }
        } else {
            if (canShoot && Greenfoot.isKeyDown(shootKey)) {
                isShooting = true;
                frameIndex = 0;
                delayCounter = 0;
            } else if (Greenfoot.isKeyDown(rightKey) || Greenfoot.isKeyDown(leftKey)) {
                animate(actor, framesWalk);
            }
        }
    }

    /** Dispara uma bala e reproduz o som correspondente. */
    private void disparar(Actor actor) {
        World world = actor.getWorld();
        if (world == null) return;

        Greenfoot.playSound(shootSound);
        Bala bala = new Bala(facingRight);
        int offset = 60;
        int yoffset = -20;
        int x = facingRight ? actor.getX() + offset : actor.getX() - offset;
        int y = actor.getY() + yoffset;
        world.addObject(bala, x, y);

        balasRestantes--;
        if (balasRestantes <= 0) {
            iniciarReload();
            Greenfoot.playSound("reload.mp3");
        }
    }

    /** Inicia o processo de recarregamento das balas. */
    private void iniciarReload() {
        recarregando = true;
        canShoot = false;
        inicioRecarga = System.currentTimeMillis(); 
    }

    /** Atualiza a animação do jogador de acordo com os frames fornecidos. */
    private void animate(Actor actor, GreenfootImage[] frames) {
        delayCounter++;
        if (delayCounter >= delay) {
            frameIndex = (frameIndex + 1) % frames.length;
            GreenfootImage img = new GreenfootImage(frames[frameIndex]);
            if (!facingRight) img.mirrorHorizontally();
            actor.setImage(img);
            delayCounter = 0;
        }
    }

    /**
     * Controla o movimento horizontal, vertical e salto do jogador.
     * Aplica gravidade e trata colisão com paredes.
     * 
     * @param actor Actor do jogador
     */
    public void movePlayer(Actor actor) {
        if (!canMove) return;

        int xOld = actor.getX();
        int yOld = actor.getY();
        int x = xOld;
        int y = yOld;

        if (Greenfoot.isKeyDown(rightKey)) { x += speed; facingRight = true; }
        if (Greenfoot.isKeyDown(leftKey))  { x -= speed; facingRight = false; }

        if (canVerticalMove) {
            if (Greenfoot.isKeyDown(upKey))   y -= speed;
            if (Greenfoot.isKeyDown(downKey)) y += speed;
        }

        if (canJump && Greenfoot.isKeyDown(jumpKey) && onGround) {
            velocityY = jumpStrength;
            onGround = false;
        }

        if (canJump) {
            velocityY += gravity;
            y += velocityY;
            if (y >= groundLevel) {
                y = groundLevel;
                velocityY = 0;
                onGround = true;
            }
        }

        actor.setLocation(x, y);

        if (actor instanceof Jogador) {
            Jogador jogador = (Jogador) actor;
            if (jogador.pixelPerfectTouching(Wall.class) || jogador.pixelPerfectTouching(Wall2.class)) {
                actor.setLocation(xOld, yOld);
            }
        }
    }

    /** Retorna a imagem inicial de andar do jogador. */
    public GreenfootImage getInitialImage() {
        return framesWalk[0];
    }

    /** Reinicia a velocidade vertical e marca o jogador como estando no chão. */
    public void resetVelocity() {
        velocityY = 0;
        onGround = true;
    }

    /** Verifica se a tecla de disparo está a ser pressionada. */
    public boolean isShootPressed() {
        return Greenfoot.isKeyDown(shootKey);
    }

    /** Dispara manualmente uma bala. */
    public void shoot(Actor actor) {
        disparar(actor);
    }
}
