package ziraja.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class UpdateAnswerEvent extends GwtEvent<UpdateAnswerEventHandler>{
  public static Type<UpdateAnswerEventHandler> TYPE = new Type<UpdateAnswerEventHandler>();

  @Override
  public Type<UpdateAnswerEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(final UpdateAnswerEventHandler handler) {
    handler.onUpdated(this);
  }
}
