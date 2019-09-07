package ca.qc.cgmatane.devoir_android.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ca.qc.cgmatane.devoir_android.R;
import ca.qc.cgmatane.devoir_android.donnees.JoueurDAO;
import ca.qc.cgmatane.devoir_android.modele.Joueur;

public class ModifierJoueur extends AppCompatActivity {

    protected JoueurDAO accesseurJoueur;
    protected Joueur joueur;

    protected EditText vueModifierJoueurChampPoste;
    protected EditText vueModifierJoueurChampNom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_modifier_joueur);

        Bundle parametres = this.getIntent().getExtras();
        String parametreId_joueur = (String) parametres.get("id_joueur");
        int idJoueur = Integer.parseInt(parametreId_joueur);

        this.accesseurJoueur = JoueurDAO.getInstance();

        joueur = accesseurJoueur.chercherJoueurParId(idJoueur);

        vueModifierJoueurChampPoste = (EditText) findViewById(R.id.vue_modifier_joueur_champ_poste);
        vueModifierJoueurChampNom = (EditText) findViewById(R.id.vue_modifier_joueur_champ_nom);

        vueModifierJoueurChampPoste.setText(joueur.getPoste());
        vueModifierJoueurChampNom.setText(joueur.getNom());

        Button vueModifierJoueurActionEnregistrer =
                (Button) findViewById(R.id.vue_modifier_joueur_action_enregistrer);

        vueModifierJoueurActionEnregistrer.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        enregistrerJoueur();
                    }
                }
        );
    }

    private void enregistrerJoueur() {

        /*
        Toast message = Toast.makeText(getApplicationContext(),
                "Poste " + vueModifierJoueurChampPoste.getText().toString(),
                Toast.LENGTH_SHORT);

        message.show();

         */
        joueur.setNom(vueModifierJoueurChampNom.getText().toString());
        joueur.setPoste(vueModifierJoueurChampPoste.getText().toString());

        accesseurJoueur.modifierJoueur(joueur);

        naviguerRetourClub();
    }

    private void naviguerRetourClub() {
        this.finish();
    }
}
