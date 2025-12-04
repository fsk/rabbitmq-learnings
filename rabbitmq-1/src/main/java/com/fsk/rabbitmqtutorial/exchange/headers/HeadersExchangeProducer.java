package com.fsk.rabbitmqtutorial.exchange.headers;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Headers Exchange için mesaj gönderen örnek Producer sınıfı
 * 
 * Headers Exchange'de routing key önemsizdir.
 * Mesajlar, header bilgilerine göre eşleşen kuyruklara yönlendirilir.
 * 
 * Bu örnekte:
 * - dosya-tipi: pdf
 * - gizlilik: yüksek
 * header'larına sahip mesajlar eşleşir.
 */
@Component
public class HeadersExchangeProducer {

    private final RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE_NAME = "exchange.headers.test";

    public HeadersExchangeProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * PDF ve yüksek gizlilik header'ları ile mesaj gönderir (eşleşir)
     */
    public void sendPdfHighSecurityMessage(String message) {
        MessageProperties properties = new MessageProperties();
        properties.setHeader("dosya-tipi", "pdf");
        properties.setHeader("gizlilik", "yüksek");
        
        Message msg = new Message(message.getBytes(), properties);
        rabbitTemplate.send(EXCHANGE_NAME, "", msg);
        System.out.println("Sent to Headers Exchange [PDF, High Security]: " + message);
    }

    /**
     * PDF ama düşük gizlilik header'ları ile mesaj gönderir (eşleşmez)
     */
    public void sendPdfLowSecurityMessage(String message) {
        MessageProperties properties = new MessageProperties();
        properties.setHeader("dosya-tipi", "pdf");
        properties.setHeader("gizlilik", "düşük");
        
        Message msg = new Message(message.getBytes(), properties);
        rabbitTemplate.send(EXCHANGE_NAME, "", msg);
        System.out.println("Sent to Headers Exchange [PDF, Low Security]: " + message);
        System.out.println("⚠️ This message will be lost - headers don't match!");
    }

    /**
     * Word dosyası header'ı ile mesaj gönderir (eşleşmez)
     */
    public void sendWordDocumentMessage(String message) {
        MessageProperties properties = new MessageProperties();
        properties.setHeader("dosya-tipi", "docx");
        properties.setHeader("gizlilik", "yüksek");
        
        Message msg = new Message(message.getBytes(), properties);
        rabbitTemplate.send(EXCHANGE_NAME, "", msg);
        System.out.println("Sent to Headers Exchange [Word, High Security]: " + message);
        System.out.println("⚠️ This message will be lost - headers don't match!");
    }

    /**
     * Özel header'lar ile mesaj gönderme
     */
    public void sendCustomHeadersMessage(String message, Map<String, Object> headers) {
        MessageProperties properties = new MessageProperties();
        headers.forEach(properties::setHeader);
        
        Message msg = new Message(message.getBytes(), properties);
        rabbitTemplate.send(EXCHANGE_NAME, "", msg);
        System.out.println("Sent to Headers Exchange [Custom Headers]: " + message);
        System.out.println("Headers: " + headers);
    }

    /**
     * Örnek kullanım: Farklı header kombinasyonları
     */
    public void sendExampleMessages() {
        sendPdfHighSecurityMessage("Headers Message: Secret PDF File");
        
        Map<String, Object> customHeaders = new HashMap<>();
        customHeaders.put("dosya-tipi", "pdf");
        customHeaders.put("gizlilik", "yüksek");
        customHeaders.put("departman", "muhasebe");
        sendCustomHeadersMessage("Custom Headers Message", customHeaders);
        
        sendPdfLowSecurityMessage("This message will be lost");
        sendWordDocumentMessage("This message will also be lost");
    }
}

