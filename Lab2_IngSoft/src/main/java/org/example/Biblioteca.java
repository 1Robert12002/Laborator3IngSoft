package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Biblioteca {
    public static void main(String[] args) {
        ArrayList<Carte> biblioteca = new ArrayList<Carte>();

        Carte carte1 = new Carte("Roald Dahl", "The Wonderful Story of Henry Sugar");
        carte1.addPage(new Pagina(1, "Ceva"));
        carte1.addPage(new Pagina(2, "Continut pagina 2"));

        Carte carte2 = new Carte("J.R.R. Tolkien", "The Lord of the Rings");
        carte2.addPage(new Pagina(3, "Alceva"));

        Carte carte3 = new Carte("J.K. Rowling", "Harry Potter and the Philosopher's Stone");
        carte3.addPage(new Pagina(2, "Alceva2"));

        Carte carte4 = new Carte("J.K. Rowling", "Harry Potter and the Deathly Hallows");
        carte4.addPage(new Pagina(1, "AbCd"));

        biblioteca.add(carte1);
        biblioteca.add(carte2);
        biblioteca.add(carte3);
        biblioteca.add(carte4);


        Collections.sort(biblioteca, new BookComparator());


        System.out.println("List of all books in the library (Ordered by Author and Title):");
        for (Carte carte : biblioteca) {
            System.out.println("Book: " + carte.getTitlu() + " by " + carte.getAutor());
            for (Pagina pagina : carte.getPagini()) {
                System.out.println("Page Number: " + pagina.getPageNumber());
                System.out.println("Content: " + pagina.getContent());
            }
            System.out.println();
        }


        Map<String, ArrayList<ContentLocation>> contentIndex = buildContentIndex(biblioteca);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the content you want to find: ");
        String searchTerm = scanner.nextLine();

        ArrayList<ContentLocation> foundLocations = contentIndex.get(searchTerm);
        if (foundLocations != null) {
            System.out.println("Search results for content '" + searchTerm + "':");
            for (ContentLocation location : foundLocations) {
                System.out.println("Book: " + location.carte.getTitlu() + " by " + location.carte.getAutor());
                System.out.println("Page Number: " + location.pagina.getPageNumber());
                System.out.println("Content: " + location.pagina.getContent() + "\n");
            }
        } else {
            System.out.println("Page with content '" + searchTerm + "' not found.");
        }


        scanner.close();
    }

    public static Map<String, ArrayList<ContentLocation>> buildContentIndex(ArrayList<Carte> biblioteca) {
        Map<String, ArrayList<ContentLocation>> contentIndex = new HashMap<>();

        for (Carte carte : biblioteca) {
            for (Pagina pagina : carte.getPagini()) {
                String content = pagina.getContent();
                if (!contentIndex.containsKey(content)) {
                    contentIndex.put(content, new ArrayList<>());
                }
                contentIndex.get(content).add(new ContentLocation(carte, pagina));
            }
        }

        return contentIndex;
    }

    static class ContentLocation {
        Carte carte;
        Pagina pagina;

        ContentLocation(Carte carte, Pagina pagina) {
            this.carte = carte;
            this.pagina = pagina;
        }
    }

    static class BookComparator implements Comparator<Carte> {
        @Override
        public int compare(Carte c1, Carte c2) {
            int authorComparison = c1.getAutor().compareTo(c2.getAutor());
            if (authorComparison == 0) {
                return c1.getTitlu().compareTo(c2.getTitlu());
            }
            return authorComparison;
        }
    }
}
