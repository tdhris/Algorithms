from game import Game
from move import BoardMove
from computer_player import computer_move
import os
import re

BOARD = """
 0 │ 1 │ 2
───┼───┼───
"""
SPACE = ' '

class CLI:
    HUMAN = Game.X
    COMPUTER = Game.O

    def __init__(self):
        self._game = Game()

    @property
    def game(self):
        return self._game

    def play(self):
        while self.game.running:
            self.clear_screen()
            self.draw_board()
            move = None
            while move is None:
                if self.game.player == self.HUMAN:
                    move = self.get_player_move()
                else:
                    move = computer_move(self.game, self.COMPUTER)

            self.game.play_turn(move)

        self.clear_screen()
        self.draw_board()

        if self.game.has_winner:
            print("{0} wins!!".format(self.game.winner))

        else:
            print("It's a tie!")


    def get_player_move(self):
        row = int(input("Enter row (1 - 3)> ")) - 1
        col = int(input("Enter column (1 - 3)> ")) - 1
        move = BoardMove(row, col)

        if self.game.valid_move(move):
                return move

    def draw_board(self):
        for i in range(self.game.board.BOARD_SIZE):
            row = [SPACE if symbol is None else symbol for symbol in self.game.board.board[i]]
            print(self.fill_row(row))

    def fill_row(self, row):
        def replacement(x):
            i = int(x.group())
            return row[i]

        result = re.sub(r"\d", replacement, BOARD)
        return result

    def clear_screen(self):
        os.system("cls")

CLI().play()