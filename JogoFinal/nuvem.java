import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Representa uma nuvem no mundo do jogo.
 * <p>
 * Esta classe é um tipo de plataforma que pode suportar jogadores ou outros atores.
 * Herda todas as funcionalidades da classe {@link Plataforma}, incluindo correção de posição e suporte ao personagem.
 * A nuvem é estática e não possui ações contínuas no método {@link #act()}.
 * </p>
 * 
 * @see Plataforma
 */
public class nuvem extends Plataforma {

    /**
     * Construtor da classe Nuvem.
     * <p>
     * Define a imagem da nuvem ao criar o objeto e redimensiona para 50x50 pixels.
     * </p>
     */
    public nuvem() {
        GreenfootImage img = getImage();       
        img.scale(50, 50); 
        setImage(img);  
    }

    /**
     * Método act() chamado a cada ciclo do mundo.
     * <p>
     * Neste caso, o método está vazio porque a nuvem não possui ações contínuas.
     * </p>
     */
    public void act() {
        // Nenhuma ação necessária
    }
}
