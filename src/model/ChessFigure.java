/**
A Figure osztalybol szarmazo figurakat tartalmazo osztaly.
Egy attributuma van az osztalynak, a figura tipusa, ezen kivul az ososztaly attributumai: x, y pozicio es a szine.
A konstruktor parameterkent kapja a babu poziciojat, szinet es tipusat.
*/

package model;


public  class ChessFigure extends Figure {

   
    public boolean canMoveTo(int x, int y) {
        System.out.println(" chessfigure can move to");
        return false;
    }
    
public enum TypeOfFigure {
    PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING;
}
    TypeOfFigure type;
    public ChessFigure(int posX, int posY, boolean dark, String s) {
        super(posX, posY, dark);
        switch (s) {
            case "PAWN" : {
                type = TypeOfFigure.PAWN;
                break;
            }
            case "ROOK" : {
                type = TypeOfFigure.ROOK;
                break;
            }
            case "BISHOP" : {
                type = TypeOfFigure.BISHOP;
                break;
            }
            case "KNIGHT" : {
                type = TypeOfFigure.KNIGHT;
                break;
            }
            case "QUEEN" : {
                type = TypeOfFigure.QUEEN;
                break;
            }
            case "KING" : {
                type = TypeOfFigure.KING;
                break;
            }
           
        }
        
        
    }

    public TypeOfFigure getType() {
        return type;
    }

    public void setType(TypeOfFigure type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChessFigure other = (ChessFigure) obj;
        if (this.type != other.type) {
            return false;
        }
        if (this.dark != other.dark) {
            return false;
        }
        return true;
    }

    
    
}
