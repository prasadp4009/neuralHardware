import java.util.*;
import java.io.*;

public class MIF_convert {
    public static void main(String[] args) throws Exception{
	BufferedReader test = new BufferedReader(new FileReader(args[0]));
	String line = test.readLine();
	List<double[]> lines = new ArrayList<double[]>();

	PrintWriter writer = new PrintWriter("data.mif", "UTF-8");
	writer.println("DEPTH = 4096;");
	writer.println("WIDTH = 8;");
	writer.println("ADDRESS_RADIX = HEX;");
	writer.println("DATA_RADIX = HEX;");
	writer.println();
	writer.println("CONTENT BEGIN");
	writer.println();

	int size = 0;
	line = test.readLine();
	while(line != null) {
	    String[] words = line.split(",");
	    if (size == 0) 
		System.out.println("Training data has " + (words.length - 1) + " inputs and 1 output");
	    double[] vals = new double[words.length];
	    for(int i = 0; i < words.length; i ++) {
		String val = 
		    String.format("%02X", (int)Double.parseDouble(words[i])).toLowerCase();
		String index = String.format("%03X", size).toLowerCase();
		writer.println(index + " : " + val + ";");
		size++;
	    }
	    line = test.readLine();
	}

	String index = String.format("%03X", size).toLowerCase();
	System.out.println("Testing data starts at " + index);
	test = new BufferedReader(new FileReader(args[1]));
	line = test.readLine();
	line = test.readLine();
        while(line != null) {
            String[] words = line.split(",");
            double[] vals = new double[words.length];
            for(int i = 0; i < words.length; i ++) {
                String val = 
		    String.format("%02X", (int)Double.parseDouble(words[i])).toLowerCase();
                index = String.format("%03X", size).toLowerCase();
                writer.println(index + " : " + val + ";");
                size++;
            }
            line = test.readLine();
        }
    }
    
}
	
