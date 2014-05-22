from board import TicTacToeBoard
from move import BoardMove

class Game:
    X = "X"
    O = "O"

    def __init__(self):
        self._board = TicTacToeBoard()
        self._player = self.X
        self._running = True
        self._has_winner = False
        self._moves = []

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

    @property
    def winning_move(self):
        return [self.player] * self.board.BOARD_SIZE

    def change_turn(self):
        if self.running:
            self._player = self.opponent

    def play_turn(self, move):
        if self.running:
            self.board.make_move(move, self.player)
            self._moves.append(move)
            self.check_game_over()
            self.change_turn()

    def check_game_over(self):
        if self.board_filled() or self.game_won():
            self.end_game()

    def board_filled(self):
        return self.board.full_board()

    def at(self, position):
        return self.board.at(position)

    def has_moves_on_board(self):
        return bool(self._moves)

    def last_move(self):
        if self.has_moves_on_board():
            self._moves[-1]

    def valid_move(self, move):
        return self.board.valid_move(move)

    def game_won(self):
        if self.check_rows() or self.check_columns() or self.check_diagonal() or self.check_antidiagonal():
            self._has_winner = True
            self.winner = self.player
            return True
        return False

    def check_rows(self):
        for i in range(self.board.BOARD_SIZE):
            if self.winning_move == self.board.get_row(i):
                return True
        return False

    def check_columns(self):
        for i in range(self.board.BOARD_SIZE):
            if self.winning_move == self.board.get_column(i):
                return True
        return False

    def check_diagonal(self):
        return self.winning_move == self.board.get_diagonal()

    def check_antidiagonal(self):
        return self.winning_move == self.board.get_antidiagonal()

    def all_possible_moves(self):
        moves = []
        for i in range(self.board.BOARD_SIZE):
            for j in range(self.board.BOARD_SIZE):
                move = BoardMove(i, j)
                if self.board.is_empty(move):
                    moves.append(move)
        return moves


    def end_game(self):
        self._running = False
    