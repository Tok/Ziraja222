package ziraja.server.service;

import java.io.IOException
import java.net.MalformedURLException

import com.google.gwt.user.server.rpc.RemoteServiceServlet
import ziraja.client.service.DoesItMeanService

class DoesItMeanServiceImpl extends RemoteServiceServlet with DoesItMeanService {
  val indent = "Did you mean:"

  /**
   * Sends a query to google and returns the value of the
   * "Did you mean" function or ?? if there is no suggestion.
   */
  override def doesItMean(input: String): String = {
    try {
      val src = io.Source.fromURL("http://www.google.com/search?lang=en&hl=en&q=" + input).mkString
      if (src != null && src.contains(indent)) ((src.split(indent)(1)).split("<i>")(1)).split("</i>")(0) else "???"
    } catch {
      case murle: MalformedURLException => "FAIL: URL malformed."
      case ioe: IOException => "??"
    }
  }
}