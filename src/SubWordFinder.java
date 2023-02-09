import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SubWordFinder implements WordFinder{
    private ArrayList<ArrayList<String>> dictionary;
    private String alpha = "abcdefghijklmnopqrstuvwxyz";

    public SubWordFinder()    {
        dictionary = new ArrayList<>();
        for(int i = 0; i < 26; i++)
            dictionary.add(new ArrayList<String>());
        populateDictionary();
    }

    /**
     * Populates the dictionary from the text file contents
     * The dictionary object should contain 26 buckets, each
     * bucket filled with an ArrayList<String>
     * The String objects in the buckets are sorted A-Z because
     * of the nature of the text file words.txt
     */
    @Override
    public void populateDictionary() {
        try {
            Scanner in = new Scanner(new File("new_Scrabble.txt"));
            while (in.hasNext()) {
                String word = in.nextLine();
                int index = alpha.indexOf(word.substring(0, 1));
                dictionary.get(index).add(word);

            }
            in.close();

            // now I need to sort all buckets
            for (int i = 0; i < dictionary.size(); i++)
                Collections.sort(dictionary.get(i));

        } catch (Exception e) {
            System.out.println("Error here: " + e);


        }
    }

    @Override
    public ArrayList<SubWord> getSubWords() {
        return null;
    }


    /**
     * Retrieve all SubWord objects from the dictionary.
     * A SubWord is defined as a word that can be split into two
     * words that are also found in the dictionary.  The words
     * MUST be split evenly, e.g. no unused characters.
     * For example, "baseball" is a SubWord because it contains
     * "base" and "ball" (no unused characters)
     * To do this, you must look through every word in the dictionary
     * to see if it is a SubWord object
     *
     * @return An ArrayList containing the SubWord objects
     * pulled from the file words.txt
     */
    @Override
    public ArrayList<SubWord> getSubWords(String word) {
        ArrayList<SubWord> subwords = new ArrayList<>();
        ArrayList<String> bucket = dictionary.get(alpha.indexOf(word.substring(0,1)));
        for (int i)
        String front = "", back = "";
        for(int i = 2; i < word.length()-1; i ++)   {
            front = word.substring(0, i);
            back = word.substring(i);

        }
        return subwords;
    }
    int BinarySearch(ArrayList<String> arr, int low, int high, String word) {

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr.get(mid).equals(word)) {
                return mid;
            }
            else if (arr.get(mid).compareTo(word) < 0)
            {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
     return -1;
    }
    /**
     * Look through the entire dictionary object to see if
     * word exists in dictionary
     *
     * @param word The item to be searched for in dictionary
     * @return true if word is in dictionary, false otherwise
     * NOTE: EFFICIENCY O(log N) vs O(N) IS A BIG DEAL HERE!!!
     * You MAY NOT use Collections.binarySearch() here; you must use
     * YOUR OWN DEFINITION of a binary search in order to receive
     * the credit as specified on the grading rubric.
     */
    @Override
    public boolean inDictionary(String word) {
        int index = alpha.indexOf(word.substring(0,1));
        return BinarySearch(dictionary.get(index),0,dictionary.get(index).size(), word) >= 0;
    }
}
