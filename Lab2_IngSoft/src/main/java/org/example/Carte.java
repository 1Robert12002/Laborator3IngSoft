package org.example;

import java.util.ArrayList;

public class Carte  {
    private String autor,titlu;
    private ArrayList<Pagina> pagini;

    public Carte(String autor, String titlu) {
        this.autor = autor;
        this.titlu = titlu;
        this.pagini = new ArrayList<>();
    }

    public String getAutor() {
       return this.autor;
    }

    public String getTitlu() {
        return this.titlu;
    }

    public ArrayList<Pagina> getPagini() {
        return this.pagini;
    }

    public void addPage(Pagina page) {
        this.pagini.add(page);
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Titlu: ").append(titlu).append(", Autor: ").append(autor).append("\n");

        for (Pagina page : pagini) {
            builder.append("Numar Pagina: ").append(page.getPageNumber()).append(", ")
                    .append("Continut: ").append(page.getContent()).append("\n");
        }
        return builder.toString();
    }
}
