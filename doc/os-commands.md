# 🚀 **OpenShift CLI – Przydatne Komendy**

## 🔐 **Logowanie do OpenShift**
Aby zalogować się do klastra OpenShift, użyj komendy:

```bash
oc login --token=<TOKEN> --server=<API_SERVER>
```
**Przykład:**
```bash
oc login --token=sha256~tcua_abc123xyz --server=https://api.example.openshift.com:6443
```

---

## ⚡ **Tworzenie lub aktualizowanie zasobów OpenShift**
Komenda `oc apply -f` pozwala na **tworzenie i aktualizację** zasobów OpenShift na podstawie pliku YAML/JSON.

```bash
oc apply -f <PLIK_KONFIGURACYJNY> -n <NAMESPACE>
```

**Przykład:**
```bash
oc apply -f os-deploy.yaml -n mateusz-dev
```

---

## 🗑️ **Usuwanie zasobów OpenShift**
Aby usunąć zasoby OpenShift, użyj `oc delete -f`:

```bash
oc delete -f <PLIK_KONFIGURACYJNY> -n <NAMESPACE>
```

**Przykład:**
```bash
oc delete -f os-deploy.yaml -n mateusz-dev
```

---

## 📊 **Weryfikacja stanu namespace**
### 🔍 **Listowanie Podów**
```bash
oc get pods -n <NAMESPACE>
```
**Przykład:**
```bash
oc get pods -n mateusz-dev
```

### 📌 **Listowanie Deploymentów i Service'ów**
```bash
oc get deployments,services -n <NAMESPACE>
```
**Przykład:**
```bash
oc get deployments,services -n mateusz-dev
```

---

## 🌐 **Service Discovery w OpenShift**
### 🔎 **Listowanie Podów wykrytych przez Service 'hazelcast-service'**

```bash
oc get pods --selector=$(oc get svc hazelcast-service -o json | jq -r '.spec.selector | to_entries | map("\(.key)=\(.value)") | join(",")') -n <NAMESPACE>
```
**Przykład:**
```bash
oc get pods --selector=$(oc get svc hazelcast-service -o json | jq -r '.spec.selector | to_entries | map("\(.key)=\(.value)") | join(",")') -n mateusz-dev
```
**Przykładowa odpowiedź:**
```
NAME                                        READY   STATUS    RESTARTS   AGE
hazelcast-poc-deployment-5cdb846485-4qkvr   1/1     Running   0          59m
hazelcast-poc-deployment-5cdb846485-6db2q   1/1     Running   0          59m
```

### 🏷️ **Listowanie Podów po selektorze zdefiniowanym w Service**

```bash
oc get pods -l app=<NAZWA_APLIKACJI> -n <NAMESPACE>
```
**Przykład:**
```bash
oc get pods -l app=hazelcast -n mateusz-dev
```
**Przykładowa odpowiedź:**
```
NAME                                        READY   STATUS    RESTARTS   AGE
hazelcast-poc-deployment-5cdb846485-4qkvr   1/1     Running   0          59m
hazelcast-poc-deployment-5cdb846485-6db2q   1/1     Running   0          59m
```

### 📝 **Przeglądanie logów wszystkich instancji aplikacji zgodnie z etykietami Deployment**
#### **Etykiety - Sprawdź template.metadata.labels w Deployment w os-deploy.yaml**

```bash
oc logs -l app=<NAZWA_APLIKACJI> -n <NAMESPACE>
```
**Przykład:**
```bash
oc logs -l app=hazelcast -n mateusz-dev
```
To polecenie pobierze logi **wszystkich** Podów oznaczonych etykietą `app=hazelcast` w namespace `mateusz-dev`. Jest to przydatne, aby zweryfikować poprawne działanie mechanizmu Service Discovery.

### 🔄 **Przeglądanie logów pojedyńczej instancji aplikacji**
```bash
oc logs -f <POD_NAME> -n <NAMESPACE>
```
**Przykład:**
```bash
oc logs -f hazelcast-poc-deployment-5cdb846485-4qkvr -n mateusz-dev
```

### 📜 **Sprawdzanie szczegółów zasobów**
```bash
oc describe <RESOURCE_TYPE> <RESOURCE_NAME> -n <NAMESPACE>
```
**Przykład:**
```bash
oc describe pod hazelcast-poc-deployment-5cdb846485-4qkvr -n mateusz-dev
```

---

## 🎯 **Podsumowanie**
✅ **`oc login`** – logowanie do klastra OpenShift  
✅ **`oc apply -f`** – tworzenie/aktualizacja zasobów  
✅ **`oc delete -f`** – usuwanie zasobów  
✅ **`oc get pods`** – sprawdzanie uruchomionych Podów  
✅ **`oc get deployments,services`** – sprawdzanie stanu aplikacji  
✅ **`oc get pods --selector`** – wykrywanie instancji usług  
✅ **`oc logs`** – przeglądanie logów  
✅ **`oc logs -l`** – sprawdzanie logów wszystkich Podów po labelach  
✅ **`oc exec`** – wejście do Poda  
✅ **`oc new-app`** – szybkie tworzenie aplikacji  
✅ **`oc describe`** – szczegółowe informacje o zasobach

📌 **Przydatne w zarządzaniu klastrem OpenShift i debuggingu usług!** 🚀

