package ca.qc.cgmatane.devoir_android.modele;

import java.util.HashMap;

public class Joueur {

    protected String nom;
    protected String poste;
    protected int id_joueur;

    public Joueur(String nom, String age, int id_joueur) {
        this.nom = nom;
        this.poste = age;
        this.id_joueur = id_joueur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public int getId_joueur() {
        return id_joueur;
    }

    public void setId_joueur(int id_joueur) {
        this.id_joueur = id_joueur;
    }

    public HashMap<String, String> obtenirJoueurPourAdapteur() {
        HashMap<String,String> joueurPourAdapteur = new HashMap<String, String>();
        joueurPourAdapteur.put("nom", this.nom);
        joueurPourAdapteur.put("poste",this.poste);
        joueurPourAdapteur.put("id_joueur",this.id_joueur + "");
        return joueurPourAdapteur;
    }
}
