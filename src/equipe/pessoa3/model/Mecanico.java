package equipe.pessoa3.model;

public class Mecanico {

    private int id;
    private String nome;
    private String especialidade; // corrigido para compatibilidade ou mantido especialidade
    private String telefone;

    public Mecanico() {}

    public Mecanico(int id, String nome, String especialidade, String telefone) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.telefone = telefone;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    @Override
    public String toString() {
        return "Mecanico{" + "id=" + id + ", nome='" + nome + '\'' + ", especialidade='" + especialidade + '\'' + ", telefone='" + telefone + '\'' + '}';
    }
}