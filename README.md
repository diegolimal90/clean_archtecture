# Treinamento com Clean Architecture

Treinamento inicial Clean Architecture em aplicação Spring Boot, todas as camadas possuem sua propria documentação e classes reais para se usar de exemplo nos seus estudos.

<p align="center">
  <img alt="Java 1.8" src="https://img.shields.io/static/v1?logo=java&label=Java&message=1.8&color=blue&style=flat-square">
  <img alt="HitCount" src="http://hits.dwyl.com/MGMAdvance/treinamentocleanarch.svg">
  <img alt="License" src="https://img.shields.io/github/license/MGMAdvance/treinamentocleanarch?style=flat-square&logo=MIT">
</p>

## Estrutura de pastas

- [Core](src/main/java/br/com/treinamento/cleanarch/core)
  - [Entity](src/main/java/br/com/treinamento/cleanarch/core/domain)
  - [Gateway](src/main/java/br/com/treinamento/cleanarch/gateway)
  - [Usecase](src/main/java/br/com/treinamento/cleanarch/core/usecase)
- [DataProvider](src/main/java/br/com/treinamento/cleanarch/dataprovider)
  - [Entity](src/main/java/br/com/treinamento/cleanarch/dataprovider/entity)
  - [Feign](src/main/java/br/com/treinamento/cleanarch/dataprovider/feign)
  - [Mapper](src/main/java/br/com/treinamento/cleanarch/dataprovider/mapper)
  - [Repository](src/main/java/br/com/treinamento/cleanarch/dataprovider/repository)
- [Entrypoint](src/main/java/br/com/treinamento/cleanarch/entrypoint)
  - [Entity](src/main/java/br/com/treinamento/cleanarch/dataprovider/entity)
  - [Mapper](src/main/java/br/com/treinamento/cleanarch/dataprovider/mapper)
- [Configuration](src/main/java/br/com/treinamento/cleanarch/configuration/)
  - [Exception](src/main/java/br/com/treinamento/cleanarch/configuration/exception)
- [Resources](/src/main/resources)
  - [Configuração da Aplicação](/src/main/resources)
- [Tests](/src/test/java/br/com/treinamento/)
  - [CORE](/src/test/java/br/com/treinamento/cleanarch/core/)
    - [USECASE](/src/test/java/br/com/treinamento/cleanarch/core/usecase)
  - [DataProvider](/src/test/java/br/com/treinamento/cleanarch/dataprovider/)
  - [Entrypoint](/src/test/java/br/com/treinamento/cleanarch/entrypoint/)

## Estrutura do projeto

```sh
.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── br
│   │   │       └── com
│   │   │           └── treinamento
│   │   │               └── cleanarch
│   │   │                   ├── configuration
│   │   │                   │   └── exception
│   │   │                   │       └── entity
│   │   │                   ├── core
│   │   │                   │   ├── entity
│   │   │                   │   ├── gateway
│   │   │                   │   └── usecase
│   │   │                   ├── dataprovider
│   │   │                   │   ├── entity
│   │   │                   │   ├── feign
│   │   │                   │   ├── mapper
│   │   │                   │   └── repository
│   │   │                   └── entrypoint
│   │   │                       ├── entity
│   │   │                       └── mapper
│   │   └── resources
│   │       ├── static
│   │       └── templates
│   └── test
│       └── java
│           └── br
│               └── com
│                   └── treinamento
│                       └── cleanarch
└...
```
