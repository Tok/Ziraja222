package ziraja.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class UpdateLetterEvent extends GwtEvent<UpdateLetterEventHandler>{
  public static Type<UpdateLetterEventHandler> TYPE = new Type<UpdateLetterEventHandler>();

  @Override
  public Type<UpdateLetterEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(final UpdateLetterEventHandler handler) {
    handler.onUpdated(this);
  }
}
