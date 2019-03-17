
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Map;
import java.net.URL;
import java.util.regex.Pattern; 
import java.util.regex.Matcher;

public class obtainData {
	public obtainData() {
		initializeConnection();
		
	}
	
	public void initializeConnection() {
		Map dictionary = new Hashtable();
		dictionary.put("lol", 6);
		try {
            
            URL url = new URL("https://finance.yahoo.com/quote/AAPL?p=AAPL");
             
            // read text returned by server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
             
            String line;
            while ((line = in.readLine()) != null) {
            	String[] allData=line.split("td");
            	
                for(int i=0; i<allData.length; i++) {
                	if(allData[i].contains("data-reactid") & allData[i].contains("data-test") ) {
                		
            			int index=allData[i].indexOf("data-test");
            			
            			
            			String restLine=allData[i].substring(index+11);
            			
            			int index2=restLine.indexOf("data-reactid");
            			

            			String Key=restLine.substring(0,index2-2);
            			String Value="";
            			
            			//System.out.println("Key: "+ Key.toString());
            			
            			Pattern p = Pattern.compile(">[1-9]{1}.*<");
            			Matcher matcher = p.matcher(restLine);
            		    int test2=matcher.find() ? matcher.start() : -1;

            			if(test2!=-1) {
            				String reduced=restLine.substring(test2+1);
            				Value=reduced.substring(0,reduced.indexOf("<"));
            			}
            			//System.out.println("Value: "+Value.toString());
            			//System.out.println(restLine);
            			dictionary.put(Key, Value);
            			
            			//System.out.println(allData[i]);
                		
                	}
                }
            	//if(line.contains("td")) {
                	//System.out.println(line);
                //}
            	
            }
            in.close();
             
        }
        catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
		
		
		for(Object key: dictionary.keySet())
            System.out.println(key + ": " + dictionary.get(key));
         
		System.out.println("test");
		
	}
	
	public int retrieveData() {
		return 0;
	}
}
