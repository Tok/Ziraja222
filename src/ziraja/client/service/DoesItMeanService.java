package ziraja.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("doesItMeanService")
public interface DoesItMeanService extends RemoteService {
  String doesItMean(String input);
}
