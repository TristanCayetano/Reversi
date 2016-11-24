import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.PrintWriter;

public class Main {

	static GameBoard board;
	static Player player1, player2;

	public static void main(String[] args) {
		System.out.println("Willkommen zu Reversi");
		setPlayers();
		System.out.println("Wähle die Spielfeldgröße: ");
		setBoard();
		save();
		GameLoop();
	}

	static void GameLoop() {
		for (int i = 0; i < (board.size * board.size) - 9; i++) {
			place(player1);
			place(player2);
		}
	}

	/**
	 * Methode zum erstellen des Spieler
	 */

	static void setPlayers() {
		System.out.println("Spieler 1 gib einen Namen ein: ");
		player1 = new Player(new Scanner(System.in).next(), 'X');

		System.out.println("Spieler 2 gib einen Namen ein: ");
		player2 = new Player(new Scanner(System.in).next(), 'O');
	}

	/**
	 * Methode zum erstellen des Spielfeldes
	 */

	static void setBoard() {
		try { // Überprüfung auf korrekte Zahlen
			int size = new Scanner(System.in).nextInt();
			if (size > 5 && size % 2 == 0) {
				board = new GameBoard(size + 2);
			} else {
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e) {
			System.out.println("Bitte nur gerade Zahlen größer 5 eingeben:");
			setBoard();
		}
	}

	static void save() {
		save("D:\\Test.txt");
	}

	/**
	 * Methode zum platzieren eines Steines
	 *
	 * @param p
	 *            der Spieler der am Zug ist
	 */

	static void place(Player p) {
		System.out.println(p.name + " ist am Zug:");
		String s = new Scanner(System.in).next();
		int x = s.toUpperCase().charAt(0);
		int y = s.charAt(1);
		if (s.length() == 2 && ((x > 64 && x < 91) && (y > 48 && y < 58))) {
			board.place(p.symbol, x - 65, y - 49);
		}
	}

	/**
	 * Methode zum speichern des Spielstandes
	 *
	 * @param file
	 *            Speicherdatei
	 *
	 */

	static void save(String file) {
		try {
			PrintWriter writer = new PrintWriter(file, "UTF-8");
			for (int i = 0; i < board.size; i++) {
				for (int j = 0; j < board.size; j++) {
					writer.print(board.stone[i][j]);
				}
			}
			writer.print(board.turn);
			writer.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
