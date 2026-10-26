package eventbroker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

final public class EventBroker {

    protected Set<EventListener> listeners = new HashSet<EventListener>();
    protected Map<String, Set<EventListener>> listenersPerType = new HashMap<String, Set<EventListener>>();
    protected final static EventBroker broker = new EventBroker();

    private EventBroker() {
    }

    public static EventBroker getEventBroker() {
        return broker;
    }

    public void addEventListener(EventListener s) {
        listeners.add(s);
    }

    public void addEventListener(String type, EventListener s) {
        Set<EventListener> set = listenersPerType.get(type);
        if (set == null) {
            set = new HashSet<EventListener>();
            listenersPerType.put(type, set);
        }
        set.add(s);
    }

    public void removeEventListener(EventListener s) {
        listeners.remove(s);
    }

    void addEvent(EventPublisher source, Event e) {
        process(source, e);
    }

    private void process(EventPublisher source, Event e) {
        for (EventListener l : listeners) {
            if (l != source) {
                l.handleEvent(e); // prevent loops !
            }
        }

        Set<EventListener> typed = listenersPerType.get(e.getType());
        if (typed != null) {
            for (EventListener l : typed) {
                if (l != source) {
                    l.handleEvent(e);
                }
            }
        }
    }
}
