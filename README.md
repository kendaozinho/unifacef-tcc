# unifacef-tcc

## Requisitos
* Java 8
* Maven

## Instalar as dependências e compilar o projeto
```sh
mvn clean install
```

## Inicializar a aplicação
```sh
mvn spring-boot:run
```

## Executar os testes
```sh
mvn clean verify
```
OBS: O relatório da cobertura dos testes fica [neste arquivo](./target/jacoco/index.html).

## Swagger
```
http://localhost:8080/swagger-ui/
https://unifacef-tcc.herokuapp.com/swagger-ui/
```
