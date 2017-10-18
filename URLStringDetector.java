import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLStringDetector {
	ArrayList<String> badWords;
	
	public void setBadWords(){
		badWords = new ArrayList<String>();
		badWords.add("sex");
		badWords.add("nude");
		badWords.add("sexy");
		badWords.add("porn");
		badWords.add("gogle");
	}
	public boolean isURLBad(String urlString){
		URL url;
		boolean flag = false;
 		try {
            url = new URL(urlString);
            URLConnection conn = url.openConnection();
            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(
                               new InputStreamReader(conn.getInputStream()));
            String inputLine;
            setBadWords();
            while ((inputLine = br.readLine()) != null) {
            	for(int i=0;i<badWords.size();i++){
            		if(inputLine.indexOf(badWords.get(i)) != -1){
            			flag = true;
            			break;
            		}
            	}
            }
            br.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 		return flag;
	}
	public boolean getURL(String string){
		Pattern pattern = Pattern.compile(
		        "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
		                + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
		                + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
		        Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		
		Matcher matcher = pattern.matcher(string);
		while (matcher.find()) {
			int matchStart = matcher.start(1);
			int matchEnd = matcher.end();
			if (isURLBad(string.substring(matchStart, matchEnd)))
				return true;
		}
		return false;
	}
	
	/*
	public static void main(String args[]){
		String string = "foo bar https://www.duolingo.com";
		URLStringDetector urlStringDetector = new URLStringDetector();
		urlStringDetector.getURL(string);
	}
	*/
}
