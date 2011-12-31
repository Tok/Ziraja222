package ziraja.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class UpdateTotalEvent extends GwtEvent<UpdateTotalEventHandler>{
  public static Type<UpdateTotalEventHandler> TYPE = new Type<UpdateTotalEventHandler>();

  @Override
  public Type<UpdateTotalEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(final UpdateTotalEventHandler handler) {
    handler.onUpdated(this);
  }
}
