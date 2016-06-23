
public class RedheadDuck extends Duck{

	public RedheadDuck() {
		fly = new FlyWithWings();
		quack = new Quack();
	}
	
	@Override
	public void display() {
		System.out.println("I'm a real RedheadDuck");
	}
}
