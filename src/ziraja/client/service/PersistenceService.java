package ziraja.client.service;

import java.util.ArrayList;

import ziraja.shared.data.Report;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("persistenceService")
public interface PersistenceService extends RemoteService {
	void saveReport(Report report);
	ArrayList<Report> getReports();
	void deleteReport(final Report report);
}
