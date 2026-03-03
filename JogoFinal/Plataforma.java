import greenfoot.Actor;

/**
 * Representa uma plataforma no mundo do jogo.
 * <p>
 * Esta classe permite que jogadores (ou outros atores) se apoiem sobre ela,
 * corrigindo a posição vertical do personagem quando ele toca no topo da plataforma.
 * É especialmente útil para aplicar gravidade corretamente e impedir que o personagem atravesse a plataforma.
 * </p>
 * 
 * @see ControlsPlayers
 */
public class Plataforma extends Actor {

    /**
     * Ajusta a posição do personagem quando ele colide com o topo da plataforma.
     * <p>
     * Este método move o personagem para ficar em cima da plataforma
     * e reseta sua velocidade vertical para interromper a queda.
     * </p>
     *
     * @param personagem o ator (por exemplo, Sherlock ou Watson) cuja posição deve ser corrigida
     * @param controller o objeto ControlsPlayers associado ao personagem, usado para controlar a velocidade vertical
     */
    public void corrigirPosicaoPersonagem(Actor personagem, ControlsPlayers controller) {
        if (personagem != null && controller.velocityY > 0) {
            // Posição do topo da plataforma
            int topPlataforma = getY() - (getImage().getHeight() / 2);

            // Altura útil do personagem (considerando apenas a parte visível)
            int alturaUtil = (int)(personagem.getImage().getHeight() * 0.4);

            // Ajusta a posição do personagem para que fique acima da plataforma
            personagem.setLocation(personagem.getX(), topPlataforma - alturaUtil / 2 + 1);

            // Reseta a velocidade vertical do personagem
            controller.resetVelocity();
        }
    }
}
