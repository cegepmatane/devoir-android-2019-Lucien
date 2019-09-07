package ca.qc.cgmatane.devoir_android.donnees;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.devoir_android.modele.Joueur;

public class JoueurDAO {

    private static JoueurDAO instance = null;
    protected List<Joueur> listeJoueur;

    private BaseDeDonnees accesseurBaseDeDonnees;

    public JoueurDAO() {
        this.accesseurBaseDeDonnees = BaseDeDonnees.getInstance();
        listeJoueur = new ArrayList<Joueur>();
        //preparerListeJoueur();
    }

    public static JoueurDAO getInstance() {

        if (null == instance)
            instance = new JoueurDAO();
        return instance;
    }

    public List<Joueur> listerJoueur() {

        String LISTER_JOUEURS = "SELECT * FROM joueur";
        Cursor curseur = accesseurBaseDeDonnees.getReadableDatabase().rawQuery(LISTER_JOUEURS, null);
        this.listeJoueur.clear();
        Joueur joueur;

        int indexId_joueur = curseur.getColumnIndex("id_joueur");
        int indexAuteur = curseur.getColumnIndex("nom");
        int indexTitre = curseur.getColumnIndex("poste");

        for (curseur.moveToFirst();!curseur.isAfterLast();curseur.moveToNext()) {
            int id_joueur = curseur.getInt(indexId_joueur);
            String nom = curseur.getString(indexAuteur);
            String poste = curseur.getString(indexTitre);
            joueur = new Joueur(nom, poste, id_joueur);
            this.listeJoueur.add(joueur);
        }

        return listeJoueur;
    }

    public List<HashMap<String,String>> recupererListeJoueurPourAdapteur() {

        List<HashMap<String,String>> listeJoueurPourAdapteur =
                new ArrayList<HashMap<String, String>>();

        listerJoueur();

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
        SQLiteDatabase db = accesseurBaseDeDonnees.getWritableDatabase();
        SQLiteStatement query = db.compileStatement("INSERT INTO joueur(id_joueur, nom, poste) VALUES(null,?,?)");
        query.bindString(1, joueur.getNom());
        query.bindString(2, joueur.getPoste());
        query.execute();
    }

    private void preparerListeJoueur() {

        listeJoueur.add(new Joueur("George", "ailier", 1));
        listeJoueur.add(new Joueur("Gobert", "pivot", 2));
        listeJoueur.add(new Joueur("James", "arriere", 3));
    }
}
