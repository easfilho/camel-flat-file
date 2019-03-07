# camel-flat-file

### Sobre o projeto
O projeto foi implementado utilizando Spring Boot versãro 2.1.3 jutamente como java 8, de bibliotecas externas foram utilzadas o Apache Camel, o Lombok e o Power Mock, como gerenciador de dependências foi utilizado o Gradle.

### Clean Code
Nesse quesito a aplicação usa bastante do princício S do Solid (Single Responsability) o que garante alta coesão e baixo acoplamento, que pode ser visto através das classes de Service, é usado também o princípio O do Solid (Open-Closed), utilizado através de um strategy, que pode ser identificado na classe FlatFileDataProcessor, ou seja, para processar qualquer outro tipo novo de dado basta colocar um parâmetro a mais no construtor, para fazer a injeção de dependência, que implemente FlatFileConverter e adicionar no mapa sem precisar fazer nenhuma mudança na método que faz a execução. Quanto ao princípios L do Solid (Liskov substitution) é visto na classe SaleSummaryService, embora o método espere uma lista do tipo FlatFileData (que pode ser Salesman, Customer ou Sale) não é feita nenhuma validação para saber o tipo de dado e depois fazer algum processamento. Quanto ao princípio I do Solid (Interface Segregation) é fácil ver nas classes que implementam FlatFileConverter, elas implementam somente os métodos dessa interface. Para o princípio D do Solid (Dependency inversion) também é visto na classe FlatFileDataProcessor, em que é feito a injeção de dependência através da inteface e não através da implementação dela, dessa forma caso seja necessário mudar o tipo de processamento do conversor basta mudar o parâmetro dentro da anotação @Qualifier.
Ainda sobre Clean Code a implementação desse sistema foi toda utilizando TDD, dessa forma é garantido um código mais enxuto, já que toda a implementação visa somente o sucesso do teste, ao invés de alguma preocupação futura.

### Simplicidade
Embora em algumas classes foram usadas o uso de uma interface para a implementação do padrão de projeto strategy, outras não se fez necessário, que é o caso da classe SaleSummaryService, ela simplesmente é instanciada e é chamada o único método público dela para calcular o resumo do faturamento. Foi usado também o Lombok que facilita muito em deixar as classes de model ou DTO mais limpas já que método como getter, setter, equals and hashcode podem ser suprimidos através de uma anotação @Data.
Outro ponto que visa a simplicidade também é o uso do Apache Camel para fazer a leitura e a escrita dos arquivos, basta adicionar os endpoints para leitura e escrita que ele faz todo o processamento
Foram utilizadas constantes para a identificação das posições de cada tipo de dados, caracteres separadores entre outros, essas constantes ficam no arquivos application.yml, dessa forma se houver alguma mudança na maneira de ler o arquivo bastar modificar este.

### Lógica
A lógica do projeto está bem simples, e tem como classe princicpal a classe ConvertFlatFileRoute, nela é fácil de ver o processamento do arquivo passo a passo através dos métodos .process(). O código também está de fácil leitura com método pequenos e auto-descritivos. O que pode ser visto nas classes de Service.
É fácil também ver uma lógica de fácil entendimento nos métodos da classe SaleSummaryService, nela o processamento dos dados é feito através da programação funcional do Java 8 que facilita bastante a legibilidade

### Separação das responsabilidades
A aplicação foi desenvolvida muito em cima dos pilares do Solid, sendo assim o princípio de responsabilidade única ajuda muito nesse quesito.

### Flexibilidade/Extensibilidade
Esse quesito é fácil de ver através da classe FlatFileDataProcessor, pois basta adicionar um novo tipo de dado que implemente FlatFileConverter no construtor, adicioná-lo ao mapa está pronto náo é necessário fazer nenhuma nova modificação, ou seja, ela está aberta para inclusões e fechada para alterações.

### Escalabilidade/Performance
Nessa questão foi tomado o cuidade de deletar o arquivo após seu processamento (vide a classe ConvertFlatFileRoute), dessa forma não se corre o risco de processar uma arquvo mais de uma vez. Também foi usado o processamento paralelo do java 8 através do .parallelStream(), dessa forma são disparadas múltiplas thread para transfomar as linhas do arquivo em dados processáveis.

### Testes automatizados
A aplicação ficou com uma cobertura de teste no total de 98%, estes por sua vez, foram divididos em testes de unidade e de integração. Foi tomado o cuidade de somente os testes de integração subirem o contexto do spring, dessa forma se tem muito mais agilidade na execução dos testes.
É disponibilizado o arquivo input1.dat para fazer a execução dos testes

### Como rodar o sistema
Para processar os arquivos basta baixar os fontes e rodar o comando gradlew bootRun, a aplicação diparará o processamento uma vez por dia.
Essa definição pode ser vista através da classe FlatFileScheduler

### Logs
São registrados logs a cada início e o fim de cada processamento, logando também o início e o fim do processamento de cada arquivo

### Dados da aplicação
Foi alterado para usar como caracter separador de dados o "|", em vista do caracter "ç" ser um caracter sensível, pois existem nomes do time Bento Gonçalves.
