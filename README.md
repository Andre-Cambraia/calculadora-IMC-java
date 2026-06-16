# Calculadora de IMC

Projeto Java de console para cadastro de pessoas e atletas, calculo de IMC, classificacao e historico da sessao.

## Requisitos

- JDK instalado.
- Java configurado no `PATH`, ou uso do caminho completo para `java.exe` e `javac.exe`.

Para verificar se o Java esta disponivel no terminal:

```powershell
java -version
javac -version
```

Se o comando `javac` nao for reconhecido, use o caminho completo do JDK. Neste computador, o caminho usado nos testes foi:

```text
C:\Program Files\Java\jdk-26.0.1\bin
```

## Como Compilar Pelo PowerShell

Abra o PowerShell ou Windows Terminal e entre na pasta do projeto:

```powershell
cd "CAMINHO_DA_PASTA\calculadora-imc"
```

Substitua `CAMINHO_DA_PASTA` pelo local onde o projeto esta salvo.

Compile os arquivos Java para a pasta `out`:

```powershell
javac -encoding UTF-8 -d out .\src\*.java
```

Se `javac` nao estiver no `PATH`, use:

```powershell
& "C:\Program Files\Java\jdk-26.0.1\bin\javac.exe" -encoding UTF-8 -d out .\src\*.java
```

## Como Rodar

Depois de compilar, execute a classe principal:

```powershell
java -cp out Main
```

Se `java` nao estiver no `PATH`, use:

```powershell
& "C:\Program Files\Java\jdk-26.0.1\bin\java.exe" -cp out Main
```

## Como Usar o Menu

Ao iniciar o programa, escolha uma das opcoes:

```text
1 - Cadastrar nova pessoa
2 - Calcular e exibir IMC
3 - Classificar IMC
4 - Cadastrar atleta
5 - Exibir historico
0 - Sair
```

Observacoes:

- Cadastre uma pessoa ou atleta antes de calcular/classificar.
- O historico mostra os cadastros feitos durante a execucao atual.
- Peso e altura aceitam ponto ou virgula como separador decimal, por exemplo `70.5` ou `70,5`.

## Opcional: Compilar Com Maven

Se o Maven estiver instalado, tambem e possivel compilar com:

```powershell
mvn compile
```

Neste projeto simples, o uso direto de `javac` e `java` ja e suficiente.
