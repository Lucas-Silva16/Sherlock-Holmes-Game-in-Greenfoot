import greenfoot.*;

/**
 * Representa a chave que os jogadores devem coletar para avançar
 * para o último nível do jogo (o labirinto do lobisomem).
 *
 * <p>Funcionalidades principais:</p>
 * <ul>
 *   <li>Redimensiona a imagem da chave ao ser criada;</li>
 *   <li>Verifica se os jogadores {@link Sherlock} e {@link Watson} 
 *       interagiram com a chave simultaneamente;</li>
 *   <li>Exibe uma pergunta de confirmação antes de avançar para o nível final;</li>
 *   <li>Para a música do nível atual antes de mudar para {@link FLevel5};</li>
 *   <li>Garante que a chave só pode ser usada uma vez.</li>
 * </ul>
 * 
 * <p>Este actor é utilizado no quarto nível {@link ELevel4} para desbloquear
 * o acesso ao nível final.</p>
 * 
 * @author Grupo 8
 */
public class Chave extends Colisoes {

    /** Indica se a chave já foi usada para avançar de nível. */
    private boolean usada = false;

    /**
     * Construtor da classe {@code Chave}.
     *
     * <p>Redimensiona a imagem original da chave para o tamanho adequado no mundo.</p>
     */
    public Chave() {
        GreenfootImage imagem = getImage();
        int width = imagem.getWidth();
        int height = imagem.getHeight();
        imagem.scale(width / 6, height / 6);
        setImage(imagem);
    }

    /**
     * Método executado continuamente pelo ciclo de jogo.
     *
     * <p>Verifica se os jogadores interagiram com a chave, exibe a confirmação
     * e, se autorizado, para a música do nível atual, remove a chave e
     * avança para {@link FLevel5}.</p>
     */
    @Override
    public void act() {
        if (usada) return;

        Sherlock sherlock = (Sherlock) getOneIntersectingObject(Sherlock.class);
        Watson watson = (Watson) getOneIntersectingObject(Watson.class);

        if (sherlock != null && watson != null) {
            String resposta = Greenfoot.ask(
                "Sherlock, the labyrinth's exit looms — a door sealed for centuries.\n" +
                "Watson, the key is in our hands, yet it demands courage to turn it.\n\n" +
                "Beyond this door, the forest awaits once more, bathed in the light of a full moon.\n" +
                "The final adversary waits: a beast of myth, terror, and legend.\n\n" +
                "Do you dare use the key and face the Werewolf? (yes)"
            );

            if (resposta != null && resposta.equalsIgnoreCase("yes")) {
                usada = true;

                if (getWorld() instanceof ELevel4) {
                    ((ELevel4) getWorld()).pararMusica();
                }

                Greenfoot.setWorld(new FLevel5());
                getWorld().removeObject(this);
            }
        }
    }
}
