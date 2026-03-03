import greenfoot.*;  
import java.util.List;

/**
 * Classe abstrata que representa um inimigo genérico no jogo.
 * 
 * <p>Herda de {@link Actor} do Greenfoot, podendo ser colocado no mundo do jogo.
 * Subclasses como {@link Fantasma}, {@link Olho} e {@link Besta} devem sobrescrever
 * métodos específicos para definir comportamentos e atributos únicos.</p>
 * 
 * <p>Funcionalidades principais:</p>
 * <ul>
 *   <li>Animação de spawn (appear) e ação (action);</li>
 *   <li>Movimento e perseguição a jogadores vivos;</li>
 *   <li>Sistema de vida, danos e morte com sons correspondentes;</li>
 *   <li>Gestão de posição real para movimentos precisos;</li>
 *   <li>Atualização de pontuação global dependendo do tipo de inimigo.</li>
 * </ul>
 * 
 * <p>Esta classe serve como base para qualquer inimigo do jogo, definindo
 * estrutura, comportamento genérico e integração com o sistema de pontuação.</p>
 * 
 * @author Grupo 8
 */
public abstract class Inimigo extends Actor {

    /** Sprites de spawn/idle. */
    protected GreenfootImage[] appear;

    /** Sprites de ação (chase, attack, etc.). */
    protected GreenfootImage[] action;

    /** Sprites para olhos (subclasses podem usar). */
    protected GreenfootImage[] olho;

    /** Índice do frame atual da animação. */
    protected int frame = 0;

    /** Contador para controlar velocidade da animação. */
    protected int timer = 0;

    /** Estado atual: 0 = appear, 1 = action. */
    protected int state = 0;

    /** Velocidade do inimigo. */
    protected double velocidade;

    /** Alvo do inimigo (jogador). */
    protected Actor target;

    /** Delay antes de começar a agir. */
    protected int spawnDelay;

    /** Vida do inimigo. */
    protected int vida;

    /** Indica se o som de spawn já foi tocado. */
    protected boolean spawnsom = false;

    /** Caminhos ou nomes dos sons. */
    protected String somSpawn;
    protected String somHit;
    protected String somMorte;

    /** Posição real do inimigo (para movimentos precisos). */
    protected double posX, posY;

    /**
     * Construtor da classe Inimigo.
     * 
     * @param target Alvo inicial do inimigo (jogador)
     * @param vida Quantidade de vida
     * @param velocidade Velocidade do inimigo
     * @param spawnDelay Frames de atraso antes de agir
     */
    public Inimigo(Actor target, int vida, double velocidade, int spawnDelay) {
        this.target = target;
        this.vida = vida;
        this.velocidade = velocidade;
        this.spawnDelay = spawnDelay;
    }

    @Override
    protected void addedToWorld(World world) {
        super.addedToWorld(world);
        posX = getX();
        posY = getY();
    }

    @Override
    public void act() {
        animar();

        BaseWorld world = (BaseWorld) getWorld();
        if (world == null || world.isGamePaused()) return;

        if (target == null || target.getWorld() == null) {
            Actor novoAlvo = encontraJogadorVivo();
            if (novoAlvo != null) target = novoAlvo;
            else return;
        }

        if (state == 1 && spawnDelay <= 0) {
            comportamento();
        } else {
            spawnDelay--;
        }
    }

    /**
     * Procura o jogador vivo mais próximo.
     * 
     * @return Jogador mais próximo ou {@code null} se não houver
     */
    protected Actor encontraJogadorVivo() {
        List<Jogador> jogadores = getWorld().getObjects(Jogador.class);
        if (jogadores.isEmpty()) return null;

        Actor maisProximo = jogadores.get(0);
        double menorDist = Double.MAX_VALUE;

        for (Actor j : jogadores) {
            double dist = Math.hypot(getX() - j.getX(), getY() - j.getY());
            if (dist < menorDist) {
                menorDist = dist;
                maisProximo = j;
            }
        }

        return maisProximo;
    }

    /**
     * Comportamento genérico do inimigo.
     * Subclasses podem sobrescrever este método.
     */
    protected void comportamento() {
        moverParaPlayer();
    }

    /**
     * Atualiza a animação do inimigo.
     */
    protected void animar() {
        timer++;
        if (timer % 10 != 0) return;
        timer = 0;

        if (state == 0 && appear != null) {
            setImage(appear[frame]);
            frame++;
            if (frame >= appear.length) {
                frame = 0;
                state = 1;
                if (!spawnsom && somSpawn != null) {
                    Greenfoot.playSound(somSpawn);
                    spawnsom = true;
                }
            }
        } else if (state == 1 && action != null) {
            setImage(action[frame]);
            frame++;
            if (frame >= action.length) frame = 0;
        }
    }

    /**
     * Move o inimigo em direção ao jogador alvo.
     */
    protected void moverParaPlayer() {
        if (target != null) {
            double dx = target.getX() - posX;
            double dy = target.getY() - posY;
            double dist = Math.sqrt(dx*dx + dy*dy);
            if (dist > 0) {
                posX += velocidade * dx / dist;
                posY += velocidade * dy / dist;
                setLocation((int) posX, (int) posY);
            }
        }
    }

    /**
     * Aplica dano ao inimigo.
     * Remove o inimigo se a vida chegar a 0.
     */
    public void levarDano() {
        vida--;
        if (vida <= 0) {
            morrer();
        } else {
            if (somHit != null) Greenfoot.playSound(somHit);
        }
    }

    /**
     * Método padrão de morte do inimigo.
     */
    protected void morrer() {
        if (somMorte != null) Greenfoot.playSound(somMorte);

        if (this instanceof Fantasma) ScoreGlobal.adicionar(100);
        else if (this instanceof Olho) ScoreGlobal.adicionar(150);
        else if (this instanceof Besta) ScoreGlobal.adicionar(300);

        if (getWorld() != null) getWorld().removeObject(this);
    }
}
