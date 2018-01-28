import java.io.File;
import java.io.IOException;


public class GAPParserTest1 {

	private static int noGapFiles = 0;
	private static int noGapFilesFail = 0;

	public static void main(String[] args)
	{
		try 
		{
			parseDir(0, new File("C:\\tools\\cygwin64\\home\\bec\\gap4r8\\lib"));
			System.out.println("noGapFiles=" + noGapFiles);
			System.out.println("noGapFilesFail=" + noGapFilesFail);
			
		} catch (Exception e) {
			
	        System.err.println(e.getMessage());
		}
	}

	static void parseDir (int level, final File folder) throws Exception
	{
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            parseDir (level+1, fileEntry);
	        } else if (fileEntry.isFile()) {
	        	processFile (level+1, fileEntry);
	        } else {
	        	System.err.println(fileEntry.getAbsolutePath());
	        }
	    }
	}

	static void processFile (int level, final File file) throws Exception
	{
		if (file.getName().matches(".+[.]g[id]?"))
		{
			noGapFiles++;
			JJGapParser parser = JJGapParser.parseGapFile(file);
			if (!parser.ok) {
//				throw new Exception("Parsing aborted.");
				noGapFilesFail ++;
			}
		}
	}


}
