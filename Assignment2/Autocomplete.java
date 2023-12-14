import java.util.Arrays;

public class Autocomplete {
    private Term[] dictionary;

    // Initializes the dictionary from the given array of terms.
    public Autocomplete(Term[] dictionary) {
        this.dictionary = dictionary;
        sortDictionary();
    }

    // Sorts the dictionary in *case-insensitive* lexicographic order.
    // Complexity: O(N log N), where N is the number of terms
    private void sortDictionary() {
        Arrays.sort(dictionary, Term.byLexicographicOrder());
    }

    // Returns all terms that start with the given prefix, in descending order of weight.
    // Complexity: O(log N + M log M), where M is the number of matching terms
    public Term[] allMatches(String prefix) {
        Term term = new Term(prefix, 0);
        int firstIndex = RangeBinarySearch.firstIndexOf(dictionary, term, Term.byPrefixOrder(prefix.length()));
        int lastIndex = RangeBinarySearch.lastIndexOf(dictionary, term, Term.byPrefixOrder(prefix.length()));
        if (firstIndex==-1){
            return new Term[0];
        }
        Term[] arr = new Term[lastIndex-firstIndex+1];
        for (int i = 0; i<arr.length; i++){
            arr[i] = dictionary[firstIndex+i];
        }

        Arrays.sort(arr, Term.byReverseWeightOrder());

        return arr;
    }

    // Returns the number of terms that start with the given prefix.
    // Complexity: O(log N)
    public int numberOfMatches(String prefix) {
        Term term = new Term(prefix, 0);
        int firstIndex = RangeBinarySearch.firstIndexOf(dictionary, term, Term.byPrefixOrder(prefix.length()));
        int lastIndex = RangeBinarySearch.lastIndexOf(dictionary, term, Term.byPrefixOrder(prefix.length()));
        if (firstIndex==-1) {
            return 0;
        }
        int nMatches = lastIndex-firstIndex+1;
        return nMatches;
    }

}
