import greenfoot.*;

/**
 * Representa um barril no mundo do jogo.
 * <p>
 * Esta classe é um tipo de plataforma que pode suportar jogadores ou outros atores.
 * Herda todas as funcionalidades da classe {@link Plataforma}, incluindo correção de posição e suporte ao personagem.
 * O barril é estático e não possui ações contínuas no método {@link #act()}.
 * </p>
 * 
 * @see Plataforma
 */
public class Barril extends Plataforma {

    /**
     * Construtor da classe Barril.
     * <p>
     * Define a imagem do barril ao criar o objeto.
     * </p>
     */
    public Barril() {
        GreenfootImage img = getImage();       
        setImage(img);  
    }

    /**
     * Método act() chamado a cada ciclo do mundo.
     * <p>
     * Neste caso, o método está vazio porque o barril não possui ações contínuas.
     * </p>
     */
    public void act() {
        // Nenhuma ação necessária
    }
}
