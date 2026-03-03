import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerir a interface gráfica (UI) dos jogadores Sherlock e Watson.
 * 
 * <p>Esta classe adiciona ao mundo os elementos visuais de interface, incluindo:</p>
 * <ul>
 *   <li>Avatares de Holmes e Watson;</li>
 *   <li>Corações que representam a vida de cada jogador;</li>
 *   <li>Atualização e remoção da UI quando necessário.</li>
 * </ul>
 * 
 * <p>Permite associar a UI aos objetos {@link Sherlock} e {@link Watson} existentes no mundo.</p>
 * 
 * @author Grupo 8
 */
public class PlayerUI {

    /** Instância do jogador Sherlock. */
    private Sherlock sherlock;

    /** Instância do jogador Watson. */
    private Watson watson;

    /** Mundo onde os elementos de UI serão adicionados. */
    private World world;

    /** Lista de corações que representam a vida do Sherlock. */
    private List<VidaUI> coracoesSherlock;

    /** Lista de corações que representam a vida do Watson. */
    private List<VidaUI> coracoesWatson;

    /** Avatar de Holmes na UI. */
    private HolmesUI holmesUI;

    /** Avatar de Watson na UI. */
    private WatsonUI watsonUI;

    /**
     * Construtor da UI dos jogadores.
     * 
     * @param world Mundo onde a UI será adicionada
     * @param sherlock Instância do jogador Sherlock
     * @param watson Instância do jogador Watson
     */
    public PlayerUI(World world, Sherlock sherlock, Watson watson) {
        this.world = world;
        this.sherlock = sherlock;
        this.watson = watson;

        initUI();
    }

    /** Inicializa os elementos de interface e adiciona-os ao mundo. */
    private void initUI() {
        // Adiciona avatares
        holmesUI = new HolmesUI();
        watsonUI = new WatsonUI();
        world.addObject(holmesUI, 70, 50);
        world.addObject(watsonUI, 960, 40);

        // Adiciona corações do Sherlock
        coracoesSherlock = new ArrayList<>();
        int[] posSherlock = {150, 210, 270};
        for (int x : posSherlock) {
            VidaUI v = new VidaUI();
            world.addObject(v, x, 30);
            coracoesSherlock.add(v);
        }
        sherlock.setCoracoes(coracoesSherlock);

        // Adiciona corações do Watson
        coracoesWatson = new ArrayList<>();
        int[] posWatson = {790, 850, 910};
        for (int i = posWatson.length - 1; i >= 0; i--) {
            VidaUI v = new VidaUI();
            world.addObject(v, posWatson[i], 30);
            coracoesWatson.add(v);
        }
        watson.setCoracoes(coracoesWatson);
    }

    /**
     * Remove todos os elementos de UI do mundo.
     * 
     * <p>Inclui avatares e corações de ambos os jogadores.</p>
     */
    public void removerUI() {
        for (VidaUI v : coracoesSherlock)
        {
        world.removeObject(v);
        }
        for (VidaUI v : coracoesWatson)
        { 
            world.removeObject(v);
        }
        world.removeObject(holmesUI);
        world.removeObject(watsonUI);
    }
}
