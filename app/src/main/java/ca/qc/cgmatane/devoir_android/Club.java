package ca.qc.cgmatane.devoir_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Club extends AppCompatActivity {

    protected ListView vueListeJoueur;
    protected List<HashMap<String, String>> listeJoueur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_club);

        vueListeJoueur = (ListView) findViewById(R.id.vue_club_liste_joueur);

        listeJoueur = preparerListeJoueur();

        SimpleAdapter adapteurVueListeJoueur = new SimpleAdapter(
                this,
                listeJoueur,
                android.R.layout.two_line_list_item,
                new String[] {"nom", "poste"},
                new int[] {android.R.id.text1, android.R.id.text2});

        vueListeJoueur.setAdapter(adapteurVueListeJoueur);
    }

    private List<HashMap<String, String>> preparerListeJoueur() {

        List<HashMap<String, String>> listeJoueur = new ArrayList<HashMap<String, String>>();

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

        return listeJoueur;
    }
}

