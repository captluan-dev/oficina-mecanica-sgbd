package equipe.pessoa5.service;

import equipe.pessoa5.model.OrdemServico;
import java.math.BigDecimal;
import java.time.LocalDate;

public class OrdemServicoService {

    public OrdemServico abrirNovaOrdem(OrdemServico os) {
        os.setData(LocalDate.now());
        os.setValorTotal(BigDecimal.ZERO); // Começa zerada no orçamento

        System.out.println("Módulo de Integração: Abrindo OS para o veículo ID: " + os.getIdVeiculo());
        return os;
    }
}