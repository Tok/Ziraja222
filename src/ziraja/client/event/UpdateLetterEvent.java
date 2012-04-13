package ziraja.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class UpdateLetterEvent extends GwtEvent<UpdateLetterEventHandler> {
    public static final Type<UpdateLetterEventHandler> TYPE = new Type<UpdateLetterEventHandler>();

    @Override
    public final Type<UpdateLetterEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected final void dispatch(final UpdateLetterEventHandler handler) {
        handler.onUpdated(this);
    }
}
