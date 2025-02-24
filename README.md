# 🚀 Hazelcast POC – High-Performance Distributed Caching

## 🔥 O Projekcie

Ten Proof of Concept (POC) demonstruje potężne możliwości **Hazelcast** jako **rozproszonego cache’a i systemu przetwarzania w pamięci** (In-Memory Data Grid – IMDG). Dzięki temu projektowi zobaczysz, jak wykorzystać **Spring Boot** i **Hazelcast** do budowy **wysoce skalowalnej, wieloinstancyjnej** aplikacji działającej w **Dockerze, Kubernetes i OpenShift**.

Niech baza danych odpocznie – **przyspiesz swoje aplikacje o rzędy wielkości**! 💥

---

## 🏗️ Kluczowe Funkcje
✅ **Wysokowydajny cache rozproszony** – błyskawiczne operacje na danych w pamięci RAM, bez czekania na bazę.
✅ **Automatyczna replikacja i partycjonowanie** – skalowanie poziome bez utraty wydajności.
✅ **Obsługa wielu środowisk** – uruchamiaj w **Dockerze, Kubernetes - Minikube i OpenShift**.
✅ **Failover i odporność na awarie** – każda instancja to część klastra, co gwarantuje **wysoką dostępność**.
✅ **Spring Boot integration** – łatwa konfiguracja i pełna kompatybilność.

---

## 📂 Struktura projektu

- **`src/main/java/com/mknieszner/hazelcastpoc`** – Główna logika aplikacji.
    - `HazelcastPocApplication.java` – Główna klasa Spring Boot.
    - `ProductService.java` – Warstwa usługowa.

- **Obsługa danych (JPA + Hazelcast Cache)**
    - `Product.java` – Model encji.
    - `ProductRepository.java` – Repozytorium JPA.
    - `JpaProductRepository.java` – Implementacja repozytorium z wykorzystaniem Hazelcast.

- **Zaawansowana konfiguracja Hazelcast**
    - `CacheConfig.java` – Konfiguracja IMDG.
    - `LocalHazelcastConfig.java` – Konfiguracja Hazelcast uruchamianego Lokalnie.
    - `DokcerHazelcastConfig.java` – Konfiguracja Hazelcast dla Dockera.
    - `KubernetesHazelcastConfig.java` – Hazelcast w klastrze Kubernetes/OpenShift.

- **Interfejs API REST**
    - `HealthController.java` – Monitorowanie aplikacji.
    - `ProductController.java` – API zarządzania produktami.

- **Pliki konfiguracyjne**
    - `pom.xml` – Konfiguracja Maven.
    - `Dockerfile` – Tworzenie obrazu Docker.
    - `deploy/docker-compose.yml` – Automatyzacja uruchamiania w Dockerze.
    - `deploy/kube-deploy.yaml` – Manifesty Kubernetes.
    - `deploy/os-deploy.yaml` – OpenShift Deployment.

- **Przykłady**
    - `doc/sample-requests.http` – Przykładowe zapytania HTTP do testowania API.
---

## 🛠 Wymagania
- **Java 17+**
- **Maven 3+**
- **Docker** (do lokalnych testów)
- **Minikube/Kubernetes** (do uruchamiania w chmurze)
- **OpenShift CLI** (opcjonalnie)

---

## 🚀 Uruchomienie Lokalnie

1. **Zbuduj aplikację**:
   ```sh
   mvn clean package
   ```

2. **Uruchom PIERWSZĄ instancje Spring Boot**:
   ```sh
   java -jar target/hazelcast-poc-1.0.0.jar --spring.profiles.active=DEV --server.port=8080 --hazelcast.port=5701
   ```

3. **Sprawdź status**:
   ```sh
   curl http://localhost:8080
   ```

4. **Uruchom DRUGĄ instancje Spring Boot**:
   ```sh
   java -jar target/hazelcast-poc-1.0.0.jar --spring.profiles.active=DEV --server.port=8081 --hazelcast.port=5702
   ```

5. **Sprawdź status**:
   ```sh
   curl http://localhost:8081
   ```

---

## 🐳 Uruchomienie w Dockerze

1. **Zbuduj obraz**:
   ```sh
   docker-compose build
   ```
2. **Uruchom kontener**:
   ```sh
   docker-compose up -d
   ```
3. **Sprawdź status**:
   ```sh
   curl http://localhost
   ```
4. **Zatrzymaj kontenery**:
   ```sh
   docker-compose down
   ```

📌 **Dodatkowe przydatne komendy Dokcer znajdziesz tutaj:** [Dokcer CLI – Przydatne Komendy](doc/docker-commands.md)

---

## ☸️ Wdrożenie w Minikube

1. **Uruchom Minikube** (jeśli nie działa):
   ```sh
   minikube start
   ```
2. **Zastosuj konfigurację Minikube**:
   ```sh
   kubectl apply -f kube-deploy.yaml
   ```
3. **Sprawdź działające pody**:
   ```sh
   kubectl get pods
   ```

📌 **Dodatkowe przydatne komendy Minikube znajdziesz tutaj:** [Minikube CLI – Przydatne Komendy](doc/kube-commands.md)

---

## 🏢 Wdrożenie w OpenShift

1. **Zaloguj się do OpenShift**:
   ```sh
   oc login --token=<your_token> --server=<your_server>
   ```
2. **Zastosuj konfigurację OpenShift**:
   ```sh
   oc apply -f os-deploy.yaml
   ```
3. **Sprawdź działające pody**:
   ```sh
   oc get pods
   ```

📌 **Dodatkowe przydatne komendy OpenShift znajdziesz tutaj:** [OpenShift CLI – Przydatne Komendy](doc/os-commands.md)

---

## 🎯 Dlaczego Hazelcast?

💡 **Brak pojedynczego punktu awarii** – dane rozproszone w wielu instancjach.
💡 **Błyskawiczny dostęp** – cacheowanie w pamięci RAM eliminuje zapytania do bazy.
💡 **Dynamiczne skalowanie** – nowy węzeł dołącza do klastra automatycznie.
💡 **Elastyczne wdrożenie** – działa w każdym środowisku: bare metal, VM, kontenery.

Hazelcast to **high-performance caching i rozproszony system pamięci masowej**, gotowy na **poważne obciążenia i mikroserwisy**! 🚀🔥
