package Jeu.Parsers;
import Exception.*;
import Jeu.Bloc.Bloc;
import Jeu.Bloc.BlocAir;

/*
    Cette classe à le même but que la classe Fichier dans te le TPCor
 */
public class creationBloc {
    public static Bloc lireCaractere(String caractere, Parser parser) throws Exception {
        if (parser == null) {
            // Il n'y a pas de parser pour ce caractère : Impossible dans notre cas
            throw new ParserManquantException();
        } else {
            try {
                return parser.traiter(caractere);
            } catch (ParserManquantException e) {
                // On ne va rien faire
            }
        }
        return new BlocAir();
    }
}
