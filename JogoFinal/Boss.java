import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe que representa o Boss, inimigo chefe do jogo.
 * 
 * <p>Herda de {@link Inimigo} e possui:
 * <ul>
 *   <li>Vidas altas (40), movimento lento (0.5) e delay inicial de spawn;</li>
 *   <li>Sons de spawn, hit e morte;</li>
 *   <li>Sprites de animação para spawn/idle e ataque;</li>
 *   <li>Comportamento específico: persegue jogadores vivos e mata instantaneamente ao contacto;</li>
 *   <li>Ao morrer, adiciona pontos extra e envia o jogador para o ecrã de vitória.</li>
 * </ul></p>
 * 
 * <p>Autor: Grupo 8</p>
 */
public class Boss extends Inimigo
{
    private Score score;

    /**
     * Construtor do Boss.
     * Inicializa vidas, velocidade, delay, sprites e sons específicos.
     */
    public Boss() {
        super(null, 40, 0.5 , 60); // target inicial nulo, 40 vidas, velocidade 0.5, delay 60 frames

        // Sons
        somSpawn = "bossSpawn.mp3";
        somHit   = "hitboss.mp3";
        somMorte = "besta_morrer.mp3";

        // Sprites de spawn/idle
        appear = new GreenfootImage[17];
        for (int i = 0; i < appear.length; i++) {
            appear[i] = new GreenfootImage("bossHowling" + (i+1) + ".png");
            appear[i].scale(380, 380);
        }

        // Sprites de ataque
        action = new GreenfootImage[10];
        for (int i = 0; i < action.length; i++) {
            action[i] = new GreenfootImage("attackBoss" + (i+1) + ".png");
            action[i].scale(380, 380);
        }

        // Inicializa com a primeira sprite
        setImage(appear[0]);
    }

    /**
     * Comportamento específico do Boss.
     * 
     * <p>Persegue o jogador alvo. Se colidir, remove todas as vidas do jogador
     * e força Game Over.</p>
     */
    @Override
    protected void comportamento() {
        if (getWorld().getObjects(Jogador.class).isEmpty()) {
            Greenfoot.setWorld(new GameOverWorld());
            return;
        }

        // Move em direção ao jogador
        moverParaPlayer();

        Jogador jogador = (Jogador) getOneIntersectingObject(Jogador.class);
        if (jogador != null) {
            jogador.morrerInstantaneamente();
        }
    }

    /**
     * Comportamento de morte do Boss.
     * 
     * <p>Toca som de morte, adiciona pontos extra ao ScoreGlobal,
     * guarda a pontuação final e muda para o ecrã de vitória.</p>
     */
    @Override
    protected void morrer() {
        if (somMorte != null) Greenfoot.playSound(somMorte);

        // Pontos extras
        ScoreGlobal.adicionar(2000);

        int pontuacaoFinal = ScoreGlobal.get();

        if (getWorld() != null) getWorld().removeObject(this);

        Greenfoot.setWorld(new WinWorld(pontuacaoFinal));
    }
}
