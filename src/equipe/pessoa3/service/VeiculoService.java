package equipe.pessoa3.service;

import equipe.pessoa3.model.Veiculo;
import equipe.pessoa3.repository.VeiculoRepository;

public class VeiculoService {

    private VeiculoRepository repository;

    public VeiculoService(VeiculoRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(Veiculo veiculo) throws Exception {

        if (veiculo.getIdCliente() <= 0) {
            throw new Exception("Veículo deve possuir cliente.");
        }

        if (repository.placaExiste(veiculo.getPlaca())) {
            throw new Exception("Placa já cadastrada.");
        }

        repository.inserir(veiculo);
    }
}