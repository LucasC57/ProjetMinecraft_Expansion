package Jeu.Parsers;
import Exception.*;
import Jeu.Bloc.Bloc;

public abstract class Parser {
    private Parser suivant = null;

    public Parser(Parser suivant) {
        this.suivant = suivant;
    }

    public Bloc traiter(String caractere) throws Exception {
        Bloc blocCree = null;
        if (saitParser(caractere))
        // Si le parser sait parser la ligne, il la parse
        {
            blocCree = parser(caractere);
        } else if (aUnSuivant())
            // S'il ne sait pas mais qu'il a un suivant dans la liste chaine, il lui repasse la ligne et qu'il se débrouille !
            getSuivant().traiter(caractere);
        else
            // Sinon, on est arrivé au bout sans trouver un parser
            // et on lance une exception ! Que le prog appelant se débrouille avec cette ligne !
            throw new ParserManquantException();
        return blocCree;
    }

    private Parser getSuivant() {
        return suivant;
    }

    private boolean aUnSuivant() {
        return suivant != null;
    }

    public abstract Bloc parser(String nomBloc) throws Exception;

    public abstract boolean saitParser(String nomBloc);
}
