import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Representa uma tábua no mundo do jogo.
 * <p>
 * Esta classe é um tipo de plataforma que pode suportar jogadores ou outros atores.
 * Herda todas as funcionalidades da classe {@link Plataforma}, incluindo correção de posição e suporte ao personagem.
 * A tábua é estática e não possui ações contínuas no método {@link #act()}.
 * </p>
 * 
 * @see Plataforma
 */
public class tabua extends Plataforma {

    /**
     * Construtor da classe Tabua.
     * <p>
     * Define a imagem da tábua ao criar o objeto.
     * </p>
     */
    public tabua() {
        GreenfootImage img = getImage();       
        setImage(img);  
    }

    /**
     * Método act() chamado a cada ciclo do mundo.
     * <p>
     * Neste caso, o método está vazio porque a tábua não possui ações contínuas.
     * </p>
     */
    public void act() {
        // Nenhuma ação necessária
    }
}
