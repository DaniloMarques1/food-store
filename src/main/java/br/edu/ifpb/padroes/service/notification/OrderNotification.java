package br.edu.ifpb.padroes.service.notification;

import br.edu.ifpb.padroes.domain.Customer;

public class OrderNotification implements Listener {
    private String emailAdministration = "contact@food-store.com";

    @Override
    public void notifyEmail(String message) {
        System.out.println("send order mail notification to "+ emailAdministration);
    }

    @Override
    public void notifyEmail(String message, Customer customer) {
        System.out.println("send order mail notification to "+ customer.getEmail());
    }
}
