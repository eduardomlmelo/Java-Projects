//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

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


        //Tabuleiro_beta board_game = new Tabuleiro_beta(opcao);

        Tabuleiro board_game = new Tabuleiro(opcao);

        scanner01.close();
    }
}