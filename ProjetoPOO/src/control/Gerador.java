/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import java.util.Random;

/**
 *
 * @author DEVENG
 */

public class Gerador {

    // Arrays de nomes, telefones e datas de nascimento
    private static final String[] NOMES = {
        "Ana", "Beatriz", "Carlos", "Daniel", "Eduardo", "Fernanda", "Gabriel", 
        "Heloisa", "Isabela", "João", "Karen", "Lucas", "Mariana", "Nathalia",
        "Otávio", "Paula", "Ricardo", "Sofia", "Tiago", "Vinícius"
    };
    
    private static final String[] TELEFONES = {
        "(11) 91234-5678", "(21) 99876-5432", "(31) 98765-4321", "(41) 99812-3456", 
        "(51) 99765-4321", "(61) 91234-8765", "(71) 99876-5432", "(81) 98712-3456",
        "(91) 99765-8765", "(22) 91123-4567", "(32) 99871-2345", "(42) 98761-2345",
        "(52) 91234-1234", "(62) 99871-4321", "(72) 98765-8765", "(82) 91234-9999",
        "(92) 91123-1111", "(33) 99876-1111", "(43) 98765-2222", "(53) 91234-2222"
    };

    private static final String[] DATAS_NASCIMENTO = {
        "01/01/1990", "02/02/1985", "03/03/1978", "04/04/2000", "05/05/1995", 
        "06/06/1992", "07/07/1988", "08/08/1991", "09/09/1999", "10/10/1983", 
        "11/11/1994", "12/12/1987", "13/01/1990", "14/02/1985", "15/03/1978", 
        "16/04/2000", "17/05/1995", "18/06/1992", "19/07/1988", "20/08/1991"
    };

    // Objeto Random para gerar números aleatórios
    private static final Random RANDOM = new Random();

    // Método que gera um nome aleatório com base em um índice
    public  String gerarNome() {
        int indiceAleatorio = RANDOM.nextInt(NOMES.length);
        return NOMES[indiceAleatorio];
    }

    // Método que gera um telefone aleatório com base em um índice
    public  String gerarTelefone() {
        int indiceAleatorio = RANDOM.nextInt(TELEFONES.length);
        return TELEFONES[indiceAleatorio];
    }

    // Método que gera uma data de nascimento aleatória com base em um índice
    public  String gerarDataNascimento() {
        int indiceAleatorio = RANDOM.nextInt(DATAS_NASCIMENTO.length);
        return DATAS_NASCIMENTO[indiceAleatorio];
    }

 /*   // Exemplo de uso da classe
    public static void main(String[] args) {
        // Gera e imprime um nome, telefone e data de nascimento aleatórios
        for (int i = 0; i < 5; i++) {
            System.out.println("Nome aleatório: " + gerarNome());
            System.out.println("Telefone aleatório: " + gerarTelefone());
            System.out.println("Data de Nascimento aleatória: " + gerarDataNascimento());
            System.out.println("------");
        }
    }
*/
}
