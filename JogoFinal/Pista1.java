import greenfoot.*;

/**
 * Representa a primeira pista do jogo.
 * 
 * <p>Quando o personagem Sherlock interage com esta pista,
 * é apresentada uma mensagem de história e o jogador é questionado
 * se deseja avançar para o próximo nível ({@link DLevel3}).</p>
 * 
 * <p>Funcionalidades principais:</p>
 * <ul>
 *   <li>Deteta colisão pixel-perfect com Sherlock;</li>
 *   <li>Apresenta mensagem narrativa ao jogador;</li>
 *   <li>Permite avançar para o nível seguinte após confirmação;</li>
 *   <li>Remove-se do mundo quando utilizada.</li>
 * </ul>
 * 
 * <p>Esta pista é utilizada no segundo nível ({@link CLevel2}).</p>
 * 
 * @author Grupo 8
 */
public class Pista1 extends Colisoes {

    /** Indica se a pista já foi usada. */
    private boolean usada = false; 

    /**
     * Construtor da classe {@code Pista1}.
     * Redimensiona a imagem da pista.
     */
    public Pista1() {
        GreenfootImage img = getImage();
        img.scale(50, 50); 
        setImage(img);
    }

    /**
     * Método executado continuamente pelo ciclo do jogo.
     * 
     * <p>Deteta colisão com Sherlock e apresenta a mensagem de história,
     * perguntando se o jogador deseja avançar para o próximo nível.</p>
     */
    @Override
    public void act() {
        if (usada) return; 

        Sherlock sherlock = (Sherlock) getOneObjectAtOffset(0, 0, Sherlock.class);
        if (sherlock != null && sherlock.pixelPerfectTouching(Pista1.class)) {

            String resposta = Greenfoot.ask(
                "Sherlock, the streets of London are silent, yet whispers echo in the shadows.\n" +
                "Ghostly figures roam, guarding secrets older than the city itself.\n\n" +
                "Watson, each specter we face reveals a fragment of a greater mystery.\n" +
                "Amid the narrow alleys, a scroll flutters to our feet.\n" +
                "'Follow the trail where darkness deepens.\n" +
                "Bravery shall illuminate what the eyes cannot see.'\n\n" +
                "Do you wish to follow this clue into the unknown? (yes)"
            );

            if (resposta != null && resposta.equalsIgnoreCase("yes")) {
                usada = true;
                if (getWorld() instanceof CLevel2) {
                    ((CLevel2) getWorld()).pararMusica();
                }
                Greenfoot.setWorld(new DLevel3());
                getWorld().removeObject(this);
            }
        }
    }
}
