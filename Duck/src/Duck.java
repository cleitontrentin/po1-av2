
public abstract class Duck {

	protected IFly fly;
	protected IQuack quack;
	
	public abstract void display();
	
	public void swim() {
		System.out.println("swim");
	}
	
	public void peformFly() {
		fly.fly();
	}
	
	public void peformQuack() {
		quack.quack();
	}
	
}
