/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI_Structures;

/**
 *
 * @author Boey
 */
public class PaymentThread extends Thread {
    private boolean paymentStatus;

    public PaymentThread() {
        paymentStatus = false;
    }

    public boolean isPaymentSuccess() {
        return paymentStatus;
    }

    @Override
    public void run() {
        // Simulate payment processing time
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        paymentStatus = true;
        System.out.println("Payment Success");
    }
}
