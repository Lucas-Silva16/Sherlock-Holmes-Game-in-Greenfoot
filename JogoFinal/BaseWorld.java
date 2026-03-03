import greenfoot.*;
/**
 * BaseWorld é a classe base para todos os mundos do jogo, fornecendo funcionalidades comuns.
 *
 * Funcionalidades principais:
 * - Extende World para definir o tamanho do mundo e a escala (1 pixel = 1 unidade).
 * - Implementa sistema de pausa:
 *   - Pressionar ESC ativa/desativa a pausa.
 *   - Durante a pausa, exibe "PAUSED" no centro do ecrã.
 * - Método isGamePaused() permite que outros objetos verifiquem se o jogo está pausado.
 *
 * Todas as classes de mundo (níveis, menu, game over, vitória) devem estender BaseWorld
 * para herdar estas funcionalidades.
 */
public abstract class BaseWorld extends World {
    private boolean isPaused = false;
    private boolean escPressed = false;

    public BaseWorld(int width, int height) {
        super(width, height, 1);
    }

    public void checkPause() {
        if (Greenfoot.isKeyDown("escape")) {
            if (!escPressed) {
                escPressed = true;
                isPaused = !isPaused; // toggle
                if (isPaused) {
                    showText("PAUSED", getWidth()/2, getHeight()/2);
                } else {
                    showText("", getWidth()/2, getHeight()/2);
                }
            }
        } else {
            escPressed = false;
        }
    }

    public boolean isGamePaused() {
        return isPaused;
    }
}

