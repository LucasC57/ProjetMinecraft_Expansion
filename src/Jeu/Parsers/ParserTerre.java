package Jeu.Parsers;

import Jeu.Bloc.Bloc;
import Exception.*;
import Jeu.Bloc.BlocTerre;

public class ParserTerre extends Parser {
    public ParserTerre(Parser suivant) {
        super(suivant);
    }

    @Override
    public Bloc parser(String nomBloc) throws Exception {
        if (nomBloc == null || nomBloc.trim().isEmpty()) {
            throw new ParserManquantException();
        }
        return new BlocTerre();
    }

    @Override
    public boolean saitParser(String nomBloc) {
        return nomBloc.contains("T");
    }
}
