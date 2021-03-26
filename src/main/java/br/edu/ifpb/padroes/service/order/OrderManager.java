package br.edu.ifpb.padroes.service.order;

import br.edu.ifpb.padroes.domain.Order;
import br.edu.ifpb.padroes.service.log.LogHandler;
import br.edu.ifpb.padroes.service.log.LogService;
import br.edu.ifpb.padroes.service.notification.CancelOrderNotification;
import br.edu.ifpb.padroes.service.notification.Notification;
import br.edu.ifpb.padroes.service.notification.OrderNotification;
import br.edu.ifpb.padroes.service.notification.RefusedOrderNotification;
import br.edu.ifpb.padroes.service.payment.PaymentService;

public class OrderManager {
    private Notification notification = new Notification("cancel", "order", "refused");

    public OrderManager(Order order) {
        this.order = order;

        RefusedOrderNotification refusedOrderNotification = new RefusedOrderNotification();
        OrderNotification orderNotification = new OrderNotification();
        CancelOrderNotification cancelOrderNotification = new CancelOrderNotification();

        this.notification.subscribe("refused", refusedOrderNotification);
        this.notification.subscribe("cancel", cancelOrderNotification);
        this.notification.subscribe("order", orderNotification);

    }

    private Order order;



    private PaymentService paymentService = new PaymentService();

    private LogService logService = new LogService(new LogHandler(LogHandler.LogHandlerType.FILE));

    public void payOrder(PaymentService.PaymentType paymentType) {
        order.setStatus(Order.OrderStatus.IN_PROGRESS);
        try {
            paymentService.doPayment(paymentType);
            order.setStatus(Order.OrderStatus.PAYMENT_SUCCESS);
            notification.notifyOrder("order", String.format("Order %d completed successfully", order.getId()));
            logService.info("payment finished");
        } catch (Exception e) {
            logService.error("payment refused");
            order.setStatus(Order.OrderStatus.PAYMENT_REFUSED);
            notification.notifyOrder("refused", String.format("Order %d refused", order.getId()));
        }
    }

    public void cancelOrder() {
        order.setStatus(Order.OrderStatus.CANCELED);
        notification.notifyOrder("cancel", String.format("Order %d canceled", order.getId()));
        logService.debug(String.format("order %d canceled", order.getId()));
    }

}
