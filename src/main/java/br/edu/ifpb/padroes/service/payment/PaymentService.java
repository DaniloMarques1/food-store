package br.edu.ifpb.padroes.service.payment;

public class PaymentService {

    public enum PaymentType implements IPaymentType {
        CREDIT_CARD {
            @Override
            public void doPayment() {
                System.out.println("Do credit card payment!");
            }
        },
        DEBIT {
            @Override
            public void doPayment() {
                System.out.println("Do debit payment!");
            }
        },
        BILLET {
            @Override
            public void doPayment() {
                System.out.println("Do billet payment!");
            }
        },
        PAYPAL {
            @Override
            public void doPayment() {
                System.out.println("Do paypal payment!");
            }
        }
    }

    public void doPayment(IPaymentType type) {
        type.doPayment();
    }

}
