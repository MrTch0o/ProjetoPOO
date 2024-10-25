package control;

import java.util.Random;

public class Gerador {

    // Arrays de nomes, telefones e datas de nascimento
    private final String[] NOMES = {
        "Ana", "Beatriz", "Carlos", "Daniel", "Eduardo", "Fernanda", "Gabriel",
        "Heloisa", "Isabela", "João", "Karen", "Lucas", "Mariana", "Nathalia",
        "Otávio", "Paula", "Ricardo", "Sofia", "Tiago", "Vinícius"
    };

    private final String[] TELEFONES = {
        "(11) 91234-5678", "(21) 99876-5432", "(31) 98765-4321", "(41) 99812-3456",
        "(51) 99765-4321", "(61) 91234-8765", "(71) 99876-5432", "(81) 98712-3456",
        "(91) 99765-8765", "(22) 91123-4567", "(32) 99871-2345", "(42) 98761-2345",
        "(52) 91234-1234", "(62) 99871-4321", "(72) 98765-8765", "(82) 91234-9999",
        "(92) 91123-1111", "(33) 99876-1111", "(43) 98765-2222", "(53) 91234-2222"
    };

    private final String[] DATAS_NASCIMENTO = {
        "01/01/1990", "02/02/1985", "03/03/1978", "04/04/2000", "05/05/1995",
        "06/06/1992", "07/07/1988", "08/08/1991", "09/09/1999", "10/10/1983",
        "11/11/1994", "12/12/1987", "13/01/1990", "14/02/1985", "15/03/1978",
        "16/04/2000", "17/05/1995", "18/06/1992", "19/07/1988", "20/08/1991"
    };

    // Objeto Random para gerar números aleatórios
    private final Random RANDOM = new Random();

    // Método que gera um nome aleatório com base em um índice
    public String gerarNome() {
        int indiceAleatorio = RANDOM.nextInt(NOMES.length);
        return NOMES[indiceAleatorio];
    }

    // Método que gera um telefone aleatório com base em um índice
    public String gerarTelefone() {
        int indiceAleatorio = RANDOM.nextInt(TELEFONES.length);
        return TELEFONES[indiceAleatorio];
    }

    // Método que gera uma data de nascimento aleatória com base em um índice
    public String gerarDataNascimento() {
        int indiceAleatorio = RANDOM.nextInt(DATAS_NASCIMENTO.length);
        return DATAS_NASCIMENTO[indiceAleatorio];
    }

    // Lista de presentes, suas cotas e valores estimados
    private static final String[] PRESENTES = {
        "Jogo de Panelas", "Conjunto de Copos", "Aparelho de Jantar", "Churrasqueira", "Batedeira",
        "Grill Elétrico", "Cafeteira", "Micro-ondas", "Liquidificador", "Torradeira",
        "Frigideira", "Panela de Pressão", "Assadeira", "Conjunto de Facas", "Espremedor de Frutas",
        "Ferro de Passar", "Máquina de Lavar Louça", "Aspirador de Pó", "Secador de Cabelos", "Cortador de Legumes",
        "Conjunto de Xícaras", "Sanduicheira", "Processador de Alimentos", "Ventilador", "Ar Condicionado",
        "Purificador de Água", "Lava-Roupas", "TV LED", "Home Theater", "Geladeira"
    };
    
    // Cotas para cada presente
    private static final int[] COTAS = {
        10, 8, 12, 6, 15, 10, 5, 7, 8, 6,
        12, 10, 5, 7, 8, 6, 12, 10, 5, 7,
        8, 10, 12, 6, 15, 10, 12, 8, 7, 5
    }; 

    //Valores estimados para cada presente
    private static final double[] VALORES_ESTIMADOS = {
        300.00, 150.00, 500.00, 400.00, 200.00, 250.00, 180.00, 600.00, 120.00, 100.00,
        80.00, 90.00, 60.00, 110.00, 70.00, 150.00, 1200.00, 300.00, 150.00, 50.00,
        40.00, 100.00, 400.00, 200.00, 1500.00, 250.00, 1600.00, 2000.00, 1200.00, 3000.00
    }; 

    public int getIndicePresente() {
        int indiceAleatorio = RANDOM.nextInt(PRESENTES.length);
        return indiceAleatorio;
    }
    
    public String getPresente(int indice) {
        return PRESENTES[indice];
    }

    public int getCota(int indice) {
        return COTAS[indice];
    }

    public double getValorEstimado(int indice) {
        return VALORES_ESTIMADOS[indice];
    }

    // Método para exibir a lista de presentes com cotas e valores de 5 em 5
    public String getListaPresentes() {
        String lista = ("Presentes disponíveis:\n");
        for (int i = 0; i < PRESENTES.length; i++) {
            double valorCota = 0;
            if (COTAS[i] == 0) {
                valorCota = 0;
            } else {
                valorCota = VALORES_ESTIMADOS[i] / COTAS[i]; // Calcula o valor de cada cota
            }
            lista += ((i + 1)
                    + "." + PRESENTES[i]
                    + " - Valor estimado: R$"
                    + String.format("%.2f", VALORES_ESTIMADOS[i])
                    + " - Valor da cota: R$"
                    + String.format("%.2f", valorCota)
                    + " - Cotas restantes: "
                    + COTAS[i]
                    + "\n");

            // Exibir 5 presentes por linha
            if ((i + 1) % 5 == 0) {
                lista += ("\n");
            }
        }
        return lista;
    }

    // Método para dar presente
    public boolean darPresente(int numeroPresente, int cotas) {
        if (COTAS[numeroPresente] >= cotas) {
            COTAS[numeroPresente] -= cotas; // Atualiza as cotas disponíveis
            return true;
        } else {
            return false; // Não há cotas suficientes
        }
    }

    // Método para obter o número de cotas disponíveis de um presente
    public int getCotasDisponiveis(int numeroPresente) {
        return COTAS[numeroPresente];
    }

}
