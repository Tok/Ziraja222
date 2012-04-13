package ziraja.client.service;

import java.util.ArrayList;

import ziraja.shared.data.Report;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PersistenceServiceAsync {
    void getReports(AsyncCallback<ArrayList<Report>> callback);
    void saveReport(Report report, AsyncCallback<Void> callback);
    void deleteReport(Report report, AsyncCallback<Void> callback);
}
