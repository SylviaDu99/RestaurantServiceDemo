package org.example.service;

import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;

/*
    Sample service to demonstrate scheduled cron tasks
 */

@Service
public class ScheduledService {
    @Scheduled(cron = "0 0 * * * *")
    public void sendOrderSummaryToAdmin() {
        System.out.println("Send hourly order summary to admin");
    }
}
