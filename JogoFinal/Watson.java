import greenfoot.*;

/**
 * Representa o personagem jogável Watson.
 *
 * <p>Herda todas as funcionalidades de {@link Jogador}, adicionando:
 * <ul>
 *   <li>Movimento horizontal e vertical controlado pelo jogador;</li>
 *   <li>Disparo de balas usando {@link Bala};</li>
 *   <li>Configuração específica de teclas e animações próprias de Watson;</li>
 *   <li>Integração com o sistema de plataformas e limites do nível.</li>
 * </ul>
 * </p>
 *
 * <p>Funcionalidades principais:</p>
 * <ul>
 *   <li>Movimento e animações do personagem;</li>
 *   <li>Disparo de projéteis e interação com inimigos;</li>
 *   <li>Permite ativar/desativar salto e movimento vertical dependendo do nível.</li>
 * </ul>
 * 
 * <p>Este actor é utilizado nos níveis do jogo para representar o personagem Watson.</p>
 * 
 * @author Grupo 8
 */
public class Watson extends Jogador {

    /**
     * Construtor da classe {@code Watson}.
     *
     * @param groundLevel nível do chão para o personagem
     */
    public Watson(int groundLevel) {
        super(
            "Run", 7,             // animação de andar
            "Shoot", 4,           // animação de disparo
            "left", "right", "K", "L", // teclas de esquerda, direita, salto e disparo
            "up", "down",          // teclas para movimento vertical
            "pewpew.mp3",          // som de disparo
            5, -10, groundLevel    // velocidade, força do salto, chão
        );

        // Neste nível, Watson pode usar movimento vertical
        setVerticalMovement(true);

        // Desativa o salto
        setCanJump(false);
    }

    /**
     * Método executado continuamente pelo ciclo de jogo.
     *
     * <p>Chama a lógica base de {@link Jogador} para controlar movimento,
     * animação, disparo e colisões.</p>
     */
    @Override
    public void act() {
        super.act();
        if (getWorld() == null) return;
    }
}
