# 🚀 **Docker Compose – Przydatne Komendy**

## 🔐 **Uruchomienie Kontenerów**
Aby uruchomić wszystkie usługi zdefiniowane w `docker-compose.yml`, użyj:

```bash
docker-compose up -d
```

**Sprawdzenie uruchomionych kontenerów:**
```bash
docker ps
```

---

## ⚡ **Tworzenie lub aktualizowanie usług w Docker Compose**
Jeśli zmieniłeś konfigurację w `docker-compose.yml` lub `Dockerfile`, musisz odbudować obrazy:

```bash
docker-compose up -d --build
```

**Zatrzymanie i usunięcie wszystkich kontenerów:**
```bash
docker-compose down
```

---

## 🗑️ **Usuwanie kontenerów i sieci**
Aby usunąć **wszystkie** kontenery i sieci powiązane z `docker-compose.yml`, użyj:

```bash
docker-compose down
```

Lub jeśli chcesz również usunąć **obrazy i wolumeny**:
```bash
docker-compose down --rmi all --volumes
```

---

## 📊 **Weryfikacja stanu usług**
### 🔍 **Listowanie działających kontenerów**
```bash
docker ps
```

### 📌 **Podgląd logów z kontenerów**
```bash
docker-compose logs -f
```

### 🎯 **Podgląd logów konkretnej usługi**
```bash
docker-compose logs -f <NAZWA_USŁUGI>
```
**Przykład:**
```bash
docker-compose logs -f hazelcast-app1
```

---

## 🌐 **Sieci i komunikacja między usługami**
### 🔎 **Listowanie dostępnych sieci Docker**
```bash
docker network ls
```

### 🔎 **Znalezienie odpowiedniej sieci Docker Compose**
Docker Compose może nadawać sieciom nazwy z prefiksem katalogu projektu. Jeśli Twoja sieć nie nazywa się dokładnie `hazelcast-net`, znajdź jej nazwę:
```bash
docker network ls | grep hazelcast
```

### 🔎 **Listowanie kontenerów w sieci Docker Compose**
Po znalezieniu poprawnej sieci (np. `deploy_hazelcast-net`), możesz zobaczyć podłączone do niej kontenery:
```bash
docker network inspect deploy_hazelcast-net | jq '.[0].Containers | keys'
```
Lub pełne szczegóły sieci:
```bash
docker network inspect deploy_hazelcast-net
```

### 🔎 **Sprawdzenie komunikacji między kontenerami**
#### **1. Sprawdzenie `ping` w kontenerze**
Niektóre obrazy Docker nie mają domyślnie zainstalowanego `ping`. Możesz sprawdzić, czy jest dostępny:
```bash
docker exec -it hazelcast-app1 sh -c "which ping"
```
Jeśli nie zwróci żadnej ścieżki, oznacza to, że `ping` nie jest zainstalowany.

#### **2. Instalacja `ping` w kontenerze (tymczasowo)**
Dla **Debiana/Ubuntu**:
```bash
docker exec -it hazelcast-app1 sh -c "apt update && apt install -y iputils-ping"
```
Dla **Alpine Linux**:
```bash
docker exec -it hazelcast-app1 sh -c "apk add --no-cache iputils"
```
Po instalacji spróbuj wywołać komende ping.
Ta komenda uruchamia proces ping w działającym kontenerze hazelcast-app1 i próbuje wysłać pakiety ICMP do hazelcast-app2,
aby sprawdzić, czy kontenery mogą się wzajemnie komunikować w sieci Docker.
```bash
docker exec -it hazelcast-app1 ping hazelcast-app2
```
🚨 **UWAGA:** Ta zmiana jest **tymczasowa** i zniknie po restarcie kontenera.

### 🏷️ **Podłączenie do kontenera PostgreSQL**
```bash
docker exec -it postgres-db psql -U user -d products_db
```

---

## 🎯 **Podsumowanie**
✅ **`docker-compose up -d`** – uruchomienie kontenerów  
✅ **`docker-compose up -d --build`** – odbudowanie i uruchomienie usług  
✅ **`docker-compose down`** – zatrzymanie i usunięcie usług  
✅ **`docker ps`** – sprawdzenie działających kontenerów  
✅ **`docker-compose logs -f`** – podgląd logów  
✅ **`docker-compose restart <USŁUGA>`** – restartowanie wybranej usługi  
✅ **`docker exec -it <KONTENER> <KOMENDA>`** – wejście do działającego kontenera  
✅ **`docker network ls`** – sprawdzenie dostępnych sieci Docker  
✅ **`docker network inspect <NAZWA_SIECI>`** – sprawdzenie, które usługi działają w danej sieci  
✅ **`docker exec -it hazelcast-app1 ping hazelcast-app2`** – sprawdzenie komunikacji między usługami (jeśli `ping` jest dostępny)

📌 **Przydatne w zarządzaniu kontenerami i debuggingu usług w środowisku Docker Compose!** 🚀

