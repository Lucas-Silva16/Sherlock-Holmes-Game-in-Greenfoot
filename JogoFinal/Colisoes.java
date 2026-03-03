import greenfoot.*;

/**
 * Classe base para objetos que necessitam de detecção de colisão precisa (pixel-perfect).
 *
 * <p>Esta classe fornece métodos para verificar colisões entre objetos
 * considerando a transparência dos pixels, em vez de usar apenas limites
 * rectangulares.</p>
 *
 * <p>Funcionalidades principais:</p>
 * <ul>
 *   <li>Permite verificar colisões pixel-perfect com outros objetos do jogo;</li>
 *   <li>Considera apenas pixels visíveis (alpha > 0) para determinar a colisão;</li>
 *   <li>É uma classe base que pode ser herdada por outros actores que necessitem
 *       de colisões precisas.</li>
 * </ul>
 * 
 * <p>O método {@link #pixelPerfectTouching(Class)} percorre todos os pixels das imagens
 * dos dois objetos e retorna {@code true} apenas se houver sobreposição de pixels visíveis.</p>
 * 
 * <p>Este método é útil para jogos onde a precisão da colisão é crítica, como plataformas
 * ou deteção de interacções complexas.</p>
 * 
 * @author Grupo 8
 */
public class Colisoes extends Actor {

    /**
     * Verifica se este actor está a tocar outro actor da classe {@code cls} com precisão de pixel.
     *
     * <p>Considera apenas pixels visíveis (alpha > 0) nas imagens dos dois actores.
     * Retorna {@code true} se houver sobreposição de pixels visíveis.</p>
     *
     * @param cls classe do actor com o qual se pretende verificar colisão.
     * @return {@code true} se houver colisão de pixels visíveis, {@code false} caso contrário.
     */
    protected boolean pixelPerfectTouching(Class<?> cls) {
        // Tenta encontrar um objeto da classe cls que esteja a intersectar com este actor
        Actor other = getOneIntersectingObject(cls);
        if (other == null) return false; // Se não houver objeto, não há colisão

        // Obtém as imagens dos dois objetos
        GreenfootImage img1 = getImage();
        GreenfootImage img2 = other.getImage();

        // Calcula a diferença de posição entre os dois objetos
        int dx = other.getX() - getX();
        int dy = other.getY() - getY();

        // Larguras e alturas das imagens
        int w1 = img1.getWidth();
        int h1 = img1.getHeight();
        int w2 = img2.getWidth();
        int h2 = img2.getHeight();

        // Calcula offsets para alinhar corretamente os pixels das imagens
        int ox = w1 / 2 - w2 / 2 - dx;
        int oy = h1 / 2 - h2 / 2 - dy;

        // Percorre todos os pixels da primeira imagem
        for (int x = 0; x < w1; x++) {
            for (int y = 0; y < h1; y++) {
                int x2 = x - ox; // Posição correspondente na segunda imagem
                int y2 = y - oy;

                // Verifica se as coordenadas correspondentes estão dentro da segunda imagem
                if (x2 >= 0 && x2 < w2 && y2 >= 0 && y2 < h2) {
                    // Verifica se ambos os pixels são visíveis (alpha > 0)
                    if (img1.getColorAt(x, y).getAlpha() > 0 &&
                        img2.getColorAt(x2, y2).getAlpha() > 0) {
                        return true; // Colisão detectada
                    }
                }
            }
        }
        return false; // Não houve colisão de pixels visíveis
    }
}
