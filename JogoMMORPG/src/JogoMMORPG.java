public class JogoMMORPG {
    public static void main(String[] args) {
        int maxAtendimentosSimultaneos = 3;
        NpcComerciante npc = new NpcComerciante(maxAtendimentosSimultaneos);

        int totalJogadores = 10;
        Thread[] jogadores = new Thread[totalJogadores];

        for (int i = 0; i < totalJogadores; i++) {
            jogadores[i] = new Jogador("Jogador-" + (i + 1), npc);
            jogadores[i].start();
            try {
                Thread.sleep(100 + (int) (Math.random() * 400));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        for (Thread jogador : jogadores) {
            try {
                jogador.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Simulaçao finalizada.");
    }
}
