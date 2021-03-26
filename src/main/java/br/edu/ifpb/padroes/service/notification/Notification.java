package br.edu.ifpb.padroes.service.notification;

import br.edu.ifpb.padroes.domain.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Notification {
    private Map<String, List<Listener>> noticationsMap = new HashMap<>();

    public Notification(String... operations) {
        for (String operation: operations) {
            this.noticationsMap.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, Listener listener) {
        List<Listener> listeners = this.noticationsMap.get(eventType);
        listeners.add(listener);
    }

    public void unsubscribe(String eventType, Listener listener) {
        List<Listener> listeners = this.noticationsMap.get(eventType);
        listeners.remove(listener);
    }

    public void notifyOrder(String eventType, String message) {
        List<Listener> listeners = this.noticationsMap.get(eventType);
        for (Listener listener: listeners) {
            listener.notifyEmail(message);
        }
    }

    public void notifyOrder(String eventType, String message, Customer customer) {
        List<Listener> listeners = this.noticationsMap.get(eventType);
        for (Listener listener: listeners) {
            listener.notifyEmail(message, customer);
        }
    }
}
