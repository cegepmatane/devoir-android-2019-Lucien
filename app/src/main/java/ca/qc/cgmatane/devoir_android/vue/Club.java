package ca.qc.cgmatane.devoir_android.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.devoir_android.R;
import ca.qc.cgmatane.devoir_android.donnees.JoueurDAO;

public class Club extends AppCompatActivity {

    static final public int ACTIVITE_AJOUTER_JOUEUR = 1;

    protected ListView vueListeJoueur;
    protected List<HashMap<String, String>> listeJoueur;
    protected JoueurDAO accesseurJoueur;
    protected Intent intentionNaviguerAjouterJoueur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_club);
        afficherToutLesJoueurs();

        intentionNaviguerAjouterJoueur = new Intent(this, AjouterJoueur.class);

        Button vueClubActionNaviguerAjouterJoueur =
                (Button) findViewById(R.id.vue_club_action_naviguer_ajouter_joueur);

        vueClubActionNaviguerAjouterJoueur.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivityForResult(intentionNaviguerAjouterJoueur, Club.ACTIVITE_AJOUTER_JOUEUR);
                    }
                }
        );
    }

    protected void afficherToutLesJoueurs() {

        vueListeJoueur = (ListView) findViewById(R.id.vue_club_liste_joueur);

        accesseurJoueur = JoueurDAO.getInstance();

        listeJoueur = accesseurJoueur.recupererListeJoueur();

        SimpleAdapter adapteurVueListeJoueur = new SimpleAdapter(
                this,
                listeJoueur,
                android.R.layout.two_line_list_item,
                new String[] {"nom", "poste"},
                new int[] {android.R.id.text1, android.R.id.text2});

        vueListeJoueur.setAdapter(adapteurVueListeJoueur);
    }
}

