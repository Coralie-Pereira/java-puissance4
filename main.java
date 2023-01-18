import Classes.Menu;
import Classes.Game;


public class Main {
    public static void main(String[] args){
        Menu.showMenu();
        Game game =  new Game();
        System.out.println(game.IAChooseColumn());
        System.out.println(game.IAChooseColumn());
        System.out.println(game.IAChooseColumn());
        System.out.println(game.IAChooseColumn());
    }

}
