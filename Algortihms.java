import java.util.*;

public class Algorithms {

    // Problem 5: Radix Sort
    public static void radixSort(String[] arr) {
        if (arr == null || arr.length == 0) return;
        int maxLen = 0;
        for (String s : arr) maxLen = Math.max(maxLen, s.length());

        for (int pos = maxLen - 1; pos >= 0; pos--) {
            Map<Character, List<String>> buckets = new HashMap<>();
            for (char c = 'a'; c <= 'z'; c++) buckets.put(c, new ArrayList<>());
            for (char c = 'A'; c <= 'Z'; c++) buckets.put(c, new ArrayList<>());

            List<String> emptyBucket = new ArrayList<>();
            for (String s : arr) {
                char key = pos < s.length() ? s.charAt(pos) : 'a' - 1;
                buckets.getOrDefault(key, emptyBucket).add(s);
            }

            int index = 0;
            for (char c = 'A'; c <= 'Z'; c++) for (String s : buckets.get(c)) arr[index++] = s;
            for (char c = 'a'; c <= 'z'; c++) for (String s : buckets.get(c)) arr[index++] = s;
        }
    }

    // Problem 6: Word Pattern
    public static boolean wordPattern(String pattern, char d, String s) {
        String[] words = s.split("\\Q" + d + "\\E"); 
        if (words.length != pattern.length()) return false;

        Map<Character, String> charToWord = new HashMap<>();
        Map<String, Character> wordToChar = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            if (charToWord.containsKey(c) && !charToWord.get(c).equals(word)) return false;
            if (wordToChar.containsKey(word) && wordToChar.get(word) != c) return false;

            charToWord.put(c, word);
            wordToChar.put(word, c);
        }
        return true;
    }

    // Sub Array Sum (EC Problem)
    public static int[] sumTarget(int[] A, int K) {
        int left = 0, sum = 0;
        for (int right = 0; right < A.length; right++) {
            sum += A[right];
            while (sum > K && left <= right) sum -= A[left++];
            if (sum == K) return new int[]{left, right};
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        // Test Radix Sort
        String[] arr = {"google", "gojo", "amazingly", "jogo", "luna", "pup", "solas", "solo", "pupperino", "amaterasu", "amazon", "puppy", "hydra", "amazonia", "vueltiao"};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));

        // Test Word Pattern Matching
        System.out.println(wordPattern("abba", '?', "dog?cat?cat?dog")); // true
        System.out.println(wordPattern("abba", '|', "apple|banana|grape|apple")); // false

        // Test Subarray Sum
        int[] A = {1, 2, 3, 7, 5};
        System.out.println(Arrays.toString(sumTarget(A, 12))); // [1, 3] or [3, 4]
    }
}
