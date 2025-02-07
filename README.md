# Projeto Demonstração
Projeto simplista, iniciado do zero, apenas para demonstrar alguns conceitos:

- Camada Controller com RequestMapping;
- Camada Facade (ServicosBancariosFacade.java);
- Abstração de classes (Cliente, ClientePessoaFisica, ClientePessoaJuridica);
- Stream + Atomic (rota: /cliente/contar  Classe: ClienteService->ContarClientes());

Outros:
- Swagger;
- Spring Security;
- Camadas: Controller, Facade, Service, Repository, Model;
- Controle de Exceptions customizadas;

Swagger rodando no appengine do GCP: https://jeriam.uc.r.appspot.com/swagger-ui/index.html

Etapas Swagger:
- authentication-controler -> registrar (Para criar um Usuário);
- authentication-controler -> login (Para obter o token);
- autenticar o swagger com o token para utilizar as funcionalidades;

Próxima etapa a implementar: IAC com Terraform
