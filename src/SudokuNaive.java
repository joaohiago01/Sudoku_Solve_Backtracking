public class SudokuNaive {

	private int[][] board;
	public static final int EMPTY = 0;
	public static final int SIZE = 9;

	SudokuNaive(int[][] board) {
		this.board = new int[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				this.board[i][j] = board[i][j];
			}
		}
	}

	private boolean isInRow(int row, int number) {
		for (int i = 0; i < SIZE; i++)
			if (board[row][i] == number) {
				System.out.println("Viola��o Da Restri��o De Linha.");
				return true;
			}

		return false;
	}

	private boolean isInCol(int col, int number) {
		for (int i = 0; i < SIZE; i++)
			if (board[i][col] == number) {
				System.out.println("Viola��o Da Restri��o De Coluna.");
				return true;
			}

		return false;
	}

	private boolean isInBox(int row, int col, int number) {
		int r = row - row % 3;
		int c = col - col % 3;

		for (int i = r; i < r + 3; i++)
			for (int j = c; j < c + 3; j++)
				if (board[i][j] == number) {
					System.out.println("Viola��o Da Restri��o De SubMatriz.");
					return true;
				}

		return false;
	}

	private boolean isOk(int row, int col, int number) {
		return !isInRow(row, number)  &&  !isInCol(col, number)  &&  !isInBox(row, col, number);
	}

	public boolean solveSudokuNaive() {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				System.out.println("Posi��o Atual: \nLinha: " + (row+1) + "\nColuna: " + (col+1));
				System.out.println();
				System.out.println("Verificando Se A Posi��o Est� Vazia.");
				if (board[row][col] == EMPTY) {
					System.out.println();
					System.out.println("Posi��o Vazia, Ou Seja, Posso Inserir Um N�mero.");
					for (int number = 1; number <= SIZE; number++) {
						System.out.println();
						System.out.println("Tento Inserir O N�mero: " + number);
						if (isOk(row, col, number)) {
							System.out.println();
							System.out.println("N�mero " + number + " N�o Viola Nenhuma Regra Do Sudoku.\nLogo � Inserido Naquela Posi��o.");
							board[row][col] = number;
							print();
							break;
						} else {
							System.out.println("N�mero " + number + " Viola As Regras Do Sudoku.\nLogo N�o Pode Ser Inserido Naquela Posi��o.");
							if (number == 9) {
								System.out.println();
								System.out.println("Sudoku N�o Resolvido.");
								return false;
							}
						}
					}
					
				} else {
					System.out.println();
					System.out.println("Posi��o Previamente Preenchida.");
				}
			}
		}
		return true;
	}

	public void print() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.print(" " + board[i][j]);
			}

			System.out.println();
		}

		System.out.println();
	}

	public int[][] getBoard() {
		return board;
	}

	public static void main(String[] args) {
		int[][] board = new int[][] 
				{ 
			{5, 3, 0, 0, 7, 0, 0, 0, 0}, 
			{6, 0, 0, 1, 9, 5, 0, 0, 0}, 
			{0, 9, 8, 0, 0, 0, 0, 6, 0}, 
			{8, 0, 0, 0, 6, 0, 0, 0, 3}, 
			{4, 0, 0, 8, 0, 3, 0, 0, 1}, 
			{7, 0, 0, 0, 2, 0, 0, 0, 6}, 
			{0, 6, 0, 0, 0, 0, 2, 8, 0}, 
			{0, 0, 0, 4, 1, 9, 0, 0, 5}, 
			{0, 0, 0, 0, 8, 0, 0, 7, 9} 
				}; 
				SudokuNaive sudoku = new SudokuNaive(board);
				sudoku.print();
				sudoku.solveSudokuNaive();
				sudoku.print();
	}
}