package fr.faget.noir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Jeu.Jeu;
import Jeu.Phrase;


public class JeuActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        //Views
        final TextView tVPhrase = findViewById(R.id.txt_phrase);
        final Button btnDroit = findViewById(R.id.b_Droite);
        final Button btnGauche = findViewById(R.id.b_Gauche);

        //Variables
        Context context=this;
        final Jeu jeu = new Jeu(context);


        //Désactivation du bouton de gauche
        btnGauche.setEnabled(false);

        //Affichage de la première phrase
        jeu.afficherPhrase(tVPhrase);

        btnDroit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Si le bouton de gauche est désactivé, on l'active
                if (!btnGauche.isEnabled()){
                    btnGauche.setEnabled(true);
                }
                if (!Phrase.isEmpty()) {
                    if (jeu.getNbClickG() == 0) {
                        //Affichage d'une phrase à chaque clique
                        jeu.afficherPhrase(tVPhrase);
                    } else {
                        jeu.reAfficherPhrases(tVPhrase);
                    }
                }else {
                    //Afficher phrase de fin
                    tVPhrase.setText(getResources().getText(R.string.phraseFin));
                    //On désactive la partie droite car plus aucune action ne peut être effectuée
                    btnDroit.setEnabled(false);
                    //Indique que la phrase de fin est affichée
                    jeu.setFini(true);
                }
            }
        });

        btnGauche.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Si le bouton de gauche est désactivé, on l'active
                if (!btnDroit.isEnabled()){
                    btnDroit.setEnabled(true);
                }
                if (jeu.getOldPhrases().isEmpty()){
                    btnGauche.setEnabled(false);
                } else{
                    jeu.afficherAnciennePhrases(tVPhrase);
                }
            }
        });
    }
}