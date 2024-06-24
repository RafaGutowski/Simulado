package br.edu.up.daos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.edu.up.Pessoa;

public class GerenciadorDeArquivos {

    private String header = "";
    private String nomeDoArquivoPessoa = "C:\\Users\\autologon\\Documents\\GitHub\\Simulado\\SimuladoJava\\src\\br\\edu\\up\\pessoas.csv";
    private String Endereco = "C:\\Users\\autologon\\Documents\\GitHub\\Simulado\\SimuladoJava\\src\\br\\edu\\up\\enderecos.csv";
    private String PessoaComEndereco = "C:\\Users\\autologon\\Documents\\GitHub\\Simulado\\SimuladoJava\\src\\br\\edu\\up\\pessoaComEndereco.csv";



    public List<Pessoa> getPessoas() {

        List<Pessoa> listaPessoas = new ArrayList<>();

        try {
            File arquivoLeituraPessoa = new File(nomeDoArquivoPessoa);
            Scanner leitorPessoa = new Scanner(arquivoLeituraPessoa);

            if (leitorPessoa.hasNextLine()) {
                header = leitorPessoa.nextLine();
            }

            while (leitorPessoa.hasNextLine()) {
                String linha = leitorPessoa.nextLine();
                String[] dados = linha.split(";");

                String codigo = dados[0];
                String nome = dados[1];


                Pessoa pessoa = new Pessoa(codigo, nome, "", "");

            }
            leitorPessoa.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado! " + e.getMessage());
        }

        return listaPessoas;
    }
    public List<Pessoa> lerEndereco() {

        List<Pessoa> listaDeEnderecos = new ArrayList<>();

        try {
            File arquivoLeituraEndereco = new File(Endereco);
            Scanner leitorPessoa = new Scanner(arquivoLeituraEndereco);

            if (leitorPessoa.hasNextLine()) {
                header = leitorPessoa.nextLine();
            }

            while (leitorPessoa.hasNextLine()) {
                String linha = leitorPessoa.nextLine();
                String[] dados = linha.split(";");

                String rua = dados[3];
                String cidade = dados[4];


                listaDeEnderecos.add(new Pessoa("", "", rua, cidade));

            }
            leitorPessoa.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado! " + e.getMessage());
        }

        return listaDeEnderecos;
    }

    

    public boolean gravarPessoaComEndereco(List<Pessoa> pessoas) {
        try {
            FileWriter arquivoGravar = new FileWriter(nomeDoArquivoPessoa);
            PrintWriter gravador = new PrintWriter(arquivoGravar);

            gravador.println("Codigo;Nome;Rua;Cidade");

            for (Pessoa pessoa : pessoas) {
                gravador.println(pessoa.toCSV());
            }
            gravador.close();

            return true;
        } catch (Exception e) {
            System.out.println("Não foi possível gravar o arquivo! " + e.getMessage());
        }
        return false;
    }



}
