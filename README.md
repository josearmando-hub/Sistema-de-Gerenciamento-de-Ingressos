<img width="1303" height="811" alt="image" src="https://github.com/user-attachments/assets/ae0f05c2-45e0-4311-9a97-fc26f8f6c295" />
Um sistema web completo (Full-Stack) para venda, cancelamento e devolução de ingressos, desenvolvido com foco na aplicação prática de conceitos avançados de Programação Orientada a Objetos (POO), como **Herança** e **Polimorfismo**.
 
## 📖 Sobre o Projeto
Esta aplicação foi construída como parte dos requisitos acadêmicos do curso de Sistemas de Informação. O sistema simula uma plataforma de eventos onde os usuários podem se cadastrar, realizar login, comprar diferentes tipos de ingressos com cálculos dinâmicos de preço, e solicitar o cancelamento ou estorno de suas compras respeitando as regras de negócio definidas (Máquina de Estados).
 
## ✨ Funcionalidades
*   **Autenticação de Usuários:** Cadastro e login com isolamento de dados (o usuário acessa apenas os próprios ingressos).
*   **Gestão de Ingressos (Polimorfismo aplicado):**
    *   🎟️ **Ingresso Normal:** Valor base do evento.
    *   ⭐ **Ingresso VIP:** Valor base + Taxa adicional VIP.
    *   🎓 **Ingresso Meia-Entrada:** Valor base com aplicação de percentual de desconto.
*   **Fluxo de Cancelamento:** Permite cancelar ingressos que ainda não foram utilizados e cujo evento ainda não ocorreu.
*   **Fluxo de Devolução:** Simulação de estorno financeiro e atualização de status para ingressos válidos.
*   **Máquina de Estados:** Controle rigoroso dos status do ingresso (`DISPONIVEL`, `PAGO`, `EMITIDO`, `CANCELADO`, `DEVOLVIDO`).
 
## 🛠️ Tecnologias Utilizadas
**Back-end:**
*   Java (JDK 17+)
*   Spring Boot (Web, Data MongoDB)
*   Spring MVC (Arquitetura RESTful)
*   Jackson (Deserialização polimórfica com `@JsonTypeInfo` e `@JsonSubTypes`)
 
**Banco de Dados:**
*   MongoDB (NoSQL)
 
**Front-end:**
*   HTML5 / CSS3
*   JavaScript (Vanilla / Fetch API)
*   Bootstrap 5 (Estilização UI/UX)
*   SessionStorage (Gerenciamento de sessão no navegador)
 
## 🚀 Como Executar o Projeto
 
### Pré-requisitos
*   [Java JDK](https://www.oracle.com/java/technologies/downloads/) instalado.
*   [Maven](https://maven.apache.org/) instalado.
*   [MongoDB](https://www.mongodb.com/try/download/community) rodando localmente (porta `27017`) ou um cluster no MongoDB Atlas.
*   Uma IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code).
 
### Passos para rodar:
1. Clone este repositório:
   ```bash
   git clone [https://github.com/SeuUsuario/sistema-ingressos.git](https://github.com/SeuUsuario/sistema-ingressos.git)
 
2.Abra o projeto na sua IDE e aguarde o download das dependências do Maven.
 
3.Certifique-se de que o MongoDB esteja ativo.
 
4.Execute a classe principal da aplicação (SistemaIngressosApplication.java).
 
5.Acesse o sistema pelo navegador na URL: http://localhost:8080
 
 
👨‍💻 Autor
José Armando Abrão Boer
Bacharelando em Sistemas de Informação - Universidade Mogi das Cruzes (UMC)
