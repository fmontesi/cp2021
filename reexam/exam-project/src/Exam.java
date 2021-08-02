import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.IntStream;

/*
This is the re-exam for DM563 - Concurrent Programming, Spring 2021.

Your task is to implement the following methods of class Exam:
- wordsStartingWith
- findWordsCommonToFiles
- findPalindromeWord

These methods search text files for particular words.
You must use a BreakIterator to identify words in a text file,
which you can obtain by calling BreakIterator.getWordInstance().
For more details on the usage of BreakIterator, please see the corresponding video lecture in the course.

The implementations of these methods must exploit concurrency to achieve improved performance.

The only code that you can change is the implementation of these methods.
In particular, you cannot change the signatures (return type, name, parameters) of any method, and you cannot edit method main.
The current code of these methods throws an UnsupportedOperationException: remove that line before proceeding on to the implementation.

You can find a complete explanation of the exam rules at the following webpage.

https://github.com/fmontesi/cp2021/tree/master/exam
*/

public class Exam {
	// Do not change this method
	public static void main(String[] args) {
		checkArguments(args.length > 0,
			"You must choose a command: help, wordsStartingWith, findWordsCommonToFiles, or findPalindromeWord.");
		switch (args[0]) {
			case "help":
				System.out.println("Available commands: help, wordsStartingWith, findWordsCommonToFiles, or findPalindromeWord.\nFor example, try:\n\tjava Exam findPalindromeWord data");
				break;
			case "wordsStartingWith":
					checkArguments(args.length == 3, "Usage: java Exam.java wordsStartingWith <directory> <prefix>");
					List<LocatedWord> prefixWords = wordsStartingWith(Paths.get(args[1]), args[2]);
					System.out.println("Found " + prefixWords.size() + " words starting with " + args[2]);
					prefixWords.forEach(locatedWord -> System.out.println(locatedWord.word + ":" + locatedWord.filepath));
					break;
			case "findWordsCommonToFiles":
					checkArguments(args.length == 3, "Usage: java Exam.java findWordsCommonToFiles <directory> <count>");
					int count = Integer.parseInt(args[2]);
					List<String> commonWords = findWordsCommonToFiles(Paths.get(args[1]), count);
					System.out.println("Found " + commonWords.size() + " words in common with " + count + " files");
					commonWords.forEach(System.out::println);
					break;
			case "findPalindromeWord":
					checkArguments(args.length == 2, "Usage: java Exam.java findPalindromeWord <directory>");
					Optional<LocatedWord> palindromeWordOptional = findPalindromeWord(Paths.get(args[1]));
					palindromeWordOptional.ifPresentOrElse(
							locatedWord -> System.out.println("Found " + locatedWord.word + " in " + locatedWord.filepath),
							() -> System.out.println("No palindrome word found"));
					break;
			default:
				System.out.println("Unrecognised command: " + args[0] + ". Try java Exam.java help.");
				break;
		}
	}
	
	// Do not change this method
	private static void checkArguments(Boolean check, String message)
	{
		if (!check) {
			throw new IllegalArgumentException(message);
		}
	}

	/** Returns the words starting with the given {@code prefix} that appear in the files
	* of the given directory.
	* 
	* This method recursively visits a directory to find text files
	* contained in it and its subdirectories (and the subdirectories of these
	* subdirectories, etc.).
	*
	* You must consider only files ending with a ".txt" suffix. You are
	* guaranteed that they will be text files.
	* 
	* The method should return a list of LocatedWord objects (defined by
	* the class at the end of this file) each containing:
	*   - a word that starts with the given prefix;
	*   - the path to the file containing the word.
	*
	* Words must be considered equal without considering differences between
	* uppercase and lowercase letters. For example, the words "Hello", "hEllo" and
	* "HELLo" must be considered all starting with the prefix "hel".
	*
	* @param dir 		the directory to search
	* @param prefix 	the prefix the words searched for should start with
	* @return a list of LocatedWord starting with the given prefix
	*/
	private static List<LocatedWord> wordsStartingWith(Path dir, String prefix) {
		throw new UnsupportedOperationException(); // Remove this once you implement the method
	}

	/** Returns the words that appear in exactly as many files (of the given directory) as
	* specified by the {@code count} paramenter.
	*
	* This method recursively visits a directory to find text files contained in it
	* and its subdirectories (and the subdirectories of these subdirectories,
	* etc.).
	*
	* You must consider only files ending with a ".txt" suffix. You are guaranteed
	* that they will be text files.
	*
	* Words must be considered equal without considering differences between
	* uppercase and lowercase letters. For example, the words "Hello", "hEllo" and
	* "HELLo" must be considered equal to the word "hello".
	*
	* @param dir 		the directory to search
	* @param count 		the number of files the words must appear in
	* @return a list of words appearing in exactly {@code count} files
	*/
	private static List<String> findWordsCommonToFiles(Path dir, int count) {
		throw new UnsupportedOperationException(); // Remove this once you implement the method
	}

	/** Returns an Optional<LocatedWord> (see below) about a palindrome word
	* found in the files of the given directory.
	*
	* This method recursively visits a directory to find text files contained in it
	* and its subdirectories (and the subdirectories of these subdirectories,
	* etc.).
	*
	* You must consider only files ending with a ".txt" suffix. You are guaranteed
	* that they will be text files.
	*
	* The method should return an (optional) LocatedWord object (defined by the
	* class at the end of this file) containing:
	* - the word found that must be a palindrome word (see method isPalindrome);
	* - the path to the file containing the word.
	*
	* If a word satisfying the description above can be found, then the method
	* should return an Optional containing the desired LocatedWord. Otherwise, if
	* such a word cannot be found, the method should return Optional.empty().
	*
	* This method should return *as soon as possible*: as soon as a satisfactory
	* word is found, the method should return a result without waiting for the
	* processing of remaining files and/or other data.
	*
	* @param dir the directory to search
	* @return an optional LocatedWord about a palindrome word
	*/
	private static Optional<LocatedWord> findPalindromeWord(Path dir) {
		throw new UnsupportedOperationException(); // Remove this once you implement the method
	}

	// Do not change this class
	private static class LocatedWord {
		private final String word; // the word
		private final Path filepath; // the file where the word has been found

		private LocatedWord(String word, Path filepath) {
			this.word = word;
			this.filepath = filepath;
		}
	}

	/** Returns whether the given word is palindrome, that is, whether the word reads the
	* same backwards as forwards.
	*
	* Examples of palindrome words are: level, radar, noon, mom, wow.
	* 
	* @param word the word to be tested
	* @return true if word is palindrome
	*/
	private static Boolean isPalindrome(String word) {
		return IntStream.range(0, word.length() / 2).allMatch(i -> word.charAt(i) == word.charAt(word.length() - i - 1));
	}
	
	// Do not change this class
	private static class InternalException extends RuntimeException {
		private InternalException(String message) {
			super(message);
		}
	}
}
