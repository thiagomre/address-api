# Address API

## Stack
* Java 11+
* Gradle 6.0+
* SpringBoot 2.2.3
* Banco de Dados H2 em memória, modo Oracle
* [Lombok](https://projectlombok.org/)

## Rodando o projeto
* Foi criado um shell script para automatizar o deploy local da api.
  Executar no terminal o comando abaixo, que permite que ele seja executado como um script:
  ```
    chmod +x gradlew addressRun.sh
  ```
  
  Este script faz o build do projeto, depois faz o build do docker e sobe a aplicação.
Depois de realizar o passo acima basta executar o próximo comando no terminal: 
  
    ```
    ./addressRun.sh
    ```
