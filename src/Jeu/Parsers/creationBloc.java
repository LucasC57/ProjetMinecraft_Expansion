package Jeu.Parsers;
import Exception.*;
import Jeu.Bloc.Bloc;

import java.awt.print.PrinterAbortException;

/**
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
                System.out.println("Aucun parser n'existe pour le caractere " + caractere);
            }
        }
        return null;
    }
}
