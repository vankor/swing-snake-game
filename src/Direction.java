enum Direction {
	TOP(1), BOTTOM(2), LEFT(3), RIGHT(4), PAUSE(5), DEAD(6);
	
	int num;
	Direction(int u){this.num = u;}
	
	public int getNum(){
		return num;
	}
}