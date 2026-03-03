import greenfoot.*;

/**
 * Representa o quarto nível do jogo, que introduz um labirinto e uma chave interactiva.
 *
 * <p>É responsável por configurar e gerir os elementos principais do nível, incluindo:
 * <ul>
 *   <li>Criação e posicionamento das personagens {@link Sherlock} e {@link Watson} com limites verticais definidos;</li>
 *   <li>Activação do movimento vertical e desactivação do salto;</li>
 *   <li>Criação das paredes horizontais e verticais que formam o labirinto;</li>
 *   <li>Execução da música de fundo específica do nível;</li>
 *   <li>Aparecimento da {@link Chave} quando o Sherlock ultrapassa uma determinada posição no eixo X;</li>
 *   <li>Implementação do sistema de pausa;</li>
 *   <li>Possibilidade de parar a música do nível quando necessário.</li>
 * </ul>
 * </p>
 * 
 * @author Grupo 8
 */
public class ELevel4 extends BaseWorld {

    /** Instância da personagem Sherlock. */
    private Sherlock sherlock;

    /** Instância da personagem Watson. */
    private Watson watson;

    /** Gerador de inimigos (não utilizado neste nível, mas disponível para consistência). */
    private Spawner spawner;

    /** Indica se a chave já apareceu. */
    private boolean chaveApareceu = false;

    /** Música de fundo do nível. */
    private GreenfootSound musica = new GreenfootSound("squidi.mp3");

    /** Placar de pontuação. */
    private Score score;

    /**
     * Construtor da classe {@code ELevel4}.
     * 
     * <p>Configura o cenário, inicializa as personagens, define o labirinto através de paredes
     * horizontais e verticais e inicia a música de fundo.</p>
     */
    public ELevel4() {
        super(1024, 683);
        setBackground("level4.png");

        // Criação das personagens
        sherlock = new Sherlock(620); 
        watson = new Watson(620);
        addObject(sherlock, 120, 620);
        addObject(watson, 220, 620);
        sherlock.setStartPosition(120, 620);
        watson.setStartPosition(220, 620);
        sherlock.setVerticalMovement(true);
        watson.setVerticalMovement(true);
        sherlock.setCanJump(false);
        watson.setCanJump(false);

        // Criação das paredes do labirinto
        spawnParedes();

        // Placar e música
        score = new Score();
        addObject(score, 512, 50); 
        SoundManager.tocarMusica("squidi.mp3", 80);
    }

    /**
     * Método executado continuamente durante o ciclo de jogo.
     * 
     * <p>É responsável por:
     * <ul>
     *   <li>Verificar se o jogo está em pausa;</li>
     *   <li>Fazer aparecer a {@link Chave} quando o Sherlock ultrapassa a posição x = 400;</li>
     *   <li>Tocar o som de notificação quando a chave aparece.</li>
     * </ul>
     * </p>
     */
    @Override
    public void act() {
        checkPause();

        if (!chaveApareceu && sherlock.getX() > 400) {
            addObject(new Chave(), 630, 260);
            chaveApareceu = true;
            Greenfoot.playSound("ding.mp3");
        }
    }

    /**
     * Cria todas as paredes que compõem o labirinto do nível.
     * 
     * <p>As paredes são geradas através de chamadas aos métodos
     * {@link #addHorizontalWall(int, int)} e {@link #addVerticalWall(int, int)},
     * com coordenadas pré-definidas que formam o percurso do labirinto.</p>
     */
    private void spawnParedes() {
        addHorizontalWall(530, 658);
        addVerticalWall(25, 341);
        addHorizontalWall(312, 20);
        addVerticalWall(999, 580);
        addVerticalWall(999, 380);
        addVerticalWall(999, 180);
        addVerticalWall(999, 20);
        addVerticalWall(25, 50);
        addVerticalWall(25, 250);
        addVerticalWall(25, 500);
        addVerticalWall(25, 750);
        addHorizontalWall(950, 20);
        addHorizontalWall(850, 20);
        addHorizontalWall(650, 20);
        addHorizontalWall(450, 20);
        addHorizontalWall(250, 20);
        addHorizontalWall(50, 20);
        addHorizontalWall(290, 195);
        addVerticalWall(370, 270);
        addVerticalWall(820, 410);
        addVerticalWall(820, 260);
        addVerticalWall(216, 275);
        addVerticalWall(520, 200);
        addHorizontalWall(590, 300);
        addVerticalWall(370, 425);
        addVerticalWall(660, 425);
        addHorizontalWall(90, 500);
        addHorizontalWall(450, 500);
        addHorizontalWall(744, 500);
        addVerticalWall(532, 575);
    }

    /**
     * Adiciona uma parede horizontal ao mundo na posição especificada.
     * 
     * @param x posição horizontal da parede.
     * @param y posição vertical da parede.
     */
    private void addHorizontalWall(int x, int y) {
        Wall wall = new Wall();
        wall.setImage(new GreenfootImage("Wall.png")); 
        addObject(wall, x, y);
    }

    /**
     * Adiciona uma parede vertical ao mundo na posição especificada.
     * 
     * @param x posição horizontal da parede.
     * @param y posição vertical da parede.
     */
    private void addVerticalWall(int x, int y) {
        Wall2 wall = new Wall2();
        wall.setImage(new GreenfootImage("Wall2.png")); 
        addObject(wall, x, y);
    }

    /**
     * Pára a música de fundo do nível.
     */
    public void pararMusica() {
        musica.stop();
    }
}
