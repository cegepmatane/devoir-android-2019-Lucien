package ca.qc.cgmatane.devoir_android.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.devoir_android.R;
import ca.qc.cgmatane.devoir_android.donnees.JoueurDAO;

public class Club extends AppCompatActivity {

    static final public int ACTIVITE_AJOUTER_JOUEUR = 1;
    static final public int ACTIVITE_MODIFIER_JOUEUR = 2;

    protected ListView vueClubListeJoueur;
    protected List<HashMap<String, String>> listeJoueur;
    
    protected JoueurDAO accesseurJoueur;
    
    protected Intent intentionNaviguerAjouterJoueur;
    protected Intent intentionNaviguerModifierJoueur;

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

    protected void onActivityResult(int activite, int resultat, Intent donnees) {
        switch (activite) {
            case ACTIVITE_AJOUTER_JOUEUR:
                afficherToutLesJoueurs();
                break;
            case ACTIVITE_MODIFIER_JOUEUR:
                afficherToutLesJoueurs();
                break;
        }
    }

    protected void afficherToutLesJoueurs() {

        vueClubListeJoueur = (ListView) findViewById(R.id.vue_club_liste_joueur);

        accesseurJoueur = JoueurDAO.getInstance();

        listeJoueur = accesseurJoueur.recupererListeJoueurPourAdapteur()    ;

        SimpleAdapter adapteurVueListeJoueur = new SimpleAdapter(
                this,
                listeJoueur,
                android.R.layout.two_line_list_item,
                new String[] {"nom", "poste"},
                new int[] {android.R.id.text1, android.R.id.text2});

        vueClubListeJoueur.setAdapter(adapteurVueListeJoueur);

        vueClubListeJoueur.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(
                            AdapterView<?> parent,
                            View vue,
                            int positionDansAdapteur,
                            long positionItem) {

                        Log.d("Club", "onItemClick");
                        ListView vueClubListeJoueurOnClick = (ListView)vue.getParent();

                        @SuppressWarnings("unchecked")
                        HashMap<String, String> joueur =
                                (HashMap<String, String>)vueClubListeJoueurOnClick.getItemAtPosition((int)positionItem);

                        /*
                        Toast message = Toast.makeText(
                                getApplicationContext(),
                                ""+joueur.get("id_joueur") + " " + joueur.get("titre"),
                                Toast.LENGTH_SHORT);
                        message.show();

                        Log.d("Club", "onItemClick Position: " + positionItem);
                        Log.d("Club", "onItemClick Titre : " + joueur.get("titre"));

                         */

                        intentionNaviguerModifierJoueur =
                                new Intent(Club.this, ModifierJoueur.class);
                        intentionNaviguerModifierJoueur.putExtra("id_joueur", joueur.get("id_joueur"));
                        startActivityForResult(intentionNaviguerModifierJoueur, ACTIVITE_MODIFIER_JOUEUR);
                    }
                }
        );
    }
}

