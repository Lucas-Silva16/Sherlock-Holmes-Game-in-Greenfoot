import greenfoot.*;
import java.util.*;

/**
 * Classe responsável por criar (spawnar) inimigos no jogo em diferentes waves.
 * 
 * <p>Cada wave contém um tipo específico de inimigo, e o spawn ocorre com intervalo
 * de tempo entre elas. Controla também o progresso das waves e verifica se todos
 * os inimigos de uma wave foram eliminados.</p>
 * 
 * <p>Funcionalidades principais:</p>
 * <ul>
 *   <li>Gerir a criação de inimigos Fantasma, Olho e Besta;</li>
 *   <li>Controlar temporizador entre waves;</li>
 *   <li>Verificar se todas as waves foram concluídas;</li>
 *   <li>Gerir posições aleatórias dentro de limites pré-definidos.</li>
 * </ul>
 * 
 * @author Grupo 8
 */
public class Spawner {
    /** Referência ao mundo onde os inimigos serão criados. */
    private World world;

    /** Delay entre waves (em frames). */
    private int delayEntreWaves = 120; 
    private int delayTimer = 0;

    /** Wave atual. */
    private int waveAtual = 1;

    /** Indica se uma wave está ativa. */
    private boolean waveAtiva = false;

    /** Limites de posição para spawn. */
    private int minX = 700;
    private int maxX = 1024;
    private int minY = 200;
    private int maxY = 600;

    /**
     * Construtor que recebe o mundo como parâmetro.
     * 
     * @param world Mundo onde os inimigos serão adicionados
     */
    public Spawner(World world) {
        this.world = world;
    }

    /**
     * Método principal de atualização, chamado a cada ciclo do jogo.
     * Controla o início e o fim das waves, bem como o temporizador entre elas.
     */
    public void atualizar() {
        if (!waveAtiva) {
            if (delayTimer > 0) {
                delayTimer--;
                return;
            }
            spawnWave(waveAtual);
        } else {
            // Verifica se a wave atual terminou
            if ((waveAtual == 1 && todosFantasmasMortos()) ||
                (waveAtual == 2 && todosOlhosMortos()) ||
                (waveAtual == 3 && todosBestasMortas())) {
                
                waveAtiva = false;
                waveAtual++;
                delayTimer = delayEntreWaves;
            }
        }
    }

    /**
     * Inicia uma nova wave conforme o número da wave atual.
     * 
     * @param wave Número da wave
     */
    private void spawnWave(int wave) {
        switch (wave) {
            case 1: 
                spawnFantasmas(9); 
                break;
            case 2: 
                spawnOlhos(5); 
                break;
            case 3: 
                spawnBesta(2); 
                break;
            default:
                return;
        }
        waveAtiva = true;
    }

    /** Cria uma quantidade definida de Fantasmas em posições aleatórias. */
    private void spawnFantasmas(int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            Fantasma f = new Fantasma();
            world.addObject(f, getRandomX(), getRandomY());
        }
    }

    /** Cria uma quantidade definida de Olhos em posições aleatórias. */
    private void spawnOlhos(int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            Olho o = new Olho();
            world.addObject(o, getRandomX(), getRandomY());
        }
    }

    /** Cria uma quantidade definida de Bestas em posições aleatórias. */
    private void spawnBesta(int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            Besta b = new Besta();
            world.addObject(b, getRandomX(), getRandomY());
        }
    }

    /** Gera uma posição X aleatória dentro dos limites. */
    private int getRandomX() {
        return Greenfoot.getRandomNumber(maxX - minX + 1) + minX;
    }

    /** Gera uma posição Y aleatória dentro dos limites. */
    private int getRandomY() {
        return Greenfoot.getRandomNumber(maxY - minY + 1) + minY;
    }

    /** Verifica se todos os Fantasmas já morreram. */
    private boolean todosFantasmasMortos() {
        return world.getObjects(Fantasma.class).isEmpty();
    }

    /** Verifica se todos os Olhos já morreram. */
    private boolean todosOlhosMortos() {
        return world.getObjects(Olho.class).isEmpty();
    }

    /** Verifica se todas as Bestas já morreram. */
    private boolean todosBestasMortas() {
        return world.getObjects(Besta.class).isEmpty();
    }

    /**
     * Indica se todas as waves foram concluídas e não restam inimigos no mundo.
     * 
     * @return {@code true} se todas as waves terminaram, {@code false} caso contrário
     */
    public boolean terminouTudo() {
        return waveAtual > 3 && todosFantasmasMortos() && todosOlhosMortos() && todosBestasMortas();
    }
}
