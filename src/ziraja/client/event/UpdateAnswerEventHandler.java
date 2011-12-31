package ziraja.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface UpdateAnswerEventHandler extends EventHandler {
  void onUpdated(final UpdateAnswerEvent event);
}
