package library;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class PictureStory{
    private HashMap<Integer, ArrayList<Word>> pages;
    private ArrayList<BufferedImage> images;

    public PictureStory() {
        pages = new HashMap<>();
        images = new ArrayList<>();
    }

    public void flipPage() {
        // Implement the logic to flip the page
        System.out.println("Page flipped!");
    }

    // Additional methods to add words and images can be added here
    
    // Method to add a single word to a specified page
    public void addWordToPage(int pageNumber, Word word) {
        pages.putIfAbsent(pageNumber, new ArrayList<>()); // Create the page if it doesn't exist
        pages.get(pageNumber).add(word); // Add the word to the specified page
    }

    // Method to get the words for the current page
    public ArrayList<Word> getCurrentPageWords() {
        return pages.getOrDefault(currentPage, new ArrayList<>()); // Return words or an empty list if no words are present
    }

    // Method to set the current page
    public void setCurrentPage(int pageNumber) {
        if (pages.containsKey(pageNumber)) {
            currentPage = pageNumber; // Set to the specified page if it exists
        } else {
            System.out.println("Page " + pageNumber + " does not exist.");
        }
    }
}

