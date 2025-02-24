# 🚀 **Minikube CLI – Przydatne Komendy**

## 🔐 **Uruchomienie Minikube**
Aby uruchomić lokalny klaster Minikube, użyj komendy:

```bash
minikube start
```

**Sprawdzenie statusu klastra:**
```bash
minikube status
```

---

## ⚡ **Tworzenie lub aktualizowanie zasobów w Minikube**
Komenda `kubectl apply -f` pozwala na **tworzenie i aktualizację** zasobów Kubernetes w Minikube na podstawie pliku YAML/JSON.

```bash
kubectl apply -f <PLIK_KONFIGURACYJNY>
```

**Przykład:**
```bash
kubectl apply -f kube-deploy.yaml
```

---

## 🗑️ **Usuwanie zasobów w Minikube**
Aby usunąć zasoby Kubernetes w Minikube, użyj `kubectl delete -f`:

```bash
kubectl delete -f <PLIK_KONFIGURACYJNY>
```

**Przykład:**
```bash
kubectl delete -f kube-deploy.yaml
```

---

## 📊 **Weryfikacja stanu klastra**
### 🔍 **Listowanie Podów**
```bash
kubectl get pods
```

### 📌 **Listowanie Deploymentów i Service'ów**
```bash
kubectl get deployments,services
```

---

## 🌐 **Service Discovery w Minikube**
### 🔎 **Listowanie Podów wykrytych przez Service 'hazelcast-service'**

```bash
kubectl get pods --selector=$(kubectl get svc hazelcast-service -o json | jq -r '.spec.selector | to_entries | map("\(.key)=\(.value)") | join(",")')
```
**Przykład:**
```bash
kubectl get pods --selector=$(kubectl get svc hazelcast-service -o json | jq -r '.spec.selector | to_entries | map("\(.key)=\(.value)") | join(",")')
```
**Przykładowa odpowiedź:**
```
NAME                                        READY   STATUS    RESTARTS   AGE
hazelcast-poc-deployment-5cdb846485-4qkvr   1/1     Running   0          59m
hazelcast-poc-deployment-5cdb846485-6db2q   1/1     Running   0          59m
```

### 🏷️ **Listowanie Podów po selektorze zdefiniowanym w Service**

```bash
kubectl get pods -l app=<NAZWA_APLIKACJI>
```
**Przykład:**
```bash
kubectl get pods -l app=hazelcast
```
**Przykładowa odpowiedź:**
```
NAME                                        READY   STATUS    RESTARTS   AGE
hazelcast-poc-deployment-5cdb846485-4qkvr   1/1     Running   0          59m
hazelcast-poc-deployment-5cdb846485-6db2q   1/1     Running   0          59m
```

### 📝 **Przeglądanie logów wszystkich instancji aplikacji zgodnie z etykietami Deployment**
#### **Etykiety - Sprawdź template.metadata.labels w Deployment w kube-deploy.yaml**

```bash
kubectl logs -l app=<NAZWA_APLIKACJI>
```
**Przykład:**
```bash
kubectl logs -l app=hazelcast
```
To polecenie pobierze logi **wszystkich** Podów oznaczonych etykietą `app=hazelcast`. Jest to przydatne, aby zweryfikować poprawne działanie mechanizmu Service Discovery.

### 🔄 **Przeglądanie logów pojedynczej instancji aplikacji**
```bash
kubectl logs -f <POD_NAME>
```
**Przykład:**
```bash
kubectl logs -f hazelcast-poc-deployment-5cdb846485-4qkvr
```

### 📜 **Sprawdzanie szczegółów zasobów**
```bash
kubectl describe <RESOURCE_TYPE> <RESOURCE_NAME>
```
**Przykład:**
```bash
kubectl describe pod hazelcast-poc-deployment-5cdb846485-4qkvr
```

---

## 🎯 **Podsumowanie**
✅ **`minikube start`** – uruchomienie klastra Minikube  
✅ **`kubectl apply -f`** – tworzenie/aktualizacja zasobów  
✅ **`kubectl delete -f`** – usuwanie zasobów  
✅ **`kubectl get pods`** – sprawdzanie uruchomionych Podów  
✅ **`kubectl get deployments,services`** – sprawdzanie stanu aplikacji  
✅ **`kubectl get pods --selector`** – wykrywanie instancji usług  
✅ **`kubectl logs`** – przeglądanie logów  
✅ **`kubectl logs -l`** – sprawdzanie logów wszystkich Podów po labelach  
✅ **`kubectl exec`** – wejście do Poda  
✅ **`kubectl describe`** – szczegółowe informacje o zasobach

📌 **Przydatne w zarządzaniu lokalnym klastrem Kubernetes i debuggingu usług!** 🚀

