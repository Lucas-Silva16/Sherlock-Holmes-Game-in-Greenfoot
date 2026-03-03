import greenfoot.*;  


/**
 * Amenu_jogo representa o menu principal do jogo.
 * 
 * Funcionalidades principais:
 * - Mostra os botões Play, Settings e Quit no menu.
 * - Toca música de fundo em loop contínuo.
 * - Atualiza a imagem de fundo a cada frame.
 * - Verifica se o jogo foi pausado (por exemplo, se ESC for pressionado).
 */
public class Amenu_jogo extends BaseWorld
{
    GreenfootSound musica = new GreenfootSound("menu.mp3");
   

    public Amenu_jogo()
    {    
        super(1024, 683);
        addObject(new Play(), 512, 341);
        addObject(new Settings(), 512, 441);
        addObject(new quit(), 512, 541);
        musica.setVolume(50);
    }

    public void started()
    {
        musica.playLoop();
    }
    
    public void act()
    {
        setBackground("raw.png");
    }
}

