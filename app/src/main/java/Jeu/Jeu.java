package Jeu;

import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;

import fr.faget.noir.R;

public class Jeu {
    //========================== Variables ==========================
    private final Context context; //Contexte de l'application
    private final ArrayList<String> oldPhrases= new ArrayList<>(); //Sauvegarde les phrases déjà affichée
    private int nbClickG=0; //Permet de verifier combien de fois on a cliquer à gauche après avoir cliqué à droite
    private boolean clickD=true; //vrai si c'est le premier clique à droite après avoir cliqué à gauche
    private boolean fini=false; //Indique que la phrase de fin est affichée
    private int indexPhrase=0; //index de la phrase à afficher
    private String phraseAffichee=""; //Phrase affichee

    //========================= Constructeur =========================
    public Jeu(Context context){
        this.context=context;
    }

    //====================== Getters et Setters ======================
    public void setFini(boolean fini) {
        this.fini = fini;
    }

    public ArrayList<String> getOldPhrases() {
        return oldPhrases;
    }

    public int getNbClickG() {
        return nbClickG;
    }

    //=========================== Méthodes ===========================
    public int nombreAleatoire(int borneMax){
        return (int)(Math.random()*borneMax);
    }

    public void afficherPhrase(TextView textView){
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
                    oldPhrases.add(phraseAffichee);
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
                    oldPhrases.add(phraseAffichee);
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
                    oldPhrases.add(phraseAffichee);
                    //Supression de la phrase affichée de la liste des phrases
                    Phrase.supprimerPhrase(indexPhrase);
                    break;

                case "pasDeJoueur":
                    //Supression de la phrase affichée de la liste des phrases
                    Phrase.supprimerPhrase(indexPhrase);
                    //On affiche une autre phrase
                    afficherPhrase(textView);
                    break;

                default:
                    //Affichage de la phrase
                    textView.setText(phraseAffichee);
                    //Sauvegarde de la phrase affichée
                    oldPhrases.add(phraseAffichee);
                    //Supression de la phrase affichée de la liste des phrases
                    Phrase.supprimerPhrase(indexPhrase);
            } //end case
        } else {
            //Afficher phrase de fin
            textView.setText(context.getResources().getText(R.string.phraseFin));
            //Indique que la phrase de fin est affichée
            fini=true;
        }
    }

    public void reAfficherPhrases(TextView textView){
        //Si c'est le premier clique sur la partie droite après avoir cliquer sur la partie gauche
        //on ajoute la derniere phrase de la lise aux phrases déjà affichée et on la supprime de la liste
        //des phrases à affichée car elle l'est déjà.
        if (!clickD && !(Phrase.nbPhrases()==1)){
            oldPhrases.add(Phrase.getPhrase(Phrase.nbPhrases()-1));
            Phrase.supprimerPhrase(Phrase.nbPhrases()-1);
        }
        //Récupération de la phrase à afficher
        indexPhrase = Phrase.nbPhrases()-1;
        phraseAffichee = Phrase.getPhrase(indexPhrase);
        //Affichage de la phrase
        textView.setText(phraseAffichee);
        //On met la phrase affichée dans les phrases déjà affichée
        oldPhrases.add(phraseAffichee);
        Phrase.supprimerPhrase(indexPhrase);
        //On indique que le premier clique sur la partie droite a été effectué
        clickD=true;
        //On remet le nombre de clique sur la partie gauche à 0
        nbClickG=0;
    }

    public void afficherAnciennePhrases(TextView textView){
        //Incrementation du nombre de clique sur la partie gauche
        nbClickG++;
        //Si c'est le premier clique sur la partie gauche après avoir cliquer sur la partie droite
        //on ajoute la derniere phrase de la lise aux phrases à affichée et on la supprime de la liste
        //des phrases déjà affichée car elle l'est déjà.
        if (nbClickG==1 && !fini){
            Phrase.ajouterPhrase(oldPhrases.get(oldPhrases.size()-1));
            oldPhrases.remove(oldPhrases.size()-1);
        }
        //Récupération de la phrase à afficher
        indexPhrase=oldPhrases.size()-1;
        phraseAffichee = oldPhrases.get(indexPhrase);
        //On met la phrase affichée dans les phrases à afficher sauf si la phrase précédement affichée était la phrase de fin
        if(!fini){
            Phrase.ajouterPhrase(phraseAffichee);
            oldPhrases.remove(indexPhrase);
        }
        //Affichage de la phrase
        textView.setText(phraseAffichee);
        //Réinitialisation du clique sur la partie droite
        clickD=false;
        //On indique que la phrase de fin n'est plus affichée et on réinitialise le  nombre de clique sur la partie gauche
        if (fini){
            fini=false;
            nbClickG=0;
        }
    }


}
