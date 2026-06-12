package equipe.pessoa5.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrdemServico {

    private Integer idOs;          // id_os INT AUTO_INCREMENT
    private Integer idVeiculo;     // id_veiculo INT
    private LocalDate dataAbertura; // data_abertura DATE
    private LocalDate dataFechamento; // data_fechamento DATE
    private String status;         // status VARCHAR(20) ('aberta', 'em andamento', etc)
    private BigDecimal valorTotal; // valor_total DECIMAL(10,2)
    private String observacoes;    // observacoes TEXT

    // Construtor
    public OrdemServico() {}

    // Getters e Setters
    public Integer getIdOs() { return idOs; }
    public void setIdOs(Integer idOs) { this.idOs = idOs; }

    public Integer getIdVeiculo() { return idVeiculo; }
    public void setIdVeiculo(Integer idVeiculo) { this.idVeiculo = idVeiculo; }

    public LocalDate getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(LocalDate dataAbertura) { this.dataAbertura = dataAbertura; }

    public LocalDate getDataFechamento() { return dataFechamento; }
    public void setDataFechamento(LocalDate dataFechamento) { this.dataFechamento = dataFechamento; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}