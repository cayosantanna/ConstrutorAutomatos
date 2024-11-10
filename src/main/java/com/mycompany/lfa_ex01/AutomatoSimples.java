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

public class AutomatoSimples {
    private List<Transicao> transicoes = new ArrayList<>(); 
    private List<String> estadosFinais = new ArrayList<>();  

    /* Carrega as transições e estados finais de um arquivo */
    public void carregarAutomato(File arquivo) throws IOException {
        Scanner reader = new Scanner(arquivo); 
        while (reader.hasNextLine()) {         // Processa cada linha do arquivo
            String linha = reader.nextLine().trim(); // Lê e remove espaços desnecessários
            if (linha.isEmpty()) continue; // Ignora linhas vazias

            if (linha.startsWith("finais;")) { 
                String[] partes = linha.split(";"); 
                for (int i = 1; i < partes.length; i++) {
                    estadosFinais.add(partes[i].trim()); 
                }
            } else { 
                String[] partes = linha.split(";");
                String estadoOrigem = partes[0];
                String simbolo = partes[1];
                String estadoDestino = partes[2];
                transicoes.add(new Transicao(estadoOrigem, simbolo, estadoDestino)); 
            }
        }
        reader.close(); // Fecha o leitor
    }

    /* Processa uma sentença e verifica se é aceita pelo autômato*/
    public boolean processarSentenca(String sentenca) {
        String estadoAtual = "q0"; 

        for (int i = 0; i < sentenca.length(); i++) {
            String simbolo = String.valueOf(sentenca.charAt(i)); // Extrai o símbolo atual
            String proximoEstado = null;

            for (Transicao transicao : transicoes) {
                if (transicao.estadoOrigem.equals(estadoAtual) && transicao.simbolo.equals(simbolo)) {
                    proximoEstado = transicao.estadoDestino; // Define o próximo estado
                    break;
                }
            }

            if (proximoEstado == null) {
                return false;
            }

            estadoAtual = proximoEstado;
        }
        return estadosFinais.contains(estadoAtual);
    }
    
public void setTransicoes(List<Transicao> transicoes) {
this.transicoes = transicoes;
}

public void setEstadosFinais(List<String> estadosFinais) {
    this.estadosFinais = estadosFinais;
}

}
