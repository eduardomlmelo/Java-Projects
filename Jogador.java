import java.util.Random;

public class Jogador {
    protected String cor;
    protected int player_type;
    protected int player_available;
    protected int numero;
    protected int casa_atual;
    protected int jogadas;

    public Jogador(String cor, int numero){
        this.cor = cor;
        this.numero = numero;

        this.player_available = 1; // 0: Não pode jogar 1: Pode jogar
        this.randomize_player_type(); // 0: Azarado 1: Sortudo 2: Normal
        this.casa_atual = 0; // Casa inicial é a Zero

    }

    public void randomize_player_type(){
        Random randomizer = new Random();
        int player_type = randomizer.nextInt(3); // Randomizador vai de 0 até 2 (3 - 1)
        this.player_type = player_type;
    }

    public String showPlayerType(){
        if(this.player_type == 0){
            return "AZARADO";
        } else if (this.player_type == 1) {
            return "SORTUDO";
        } else if (this.player_type == 2) {
            return "NORMAL";
        }
        return "Erro de Tipo";
    }

    public int[] playDices(){
        int[] resultado = new int[2]; // Vetor que armazena a soma dos dados (posisao 0) e se houve dados iguais ou não(posicao 1)
        resultado[1] = 0; // Define por padrão que não houve valores iguais nos dados

        Random randomizer = new Random();
        int num1 = randomizer.nextInt(7);
        int num2 = randomizer.nextInt(7);
        int soma = num1 + num2;

        if(this.player_type == 0){ // Jogador azarado só pode ter resultados no máximo 6
            if(soma >= 7){
                soma = 6;
                resultado[0] = soma;
            }else {
                resultado[0] = soma;
            }
        } else if (this.player_type == 1) { // Jogador sortudo só pode ter resultados no mínimo 7
            if(soma <= 6){
                soma = 7;
                resultado[0] = soma;
            }else {
                resultado[0] = soma;
            }
        } else if (this.player_type == 2) { // Jogador normal pode ter qualquer resultado
            resultado[0] = soma;
        }

        if(num1 == num2){
            resultado[1] = 1; // Significa que houve valores iguais nos dados
        }

        String mensagem = String.format("Soma dos dados: %d \n", resultado[0]);
        System.out.println(mensagem);

        return resultado;
    }

    public void move(int resultado){
        this.casa_atual += resultado;
    }

    public void showData(){
        // Condicional só para ajeitar a saída final do showData porque a casa atual pode ser maior que 39
        if (this.casa_atual > 39){
            this.casa_atual = 39;
        }

        String mensagem = String.format("%s teve %d jogadas e terminou na casa %d", this.cor, this.jogadas, this.casa_atual);
        System.out.println(mensagem);
    }

}