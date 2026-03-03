import greenfoot.*;

/**
 * Representa a pista final do jogo.
 * 
 * <p>Quando o personagem Sherlock interage com esta pista,
 * é apresentada uma narrativa final do nível 3 e o jogador é questionado
 * se deseja avançar para o labirinto do nível 4 ({@link ELevel4}).</p>
 * 
 * <p>Funcionalidades principais:</p>
 * <ul>
 *   <li>Deteta colisão pixel-perfect com Sherlock;</li>
 *   <li>Apresenta mensagem narrativa detalhada ao jogador;</li>
 *   <li>Permite avançar para o próximo nível após confirmação;</li>
 *   <li>Remove-se do mundo quando utilizada.</li>
 * </ul>
 * 
 * <p>Esta pista é utilizada no terceiro nível ({@link DLevel3}).</p>
 * 
 * @author Grupo 8
 */
public class PistaFinal extends Colisoes {

    /** Indica se a pista já foi usada. */
    private boolean usada = false;

    /**
     * Construtor da classe {@code PistaFinal}.
     * Redimensiona a imagem da pista.
     */
    public PistaFinal() {
        GreenfootImage img = getImage();
        img.scale(50, 50); 
        setImage(img);
    }

    /**
     * Método executado continuamente pelo ciclo do jogo.
     * 
     * <p>Deteta colisão com Sherlock e apresenta a narrativa final,
     * perguntando se o jogador deseja avançar para o próximo nível (labirinto).</p>
     */
    @Override
    public void act() {
        if (usada) return;

        Sherlock sherlock = (Sherlock) getOneObjectAtOffset(0, 0, Sherlock.class);
        if (sherlock != null && sherlock.pixelPerfectTouching(PistaFinal.class)) {

            String resposta = Greenfoot.ask(
                "The forest looms, dark and foreboding.\n" +
                "Eyes glimmer in the shadows, horns pierce the mist, and whispers turn into growls.\n\n" +
                "Sherlock, the trail from the city led us here — and it continues deeper.\n" +
                "Watson, the enemies are not mere beasts, but guardians of the secrets hidden within.\n" +
                "We have faced every wave, and yet the path continues.\n\n" +
                "At last, the labyrinth stretches before us.\n" +
                "Corridors twist like the veins of the earth, shadows crawl along the walls.\n" +
                "Sherlock, only together may we navigate its dark paths.\n" +
                "Watson, the key we seek lies hidden — the final gateway to the truth.\n" +
                "Every turn tests courage, every dead end a lesson.\n\n" +
                "Do you dare enter the labyrinth and retrieve the key? (yes)"
            );

            if (resposta != null && resposta.equalsIgnoreCase("yes")) {
                usada = true;
                if (getWorld() instanceof DLevel3) {
                    ((DLevel3) getWorld()).pararMusica();
                }
                Greenfoot.setWorld(new ELevel4());
                getWorld().removeObject(this);
            }
        }
    }
}
