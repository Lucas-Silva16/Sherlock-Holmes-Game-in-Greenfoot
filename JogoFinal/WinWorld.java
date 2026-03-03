import greenfoot.*;

/**
 * Representa o ecrã de vitória do jogo, apresentado quando o jogador conclui todos os níveis.
 *
 * <p>Este mundo exibe uma mensagem de parabéns, mostra a pontuação final do jogador
 * e permite retornar ao menu principal.</p>
 *
 * <p>Funcionalidades principais:</p>
 * <ul>
 *   <li>Mostra uma mensagem de vitória e a pontuação final do jogador;</li>
 *   <li>Define uma imagem de fundo personalizada para o ecrã de vitória;</li>
 *   <li>Exibe instruções para retornar ao menu principal através da tecla <b>ESPAÇO</b>;</li>
 *   <li>Toca a música de vitória ao entrar no mundo;</li>
 *   <li>Ao pressionar <b>ESPAÇO</b>, para a música e carrega o menu principal {@link Amenu_jogo}.</li>
 * </ul>
 * 
 * @author Grupo 8
 */
public class WinWorld extends BaseWorld {

    /** Pontuação final do jogador neste jogo. */
    private int finalScore;

    /** Música de fundo do ecrã de vitória. */
    GreenfootSound musica = new GreenfootSound("victory.mp3");

    /**
     * Construtor da classe {@code WinWorld}.
     * 
     * <p>Configura a imagem de fundo, exibe a mensagem de vitória, mostra a pontuação
     * e inicia a música de vitória.</p>
     * 
     * @param score a pontuação final do jogador a apresentar no ecrã.
     */
    public WinWorld(int score) {
        super(1024, 683);
        this.finalScore = score;

        // Reinicia o score global
        ScoreGlobal.reset();

        // Define uma imagem de fundo personalizada
        // Substitui "win_bg.png" pela imagem de vitória que quiseres usar
        setBackground("GanhouOjogo.jpg");

            GreenfootImage voltar = new GreenfootImage(
            "Press SPACE to return to Menu",
            35, Color.WHITE, new Color(0, 0, 0, 0)
        );
        
        // Define manualmente a posição da instrução
        int voltarX = 310;   
        int voltarY = 500;   
        getBackground().drawImage(voltar, voltarX, voltarY);
        
        // Mensagem da pontuação final (aparece por baixo da instrução)
        GreenfootImage text = new GreenfootImage(
            "YOUR FINAL SCORE IS: " + finalScore,
            45, Color.YELLOW, new Color(0, 0, 0, 0)
        );
        
        // Define manualmente a posição da pontuação
        int scoreX = 280;       
        int scoreY = voltarY + 60;  
        getBackground().drawImage(text, scoreX, scoreY);


        // Música de vitória
        SoundManager.tocarMusica("victory.mp3", 40);
    }

    /**
     * Método executado continuamente durante o ciclo de jogo.
     * 
     * <p>Verifica se o jogador pressionou a tecla <b>ESPAÇO</b> e, caso afirmativo,
     * para a música de vitória e carrega o menu principal {@link Amenu_jogo}.</p>
     */
    @Override
    public void act() {
        if (Greenfoot.isKeyDown("space")) {
            SoundManager.pararMusica();
            Greenfoot.setWorld(new Amenu_jogo());
        }
    }
}
