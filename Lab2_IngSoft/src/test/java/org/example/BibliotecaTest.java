package org.example;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.*;

public class BibliotecaTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testContentIndex() {
        // Create a test library with books
        ArrayList<Carte> testLibrary = new ArrayList<>();
        Carte carte1 = new Carte("Author1", "Book1");
        carte1.addPage(new Pagina(1, "Content1"));
        testLibrary.add(carte1);
        Carte carte2 = new Carte("Author2", "Book2");
        carte2.addPage(new Pagina(2, "Content2"));
        testLibrary.add(carte2);

        // Build the content index
        Map<String, ArrayList<Biblioteca.ContentLocation>> contentIndex = Biblioteca.buildContentIndex(testLibrary);

        // Verify the content index
        assertTrue(contentIndex.containsKey("Content1"));
        assertTrue(contentIndex.containsKey("Content2"));
        assertFalse(contentIndex.containsKey("NonExistentContent"));
    }

    @Test
    public void testSearchPageByContent() {
        // Redirect System.out to capture printed output
        System.setOut(new PrintStream(outContent));

        // Create a test library with books
        ArrayList<Carte> testLibrary = new ArrayList<>();
        Carte carte1 = new Carte("Author1", "Book1");
        carte1.addPage(new Pagina(1, "Content1"));
        testLibrary.add(carte1);
        Carte carte2 = new Carte("Author2", "Book2");
        carte2.addPage(new Pagina(2, "Content2"));
        testLibrary.add(carte2);

        // Create an instance of Biblioteca
        Biblioteca biblioteca = new Biblioteca();

        // Build the content index
        Map<String, ArrayList<Biblioteca.ContentLocation>> contentIndex = biblioteca.buildContentIndex(testLibrary);

        // Test searching for content "Content1"
        ByteArrayInputStream inContent = new ByteArrayInputStream("Content1\n".getBytes());
        System.setIn(inContent);


        String output = outContent.toString();
        assertTrue(output.contains("Author1"));
        assertTrue(output.contains("Book1"));

        // Test searching for non-existent content
        outContent.reset();
        inContent = new ByteArrayInputStream("NonExistentContent\n".getBytes());
        System.setIn(inContent);
        

        output = outContent.toString();
        assertTrue(output.contains("Page with content 'NonExistentContent' not found"));

        // Restore the original System.out
        System.setOut(originalOut);
    }
}
