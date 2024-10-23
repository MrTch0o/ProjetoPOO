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
        String[] opcoes = {"Gerenciar Pessoas", "Gerenciar Usuarios", "Gerenciar Fornecedores", "Gerenciar Convidados", "Gerenciar Presentes", "Gerenciar Pagamentos", "Gerenciar Calendário", "Mural de Recados", "Voltar"};
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
        String[] opcoes = {"Incluir Usuario\n", "Alterar Usuario", "Remover Usuario", "Visualizar Usuario", "Visualizar Todos Usuarios", "Voltar"};
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

    public int menuGerenciarPresentes() {
        String[] opcoes = {"Incluir Presente", "Alterar Presente", "Remover Presente", "Visualizar Presentes Cadastrados", "Visualizar Presentes Recebidos", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Gerenciar Presentes", "Presentes", JOptionPane.DEFAULT_OPTION,
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

        String[] opcoes = {"Dar Presentes", "Deixar Recados", "Confirmar Presença", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Menu Convidado", "Convidado", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

        return escolha;
    }

//FIM MENU CONVIDADO
//INICIO MENU NAO LOGADO
    public int menuNaoLogado() {

        String[] opcoes = {"Dar Presentes", "Deixar Recados", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Menu Não Logado", "Menu", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
        return escolha;

    }
}
