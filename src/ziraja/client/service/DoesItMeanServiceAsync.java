package ziraja.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DoesItMeanServiceAsync {
    void doesItMean(String input, AsyncCallback<String> callback);
}
