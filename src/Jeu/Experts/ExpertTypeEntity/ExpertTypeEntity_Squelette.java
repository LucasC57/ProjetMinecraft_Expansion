package Jeu.Experts.ExpertTypeEntity;

public class ExpertTypeEntity_Squelette extends ExpertTypeEntity{
    public ExpertTypeEntity_Squelette(ExpertTypeEntity suivant) {
        super(suivant);
    }

    @Override
    public boolean resout(String type) throws Exception {
        return true;
    }

    @Override
    public boolean estExistant(String type) {
        String regex = "^[s|S]{1}[q|Q]{1}[u|U]{1}[e|E]{1}[l|L]{1}[e|E]{1}[t|T]{1}[t|T]{1}[e|E]{1}$";
        if (type.matches(regex)) {
            return true;
        }
        return false;
    }
}
