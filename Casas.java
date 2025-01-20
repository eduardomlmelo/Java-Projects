import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Casas {

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
            if (player.player_type == 1 || player.player_type == 2){ // Verifica se não é um jogador azarado
                player.casa_atual += 3;
            }
            System.out.printf("Que Azar! Jogador %s é ARAZARADO e não vai andar 3 casas :( \n", player.cor);
        } else if (player.casa_atual == 15) {
            String mensagem = String.format("Casa de Sorte (Jogador %s ande 3 casas para frente) \n", player.cor);
            System.out.printf(mensagem);
            if (player.player_type == 1 || player.player_type == 2){
                player.casa_atual += 3; // Move o jogador 3 casas para frente
            }
            System.out.printf("Que Azar! Jogador %s é ARAZARADO e não vai andar 3 casas :( \n", player.cor);
        } else if (player.casa_atual == 30) {
            String mensagem = String.format("Casa de Sorte (Jogador %s ande 3 casas para frente) \n", player.cor);
            System.out.printf(mensagem);
            if (player.player_type == 1 || player.player_type == 2){
                player.casa_atual += 3;
            }
            System.out.printf("Que Azar! Jogador %s é ARAZARADO e não vai andar 3 casas :( \n", player.cor);
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

    public void backBeginning(ArrayList<Jogador> jogadores) {
        String opcao;
        Scanner celadon = new Scanner(System.in);

        while (true) {
            System.out.printf("Digite um jogador para voltar ao início: %n");
            opcao = celadon.next().toLowerCase();

            boolean jogadorEncontrado = false;

            for (Jogador jogador : jogadores) {
                if (opcao.equals(jogador.cor.toLowerCase())) {
                    jogadorEncontrado = true;
                    String mensagem = String.format("Jogador %s voltou ao início!", jogador.cor);
                    System.out.println(mensagem);
                    jogador.casa_atual = 0;
                    break;
                }
            }

            if (jogadorEncontrado) {
                break;
            } else {
                System.out.printf("Jogador inválido. Digite novamente: %n");
            }
        }

        // celadon.close(); // Se precisar fechar o Scanner, faça isso fora do loop.
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
