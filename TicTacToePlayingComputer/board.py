from move import BoardMove

class TicTacToeBoard:
    BOARD_SIZE = 3
    EMPTY = None

    def __init__(self):
        self._board = self.initialize_board()

    @property
    def board(self):
        return self._board

    def initialize_board(self):
        return [ [self.EMPTY] * self.BOARD_SIZE for row in range(self.BOARD_SIZE) ]

    def make_move(self, move, symbol):
        if self.valid_move(move):
            self._board[move.x][move.y] = symbol

    def at(self, position):
        return self.board[position.x][position.y]

    def valid_move(self, move):
        return self.is_within(move) and self.is_empty(move)

    def is_within(self, move):
        return self.inside(move.x) and self.inside(move.y)

    def is_empty(self, move):
        return self._board[move.x][move.y] == self.EMPTY

    def inside(self, point):
        return point >= 0 and point < self.BOARD_SIZE

    def clear_square(self, square):
        self._board[square.x][square.y] = self.EMPTY

    def full_board(self):
        for row in self.board:
            for element in row:
                if element == self.EMPTY:
                    return False
        return True

    def get_diagonal(self):
        return [self.board[i][i] for i in range(self.BOARD_SIZE)]

    def get_antidiagonal(self):
        return [self.board[i][self.BOARD_SIZE - 1 - i] for i in range(self.BOARD_SIZE)]

    def get_row(self, row_number):
        return self.board[row_number]

    def get_column(self, column_number):
        return [self.board[i][column_number] for i in range(self.BOARD_SIZE)]