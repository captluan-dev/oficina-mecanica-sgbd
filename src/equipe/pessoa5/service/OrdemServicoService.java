package equipe.pessoa5.service;

import equipe.pessoa5.model.OrdemServico;
import java.math.BigDecimal;
import java.time.LocalDate;

public class OrdemServicoService {

    public OrdemServico abrirNovaOrdem(OrdemServico os) {
        os.setDataAbertura(LocalDate.now());
        os.setStatus("aberta"); // Status padrão que o Leonardo colocou no banco!
        os.setValorTotal(BigDecimal.ZERO);

        System.out.println("Módulo de Integração: Abrindo OS para o veículo ID: " + os.getIdVeiculo());
        return os;
    }
}