# Headers Exchange Örnekleri

## Açıklama
Headers Exchange'de routing key önemsizdir. Mesajlar, header bilgilerine göre eşleşen kuyruklara yönlendirilir. Header-based routing için idealdir.

## Kullanım

### Producer
```java
@Autowired
private HeadersExchangeProducer producer;

// PDF ve yüksek gizlilik header'ları ile mesaj gönder
producer.sendPdfHighSecurityMessage("Gizli PDF dosyası");

// Özel header'lar ile mesaj gönder
Map<String, Object> headers = new HashMap<>();
headers.put("dosya-tipi", "pdf");
headers.put("gizlilik", "yüksek");
headers.put("departman", "muhasebe");
producer.sendCustomHeadersMessage("Özel mesaj", headers);
```

### Consumer
Consumer sadece şu header'lara sahip mesajları alır:
- `dosya-tipi: pdf`
- `gizlilik: yüksek`

`whereAll()` kullanıldığı için TÜM header'lar eşleşmeli.

## Header Eşleşmesi
- ✅ `dosya-tipi: pdf` + `gizlilik: yüksek` → Eşleşir
- ❌ `dosya-tipi: pdf` + `gizlilik: düşük` → Eşleşmez
- ❌ `dosya-tipi: docx` + `gizlilik: yüksek` → Eşleşmez

## Kullanım Senaryoları
- 🔒 Güvenlik seviyesine göre mesaj yönlendirme
- 📄 Dosya tipine göre işleme
- 🏢 Departman bazlı mesajlaşma
- 🏷️ Metadata bazlı routing

