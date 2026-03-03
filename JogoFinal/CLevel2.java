import greenfoot.*;  
import java.util.ArrayList;
import java.util.List;

/**
 * Representa o segundo nível do jogo.
 * 
 * <p>É responsável por configurar e gerir todos os elementos do nível, incluindo:
 * <ul>
 *   <li>Criação e posicionamento das personagens {@link Sherlock} e {@link Watson};</li>
 *   <li>Ativação da funcionalidade de salto para ambos os jogadores;</li>
 *   <li>Inicialização da interface do jogador ({@link PlayerUI}) e do placar ({@link Score});</li>
 *   <li>Adição de plataformas, obstáculos e elementos decorativos (como tábuas, barris e nuvens);</li>
 *   <li>Criação de inimigos específicos ({@link Fantasma}) em posições definidas;</li>
 *   <li>Inserção da pista interactiva ({@link Pista1}) que permite avançar para o próximo nível;</li>
 *   <li>Controlo da música de fundo e possibilidade de a parar quando necessário;</li>
 *   <li>Actualização do placar e verificação do estado de pausa a cada ciclo de jogo.</li>
 * </ul>
 * </p>
 * 
 * @author Grupo 8
 */
public class CLevel2 extends BaseWorld {

    /** Instância da personagem Sherlock. */
    private Sherlock sherlock;

    /** Instância da personagem Watson. */
    private Watson watson;

    /** Interface do utilizador dos jogadores. */
    private PlayerUI playerUI;

    /** Placar de pontuação do nível. */
    private Score score;
    
    /** Música de fundo do nível. */
    private GreenfootSound musica = new GreenfootSound("nivel2.mp3");


    /**
     * Construtor da classe {@code CLevel2}.
     * 
     * <p>Configura o cenário do nível, cria as personagens e adiciona todos os elementos visuais
     * e interactivos, incluindo plataformas, inimigos e a pista para o próximo nível.
     * Também inicia a reprodução da música de fundo.</p>
     */
    public CLevel2() {
        super(1024, 683);
        setBackground("level2.png");

        // Criação das personagens
        sherlock = new Sherlock(620); 
        watson = new Watson(620);
        addObject(sherlock, 150, 620);
        addObject(watson, 250, 620);
        sherlock.setVerticalMovement(false);
        watson.setVerticalMovement(false);
        sherlock.setCanJump(true);
        watson.setCanJump(true);

        // Interface do jogador e placar
        playerUI = new PlayerUI(this, sherlock, watson);
        score = new Score();
        addObject(score, 512, 50); 

        // -----------------------------
        // Plataformas e obstáculos
        // -----------------------------
        int[] xTabuas = {700, 550, 400};
        for (int x : xTabuas) addObject(new tabua(), x, 400);
        addObject(new tabua(), 760, 500);
        addObject(new Barril(), 820, 620);
        addObject(new nuvem(), 260, 360);
        addObject(new nuvem(), 120, 340);

        // Pista para avançar de nível
        addObject(new Pista1(), 120, 300); 

        // Criação de inimigos (fantasmas)
        int[][] posicoesFantasmas = {
            {800, 500},
            {850, 500},
            {900, 500},
            {950, 600},
            {1000, 600},
            {870, 600}
        };
        
        for (int[] pos : posicoesFantasmas) {
            addObject(new Fantasma(), pos[0], pos[1]);
        }

        // Inicia a música de fundo
        SoundManager.tocarMusica("nivel2.mp3", 30);
    }
    
    /**
     * Pára a música de fundo do nível.
     */
    public void pararMusica() {
        musica.stop();
    }

    /**
     * Método executado continuamente durante o ciclo de jogo.
     * 
     * <p>É responsável por:
     * <ul>
     *   <li>Verificar se o jogo está em pausa;</li>
     *   <li>Actualizar o placar de pontuação.</li>
     * </ul>
     * </p>
     */
    @Override
    public void act() {
        checkPause(); 
        score.atualizar();
    }
}
