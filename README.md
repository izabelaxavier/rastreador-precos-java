# 🔍 Rastreador de Preços Resiliente - Spring Boot

Este projeto é um sistema de monitoramento automatizado de preços desenvolvido em **Java** com **Spring Boot**. Ele foi projetado para realizar Web Scraping de forma assíncrona, capturando informações de produtos, autores e preços em tempo real, com foco em resiliência e auditoria de erros.

## 🚀 Tecnologias Utilizadas
- **Java 17** (ou a versão que você estiver usando)
- **Spring Boot 3.x**
- **Jsoup**: Para extração e manipulação de dados HTML.
- **Maven**: Gerenciamento de dependências.
- **Spring Scheduling**: Para automação de tarefas repetitivas.

## ⚙️ Funcionalidades
- **Monitoramento Automático**: Utiliza `@Scheduled` para verificar o preço do produto em intervalos definidos.
- **Web Scraping Avançado**: Captura Título, Autor e Preço, tratando seletores CSS dinâmicos.
- **Tratamento de Dados**: Uso de **Regex** para limpeza de strings e conversão monetária.
- **Log de Auditoria**: Sistema de logs persistentes em arquivo `.txt` para rastreamento de falhas de conexão (ex: Erro 403) ou mudanças de layout no site alvo.

## 🛠️ Arquitetura (Clean Code)
O projeto segue as melhores práticas de divisão de responsabilidades:
- `model`: Representação do objeto Produto.
- `service`: Lógica de extração de dados (Scraper).
- `scheduler`: Controle do ciclo de repetição das tarefas.
- `util`: Ferramentas de suporte, como o serviço de log.

## 📈 Desafios Superados
Durante o desenvolvimento, foram aplicadas técnicas de **Header Spoofing** (User-Agent) para contornar bloqueios de acesso automatizado e implementada uma lógica de **Fallback de Seletores** para garantir que o sistema continue operando mesmo com pequenas alterações no site alvo.

## 📝 Como Executar
1. Clone o repositório.
2. Certifique-se de ter o Maven instalado.
3. Execute a classe `RastreadorPrecosApplication`.
4. Acompanhe o monitoramento pelo console ou pelo arquivo de logs na pasta `util`.

## 🚀 Diário de Evolução: Parte 2 - Persistência com Spring Data JPA (10/02/2026)
Nesta fase, o projeto deixou de ser apenas um monitor temporário e passou a salvar o histórico de capturas de forma permanente.

### ✅ O que foi implementado hoje:
* **Mapeamento JPA**: Transformação da classe `Produto` em uma entidade gerenciada pelo banco de dados.
* **Spring Data JPA**: Criação do `ProdutoRepository` para salvar as informações de preço e horário automaticamente.
* **Banco de Dados H2**: Configuração de um banco em memória para auditoria rápida e visualização das tabelas.
* **Integração Scraper + DB**: O serviço de captura agora injeta o repositório e persiste cada dado extraído com sucesso.

### 🔍 Monitoramento do Banco
Agora é possível visualizar a tabela de preços via navegador enquanto o sistema roda:
- **URL**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **User**: `sa`

## 🚀 Diário de Evolução: Parte 3 - Inteligência de Persistência (10/02/2026)
Implementação de lógica para evitar redundância de dados no histórico.

### ✅ O que foi implementado:
* **Comparação de Estado**: O sistema agora consulta o último preço registrado no banco antes de realizar um novo `save`.
* **Otimização de Armazenamento**: Registros só são criados se houver alteração no valor do produto, economizando recursos do banco de dados.
* **Query Customizada**: Uso de `findFirstByOrderByDataConsultaDesc` no Repository para capturar o estado mais recente do produto.

---

---
*Projeto desenvolvido por Izabela Xavier como parte dos estudos em Análise e Desenvolvimento de Sistemas.*