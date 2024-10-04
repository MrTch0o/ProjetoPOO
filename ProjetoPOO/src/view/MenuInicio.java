package view;

import javax.swing.JOptionPane;
import java.util.Calendar;

public class MenuInicio {

    static Calendar calendario = Calendar.getInstance(); // Inicializando o calendário com a data atual

    public int menuInicial() {
        String[] opcoes = {"Login", "Entrar sem Registrar", "Registrar", "Sair"};
        int escolha = JOptionPane.showOptionDialog(null, "Bem-vindo ao Sistema de Gerenciamento de Casamento!", "Menu Principal",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[2]);
        return escolha;
    }

    public int menuLogin() {
        String[] perfis = {"Administrador", "Convidado", "Voltar"};
        int escolhaPerfil = JOptionPane.showOptionDialog(null, "Escolha o perfil de acesso", "Seleção de Perfil",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, perfis, perfis[perfis.length - 1]);
        return escolhaPerfil;
    }

    //INICIO MENU ADMINISTRADOR
    public int menuAdministrador() {
        String[] opcoes = {"Gerenciar Pessoas", "Gerenciar Usuarios", "Gerenciar Fornecedores", "Gerenciar Convidados", "Gerenciar Pagamentos", "Gerenciar Calendário", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Menu Administrador", "Administração", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
        return escolha;
    }

    public int menuPessoas() {
        String[] opcoes = {"Incluir Pessoas", "Alterar Pessoa", "Remover Pessoa", "Visualizar Pessoa", "Visualizar Todas Pessoas", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Menu Pessoas", "Pessoas", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
        return escolha;
    }

    public int menuUsuario() {
        String[] opcoes = {"Incluir Usuario", "Alterar Usuario", "Remover Usuario", "Visualizar Usuario", "Visualizar Todos Usuarios", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Menu Usuario", "Usuários", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
        return escolha;
    }

    public int menuGerenciarFornecedores() {
        String[] opcoes = {"Incluir Fornecedor", "Alterar Fornecedor", "Remover Fornecedor", "Visualizar Fornecedor", "Visualizar Todos Fornecedores", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Gerenciar Fornecedores", "Fornecedores", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
        return escolha;
    }

    public int menuGerenciarConvidados() {
        String[] opcoes = {"Incluir Convite Individual", "Incluir Convite Família", "Remover Convite ID", "Remover Convite Familia", "Visualizar Convite", "Visualizar Todos Convites", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Gerenciar Convidados", "Convidados", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
        return escolha;
    }

    public int menuGerenciarPagamentos() {
        String[] opcoes = {"Incluir Pagamento", "Alterar Pagamento", "Remover Pagamento", "Verificar Pagamentos ID", "Verificar Todos os Pagamentos", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Menu de Pagamentos", "Pagamentos", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
        return escolha;
    }

    public int menuGerenciarCalendario() {
 
            String[] opcoes = {"Adicionar Evento", "Alterar Evento", "Remover Evento", "Visualizar Eventos Hoje", "Visualizar Calendário", "Voltar"};
            int escolha = JOptionPane.showOptionDialog(null, "Gerenciamento de Calendário", "Calendário", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
            return escolha;

    }
//FIM MENU ADMINISTRADOR

//INICIO MENU CONVIDADO
    public int menuConvidado() {

        String[] opcoes = {"Presentes", "Recados", "Confirmar Presença", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Menu Convidado", "Convidado", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

        return escolha;
    }

    // Menu de Presentes para Convidado
    public void menuPresentesConvidado() {
        String[] opcoes = {"Dar Presente", "Ver Presentes", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Menu Presentes", "Presentes", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

        switch (escolha) {
            case 0:
                JOptionPane.showMessageDialog(null, "Dando presente.");
                // Função de dar presente aqui
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "Exibindo lista de presentes.");
                // Função de exibição de presentes aqui
                break;
            case 2:
                return; // Volta ao menu anterior
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida.");
        }
    }

    // Menu de Recados para Convidado
    public void menuRecadosConvidado() {
        String[] opcoes = {"Ver Recados", "Deixar Recado", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Menu Recados", "Recados", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

        switch (escolha) {
            case 0:
                JOptionPane.showMessageDialog(null, "Exibindo mural de recados.");
                // Função de exibição de recados
                break;
            case 1:
                deixarRecadoLogado();
                break;
            case 2:
                return; // Volta ao menu anterior
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida.");
        }
    }

    // Deixar Recado no Menu Logado
    public void deixarRecadoLogado() {
        String idConvidado = JOptionPane.showInputDialog("Digite o ID do convidado:");
        String recado = JOptionPane.showInputDialog(null, "Digite seu recado (até 4000 caracteres):",
                "Deixar Recado", JOptionPane.PLAIN_MESSAGE);

        if (recado.length() > 4000) {
            recado = recado.substring(0, 4000); // Trunca o recado para 4000 caracteres
        }

        JOptionPane.showMessageDialog(null, "Recado deixado com sucesso!\n" + recado);
        // Lógica para armazenar o recado
    }

    // Método de Confirmar Presença
    public void confirmarPresenca() {
        String idConvidado = JOptionPane.showInputDialog("Digite o ID do convidado:");

        if (idConvidado != null && !idConvidado.isEmpty()) {
            // Lógica para confirmar presença (armazenar ou marcar a presença)
            JOptionPane.showMessageDialog(null, "Presença confirmada com sucesso para o convidado com ID: " + idConvidado);
        } else {
            JOptionPane.showMessageDialog(null, "ID do convidado inválido. Tente novamente.");
        }
    }
//FIM MENU CONVIDADO   

//INICIO MENU NAO LOGADO
    public void menuNaoLogado() {
        while (true) {
            String[] opcoes = {"Presentes", "Recados", "Voltar"};
            int escolha = JOptionPane.showOptionDialog(null, "Menu Não Logado", "Menu", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

            switch (escolha) {
                case 0:
                    menuPresentesNaoLogado();
                    break;
                case 1:
                    menuRecadosNaoLogado();
                    break;
                case 2:
                    return; // Volta ao menu anterior
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
        }
    }

    // Menu de Presentes para Não Logado
    public void menuPresentesNaoLogado() {
        String[] opcoes = {"Dar Presente", "Ver Presentes", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Menu Presentes", "Presentes", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

        switch (escolha) {
            case 0:
                JOptionPane.showMessageDialog(null, "Dando presente.");
                // Função de dar presente aqui
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "Exibindo lista de presentes.");
                // Função de exibição de presentes aqui
                break;
            case 2:
                return; // Volta ao menu anterior
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida.");
        }
    }

    // Menu de Recados para Não Logado
    public void menuRecadosNaoLogado() {
        String[] opcoes = {"Ver Recados", "Deixar Recado", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Menu Recados", "Recados", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

        switch (escolha) {
            case 0:
                JOptionPane.showMessageDialog(null, "Exibindo mural de recados.");
                // Função de exibição de recados
                break;
            case 1:
                deixarRecadoNaoLogado();
                break;
            case 2:
                return; // Volta ao menu anterior
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida.");
        }
    }

    // Deixar Recado no Menu Não Logado
    public void deixarRecadoNaoLogado() {
        String nome = JOptionPane.showInputDialog("Digite seu nome:");
        String recado = JOptionPane.showInputDialog(null, "Digite seu recado (até 4000 caracteres):",
                "Deixar Recado", JOptionPane.PLAIN_MESSAGE);

        if (recado.length() > 4000) {
            recado = recado.substring(0, 4000); // Trunca o recado para 4000 caracteres
        }

        JOptionPane.showMessageDialog(null, "Recado deixado com sucesso!\n" + recado);
        // Lógica para armazenar o recado
    }
//FIM MENU NAO LOGADO

}
