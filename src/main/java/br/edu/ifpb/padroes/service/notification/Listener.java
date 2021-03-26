package br.edu.ifpb.padroes.service.notification;

import br.edu.ifpb.padroes.domain.Customer;

public interface Listener {
    void notifyEmail(String message);
    void notifyEmail(String message, Customer customer);
}
