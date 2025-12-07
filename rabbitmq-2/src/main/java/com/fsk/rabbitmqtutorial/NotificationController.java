package com.fsk.rabbitmqtutorial;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationProducerService notificationProducerService;

    @PostMapping("/sms")
    public ResponseEntity<String> sendSmsNotification(@RequestParam String message) {
        notificationProducerService.sendNotificationToQueue(QueueConstants.SMS_ROUTING_KEY, message);
        return ResponseEntity.ok("SMS notification sent successfully");
    }

    @PostMapping("/email")
    public ResponseEntity<String> sendEmailNotification(@RequestParam String message) {
        notificationProducerService.sendNotificationToQueue(QueueConstants.EMAIL_ROUTING_KEY, message);
        return ResponseEntity.ok("Email notification sent successfully");
    }

    @PostMapping("/slack")
    public ResponseEntity<String> sendSlackNotification(@RequestParam String message) {
        notificationProducerService.sendNotificationToQueue(QueueConstants.SLACK_ROUTING_KEY, message);
        return ResponseEntity.ok("Slack notification sent successfully");
    }

}
