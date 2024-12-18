package control;

public class ValidaInput {

    // Valida se a string não é nula ou contém somente espaços em branco
    public static boolean string(String input) {
//        return !(input == null || input.isBlank());
        return !(input == null || input.trim().isEmpty());
    }

    // Valida se a string contém apenas números 
    public static boolean stringEhInt(String input) {
        return input.matches("^\\d+$");
    }

    // Valida se a string contém apenas números e uma única vírgula
    public static boolean stringEhDouble(String input) {
        // Verifica se a string no formato de números com no máximo uma vírgula
        return input.matches("^\\d+,?\\d*$");
    }

}
