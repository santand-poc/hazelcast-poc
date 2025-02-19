# 🚀 Hazelcast POC – High-Performance Distributed Caching

## 🔥 O Projekcie

Ten Proof of Concept (POC) demonstruje potężne możliwości **Hazelcast** jako **rozproszonego cache’a i systemu przetwarzania w pamięci** (In-Memory Data Grid – IMDG). Dzięki temu projektowi zobaczysz, jak wykorzystać **Spring Boot** i **Hazelcast** do budowy **wysoce skalowalnej, wieloinstancyjnej** aplikacji działającej w **Dockerze, Kubernetes i OpenShift**.

Niech baza danych odpocznie – **przyspiesz swoje aplikacje o rzędy wielkości**! 💥

---

## 🏗️ Kluczowe Funkcje
✅ **Wysokowydajny cache rozproszony** – błyskawiczne operacje na danych w pamięci RAM, bez czekania na bazę.
✅ **Automatyczna replikacja i partycjonowanie** – skalowanie poziome bez utraty wydajności.
✅ **Obsługa wielu środowisk** – uruchamiaj w **Dockerze, Kubernetes i OpenShift**.
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
    - `DokcerHazelcastConfig.java` – Konfiguracja Hazelcast dla Dockera.
    - `KubernetesHazelcastConfig.java` – Hazelcast w klastrze Kubernetes.
    - `OpenshiftHazelcastConfig.java` – Hazelcast w OpenShift.

- **Interfejs API REST**
    - `HealthController.java` – Monitorowanie aplikacji.
    - `ProductController.java` – API zarządzania produktami.

- **Pliki konfiguracyjne**
    - `pom.xml` – Konfiguracja Maven.
    - `Dockerfile` – Tworzenie obrazu Docker.
    - `docker-compose.yml` – Automatyzacja uruchamiania w Dockerze.
    - `kube-deploy.yaml` – Manifesty Kubernetes.
    - `os-deploy.yaml` – OpenShift Deployment.

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

2. **Uruchom Spring Boot**:
   ```sh
   java -jar target/hazelcast-poc-1.0.0.jar --spring.profiles.active=DEV
   ```

3. **Sprawdź status**:
   ```sh
   curl http://localhost:8080
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

---

## ☸️ Wdrożenie w Kubernetes

1. **Uruchom Minikube** (jeśli nie działa):
   ```sh
   minikube start
   ```
2. **Zastosuj konfigurację Kubernetes**:
   ```sh
   kubectl apply -f kube-deploy.yaml
   ```
3. **Sprawdź działające pody**:
   ```sh
   kubectl get pods
   ```

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

---

## 🎯 Dlaczego Hazelcast?

💡 **Brak pojedynczego punktu awarii** – dane rozproszone w wielu instancjach.
💡 **Błyskawiczny dostęp** – cacheowanie w pamięci RAM eliminuje zapytania do bazy.
💡 **Dynamiczne skalowanie** – nowy węzeł dołącza do klastra automatycznie.
💡 **Elastyczne wdrożenie** – działa w każdym środowisku: bare metal, VM, kontenery.

Hazelcast to **high-performance caching i rozproszony system pamięci masowej**, gotowy na **poważne obciążenia i mikroserwisy**! 🚀🔥
