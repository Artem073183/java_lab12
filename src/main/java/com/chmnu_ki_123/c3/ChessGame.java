package com.chmnu_ki_123.c3;

enum Color {
    WHITE, BLACK
}

enum PieceType {
    PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING
}

class Piece {
    private Color color;
    private PieceType type;

    public Piece(Color color, PieceType type) {
        this.color = color;
        this.type = type;
    }

    public boolean isValidMove(Square targetSquare) {
        // Тут можна реалізувати логіку перевірки на допустимість руху для кожної фігури
        return true; // Заглушка
    }

    public Color getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }
}

class Square {
    private int x;
    private int y;
    private Piece piece;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        this.piece = null;
    }

    public boolean isOccupied() {
        return piece != null;
    }

    public void placePiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

class ChessBoard {
    private Square[][] squares;

    public ChessBoard() {
        squares = new Square[8][8];
        initializeBoard();
    }

    public void initializeBoard() {
        // Ініціалізація квадратів та розміщення фігур
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new Square(i, j);
            }
        }

        // Розміщення фігур на початкових позиціях
        squares[0][0].placePiece(new Piece(Color.WHITE, PieceType.ROOK));
        squares[0][1].placePiece(new Piece(Color.WHITE, PieceType.KNIGHT));
        squares[0][2].placePiece(new Piece(Color.WHITE, PieceType.BISHOP));
        squares[0][3].placePiece(new Piece(Color.WHITE, PieceType.QUEEN));
        squares[0][4].placePiece(new Piece(Color.WHITE, PieceType.KING));
        squares[0][5].placePiece(new Piece(Color.WHITE, PieceType.BISHOP));
        squares[0][6].placePiece(new Piece(Color.WHITE, PieceType.KNIGHT));
        squares[0][7].placePiece(new Piece(Color.WHITE, PieceType.ROOK));

        // Додайте чорні фігури
        squares[7][0].placePiece(new Piece(Color.BLACK, PieceType.ROOK));
        squares[7][1].placePiece(new Piece(Color.BLACK, PieceType.KNIGHT));
        squares[7][2].placePiece(new Piece(Color.BLACK, PieceType.BISHOP));
        squares[7][3].placePiece(new Piece(Color.BLACK, PieceType.QUEEN));
        squares[7][4].placePiece(new Piece(Color.BLACK, PieceType.KING));
        squares[7][5].placePiece(new Piece(Color.BLACK, PieceType.BISHOP));
        squares[7][6].placePiece(new Piece(Color.BLACK, PieceType.KNIGHT));
        squares[7][7].placePiece(new Piece(Color.BLACK, PieceType.ROOK));

        // Розміщення пішаків
        for (int i = 0; i < 8; i++) {
            squares[1][i].placePiece(new Piece(Color.WHITE, PieceType.PAWN));
            squares[6][i].placePiece(new Piece(Color.BLACK, PieceType.PAWN));
        }
    }

    public Square getSquare(int x, int y) {
        return squares[x][y];
    }

    public void movePiece(int fromX, int fromY, int toX, int toY) {
        Square fromSquare = getSquare(fromX, fromY);
        Square toSquare = getSquare(toX, toY);

        if (fromSquare.isOccupied() && (!toSquare.isOccupied() ||
                fromSquare.getPiece().getColor() != toSquare.getPiece().getColor())) {
            // Перевірка допустимості руху
            if (fromSquare.getPiece().isValidMove(toSquare)) {
                toSquare.placePiece(fromSquare.getPiece());
                fromSquare.placePiece(null);
                System.out.println("Фігуру переміщено.");
            } else {
                System.out.println("Недопустимий рух.");
            }
        } else {
            System.out.println("Цей квадрат зайнятий або фігура не може бути переміщена.");
        }
    }
}

// Основний клас для тестування
public class ChessGame {
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        // Приклад переміщення фігури
        board.movePiece(1, 0, 2, 0); // Пішак вперед
        board.movePiece(1, 0, 2, 1); // Неправильний рух
    }
}
