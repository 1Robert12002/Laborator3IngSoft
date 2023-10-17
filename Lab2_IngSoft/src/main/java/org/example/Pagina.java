package org.example;

public class Pagina {

        private int numarPagina;
        private String continut;

        public Pagina(int numarPagina, String continut) {
            this.numarPagina = numarPagina;
            this.continut = continut;
        }

        public int getPageNumber() {
            return this.numarPagina;
        }

        public String getContent() {
            return this.continut;
        }

        @Override
        public String toString() {
            return "Page Number: " + this.numarPagina + "\n" + this.continut;
        }
    }


