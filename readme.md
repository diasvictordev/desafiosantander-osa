# Desafio Santander - Cadastro de Agências e Distância

API REST que permite o **cadastro de agências** com coordenadas e o **cálculo de distâncias** até elas, com base em uma posição informada pelo usuário.

---

## 🚀 Base URL

```
http://localhost:8080/desafio
```

---

## 📌 Endpoints

### ▶️ Cadastrar Agência

**POST** `/cadastrar`

* Cadastra uma nova agência com coordenadas `posX` e `posY`.
* Campos obrigatórios: `posX`, `posY`.

**Resposta esperada**:

* `201 Created` com os dados da agência cadastrada.
* `400 Bad Request` se faltar algum campo.

---

### 📏 Calcular Distâncias

**GET** `/distancia?posX={valor}&posY={valor}`

* Retorna uma lista de agências ordenadas pela menor distância até a posição informada.
* As distâncias são calculadas em tempo real e armazenadas em cache para otimizar chamadas repetidas.

**Resposta esperada**:

* `200 OK` com as agências e suas respectivas distâncias.
* `400 Bad Request` se algum parâmetro for omitido ou inválido.

---

## 📖 Documentação Swagger

Acesse a interface interativa em:

```
http://localhost:8080/swagger-ui.html
```

---

## 🧪 Console do Banco (H2)

Para visualizar os dados salvos:

```
http://localhost:8080/h2-console
```

* JDBC URL: `jdbc:h2:file:./data/desafiosantander`
* Usuário: `desafio`

---

## ✅ Requisitos

* Java 17+
* Maven
* Spring Boot

---

## Melhorias incluídas
- DTOs
- Tratamento global de exceções
- Cache (Caffeine) para cálculos mais rápidos de distância
- Testes dos services
- Swagger/OpenAPI
