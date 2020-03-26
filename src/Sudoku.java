public class Sudoku { 

	public static final int EMPTY = 0;
	public static final int SIZE = 9;

	public static boolean isSafe(int[][] board, int row, int col, int num) { 

		for (int i = 0; i < board.length; i++) { 
			if (board[row][i] == num) { 
				System.out.println("Violação Da Restrição De Linha.");
				return false; 
			} 
		} 

		for (int i = 0; i < board.length; i++) { 
			if (board[i][col] == num) { 
				System.out.println("Violação Da Restrição De Coluna.");
				return false; 
			} 
		} 

		int sqrt = (int) Math.sqrt(board.length); 
		int boxRowStart = row - row % sqrt; 
		int boxColStart = col - col % sqrt; 

		for (int r = boxRowStart; r < boxRowStart + sqrt; r++) { 
			for (int c = boxColStart; c < boxColStart + sqrt; c++) { 
				if (board[r][c] == num) { 
					System.out.println("Violação Da Restrição De SubMatriz.");
					return false; 
				} 
			} 
		} 
		return true; 
	} 

	public static boolean solveSudoku(int[][] board) {

		int row = -1; 
		int col = -1; 
		boolean isEmpty = true; 
		for (int i = 0; i < SIZE; i++) { 
			for (int j = 0; j < SIZE; j++) { 
				if (board[i][j] == EMPTY) { 
					row = i; 
					col = j; 
					isEmpty = false; 
					break; 
				} 
			} 
			if (!isEmpty) { 
				System.out.println();
				print(board);
				break; 
			} 
		}

		if (isEmpty) {//CASO BASE
			return true; 
		}

		System.out.println();
		System.out.println("Posição Atual: \nLinha: " + (row+1) + "\nColuna: " + (col+1));

		for (int num = 1; num <= SIZE; num++) { 
			System.out.println();
			System.out.println("Tento Inserir O Número: " + num);
			if (isSafe(board, row, col, num)) { 
				System.out.println();
				System.out.println("Número " + num + " Não Viola Nenhuma Regra Do Sudoku.\nLogo É Inserido Naquela Posição.");
				board[row][col] = num; 
				if (solveSudoku(board)) {
					return true; 
				} 
				else {
					System.out.println("Backtracking Na Linha " + (row+1) + " | Coluna "+ (col+1));
					board[row][col] = 0;
					System.out.println();
					print(board);
				} 
			} else {
				System.out.println("Número " + num + " Viola As Regras Do Sudoku.\nLogo Não Pode Ser Inserido Naquela Posição.");
				if (num == 9) {
					System.out.println();
					System.out.println("Sem Solução Para o Sudoku Na Linha " + (row+1) + " | Coluna "+ (col+1) + "\nÉ Necessário Fazer Backtracking!");
					System.out.println();
					print(board);
				}
			}
		}

		return false; 
	} 

	public static void print(int[][] board) { 
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.print(" " + board[i][j]);
			}

			System.out.println();
		}

		System.out.println();
	}

	public static void main(String args[]) { 

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

				if (solveSudoku(board)) { 
					System.out.println();
					System.out.println("Solução:");
					System.out.println();
					print(board);
				} 
				else { 
					System.out.println("Sem Solução!"); 
				} 
	} 
} 
