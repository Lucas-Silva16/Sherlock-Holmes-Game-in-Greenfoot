import greenfoot.*;

/**
 * Representa o ecrã de fim de jogo ("Game Over") apresentado quando o jogador perde.
 *
 * <p>Este mundo é responsável por gerir o estado de fim de jogo, exibindo a mensagem
 * de derrota e permitindo que o jogador reinicie o jogo.</p>
 *
 * <p>Funcionalidades principais:</p>
 * <ul>
 *   <li>Reinicia a pontuação global ao entrar no mundo;</li>
 *   <li>Define uma imagem de fundo personalizada;</li>
 *   <li>Exibe a mensagem “GAME OVER” e instruções para reiniciar;</li>
 *   <li>Toca a música de fundo específica do ecrã de Game Over;</li>
 *   <li>Permite reiniciar o jogo através da tecla <b>ESPAÇO</b>, carregando o nível {@link CLevel2}.</li>
 * </ul>
 *
 * @author Grupo 8
 */
public class GameOverWorld extends BaseWorld {

    /**
     * Construtor da classe {@code GameOverWorld}.
     *
     * <p>Configura a imagem de fundo, a mensagem de “Game Over”, as instruções
     * de reinício e a música ambiente do ecrã.</p>
     */
    public GameOverWorld() {
        super(1024, 683);

        // Reinicia o score global
        ScoreGlobal.reset();

        // Define a imagem de fundo
        setBackground("gameover.jpg");

        // Instrução para reiniciar o jogo
        GreenfootImage instr = new GreenfootImage("Press SPACE to restart", 40, Color.WHITE, new Color(0, 0, 0, 0));
        getBackground().drawImage(instr, getWidth() / 2 - instr.getWidth() / 2, getHeight() / 2 + 80);

        // Música de fundo do ecrã de Game Over
        SoundManager.tocarMusica("gameover.mp3", 70);
    }

    /**
     * Método executado continuamente durante o ciclo de jogo.
     *
     * <p>Verifica se o jogador pressionou a tecla <b>ESPAÇO</b> e, caso afirmativo,
     * reinicia o jogo, carregando o nível {@link CLevel2}.</p>
     */
    @Override
    public void act() {
        if (Greenfoot.isKeyDown("space")) {
            Greenfoot.setWorld(new CLevel2());
        }
    }
}
