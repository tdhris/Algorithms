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
    return alpha_beta_minimax(game, computer_player).move

"""Minimax Algorithm assumes that at each step:
* Maximazing player is trying to maximize his chances of winning
* on the next turn the Minimizing player is trying to minimize the chances of the maximazing player winning

Alpha–beta pruning algorithm: seeks to decrease the number of nodes that are evaluated by the minimax algorithm
* Stops evaluating a move when at least one possibility has been found that proves the move to be worse than a previously examined move"""

def alpha_beta_minimax(game, computer_player, depth=INITIAL_DEPTH, alpha=NINFINITY, beta = INFINITY):
    possible_game = deepcopy(game)

    if possible_game.running is False:
        score = score_game(possible_game, computer_player, depth)
        return BestMove(score, possible_game.last_move())

    if game.player == computer_player:
        best_move = BestMove(alpha, None)
        for move in possible_game.all_possible_moves():
            possible_game.play_turn(move)
            possible_game.change_turn()
            scored_move = alpha_beta_minimax(possible_game, computer_player, depth-1, alpha, beta)
            if scored_move.score > best_move.score:
                best_move = scored_move
                best_move.move = move
            if alpha > beta:
                break
        return best_move

    else:
        best_move = BestMove(beta, None)
        for move in possible_game.all_possible_moves():
            possible_game.play_turn(move)
            possible_game.change_turn()
            scored_move = alpha_beta_minimax(possible_game, computer_player, depth-1, alpha, beta)
            if scored_move.score < best_move.score:
                best_move = scored_move
                best_move.move = move
            if alpha > beta:
                break
        return best_move


def score_game(game, computer_player, depth):
    if not game.has_winner:
        return TIE + game.count_empty_squares() + depth
    if game.winner == computer_player:
        return WIN * (game.count_empty_squares() + depth)
    else:
        return LOSE * (game.count_empty_squares() + depth)
