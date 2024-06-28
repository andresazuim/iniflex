import models.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class Pincipal {
    public static void main(String[] args){

        // 3.1
        List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
                new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
                new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
                new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        ));

        // 3.2
        funcionarios.removeIf(f -> f.getNome().equals("João"));

        // 3.3
        for (int i = 0; i < funcionarios.size(); i++){
            System.out.println(funcionarios.get(i));
        }

        // 3.4
        funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(new BigDecimal("1.1"))));

        // 3.5
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));


        // 3.6
        System.out.println("\nFuncionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, listaFuncionarios) -> {
            System.out.println("\nFunção: " + funcao);
            listaFuncionarios.forEach(System.out::println);
        });

        // 3.8
        List<Funcionario> aniversariantes = funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonth() == Month.OCTOBER || f.getDataNascimento().getMonth() == Month.DECEMBER )
                .collect(Collectors.toList());
        System.out.println("\nAniversariantes de Outubro e Dezembro:");
        aniversariantes.forEach(System.out::println);

        // 3.9
        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElseThrow(NoSuchElementException::new);
        System.out.println("\nFuncionário com maior idade: Nome: " + maisVelho.getNome() +
                ", Idade: " + (LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear()));


        // 3.10
        List<Funcionario> funcionariosOrdenados = new ArrayList<>(funcionarios);
        funcionariosOrdenados.sort(Comparator.comparing(Funcionario::getNome));
        System.out.println("\nFuncionários em ordem alfabética:");
        funcionariosOrdenados.forEach(System.out::println);

        // 3.11
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários: " + new Funcionario("", LocalDate.now(), totalSalarios, "").getSalarioFormatado());

        //3.12
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nQuantidade de salários mínimos por funcionário:");
        funcionarios.forEach(f -> {
            BigDecimal qtdSalariosMinimos = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_DOWN);
            System.out.println(f.getNome() + " ganha " + qtdSalariosMinimos + " salários mínimos.");
        });
    }



}
