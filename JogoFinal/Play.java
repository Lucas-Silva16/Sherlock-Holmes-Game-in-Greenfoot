import greenfoot.*;

/**
 * Representa o botão de "Play" no menu principal do jogo.
 *
 * <p>Este botão, quando clicado, interrompe a música do menu
 * e inicia a cena introdutória {@link intro} do jogo.</p>
 *
 * <p>Funcionalidades principais:</p>
 * <ul>
 *   <li>Define a imagem do botão como "play.png";</li>
 *   <li>Reproduz o som de clique ao ser pressionado (herdado de {@link Butoes});</li>
 *   <li>Para a música do menu ao clicar;</li>
 *   <li>Carrega a cena introdutória {@link intro}.</li>
 * </ul>
 * 
 * @author Grupo 8
 */
public class Play extends Butoes {

    /**
     * Construtor da classe {@code Play}.
     *
     * <p>Configura a imagem do botão e associa o som de clique.</p>
     */
    public Play() {
        super("play.png", botao.click);
    }

    /**
     * Acção a executar quando o botão é clicado.
     *
     * <p>Para a música do menu e inicia o mundo {@link intro}.</p>
     */
    @Override
    protected void onClick() {
        ((Amenu_jogo) getWorld()).musica.stop();
        Greenfoot.setWorld(new intro());
    }
}
