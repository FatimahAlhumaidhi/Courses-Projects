public class IntegerWrapper {
	private int x;
	IntegerWrapper(){
		x = 0;
	}
	IntegerWrapper(int x){
		this.x = x;
	}
	void Increment(){
		x++;
	}
	void add(int x) {
		this.x += x;
	}
	int get() {
		return x;
	}
}
