package Jeu;

import Jeu.Bloc.BlocAir;
import Jeu.Experts.ExpertCraft.ExpertCraft;
import Jeu.Experts.ExpertMinage.Expert;
import Jeu.Item.Item;
import Exception.*;
import Jeu.Item.MainVide;

import java.util.ArrayList;
import java.util.Objects;

/**
 * La classe Joueur répresente tout simplement le ou les joueurs introduit dans un monde spécifique
 */
public class Joueur {
    /**
     * Nom du joueur.
     */
    private String nom;
    /**
     * Inventaire du joueur créé, initialisé à vide.
     */
    private Inventory inventaire = new Inventory();
    /**
     * Main du joueur, initialisé à vide.
     */
    private Item main = new MainVide();
    /**
     * Coordonnées du joueur créé.
     */
    private Coord coordonnees_joueur;
    /**
     * Monde où le joueur est créé.
     */
    private Monde monde;
    private int Vie = 20;
    /**
     * Constructeur de la classe Joueur
     * @param nom Nom du joueur créé.
     * @param inventaire Inventaire du joueur créé.
     * @param main Main du joueur créé.
     * @param coordonnees_joueur Coordonnées du joueur créé.
     * @param monde Monde là où le joueur est créé.
     * @throws CoordException
     * @throws PlayerArgumentException
     * @throws DeplacementException
     */
    // Constructeur
    public Joueur(String nom, Inventory inventaire, Item main, Coord coordonnees_joueur, Monde monde) throws CoordException, PlayerArgumentException, DeplacementException {
        setNom(nom);
        setInventaire(inventaire);
        setMain(main);
        setMonde(monde);
        setCoordonnees_joueur(coordonnees_joueur);
    }
    /**
     * Deuxième constructeur de la classe Joueur. Le joueur à un nom par défaut "Steve", il a l'inventaire de base,
     * donc initialisé à vide, il a rien dans sa main et les coordonnées du joueur créé sont les coordonnées du point de respawn
     * du monde donné.
     * @param monde Monde là où le joueur est créé.
     * @throws CoordException
     * @throws PlayerArgumentException
     * @throws DeplacementException
     */
    public Joueur(Monde monde) throws CoordException, PlayerArgumentException, DeplacementException {
        setNom("Steve");
        // Pas de setInventaire car l'inventaire est initialisé à vide
        // Pareil pour la main
        // On va partir du principe que si l'on ne précise pas les co du joueur, alors c'est au pts de respawn
        setMonde(monde);
        setCoordonnees_joueur(monde.getPoint_respawn());
    }
    /**
     * Getter concernant le nom du joueur.
     * @return Le nom du joueur créé.
     */
    public String getNom() {
        return nom;
    }
    /**
     * Setter concernant le nom du joueur créé.
     * @param nom Le nom du joueur créé.
     * @throws PlayerArgumentException Si le nom est null ou qu'il est vide.
     */
    public void setNom(String nom) throws PlayerArgumentException {
        if (nom == null || nom.trim().isEmpty()) {
            throw new PlayerArgumentException();
        }
        this.nom = nom;
    }
    /**
     * Getter concernant l'inventaire du joueur créé.
     * @return L'inventaire du joueur créé.
     */
    public Inventory getInventaire() {
        return inventaire;
    }
    /**
     * Setter concernant l'inventaire du joueur créé.
     * @param inventaire L'inventaire du joueur créé.
     * @throws PlayerArgumentException Si l'inventaire donné n'est pas initialisé ou qu'il n'existe pas.
     */
    public void setInventaire(Inventory inventaire) throws PlayerArgumentException {
        if (inventaire == null) {
            throw new PlayerArgumentException();
        }
        this.inventaire = inventaire;
    }
    /**
     * Getter concernant la main du joueur créé.
     * @return La main du joueur créé.
     */
    public Item getMain() {
        return main;
    }
    /**
     * Setter concernant la main du joueur créé.
     * @param main La main du joueur créé.
     * @throws PlayerArgumentException Si la main du joueur n'est pas initialisé ou qu'elle n'existe pas.
     */
    public void setMain(Item main) throws PlayerArgumentException {
        if (main == null) {
            throw new PlayerArgumentException();
        }
        this.main = main;
    }
    /**
     * Getter concernant les coordonnées du joueur créé.
     * @return Les coordonnées du joueur créé.
     */
    public Coord getCoordonnees_joueur() {
        return this.coordonnees_joueur;
    }
    /**
     * Setter concernant les coordonnées du joueur créé.
     * @param coordonnees_joueur Les coordonnées du joueur créé.
     * @throws PlayerArgumentException Si les coordonnées ne sont pas initialisées ou qu'elles n'existent pas.
     * @throws DeplacementException Si les coordonnées données ne sont pas valable avec les dimensions du monde, si les blocs dans les cases
     * liées à la tête et aux pieds du joueur ne sont pas fluides.
     */
    public void setCoordonnees_joueur(Coord coordonnees_joueur) throws PlayerArgumentException, DeplacementException {
        if (coordonnees_joueur == null) {
            throw new PlayerArgumentException();
        }
        // Vérification pour la sortie du monde
        if (coordonnees_joueur.getY() < 0) {
            throw new DeplacementException();
        }
        if (coordonnees_joueur.getX() - 1 < 0) {
            throw new DeplacementException();
        }
        if (coordonnees_joueur.getX() > monde.getHauteur() - 1) {
            throw new DeplacementException();
        }
        if (coordonnees_joueur.getY() > monde.getLargeur() - 1) {
            throw new DeplacementException();
        }
        this.coordonnees_joueur = coordonnees_joueur;
        Case[][] test = this.getMonde().getTab_monde();
        if (test[getCoordonnees_joueur().getY()][getCoordonnees_joueur().getX()].getContenu().isFluidite()) {
            test[getCoordonnees_joueur().getY()][getCoordonnees_joueur().getX()].setPresence_pieds(true);
        } else {
            throw new DeplacementException();
        }
        if (test[getCoordonnees_joueur().getY()][getCoordonnees_joueur().getX()-1].getContenu().isFluidite()) {
            test[getCoordonnees_joueur().getY()][getCoordonnees_joueur().getX()-1].setPresence_tete(true);
        } else {
            throw new DeplacementException();
        }
    }
    /**
     * Getter concernant le monde du joueur créé.
     * @return Le monde là où le joueur est créé.
     */
    public Monde getMonde() {
        return monde;
    }
    /**
     * Setter concernant le monde du joueur créé.
     * @param monde Le monde là ou le joueur sera créé.
     * @throws PlayerArgumentException Si le monde donné n'est pas initialisé ou qu'il est vide.
     */
    public void setMonde(Monde monde) throws PlayerArgumentException {
        if (monde == null) {
            throw new PlayerArgumentException();
        }
        this.monde = monde;
    }
    public int getVie() {
        return Vie;
    }
    public void setVie(int vie) throws VieJoueurException {
        if (vie < 0 || vie > 20) {
            throw new VieJoueurException();
        }
        Vie = vie;
    }
    public void enleverPointDeVie(int vieAEnlever) throws MontantVieException, VieJoueurException {
        if (vieAEnlever < 0 || vieAEnlever > 20) {
            throw new MontantVieException();
        }
        if (this.getVie() < vieAEnlever) {
            throw new MontantVieException();
        }
        // Sinon
        this.setVie(this.getVie() - vieAEnlever);
    }
    public void ajouterPointDeVie(int vieAAjouter) throws MontantVieException, VieJoueurException {
        if (vieAAjouter < 0 || vieAAjouter > 20) {
            throw new MontantVieException();
        }
        if ((this.getVie() + vieAAjouter) > 20) {
            throw new MontantVieException();
        }
        // Sinon
        this.setVie(this.getVie() + vieAAjouter);
    }
    /**
     * Fonction qui permet de savoir il y a combien d'occurence de l'objet obj dans l'inventaire de this.
     * @param obj L'objet concernant la recherche d'occurence.
     * @return Le nombre d'occurence de obj dans l'inventaire du joueur.
     * @throws InventoryException
     */
    public int getNbrObjetDansInventaire(Objets obj) throws InventoryException {
        // Récupérer le nombre d'occurence de l'objet obj dans l'inventaire du joueur
        int compteurOccu = 0;
        for (int i = 0; i < this.getInventaire().getTaille(); i++) {
            if (this.getInventaire().get(i).getClass() == obj.getClass()) {
                compteurOccu++;
            }
        }
        return compteurOccu;
    }
    /**
     * Fonction booléenne qui permet de connaitre le voisinage de this en lien avec le ramassage.
     * @param coordCaseRamassage La case qui est pointé par cette fonction et la vérification.
     * @return True si la case concernée est dans le voisinage de this.
     */
    public boolean blocDansVoisinageJoueurPourRamassage(Coord coordCaseRamassage) {
        boolean voisin = false;
        int x_case = coordCaseRamassage.getX();
        int y_case = coordCaseRamassage.getY();
        int x_joueur = this.getCoordonnees_joueur().getX();
        int y_joueur = this.getCoordonnees_joueur().getY();

        // Voisinage proche :
        if ((x_case <= x_joueur - 1 || x_case <= x_joueur + 1) && (y_case <= y_joueur - 1 || y_case <= y_joueur + 1)) {
            voisin = true;
        }
        return voisin;
    }

    /**
     * Fonction qui effectue l'action de ramassage dans une case précise.
     * @param caseRammassage Les coordonnées de la case concernée par l'action du ramassage.
     * @throws ListObjetsInexistantException
     * @throws CaseNonVoisineException Si la case concernée n'est pas dans le voisinage de this.
     */
    public void ramasserItems(Coord caseRammassage) throws ListObjetsInexistantException, CaseNonVoisineException {
        // On va récupérer le tab_monde
        Joueur joueur_concerne = this;
        Case[][] liste_cases = joueur_concerne.getMonde().getTab_monde();

        // Le joueur peut ramasser les objets dans sa case
        if (caseRammassage.equals(joueur_concerne.getCoordonnees_joueur())) {
            // On va récupérer le contenu de la case en question et ajouter ce contenu dans l'inventaire du Joueur
            for (int i = 0; i < liste_cases[caseRammassage.getY()][caseRammassage.getX()].getTaille(); i++) {
                Objets obj_case = liste_cases[joueur_concerne.getCoordonnees_joueur().getY()][joueur_concerne.getCoordonnees_joueur().getX()].getItems_au_sol().get(i);
                // On ajoute dans son inventaire
                joueur_concerne.getInventaire().addInventory(obj_case);
                // On n'oublie pas de vider la case
                liste_cases[joueur_concerne.getCoordonnees_joueur().getY()][joueur_concerne.getCoordonnees_joueur().getX()].removeObjets(i);
            }
        } else {
            // Si c'est bien une case voisine :
            if (blocDansVoisinageJoueurPourRamassage(caseRammassage)) {
                for (int i = 0; i < liste_cases[caseRammassage.getY()][caseRammassage.getX()].getTaille(); i++) {
                    Objets obj_case = liste_cases[caseRammassage.getY()][caseRammassage.getX()].getItems_au_sol().get(i);
                    // On ajoute dans son inventaire
                    joueur_concerne.getInventaire().addInventory(obj_case);
                }
                // On n'oublie pas de supprimer tous les objets qui se trouve dans la case :
                ArrayList<Objets> listm = liste_cases[caseRammassage.getY()][caseRammassage.getX()].getItems_au_sol();
                int nb_objets_a_supprimer = listm.size(); // nombre d'objets à supprimer
                int objets_supprimes = 0; // nombre d'objets déjà supprimés
                while (objets_supprimes < nb_objets_a_supprimer && !listm.isEmpty()) {
                    Objets obj_case = listm.get(0);
                    liste_cases[caseRammassage.getY()][caseRammassage.getX()].removeObjets(0);
                    objets_supprimes++;
                }
            } else {
                throw new CaseNonVoisineException();
            }
        }

    }
    /**
     * Fonction booléenne qui permet de connaitre le voisinage de this en lien avec le minage.
     * @param coordCaseMinage La case qui est pointé par cette fonction et la vérification.
     * @return True si la case concernée est dans le voisinage de this.
     */
    public boolean blocDansVoisinageJoueurPourMinage(Coord coordCaseMinage) {
        boolean voisin = false;
        int x_case = coordCaseMinage.getX();
        int y_case = coordCaseMinage.getY();
        int x_joueur = this.getCoordonnees_joueur().getX();
        int y_joueur = this.getCoordonnees_joueur().getY();

        // Voisinage proche :
        if ((x_case <= x_joueur - 1 || x_case <= x_joueur + 1) && (y_case <= y_joueur - 1 || y_case <= y_joueur + 1)) {
            voisin = true;
        }

        // Voisinage lointain (2 Blocs) :

        if ((x_case == x_joueur - 3 || x_case == x_joueur + 2) && (y_case == y_joueur - 2 || y_case == y_joueur + 2)
                || ((x_case == x_joueur - 3) && (y_case >= y_joueur - 1 && y_case <= y_joueur + 1)) ||
                ((x_case == x_joueur + 2) && (y_case >= y_joueur - 1 && y_case <= y_joueur + 1)) ||
                ((y_case == y_joueur - 2) && (x_case >= x_joueur - 2 && x_case <= x_joueur + 2)) ||
                ((y_case == y_joueur + 2) && (x_case >= x_joueur - 2 && x_case <= x_joueur + 2))) {
            // 8 Cas :
            // Cas 1 : Plage horizontale haut
            if (x_case == x_joueur - 3 && (y_case != y_joueur + 2 && y_case != y_joueur - 2)) {
                if (this.getMonde().getTab_monde()[y_case][x_case + 1].getContenu().isFluidite()) {
                    voisin = true;
                } else {
                    voisin = false;
                }
            }
            // Cas 2 : Plage horizontale bas
            if (x_case == x_joueur + 2 && (y_case != y_joueur + 2 && y_case != y_joueur - 2)) {
                if (this.getMonde().getTab_monde()[y_case][x_case - 1].getContenu().isFluidite()) {
                    voisin = true;
                } else {
                    voisin = false;
                }
            }
            // Cas 3 : Plage verticale gauche
            if (y_case == y_joueur - 2 && (x_case != x_joueur - 3 && x_case != x_joueur + 2)) {
                if (this.getMonde().getTab_monde()[y_case + 1][x_case].getContenu().isFluidite()) {
                    voisin = true;
                } else {
                    voisin = false;
                }
            }
            // Cas 4 : Plage verticale droite
            if (y_case == y_joueur + 2 && (x_case != x_joueur - 3 && x_case != x_joueur + 2)) {
                if (this.getMonde().getTab_monde()[y_case - 1][x_case].getContenu().isFluidite()) {
                    voisin = true;
                } else {
                    voisin = false;
                }
            }
            // Coins :
            // Coin haut gauche :
            if (x_case == x_joueur - 3 && y_case == y_joueur - 2) {
                if (this.getMonde().getTab_monde()[y_case + 1][x_case + 1].getContenu().isFluidite()) {
                    voisin = true;
                } else {
                    voisin = false;
                }
            }
            // Coin haut droit :
            if (x_case == x_joueur - 3 && y_case == y_joueur + 2) {
                if (this.getMonde().getTab_monde()[y_case - 1][x_case + 1].getContenu().isFluidite()) {
                    voisin = true;
                } else {
                    voisin = false;
                }
            }
            // Coin bas gauche :
            if (x_case == x_joueur + 2 && y_case == y_joueur - 2) {
                if (this.getMonde().getTab_monde()[y_case + 1][x_case - 1].getContenu().isFluidite()) {
                    voisin = true;
                } else {
                    voisin = false;
                }
            }
            // Coin bas droit :
            if (x_case == x_joueur + 2 && y_case == y_joueur + 2) {
                if (this.getMonde().getTab_monde()[y_case - 1][x_case - 1].getContenu().isFluidite()) {
                    voisin = true;
                } else {
                    voisin = false;
                }
            }
        }
        return voisin;
    }
    /**
     * Fonction qui effectue l'action de minage dans une case précise.
     * @param expert L'expert qui permet de savoir si this est capable de miner le bloc en question.
     * @param caseMinage Les coordonnées de la case concernée par l'action de minage.
     * @throws Exception Si la COR est vide ou que la case en question ne se trouve pas dans le voisinage de this.
     */
    public void minerBloc(Expert expert, Coord caseMinage) throws Exception {
        if (expert != null) {
            // Travail avec la COR :
            Objets mainJoueur = this.getMain();
            Case[][] caseViseParJoueur = this.getMonde().getTab_monde();
            Objets blocVise = caseViseParJoueur[caseMinage.getY()][caseMinage.getX()].getContenu();

            // Le résultat de expert :
            try {
                // On défini le voisinage proche pour le minage
                // Si c'est bien dans le voisinage du joueur :
                if (blocDansVoisinageJoueurPourMinage(caseMinage)) {
                    Objets res = expert.expertiser(mainJoueur, blocVise);
                    // On doit remplacer le bloc par de l'air s'il peut bien miner
                    if (res != null) {
                        this.getMonde().getTab_monde()[caseMinage.getY()][caseMinage.getX()].setContenu(new BlocAir());
                        // On doit aussi ajouter le bloc en question dans la liste des items au sol de la case
                        this.getMonde().getTab_monde()[caseMinage.getY()][caseMinage.getX()].addItemsAuSol(res);
                    } else {
                        // Le bloc est que même cassé mais rien de drop
                        this.getMonde().getTab_monde()[caseMinage.getY()][caseMinage.getX()].setContenu(new BlocAir());
                    }
                } else {
                    throw new CaseNonVoisineException();
                }
            } catch (ExpertManquantException e) {}
        } else {
            throw new CORVideException();
        }
    }
    /**
     * Fonction qui effectue l'action de fabrication d'un item en fonction d'une recette donnée.
     * @param expertRecette L'expert qui permet de savoir si this est capable de fabriquer l'item en question.
     * @param recette La recette qui permet de fabriquer un item voulu.
     * @throws Exception Si la COR est vide ou si le joueur n'a pas les items dans son inventaire de la recette donnée.
     */
    public void effectuerRecette(ExpertCraft expertRecette, ArrayList<Objets> recette) throws Exception {
        if (expertRecette != null) {
            ArrayList<Objets> inv_Joueur = this.getInventaire().getListItems();
            ArrayList<Objets> objetsNoAir = new ArrayList<Objets>();
            // Avant tout de chose, on va vérifier que le joueur possède les éléments qui se trouve dans sa recette donnée
            for (int i = 0; i < recette.size(); i++) {
                // On va éviter toute comparaison avec les blocs d'air
                if (!(recette.get(i) instanceof BlocAir)) {
                    objetsNoAir.add(recette.get(i));
                }
            }
            // Première vérification :
            if (this.getInventaire().getTaille() < objetsNoAir.size()) {
                throw new InventoryException();
            }
            // Il y a le même nombre d'éléments, mais maintenant il faut vérifier si les éléments de objetsNoAir sont dans son inventaire
            for (Objets obj : objetsNoAir) {
                if (!inv_Joueur.contains(obj)) {
                    throw new InventoryException();
                }
            }
            // Le Joueur à bien tous les éléments dans son inventaire
            // Le travail de l'expert
            try {
                ArrayList<Objets> res_recette = expertRecette.expertiserCraft(recette);
                // On doit supprimer de l'inventaire du Joueur les éléments dans la recette :
                for (int i = 0; i < objetsNoAir.size(); i++) {
                    inv_Joueur.remove(objetsNoAir.get(i));
                }
                // On doit parcourir l'arraylist et ajouter chaque élément de celle-ci dans l'inventaire du Joueur
                for (int j = 0; j < res_recette.size(); j++) {
                    this.getInventaire().addInventory(res_recette.get(j));
                }
            } catch (ExpertManquantException e) {
                System.out.printf("\nIl n'y a pas d'expert pour cette recette : %s", recette);
            }
        } else {
            throw new CORVideException();
        }
    }
    /**
     * Procédure qui effectue l'action de déplacement vers la droite.
     * @throws CoordException
     * @throws PlayerArgumentException
     * @throws DeplacementException
     */
    public void allerDroite() throws CoordException, PlayerArgumentException, DeplacementException {
        Coord nouvelleCoordonnees = new Coord(this.getCoordonnees_joueur().getX(), this.getCoordonnees_joueur().getY()+1);
        this.setCoordonnees_joueur(nouvelleCoordonnees);
    }
    /**
     * Procédure qui effectue l'action de déplacement vers la gauche.
     * @throws CoordException
     * @throws PlayerArgumentException
     * @throws DeplacementException
     */
    public void allerGauche() throws CoordException, PlayerArgumentException, DeplacementException {
        Coord nouvelleCoordonnees = new Coord(this.getCoordonnees_joueur().getX(), this.getCoordonnees_joueur().getY()-1);
        this.setCoordonnees_joueur(nouvelleCoordonnees);
    }
    /**
     * Procédure qui effectue l'action de déplacement vers le haut.
     * @throws CoordException
     * @throws PlayerArgumentException
     * @throws DeplacementException
     */
    public void allerHaut() throws CoordException, PlayerArgumentException, DeplacementException {
        Coord nouvelleCoordonnees = new Coord(this.getCoordonnees_joueur().getX()-1, this.getCoordonnees_joueur().getY());
        this.setCoordonnees_joueur(nouvelleCoordonnees);
    }
    /**
     * Procédure qui effectue l'action de déplacement vers le bas.
     * @throws CoordException
     * @throws PlayerArgumentException
     * @throws DeplacementException
     */
    public void allerBas() throws CoordException, PlayerArgumentException, DeplacementException {
        Coord nouvelleCoordonnees = new Coord(this.getCoordonnees_joueur().getX()+1, this.getCoordonnees_joueur().getY());
        this.setCoordonnees_joueur(nouvelleCoordonnees);
    }
    /**
     * Procédure qui effectue l'action de déplacement vers le haut et la gauche.
     * @throws CoordException
     * @throws PlayerArgumentException
     * @throws DeplacementException
     */
    public void allerHautGauche() throws CoordException, PlayerArgumentException, DeplacementException {
        Coord nouvelleCoordonnees = new Coord(this.getCoordonnees_joueur().getX()-1, this.getCoordonnees_joueur().getY()-1);
        this.setCoordonnees_joueur(nouvelleCoordonnees);
    }
    /**
     * Procédure qui effectue l'action de déplacement vers le haut et la droite.
     * @throws CoordException
     * @throws PlayerArgumentException
     * @throws DeplacementException
     */
    public void allerHautDroite() throws CoordException, PlayerArgumentException, DeplacementException {
        Coord nouvelleCoordonnees = new Coord(this.getCoordonnees_joueur().getX()-1, this.getCoordonnees_joueur().getY()+1);
        this.setCoordonnees_joueur(nouvelleCoordonnees);
    }
    /**
     * Procédure qui effectue l'action de déplacement vers le bas et la gauche.
     * @throws CoordException
     * @throws PlayerArgumentException
     * @throws DeplacementException
     */
    public void allerBasGauche() throws CoordException, PlayerArgumentException, DeplacementException {
        Coord nouvelleCoordonnees = new Coord(this.getCoordonnees_joueur().getX()+1, this.getCoordonnees_joueur().getY()-1);
        this.setCoordonnees_joueur(nouvelleCoordonnees);
    }
    /**
     * Procédure qui effectue l'action de déplacement vers le bas et la droite.
     * @throws CoordException
     * @throws PlayerArgumentException
     * @throws DeplacementException
     */
    public void allerBasDroite() throws CoordException, PlayerArgumentException, DeplacementException {
        Coord nouvelleCoordonnees = new Coord(this.getCoordonnees_joueur().getX()+1, this.getCoordonnees_joueur().getY()+1);
        this.setCoordonnees_joueur(nouvelleCoordonnees);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joueur joueur = (Joueur) o;
        return Objects.equals(nom, joueur.nom) && Objects.equals(coordonnees_joueur, joueur.coordonnees_joueur) && Objects.equals(monde, joueur.monde);
    }
    @Override
    public String toString() {
        return "Joueur{" +
                "nom='" + nom + '\'' +
                ", inventaire=" + inventaire +
                ", main=" + main +
                ", coordonnees_joueur=" + coordonnees_joueur +
                ", monde=" + monde +
                '}';
    }
}
