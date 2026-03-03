import greenfoot.*;

/**
 * Representa o botão de "Configurações" no menu principal do jogo.
 *
 * <p>Este botão, quando clicado, interrompe a música do menu
 * e carrega o mundo de configurações {@link Jebait}.</p>
 *
 * <p>Funcionalidades principais:</p>
 * <ul>
 *   <li>Define a imagem do botão como "settings.png";</li>
 *   <li>Reproduz o som de clique ao ser pressionado (herdado de {@link Butoes});</li>
 *   <li>Para a música do menu ao clicar;</li>
 *   <li>Carrega o mundo de configurações {@link Jebait}.</li>
 * </ul>
 * 
 * @author Grupo 8
 */
public class Settings extends Butoes {

    /**
     * Construtor da classe {@code Settings}.
     *
     * <p>Configura a imagem do botão e associa o som de clique.</p>
     */
    public Settings() {
        super("settings.png", botao.click);
    }

    /**
     * Acção a executar quando o botão é clicado.
     *
     * <p>Para a música do menu e inicia o mundo {@link Jebait}.</p>
     */
    @Override
    protected void onClick() {
        ((Amenu_jogo) getWorld()).musica.stop();
        Greenfoot.setWorld(new Jebait());
    }
}
