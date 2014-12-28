
public class CurrentGameBoard {
	private String[] board;
	private String player;
	private boolean toggleMessages = true;

	public CurrentGameBoard(String[] array) {
		board = array;
		player = "X";
		for (int i = 0; i < board.length; i++) {
			board[i] = " ";
		}
	}

	public CurrentGameBoard(CurrentGameBoard cBG) {
		board = cBG.board.clone();
		player = cBG.player;
	}

	public String getCurrentPlayer() {
		return player;
	}

	private boolean validPos(int i) {
		return i > 0 && i <= 9 && board[i - 1].equals(" ");
	}

	public boolean isFinished() {

		if (checkColumns() || checkRows() || checkDiags()) {
			printWinMessage();

			return true;
		} else if (checkFilled()) {
			printTieMessage();

			return true;
		} else {
			return false;
		}

	}

	private boolean checkFilled() {
		for (int i = 0; i < board.length; i++) {
			if (board[i].equals(" ")) {
				return false;
			}
		}
		return true;
	}

	private boolean checkColumns() {
		for (int i = 0; i < 3; i++) {
			if (board[i].equals(board[i + 3])
					&& board[i + 3].equals(board[i + 6])
					&& !board[i + 3].equals(" ")) {
				player = board[i];
				return true;
			}
		}
		return false;
	}

	private boolean checkRows() {
		for (int i = 0; i < 9; i += 3) {
			if (board[i].equals(board[i + 1]) && board[i + 1].equals(board[i + 2])
					&& !board[i + 1].equals(" ")) {
				player = board[i];
				return true;
			}
		}
		return false;
	}

	private boolean checkDiags() {
		if (board[6].equals(board[4]) && board[4].equals(board[2])
				&& !board[4].equals(" ")) {
			player = board[4];
			return true;
		} else if (board[8].equals(board[4]) && board[4].equals(board[0])
				&& !board[4].equals(" ")) {
			player = board[4];
			return true;
		} else {
			return false;
		}
	}

	public boolean moveMade(int i) {
		if (!validPos(i)) {
			printInvalidMessage(i);
			return false;
		} else {
			board[i - 1] = player;
			if (player.equals("X")) {
				player = "O";
				return true;
			} else {
				player = "X";
				return true;
			}
		}
	}

	public void setOtherPlayer() {
		if (player.equals("X")) {
			player = "O";
		} else {
			player = "X";
		}
	}

	public boolean isTie() {
		if (!checkDiags() && !checkColumns() && !checkRows() && checkFilled()) {
			return true;
		}
		return false;
	}

	public String[] getBoard() {
		return board;
	}

	public void printBoard() {
		System.out.printf(" %s + %s + %s \n", board[6], board[7], board[8]);
		System.out.printf("---+---+---\n");
		System.out.printf(" %s + %s + %s \n", board[3], board[4], board[5]);
		System.out.printf("---+---+---\n");
		System.out.printf(" %s + %s + %s \n", board[0], board[1], board[2]);
	}

	public void toggleMessages() {
		toggleMessages = !toggleMessages;
	}

	public void printWinMessage() {
		if (toggleMessages) {
			System.out.printf("Player %s wins!\n", player);
			printBoard();
		}
	}

	public void printTieMessage() {
		if (toggleMessages) {
			System.out.println("Tie game! No winner.");
			printBoard();
		}
	}

	public void printInvalidMessage(int i) {
		if (toggleMessages) {
			System.out.printf("Invalid board position <%s>! Try again.\n", i);
			printBoard();
		}
	}
}