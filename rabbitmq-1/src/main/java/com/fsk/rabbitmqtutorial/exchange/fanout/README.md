# Fanout Exchange Örnekleri

## Açıklama
Fanout Exchange'de routing key önemsizdir. Mesaj, exchange'e bağlı TÜM kuyruklara gönderilir. Broadcast (yayın) senaryoları için idealdir.

## Kullanım

### Producer
```java
@Autowired
private FanoutExchangeProducer producer;

// Tüm kuyruklara mesaj gönder
producer.sendBroadcastMessage("Herkese duyuru!");

// Duyuru mesajı gönder
producer.sendAnnouncement("Sistem bakımı başlıyor");
```

### Consumer
İki farklı consumer iki farklı kuyruktan mesaj dinler:
- `queue.fanout.test.1` → Email gönderme servisi
- `queue.fanout.test.2` → SMS gönderme servisi

## Kullanım Senaryoları
- 📧 Email bildirimleri
- 📱 SMS bildirimleri
- 🔔 Push notification'lar
- 📢 Genel duyurular

