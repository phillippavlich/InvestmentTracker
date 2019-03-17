
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
	Map AllData = new Hashtable();
	public obtainData() {
	
	
	}
	
	public Map grabStock(String ticker) {
		Map dictionary = new Hashtable();
		try {
            URL url = new URL("https://finance.yahoo.com/quote/"+ticker+"?p="+ticker);
             
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
            			
            			Pattern p = Pattern.compile(">[1-9]{1}.*<");
            			Matcher matcher = p.matcher(restLine);
            		    int test2=matcher.find() ? matcher.start() : -1;

            			if(test2!=-1) {
            				String reduced=restLine.substring(test2+1);
            				Value=reduced.substring(0,reduced.indexOf("<"));
            			}
            			
            			if(Value!="" && !Key.contains("ocator")&& !Key.contains("search-assist")) {
            				dictionary.put(Key, Value);
            			}
            	
                		
                	}
                }
            	
            }
            in.close();
             
        }
        catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
		
		if(!dictionary.keySet().isEmpty()) {
			AllData.put(ticker, dictionary);
		}
		else {
			System.out.println("The ticker "+ticker+" that you entered does not exist!");
		}
		
		return dictionary;
	}
	
	public Map retrieveData() {
		for(Object key: AllData.keySet())
            System.out.println(key + ": " + AllData.get(key));
		return AllData;
	}
}
