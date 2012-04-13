package ziraja.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class DoAnswerEvaluationEvent extends GwtEvent<DoAnswerEvaluationEventHandler> {
    public static final Type<DoAnswerEvaluationEventHandler> TYPE = new Type<DoAnswerEvaluationEventHandler>();

    @Override
    public final Type<DoAnswerEvaluationEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected final void dispatch(final DoAnswerEvaluationEventHandler handler) {
        handler.onEvaluate(this);
    }
}
