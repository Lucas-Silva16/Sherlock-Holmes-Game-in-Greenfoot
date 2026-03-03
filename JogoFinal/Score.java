import greenfoot.*;

/**
 * Representa o indicador de pontuação do jogo.
 * <p>
 * Exibe a pontuação atual do jogador na tela, atualizando sempre que necessário.
 * A pontuação global é mantida pela classe {@link ScoreGlobal}.
 * </p>
 */
public class Score extends Actor {

    /**
     * Construtor da classe Score.
     * <p>
     * Inicializa o indicador de pontuação na tela chamando {@link #atualizar()}.
     * </p>
     */
    public Score() {
        atualizar();
    }

    /**
     * Atualiza a imagem do indicador de pontuação no mundo.
     * <p>
     * Obtém o valor atual da pontuação global {@link ScoreGlobal#get()} e cria
     * uma nova imagem com o texto "Score: X", onde X é o valor da pontuação.
     * </p>
     */
    public void atualizar() {
        setImage(new GreenfootImage("Score: " + ScoreGlobal.get(), 30, Color.WHITE, new Color(0,0,0,0)));
    }
}
