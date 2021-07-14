package android.app.anisbookupdate.Utilities;

import java.text.Collator;
import java.util.Locale;


/**
 * This class contains 2 methods for searching in arabic strings by ignoring Diacritic
 * <p>
 * {@link #searchInStringWordByWord(String, String, int)} is super fast but limited to search by word (witch in many
 * case id what you need)
 * <p>
 * {@link #searchInStringLetterByLetter(String, String, int)} is slow but it can search anything from a letter to many
 * letter :)
 *
 * @author Mehdi Sohrabi (mehdok@gmail.com) on 7/19/2017.
 */

public class DiacriticInsensitiveSearch {

    /**
     * This method will search through input string for search word, word by word
     * Note that it will search the input word by word so it is really fast but has some cons
     * so use it if you know the pros and cons
     * <p>
     * Pros: It is really really fast as hell :)
     * <p>
     * Cons: It search word by word, so you can not search for sw = "مه", actually you can BUT this method wont find it :)
     * You must search for whole word, number of word is not matter but they must be whole word
     * If you have input = "مِهدی؛ سُهرابی" and you search for sw = "مهدی" this method wont find any thing cause it
     * consider مِهدی؛ as one word and obviously it is different from your search word
     *
     * @param input The input string
     * @param sw    The search word
     * @param index The index that search will start from
     * @return array of 2 int contain start index and end index, or {-1,-1} in case it did not find the search word
     * result[0] -> firstIndex
     * result[1] -> lastIndex
     */
    public static int[] searchInStringWordByWord(String input, String sw, int index) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;

        int counter = index;

        input = input.substring(index, input.length());

        final Collator instance = Collator.getInstance(new Locale("ar"));
        instance.setStrength(Collator.NO_DECOMPOSITION);

        String[] source = input.split(" ");
        String[] target = sw.split(" ");

        for (int i = 0; i < source.length; i++) {
            if (instance.compare(source[i], target[0]) == 0) {
                //first char is equal
                if (target.length > 1) {
                    // check other char
                    boolean exist = searchForCompanionWords(instance, source, target, i, 0);
                    if (exist) {
                        result[0] = counter;
                        result[1] = getLastIndex(source, i, target.length, counter);
                    }
                } else {
                    result[0] = counter;
                    result[1] = counter + source[i].length();
                    return result;
                }
            }

            counter += source[i].length() + 1; // +1 for omitted space
        }

        return result;
    }

    /**
     * If the search word is more than one word, this method comes in place and use a recursive method to checks for
     * equality, it advance one by one into source and target and check them and return true if all of them is equal
     * I know it is recursive BUT it will execute 2 or 3 times (depends on number of word in search word) so it is Not
     * big deal in performance
     *
     * @param collator    The java Collator that use for comparison
     * @param source      The source string the is space splitted
     * @param target      The search word that is space splitted
     * @param sourceMatch The index in source that match the `targetMatch` in search word
     * @param targetMatch The index in search word that match the `sourceMatch` in source
     * @return true if other words are equal and false otherwise
     */
    private static boolean searchForCompanionWords(Collator collator, String[] source, String[] target, int sourceMatch,
                                                   int targetMatch) {
        if (source.length <= sourceMatch + 1) return false;
        if (target.length <= targetMatch + 1) return false;

        if (collator.compare(source[sourceMatch + 1], target[targetMatch + 1]) == 0) {
            // The next char matches, is there any target word?
            if (target.length <= targetMatch + 2) {
                return true;
            } else {
                // There is more target word
                return searchForCompanionWords(collator, source, target, sourceMatch + 1, targetMatch + 1);
            }
        }

        return false;
    }

    /**
     * Get last index by lopping trough source
     *
     * @param source  The space splitted source
     * @param start   Start index in source
     * @param howMany Advance number
     * @param size    Size of strings in source before *start* index
     * @return The last index
     */
    private static int getLastIndex(String[] source, int start, int howMany, int size) {
        for (int i = start; i < start + howMany; i++) {
            size += source[i].length() + 1; // +1 for omitted space
        }

        return size - 1; // -1 for last extra space
    }

    /**
     * This method will search through input string for search word letter by letter
     * Note that it will search the input letter by letter so it is SLOW (i tries to optimized it and this is as far as
     * i can go). so use it if you know the pros and cons
     * <p>
     * Pros: it can search anything, a letter, a word, anything if you have input = "مِهدی سُهرابی" you can search for
     * sw = "مه" and it find it for you
     * <p>
     * Cons: Really slow and unusable for long search
     *
     * @param input The input string
     * @param sw    The search word
     * @param index The index that search will start from
     * @return Array of 2 int contain start index and end index, or {-1,-1} in case it did not find the search word
     * result[0] -> firstIndex
     * result[1] -> lastIndex
     */
    public static int[] searchInStringLetterByLetter(String input, String sw, int index) {
        int[] result = new int[2];
        int counter = 0;
        String temp;

        for (int i = index; i < input.length(); i++) {
            temp = removeDiacritic(input.substring(i, i + 1));
            if (temp.equals("")) continue; // if it is empty it means the char is diacritic

            if (sw.length() - 1 == counter) {
                //the char found
                result[1] = i + 1; // This is the last index
                return result;
            }

            if (temp.equals(sw.substring(counter, counter + 1))) {
                // the first char is equal, search for other
                if (counter == 0) {
                    result[0] = i;
                }
                ++counter;
            } else {
                // reset char comparison
                counter = 0;
            }
        }

        result[0] = -1;
        result[1] = -1;
        return result;
    }

    /**
     * Remove any diacritic from input string
     * less diacritics means faster search
     *
     * @param input The input string
     * @return Diacritic free string
     */
    private static String removeDiacritic(String input) {
        input = input.replaceAll("\u0650", "");
        input = input.replaceAll("\u0651", "");
        input = input.replaceAll("\u0652", "");
        input = input.replaceAll("\u064E", "");
        input = input.replaceAll("\u064F", "");
        input = input.replaceAll("\u064D", "");
        input = input.replaceAll("\u064B", "");
        return input;
    }

    public static void main(String[] args) {
        int searchIndex[] = searchInStringWordByWord(source, sw, 0);
//        int searchIndex[] = searchInStringLetterByLetter(source, sw, 0);
        while (searchIndex[0] >= 0) {
            System.out.println("start index: " + searchIndex[0] + " - end index: " + searchIndex[1]);
            // Do something useful with this indexes, eg. highlighting
            searchIndex = searchInStringWordByWord(source, sw, searchIndex[1] + 1);
//            searchIndex = searchInStringLetterByLetter(source, sw, searchIndex[1] + 1);
        }
    }

    // I don't know what this means, i just copy and paste it from internet
    private static String source = "یبدو أن شکوک الأمس قد تبخرت وأنت الآن منشغل بالتقدم للأمام؛ حاول " +
            "استخدام هذه الطاقه فی البدء فی مشروعات جدیده واتخاذ قرارات هامه. بما أن الأمور تسیر فی الاتجاه" +
            " الصحیح، وأنت تشعر بالراحه بالنسبه للأمور التی تقوم بها، یمکنک الاستمتاع بالنجاح القادم. لکن" +
            " لا ترتبط بالکثیر من المشروعات وذلک لأن توازنک الداخلی سوف یصبح مهددًا وسوف تتعرض لخطر استنفاذ نفسک.";

    private static String sw = "فی";

}
