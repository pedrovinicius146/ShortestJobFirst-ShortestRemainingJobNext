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

    public void remover(int indice) {
        for (int i = indice; i < tamanho - 1; i++) {
            processos[i] = processos[i + 1];
        }
        tamanho--;
    }

    public Processo obter(int indice) {
        return processos[indice];
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

public class SRTF {
    public static void main(String[] args) {
        ListaDeProcessos processos = new ListaDeProcessos(10);
        processos.adicionar(new Processo(1, 0, 8));
        processos.adicionar(new Processo(2, 1, 4));
        processos.adicionar(new Processo(3, 2, 9));
        processos.adicionar(new Processo(4, 7, 7));
        processos.adicionar(new Processo(5, 6, 6));
        processos.adicionar(new Processo(6, 5, 3));
        processos.adicionar(new Processo(7, 11, 2));
        processos.adicionar(new Processo(8, 22, 11));
        processos.adicionar(new Processo(9, 3, 13));
        processos.adicionar(new Processo(10, 4, 1));
        simularEscalonamento(processos);
    }

    public static void simularEscalonamento(ListaDeProcessos processos) {
        int tempoAtual = 0;
        int totalProcessos = processos.tamanho();
        int processoAtual = -1;

        while (totalProcessos > 0) {
            int menorTempoRestante = Integer.MAX_VALUE;
            int indiceMenorTempoRestante = -1;

            for (int i = 0; i < processos.tamanho(); i++) {
                Processo p = processos.obter(i);
                if (p.tempoChegada <= tempoAtual && p.tempoRestante < menorTempoRestante) {
                    menorTempoRestante = p.tempoRestante;
                    indiceMenorTempoRestante = i;
                }
            }

            if (indiceMenorTempoRestante != -1) {
                Processo p = processos.obter(indiceMenorTempoRestante);
                if (processoAtual != indiceMenorTempoRestante) {
                    processoAtual = indiceMenorTempoRestante;
                    System.out.println("Tempo " + tempoAtual + ": Executando processo P" + p.id);
                }

                p.tempoRestante--;

                if (p.tempoRestante == 0) {
                    totalProcessos--;
                    processos.remover(processoAtual);
                    processoAtual = -1;
                }
            } else {
                System.out.println("Tempo " + tempoAtual + ": CPU ociosa");
            }

            tempoAtual++;
        }

    }
void obter(int id){};
}
