package ziraja.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class UpdateTotalEvent extends GwtEvent<UpdateTotalEventHandler> {
    public static final Type<UpdateTotalEventHandler> TYPE = new Type<UpdateTotalEventHandler>();

    @Override
    public final Type<UpdateTotalEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected final void dispatch(final UpdateTotalEventHandler handler) {
        handler.onUpdated(this);
    }
}
