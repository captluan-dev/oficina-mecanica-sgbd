package equipe.pessoa5.controller;

import equipe.pessoa5.model.OrdemServico;
import equipe.pessoa5.service.OrdemServicoService;

public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;

    public OrdemServicoController() {
        this.ordemServicoService = new OrdemServicoService();
    }

    // Simula o recebimento dos dados da Ordem de Serviço
    public void receberRequisicaoCriarOS(OrdemServico os) {
        this.ordemServicoService.abrirNovaOrdem(os);
    }
}