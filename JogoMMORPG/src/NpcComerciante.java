import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

public class NpcComerciante {
    private final Semaphore semaforo;
    private final PriorityBlockingQueue<Jogador> fila;

    public NpcComerciante(int maxAtendimentosSimultaneos) {
        this.semaforo = new Semaphore(maxAtendimentosSimultaneos);
        this.fila = new PriorityBlockingQueue<>();
    }

    public void entrarNaFila(Jogador j) {
        fila.add(j);
        System.out.println(j.getName() + " entrou na fila.");
    }

    public void atenderJogador(Jogador j) {
        try {
            while (true) {
                Jogador primeiroDaFila = fila.peek();
                if (primeiroDaFila == j) {
                    semaforo.acquire();
                    fila.poll();
                    System.out.println(j.getName() + " está sendo atendido.");
                    break;
                }
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void sairDoAtendimento(Jogador j) {
        System.out.println(j.getName() + " finalizou o atendimento.");
        semaforo.release();
    }
}
