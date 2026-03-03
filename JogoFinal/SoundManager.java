import greenfoot.*;

/**
 * Classe responsável por gerir a reprodução de músicas e sons de fundo.
 * 
 * <p>Permite tocar, parar e alternar músicas sem que estas se sobreponham.</p>
 * 
 * <p>Funcionalidades principais:</p>
 * <ul>
 *   <li>Reproduzir uma música em loop;</li>
 *   <li>Evitar repetir a mesma música se já estiver a tocar;</li>
 *   <li>Parar a música atual.</li>
 * </ul>
 * 
 * <p>Todos os métodos e variáveis são estáticos para fácil acesso global.</p>
 * 
 * @author Grupo 8
 */
public class SoundManager {
    
    /** Música que está atualmente a ser reproduzida. */
    private static GreenfootSound musicaAtual = null;
    
    /** Nome do ficheiro da música atual. */
    private static String nomeMusicaAtual = "";
    
    /**
     * Toca a música indicada em loop.
     * 
     * <p>Se a música já estiver a tocar, não faz nada.</p>
     * 
     * @param nomeFicheiro Nome do ficheiro de som
     * @param volume Volume da música (0–100)
     */
    public static void tocarMusica(String nomeFicheiro, int volume) {
        if (!nomeFicheiro.equals(nomeMusicaAtual)) {
            pararMusica();
            musicaAtual = new GreenfootSound(nomeFicheiro);
            musicaAtual.setVolume(volume);
            musicaAtual.playLoop();
            nomeMusicaAtual = nomeFicheiro;
        }
    }
    
    /**
     * Para a música atual, se houver alguma a tocar.
     */
    public static void pararMusica() {
        if (musicaAtual != null) {
            musicaAtual.stop();
            musicaAtual = null;
            nomeMusicaAtual = "";
        }
    }
}
