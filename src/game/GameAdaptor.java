package game;

public class GameAdaptor implements GamePlayerInterface{
    //NOT FINISHED .... DO IT ASAP
    public GameObservable gameObservable;

    public GameAdaptor(GameObservable gameObservable) {
        this.gameObservable = gameObservable;
    }

    @Override
    public String play(String player, String cards) {
        return null;
    }

}
