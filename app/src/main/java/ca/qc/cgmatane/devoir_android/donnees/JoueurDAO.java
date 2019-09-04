package ca.qc.cgmatane.devoir_android.donnees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.devoir_android.modele.Joueur;

public class JoueurDAO {

    private static JoueurDAO instance = null;
    protected List<Joueur> listeJoueur;

    public JoueurDAO() {
        listeJoueur = new ArrayList<Joueur>();
        preparerListeJoueur();
    }

    public static JoueurDAO getInstance() {

        if (null == instance)
            instance = new JoueurDAO();
        return instance;
    }

    public List<HashMap<String,String>> recupererListeJoueurPourAdapteur() {

        List<HashMap<String,String>> listeJoueurPourAdapteur =
                new ArrayList<HashMap<String, String>>();

        //listerJoueur()

        for (Joueur joueur :
                listeJoueur) {
            listeJoueurPourAdapteur.add(joueur.obtenirJoueurPourAdapteur());
        }
        return listeJoueurPourAdapteur;
    }

    public Joueur chercherJoueurParId(int id_joueur) {

        for (Joueur joueurRecherche :
                this.listeJoueur) {
            if (joueurRecherche.getId_joueur() == id_joueur)
                return joueurRecherche;
        }
        return null;
    }

    public void modifierJoueur(Joueur joueur) {

        for (Joueur joueurRecherche :
                this.listeJoueur) {
            if (joueurRecherche.getId_joueur() == joueur.getId_joueur()) {
                joueurRecherche.setNom(joueur.getNom());
                joueurRecherche.setPoste(joueur.getPoste());
            }
        }
    }

    public List<Joueur> recupererListeJoueur() {
        return listeJoueur;
    }

    public void ajouterJoueur(Joueur joueur) {
        listeJoueur.add(joueur);
    }

    private void preparerListeJoueur() {

        listeJoueur.add(new Joueur("George", "ailier", 1));
        listeJoueur.add(new Joueur("Gobert", "pivot", 2));
        listeJoueur.add(new Joueur("James", "arriere", 3));
    }
}
