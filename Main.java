import java.util.Scanner;
//import Classes.Tabuleiro;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int opcao;
        Scanner scanner01 = new Scanner(System.in);

        while (true){

            System.out.printf("Digite a quantidade de jogadores da partida (Máx 6)(Mín 2): ");
            opcao = scanner01.nextInt();

            if (opcao < 2){
                System.out.println("Quantidade inválida");
                continue;
            } else if (opcao > 6) {
                System.out.println("Quantidade inválida");
                continue;
            }

            break;

        }

        Tabuleiro board_game = new Tabuleiro(opcao);

        scanner01.close();

        }

}
