import greenfoot.*;

/**
 * Representa o botão de "Sair" no menu principal do jogo.
 *
 * <p>Este botão, quando clicado, encerra completamente o jogo.</p>
 *
 * <p>Funcionalidades principais:</p>
 * <ul>
 *   <li>Define a imagem do botão como "quit.png";</li>
 *   <li>Reproduz o som de clique ao ser pressionado (herdado de {@link Butoes});</li>
 *   <li>Encerra o jogo ao clicar, utilizando {@link System#exit(int)}.</li>
 * </ul>
 * 
 * @author Grupo 8
 */
public class quit extends Butoes {

    /**
     * Construtor da classe {@code quit}.
     *
     * <p>Configura a imagem do botão e associa o som de clique.</p>
     */
    public quit() {
        super("quit.png", botao.click);
    }

    /**
     * Acção a executar quando o botão é clicado.
     *
     * <p>Encerra a aplicação do jogo.</p>
     */
    @Override
    protected void onClick() {
        System.exit(0);
    }
}
