package ziraja.server.service

import com.google.gwt.user.server.rpc.RemoteServiceServlet
import ziraja.client.service.PersistenceService
import javax.jdo.Extent
import javax.jdo.JDOHelper
import javax.jdo.PersistenceManager
import javax.jdo.PersistenceManagerFactory
import javax.jdo.Query;
import ziraja.shared.data.Report

class PersistenceServiceImpl extends RemoteServiceServlet with PersistenceService {
  val PMF = JDOHelper.getPersistenceManagerFactory("transactions-optional")
  var MAX_COUNT = 222
  
  //wrapper to make java collections iterable for scala
  implicit def iteratorToWrapper[T](iter: java.util.Iterator[T]): IteratorWrapper[T] = new IteratorWrapper[T](iter)
  class IteratorWrapper[A](iter: java.util.Iterator[A]) {
    def foreach(f: A => Unit): Unit = {
      while (iter.hasNext) {
        f(iter.next)
      }
    }
  }
  
  override def saveReport(report: Report): Unit = {
    val PM = PMF.getPersistenceManager()
    val extent = PM.getExtent(classOf[Report], false) //Extent<Report> 
    var count = 0
    for (existingReport <- extent.iterator()) { //Report
      count += 1
      if (count >= MAX_COUNT) {
        PM.deletePersistent(existingReport)
      }
    }
    try {
      PM.makePersistent(report)
    } finally {
      PM.close()
    }
  }

  override def getReports(): java.util.ArrayList[Report] = {
    val result = new java.util.ArrayList[Report]()
    val PM = PMF.getPersistenceManager()
    try {
      var query = PM.newQuery(classOf[Report])
      query.setRange(0, MAX_COUNT)
      val tmp = query.execute().asInstanceOf[java.util.List[Report]]
      result.addAll(tmp)
    } finally {
      PM.close()
    }
    return result
  }

  override def deleteReport(report: Report): Unit = {
    val PM = PMF.getPersistenceManager()
    try {
      PM.deletePersistent(PM.getObjectById(classOf[Report], report.getId()))
    } finally {
      PM.close()
    }
  }

}