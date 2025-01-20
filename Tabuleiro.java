import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Tabuleiro extends Casas{

    private int[][] board; // Tabuleiro do jogo vai ser um 2x20, onde cada casa vai ter seu respectivo número. Ex: elemento[0][0] casa 1
    private ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
    private String[] cores = new String[6];
    private int num_players;


    public Tabuleiro(int numero_jogadores) throws InterruptedException{
        this.board = new int[2][20];
        this.num_players = numero_jogadores;

        this.execute();
    }


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


    public void generatePlayers(int numero_players){
        String[] colours = this.cores(); // Gera o vetor de cores para os jogadores
        for(int i = 0; i < numero_players; i++){
            Jogador player = new Jogador(colours[i], i + 1);
            this.jogadores.add(player);
        }
    }


    public void generateBoard(){
        int contador = 0;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 20; j++){
                this.board[i][j] = contador++; // Atribui aos elementos da matriz, um número correspondente da casa
            }
        }
    }


    public void printBoard(){
        // Primeiro e Segundo Loop vão iterar todas as posições do tabuleiro
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 20; j++){
                int contador = 0;
                // Terceiro Loop itera por todos os jogadores, para ver se há algum na casa iterada
                for(int k = 0; k < this.jogadores.size(); k++){
                    // Condicional verifica se a casa atual do jogador é igual a casa iterada
                    if (this.jogadores.get(k).casa_atual == this.board[i][j]){
                        contador++;
                    }
                }
                String mensagem = String.format("| " + "%d" + " |",contador);
                System.out.printf(mensagem);
            }
            System.out.println(" ");
        }
    }


    public void play(int[] resultado, int i) throws InterruptedException{
        resultado = this.jogadores.get(i).playDices();
        this.jogadores.get(i).move(resultado[0]);
        this.jogadores.get(i).jogadas++;
        this.efeitosEspeciais(jogadores.get(i), this.jogadores);

        // Condicional que avalia se o jogador ganhou
        if (this.jogadores.get(i).casa_atual >= 39){
            String win_message = String.format("%s ganhou!!!", this.jogadores.get(i).cor);
            System.out.println(win_message);

            for(int j = 0; j < this.num_players; j++){
                jogadores.get(j).showData();
            }

            System.exit(0);
        }

        if(jogadores.get(i).player_available == 1){ // Verifica se o jogador pode jogar outra vez
            // Condicional que verifica se houve rolagem dupla
            if (resultado[1] == 1){
                this.jogadores.get(i).move(resultado[0]);
                this.jogadores.get(i).jogadas++;

                // Condicional que avalia se o jogador ganhou
                if (this.jogadores.get(i).casa_atual >= 39){
                    String win_message = String.format("%s ganhou!!!", this.jogadores.get(i).cor);
                    System.out.println(win_message);

                    for(int j = 0; j < this.num_players; j++){
                        jogadores.get(j).showData();
                    }

                    System.exit(0);
                }
            }

        }

    }


    public void execute() throws InterruptedException{
        this.generateBoard();
        this.generatePlayers(this.num_players);

        // Determino que os dois primeiros jogadores sempre serão de tipos diferentes
        Random randomizer = new Random();
        this.jogadores.get(0).player_type = randomizer.nextInt(2);
        this.jogadores.get(1).player_type = this.jogadores.get(0).player_type; // Inicializa o player_type do 2° jogador como igual ao 1° para sempre cair no while

        while(this.jogadores.get(1).player_type == this.jogadores.get(0).player_type){
            this.jogadores.get(1).player_type = randomizer.nextInt(2);
        }

        // Gero aleatoriamente os tipos para os jogadores restantes
        for (int i = 1; i < this.num_players - 1; i++){
            this.jogadores.get(i + 1).randomize_player_type();
        }


        // Contador de rodadas
        int contador = 1;


        while (true){
            // Loop para representar uma rodada (Quando todos os jogadores jogarem termina a rodada)
            for (int i = 0; i < this.num_players; i++){
                int[] resultado = new int[2];

                String tipo_do_jogador = jogadores.get(i).showPlayerType();

                String mensagem = String.format("Vez do jogador %s [%s]", jogadores.get(i).cor, tipo_do_jogador);
                System.out.println(mensagem);
                mensagem = String.format("Casa Atual: %d", jogadores.get(i).casa_atual);
                System.out.println(mensagem);

                this.printBoard(); // Mostra o tabuleiro antes do jogador fazer sua jogada

                if (this.jogadores.get(i).player_available == 1){ // Siginifica que o jogador pode jogar


                    Scanner teclado = new Scanner(System.in);

                    System.out.println("Digite qualquer coisa para jogar: ");
                    teclado.nextLine();
                    //teclado.close();


                    this.play(resultado, i);

                    // this.printBoard(); // Mostra o tabuleiro depois do jogador fazer sua jogada


                }else { // Jogador não pode jogar
                    System.out.println("Jogador não pode jogar e passa sua vez");
                    this.jogadores.get(i).player_available = 1; // Libera o jogador para jogar a próxima rodada
                }
            }

            // Imprime o fim de uma rodada
            String round_message = String.format("Fim da rodada %d", contador);
            System.out.println(round_message);
            contador++;


            // Loop para mostrar as posições de cada jogador ao fim de cada rodada
            for (int i = 0; i < this.num_players; i++){
                String mensagem = String.format("%s na casa %d \n", this.jogadores.get(i).cor, this.jogadores.get(i).casa_atual);
                System.out.printf(mensagem);
            }

            this.cleanBoard();

        }

    }


    public void cleanBoard(){
        for(int i = 0; i < 20; i++){
            System.out.println(" ");
        }
    }

}
