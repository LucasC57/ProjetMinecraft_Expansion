package Jeu.Parsers;

import Jeu.Bloc.Bloc;
import Exception.*;
import Jeu.Bloc.BlocPierre;

public class ParserPierre extends Parser {
    public ParserPierre(Parser suivant) {
        super(suivant);
    }

    @Override
    public Bloc parser(String nomBloc) throws Exception {
        if (nomBloc == null || nomBloc.trim().isEmpty()) {
            throw new ParserManquantException();
        }
        return new BlocPierre();
    }

    @Override
    public boolean saitParser(String nomBloc) {
        return nomBloc.trim().equals("P");
    }
}
