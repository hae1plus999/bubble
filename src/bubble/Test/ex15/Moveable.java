package bubble.Test.ex15;

public interface Moveable {
	public abstract void left();
	public abstract void right();
	public abstract void up();
	//default를 사용하면 인터페이스도 몸체가 있는 메서드응 만들 수 있다. 다중상속이 안되는것이 많기 때문에
	//어댑터 패턴보다는 디폴트를 사용하는 것이 좋다
	default public void down() {};
	default public void attack() {};
}
