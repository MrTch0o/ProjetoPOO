package model.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils extends Identifiable{

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate data;

    //formato dd-MM-yy
    public LocalDate formatDate(String data) throws Exception {
            this.data = LocalDate.parse(data, formatter);
        return this.data;
    }
    
    public String formatDateToString(LocalDate data){
        return data.format(formatter);
    }
    
    // Valida se a string contém apenas números e uma única vírgula
    public static String formatDouble(String input) {
        // Se contém vírgula, devemos garantir que haja duas casas decimais
        input = input.replace(".", ",");
        if (input.contains(",")) {
            // Separa a parte inteira da parte decimal
            String[] partes = input.split(",");
            String parteInteira = partes[0];
            String parteDecimal = partes.length > 1 ? partes[1] : "";

            // Se a parte decimal tem menos de 2 dígitos, completar com zeros
            while (parteDecimal.length() < 2) {
                parteDecimal += "0";
            }
            // Retorna a string formatada com duas casas decimais
            return parteInteira + "." + parteDecimal.substring(0, 2);
        } else if (!input.contains(",")) {
            // Se não há vírgula, adicionar ",00" para representar duas casas decimais
            return input + ".00";
        } else {
            // Se não está no formato correto, retorna null ou uma mensagem de erro
            return null;
        }

    }
}
