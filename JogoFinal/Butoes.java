import greenfoot.*;

/**
 * Classe abstrata que representa botões clicáveis no jogo.
 *
 * <p>Os botões reproduzem um som ao serem clicados e permitem definir
 * uma acção personalizada ao clique através do método {@link #onClick()}.</p>
 *
 * <p>Funcionalidades principais:</p>
 * <ul>
 *   <li>Define a imagem do botão ao ser criado;</li>
 *   <li>Reproduz um som de clique ao ser pressionado;</li>
 *   <li>Permite implementar acções específicas no clique através do método abstracto {@link #onClick()}.</li>
 * </ul>
 * 
 * @author Grupo 8
 */
public abstract class Butoes extends Actor {

    /** Som reproduzido ao clicar no botão. */
    protected GreenfootSound clickSound;

    /**
     * Construtor da classe {@code Butoes}.
     *
     * @param imagePath caminho da imagem a utilizar para o botão.
     * @param clickSound som a reproduzir quando o botão é clicado.
     */
    public Butoes(String imagePath, GreenfootSound clickSound) {
        setImage(imagePath);
        this.clickSound = clickSound;
    }

    /**
     * Método executado continuamente pelo ciclo de jogo.
     *
     * <p>Verifica se o botão foi clicado pelo rato. Se sim, reproduz o som
     * associado e chama o método {@link #onClick()}.</p>
     */
    @Override
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            if (clickSound != null) clickSound.play();
            onClick();
        }
    }

    /**
     * Método abstracto que define a acção a executar quando o botão é clicado.
     * 
     * <p>As subclasses devem implementar este método para especificar o comportamento desejado.</p>
     */
    protected abstract void onClick();
}
