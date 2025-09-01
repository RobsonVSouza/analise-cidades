# Sistema de Análise de Cidades Brasileiras

Este projeto é um desafio que envolve o consumo de APIs externas (IBGE e OpenWeather), uso de cache com Redis, e diversas práticas recomendadas com Spring Boot.

## Funcionalidades esperadas

- Listar estados e municípios do Brasil
- Consultar clima atual de cidades
- Gerar análises por estado (quantidade de cidades, médias de temperatura, etc.)

## Tecnologias

- Java 8
- Spring Boot
- Feign Client
- Redis (via Spring Cache)
- Docker & Docker Compose
- JUnit / Mockito

## Como rodar

```bash
docker-compose up --build
```

## Endpoints esperados

- `GET /estados`
- `GET /municipios?estado=UF`
- `GET /clima?municipio=Nome`
- `GET /analise/estado?sigla=UF`
