package equipe.pessoa3.service;

import equipe.pessoa3.model.Mecanico;
import equipe.pessoa3.repository.MecanicoRepository;

public class MecanicoService {

    private MecanicoRepository repository;

    public MecanicoService(MecanicoRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(Mecanico mecanico) throws Exception {

        if (mecanico.getNome().isBlank()) {
            throw new Exception("Nome obrigatório.");
        }

        repository.inserir(mecanico);
    }
}