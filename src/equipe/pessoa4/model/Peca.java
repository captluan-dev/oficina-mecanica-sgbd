package equipe.pessoa4.model;

public class Peca {

    // Representa a peça cadastrada, com dados básicos e saldo atual em estoque.
    private Long id;
    private String codigo;
    private String nome;
    private String descricao;
    private double precoUnitario;
    private int quantidadeEstoque;

    public Peca() {
    }

    // Construtor completo usado quando a peça já vem com id do banco.
    public Peca(Long id, String codigo, String nome, String descricao, double precoUnitario, int quantidadeEstoque) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Peca(String codigo, String nome, String descricao, double precoUnitario, int quantidadeEstoque) {
        this(null, codigo, nome, descricao, precoUnitario, quantidadeEstoque);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    @Override
    public String toString() {
        // Facilita a visualização da peça na listagem do console.
        return "Peca{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", precoUnitario=" + precoUnitario +
                ", quantidadeEstoque=" + quantidadeEstoque +
                '}';
    }
}