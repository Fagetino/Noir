package fr.faget.noir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import Jeu.Joueur;
import Jeu.Phrase;


public class JeuActivity extends AppCompatActivity {

    private int nbClickG = 0; //Permet de verifier combien de fois on a cliquer à gauche après avoir cliqué à droite
    private boolean clickD = false; //vrai si c'est le premier clique à droite après avoir cliqué à gauche
    private int indexPhrase; //index de la phrase à afficher
    private String phraseAffichee; //Phrase affichee

    public int nombreAleatoire(int borneMax){
        return (int)(Math.random()*borneMax);
    }

    public void afficherPhrase(TextView textView, ArrayList<String> oldPhrase){
        String occurence; //occurences trouvées dans la phrase à afficher
        int indexJoueurX, indexJoueurY, indexJoueurZ; //Index des joueurs tirés au hazard
        String joueurX, joueurY; //Permet d'enregistrer le joueur tirer au hazard

        if (!Phrase.isEmpty()) {
            indexPhrase = nombreAleatoire(Phrase.nbPhrases());
            phraseAffichee = Phrase.getPhrase(indexPhrase);
            occurence = Phrase.rechercheOccurence(indexPhrase);

            switch (occurence) {
                case "X":
                    //Reccuperation du JoueurX
                    indexJoueurX = nombreAleatoire(Joueur.nbJoueurs());
                    //Modification de la phrase avec le nom du JoueurX
                    Phrase.setPhrase(indexPhrase, phraseAffichee.replaceAll("X", Joueur.getJoueur(indexJoueurX)));
                    phraseAffichee = Phrase.getPhrase(indexPhrase);
                    //Affichage de la phrase
                    textView.setText(phraseAffichee);
                    //Sauvegarde de la phrase affichée
                    oldPhrase.add(phraseAffichee);
                    //Supression de la phrase affichée de la liste des phrases
                    Phrase.supprimerPhrase(indexPhrase);
                    break;

                case "XY":
                    //Reccuperation du JoueurX
                    indexJoueurX = nombreAleatoire(Joueur.nbJoueurs());
                    joueurX = Joueur.getJoueur(indexJoueurX);
                    //Modification de la phrase avec le nom du JoueurX
                    Phrase.setPhrase(indexPhrase, phraseAffichee.replaceAll("X", joueurX));
                    phraseAffichee = Phrase.getPhrase(indexPhrase);
                    //Suppression du JoueurX pour ne pas le retirer au hazard
                    Joueur.supprimerJoueur(indexJoueurX);
                    //Reccuperation du JoueurY
                    indexJoueurY = nombreAleatoire(Joueur.nbJoueurs());
                    //Modification de la phrase avec le nom du JoueurY
                    Phrase.setPhrase(indexPhrase, phraseAffichee.replaceAll("Y", Joueur.getJoueur(indexJoueurY)));
                    phraseAffichee = Phrase.getPhrase(indexPhrase);
                    //On remet le JoueurX dans la liste des joueur
                    Joueur.ajouterJoueur(joueurX);
                    //Affichage de la phrase
                    textView.setText(phraseAffichee);
                    //Sauvegarde de la phrase affichée
                    oldPhrase.add(phraseAffichee);
                    //Supression de la phrase affichée de la liste des phrases
                    Phrase.supprimerPhrase(indexPhrase);
                    break;

                case "XYZ":
                    //Reccuperation du JoueurX
                    indexJoueurX = nombreAleatoire(Joueur.nbJoueurs());
                    joueurX = Joueur.getJoueur(indexJoueurX);
                    //Modification de la phrase avec le nom du JoueurX
                    Phrase.setPhrase(indexPhrase, phraseAffichee.replaceAll("X", joueurX));
                    phraseAffichee = Phrase.getPhrase(indexPhrase);
                    //Suppression du JoueurX pour ne pas le retirer au hazard
                    Joueur.supprimerJoueur(indexJoueurX);
                    //Reccuperation du JoueurY
                    indexJoueurY = nombreAleatoire(Joueur.nbJoueurs());
                    joueurY = Joueur.getJoueur(indexJoueurX);
                    //Modification de la phrase avec le nom du JoueurY
                    Phrase.setPhrase(indexPhrase, phraseAffichee.replaceAll("Y", joueurY));
                    phraseAffichee = Phrase.getPhrase(indexPhrase);
                    //Suppression du JoueurY pour ne pas le retirer au hazard
                    Joueur.supprimerJoueur(indexJoueurY);
                    //Reccuperation du JoueurZ
                    indexJoueurZ = nombreAleatoire(Joueur.nbJoueurs());
                    //Modification de la phrase avec le nom du JoueurZ
                    Phrase.setPhrase(indexPhrase, phraseAffichee.replaceAll("Z", Joueur.getJoueur(indexJoueurZ)));
                    phraseAffichee = Phrase.getPhrase(indexPhrase);
                    //On remet le JoueurX et le JoueurY dans la liste des joueur
                    Joueur.ajouterJoueur(joueurX);
                    Joueur.ajouterJoueur(joueurY);
                    //Affichage de la phrase
                    textView.setText(phraseAffichee);
                    //Sauvegarde de la phrase affichée
                    oldPhrase.add(phraseAffichee);
                    //Supression de la phrase affichée de la liste des phrases
                    Phrase.supprimerPhrase(indexPhrase);
                    break;

                case "pasDeJoueur":
                    //Supression de la phrase affichée de la liste des phrases
                    Phrase.supprimerPhrase(indexPhrase);
                    //On affiche une autre phrase
                    afficherPhrase(textView, oldPhrase);
                    break;

                default:
                    //Affichage de la phrase
                    textView.setText(phraseAffichee);
                    //Sauvegarde de la phrase affichée
                    oldPhrase.add(phraseAffichee);
                    //Supression de la phrase affichée de la liste des phrases
                    Phrase.supprimerPhrase(indexPhrase);
            } //end case
        } else {
            //Afficher phrase de fin
            textView.setText(getResources().getText(R.string.phraseFin));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        //Views
        final TextView tVPhrase = findViewById(R.id.txt_phrase);
        final Button btnDroit = findViewById(R.id.b_Droite);
        final Button btnGauche = findViewById(R.id.b_Gauche);

        //Variables
        final ArrayList<String> oldPhrases = new ArrayList<>();


        //Désactivation du bouton de gauche
        btnGauche.setEnabled(false);

        //Affichage de la première phrase
        afficherPhrase(tVPhrase, oldPhrases);

        btnDroit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Si le bouton de gauche est désactivé, on l'active
                if (!btnGauche.isEnabled()){
                    btnGauche.setEnabled(true);
                }
                if (!Phrase.isEmpty()) {
                    if (nbClickG == 0) {
                        //Affichage d'une phrase à chaque clique
                        afficherPhrase(tVPhrase, oldPhrases);
                    } else {
                        //Si c'est le premier clique sur la partie droite après avoir cliquer sur la partie gauche
                        //on ajoute la derniere phrase de la lise aux phrases déjà affichée et on la supprime de la liste
                        //des phrases à affichée car elle l'est déjà.
                        if (!clickD){
                            oldPhrases.add(Phrase.getPhrase(Phrase.nbPhrases()-1));
                            Phrase.supprimerPhrase(Phrase.nbPhrases()-1);
                        }
                        //Récupération de la phrase à afficher
                        indexPhrase = Phrase.nbPhrases() - 1;
                        phraseAffichee = Phrase.getPhrase(indexPhrase);
                        //Affichage de la phrase
                        tVPhrase.setText(phraseAffichee);
                        //On met la phrase affichée dans les phrases déjà affichée
                        oldPhrases.add(phraseAffichee);
                        Phrase.supprimerPhrase(indexPhrase);
                        //On remet le nombre de clique sur la partie gauche à 0
                        nbClickG=0;
                        //On indique que le premier clique sur la partie droite a été effectué
                        clickD=true;
                    }
                }else {
                    //Afficher phrase de fin
                    tVPhrase.setText(getResources().getText(R.string.phraseFin));
                    btnDroit.setEnabled(false);
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
                //On incremente le nombre de clique sur la partie gauche
                nbClickG++;
                if (oldPhrases.isEmpty()){
                    btnGauche.setEnabled(false);
                } else{
                    //Si c'est le premier clique sur la partie gauche après avoir cliquer sur la partie droite
                    //on ajoute la derniere phrase de la lise aux phrases à affichée et on la supprime de la liste
                    //des phrases déjà affichée car elle l'est déjà.
                    if (nbClickG==1 && !Phrase.isEmpty()){
                        Phrase.ajouterPhrase(oldPhrases.get(oldPhrases.size()-1));
                        oldPhrases.remove(oldPhrases.size()-1);
                    }
                    //Récupération de la phrase à afficher
                    indexPhrase = oldPhrases.size()-1;
                    phraseAffichee = oldPhrases.get(indexPhrase);
                    //Affichage de la phrase
                    tVPhrase.setText(phraseAffichee);
                    //On met la phrase affichée dans les phrases à afficher
                    Phrase.ajouterPhrase(phraseAffichee);
                    oldPhrases.remove(indexPhrase);
                    //Réinitialisation du clique sur la partie droite
                    clickD=false;
                }
            }
        });
    }
}