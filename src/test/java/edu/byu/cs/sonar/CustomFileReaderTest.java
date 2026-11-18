package edu.byu.cs.sonar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class CustomFileReaderTest {

    private CustomFileReader sut;

    @BeforeEach
    void setUp() {
        sut = new CustomFileReader("readMe1.txt");
    }

    @Test
    void testHowManyWordsInFile() throws FileNotFoundException {
        assertEquals(4, sut.howManyWordsInFile(), "There should be 4 words in readMe1.txt");
    }

    @Test
    void testReturnThatWord() throws FileNotFoundException {
        assertEquals("I", sut.returnThatWord(1), "The first word should be I in readMe1.txt");
    }

    @Test
    void testFindNewWord() throws FileNotFoundException {
        sut.findNewWord("C");
        assertEquals("Computer ", sut.getNewSentence(), "Computer should be found when C queried");
    }

    @Test
    void testGetNewSentence() {
        assertEquals("", sut.getNewSentence(), "New sentence should be empty initially");
    }

    @Test
    void setNewSentence() {
        String betterSentence = "New Sentence.";
        sut.setNewSentence(betterSentence);
        assertEquals(betterSentence, sut.getNewSentence());
    }

    @Test
    void testFileNotFound() {
        CustomFileReader missing = new CustomFileReader("missing.txt");
        assertThrows(FileNotFoundException.class, missing::howManyWordsInFile);
    }

    @Test
    void testEquals() throws FileNotFoundException {
        CustomFileReader alias = sut;
        CustomFileReader same = new CustomFileReader("readMe1.txt");
        assertEquals(sut, alias);
        assertEquals(sut, same);
        assertNotEquals(null, sut);
        assertNotEquals(new CustomFileReader("readMe2.txt"), sut);

        same.setNewSentence("Different");
        assertNotEquals(sut, same);

        same.setNewSentence("");
        same.howManyWordsInFile();
        assertNotEquals(sut, same);
    }

    @Test
    void testHashCode() {
        assertEquals(sut.hashCode(), new CustomFileReader("readMe1.txt").hashCode());
    }

    @Test
    void testToString() throws FileNotFoundException {
        sut.howManyWordsInFile();
        sut.setNewSentence("Hello");
        assertEquals("Hello 4", sut.toString());
    }
}
