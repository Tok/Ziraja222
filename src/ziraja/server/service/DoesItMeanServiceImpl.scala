package ziraja.server.service;

import java.io.IOException
import java.net.MalformedURLException

import com.google.gwt.user.server.rpc.RemoteServiceServlet
import ziraja.client.service.DoesItMeanService

class DoesItMeanServiceImpl extends RemoteServiceServlet with DoesItMeanService {

  /**
   * Sends a query to google and returns the value of the
   * "Did you mean" function or ?? if there is no suggestion.
   */
  override def doesItMean(input: String): String = {
    var output = "??"
    try {
      def read(url: String): String = io.Source.fromURL(url).mkString
      for (line <- read("http://www.google.com/search?lang=en&hl=en&q=" + input).split("/n")) {
        if (line != null && line.contains("Did you mean:")) {
          val firstSplit = line.split("Did you mean:");
          val secondSplit = firstSplit(1).split("<i>");
          val thirdSplit = secondSplit(1).split("</i>");
          output = thirdSplit(0);
        }
      }
    } catch {
      case murle: MalformedURLException => output = "FAIL: URL malformed.";
      case ioe: IOException => output = "FAIL: IO-Exception.";
    }
    return output;
  }

}