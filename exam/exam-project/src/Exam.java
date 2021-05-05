import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/*
This is the exam for DM563 - Concurrent Programming, Spring 2021.

Your task is to implement the following methods of class Exam:
- findUniqueWords;
- findCommonWords;
- wordLongerThan.

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
				"You must choose a command: help, shortestWord, longestWord, wordStartingWith, or findWord.");
		switch (args[0]) {
			case "help":
				System.out.println(
						"Available commands: help, shortestWord, longestWord, wordStartingWith, or findWord.\nFor example, try:\n\tjava Exam shortestWord data");
				break;
			case "findUniqueWords":
				checkArguments(args.length == 2, "Usage: java Exam.java findUniqueWords <directory>");
				List<LocatedWord> uniqueWords = findUniqueWords(Paths.get(args[1]));
				System.out.println("Found " + uniqueWords.size() + " unique words");
				uniqueWords.forEach(locatedWord -> System.out.println(locatedWord.word + ":" + locatedWord.filepath));
				break;
			case "findCommonWords":
				checkArguments(args.length == 2, "Usage: java Exam.java findCommonWords <directory>");
				List<String> commonWords = findCommonWords(Paths.get(args[1]));
				System.out.println("Found " + commonWords.size() + " words in common");
				commonWords.forEach(System.out::println);
				break;
			case "wordLongerThan":
				checkArguments(args.length == 3, "Usage: java Exam.java wordLongerThan <directory> <length>");
				int length = Integer.parseInt(args[3]);
				Optional<LocatedWord> longerWordOptional = wordLongerThan(Paths.get(args[1]), length);
				longerWordOptional.ifPresentOrElse(
						locatedWord -> System.out.println("Found " + locatedWord.word + " in " + locatedWord.filepath),
						() -> System.out.println("No word found longer than " + args[2]));
				break;
			default:
				System.out.println("Unrecognised command: " + args[0] + ". Try java Exam.java help.");
				break;
		}
	}

	// Do not change this method
	private static void checkArguments(Boolean check, String message) {
		if (!check) {
			throw new IllegalArgumentException(message);
		}
	}
	/**
	 * Returns all the words that appear in at most one file among all the text
	 * files contained in the given directory.
	 *
	 * This method recursively visits a directory to find text files contained in it
	 * and its subdirectories (and the subdirectories of these subdirectories,
	 * etc.).
	 *
	 * You must consider only files ending with a ".txt" suffix. You are guaranteed
	 * that they will be text files.
	 *
	 * The method should return a list of LocatedWord objects (defined by the class
	 * at the end of this file). Each LocatedWord object should consist of: - a word
	 * that appears in exactly one file (that is, the word must appear in at least
	 * one file, but not more than one); - the path to the file containing the word.
	 *
	 * All unique words must appear in the list: words that can be in the list must
	 * be in the list.
	 * 
	 * Words must be considered equal without considering differences between
	 * uppercase and lowercase letters. For example, the words "Hello", "hEllo" and
	 * "HELLo" must be considered equal to the word "hello".
	 *
	 * @param dir the directory to search
	 * @return a list of words unique to a single file
	 */
	private static List<LocatedWord> findUniqueWords(Path dir) {
		throw new UnsupportedOperationException(); // Remove this once you implement the method
	}

	/**
	 * Returns the words that appear at least once in every text file contained in
	 * the given directory.
	 *
	 * This method recursively visits a directory to find text files contained in it
	 * and its subdirectories (and the subdirectories of these subdirectories,
	 * etc.).
	 *
	 * You must consider only files ending with a ".txt" suffix. You are guaranteed
	 * that they will be text files.
	 *
	 * The method should return a list of words, where each word appears at least once in
	 * every file contained in the given directory.
	 *
	 * Words must be considered equal without considering differences between
	 * uppercase and lowercase letters. For example, the words "Hello", "hEllo" and
	 * "HELLo" must be considered equal to the word "hello".
	 *
	 * @param dir the directory to search
	 * @return a list of words common to all the files
	 */
	private static List<String> findCommonWords(Path dir) {
		throw new UnsupportedOperationException(); // Remove this once you implement the method
	}

	/**
	 * Returns an Optional<LocatedWord> (see below) about a word found in the files
	 * of the given directory whose length is greater than the given length.
	 *
	 * This method recursively visits a directory to find text files contained in it
	 * and its subdirectories (and the subdirectories of these subdirectories,
	 * etc.).
	 *
	 * You must consider only files ending with a ".txt" suffix. You are guaranteed
	 * that they will be text files.
	 *
	 * The method should return an (optional) LocatedWord object (defined by the
	 * class at the end of this file), consisting of:
	 * - the word found that is longer than the given length;
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
	 * @param dir    the directory to search
	 * @param length the length the word searched for must exceed
	 * @return an optional LocatedWord about a word longer than the given length
	 */
	private static Optional<LocatedWord> wordLongerThan(Path dir, int length) {
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

	// Do not change this class
	private static class WordLocation {
		private final Path filepath; // the file where the word has been found
		private final int line; // the line number at which the word has been found

		private WordLocation(Path filepath, int line) {
			this.filepath = filepath;
			this.line = line;
		}
	}

	// Do not change this class
	private static class InternalException extends RuntimeException {
		private InternalException(String message) {
			super(message);
		}
	}
}
