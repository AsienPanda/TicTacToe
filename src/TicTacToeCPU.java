public class TicTacToeCPU {
	private CurrentGameBoard cBG;
	private String player;

	public TicTacToeCPU(CurrentGameBoard cBG) {
		this.cBG = cBG;
		player = "O";
	}

	public int chooseBestMove() {
		int defenseMove = getDefenseMove();
		if (defenseMove < 0) {
			return chooseOffenseMove(player, 0, cBG);
		} else {
			return defenseMove;
		}
	}

	private int chooseOffenseMove(String currentPlayer, int depth,
			CurrentGameBoard aCBG) {
		boolean allTie = true;
		int lastMove = 0;
		if (aCBG.isFinished()) {
			if (!aCBG.isTie()) {
				if (aCBG.getCurrentPlayer().equals(player)) {
					return depth;
				} else {
					return -1;
				}
			} else {
				return 0;
			}
		} else {
			int bestScore = 0;
			int bestMove = 0;
			for (int i = 0; i < aCBG.getBoard().length; i++) {
				CurrentGameBoard tempCBG = new CurrentGameBoard(aCBG);
				tempCBG.toggleMessages();
				String[] tempBoard = tempCBG.getBoard();
				int currentScore = depth;
				if (tempBoard[i].equals(" ")) {
					tempBoard[i] = currentPlayer;
					if (currentPlayer.equals("X")) {
						currentScore = chooseOffenseMove("O", depth + 1,
								tempCBG);

					} else {
						currentScore = chooseOffenseMove("X", depth + 1,
								tempCBG);

					}
					if ((currentScore > 0 && currentScore < bestScore)
							|| (currentScore > 0 && bestScore == 0)) {
						bestScore = currentScore;
						bestMove = i;
						allTie = false;
					} else if (allTie) {
						lastMove = i;
					}
				}

			}
			if (depth == 0) {
				if (allTie) {
					return lastMove + 1;
				} else {
					return bestMove + 1;
				}
			} else {
				return bestScore;
			}
		}
	}

	public int getDefenseMove() {
		String opponent;
		if (player.equals("O")) {
			opponent = "X";
		} else {
			opponent = "O";
		}

		for (int i = 0; i < cBG.getBoard().length; i++) {
			CurrentGameBoard tempCBG = new CurrentGameBoard(cBG);
			tempCBG.toggleMessages();
			if (tempCBG.getBoard()[i].equals(" ")) {
				tempCBG.moveMade(i + 1);
				if (tempCBG.isFinished() && !tempCBG.isTie()
						&& tempCBG.getCurrentPlayer().equals(player)) {
					return -1;
				}
			}
		}

		for (int i = 0; i < cBG.getBoard().length; i++) {
			CurrentGameBoard tempCBG = new CurrentGameBoard(cBG);
			tempCBG.setOtherPlayer();
			tempCBG.toggleMessages();
			if (tempCBG.getBoard()[i].equals(" ")) {
				tempCBG.moveMade(i + 1);
				if (tempCBG.isFinished() && !tempCBG.isTie()
						&& tempCBG.getCurrentPlayer().equals(opponent)) {
					return i + 1;
				}
			}
		}
		return -1;
	}
}