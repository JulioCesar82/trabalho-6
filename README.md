# Configuração de Bancos de Dados com Docker

Comandos Docker para subir os bancos de dados MySQL e MongoDB utilizados pela aplicação.

MySQL:Para iniciar um contêiner MySQL com as configurações necessárias:

```bash
docker run --name mysql-db -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=loja -p 3306:3306 -d mysql:8.0
```

MongoDB: Para iniciar um contêiner MongoDB:

```bash
docker run --name mongodb-db -p 27017:27017 -d mongo:latest
```

Parar e Remover os Contêineres:

```bash
docker stop mysql-db mongodb-db
```

Para remover os contêineres (após pará-los):

```bash
docker rm mysql-db mongodb-db

## Como Executar a Aplicação

A aplicação é um projeto Maven. Para compilá-la e executá-la, siga os passos abaixo:

1.  **Compilar o Projeto:**
    Navegue até o diretório raiz do projeto (onde o `pom.xml` está localizado) e execute o seguinte comando para compilar o projeto:

    ```bash
    mvn clean install
    ```

2.  **Executar a Aplicação:**
    Após a compilação, você pode executar a aplicação com o seguinte comando:

    ```bash
    mvn exec:java -Dexec.mainClass="MainApplication"
    ```
    A aplicação irá pedir para você escolher o tipo de repositório (Em Memória, MySQL ou MongoDB).
