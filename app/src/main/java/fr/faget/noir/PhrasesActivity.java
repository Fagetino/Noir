package fr.faget.noir;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import Jeu.Phrase;


public class PhrasesActivity extends AppCompatActivity {

    ImageView boutonPlus; //Image clicable qui représente un '+' et qui permet d'ajouter des phrases
    ImageView boutonFleche;//Boutton permettant de lancer la partie

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Ouverture du clavier à l'ouverture de l'activité
        final InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);
        ListView listPhrases = findViewById(R.id.ListPhrases);
        final EditText Input = findViewById(R.id.txtEdit_Phrase); //Widget permettant à l'utilisateur d'entrer une valeur
        Input.requestFocus(); //Permet de focus l'EditText à l'ouverture de l'activité

        final ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, R.layout.police, Phrase.getPhrases());
        listPhrases.setAdapter(listAdapter);

        boutonPlus = findViewById(R.id.img_Plus);

        boutonFleche = findViewById(R.id.img_Fleche);


//============================================= CLICK SUR '+' =================================================
        this.boutonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Réccupération de la nouvelle phrase
                String nouvellePhrase = Input.getText().toString();

                //Si la chaine de caractère n'est pas vide et que le nom n'est pas entré en doublon, on ajoute le nom et on l'affiche
                if((!nouvellePhrase.isEmpty()) && (!Phrase.getPhrases().contains(nouvellePhrase))) {
                    //Ajout du phrase dans la liste
                    Phrase.ajouterPhrase(nouvellePhrase);
                    listAdapter.notifyDataSetChanged();

                    //Modification de l'EditText
                    Input.setHint("Phrase " + (Phrase.nbPhrases()+1));
                    Input.setText("");
                }
            }
        });


//============================================ CLICK SUR "FLECHE" =========================================
        this.boutonFleche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cB_phraseParDefaut = findViewById(R.id.cB_phraseParDefaut);
                //On vérifie si la checkBox est checker et on ajoute les phrases par defaut si elle l'est.
                if (cB_phraseParDefaut.isChecked()){
                    Phrase.ajouterPhrases(getResources().getStringArray(R.array.sA_phrases));
                }

                Intent JeuActivity = new Intent(getApplicationContext(), JeuActivity.class);

                //Lancement de la nouvelle activité
                startActivity(JeuActivity);
            }
        });
    }
}