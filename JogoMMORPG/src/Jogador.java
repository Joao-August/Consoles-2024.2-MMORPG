import java.util.Random;

public class Jogador extends Thread implements Comparable<Jogador> {
    private final long tempoEntrada;
    private final NpcComerciante npc;
    private final Random random;

    public Jogador(String nome, NpcComerciante npc) {
        super(nome);
        this.npc = npc;
        this.tempoEntrada = System.currentTimeMillis();
        this.random = new Random();
    }

    public long getTempoEntrada() {
        return tempoEntrada;
    }

    @Override
    public int compareTo(Jogador outro) {
        return Long.compare(this.tempoEntrada, outro.getTempoEntrada());
    }

    @Override
    public void run() {
        npc.entrarNaFila(this);
        npc.atenderJogador(this);

        int tempoNegociacao = 1000 + random.nextInt(2000);
        try {
            System.out.println(getName() + " negociando por " + tempoNegociacao + " ms.");
            Thread.sleep(tempoNegociacao);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        npc.sairDoAtendimento(this);
    }
}
