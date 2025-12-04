package com.fsk.rabbitmqtutorial.exchange.headers;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Headers Exchange için mesaj dinleyen örnek Consumer sınıfı
 * 
 * Headers Exchange'de mesajlar header bilgilerine göre eşleşir.
 * Bu consumer sadece şu header'lara sahip mesajları alır:
 * - dosya-tipi: pdf
 * - gizlilik: yüksek
 * 
 * whereAll() kullanıldığı için TÜM header'lar eşleşmeli.
 */
@Component
public class HeadersExchangeConsumer {

    /**
     * Header'lara göre eşleşen mesajları dinler
     */
    @RabbitListener(queues = "queue.headers.test")
    public void receiveHeadersMessage(Message message) {
        String body = new String(message.getBody());
        Map<String, Object> headers = message.getMessageProperties().getHeaders();
        
        System.out.println("=== Message Received from Headers Exchange ===");
        System.out.println("Message: " + body);
        System.out.println("Queue: queue.headers.test");
        System.out.println("Exchange: exchange.headers.test");
        System.out.println("Headers:");
        headers.forEach((key, value) -> 
            System.out.println("  " + key + ": " + value)
        );
        System.out.println("================================");
    }

    /**
     * Örnek: Header bilgilerine göre işlem yapma
     */
    @RabbitListener(queues = "queue.headers.test")
    public void processHeadersMessage(Message message) {
        String body = new String(message.getBody());
        Map<String, Object> headers = message.getMessageProperties().getHeaders();
        
        String dosyaTipi = (String) headers.get("dosya-tipi");
        String gizlilik = (String) headers.get("gizlilik");
        
        if ("pdf".equals(dosyaTipi) && "yüksek".equals(gizlilik)) {
            System.out.println("🔒 Processing High Security PDF: " + body);
            // Özel güvenlik işlemleri buraya yazılabilir
        }
        
        // Diğer header'ları kontrol et
        if (headers.containsKey("departman")) {
            String departman = (String) headers.get("departman");
            System.out.println("📁 Department: " + departman);
        }
    }
}

