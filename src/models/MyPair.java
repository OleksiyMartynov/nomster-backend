package models;

public class MyPair<T,U> 
{
	T one;
	U two;
	public MyPair(T one, U two) {
		super();
		this.one = one;
		this.two = two;
	}
	public T getOne() {
		return one;
	}
	public void setOne(T one) {
		this.one = one;
	}
	public U getTwo() {
		return two;
	}
	public void setTwo(U two) {
		this.two = two;
	}
	
}
