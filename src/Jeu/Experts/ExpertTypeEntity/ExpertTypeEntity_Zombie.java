package Jeu.Experts.ExpertTypeEntity;

public class ExpertTypeEntity_Zombie extends ExpertTypeEntity {
    public ExpertTypeEntity_Zombie(ExpertTypeEntity suivant) {
        super(suivant);
    }

    @Override
    public boolean resout(String type) throws Exception {
        return true;
    }

    @Override
    public boolean estExistant(String type) {
        String regex = "^[z|Z]{1}[o|O]{1}[m|M]{1}[b|B]{1}[i|I]{1}[e|E]{1}$";
        if (type.matches(regex)) {
            return true;
        }
        return false;
    }
}
