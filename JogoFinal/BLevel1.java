import greenfoot.*;

/**
 * Representa o primeiro nível do jogo.
 * 
 * <p>É responsável por inicializar o ambiente do nível, incluindo:
 * <ul>
 *   <li>Criação e posicionamento das personagens {@link Sherlock} e {@link Watson};</li>
 *   <li>Inicialização da interface do jogador ({@link PlayerUI});</li>
 *   <li>Controlo da música de fundo do nível;</li>
 *   <li>Verificação do estado de pausa;</li>
 *   <li>Exibição de uma carta interactiva quando o Sherlock ultrapassa uma posição específica.</li>
 * </ul>
 * </p>
 * 
 * @author Grupo 8
 */
public class BLevel1 extends BaseWorld {

    /** Instância da personagem Sherlock. */
    private Sherlock sherlock;   

    /** Instância da personagem Watson. */
    private Watson watson;

    /** Interface do utilizador dos jogadores. */
    private PlayerUI playerUI; 

    /** Indica se a carta interactiva já apareceu. */
    private boolean cartaApareceu = false;

    /** Música de fundo do nível. */
    private GreenfootSound musica = new GreenfootSound("nivel1.mp3");


    /**
     * Construtor da classe {@code BLevel1}.
     * 
     * <p>Configura o cenário, adiciona as personagens e inicializa a interface do jogador.
     * Também inicia a reprodução da música de fundo do nível.</p>
     */
    public BLevel1() {
        super(1024, 683);
        setBackground("level1.jpg");

        // Criação das personagens
        sherlock = new Sherlock(500);
        watson = new Watson(500);
        addObject(sherlock, 200, 500);
        addObject(watson, 200, 600);
        sherlock.setVerticalMovement(false);
        watson.setVerticalMovement(false);

        playerUI = new PlayerUI(this, sherlock, watson);

        // Inicia a música de fundo com volume 30
        SoundManager.tocarMusica("nivel1.mp3", 30);
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
     *   <li>Fazer aparecer a carta quando o {@code Sherlock} ultrapassa a posição x = 400;</li>
     *   <li>Tocar o som da porta quando a carta aparece.</li>
     * </ul>
     * </p>
     */
    @Override
    public void act() {
        checkPause(); 

        // A carta aparece quando o Sherlock ultrapassa a posição x > 400
        if (!cartaApareceu && sherlock.getX() > 400) {
            addObject(new carta(), 950, 500);
            cartaApareceu = true;
            Greenfoot.playSound("porta.mp3");
        }
    }
}
