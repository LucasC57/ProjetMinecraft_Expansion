package Jeu.Parsers;

import Jeu.Bloc.Bloc;
import Exception.*;
import Jeu.Bloc.BlocAir;

public class ParserBlocRespawn extends Parser{
    public ParserBlocRespawn(Parser suivant) {
        super(suivant);
    }

    @Override
    public Bloc parser(String nomBloc) throws Exception {
        if (nomBloc == null || nomBloc.trim().isEmpty()) {
            throw new ParserManquantException();
        }
        return new BlocAir();
    }

    @Override
    public boolean saitParser(String nomBloc) {
        return nomBloc.trim().equals("R");
    }
}
