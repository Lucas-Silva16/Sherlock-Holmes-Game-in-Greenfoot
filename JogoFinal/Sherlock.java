import greenfoot.*;

/**
 * Representa o personagem jogável Sherlock.
 *
 * <p>Herda todas as funcionalidades de {@link Jogador}, adicionando:
 * <ul>
 *   <li>Controle específico de colisão com plataformas ({@link Plataforma});</li>
 *   <li>Deteção de colisão com cartas ({@link carta}) e reprodução de áudio associado;</li>
 *   <li>Exibição de mensagens após tocar o som da carta;</li>
 *   <li>Transição automática para o próximo nível ({@link CLevel2}) ao pressionar SPACE.</li>
 * </ul>
 * </p>
 * 
 * <p>Este actor é utilizado nos níveis iniciais para representar o personagem Sherlock.</p>
 * 
 * <p>Funcionalidades principais:</p>
 * <ul>
 *   <li>Movimento horizontal e vertical controlado pelo jogador;</li>
 *   <li>Disparo de balas usando {@link Bala};</li>
 *   <li>Interação com cartas para avançar a história;</li>
 *   <li>Integração com o sistema de plataformas do jogo.</li>
 * </ul>
 * 
 * @author Grupo 8
 */
public class Sherlock extends Jogador {

    /** Indica se o som da carta já foi tocado. */
    private boolean audioTocado = false;

    /** Contador de frames após tocar o som da carta. */
    private int contadorFrames = 0;

    /** Indica se a mensagem final já apareceu. */
    private boolean mensagemApareceu = false;

    /**
     * Construtor da classe {@code Sherlock}.
     *
     * @param groundLevel nível do chão para o personagem
     */
    public Sherlock(int groundLevel) {
        super(
            "sigma", 6,             // animação de andar
            "shooting", 6,          // animação de disparo
            "A", "D", "E", "F",     // teclas de esquerda, direita, salto e disparo
            "W", "S",               // teclas de movimento vertical
            "pew.mp3",              // som de disparo
            3, -18, groundLevel     // velocidade, força do salto e chão
        );

        setVerticalMovement(false);
    }

    /**
     * Método executado continuamente pelo ciclo de jogo.
     *
     * <p>Controla movimento, colisões com plataformas e cartas,
     * reprodução de áudio, exibição de mensagens e transição de nível.</p>
     */
    @Override
    public void act() {
        super.act();

        if (getWorld() == null) return;

        // Colisão com plataformas
        Plataforma plataforma = (Plataforma) getOneObjectAtOffset(
            0, (getImage().getHeight() / 2) + 1, Plataforma.class
        );
        if (plataforma != null) {
            plataforma.corrigirPosicaoPersonagem(this, controller);
        }

        // Colisão com carta
        if (pixelPerfectTouching(carta.class) && !audioTocado) {
            Greenfoot.playSound("mensagemcarta.mp3");
            audioTocado = true;
            contadorFrames = 0;
            removeTouching(carta.class);
        }

        // Mostrar mensagem após som
        if (audioTocado) {
            contadorFrames++;
            if (contadorFrames >= 2100) {
                getWorld().showText(
                    "Are you ready? (press space for next level)",
                    getWorld().getWidth() / 2,
                    getWorld().getHeight() / 2
                );
                audioTocado = false;
                mensagemApareceu = true;
            }
        }

        // Avançar para o próximo nível
        if (mensagemApareceu && Greenfoot.isKeyDown("space")) {
            if (getWorld() instanceof BLevel1) {
                ((BLevel1) getWorld()).pararMusica();
            }
            Greenfoot.setWorld(new CLevel2());
        }
    }
}
