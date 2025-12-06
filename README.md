# RabbitMQ Tutorial - Multi-Module Project

Bu proje, RabbitMQ'nun farklı Exchange türlerini öğrenmek için oluşturulmuş bir multi-module Maven projesidir.

## Proje Yapısı

```
rabbitmq-tutorial/
├── pom.xml (parent pom)
├── rabbitmq-1/
│   ├── pom.xml
│   └── src/
│       └── main/java/com/fsk/rabbitmqtutorial/
│           ├── exchange/
│           │   ├── direct/      # Direct Exchange örnekleri
│           │   ├── fanout/      # Fanout Exchange örnekleri
│           │   ├── topic/       # Topic Exchange örnekleri
│           │   └── headers/     # Headers Exchange örnekleri
│           └── controller/      # REST Controller
└── rabbitmq-2/
    ├── pom.xml
    └── src/
```

## RabbitMQ Kurulumu

### Docker ile

Proje ile aynı Docker network'ünde çalışacak şekilde:

```bash
# RabbitMQ'yu başlat (port mapping yok - sadece container içinde çalışır)
docker-compose up -d

# RabbitMQ'yu durdur
docker-compose down

# Logları görüntüle
docker-compose logs -f rabbitmq

# Container durumunu kontrol et
docker-compose ps
```

## Uygulama Yapılandırması

RabbitMQ container ile aynı network'te çalışması için `application.properties` dosyasına:

```properties
spring.rabbitmq.host=rabbitmq
spring.rabbitmq.port=5672
spring.rabbitmq.username=admin
spring.rabbitmq.password=admin123
```

## Exchange Türleri

### 1. Direct Exchange
Routing key'e göre tam eşleşme yapar. Mesajlar sadece routing key'i tam olarak eşleşen kuyruklara gönderilir.

**Örnek:** `exchange.direct.test` → `queue.direct.test` (routing key: `kirmizi`)

### 2. Fanout Exchange
Routing key önemsizdir. Mesaj, exchange'e bağlı TÜM kuyruklara gönderilir. Broadcast senaryoları için idealdir.

**Örnek:** `exchange.fanout.test` → `queue.fanout.test.1` ve `queue.fanout.test.2`

### 3. Topic Exchange
Routing key pattern matching kullanır. Wildcard karakterler ile esnek eşleşme yapılabilir.

**Pattern Karakterleri:**
- `*` (yıldız): Tek bir kelime yerine geçer
- `#` (hash): Sıfır veya daha fazla kelime yerine geçer

**Örnek:** `exchange.topic.test` → `queue.topic.test` (pattern: `spor.#`)

### 4. Headers Exchange
Routing key önemsizdir. Mesajlar, header bilgilerine göre eşleşen kuyruklara yönlendirilir.

**Örnek:** `exchange.headers.test` → `queue.headers.test` (header: `dosya-tipi=pdf`, `gizlilik=yüksek`)

## Kullanım

### Projeyi Çalıştırma

```bash
# Tüm modülleri derle
mvn clean install

# rabbitmq-1 modülünü çalıştır
cd rabbitmq-1
mvn spring-boot:run
```

### API Endpoints

Proje çalıştıktan sonra aşağıdaki endpoint'leri kullanabilirsiniz:

- `GET /api/send/all` - Tüm exchange türlerine mesaj gönderir
- `GET /api/send/direct` - Sadece Direct Exchange'e mesaj gönderir
- `GET /api/send/fanout` - Sadece Fanout Exchange'e mesaj gönderir
- `GET /api/send/topic` - Sadece Topic Exchange'e mesaj gönderir
- `GET /api/send/headers` - Sadece Headers Exchange'e mesaj gönderir

## Bağımlılıklar

Tüm bağımlılıklar parent `pom.xml`'de tanımlıdır:

- Spring Boot Starter AMQP
- Spring Boot Starter WebMVC
- Spring Rabbit Stream
- Lombok
- Spring Boot Test bağımlılıkları

## Gereksinimler

- Java 21
- Maven 3.6+
- Docker & Docker Compose
- RabbitMQ Server (Docker ile çalıştırılabilir)

## Örnek Kullanım

Her exchange türü için ayrı paketlerde örnek Producer ve Consumer sınıfları bulunmaktadır:

- `DirectExchangeProducer` / `DirectExchangeConsumer`
- `FanoutExchangeProducer` / `FanoutExchangeConsumer`
- `TopicExchangeProducer` / `TopicExchangeConsumer`
- `HeadersExchangeProducer` / `HeadersExchangeConsumer`

Her pakette detaylı açıklamalar ve örnekler bulunmaktadır.

## Docker Network

Uygulama ve RabbitMQ aynı Docker network'ünde çalışır. Port mapping yapılmadığı için sadece container'lar arası iletişim mümkündür.

## Lisans

Bu proje eğitim amaçlıdır.
