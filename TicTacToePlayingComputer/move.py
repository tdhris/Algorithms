class BoardMove:
    def __init__(self, x=None, y=None):
        self._x = x
        self._y = y

    def __repr__(self):
        return "(" + str(self.x) + ", " + str(self.y) + ")"

    @property
    def x(self):
        return self._x

    @property
    def y(self):
        return self._y


class BestMove(BoardMove):
    def __init__(self, score, move):
        super(BestMove, self).__init__(move)
        self.score = score

    def __repr__(self):
        return str(self.move)
    