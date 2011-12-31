package ziraja.server.service;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import ziraja.client.service.PersistenceService;
import ziraja.shared.data.Report;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class PersistenceServiceImpl extends RemoteServiceServlet implements	PersistenceService {
	private static final long serialVersionUID = -1692278752476830291L;
	private static final PersistenceManagerFactory PMF = JDOHelper.getPersistenceManagerFactory("transactions-optional");

	public PersistenceServiceImpl() {
	}

	@Override
	public void saveReport(Report report) {
		final PersistenceManager pm = PMF.getPersistenceManager();
		final Extent<Report> extent = pm.getExtent(Report.class, false);
		int count = 0;
		for (Report existingReport : extent) {
			count++;
			if (count >= 222) {
				pm.deletePersistent(existingReport);
			}
		}
		try {
			pm.makePersistent(report);
		} finally {
			pm.close();
		}
	}

	@Override
	public ArrayList<Report> getReports() {
		final PersistenceManager pm = PMF.getPersistenceManager();
		ArrayList<Report> result = new ArrayList<Report>();
		try {
			Query query = pm.newQuery(Report.class);
			query.setRange(0, 222);
			@SuppressWarnings("unchecked")
			List<Report> tmp = (List<Report>) query.execute();
			result.addAll(tmp);
		} finally {
			pm.close();
		}
		return result;
	}

	@Override
	public void deleteReport(Report report) {
		final PersistenceManager pm = PMF.getPersistenceManager();
		try {
			pm.deletePersistent(pm.getObjectById(Report.class, report.getId()));
		} finally {
			pm.close();
		}
	}
}
