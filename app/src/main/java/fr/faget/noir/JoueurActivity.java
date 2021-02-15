package fr.faget.noir;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import Jeu.Joueur;


public class JoueurActivity extends AppCompatActivity {

    ImageView boutonPlus; //Image clicable qui représente un '+' et qui permet d'ajouter des joueurs
    ImageView boutonCommencer;//Boutton permettant de lancer la partie

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Ouverture du clavier à l'ouverture de l'activité
        final InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joueur);
        ListView listJoueurs = findViewById(R.id.ListJoueurs);
        final EditText Input = findViewById(R.id.txtEdit_Joueur); //Widget permettant à l'utilisateur d'entrer une valeur
        Input.requestFocus(); //Permet de focus l'EditText à l'ouverture de l'activité

        final ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, R.layout.police, Joueur.getJoueurs());
        listJoueurs.setAdapter(listAdapter);

        boutonPlus = findViewById(R.id.img_Plus);

        boutonCommencer = findViewById(R.id.img_Fleche);


//============================================= CLICK SUR '+' =================================================
        this.boutonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Réccupération du nom du nouveau Joueur
                String nouveauJoueur = Input.getText().toString();

                //Si la chaine de caractère n'est pas vide et que le nom n'est pas entré en doublon, on ajoute le nom et on l'affiche
                   if((!nouveauJoueur.isEmpty()) && (!Joueur.getJoueurs().contains(nouveauJoueur))) {
                       //Ajout du joueur dans la liste
                       Joueur.ajouterJoueur(nouveauJoueur);
                       listAdapter.notifyDataSetChanged();

                       //Modification de l'EditText
                       Input.setHint("Joueur " + (Joueur.nbJoueurs()+1));
                       Input.setText("");
                   }
        }
        });


//============================================ CLICK SUR "FLECHE" =========================================
        this.boutonCommencer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PhraseActivity = new Intent(getApplicationContext(), PhrasesActivity.class);

                //Lancement de la nouvelle activité
                startActivity(PhraseActivity);
            }
        });
    }
}