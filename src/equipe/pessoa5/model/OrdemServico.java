package equipe.pessoa5.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrdemServico {

    private Long idServico;
    private String descricao;
    private LocalDate data;
    private BigDecimal valorTotal;
    private Long idVeiculo;

    // Construtor padrão
    public OrdemServico() {}

    // Getters e Setters
    public Long getIdServico() { return idServico; }
    public void setIdServico(Long idServico) { this.idServico = idServico; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
    public Long getIdVeiculo() { return idVeiculo; }
    public void setIdVeiculo(Long idVeiculo) { this.idVeiculo = idVeiculo; }
}