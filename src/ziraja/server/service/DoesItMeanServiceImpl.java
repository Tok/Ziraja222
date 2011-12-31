package ziraja.server.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import ziraja.client.service.DoesItMeanService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DoesItMeanServiceImpl extends RemoteServiceServlet implements
		DoesItMeanService {
	private static final long serialVersionUID = -1624358997818185913L;

	public DoesItMeanServiceImpl() {
	}

	/**
	 * Sends a query to google and returns the value of the 
	 * "Did you mean" function or ?? if there is no suggestion.
	 */
	@Override
	public String doesItMean(String input) {
		String output = "??";
		try {
			URL url = new URL("http://www.google.com/search?lang=en&hl=en&q=" + input);		
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.contains("Did you mean:")) {
					String[] firstSplit = line.split("Did you mean:");
					String[] secondSplit = firstSplit[1].split("<i>");
					String[] thirdSplit = secondSplit[1].split("</i>");
					output = thirdSplit[0];
				}
			}
			reader.close();
		} catch (MalformedURLException e) {
			//ignore
		} catch (IOException e) {
			//ignore
		}
		return output;
	}
}
