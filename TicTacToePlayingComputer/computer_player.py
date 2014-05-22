from game import Game
from move import BestMove, BoardMove
from copy import deepcopy

WIN = 1
LOSE = -1
TIE = 0

NINFINITY = - 10000
INFINITY = 10000

INITIAL_DEPTH = 100


def computer_move(game, computer_player):
    return minimax(game, computer_player).move

"""Minimax Algorithm assumes that at each step:
* Maximazing player is trying to maximize his chances of winning
* on the next turn the Minimizing player is trying to minimize the chances of the maximazing player winning"""
def minimax(game, computer_player, depth=INITIAL_DEPTH):
    possible_game = deepcopy(game)

    if possible_game.running is False:
        score = score_game(possible_game, computer_player, depth)
        return BestMove(score, possible_game.last_move())

    if game.player == computer_player:
        best_move = BestMove(NINFINITY, None)
        for move in possible_game.all_possible_moves():
            possible_game.play_turn(move)
            scored_move = minimax(possible_game, computer_player, depth-1)
            if scored_move.score > best_move.score:
                best_move = scored_move
                best_move.move = move
        return best_move

    else:
        best_move = BestMove(INFINITY, None)
        for move in possible_game.all_possible_moves():
            possible_game.play_turn(move)
            scored_move = minimax(possible_game, computer_player, depth-1)
            if scored_move.score < best_move.score:
                best_move = scored_move
                best_move.move = move
        return best_move


def score_game(game, computer_player, depth):
    if not game.has_winner:
        return TIE + game.count_empty_squares() + depth
    if game.winner == computer_player:
        return WIN * game.count_empty_squares() + depth
    else:
        return LOSE * game.count_empty_squares() + depth
