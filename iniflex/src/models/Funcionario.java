package models;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Locale;

public class Funcionario extends Pessoa{

    private BigDecimal salario;
    private String funcao;
    public String getSalarioFormatado() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
        return df.format(salario);
    }

    @Override
    public String toString() {
        return String.format("Nome: %s | Data de Nascimento: %s | Salário: %s | Função: %s",
                getNome(),
                getDataNascimento().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                getSalarioFormatado(),
                //salario.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString().replace(".", ","),
                funcao);
    }

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        this.setNome(nome);
        this.setDataNascimento(dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }
    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
