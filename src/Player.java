
public class Player {

	String name;
	char symbol;
	int score;

	Player(String name, char symbol) {
		this.name = name;
		this.symbol = symbol;
	}
	
	GameBoard place(GameBoard board, int x, int y){
		return board.place(symbol, x, y);
		}
}
