package Jeu.Parsers;

import Jeu.Bloc.Bloc;
import Exception.*;
import Jeu.Bloc.BlocHerbe;

public class ParserHerbe extends Parser {
    public ParserHerbe(Parser suivant) {
        super(suivant);
    }

    @Override
    public Bloc parser(String nomBloc) throws Exception {
        if (nomBloc == null || nomBloc.trim().isEmpty()) {
            throw new ParserManquantException();
        }
        return new BlocHerbe();
    }

    @Override
    public boolean saitParser(String nomBloc) {
        return nomBloc.trim().equals("H");
    }
}
