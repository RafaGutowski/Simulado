package br.edu.up.daos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.edu.up.Pessoa;

public class GerenciadorDeArquivos {

    private String header = "";
    private String nomeDoArquivoPessoa = "C:\\Users\\autologon\\Documents\\GitHub\\Simulado\\SimuladoJava\\src\\br\\edu\\up\\pessoas.csv";

    public List<Pessoa> getPessoasComEndereco() {

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
                String rua = dados[2];
                String cidade = dados[3];

                Pessoa pessoa = new Pessoa(codigo, nome, rua, cidade);

            }
            leitorPessoa.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado! " + e.getMessage());
        }

        return listaPessoas;
    }

    public boolean gravarCarros(List<Pessoa> pessoas) {
        try {
            FileWriter arquivoGravar = new FileWriter(nomeDoArquivoPessoa);
            PrintWriter gravador = new PrintWriter(arquivoGravar);

            gravador.println(header);
            
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
