import greenfoot.*;

/**
 * Representa a cena introdutória do jogo.
 *
 * <p>Este mundo apresenta a introdução gráfica, explica os controlos e adiciona
 * as personagens principais com movimentos e disparos desactivados para demonstração.</p>
 *
 * <p>Funcionalidades principais:</p>
 * <ul>
 *   <li>Exibe a imagem de introdução e a instrução "Press space to continue";</li>
 *   <li>Adiciona as personagens {@link Sherlock} e {@link Watson} com movimento e disparo desactivados;</li>
 *   <li>Mostra representações gráficas dos controlos (AWSD para Watson e setas para Sherlock);</li>
 *   <li>Mantém o sistema de pausa activo;</li>
 *   <li>Ao pressionar <b>ESPAÇO</b>, inicia o primeiro nível ({@link BLevel1}) e toca o som de clique.</li>
 * </ul>
 * 
 * @author Grupo 8
 */
public class intro extends BaseWorld {

    /**
     * Construtor da classe {@code intro}.
     *
     * <p>Configura o fundo, adiciona as personagens e os elementos gráficos de instrução.</p>
     */
    public intro() {    
        super(1024, 683);

        // Fundo de introdução
        setBackground("intro.png");
        showText("Press space to continue", 500, 660);

        // Criação das personagens
        Sherlock sherlock = new Sherlock(350);
        Watson watson = new Watson(350);

        // Desactivar movimento e disparo
        sherlock.setCanMove(false);
        sherlock.setCanShoot(false);
        watson.setCanMove(false);
        watson.setCanShoot(false);

        // Adição ao mundo
        addObject(watson, 265, 350);
        addObject(sherlock, 700, 350);

        // Representações gráficas dos controlos
        awsd Awsd = new awsd();
        setas Setas = new setas();
        addObject(Setas, 250, 490);
        addObject(Awsd, 700, 490);

        // Outros elementos gráficos
        addObject(new kl(), 250, 600);
        addObject(new ef(), 700, 600);
    }

    /**
     * Método executado continuamente durante o ciclo de jogo.
     *
     * <p>Verifica se o jogador pressionou a tecla <b>ESPAÇO</b> e, caso afirmativo,
     * inicia o primeiro nível {@link BLevel1} e reproduz o som de clique.</p>
     */
    @Override
    public void act() {
        if (Greenfoot.isKeyDown("space")) {
            Greenfoot.setWorld(new BLevel1());
            botao.click.play();
        }
    }
}
