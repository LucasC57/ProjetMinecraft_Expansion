package Jeu.Parsers;

import Jeu.Bloc.Bloc;
import Exception.*;
import Jeu.Bloc.BlocBois;

public class ParserBois extends Parser {
    public ParserBois(Parser suivant) {
        super(suivant);
    }

    @Override
    public Bloc parser(String nomBloc) throws Exception {
        if (nomBloc == null || nomBloc.trim().isEmpty()) {
            throw new ParserManquantException();
        }
        return new BlocBois();
    }

    @Override
    public boolean saitParser(String nomBloc) {
        return nomBloc.trim().equals("B");
    }
}
