package equipe.pessoa4.model;

import java.time.LocalDateTime;

public class MovimentoEstoque {

    public enum TipoMovimento {
        ENTRADA,
        SAIDA
    }

    private Long id;
    private Long pecaId;
    private TipoMovimento tipo;
    private int quantidade;
    private LocalDateTime dataHora;
    private String observacao;

    public MovimentoEstoque() {
    }

    public MovimentoEstoque(Long id, Long pecaId, TipoMovimento tipo, int quantidade, LocalDateTime dataHora, String observacao) {
        this.id = id;
        this.pecaId = pecaId;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.dataHora = dataHora;
        this.observacao = observacao;
    }

    public MovimentoEstoque(Long pecaId, TipoMovimento tipo, int quantidade, String observacao) {
        this(null, pecaId, tipo, quantidade, LocalDateTime.now(), observacao);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPecaId() {
        return pecaId;
    }

    public void setPecaId(Long pecaId) {
        this.pecaId = pecaId;
    }

    public TipoMovimento getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimento tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}