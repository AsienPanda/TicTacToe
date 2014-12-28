import java.util.Scanner;

public class TicTacToe {
	public static void main(String[] args) {
		String[] board = new String[9];
		Scanner in = new Scanner(System.in);
		CurrentGameBoard newGame = new CurrentGameBoard(board);
		newGame.printBoard();

		System.out
				.printf("The board has been set! Player X, make your move!\n");
		while (in.hasNext()) {
			if (in.hasNextInt()) {
				while (!newGame.moveMade(in.nextInt())) {
					newGame.printBoard();
				}
				CurrentGameBoard tempCBG = new CurrentGameBoard(newGame);
				TicTacToeCPU cpu = new TicTacToeCPU(tempCBG);
				System.out.printf("CPU made move <%s>\n", cpu.chooseBestMove());
				if (!newGame.isFinished()) {
					newGame.moveMade(cpu.chooseBestMove());
					newGame.printBoard();
				}

				if (newGame.isFinished()) {
					break;
				}
			} else {
				System.out.println("Invalid board position! Try again.");
				in.next();
				newGame.printBoard();
			}
		}
	}

}
