/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lfa_ex01;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LFA_Ex01 {
    public static void main(String[] args) {
        AutomatoSimples automato = new AutomatoSimples(); 
        Scanner scanner = new Scanner(System.in);         

        System.out.println("Deseja cadastrar um automato? (s/n)");
        String resposta = scanner.nextLine().trim().toLowerCase();

        if (resposta.equals("s")) {
            List<Transicao> transicoes = new ArrayList<>(); 
            while (true) {
                System.out.println("Digite as transicoes (estadoOrigem;simbolo;estadoDestino) ou 'fim' para encerrar:");
                String linha = scanner.nextLine();
                if (linha.equalsIgnoreCase("fim")) break;

                String[] partes = linha.split(";");
                if (partes.length < 3) {
                    System.out.println("Formato invalido.");
                    continue;
                }
                transicoes.add(new Transicao(partes[0], partes[1], partes[2])); 
            }
            automato.setTransicoes(transicoes);

            System.out.println("Digite os estados finais separados por vírgula:");
            String[] estadosFinaisInput = scanner.nextLine().split(",");
            List<String> estadosFinais = new ArrayList<>();
            for (String estadoFinal : estadosFinaisInput) {
                estadosFinais.add(estadoFinal.trim());
            }
            automato.setEstadosFinais(estadosFinais); 

            System.out.println("Digite uma sentenca para testar:");
            String sentencaUsuario = scanner.nextLine();
            testarSentenca(automato, sentencaUsuario); 

        } else {
            System.out.println("Digite uma sentenca para testar:");
            String sentencaUsuario = scanner.nextLine();
            testarAutomato(automato, "src/main/java/com/mycompany/lfa_ex01/Automato01.txt", sentencaUsuario);
        }
        scanner.close(); 
    }

    /* Testa o autômato carregando de um arquivo e verifica sentenças.*/
    private static void testarAutomato(AutomatoSimples automato, String caminhoArquivo, String... sentencas) {
        try {
            automato.carregarAutomato(new File(caminhoArquivo));
            for (String sentenca : sentencas) {
                boolean aceita = automato.processarSentenca(sentenca);
                System.out.println("Sentenca \"" + sentenca + "\" " + (aceita ? "aceita" : "rejeitada") + " pelo automato.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo " + caminhoArquivo + ": " + e.getMessage());
        }
    }

    /*Testa uma sentença com o automato configurado manualmente.*/
    private static void testarSentenca(AutomatoSimples automato, String sentenca) {
        boolean aceita = automato.processarSentenca(sentenca);
        System.out.println("Sentenca \"" + sentenca + "\" " + (aceita ? "aceita" : "rejeitada") + " pelo automato.");
    }
}
