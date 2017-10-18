/* Email Server Protection*/
/* Bayesian Spam Filter */

//package bayes;

import java.io.*;
import javax.swing.*;

public class Bayesian {
    String message;
	
	public Bayesian(){
		message = new String();
	}
	
	// Checks whether message is Spam or Ham
	//Returns true if message is GENUINE and false if SPAM
	public boolean checkSpam()
	{
		try {
			// Create a new SpamFilter Object
			SpamFilter filter = new SpamFilter();
			// Train spam with a file of spam e-mails
			filter.trainSpam("spam.txt");
			// Train spam with a file of regular e-mails
			filter.trainGood("good.txt");
			// Finished adding words so finalize the results
			filter.finalizeTraining();
			
			//Test if Message contains any Bad URL
			//URLStringDetector urlStringDetector = new URLStringDetector();
			//if(urlStringDetector.getURL(message))
			//	return false;
			
			// Ask the filter to analyze message for Spam
			boolean spam = filter.analyze(message);
												// Print results
			if (spam) {
				//System.out.println("I do believe this message is spam!");
				return false;
			} else {
				//System.out.println("I do believe this is a genuine message!");
				return true;
			}

		}
		catch (IOException e) {
			try{
				A2ZFileWriter writer = new A2ZFileWriter("error_logs.txt");
				writer.writeContent(e.getMessage());
			}catch(IOException ex){
				System.out.print(ex.getMessage());
			}
			return true;
		}
	}
	
	//Set the message that needs to be Checked
	public void setMessage(String message){
		this.message = message;	
	}
	
	//Method used to append Good Messages in Good File.
	public void trainHam(String content){
		content = " " + content;
		try{
			A2ZFileWriter writer = new A2ZFileWriter("good.txt");
			writer.writeContent(content);
		}catch(IOException e){
			System.out.print(e.getMessage());
		}
	}
	
	//Method used to append Spam Messages in Bad File.
	public void trainSpam(String content){
		content = " " + content;
		try{
			A2ZFileWriter writer = new A2ZFileWriter("spam.txt");
			writer.writeContent(content);
		}catch(IOException e){
			System.out.print(e.getMessage());
		}
	}
	public static void main(String args[]){
		Bayesian b = new Bayesian();
		String message = "foo bar https://www.duolingo.com";
		b.setMessage(message);
		System.out.print(b.checkSpam());
	}

}
