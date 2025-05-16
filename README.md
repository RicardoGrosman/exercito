Sistema de Assinatura Hierárquica
Este projeto implementa um sistema de aprovação de documentos baseado na hierarquia militar do Exército Brasileiro, utilizando o padrão de projeto Chain of Responsibility em Java. O sistema garante que um documento seja assinado em ordem hierárquica, começando pelo nível mais baixo (Soldado) até o mais alto (General), com a possibilidade de encaminhar o documento ao próximo nível se o nível atual não puder assiná-lo.
Estrutura do Projeto
O projeto está organizado no pacote exercito e contém as seguintes classes:

Aprovador.java: Classe abstrata que define a estrutura base para todos os níveis hierárquicos, implementando a lógica do Chain of Responsibility.
Documento.java: Representa o documento a ser assinado, com uma lista de assinaturas e métodos para gerenciá-las.
Soldado.java, Sargento.java, Tenente.java, Capitao.java, Coronel.java, General.java: Subclasses de Aprovador, representando os níveis hierárquicos.
HierarquiaAssinaturaTest.java: Classe de teste JUnit que valida o comportamento do sistema, incluindo assinatura na ordem correta, fora da ordem, encadeamento e prevenção de assinaturas duplicadas.

Diagrama UML
O diagrama UML está disponível no arquivo diagrama.puml e pode ser visualizado com o plugin PlantUML no IntelliJ ou em ferramentas como PlantUML Web Server. Ele mostra:

A hierarquia de herança entre Aprovador e suas subclasses.
Relacionamentos bidirecionais (superior e inferior) entre instâncias de Aprovador.
Interação entre Aprovador e Documento para assinatura.

Pré-requisitos

Java: JDK 11 ou superior.
IntelliJ IDEA: Para desenvolvimento e execução dos testes.
Maven: Para gerenciamento de dependências (opcional, mas recomendado).
JUnit 5: Para executar os testes unitários.

Configuração do Projeto

Criar o Projeto:

Abra o IntelliJ IDEA e crie um novo projeto Java (File > New > Project > Java).
Nomeie o projeto, por exemplo, ExercitoBrasileiro.


Adicionar Dependência JUnit:

Se usar Maven, adicione ao pom.xml:<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.9.2</version>
    <scope>test</scope>
</dependency>


Ou adicione via IntelliJ: File > Project Structure > Libraries > + > From Maven.


Estrutura de Pacotes:

Crie o pacote exercito em src/main/java para as classes principais.
Crie o pacote exercito em src/test/java para a classe de teste.
Adicione os arquivos fornecidos:
Aprovador.java, Documento.java, Soldado.java, Sargento.java, Tenente.java, Capitao.java, Coronel.java, General.java em src/main/java/exercito.
HierarquiaAssinaturaTest.java em src/test/java/exercito.




Diagrama UML (opcional):

Instale o plugin PlantUML no IntelliJ (File > Settings > Plugins > Marketplace > PlantUML).
Crie um arquivo diagrama.puml na raiz do projeto e cole o conteúdo do diagrama UML fornecido.
Visualize o diagrama no IntelliJ ou em PlantUML Web Server.



Como Executar

Compilar o Projeto:

No IntelliJ, vá para Build > Rebuild Project para compilar todas as classes.


Executar os Testes:

Clique com o botão direito em HierarquiaAssinaturaTest.java (em src/test/java/exercito) e selecione Run 'HierarquiaAssinaturaTest'.
Os testes validam os seguintes cenários:
Assinatura na ordem hierárquica correta.
Tentativa de assinatura fora da ordem.
Encadeamento quando um nível não pode assinar.
Encadeamento até o topo da hierarquia (General) sem assinaturas prévias.
Documento sem assinaturas.
Prevenção de assinaturas duplicadas.




Depurar Falhas:

Se algum teste falhar, verifique as mensagens de erro no console do IntelliJ.
Para resolver problemas de compilação, execute File > Invalidate Caches / Restart e selecione Invalidate and Restart.



Funcionalidades

Hierarquia de Assinaturas: Um documento só pode ser assinado por um nível hierárquico se o nível imediatamente inferior já tiver assinado.
Chain of Responsibility: Se um nível não pode assinar (porque o inferior não assinou ou porque já assinou), o documento é encaminhado ao nível superior, até o General ou até que ninguém possa assinar.
Prevenção de Assinaturas Duplicadas: Um Aprovador não pode assinar o mesmo documento mais de uma vez.
Testes Abrangentes: A classe HierarquiaAssinaturaTest cobre todos os cenários principais, garantindo que o sistema funcione conforme esperado.

Exemplo de Uso
Documento documento = new Documento("Plano de Operações 2025");
Soldado soldado = new Soldado("João");
Sargento sargento = new Sargento("Pedro");
soldado.setSuperior(sargento);

soldado.assinarDocumento(documento); // João assina
sargento.assinarDocumento(documento); // Pedro assina
System.out.println(documento); // Exibe: Documento: Plano de Operações 2025, Assinaturas: [João, Pedro]

Notas

O sistema é genérico e pode ser adaptado para outras hierarquias, desde que mantida a estrutura de Aprovador com superior e inferior.
O padrão Chain of Responsibility permite flexibilidade para adicionar novos níveis hierárquicos sem alterar a lógica existente.
Para expandir o sistema, considere adicionar validações específicas para cada nível ou integrar com uma interface gráfica.

Contribuições
Contribuições são bem-vindas! Para sugerir melhorias:

Faça um fork do repositório.
Crie uma branch para sua feature (git checkout -b feature/nova-funcionalidade).
Envie um pull request com uma descrição clara das mudanças.

Licença
Este projeto é de código aberto e está licenciado sob a MIT License.
