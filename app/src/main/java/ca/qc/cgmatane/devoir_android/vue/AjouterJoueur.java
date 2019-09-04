package ca.qc.cgmatane.devoir_android.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import ca.qc.cgmatane.devoir_android.R;
import ca.qc.cgmatane.devoir_android.donnees.JoueurDAO;
import ca.qc.cgmatane.devoir_android.modele.Joueur;

public class AjouterJoueur extends AppCompatActivity {

    protected EditText vueAjouterJoueurChampNom;
    protected EditText getVueAjouterJoueurChampPoste;

    protected JoueurDAO accesseurJoueur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_ajouter_joueur);

        vueAjouterJoueurChampNom = (EditText) findViewById(R.id.vue_ajouter_joueur_champ_nom);
        getVueAjouterJoueurChampPoste = (EditText) findViewById(R.id.vue_ajouter_joueur_champ_poste);

        Button vueAjouterJoueurActionEnregistrer =
                (Button) findViewById(R.id.vue_ajouter_joueur_action_enregistrer);

        vueAjouterJoueurActionEnregistrer.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        enregistrerJoueur();
                    }
                }
        );
    }

    private void enregistrerJoueur() {

        Toast message = Toast.makeText(getApplicationContext(),
                "Nom " + vueAjouterJoueurChampNom.getText().toString(),
                Toast.LENGTH_SHORT);

        message.show();

        accesseurJoueur = JoueurDAO.getInstance();

        accesseurJoueur.ajouterJoueur(new Joueur(vueAjouterJoueurChampNom.getText().toString(),
                getVueAjouterJoueurChampPoste.getText().toString(),
                0));

        naviguerRetourClub();
    }

    private void naviguerRetourClub() {
        this.finish();
    }
}