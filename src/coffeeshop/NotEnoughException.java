package coffeeshop;

public class NotEnoughException extends RuntimeException{
    public NotEnoughException(double money) {
        super(money + " cannot cover cost of beverages.");
    }
}
