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

    def make_move(self, x, y, symbol):
        if self.valid_move(x, y):
            self._board[x][y] = symbol

    def valid_move(self, x, y):
        return self.is_within(x, y) and self.is_empty(x, y)

    def is_within(self, x, y):
        return self.inside(x) and self.inside(y)

    def is_empty(self, x, y):
        return self._board[x][y] == None

    def inside(self, point):
        return point >= 0 and point < self.BOARD_SIZE

    def full_board(self):
        for row in self.board:
            for element in row:
                if element == self.EMPTY:
                    return False
        return True