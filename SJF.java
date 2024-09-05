import java.util.Arrays;

class ListaDeProcessos {
    Processo[] processos;
    int tamanho;
    int capacidade;

    public ListaDeProcessos(int capacidadeInicial) {
        this.capacidade = capacidadeInicial;
        this.processos = new Processo[capacidadeInicial];
        this.tamanho = 0;
    }

    public void adicionar(Processo p) {
        if (tamanho == capacidade) {
            capacidade *= 2;
            Processo[] novaLista = new Processo[capacidade];
            for (int i = 0; i < tamanho; i++) {
                novaLista[i] = processos[i];
            }
            processos = novaLista;
        }
        processos[tamanho] = p;
        tamanho++;
    }

    public void removerPorID(int id) {
        for (int i = 0; i < tamanho; i++) {
            if (processos[i].id == id) {
                for (int j = i; j < tamanho - 1; j++) {
                    processos[j] = processos[j + 1];
                }
                tamanho--;
                break;
            }
        }
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public int tamanho() {
        return tamanho;
    }
}

class Processo {
    int id;
    int tempoChegada;
    int tempoExecucao;
    int tempoRestante;

    public Processo(int id, int tempoChegada, int tempoExecucao) {
        this.id = id;
        this.tempoChegada = tempoChegada;
        this.tempoExecucao = tempoExecucao;
        this.tempoRestante = tempoExecucao;
    }
}

public class SJF {
    public static void main(String[] args) {
        ListaDeProcessos processos = new ListaDeProcessos(10);
        processos.adicionar(new Processo(1, 0, 8));
        processos.adicionar(new Processo(2, 1, 4));
        processos.adicionar(new Processo(3, 2, 5));
        processos.adicionar(new Processo(4, 7, 5));
        processos.adicionar(new Processo(5, 6, 6));
        processos.adicionar(new Processo(6, 5, 3));
        processos.adicionar(new Processo(7, 11, 2));
        processos.adicionar(new Processo(8, 22, 1));
        processos.adicionar(new Processo(9, 3, 3));
        processos.adicionar(new Processo(10, 4, 1));
        simularEscalonamentoSJF(processos);
    }

    public static void simularEscalonamentoSJF(ListaDeProcessos processos) {
        int tempoAtual = 0;
        int totalProcessos = processos.tamanho();
        int processoAtual = -1;

        while (totalProcessos > 0) {
            int menorTempoExecucao = Integer.MAX_VALUE;
            int indiceMenorTempoExecucao = -1;

            for (int i = 0; i < processos.tamanho(); i++) {
                Processo p = processos.processos[i];
                if (p.tempoChegada <= tempoAtual && p.tempoExecucao < menorTempoExecucao && p.tempoRestante > 0) {
                    menorTempoExecucao = p.tempoExecucao;
                    indiceMenorTempoExecucao = i;
                }
            }

            if (indiceMenorTempoExecucao != -1) {
                Processo p = processos.processos[indiceMenorTempoExecucao];
                if (processoAtual != indiceMenorTempoExecucao) {
                    processoAtual = indiceMenorTempoExecucao;
                    System.out.println("Tempo " + tempoAtual + ": Executando processo P" + p.id);
                }

                p.tempoRestante--;

                if (p.tempoRestante == 0) {
                    totalProcessos--;
                    processos.removerPorID(p.id);
                    processoAtual = -1;
                }
            } else {
                System.out.println("Tempo " + tempoAtual + ": CPU ociosa");
            }

            tempoAtual++;
        }
    }
}