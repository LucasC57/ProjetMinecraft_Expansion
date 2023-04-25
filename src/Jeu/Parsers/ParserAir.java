package Jeu.Parsers;
import Jeu.Bloc.Bloc;
import Exception.*;
import Jeu.Bloc.BlocAir;

public class ParserAir extends Parser {
    public ParserAir(Parser suivant) {
        super(suivant);
    }
    @Override
    public Bloc parser(String nomBloc) throws Exception {
        if (nomBloc == null || nomBloc.trim().isEmpty()) {
            throw new ParserManquantException();
        }
        // Création d'un bloc d'air
        return new BlocAir();
    }
    @Override
    public boolean saitParser(String nomBloc) {
        return nomBloc.trim().equals("A");
    }
}
