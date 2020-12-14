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

## Swagger
```
http://localhost:8080/swagger-ui/
```

## Realizar o deploy no DockerHub
```sh
mvn clean package

docker login --username=kennethgaz

docker build -t kennethgaz/unifacef-tcc:0.0.1 .
docker push kennethgaz/unifacef-tcc:0.0.1
```
