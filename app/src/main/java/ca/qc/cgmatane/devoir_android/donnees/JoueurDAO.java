package ca.qc.cgmatane.devoir_android.donnees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.devoir_android.modele.Joueur;

public class JoueurDAO {

    private static JoueurDAO instance = null;
    protected List<HashMap<String, String>> listeJoueur;

    public JoueurDAO() {
        listeJoueur = new ArrayList<HashMap<String, String>>();
        preparerListeJoueur();
    }

    public static JoueurDAO getInstance() {
        if (null == instance)
            instance = new JoueurDAO();
        return instance;
    }

    private void preparerListeJoueur() {

        HashMap<String, String> joueur;

        joueur = new HashMap<String, String>();
        joueur.put("nom", "Durant");
        joueur.put("poste", "Ailier");
        listeJoueur.add(joueur);

        joueur = new HashMap<String, String>();
        joueur.put("nom", "Westbrook");
        joueur.put("poste", "Meneur");
        listeJoueur.add(joueur);

        joueur = new HashMap<String, String>();
        joueur.put("nom", "Gobert");
        joueur.put("poste", "Pivot");
        listeJoueur.add(joueur);

    }

    public List<HashMap<String, String>> recupererListeJoueur() {
        return listeJoueur;
    }
}
