from board import TicTacToeBoard

class Game:
    X = "X"
    O = "O"

    def __init__(self):
        self._board = TicTacToeBoard()
        self._player = self.X
        self._running = True
        self._has_winner = False

    @property
    def running(self):
        return self._running

    @property    
    def has_winner(self):
        return self._has_winner

    @property
    def board(self):
        return self._board

    @property
    def player(self):
        return self._player

    @property
    def opponent(self):
        if self.player == self.X:
            return self.O
        return self.X

    def change_turn(self):
        if self.running:
            self._player = self.opponent

    def play_turn(self, x, y):
        if self.running:
            self.board.make_move(x, y, self.player)
            self.check_game_over()
            self.change_turn()

    def check_game_over(self):
        if self.board_filled() or self.game_won():
            self.end_game()

    def board_filled(self):
        return self.board.full_board()

    def at(self, x, y):
        return self.board.board[x][y]

    def game_won(self):
        if self.check_rows() or self.check_columns() or self.check_diagonal() or self.check_antidiagonal():
            self._has_winner = True
            self.winner = self.player
            return True
        return False

    def check_rows(self):
        for row in self.board.board:
            if row == [self.player] * self.board.BOARD_SIZE:
                return True
        return False

    def check_columns(self):
        for i in range(self.board.BOARD_SIZE):
            column = [self.board.board[i][j] for j in range(self.board.BOARD_SIZE)]
            if column == ([self.player] * self.board.BOARD_SIZE):
                return True
        return False;

    def check_diagonal(self):
        for i in range(self.board.BOARD_SIZE):
            if self.board.board[i][i] != self.player:
                return False
        return True

    def check_antidiagonal(self):
        for i in range(self.board.BOARD_SIZE):
            if self.board.board[i][self.board.BOARD_SIZE - 1 - i] != self.player:
                return False
        return True

    def all_possible_moves(self):
        moves = []
        for i in range(self.board.BOARD_SIZE):
            for j in range(self.board.BOARD_SIZE):
                if self.board.is_empty(i, j):
                    moves.append((i, j))
        return moves


    def end_game(self):
        self._running = False
    