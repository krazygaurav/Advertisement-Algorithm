//package a2z;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class A2ZFileWriter {
	  private String filename;
	  
	  public A2ZFileWriter(String name) {
	    filename = name;
	  }
	  
	  public void writeContent(String content) throws IOException {
																// Create an output stream and file channel
																// Using second argument as file name to write out
	    FileOutputStream fos = new FileOutputStream(filename, true);
	    FileChannel outfc = fos.getChannel();
																// Convert content String into ByteBuffer and write out to file
	    ByteBuffer bb = ByteBuffer.wrap(content.getBytes());
																//outfc.position(0);
	    outfc.position(outfc.size());
		outfc.write(bb);
	    outfc.close();
	  }	  
}
