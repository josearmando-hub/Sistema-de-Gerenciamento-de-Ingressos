<img width="1303" height="811" alt="image" src="https://github.com/user-attachments/assets/ae0f05c2-45e0-4311-9a97-fc26f8f6c295" />
<img width="781" height="683" alt="imagem (1)" src="https://github.com/user-attachments/assets/796d208d-c57b-4121-af23-bf3d8508396e" />
<img width="1024" height="682" alt="Diagrama Jose" src="https://github.com/user-attachments/assets/a63ec402-0734-460d-9832-8c0d29c5fdfb" />

Sistema Web de Gestão de Ingressos
Um sistema web completo (Full-Stack) para venda, cancelamento e devolução de ingressos, desenvolvido com foco na aplicação prática de conceitos avançados de Programação Orientada a Objetos (POO), como Herança e Polimorfismo.

📖 Sobre o Projeto
Esta aplicação foi construída como parte dos requisitos acadêmicos do curso de Sistemas de Informação. O sistema simula uma plataforma de eventos onde os usuários podem se cadastrar, realizar login, comprar diferentes tipos de ingressos com cálculos dinâmicos de preço, e solicitar o cancelamento ou estorno de suas compras respeitando as regras de negócio definidas (Máquina de Estados).

📋 Requisitos do Sistema
Requisitos Funcionais (RF)
RF01: O sistema deve permitir o cadastro de usuários, diferenciando os perfis entre Cliente e Administrador.

RF02: O sistema deve permitir a autenticação (login) de usuários e manter o isolamento dos dados (cada cliente acessa apenas seus ingressos).

RF03: O sistema deve exibir um catálogo de eventos disponíveis para os clientes.

RF04: O sistema deve permitir que clientes realizem a compra/reserva de ingressos de diferentes tipos (Normal, VIP e Meia-entrada).

RF05: O sistema deve calcular dinamicamente o valor final do ingresso com base no seu tipo (aplicação de polimorfismo).

RF06: O sistema deve gerenciar a disponibilidade de vagas, decrescendo a quantidade de ingressos do evento no momento da reserva.

RF07: O sistema deve gerar um QR Code único para cada ingresso emitido.

RF08: O sistema deve permitir que o cliente solicite o cancelamento ou a devolução (estorno) de ingressos de acordo com a política e o tempo da reserva.

RF09: O sistema deve permitir que Administradores validem a entrada de clientes fazendo a leitura/validação do QR Code.

RF10: O sistema deve controlar e atualizar o estado de cada ingresso rigorosamente seguindo o fluxo de vida pré-definido.

Requisitos Não Funcionais (RNF)
RNF01: O back-end deve ser desenvolvido em Java (JDK 17+) utilizando o framework Spring Boot.

RNF02: O sistema deve utilizar a arquitetura RESTful (Spring MVC).

RNF03: Os dados devem ser persistidos no banco de dados NoSQL MongoDB.

RNF04: A herança e polimorfismo dos modelos (Ingresso e Usuario) devem ser refletidos no MongoDB através do atributo de controle _class.

RNF05: O front-end deve ser responsivo e construído com HTML5, CSS3, Vanilla JavaScript e Bootstrap 5.

RNF06: O controle de sessão de acesso no front-end deve ser gerenciado através de SessionStorage.

👥 Diagrama de Casos de Uso (Visão Geral)
A interação do sistema divide-se entre dois atores principais: Cliente e Administrador.

Ator: Cliente (Autenticado)

Fazer Login no sistema.

Visualizar o catálogo de Eventos.

Selecionar Evento e Reservar Ingresso (Normal, VIP ou Meia).

Pagar Ingresso (ação que aciona a emissão e geração do QR Code).

Cancelar Reserva (se o tempo expirar ou por decisão própria antes do pagamento).

Solicitar Devolução/Estorno (após pagamento e geração).

Ator: Administrador (Autenticado)

Fazer Login no painel administrativo.

Validar Entrada com QR Code no momento do evento. O sistema checa se o estado é Emitido (aprovando a entrada e mudando para Usado) ou se já foi utilizado/rejeitado.

🧩 Arquitetura e Componentes do Sistema
O projeto segue uma arquitetura multicamadas clássica para aplicações web:

Frontend (Camada de Apresentação): * Composto por interfaces web (Telas de Cliente e Admin). Comunica-se de forma assíncrona com o backend via Fetch API enviando e consumindo dados em formato JSON.

Backend (Camada Lógica e de Serviços REST):

Controllers: UsuarioController, EventoController, IngressoController, AdminController. Recebem as requisições HTTP e direcionam o fluxo.

Services: UsuarioService, EventoService, IngressoService. Contêm as regras de negócio, cálculo polimórfico de ingressos, geração de QR Code e orquestração da Máquina de Estados.

Repositories: Interfaces do Spring Data MongoDB para acesso aos dados (UsuarioRepository, EventoRepository, IngressoRepository).

Banco de Dados (Camada de Persistência): * MongoDB atuando com 3 coleções principais (usuarios, eventos, ingressos). Utiliza o campo _class para mapear corretamente qual subtipo de classe Java deve ser instanciado durante a leitura dos documentos.

✨ Funcionalidades e Regras de Negócio
Gestão de Ingressos (Polimorfismo aplicado):

🎟️ Ingresso Normal: Valor base do evento.

⭐ Ingresso VIP: Valor base + Taxa adicional VIP.

🎓 Ingresso Meia-Entrada: Valor base com aplicação de percentual de desconto.

Máquina de Estados do Ingresso:

O ciclo de vida do ingresso obedece ao fluxo: DISPONÍVEL ➔ RESERVADO ➔ PAGO ➔ EMITIDO ➔ USADO.

Caminhos alternativos: CANCELADO (se a reserva expirar ou for interrompida pelo usuário) ou DEVOLVIDO (após análise da solicitação de estorno de um ingresso emitido).

🛠️ Tecnologias Utilizadas
Back-end:

Java (JDK 17+)

Spring Boot (Web, Data MongoDB)

Spring MVC (Arquitetura RESTful)

Jackson (Deserialização polimórfica com @JsonTypeInfo e @JsonSubTypes)

Banco de Dados:

MongoDB (NoSQL)

Front-end:

HTML5 / CSS3

JavaScript (Vanilla / Fetch API)

Bootstrap 5 (Estilização UI/UX)

SessionStorage (Gerenciamento de sessão no navegador)

🚀 Como Executar o Projeto
Pré-requisitos
Java JDK 17+ instalado.

Maven instalado.

MongoDB rodando localmente (porta 27017) ou um cluster no MongoDB Atlas.

Uma IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code).

Passos para rodar:
Clone este repositório:

Bash
git clone https://github.com/SeuUsuario/sistema-ingressos.git
Abra o projeto na sua IDE e aguarde o download das dependências do Maven.

Certifique-se de que o serviço do MongoDB esteja ativo na sua máquina.

Execute a classe principal da aplicação (SistemaIngressosApplication.java).

Acesse o sistema pelo navegador na URL: http://localhost:8080

👨‍💻 Autor
José Armando Abrão Boer
Bacharelando em Sistemas de Informação - Universidade de Mogi das Cruzes (UMC)
