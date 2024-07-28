import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CasasEspeciais {
    protected String[] cores = new String[6];


    public String[] cores(){
        // Inicialização dos elementos do vetor
        this.cores[0] = "AZUL";
        this.cores[1] = "VERMELHO";
        this.cores[2] = "BRANCO";
        this.cores[3] = "LARANJA";
        this.cores[4] = "ROXO";
        this.cores[5] = "VERDE";

        return this.cores;
    }

    public void efeitosEspeciais(Jogador player, ArrayList<Jogador> jogadores){
        // Casas 10, 25, 38
        if (player.casa_atual == 10){
            String mensagem = String.format("Casa de Travamento (Que Azar, jogador %s vai ficar sem andar) \n", player.cor);
            System.out.printf(mensagem);
            player.player_available = 0;
        } else if (player.casa_atual == 25) {
            String mensagem = String.format("Casa de Travamento (Que Azar, jogador %s vai ficar sem andar) \n", player.cor);
            System.out.printf(mensagem);
            player.player_available = 0;
        } else if (player.casa_atual == 38) {
            String mensagem = String.format("Casa de Travamento (Que Azar, jogador %s vai ficar sem andar) \n", player.cor);
            System.out.printf(mensagem);
            player.player_available = 0;
        }

        // Casa 13
        if (player.casa_atual == 13){
            String mensagem = String.format("Casa de Tipo (%s Troque o seu tipo de jogador) \n", player.cor);
            System.out.printf(mensagem);
            // Loop para fazer o jogador trocar para um tipo diferente
            while (true){
                Random randomizer = new Random();
                int new_player_type = randomizer.nextInt(3);

                if(new_player_type != player.player_type){
                    player.player_type = new_player_type;
                    break;
                }

            }
            // Imprime a mensagem para qual tipo o jogador trocou
            if(player.player_type == 0){
                String mensagem2 = String.format("%s trocou para Azarado", player.cor);
                System.out.println(mensagem2);
            } else if (player.player_type == 1) {
                String mensagem2 = String.format("%s trocou para Sortudo", player.cor);
                System.out.println(mensagem2);
            } else if (player.player_type == 2) {
                String mensagem2 = String.format("%s trocou para Normal", player.cor);
                System.out.println(mensagem2);
            }
        }

        // Casas 5, 15, 30
        if (player.casa_atual == 5){
            String mensagem = String.format("Casa de Sorte (Jogador %s ande 3 casas para frente) \n", player.cor);
            System.out.printf(mensagem);
            if (player.player_type != 0){ // Verifica se não é um jogador azarado
                player.casa_atual += 3;
            }
        } else if (player.casa_atual == 15) {
            String mensagem = String.format("Casa de Sorte (Jogador %s ande 3 casas para frente) \n", player.cor);
            System.out.printf(mensagem);
            if (player.player_type != 0){
                player.casa_atual += 3; // Move o jogador 3 casas para frente
            }
        } else if (player.casa_atual == 30) {
            String mensagem = String.format("Casa de Sorte (Jogador %s ande 3 casas para frente) \n", player.cor);
            System.out.printf(mensagem);
            if (player.player_type != 0){
                player.casa_atual += 3;
            }
        }

        // Casas 17, 27
        if (player.casa_atual == 17){
            String mensagem = String.format("Casa de Volta (Jogador %s escolha outro jogador para voltar ao início \n", player.cor);
            System.out.printf(mensagem);
            this.backBeginning(jogadores);
        } else if (player.casa_atual == 27) {
            String mensagem = String.format("Casa de Volta (Jogador %s escolha outro jogador para voltar ao início \n", player.cor);
            System.out.printf(mensagem);
            this.backBeginning(jogadores);
        }

        // Casas 20, 35 Troca de lugar com o jogador mais atrás no jogo
        if (player.casa_atual == 20){
            System.out.printf("Casa de Troca (Jogador troca com jogador mais atrás!!!)");
            this.switchPlayer(player, jogadores);
        } else if (player.casa_atual == 35) {
            System.out.printf("Casa de Troca (Jogador troca com jogador mais atrás!!!)");
            this.switchPlayer(player, jogadores);
        }



    }

    public void backBeginning(ArrayList<Jogador> jogadores){
        String opcao;
        Scanner celadon = new Scanner(System.in);

        while (true){
            System.out.printf("Digite um jogador para voltar ao início: ");
            opcao = celadon.next();

            switch (opcao){

                case "AZUL":
                    break;
                case "VERMELHO":
                    break;
                case "BRANCO":
                    break;
                case "LARANJA":
                    break;
                case "ROXO":
                    break;
                case "VERDE":
                    break;
                default:
                    System.out.printf("Jogador inválido. Digite novamente");
                    continue;

            }

            //celadon.close();

            for (int i = 0; i < jogadores.size(); i++){
                if (opcao.equals(jogadores.get(i).cor)){
                    String mensagem = String.format("Jogador %s voltou ao início", jogadores.get(i).cor);
                    System.out.println(mensagem);
                    jogadores.get(i).casa_atual = 0;
                    break;
                }
            }

            System.out.println("Jogador inválido!!! Digite novamente");
        }
    }

    public void switchPlayer(Jogador player_atual, ArrayList<Jogador> jogadores){
        // Loop para avaliar qual o jogador com menor atributo 'casa_atual'
        int jogador_atras = 0;
        for (int i = 0; i < jogadores.size() - 1; i++){
            if (jogadores.get(i + 1).casa_atual <= jogadores.get(i).casa_atual){
                jogador_atras = i + 1; // Atribui o índice do jogador mais atrás
            }
        }

        String mensagem = String.format("%s trocou de lugar com %s", player_atual.cor, jogadores.get(jogador_atras).cor);
        System.out.println(mensagem);

        int temp = player_atual.casa_atual;
        player_atual.casa_atual = jogadores.get(jogador_atras).casa_atual;
        jogadores.get(jogador_atras).casa_atual = temp;
    }


}