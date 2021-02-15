package Jeu;

import java.util.ArrayList;

public class Joueur {
    static ArrayList<String> joueurs = new ArrayList<>();

    public Joueur(ArrayList<String> joueurs){
        Joueur.joueurs = joueurs;
    }

    public static ArrayList<String> getJoueurs(){
        return joueurs;
    }

    public static void ajouterJoueur(String joueur){
        joueurs.add(joueur);
    }

    public static void supprimerJoueur(int indexJoueur){
        joueurs.remove(indexJoueur);
    }

    public static int nbJoueurs(){
        return joueurs.size();
    }

    public static String getJoueur(int index){
        return joueurs.get(index);
    }

    public static boolean isEmpty(){
        return joueurs.isEmpty();
    }
}
