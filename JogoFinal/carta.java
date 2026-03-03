import greenfoot.*;

/**
 * Representa uma carta no jogo.
 * 
 * <p>Herda de {@link Colisoes}, permitindo ser utilizada nos sistemas
 * de deteção de colisão com jogadores ou outros objetos interativos.</p>
 * 
 * <p>Funcionalidades principais:</p>
 * <ul>
 *   <li>Redimensiona a imagem da carta ao ser criada;</li>
 *   <li>Não possui ações próprias no ciclo do jogo ({@link #act()});</li>
 *   <li>Pode ser usada como objeto interativo para mostrar mensagens ou eventos.</li>
 * </ul>
 * 
 * <p>Esta classe é utilizada principalmente no primeiro nível ({@link BLevel1}).</p>
 * 
 * @author Grupo 8
 */
public class carta extends Colisoes {

    /**
     * Construtor da classe {@code carta}.
     * Redimensiona a imagem da carta.
     */
    public carta() {
        GreenfootImage imagem = getImage();
        int width = imagem.getWidth();
        int height = imagem.getHeight();
        imagem.scale(width / 6, height / 6);
    }

    /**
     * Método chamado continuamente pelo ciclo do jogo.
     * 
     * <p>Não possui implementação pois a carta não executa ações autónomas.</p>
     */
    @Override
    public void act() {
        // A carta não executa ações próprias
    }
}
