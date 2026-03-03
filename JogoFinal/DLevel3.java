import greenfoot.*;
import java.util.*;

/**
 * Representa o terceiro nível do jogo.
 * 
 * <p>É responsável por configurar e gerir todos os elementos e mecânicas do nível, incluindo:
 * <ul>
 *   <li>Criação e posicionamento das personagens {@link Sherlock} e {@link Watson}, com controlo de movimento vertical;</li>
 *   <li>Definição de limites verticais superior e inferior para as personagens;</li>
 *   <li>Inicialização da interface do jogador ({@link PlayerUI}) e do placar ({@link Score});</li>
 *   <li>Gestão do gerador de inimigos ({@link Spawner}) que cria ondas de inimigos de forma controlada;</li>
 *   <li>Execução da música de fundo específica do nível;</li>
 *   <li>Criação da carta final ({@link PistaFinal}) quando todos os inimigos forem eliminados;</li>
 *   <li>Verificação do estado de pausa e actualização contínua dos elementos do jogo.</li>
 * </ul>
 * </p>
 * 
 * @author Grupo 8
 */
public class DLevel3 extends BaseWorld {

    /** Instância da personagem Sherlock. */
    private Sherlock sherlock;

    /** Instância da personagem Watson. */
    private Watson watson;

    /** Gerador de inimigos (spawner) do nível. */
    private Spawner spawner;

    /** Interface do utilizador dos jogadores. */
    private PlayerUI playerUI;

    /** Placar de pontuação. */
    private Score score;

    /** Indica se a carta final já foi criada. */
    private boolean cartaSpawnada = false; 

    /** Música de fundo do nível. */
    private GreenfootSound musica = new GreenfootSound("nivelwaves.mp3");


    /**
     * Construtor da classe {@code DLevel3}.
     * 
     * <p>Inicializa o cenário do nível, configura as personagens, a interface do jogador e o placar.
     * Também activa o controlo vertical de movimento e inicia o gerador de inimigos e a música de fundo.</p>
     */
    public DLevel3() {
        super(1024, 683);
        setBackground("level3.png");

        // Criação das personagens
        sherlock = new Sherlock(620); 
        watson = new Watson(620);
        addObject(sherlock, 150, 620);
        addObject(watson, 250, 620);

        // Activar movimento vertical e definir restrições
        sherlock.setVerticalMovement(true);
        watson.setVerticalMovement(true);
        sherlock.setCanJump(false);
        watson.setCanJump(false);

        // Placar e interface do jogador
        score = new Score();
        addObject(score, 512, 50); 
        playerUI = new PlayerUI(this, sherlock, watson);

        // Gerador de inimigos e música de fundo
        spawner = new Spawner(this);
        SoundManager.tocarMusica("nivelwaves.mp3", 30);
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
     *   <li>Limitar o movimento vertical das personagens;</li>
     *   <li>Actualizar o gerador de inimigos e o placar;</li>
     *   <li>Fazer aparecer a carta final quando todos os inimigos forem derrotados.</li>
     * </ul>
     * </p>
     */
    @Override
    public void act() {
        checkPause();
        limitarMovimentoVertical();
        spawner.atualizar();
        score.atualizar();

        if (!cartaSpawnada && spawner.terminouTudo()) {
            spawnCarta();
            cartaSpawnada = true;
        }
    }

    /**
     * Limita o movimento vertical das personagens Sherlock e Watson, 
     * impedindo que ultrapassem os limites superior e inferior do ecrã.
     */
    private void limitarMovimentoVertical() {
        int limiteSuperior = 300;
        int limiteInferior = 650;

        if (sherlock != null && sherlock.getWorld() != null) {
            int yS = sherlock.getY();
            if (yS < limiteSuperior) sherlock.setLocation(sherlock.getX(), limiteSuperior);
            else if (yS > limiteInferior) sherlock.setLocation(sherlock.getX(), limiteInferior);
        }

        if (watson != null && watson.getWorld() != null) {
            int yW = watson.getY();
            if (yW < limiteSuperior) watson.setLocation(watson.getX(), limiteSuperior);
            else if (yW > limiteInferior) watson.setLocation(watson.getX(), limiteInferior);
        }
    }

    /**
     * Cria a carta final ({@link PistaFinal}) no centro do ecrã
     * quando todas as ondas de inimigos forem concluídas.
     */
    private void spawnCarta() {
        PistaFinal pista = new PistaFinal(); 
        addObject(pista, getWidth() / 2, getHeight() / 2);
    }
}
