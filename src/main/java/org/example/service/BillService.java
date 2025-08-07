package org.example.service;

import org.example.client.NotificationClient;
import org.example.domain.Bill;
import org.example.dto.EmailRequest;
import org.example.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService {
    private final BillRepository billRepository;
    private final NotificationClient notificationClient;

    @Autowired
    public BillService(BillRepository billRepository, NotificationClient notificationClient) {
        this.billRepository = billRepository;
        this.notificationClient = notificationClient;
    }

    public Bill getBillById(Integer billId) {
        return billRepository.findById(billId).orElse(null);
    }

    public Bill saveBill(Bill bill) {
        return billRepository.save(bill);
    }

    public Bill updateBill(Integer billId, Bill bill) {
        Bill existingBill = getBillById(billId);
        if (existingBill != null) {
            existingBill.setOrderItems(bill.getOrderItems());
            existingBill.setTotalCost(bill.getTotalCost());
            existingBill.setCounterSale(bill.isCounterSale());
            existingBill.setCustomerName(bill.getCustomerName());
            existingBill.setCustomerPhone(bill.getCustomerPhone());
            return billRepository.save(existingBill);
        }
        return null;
    }

    public void deleteBill(Integer billId) {
        billRepository.deleteById(billId);
    }

    public void sendBillNotification(String email, String subject, String body) {
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setTo(email);
        emailRequest.setSubject(subject);
        emailRequest.setBody(body);
        notificationClient.sendEmail(emailRequest);
    }
}
