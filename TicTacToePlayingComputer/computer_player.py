from game import Game
from copy import deepcopy

WIN = 1
LOSE = -1
TIE = 0


#returns best move
def minimax(game, player):
    if not game.running:
        return (score_game(game, player), None)

    if game.player == player:
        best_move = (-2, None)
        for move in game.all_possible_moves():
            possible_game = deepcopy(game)
            possible_game.play_turn(*move)
            score = minimax(possible_game, game.opponent)[0]
            if score > best_move[0]:
                best_move = (score, move)

    if game.player != player:
        best_move = (2, None)
        for move in game.all_possible_moves():
            possible_game = deepcopy(game)
            possible_game.play_turn(*move)
            score = minimax(possible_game, game.opponent)[0]
            if score < best_move[0]:
                best_move = (score, move)

    return best_move


def score_game(game, player):
    if not game.has_winner:
        return TIE

    if game.winner == player:
        return WIN

    else:
        return LOSE
