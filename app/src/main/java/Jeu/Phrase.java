package Jeu;

import java.util.ArrayList;
import java.util.Arrays;

public class Phrase {
    static ArrayList<String> phrases = new ArrayList<>();

    public Phrase(ArrayList<String> phrases){
        Phrase.phrases = phrases;
    }

    public static ArrayList<String> getPhrases(){
        return phrases;
    }

    public static void ajouterPhrase(String joueur){
        phrases.add(joueur);
    }

    public static void ajouterPhrases(String[] listePhrases){
        phrases.addAll(Arrays.asList(listePhrases));
    }

    public static void supprimerPhrase(int indexPhrase){
        phrases.remove(indexPhrase);
    }

    public static int nbPhrases(){
        return phrases.size();
    }

    public static String getPhrase(int index){
        return phrases.get(index);
    }

    public static void setPhrase(int indexPhrase, String newPhrase){
        phrases.set(indexPhrase,newPhrase);
    }

    public static String rechercheOccurence(int indexPhrase){
        String occurence="none";//Occurences trouvées

        //Recherche de la présence des occurence (X,Y ou Z) dans la phrase (-1 si pas présent)
        int indexX = getPhrase(indexPhrase).indexOf('X');
        int indexY = getPhrase(indexPhrase).indexOf('Y');
        int indexZ = getPhrase(indexPhrase).indexOf('Z');

        //Si X a été trouvé
        if(indexX > -1 && indexY == -1 && indexZ == -1 && !Joueur.isEmpty()){
            occurence="X";
        //Si X et Y ont été trouvé
        } else if(indexX > -1 && indexY > -1 && indexZ == -1 && Joueur.nbJoueurs()>=2){
            occurence="XY";
        //Si X, Y et Z ont été trouvé
        } else if(indexX > -1 && indexY > -1 && indexZ > -1 && Joueur.nbJoueurs()>=3){
            occurence="XYZ";
        } else if (((indexX > -1 || indexY > -1 || indexZ > -1) && Joueur.isEmpty()) ||
                (((indexY == -1 || indexZ == -1) && indexX > -1) && Joueur.nbJoueurs() == 1) ||
                (indexX > -1 && indexY > -1)){
            occurence="pasDeJoueur";
        }

        return occurence;
    }

    public static boolean isEmpty(){
        return phrases.isEmpty();
    }
}
