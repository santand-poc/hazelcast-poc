# Hazelcast POC

## 📌 Opis projektu
Ten projekt to Proof of Concept (POC) pokazujący, jak skonfigurować i uruchomić Hazelcast wbudowany w aplikację Java opartą na Spring Boot. Aplikacja obsługuje różne środowiska uruchomieniowe: **Docker, Kubernetes i OpenShift**.

## 📂 Struktura projektu

- **`src/main/java/com/mknieszner/hazelcastpoc`** – Główna logika aplikacji.
  - `HazelcastPocApplication.java` – Główna klasa Spring Boot.
  - `ProductService.java` – Logika biznesowa zarządzania produktami.
  
- **Obsługa danych (JPA)**
  - `Product.java` – Encja JPA.
  - `ProductRepository.java` – Interfejs repozytorium.
  - `JpaProductRepository.java` – Implementacja repozytorium.

- **Konfiguracja Hazelcast dla różnych środowisk**
  - `CacheConfig.java` – Główna konfiguracja cache.
  - `DokcerHazelcastConfig.java` – Konfiguracja dla Dockera.
  - `KubernetesHazelcastConfig.java` – Konfiguracja dla Kubernetes.
  - `OpenshiftHazelcastConfig.java` – Konfiguracja dla OpenShift.

- **Kontrolery REST**
  - `HealthController.java` – Endpoint zdrowotności aplikacji.
  - `ProductController.java` – API do zarządzania produktami.

- **Pliki konfiguracyjne**
  - `pom.xml` – Konfiguracja Maven.
  - `Dockerfile` – Plik do budowania obrazu Docker.
  - `docker-compose.yml` – Konfiguracja dla Docker Compose.
  - `kube-deploy.yaml` – Manifest Kubernetes.
  - `os-deploy.yaml` – Manifest OpenShift.

## 🛠 Wymagania
- Java 17+
- Maven 3+
- Docker (opcjonalnie)
- Minikube/Kubernetes (opcjonalnie)
- OpenShift CLI (opcjonalnie)

## 🚀 Uruchomienie lokalne

**Zbudowanie aplikacji**:
   ```sh
   mvn clean package
   ```

## 🐳 Uruchomienie w Dockerze

1. **Zbudowanie obrazu Docker**:
   ```sh
   docker-compose build
   ```
2. **Uruchomienie kontenera**:
   ```sh
   docker-compose up -d
   ```
3. **Sprawdzenie działania**:
   ```sh
   curl http://localhost:8080/health
   ```
4. **Zatrzymanie kontenera**:
   ```sh
   docker-compose down
   ```

## ☸️ Wdrożenie w Kubernetes

1. **Uruchomienie Minikube (jeśli nie działa)**:
   ```sh
   minikube start
   ```
2. **Zastosowanie konfiguracji**:
   ```sh
   kubectl apply -f kube-deploy.yaml
   ```
3. **Sprawdzenie działania**:
   ```sh
   kubectl get pods
   ```

## 🏢 Wdrożenie w OpenShift

1. **Logowanie do OpenShift**:
   ```sh
   oc login --token=<your_token> --server=<your_server>
   ```
2. **Zastosowanie konfiguracji**:
   ```sh
   oc apply -f os-deploy.yaml
   ```
3. **Sprawdzenie działania**:
   ```sh
   oc get pods
   ```

## 📜 Licencja
Ten projekt jest udostępniony na licencji MIT.
