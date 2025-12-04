# Topic Exchange Örnekleri

## Açıklama
Topic Exchange, routing key pattern matching kullanır. Wildcard karakterler ile esnek eşleşme yapılabilir.

## Pattern Karakterleri
- `*` (yıldız): Tek bir kelime yerine geçer
- `#` (hash): Sıfır veya daha fazla kelime yerine geçer

## Kullanım

### Producer
```java
@Autowired
private TopicExchangeProducer producer;

// Futbol haberleri
producer.sendFootballNews("Gol oldu!");

// Basketbol haberleri
producer.sendBasketballNews("Maç başladı");

// Tenis haberleri
producer.sendTennisNews("Turnuva devam ediyor");
```

### Consumer
Consumer `spor.#` pattern'ine uyan tüm mesajları alır:
- ✅ `spor.futbol` → Eşleşir
- ✅ `spor.basketbol` → Eşleşir
- ✅ `spor.tenis` → Eşleşir
- ✅ `spor.futbol.maç` → Eşleşir
- ❌ `teknoloji.haber` → Eşleşmez

## Pattern Örnekleri
- `spor.#` → `spor.futbol`, `spor.basketbol.maç`, `spor.tenis.turnuva` gibi tüm spor haberleri
- `spor.*` → `spor.futbol`, `spor.basketbol` gibi tek kelimeli spor haberleri
- `*.futbol` → `spor.futbol`, `haber.futbol` gibi futbol ile biten haberler

