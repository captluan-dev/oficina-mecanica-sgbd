# CRUD Peças e Estoque

Aviso: esta área é exclusiva para os pacotes Java de peças e estoque.

Responsabilidades:
- Cadastro de peças
- Controle de estoque
- Entrada de peças
- Saída de peças
- Bloqueio de estoque negativo

Implementação entregue nesta pasta:
- `model/Peca.java`
- `model/MovimentoEstoque.java`
- `repository/PecaRepository.java`
- `repository/MovimentoEstoqueRepository.java`
- `service/PecaService.java`
- `util/DatabaseConnection.java`
- `application/CrudPecasEstoqueApp.java`

Regras cobertas:
- CRUD completo de peças
- Entrada e saída com atualização automática do estoque
- Validação para impedir estoque negativo
- Registro de movimentações de estoque
