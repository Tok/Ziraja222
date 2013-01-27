package ziraja.server.service;

import java.io.IOException
import java.net.MalformedURLException

import com.google.gwt.user.server.rpc.RemoteServiceServlet
import ziraja.client.service.DoesItMeanService

class DoesItMeanServiceImpl extends RemoteServiceServlet with DoesItMeanService {
  val dym = "Did you mean:"
  val sif = ">Search instead for</span>"
  val srf = ">Showing results for</span>"

  /**
   * Sends a query to google and returns the value of the
   * "Did you mean" function or ?? if there is no suggestion.
   */
  override def doesItMean(input: String): String = {
    try {
      val src = io.Source.fromURL("http://www.google.com/search?lang=en&hl=en&q=" + input).mkString
      if (src != null && src.contains(dym)) ((src.split(dym)(1)).split("<i>")(1)).split("</i>")(0)
      else if (src != null && src.contains(sif)) ((src.split(sif)(1)).split(">")(1)).split("</a>")(0)
      else if (src != null && src.contains(srf)) ((src.split(srf)(1)).split("<i>")(1)).split("</i>")(0)
      else "???"
    } catch {
      case murle: MalformedURLException => "FAIL: URL malformed."
      case ioe: IOException => "??"
    }
  }
}