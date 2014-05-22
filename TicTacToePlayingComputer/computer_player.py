from game import Game
from move import BestMove, BoardMove
from copy import deepcopy

WIN = 1
LOSE = -1
TIE = 0

NINFINITY = - 10000
INFINITY = 10000

INITIAL_DEPTH = 100


def minimax(game, depth=INITIAL_DEPTH, maximazing_player=True):
    possible_game = deepcopy(game)

    if possible_game.running is False:
        score = score_game(possible_game, maximazing_player)
        return BestMove(score, depth, possible_game.last_move())

    if maximazing_player:
        best_move = BestMove(NINFINITY, depth, None)
        for move in possible_game.all_possible_moves():
            possible_game.play_turn(move)
            scored_move = minimax(possible_game, depth - 1, False)
            if scored_move.score > best_move.score and scored_move.depth <= best_move.depth:
                best_move = scored_move
                best_move.move = move

    else:
        best_move = BestMove(INFINITY, depth, None)
        for move in possible_game.all_possible_moves():
            possible_game.play_turn(move)
            scored_move = minimax(possible_game, depth - 1, True)
            if scored_move.score < best_move.score and scored_move.depth <= best_move.depth:
                best_move = scored_move
                best_move.move = move
    return best_move


def score_game(game, maximazing_player):
    if not game.has_winner:
        return TIE
    if maximazing_player:
        return WIN
    else:
        return LOSE
