import intro.ex7.*;

/**
 * file:RecursiveLs.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * exercise : intro2cs ex7 2011-2012 
 * description: A class defined especially for ex7.
 *  Contains a single static method that iterates recursively over folders
 *  beginning at a certain root and prints the contents using an instance of FileDisplay.
 */
public class RecursiveLs {
	/**
	 * Recursively prints the contents of root up to depth using an instance of FileDisplay.
	 *  If depth is 0, just prints root.
	 *  An increase of 1 in depth means printing another level down. 
	 * @param depth The depth of the recursion.
	 * @param root The folder who's contents this method will display.
	 * @param gui The FileDisplay instance this method will use in order to print the output.
	 */
	public static void displayFileTree(int depth, IntroFile root, FileDisplay gui) {
		
		displayFileTreeRecursive(0, depth, root, gui);
	}
	
	
	/**
	 * Recursively prints the contents of root up to depth using an instance of FileDisplay.
	 *  If depth is 0, just prints root.
	 *  An increase of 1 in depth means printing another level down. 
	 * @param depth The depth the recursion went by far.
	 * @param maxDepth The maximum depth of the recursion.
	 * @param root The folder who's contents this method will display.
	 * @param gui The FileDisplay instance this method will use in order to print the output.
	 */
	private static void displayFileTreeRecursive(int depth,int maxDepth,IntroFile root, FileDisplay gui) {
		gui.addLine(depth, root.isDirectory(), root.getName());
		
		if(depth == maxDepth || root.isFile())
			return;
		
		for(IntroFile file:root.listFiles())
			displayFileTreeRecursive(depth+1,maxDepth, file, gui);
		
	}
}
