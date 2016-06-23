
public class RubberDuck extends Duck {

	public RubberDuck() {
		fly = new FlyNoWay();
		quack = new MuteQuack();
	}
	
	@Override
	public void display() {
		System.out.println("I'm a real RubberDuck");
	}
	
}
