package ziraja.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class DoAnswerEvaluationEvent extends GwtEvent<DoAnswerEvaluationEventHandler>{
  public static Type<DoAnswerEvaluationEventHandler> TYPE = new Type<DoAnswerEvaluationEventHandler>();

  @Override
  public Type<DoAnswerEvaluationEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(final DoAnswerEvaluationEventHandler handler) {
    handler.onEvaluate(this);
  }
}
