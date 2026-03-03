import greenfoot.*;  

/**
 * Representa o quinto e último nível do jogo, onde os jogadores enfrentam o Boss final.
 *
 * <p>É responsável por configurar o cenário, criar os personagens principais e o inimigo final,
 * bem como gerir a interface e a música específica deste nível.</p>
 *
 * <p>Funcionalidades principais:</p>
 * <ul>
 *   <li>Criação das personagens {@link Sherlock} e {@link Watson} com posições iniciais definidas;</li>
 *   <li>Criação e posicionamento do {@link Boss} como inimigo principal;</li>
 *   <li>Adição da interface do jogador ({@link PlayerUI}) e do placar de pontuação ({@link Score});</li>
 *   <li>Execução da música de fundo específica do nível final;</li>
 *   <li>Implementação do sistema de pausa;</li>
 *   <li>Desactivação dos movimentos verticais e da capacidade de salto;</li>
 *   <li>Disponibilização de um método para obter a pontuação actual.</li>
 * </ul>
 *
 * @author Grupo 8
 */
public class FLevel5 extends BaseWorld {

    /** Instância da personagem Sherlock. */
    private Sherlock sherlock;

    /** Instância da personagem Watson. */
    private Watson watson;

    /** Instância do inimigo principal (Boss). */
    private Boss boss;

    /** Gestor de geração de inimigos (não utilizado neste nível, mas mantido por consistência). */
    private Spawner spawner;

    /** Interface do jogador. */
    private PlayerUI playerUI;

    /** Placar de pontuação. */
    private Score score;

    /** Indica se o ecrã de vitória já foi mostrado. */
    private boolean vitoriaMostrada = false;

    /**
     * Construtor da classe {@code FLevel5}.
     *
     * <p>Inicializa o cenário, posiciona as personagens principais e o Boss,
     * adiciona os elementos de interface e inicia a música de fundo do nível.</p>
     */
    public FLevel5() {
        super(1024, 683);
        setBackground("final.png");

        // Criação das personagens e do Boss
        sherlock = new Sherlock(620); 
        watson = new Watson(620);
        boss = new Boss();

        // Adição ao mundo
        addObject(sherlock, 120, 580);
        addObject(watson, 220, 580);
        addObject(boss, 830, 490);

        // Configuração de movimento
        sherlock.setVerticalMovement(false);
        watson.setVerticalMovement(false);
        sherlock.setCanJump(false);
        watson.setCanJump(false);

        // Interface e pontuação
        playerUI = new PlayerUI(this, sherlock, watson);
        score = new Score();
        addObject(score, 60, 120); 

        // Música de fundo
        SoundManager.tocarMusica("bossfinal.mp3", 20);
    }

    /**
     * Método principal do ciclo de jogo, executado continuamente.
     *
     * <p>Verifica se o jogo está em pausa e permite inserir futura lógica
     * de controlo ou interacção durante o combate com o Boss.</p>
     */
    @Override
    public void act() {
        checkPause(); 
    }

    /**
     * Devolve a instância atual de {@link Score}.
     *
     * @return o objecto {@link Score} que representa a pontuação do jogador.
     */
    public Score getScore() {
        return score;
    }
}
