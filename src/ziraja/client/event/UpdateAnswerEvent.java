package ziraja.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class UpdateAnswerEvent extends GwtEvent<UpdateAnswerEventHandler> {
    public static final Type<UpdateAnswerEventHandler> TYPE = new Type<UpdateAnswerEventHandler>();

    @Override
    public final Type<UpdateAnswerEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected final void dispatch(final UpdateAnswerEventHandler handler) {
        handler.onUpdated(this);
    }
}
