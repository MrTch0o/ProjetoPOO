package control;

import java.awt.HeadlessException;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import model.Pessoa;
import model.Usuario;
import model.Fornecedor;
import model.Convidado;
import model.Familia;
import model.Pagamento;
import model.Calendario;
import model.MuralRecado;
import model.Presente;
import model.PresenteRecebido;
import model.dao.Database;
import model.dao.Utils;
import view.MenuInicio;

public class Controller {

    boolean controlForm = true; //Variavel para controle de Form;
    int escolha;
    MenuInicio menuInicio = new MenuInicio();

    Pessoa pessoa;
    Usuario usuario;
    Fornecedor fornecedor;
    Convidado convidado;
    Familia familia;
    Pagamento pagamento;
    Calendario calendario;
    Presente presente;
    PresenteRecebido presenteRecebido;
    MuralRecado muralRecado;
    Pessoa[] todasPessoas;
    Usuario[] todosUsuarios;
    Fornecedor[] todosFornecedores;
    Convidado[] todosConvidados;
    Pagamento[] todosPagamentos;
    Calendario[] todosCalendarios;
    Presente[] todosPresentes;
    PresenteRecebido[] todosPresentesRecebido;
    MuralRecado[] todosMuralRecado;
    Database<Pessoa> pessoasDatabase = new Database<>(new Pessoa[0]);
    Database<Usuario> usuariosDatabase = new Database<>(new Usuario[0]);
    Database<Fornecedor> fornecedoresDatabase = new Database<>(new Fornecedor[0]);
    Database<Convidado> convidadosDatabase = new Database<>(new Convidado[0]);
    Database<Pagamento> pagamentosDatabase = new Database<>(new Pagamento[0]);
    Database<Calendario> calendariosDatabase = new Database<>(new Calendario[0]);
    Database<Presente> presentesDatabase = new Database<>(new Presente[0]);
    Database<PresenteRecebido> presentesRecebidosDatabase = new Database<>(new PresenteRecebido[0]);
    Database<MuralRecado> muralRecadoDatabase = new Database<>(new MuralRecado[0]);
    Utils utils = new Utils();
    Gerador gerador = new Gerador();

    public void main(String[] args) {

        while (controlForm) {
            escolha = menuInicio.menuInicial();
            if (escolha == 3 || escolha == -1) {
                escolha = 3;
            }

            switch (escolha) {
                case 0:
                    while (controlForm) {
                        int escolhaPerfil = menuInicio.menuLogin();
                        perfilLogin(escolhaPerfil);
                    }
                    controlForm = true;
                    break;
                case 1:
                    while (controlForm) {
                        int escolhaPerfil = menuInicio.menuNaoLogado();
                        perfilNaoLogado(escolhaPerfil);
                    }
                    controlForm = true;
                    break;
                case 2: // Função de registro aqui
                    while (controlForm) {
                        controlForm = registraUsuario();
                    }
                    controlForm = true;
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Saindo do sistema.");
                    System.exit(0);
            }
        }
    }

    public void perfilLogin(int escolhaPerfil) {
        if (escolhaPerfil == 2 || escolhaPerfil == -1) {
            controlForm = false;
            return;
        }
        String login = JOptionPane.showInputDialog("Digite seu login:");
        if (!ValidaInput.string(login)) {
            return;
        }
        String senha = JOptionPane.showInputDialog("Digite sua senha:");
        if (!ValidaInput.string(senha)) {
            return;
        }

        while (true) {
            switch (escolhaPerfil) {
                case 0:
                    todosUsuarios = usuariosDatabase.getAll();
                    boolean logado = false;
                    for (Usuario u : todosUsuarios) {
                        if ((login.equalsIgnoreCase(u.getLogin()) && senha.equals(u.getSenha())) && u.isAdmin()) {
                            logado = true;
                            u.setLogado(true);
                            usuariosDatabase.update(u);
                            while (controlForm) {
                                int escolhaAdm = menuInicio.menuAdministrador();
                                perfilAdm(escolhaAdm);
                            }
                            u.setLogado(false);
                            usuariosDatabase.update(u);
                            controlForm = false;
                            break;
                        }
                    }
                    if (!logado) {
                        JOptionPane.showMessageDialog(null, "Login ou senha inválidos para Administrador.");
                    }
                    logado = false;
                    controlForm = true;
                    return;

                case 1:
                    todosUsuarios = usuariosDatabase.getAll();
                    logado = false;
                    for (Usuario u : todosUsuarios) {
                        if ((login.equalsIgnoreCase(u.getLogin()) && senha.equals(u.getSenha())) && !u.isAdmin()) {
                            logado = true;
                            u.setLogado(true);
                            usuariosDatabase.update(u);
                            while (controlForm) {
                                int escolhaConvidado = menuInicio.menuConvidado();
                                perfilConvidado(escolhaConvidado);
                            }
                            u.setLogado(false);
                            usuariosDatabase.update(u);
                            controlForm = false;
                            break;
                        }
                    }
                    if (!logado) {
                        JOptionPane.showMessageDialog(null, "Login ou senha inválidos para Convidado.");
                    }
                    logado = false;
                    controlForm = true;
                    return;
            }
        }
    }
    //INICIO MENU ADMINISTRADOR

    public void perfilAdm(int escolhaAdm) {
        if (escolhaAdm == 8 || escolhaAdm == -1) {
            controlForm = false;
            return;
        }
        while (controlForm) {
            switch (escolhaAdm) {
                case 0:
                    while (controlForm) {
                        int escolhaPessoa = menuInicio.menuPessoas();
                        perfilPessoa(escolhaPessoa);
                    }
                    controlForm = true;
                    return;
                case 1:
                    while (controlForm) {
                        int escolhaUsuario = menuInicio.menuUsuario();
                        perfilUsuario(escolhaUsuario);
                    }
                    controlForm = true;
                    return;
                case 2:
                    while (controlForm) {
                        int escolhaFornecedor = menuInicio.menuGerenciarFornecedores();
                        perfilGerenciarFornecedor(escolhaFornecedor);
                    }
                    controlForm = true;
                    return;
                case 3:
                    while (controlForm) {
                        int escolhaConvidado = menuInicio.menuGerenciarConvidados();
                        perfilGerenciarConvidado(escolhaConvidado);

                    }
                    controlForm = true;
                    return;
                case 4:
                    while (controlForm) {
                        int escolhaPresente = menuInicio.menuGerenciarPresentes();
                        perfilGerenciarPresente(escolhaPresente);

                    }
                    controlForm = true;
                    return;
                case 5:
                    while (controlForm) {
                        int escolhaPagamento = menuInicio.menuGerenciarPagamentos();
                        perfilGerenciarPagamento(escolhaPagamento);

                    }
                    controlForm = true;
                    return;
                case 6:
                    while (controlForm) {
                        int escolhaCalendario = menuInicio.menuGerenciarCalendario();
                        perfilGerenciarCalendario(escolhaCalendario);
                    }
                    controlForm = true;
                    return;
                case 7:
                    todosMuralRecado = muralRecadoDatabase.getAll();
                    String strMuralRecado = "";
                    for (MuralRecado mr : todosMuralRecado) {
                        strMuralRecado += mr.toString() + "\n";
                    }
                    JOptionPane.showMessageDialog(null, strMuralRecado, "Mural de Recados", JOptionPane.INFORMATION_MESSAGE);
                    return;
            }
        }
    }

    public void perfilPessoa(int escolhaPessoa) {
        if (escolhaPessoa == 5 || escolhaPessoa == -1) {
            controlForm = false;
            return;
        }

        switch (escolhaPessoa) {
            case 0: // incluir
                pessoa = new Pessoa();

                // Validação de nome
                String nome = JOptionPane.showInputDialog("Digite seu nome:", gerador.gerarNome());
                if (!ValidaInput.string(nome)) {
                    return; // Volta ao menu se cancelar ou fechar
                }
                pessoa.setNome(nome);

                // Validação de telefone
                String telefone = JOptionPane.showInputDialog("Digite seu telefone:", gerador.gerarTelefone());
                if (!ValidaInput.string(telefone)) {
                    return; // Volta ao menu se cancelar ou fechar
                }
                pessoa.setTelefone(telefone);

                // Validação de nascimento
                String nascimento = JOptionPane.showInputDialog("Digite o seu nascimento (dd/MM/yyyy):", gerador.gerarDataNascimento());
                if (!ValidaInput.string(nascimento)) {
                    return; // Volta ao menu se cancelar ou fechar
                }
                try {
                    pessoa.setNascimento(utils.formatDate(nascimento));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data inválida");
                    return;
                }
                pessoasDatabase.create(pessoa);
                JOptionPane.showMessageDialog(null, "Pessoa incluída com sucesso!");
                return;

            case 1: // alterar
                todasPessoas = pessoasDatabase.getAll();
                String strPessoaId = "Digite o ID da pessoa que deseja alterar:" + "\n";
                for (Pessoa p : todasPessoas) {
                    strPessoaId += p.toString() + "\n";
                }
                String idAlterar = JOptionPane.showInputDialog(null, strPessoaId, "Pessoas", JOptionPane.QUESTION_MESSAGE);
                if (!ValidaInput.string(idAlterar) || !ValidaInput.stringEhInt(idAlterar)) { // Verifica se contem somente numero na string
                    return;
                }
                pessoa = pessoasDatabase.getById(Integer.parseInt(idAlterar));
                if (pessoa != null) {
                    // Alterar nome
                    String novoNome = JOptionPane.showInputDialog("Digite o novo nome:", pessoa.getNome());
                    if (!ValidaInput.string(novoNome)) {
                        return; // Volta ao menu se cancelar ou fechar
                    }
                    pessoa.setNome(novoNome);

                    // Alterar telefone
                    String novoTelefone = JOptionPane.showInputDialog("Digite o novo telefone:", pessoa.getTelefone());
                    if (!ValidaInput.string(novoTelefone)) {
                        return; // Volta ao menu se cancelar ou fechar
                    }
                    pessoa.setTelefone(novoTelefone);

                    // Alterar data de nascimento
                    String novoNascimento = JOptionPane.showInputDialog("Digite a nova data de nascimento (dd/MM/yyyy):", utils.formatDateToString(pessoa.getNascimento()));
                    if (!ValidaInput.string(novoNascimento)) {
                        return; // Volta ao menu se cancelar ou fechar
                    }
                    try {
                        pessoa.setNascimento(utils.formatDate(novoNascimento));
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Data inválida");
                        return;
                    }

                    pessoa.setDataModificacao();
                    pessoasDatabase.update(pessoa);
                    JOptionPane.showMessageDialog(null, "Pessoa alterada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Pessoa com ID " + idAlterar + " não encontrada.");
                }
                return;

            case 2: // remover
                todasPessoas = pessoasDatabase.getAll();
                strPessoaId = "Digite o ID da pessoa que deseja remover:" + "\n";
                for (Pessoa p : todasPessoas) {
                    strPessoaId += p.toString() + "\n";
                }
                String idRemover = JOptionPane.showInputDialog(null, strPessoaId, "Pessoas", JOptionPane.QUESTION_MESSAGE);
                if (!ValidaInput.string(idRemover) || !ValidaInput.stringEhInt(idRemover)) {
                    return;
                }
                pessoa = pessoasDatabase.getById(Integer.parseInt(idRemover));
                usuario = usuariosDatabase.getById(Integer.parseInt(idRemover));
                fornecedor = fornecedoresDatabase.getById(Integer.parseInt(idRemover));
                if (usuario != null) {
                    JOptionPane.showMessageDialog(null, "Existe usuário vinculado a este ID. Por favor delete o usuário");
                    return;
                } else if (fornecedor != null) {
                    JOptionPane.showMessageDialog(null, "Existe fornecedor vinculado a este ID. Por favor delete o fornecedor");
                    return;
                } else if (pessoa != null) {
                    int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover a pessoa com ID " + idRemover + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (confirmacao == JOptionPane.YES_OPTION) {
                        pessoasDatabase.delete(Integer.parseInt(idRemover));
                        JOptionPane.showMessageDialog(null, "Pessoa removida com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Remoção cancelada.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Pessoa com ID " + idRemover + " não encontrada.");
                }
                return;

            case 3: // visualizar
                // Visualizar pessoa pelo ID
                String idVisualizar = JOptionPane.showInputDialog("Digite o ID da pessoa que deseja visualizar:");
                if (!ValidaInput.string(idVisualizar) || !ValidaInput.stringEhInt(idVisualizar)) {
                    return;
                }
                pessoa = pessoasDatabase.getById(Integer.parseInt(idVisualizar));
                if (pessoa != null) {
                    JOptionPane.showMessageDialog(null, pessoa.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Pessoa com ID " + idVisualizar + " não encontrada.");
                }
                return;

            case 4: // visualizar todos
                todasPessoas = pessoasDatabase.getAll();
                if (todasPessoas.length > 0) {
                    String strPessoa = "";
                    for (Pessoa p : todasPessoas) {
                        strPessoa += p.toString() + "\n";
                    }
                    JOptionPane.showMessageDialog(null, strPessoa);
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhuma pessoa cadastrada.");
                }
        }
    }

    public void perfilUsuario(int escolhaUsuario) {
        if (escolhaUsuario == 5 || escolhaUsuario == -1) {
            controlForm = false;
            return;
        }
        switch (escolhaUsuario) {

            case 0: // incluir
                usuario = new Usuario();
                todasPessoas = pessoasDatabase.getAll();
                String strPessoaId = "Digite o ID da pessoa para criar usuário:" + "\n";
                for (Pessoa p : todasPessoas) {
                    strPessoaId += p.toString() + "\n";
                }
                String pessoaId = JOptionPane.showInputDialog(null, strPessoaId, "Pessoas", JOptionPane.QUESTION_MESSAGE);
                if (!ValidaInput.string(pessoaId) || !ValidaInput.stringEhInt(pessoaId)) { // Verifica se contem somente numero na string
                    return;
                }
                pessoa = pessoasDatabase.getById(Integer.parseInt(pessoaId));
                if (pessoa == null) {
                    JOptionPane.showMessageDialog(null, "Pessoa com ID " + pessoaId + " não encontrada.");
                    return;
                }
                if (usuariosDatabase.getById(Integer.parseInt(pessoaId)) != null) {
                    JOptionPane.showMessageDialog(null, "Pessoa com ID " + pessoaId + " já tem usuário vinculado.");
                    return;
                }

                usuario.setPessoa(pessoa);
                String login = JOptionPane.showInputDialog("Digite o login do usuário:");
                if (!ValidaInput.string(login)) {
                    return; // Volta ao menu se cancelar ou fechar
                }
                usuario.setLogin(login);

                String senha = JOptionPane.showInputDialog("Digite a senha do usuário:");
                if (!ValidaInput.string(senha)) {
                    return; // Volta ao menu se cancelar ou fechar
                }
                usuario.setSenha(senha);

                String tipo = JOptionPane.showInputDialog("Digite o tipo do usuário (0 - Administrador | 1 - Usuario):", "1");
                if (!ValidaInput.string(tipo) || !ValidaInput.stringEhInt(tipo) || (!"0".equals(tipo) && !"1".equals(tipo))) {
                    return; // Volta ao menu se cancelar ou fechar
                }
                usuario.setTipo(Integer.parseInt(tipo));
                if (usuario.getTipo() == 0) {
                    usuario.setAdmin(true);
                }

                // Criar usuário no banco de dados
                usuariosDatabase.create(usuario);

                JOptionPane.showMessageDialog(null, "Usuário incluído com sucesso!");
                return;

            case 1: // alterar

                todosUsuarios = usuariosDatabase.getAll();
                String strUsuarioId = "Digite o ID do usuário:" + "\n";
                for (Usuario p : todosUsuarios) {
                    strUsuarioId += p.toString() + "\n";
                }
                String idAlterar = JOptionPane.showInputDialog(null, strUsuarioId, "Usuários", JOptionPane.QUESTION_MESSAGE);
                if (!ValidaInput.string(idAlterar) || !ValidaInput.stringEhInt(idAlterar)) { // Verifica se contem somente numero na string
                    return;
                }
                usuario = usuariosDatabase.getById(Integer.parseInt(idAlterar));
                if (usuario != null) {
                    String novoLogin = JOptionPane.showInputDialog("Digite o novo login:", usuario.getLogin());
                    if (!ValidaInput.string(novoLogin)) {
                        return;
                    }
                    usuario.setLogin(novoLogin);

                    String novaSenha = JOptionPane.showInputDialog("Digite a nova senha:", usuario.getSenha());
                    if (!ValidaInput.string(novaSenha)) {
                        return;
                    }
                    usuario.setSenha(novaSenha);

                    String novoTipo = JOptionPane.showInputDialog("Digite o tipo do usuário (0 - Administrador | 1 - Usuario):", usuario.getTipo());
                    if (!ValidaInput.string(novoTipo) || !ValidaInput.stringEhInt(novoTipo) || (!"0".equals(novoTipo) && !"1".equals(novoTipo))) {
                        return; // Volta ao menu se cancelar ou fechar
                    }
                    usuario.setTipo(Integer.parseInt(novoTipo));
                    if (usuario.getTipo() == 0) {
                        usuario.setAdmin(true);
                    }
                    usuario.setDataModificacao();

                    //Atualizar registro no banco de dados
                    usuariosDatabase.update(usuario);
                    JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário com ID " + idAlterar + " não encontrado.");
                }
                return;

            case 2: // remover
                String idRemover = JOptionPane.showInputDialog("Digite o ID do usuário:");
                if (!ValidaInput.string(idRemover) || !ValidaInput.stringEhInt(idRemover)) { // Verifica se contem somente numero na string
                    return;
                }
                usuario = usuariosDatabase.getById(Integer.parseInt(idRemover));
                if (usuario != null) {
                    int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o usuário com ID " + idRemover + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (confirmacao == JOptionPane.YES_OPTION) {
                        usuariosDatabase.delete(Integer.parseInt(idRemover));
                        JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Remoção cancelada.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário com ID " + idRemover + " não encontrada.");
                }
                return;

            case 3: // visualizar
                // Visualizar pessoa pelo ID
                String idVisualizar = JOptionPane.showInputDialog("Digite o ID do usuário que deseja visualizar:");
                if (!ValidaInput.string(idVisualizar) || !ValidaInput.stringEhInt(idVisualizar)) {
                    return;
                }
                usuario = usuariosDatabase.getById(Integer.parseInt(idVisualizar));
                if (usuario != null) {
                    JOptionPane.showMessageDialog(null, usuario.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário com ID " + idVisualizar + " não encontrado.");
                }
                return;

            case 4: // visualizar todos 
                todosUsuarios = usuariosDatabase.getAll();
                if (todosUsuarios.length > 0) {
                    String strUsuario = "";
                    for (Usuario u : todosUsuarios) {
                        strUsuario += u.toString() + "\n";
                    }
                    JOptionPane.showMessageDialog(null, strUsuario);
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum usuário cadastrado.");
                }
        }
    }

    public void perfilGerenciarFornecedor(int escolhaFornecedor) {
        if (escolhaFornecedor == 5 || escolhaFornecedor == -1) {
            controlForm = false;
            return;
        }
        switch (escolhaFornecedor) {

            case 0: //incluir

                fornecedor = new Fornecedor();
                String fornecedorId = JOptionPane.showInputDialog("Digite o ID da pessoa para criar fornecedor:");
                if (!ValidaInput.string(fornecedorId) || !ValidaInput.stringEhInt(fornecedorId)) { // Verifica se contem somente numero na string
                    return;
                }
                pessoa = pessoasDatabase.getById(Integer.parseInt(fornecedorId));
                if (pessoa == null) {
                    JOptionPane.showMessageDialog(null, "Pessoa com ID " + fornecedorId + " não encontrada.");
                    return;
                }
                if (fornecedoresDatabase.getById(Integer.parseInt(fornecedorId)) != null) {
                    JOptionPane.showMessageDialog(null, "Pessoa com ID " + fornecedorId + " já tem fornecedor vinculado.");
                    return;
                }

                fornecedor.setPessoa(pessoa);

                String razaoSocial = JOptionPane.showInputDialog("Digite a Razão Social ou Nome Fantasia do fornecedor:");
                if (!ValidaInput.string(razaoSocial)) {
                    return; // Volta ao menu se cancelar ou fechar
                }
                fornecedor.setRazaoSocial(razaoSocial);

                String cnpj = JOptionPane.showInputDialog("Digite o CNPJ/CPF do fornecedor (Somente números | 0 - se nao tem.):");
                if (!ValidaInput.string(cnpj) || !ValidaInput.stringEhInt(cnpj)) {
                    return; // Volta ao menu se cancelar ou fechar
                }
                fornecedor.setCpfCnpj(cnpj);

                String telefone = JOptionPane.showInputDialog("Digite o telefone empresarial do fornecedor:");
                if (!ValidaInput.string(telefone)) {
                    return; // Volta ao menu se cancelar ou fechar
                }
                fornecedor.setTelefone(telefone);

                // Criar usuário no banco de dados
                fornecedoresDatabase.create(fornecedor);

                JOptionPane.showMessageDialog(null, "Fornecedros incluído com sucesso!");
                return;

            case 1: //alterar

                String idAlterar = JOptionPane.showInputDialog("Digite o ID do fornecedor:");
                if (!ValidaInput.string(idAlterar) || !ValidaInput.stringEhInt(idAlterar)) { // Verifica se contem somente numero na string
                    return;
                }
                fornecedor = fornecedoresDatabase.getById(Integer.parseInt(idAlterar));
                if (fornecedor != null) {
                    String novaRazaoSocial = JOptionPane.showInputDialog("Digite a Razão Social ou Nome Fantasia do fornecedor:", fornecedor.getRazaoSocial());
                    if (!ValidaInput.string(novaRazaoSocial)) {
                        return; // Volta ao menu se cancelar ou fechar
                    }
                    fornecedor.setRazaoSocial(novaRazaoSocial);

                    String novoCpfCnpj = JOptionPane.showInputDialog("Digite o CNPJ/CPF do fornecedor (Somente números | 0 - se nao tem.):", fornecedor.getcpfCnpj());
                    if (!ValidaInput.string(novoCpfCnpj) || !ValidaInput.stringEhInt(novoCpfCnpj)) {
                        return; // Volta ao menu se cancelar ou fechar
                    }
                    fornecedor.setCpfCnpj(novoCpfCnpj);

                    String novoTelefone = JOptionPane.showInputDialog("Digite o telefone empresarial do fornecedor:", fornecedor.getTelefone());
                    if (!ValidaInput.string(novoTelefone)) {
                        return; // Volta ao menu se cancelar ou fechar
                    }
                    fornecedor.setTelefone(novoTelefone);

                    fornecedor.setDataModificacao();

                    //Atualizar registro no banco de dados
                    fornecedoresDatabase.update(fornecedor);
                    JOptionPane.showMessageDialog(null, "Fornecedor alterado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Fornecedor com ID " + idAlterar + " não encontrado.");
                }
                return;

            case 2: // remover
                String idRemover = JOptionPane.showInputDialog("Digite o ID do fornecedor:");
                if (!ValidaInput.string(idRemover) || !ValidaInput.stringEhInt(idRemover)) { // Verifica se contem somente numero na string
                    return;
                }
                fornecedor = fornecedoresDatabase.getById(Integer.parseInt(idRemover));
                todosPagamentos = pagamentosDatabase.getAll();
                for (Pagamento p : todosPagamentos) {
                    if (p.getFornecedor() == fornecedor) {
                        JOptionPane.showMessageDialog(null, "Existe pagamento para o fornecedor de ID " + idRemover + ", para deletar, remova o pagamento.");
                        return;
                    }
                }
                if (fornecedor != null) {
                    int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o fornecedor com ID " + idRemover + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (confirmacao == JOptionPane.YES_OPTION) {
                        fornecedoresDatabase.delete(Integer.parseInt(idRemover));
                        JOptionPane.showMessageDialog(null, "Fornecedor removido com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Remoção cancelada.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Fornecedor com ID " + idRemover + " não encontrada.");
                }
                return;

            case 3: // visualizar
                // Visualizar pessoa pelo ID
                String idVisualizar = JOptionPane.showInputDialog("Digite o ID do fornecedor que deseja visualizar:");
                if (!ValidaInput.string(idVisualizar) || !ValidaInput.stringEhInt(idVisualizar)) {
                    return;
                }
                fornecedor = fornecedoresDatabase.getById(Integer.parseInt(idVisualizar));
                if (fornecedor != null) {
                    JOptionPane.showMessageDialog(null, fornecedor.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário com ID " + idVisualizar + " não encontrado.");
                }
                return;

            case 4: // visualizar todos 
                todosFornecedores = fornecedoresDatabase.getAll();
                if (todosFornecedores.length > 0) {
                    String strFornecedores = "";
                    for (Fornecedor f : todosFornecedores) {
                        strFornecedores += f.toString() + "\n";
                    }
                    JOptionPane.showMessageDialog(null, strFornecedores);
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum fornecedor cadastrado.");
                }
        }
    }

    public void perfilGerenciarConvidado(int escolhaConvite) {
        if (escolhaConvite == 6 || escolhaConvite == -1) {
            controlForm = false;
            return;
        }

        switch (escolhaConvite) {
            case 0://incluir convite individual

                familia = new Familia();
                convidado = new Convidado();
                String convidadoId = JOptionPane.showInputDialog("Digite o ID da pessoa para criar convidado:");
                if (!ValidaInput.string(convidadoId) || !ValidaInput.stringEhInt(convidadoId)) { // Verifica se contem somente numero na string
                    return;
                }
                pessoa = pessoasDatabase.getById(Integer.parseInt(convidadoId));
                if (pessoa == null) {
                    JOptionPane.showMessageDialog(null, "Pessoa com ID " + convidadoId + " não encontrada.");
                    return;
                }
                if (convidadosDatabase.getById(Integer.parseInt(convidadoId)) != null) {
                    JOptionPane.showMessageDialog(null, "Pessoa com ID " + convidadoId + " já tem convite vinculado.");
                    return;
                }
                convidado.setPessoa(pessoa);
                familia.setNomeFamilia("individual");
                String acessoI = "ci" + familia.getDataCriacao();
                String acessoFormatadoI = "ci" + acessoI.substring(acessoI.length() - 6);
                familia.setAcesso(acessoFormatadoI); //definir como os convidados vao acessar 
                convidado.setFamilia(familia);
                String parentesco = JOptionPane.showInputDialog("Digite o parentesco do convidado (Pai, Mãe, Avô, etc...):");
                if (!ValidaInput.string(parentesco)) {
                    return; // Volta ao menu se cancelar ou fechar
                }
                convidado.setParentesco(parentesco);

                // Criar usuário no banco de dados
                convidadosDatabase.create(convidado);

                JOptionPane.showMessageDialog(null, "Convidado incluído com sucesso!");
                return;

            case 1: //incluir convite familia
                familia = new Familia();
                String nomeFamilia = JOptionPane.showInputDialog("Digite o nome da família:");
                if (!ValidaInput.string(nomeFamilia)) {
                    return; // Volta ao menu se cancelar ou fechar
                }
                familia.setNomeFamilia(nomeFamilia);

// Variável para controlar se o primeiro membro já foi adicionado
                boolean primeiroMembro = true;

// Vincula várias pessoas à família até que o usuário cancele
                while (true) {
                    String pessoaId = JOptionPane.showInputDialog("Digite o ID da pessoa para vincular à família (ou clique Cancelar para terminar):");
                    if (!ValidaInput.string(pessoaId) || !ValidaInput.stringEhInt(pessoaId)) {
                        break; // Sai do loop se o usuário cancelar ou o ID não for válido
                    }

                    pessoa = pessoasDatabase.getById(Integer.parseInt(pessoaId));
                    if (pessoa == null) {
                        JOptionPane.showMessageDialog(null, "Pessoa com ID " + pessoaId + " não encontrada.");
                        continue; // Pede outro ID se a pessoa não for encontrada
                    }

                    if (convidadosDatabase.getById(Integer.parseInt(pessoaId)) != null) {
                        JOptionPane.showMessageDialog(null, "Pessoa com ID " + pessoaId + " já tem convite vinculado.");
                        continue; // Pede outro ID se a pessoa já for um convidado
                    }

                    // Cria um novo convidado e associa à família
                    convidado = new Convidado();
                    convidado.setPessoa(pessoa);
                    convidado.setFamilia(familia);
                    parentesco = "familia - " + familia.getNomeFamilia();
                    convidado.setParentesco(parentesco);
                    String acessoF = "cf" + familia.getDataCriacao();
                    String acessoFormatadoF = "cf" + acessoF.substring(acessoF.length() - 6);
                    familia.setAcesso(acessoFormatadoF); // Somente o primeiro membro terá o acesso
                    // Salva o convidado no banco de dados
                    convidadosDatabase.create(convidado);
                }

                JOptionPane.showMessageDialog(null, "Convidados familiares incluídos com sucesso!");
                return;

            case 2: //remover por ID
                String idRemover = JOptionPane.showInputDialog("Digite o ID do convidado:");
                if (!ValidaInput.string(idRemover) || !ValidaInput.stringEhInt(idRemover)) { // Verifica se contem somente numero na string
                    return;
                }
                convidado = convidadosDatabase.getById(Integer.parseInt(idRemover));
                if (convidado != null) {
                    int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o convidado com ID " + idRemover + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (confirmacao == JOptionPane.YES_OPTION) {
                        convidadosDatabase.delete(Integer.parseInt(idRemover));
                        JOptionPane.showMessageDialog(null, "Convidado removido com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Remoção cancelada.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Convidado com ID " + idRemover + " não encontrada.");
                }
                return;
            case 3: //remover por convite familia
                String familiaRemover = JOptionPane.showInputDialog(null, "Digite nome da familia que deseja remover os convites:");
                if (!ValidaInput.string(familiaRemover)) {
                    return;
                }
                todosConvidados = convidadosDatabase.getAll();
                Familia f;
                int qtdConvites = 0;
                int qtdRemovidos = 0;
                for (Convidado c : todosConvidados) {
                    f = c.getFamilia();
                    if (familiaRemover.equals(f.getNomeFamilia())) {
                        qtdConvites++;
                    }
                }
                if (qtdConvites == 0) {
                    JOptionPane.showMessageDialog(null, "Não encontrado convite para família " + familiaRemover);
                } else {
                    for (Convidado c : todosConvidados) {
                        f = c.getFamilia();
                        if (familiaRemover.equals(f.getNomeFamilia())) {
                            int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o convidado com ID " + c.getID() + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
                            if (confirmacao == JOptionPane.YES_OPTION) {
                                convidadosDatabase.delete(c.getID());
                                qtdRemovidos++;
                                JOptionPane.showMessageDialog(null, "Convidado removido com sucesso!");
                            } else {
                                JOptionPane.showMessageDialog(null, "Remoção cancelada.");
                            }
                        }
                    }
                    if (qtdRemovidos > 0) {
                        JOptionPane.showMessageDialog(null, "Foram removidos " + qtdRemovidos + " convites da família " + familiaRemover + " de um total de " + qtdConvites + " convites.");
                    }
                }
                return;
            case 4:
                // Visualizar convidado pelo ID
                String idVisualizar = JOptionPane.showInputDialog("Digite o ID do Convidado que deseja visualizar:");
                if (!ValidaInput.string(idVisualizar) || !ValidaInput.stringEhInt(idVisualizar)) {
                    return;
                }
                convidado = convidadosDatabase.getById(Integer.parseInt(idVisualizar));
                if (convidado != null) {
                    JOptionPane.showMessageDialog(null, convidado.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário com ID " + idVisualizar + " não encontrado.");
                }
            case 5:
                todosConvidados = convidadosDatabase.getAll();
                if (todosConvidados.length > 0) {
                    String strConvidados = "";
                    for (Convidado c : todosConvidados) {
                        strConvidados += c.toString() + "\n";
                    }
                    JOptionPane.showMessageDialog(null, strConvidados);
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum convidado cadastrado.");
                }
        }
    }

    public void perfilGerenciarPresente(int escolhaPresente) {
        if (escolhaPresente == 5 || escolhaPresente == -1) {
            controlForm = false;
            return;
        }
        switch (escolhaPresente) {

            case 0: // Incluir Presente
                presente = new Presente();
                int indicePresente = gerador.getIndicePresente();

                String nomePresente = JOptionPane.showInputDialog("Digite o nome do presente:", gerador.getPresente(indicePresente));
                if (!ValidaInput.string(nomePresente)) {
                    return; // Volta ao menu se cancelar ou fechar
                }
                presente.setNome(nomePresente);

                String valorEstimado = String.format("%.2f", gerador.getValorEstimado(indicePresente));
                String valorPresente = JOptionPane.showInputDialog("Digite o valor do presente:", valorEstimado);
                if (!ValidaInput.string(valorPresente) || !ValidaInput.stringEhDouble(valorPresente)) {
                    return; // Volta ao menu se cancelar ou fechar
                }
//                System.out.println(valorPresente);
                String valorPresenteFormat = Utils.formatDouble(valorPresente);
//                System.out.println(valorPresenteFormat);
//                if (!ValidaInput.string(valorPresenteFormat)) { // Verifica se contem somente numero na string com virgula
//                    return;
//                }
                presente.setValor(Double.parseDouble(valorPresenteFormat));

                String cotasPresente = JOptionPane.showInputDialog("Digite quantas cotas terá o presente:", gerador.getCota(indicePresente));
                if (!ValidaInput.string(cotasPresente) || !ValidaInput.stringEhInt(cotasPresente)) {
                    return; // Volta ao menu se cancelar ou fechar
                }
                presente.setCotas(Integer.parseInt(cotasPresente));
                double valorCota = presente.getValor() / (double) Integer.parseInt(cotasPresente);
                presente.setValorCota(valorCota);

                // Adiciona ao banco de dados (ou lista)
                presentesDatabase.create(presente);
                JOptionPane.showMessageDialog(null, "Presente incluído com sucesso!");

                return;

            case 1: //alterar presente
                String idAlterar = JOptionPane.showInputDialog("Digite o ID do presente que deseja alterar:");
                if (!ValidaInput.string(idAlterar) || !ValidaInput.stringEhInt(idAlterar)) {
                    return;
                }

                presente = presentesDatabase.getById(Integer.parseInt(idAlterar));
                if (presente != null) {
                    String novoNome = JOptionPane.showInputDialog("Digite o novo nome:", presente.getNome());
                    if (!ValidaInput.string(novoNome)) {
                        return;
                    }
                    presente.setNome(novoNome);

                    String valor = String.format("%.2f", presente.getValor());
                    String novoValor = JOptionPane.showInputDialog("Digite o novo valor:", valor);
                    if (!ValidaInput.string(novoValor) || !ValidaInput.stringEhDouble(novoValor)) {
                        return;
                    }

                    presente.setValor(Double.parseDouble(Utils.formatDouble(novoValor)));

                    String novasCotas = JOptionPane.showInputDialog("Digite as novas cotas:", presente.getCotas());
                    if (!ValidaInput.string(novasCotas) || !ValidaInput.stringEhInt(novasCotas)) {
                        return;
                    }

                    presente.setCotas(Integer.parseInt(novasCotas));
                    presente.setDataModificacao();
                    presentesDatabase.update(presente);
                    JOptionPane.showMessageDialog(null, "Presente alterado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Presente com ID " + idAlterar + " não encontrado.");
                }
                return;

            case 2: //remover presente
                String idRemover = JOptionPane.showInputDialog("Digite o ID do presente que deseja remover:");
                if (!ValidaInput.string(idRemover) || !ValidaInput.stringEhInt(idRemover)) {
                    return;
                }

                presente = presentesDatabase.getById(Integer.parseInt(idRemover));
                if (presente != null) {
                    int confirmacaoRemocao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o presente com ID " + idRemover + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (confirmacaoRemocao == JOptionPane.YES_OPTION) {
                        presentesDatabase.delete(Integer.parseInt(idRemover));
                        JOptionPane.showMessageDialog(null, "Presente removido com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Remoção cancelada.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Presente com ID " + idRemover + " não encontrado.");
                }
                return;

            case 3: // Visualizar Presentes Cadastrados
                todosPresentes = presentesDatabase.getAll();
                if (todosPresentes.length > 0) {
                    String strPresentes = "";
                    for (int i = 0; i < todosPresentes.length; i++) {
                        strPresentes += todosPresentes[i].toString() + "\n";
                    }
                    JOptionPane.showMessageDialog(null, strPresentes);
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum presente cadastrado.");
                }
                return;

            case 4: // Visualizar Presentes Recebidos
                todosPresentesRecebido = presentesRecebidosDatabase.getAll();
                if (todosPresentesRecebido.length > 0) {
                    String strPresentesRecebidos = "";
                    for (PresenteRecebido pr : todosPresentesRecebido) {
                        strPresentesRecebidos += pr.toString() + "\n";
                    }
                    JOptionPane.showMessageDialog(null, strPresentesRecebidos);
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum presente recebido.");
                }
                return;
        }
    }

    public void perfilGerenciarPagamento(int escolhaPagamento) {
        if (escolhaPagamento == 5 || escolhaPagamento == -1) {
            controlForm = false;
            return;
        }

        switch (escolhaPagamento) {
            case 0: //incluir pagamento

                pagamento = new Pagamento();
                fornecedor = new Fornecedor();
                //calendario = new Calendario();

                //define o fornecedor do pagamento
                String fornecedorId = JOptionPane.showInputDialog("Digite o ID do fornecedor:");
                if (!ValidaInput.string(fornecedorId) || !ValidaInput.stringEhInt(fornecedorId)) { // Verifica se contem somente numero na string
                    return;
                }
                fornecedor = fornecedoresDatabase.getById(Integer.parseInt(fornecedorId));
                if (fornecedor == null) {
                    JOptionPane.showMessageDialog(null, "Fornecedor com ID " + fornecedorId + " não encontrado.");
                    return;
                }
                pagamento.setFornecedor(fornecedor);

                //define o valor do pagamento
                String valorPagamento = JOptionPane.showInputDialog("Digite o valor do pagamento:");
                if (!ValidaInput.string(valorPagamento) || !ValidaInput.stringEhDouble(valorPagamento)) { // Verifica se contem somente numero na string com virgula
                    return;
                }
                String valorPagamentoFormat = Utils.formatDouble(valorPagamento);
                if (!ValidaInput.string(valorPagamentoFormat)) { // Verifica se contem somente numero na string com virgula
                    return;
                }
                pagamento.setValor(Double.parseDouble(valorPagamentoFormat));

                //define o tipo do pagamento
                String tipo = JOptionPane.showInputDialog("Digite o tipo do pagamento:\n1 - A VISTA\n2 - PARCELADO");
                if (!ValidaInput.string(tipo) || !ValidaInput.stringEhInt(tipo)) {
                    return;
                }
                if (!("1".equals(tipo) || "2".equals(tipo))) {
                    JOptionPane.showMessageDialog(null, "Digite o tipo do pagamento:\n1 - A VISTA\n2 - PARCELADO");
                    return;
                }
                pagamento.setTipo(Integer.parseInt(tipo));

                //define a descriçao do pagamento
                String descricao = JOptionPane.showInputDialog("Digite a descrição do pagamento (Obrigatório mais de 15 caracteres na descrição):");
                if (!ValidaInput.string(descricao)) {
                    return;
                }
                if (descricao.length() < 15) {
                    JOptionPane.showMessageDialog(null, "Obrigatório mais de 15 caracteres na descrição");
                    return;
                } else {
                    pagamento.setDescricao(descricao);
                }

                //define a data do pagamento
                String dataPagamento = JOptionPane.showInputDialog("Digite a data do pagamento (dd/mm/yyyy):");
                if (!ValidaInput.string(dataPagamento)) {
                    return;
                }
                try {
                    pagamento.setData(utils.formatDate(dataPagamento));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data inválida");
                    return;
                }

                String stringDescricao = "";
                String intervaloPagamento = "";
                if (pagamento.getTipo() == 1) {
                    pagamento.setParcela(0);
                } else {
                    //define a quantidade de parcelas
                    String qtdParc = JOptionPane.showInputDialog("Digite quantas parcelas:");
                    if (!ValidaInput.string(qtdParc) || !ValidaInput.stringEhInt(qtdParc)) {
                        return;
                    }
                    pagamento.setParcela(Integer.parseInt(qtdParc));
                    intervaloPagamento = JOptionPane.showInputDialog("Digite o intervalo de dias corridos (1 - 30) entre os pagamentos das parcelas:");
                    if (!ValidaInput.string(intervaloPagamento) || !ValidaInput.stringEhInt(intervaloPagamento)) {
                        return;
                    }

                }
                pagamentosDatabase.create(pagamento);

                if (pagamento.getTipo() == 1) {
                    calendario = new Calendario();
                    calendario.setData(pagamento.getData());
                    calendario.setTitulo("Pagamento Fornecedor " + fornecedor.getID() + " - " + fornecedor.getRazaoSocial());
                    stringDescricao = "Pagamento a vista - " + pagamento.getDescricao();
                    calendario.setDescricao(stringDescricao);
                    calendario.setPagamento(pagamento);
                    calendariosDatabase.create(calendario);
                } else {
                    for (int i = 0; i < pagamento.getParcela(); i++) {
                        calendario = new Calendario();
                        calendario.setData(pagamento.getData().plusDays(Long.parseLong(String.valueOf((Integer.parseInt(intervaloPagamento) * (i + 1))))));
                        calendario.setTitulo("Pagamento Fornecedor " + fornecedor.getID() + " - " + fornecedor.getRazaoSocial());
                        stringDescricao = "Pagamento a prazo - " + (i + 1) + " de " + pagamento.getParcela() + " | " + pagamento.getDescricao();
                        calendario.setDescricao(stringDescricao);
                        calendario.setPagamento(pagamento);
                        calendariosDatabase.create(calendario);
                    }
                }

                JOptionPane.showMessageDialog(null, "Pagamento incluído com sucesso!");
                return;

            case 1://alterar pagamento  

                String idAlterar = JOptionPane.showInputDialog("Digite o ID do pagamento para alterar");
                if (!ValidaInput.string(idAlterar) || !ValidaInput.stringEhInt(idAlterar)) {
                    return;
                }
                pagamento = pagamentosDatabase.getById(Integer.parseInt(idAlterar));
                if (pagamento == null) {
                    JOptionPane.showMessageDialog(null, "Pagamento com ID " + idAlterar + " não encontrado");
                    return;
                }
                fornecedor = pagamento.getFornecedor();
                String novoFornecedorId = JOptionPane.showInputDialog("Digite o novo ID do fornecedor:", fornecedor.getID());
                if (!ValidaInput.string(novoFornecedorId) || !ValidaInput.stringEhInt(novoFornecedorId)) { // Verifica se contem somente numero na string
                    return;
                }
                fornecedor = fornecedoresDatabase.getById(Integer.parseInt(novoFornecedorId));
                if (fornecedor == null) {
                    JOptionPane.showMessageDialog(null, "Fornecedor com ID " + novoFornecedorId + " não encontrado.");
                    return;
                }
                pagamento.setFornecedor(fornecedor);
                String novoValorPagamento = JOptionPane.showInputDialog("Digite o novo valor do pagamento:", pagamento.getValor());
                if (!ValidaInput.string(novoValorPagamento) || !ValidaInput.stringEhDouble(novoValorPagamento)) { // Verifica se contem somente numero na string com virgula
                    return;
                }
                String novoValorPagamentoFormat = Utils.formatDouble(novoValorPagamento);
                if (!ValidaInput.string(novoValorPagamentoFormat)) { // Verifica se contem somente numero na string com virgula
                    return;
                }
                pagamento.setValor(Double.parseDouble(novoValorPagamentoFormat));

                String novoTipo = JOptionPane.showInputDialog("Digite o novo tipo do pagamento:\n1 - A VISTA\n2 - PARCELADO", pagamento.getTipo());
                if (!ValidaInput.string(novoTipo) || !ValidaInput.stringEhInt(novoTipo)) {
                    return;
                }
                if (!("1".equals(novoTipo) || "2".equals(novoTipo))) {
                    JOptionPane.showMessageDialog(null, "Digite o tipo do pagamento:\n1 - A VISTA\n2 - PARCELADO");
                    return;
                }
                pagamento.setTipo(Integer.parseInt(novoTipo));

                String novaDescricao = JOptionPane.showInputDialog("Digite a descrição do pagamento (Obrigatório mais de 15 caracteres na descrição):", pagamento.getDescricao());
                if (!ValidaInput.string(novaDescricao)) {
                    return;
                }
                if (novaDescricao.length() < 15) {
                    JOptionPane.showMessageDialog(null, "Obrigatório mais de 15 caracteres na descrição");
                    return;
                } else {
                    pagamento.setDescricao(novaDescricao);
                }

                //define a data do pagamento
                String novaDataPagamento = JOptionPane.showInputDialog("Digite a data do pagamento (dd/mm/yyyy):", pagamento.getData());
                if (!ValidaInput.string(novaDataPagamento)) {
                    return;
                }
                try {
                    pagamento.setData(utils.formatDate(novaDataPagamento));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data inválida");
                    return;
                }

                String novaStringDescricao = "";
                String novoIntervaloPagamento = "";
                if (pagamento.getTipo() == 1) {
                    pagamento.setParcela(0);
                } else {
                    //define a quantidade de parcelas
                    String novoQtdParc = JOptionPane.showInputDialog("Digite quantas parcelas:");
                    if (!ValidaInput.string(novoQtdParc) || !ValidaInput.stringEhInt(novoQtdParc)) {
                        return;
                    }
                    pagamento.setParcela(Integer.parseInt(novoQtdParc));
                    novoIntervaloPagamento = JOptionPane.showInputDialog("Digite o intervalo de dias corridos (1 - 30) entre os pagamentos das parcelas:");
                    if (!ValidaInput.string(novoIntervaloPagamento) || !ValidaInput.stringEhInt(novoIntervaloPagamento)) {
                        return;
                    }

                }
                todosCalendarios = calendariosDatabase.getAll();
                for (Calendario c : todosCalendarios) {
                    Pagamento calendarioPagamento = c.getPagamento();
                    if (calendarioPagamento.getID() == Integer.parseInt(idAlterar)) {
                        calendariosDatabase.delete(c.getID());
                    }
                }
                pagamento.setDataModificacao();
                pagamentosDatabase.update(pagamento);
                if (pagamento.getTipo() == 1) {
                    calendario = new Calendario();
                    calendario.setData(pagamento.getData());
                    calendario.setTitulo("Pagamento Fornecedor " + fornecedor.getID() + " - " + fornecedor.getRazaoSocial());
                    novaStringDescricao = "Pagamento a vista - " + pagamento.getDescricao();
                    calendario.setDescricao(novaStringDescricao);
                    calendario.setPagamento(pagamento);
                    calendariosDatabase.create(calendario);
                } else {
                    for (int i = 0; i < pagamento.getParcela(); i++) {
                        calendario = new Calendario();
                        calendario.setData(pagamento.getData().plusDays(Long.parseLong(String.valueOf((Integer.parseInt(novoIntervaloPagamento) * (i + 1))))));
                        calendario.setTitulo("Pagamento Fornecedor " + fornecedor.getID() + " - " + fornecedor.getRazaoSocial());
                        novaStringDescricao = "Pagamento a prazo - " + (i + 1) + " de " + pagamento.getParcela() + " | " + pagamento.getDescricao();
                        calendario.setDescricao(novaStringDescricao);
                        calendario.setPagamento(pagamento);
                        calendariosDatabase.create(calendario);
                    }
                }
                JOptionPane.showMessageDialog(null, "Pagamento atualizado com sucesso!");
                return;

            case 2://remover pagamento ID
                String idRemover = JOptionPane.showInputDialog("Digite o ID do pagmento:");
                if (!ValidaInput.string(idRemover) || !ValidaInput.stringEhInt(idRemover)) { // Verifica se contem somente numero na string
                    return;
                }
                pagamento = pagamentosDatabase.getById(Integer.parseInt(idRemover));
                todosCalendarios = calendariosDatabase.getAll();
                if (pagamento != null) {
                    int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o pagamento com ID " + idRemover + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (confirmacao == JOptionPane.YES_OPTION) {
                        for (Calendario c : todosCalendarios) {
                            if (c.getPagamento() == pagamento) {
                                calendariosDatabase.delete(c.getID());
                            }
                        }
                        pagamentosDatabase.delete(Integer.parseInt(idRemover));
                        JOptionPane.showMessageDialog(null, "Pagamento removido com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Remoção cancelada.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Pagamento com ID " + idRemover + " não encontrada.");
                }
                return;
            case 3:
                // Visualizar pagamento pelo ID
                String idVisualizar = JOptionPane.showInputDialog("Digite o ID do Pagamento que deseja visualizar:");
                if (!ValidaInput.string(idVisualizar) || !ValidaInput.stringEhInt(idVisualizar)) {
                    return;
                }
                pagamento = pagamentosDatabase.getById(Integer.parseInt(idVisualizar));
                if (pagamento != null) {
                    JOptionPane.showMessageDialog(null, pagamento.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Pagamento com ID " + idVisualizar + " não encontrado.");
                }
                return;
            case 4:
                todosPagamentos = pagamentosDatabase.getAll();
                if (todosPagamentos.length > 0) {
                    String strPagamentos = "";
                    for (Pagamento p : todosPagamentos) {
                        strPagamentos += p.toString() + "\n";
                    }
                    JOptionPane.showMessageDialog(null, strPagamentos);
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum convidado cadastrado.");
                }
        }

    }

    public void perfilGerenciarCalendario(int escolhaCalendario) {
        if (escolhaCalendario == 5 || escolhaCalendario == -1) {
            controlForm = false;
            return;
        }
        switch (escolhaCalendario) {
            case 0://adicionar evento
                calendario = new Calendario();
                String dataEvento = JOptionPane.showInputDialog("Digite a data do evento (dd/mm/yyyy):");
                if (!ValidaInput.string(dataEvento)) {
                    return;
                }
                try {
                    calendario.setData(utils.formatDate(dataEvento));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data inválida");
                    return;
                }
                //calendario.setDataEventoFormat(utils.formatDateToString(calendario.getData())); //modifica a data para string de format pattern
                String tituloEvento = JOptionPane.showInputDialog("Digite o titulo do evento:");
                if (!ValidaInput.string(tituloEvento)) {
                    return;
                }
                calendario.setTitulo(tituloEvento);
                String descricaoEvento = JOptionPane.showInputDialog(null, "Digite a descrição do evento (até 4000 caracteres):");
                // Limitar a descrição a 4000 caracteres
                if (!ValidaInput.string(tituloEvento)) {
                    return;
                }
                if (descricaoEvento.length() > 4000) {
                    descricaoEvento = descricaoEvento.substring(0, 4000); // Trunca o texto para 4000 caracteres
                }
                calendario.setDescricao(descricaoEvento);
                calendariosDatabase.create(calendario);

                JOptionPane.showMessageDialog(null, "Evento adicionado com sucesso!\n");
                return;

            case 1://alterar evento
                String idAlterar = JOptionPane.showInputDialog("Digite o ID da data do evento:");
                if (!ValidaInput.string(idAlterar) || !ValidaInput.stringEhInt(idAlterar)) {
                    return;
                }
                calendario = calendariosDatabase.getById(Integer.parseInt(idAlterar));
                String novoDataEvento = JOptionPane.showInputDialog("Digite a data do evento (dd/mm/yyyy):", calendario.getData());
                if (!ValidaInput.string(novoDataEvento)) {
                    return;
                }
                try {
                    calendario.setData(utils.formatDate(novoDataEvento));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data inválida");
                    return;
                }
                //calendario.setDataEventoFormat(utils.formatDateToString(calendario.getData())); //modifica a data para string de format pattern
                String novoTituloEvento = JOptionPane.showInputDialog("Digite o titulo do evento:", calendario.getTitulo());
                if (!ValidaInput.string(novoTituloEvento)) {
                    return;
                }
                calendario.setTitulo(novoTituloEvento);
                String novoDescricaoEvento = JOptionPane.showInputDialog(null, "Digite a descrição do evento (até 4000 caracteres):", calendario.getDescricao());
                // Limitar a descrição a 4000 caracteres
                if (!ValidaInput.string(novoDescricaoEvento)) {
                    return;
                }
                if (novoDescricaoEvento.length() > 4000) {
                    novoDescricaoEvento = novoDescricaoEvento.substring(0, 4000); // Trunca o texto para 4000 caracteres
                }
                calendario.setDescricao(novoDescricaoEvento);
                calendario.setDataModificacao();
                calendariosDatabase.update(calendario);

                JOptionPane.showMessageDialog(null, "Evento Alterado com sucesso!\n");
                return;

            case 2://remover evento
                String idRemover = JOptionPane.showInputDialog("Digite o ID do evento que deseja excluir:");
                if (!ValidaInput.string(idRemover) || !ValidaInput.stringEhInt(idRemover)) {
                    return;
                }
                calendario = calendariosDatabase.getById(Integer.parseInt(idRemover));
                if (calendario != null) {
                    int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o evento com ID " + idRemover + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (confirmacao == JOptionPane.YES_OPTION) {
                        calendariosDatabase.delete(Integer.parseInt(idRemover));
                        JOptionPane.showMessageDialog(null, "Evento removido com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Remoção cancelada.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Evento com ID " + idRemover + " não encontrado.");
                }
                return;
            case 3: //visualizar compromissos hoje
                todosCalendarios = calendariosDatabase.getAll();
                LocalDate dH;
                //calendario = new Calendario();
                String dataHoje = JOptionPane.showInputDialog("Digite a data do evento (dd/mm/yyyy):");
                if (!ValidaInput.string(dataHoje)) {
                    return;
                }
                try {
                    dH = utils.formatDate(dataHoje);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data inválida");
                    return;
                }
                if (todosCalendarios.length > 0) {
                    String strCalendarios = "";
                    for (Calendario c : todosCalendarios) {
                        if (dH.isEqual(c.getData())) {
                            strCalendarios += c.toString() + "\n";
                        }
                    }
                    JOptionPane.showMessageDialog(null, strCalendarios);
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum evento para hoje.");
                }
                return;
            case 4://visualizar calendario
                todosCalendarios = calendariosDatabase.getAll();
                if (todosCalendarios.length > 0) {
                    String strCalendarios = "";
                    for (Calendario c : todosCalendarios) {
                        strCalendarios += c.toString() + "\n";
                    }
                    JOptionPane.showMessageDialog(null, strCalendarios);
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum evento cadastrado.");
                }
        }
    }
//FIM MENU ADMINISTRADOR

//INICIO MENU CONVIDADO
    public void perfilConvidado(int escolhaConvidado) {
        if (escolhaConvidado == 3 || escolhaConvidado == -1) {
            controlForm = false;
            return;
        }
        while (controlForm) {
            switch (escolhaConvidado) {
                case 0: // Dar presente

                    todosPresentes = presentesDatabase.getAll();
                    String strPresentes = "";

                    if (todosPresentes.length > 0) {
                        for (int i = 0; i < todosPresentes.length; i++) {
                            strPresentes += todosPresentes[i].toStringConvidado() + "\n";
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum presente cadastrado.");
                    }

                    String escolha = JOptionPane.showInputDialog(null, strPresentes + "\nEscolha o número do presente que deseja dar:", "Presentes Disponíveis", JOptionPane.QUESTION_MESSAGE);

                    if (!ValidaInput.string(escolha) || !ValidaInput.stringEhInt(escolha)) {
                        return; // Volta ao menu se cancelar ou fechar
                    }

                    int numeroPresente = Integer.parseInt(escolha) - 1;
                    if (numeroPresente < 0 || numeroPresente >= todosPresentes.length) {
                        JOptionPane.showMessageDialog(null, "Presente inválido! Escolha novamente.");
                        return;
                    }
                    presente = presentesDatabase.getById(numeroPresente + 1);

                    // Solicita quantas cotas o usuário deseja dar
                    String cotas = JOptionPane.showInputDialog(null, "Digite o número de cotas que deseja dar:");
                    if (!ValidaInput.string(cotas) || !ValidaInput.stringEhInt(cotas)) {
                        return; // Volta ao menu se cancelar ou fechar
                    }

                    int cotasInt = Integer.parseInt(cotas);
                    if (cotasInt < 0 || cotasInt > presente.getCotas()) {
                        JOptionPane.showMessageDialog(null, "Cotas inválido! Escolha novamente.");
                        return;
                    }
                    int cotasDisponiveis = presente.getCotas();

                    // Confirmação
                    int confirmacao = JOptionPane.showConfirmDialog(null,
                            "Você escolheu dar " + cotasInt + " cotas do presente " + presente.getNome()
                            + "\nCotas disponíveis: " + cotasDisponiveis + "\nConfirmar?", "Confirmar", JOptionPane.YES_NO_OPTION);

                    if (confirmacao == JOptionPane.YES_OPTION) {
                        presente.setCotas(cotasDisponiveis - cotasInt);
                        presentesDatabase.update(presente);
                        presenteRecebido = new PresenteRecebido();
                        presenteRecebido.setPresente(presente);
                        presenteRecebido.setQtdCotas(cotasInt);
                        for (Usuario u : todosUsuarios) {
                            if (u.isLogado()) {
                                presenteRecebido.setPessoa(u.getPessoa());
                            }
                        }
                        presentesRecebidosDatabase.create(presenteRecebido);
                        JOptionPane.showMessageDialog(null, "Presente dado com sucesso!\nCotas restantes atualizadas.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Presente Cancelado!");
                    }
                    return;
                case 1://deixar recado
                    if (deixarRecado()) {
                        JOptionPane.showMessageDialog(null, "Recado deixado com sucesso!\n");
                    } else {
                        JOptionPane.showMessageDialog(null, "Falha ao deixar Recado!\n");
                    }
                    return;

                case 2: //confirmar presença
                    // Método de Confirmar Presença
                    String codigoAcesso = JOptionPane.showInputDialog("Digite o Codigo de acesso do Convite:");
                    if (!ValidaInput.string(codigoAcesso)) {
                        return;
                    }
                    todosConvidados = convidadosDatabase.getAll();
                    todosUsuarios = usuariosDatabase.getAll();
                    for (Usuario u : todosUsuarios) {
                        if (u.isLogado()) {
                            usuario = u;
                        }
                    }
                    for (Convidado c : todosConvidados) {
                        Familia f = c.getFamilia();
                        if (codigoAcesso.equals(f.getAcesso())) {
                            // Se for um convite individual
                            if ("ci".equals(f.getAcesso().substring(0, 2))) {
                                if (c.isConfirmado()) {
                                    JOptionPane.showMessageDialog(null, "O convidado já confirmou a presença!");
                                    return;
                                }

                                pessoa = c.getPessoa();
                                String detalhesPessoa = pessoa.toString();
                                confirmacao = JOptionPane.showConfirmDialog(null, detalhesPessoa, "Confirmação de Presença", JOptionPane.YES_NO_OPTION);

                                if (confirmacao == JOptionPane.YES_OPTION) {
                                    c.setConfirmado(true); // Atualiza a confirmação
                                    c.setDataModificacao(); // Atualiza a data de modificação
                                    convidadosDatabase.update(c); // Salva no banco de dados
                                    JOptionPane.showMessageDialog(null, "Presença confirmada com sucesso para o convidado com ID: " + c.getID());
                                } else {
                                    JOptionPane.showMessageDialog(null, "Confirmação de presença cancelada.");
                                }
                                return;

                                // Se for um convite de família
                            } else if ("cf".equals(f.getAcesso().substring(0, 2))) {
                                // Mostrar todos os membros da família
                                String membrosFamilia = "";
                                for (Convidado membro : todosConvidados) {
                                    if (membro.getFamilia().equals(f)) {
                                        membrosFamilia += membro.getPessoa().toString() + "\n";
                                    }
                                }

                                String pessoaIdConfirmar = JOptionPane.showInputDialog(null, "Membros da família:\n" + membrosFamilia + "\nDigite o ID da pessoa que deseja confirmar a presença:");
                                if (!ValidaInput.stringEhInt(pessoaIdConfirmar)) {
                                    JOptionPane.showMessageDialog(null, "ID inválido. Tente novamente.");
                                    return;
                                }

                                int idConfirmar = Integer.parseInt(pessoaIdConfirmar);
                                Convidado convidadoConfirmar = null;

                                // Busca o convidado pelo ID
                                for (Convidado membro : todosConvidados) {
                                    if (membro.getPessoa().getID() == idConfirmar && membro.getFamilia().equals(f)) {
                                        convidadoConfirmar = membro;
                                        break;
                                    }
                                }

                                if (convidadoConfirmar == null) {
                                    JOptionPane.showMessageDialog(null, "Membro da família não encontrado.");
                                    return;
                                }

                                // Verifica se o convidado já confirmou
                                if (convidadoConfirmar.isConfirmado()) {
                                    JOptionPane.showMessageDialog(null, "Este membro da família já confirmou a presença.");
                                    return;
                                }

                                String detalhesPessoa = convidadoConfirmar.getPessoa().toString();
                                confirmacao = JOptionPane.showConfirmDialog(null, detalhesPessoa, "Confirmação de Presença", JOptionPane.YES_NO_OPTION);
                                if (confirmacao == JOptionPane.YES_OPTION) {
                                    convidadoConfirmar.setConfirmado(true); // Atualiza a confirmação
                                    convidadoConfirmar.setDataModificacao(); // Atualiza a data de modificação
                                    convidadosDatabase.update(convidadoConfirmar); // Salva no banco de dados
                                    JOptionPane.showMessageDialog(null, "Presença confirmada com sucesso para o membro da família com ID: " + convidadoConfirmar.getID());
                                    return;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Confirmação de presença cancelada.");
                                    return;
                                }
                            }
                        }
                    }
                    return;
            }
        }
    }

//FIM MENU CONVIDADO
//INICIO MENU NAO LOGADO
    public void perfilNaoLogado(int escolhaNaoLogado) {
        if (escolhaNaoLogado == 2 || escolhaNaoLogado == -1) {
            controlForm = false;
            return;
        }
        while (controlForm) {
            switch (escolhaNaoLogado) {
                case 0:

                    todosPresentes = presentesDatabase.getAll();
                    String strPresentes = "";

                    if (todosPresentes.length > 0) {
                        for (int i = 0; i < todosPresentes.length; i++) {
                            strPresentes += todosPresentes[i].toStringConvidado() + "\n";
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum presente cadastrado.");
                    }

                    String escolha = JOptionPane.showInputDialog(null, strPresentes + "\nEscolha o número do presente que deseja dar:", "Presentes Disponíveis", JOptionPane.QUESTION_MESSAGE);

                    if (!ValidaInput.string(escolha) || !ValidaInput.stringEhInt(escolha)) {
                        return; // Volta ao menu se cancelar ou fechar
                    }

                    int numeroPresente = Integer.parseInt(escolha) - 1;
                    if (numeroPresente < 0 || numeroPresente >= todosPresentes.length) {
                        JOptionPane.showMessageDialog(null, "Presente inválido! Escolha novamente.");
                        return;
                    }
                    presente = presentesDatabase.getById(numeroPresente + 1);

                    // Solicita quantas cotas o usuário deseja dar
                    String cotas = JOptionPane.showInputDialog(null, "Digite o número de cotas que deseja dar:");
                    if (!ValidaInput.string(cotas) || !ValidaInput.stringEhInt(cotas)) {
                        return; // Volta ao menu se cancelar ou fechar
                    }

                    int cotasInt = Integer.parseInt(cotas);
                    if (cotasInt < 0 || cotasInt > presente.getCotas()) {
                        JOptionPane.showMessageDialog(null, "Cotas inválido! Escolha novamente.");
                        return;
                    }
                    int cotasDisponiveis = presente.getCotas();

                    // Confirmação
                    int confirmacao = JOptionPane.showConfirmDialog(null,
                            "Você escolheu dar " + cotasInt + " cotas do presente " + presente.getNome()
                            + "\nCotas disponíveis: " + cotasDisponiveis + "\nConfirmar?", "Confirmar", JOptionPane.YES_NO_OPTION);

                    if (confirmacao == JOptionPane.YES_OPTION) {
                        presente.setCotas(cotasDisponiveis - cotasInt);
                        presentesDatabase.update(presente);
                        presenteRecebido = new PresenteRecebido();
                        presenteRecebido.setPresente(presente);
                        presenteRecebido.setQtdCotas(cotasInt);
                        pessoa = new Pessoa();
                        String nome = JOptionPane.showInputDialog("Digite seu nome:", gerador.gerarNome());
                        if (!ValidaInput.string(nome)) {
                            return; // Volta ao menu se cancelar ou fechar
                        }
                        pessoa.setNome(nome);
                        presenteRecebido.setPessoa(pessoa);
                        presentesRecebidosDatabase.create(presenteRecebido);
                        JOptionPane.showMessageDialog(null, "Presente dado com sucesso!\nCotas restantes atualizadas.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Presente Cancelado!");
                    }

                    return;

                case 1:
                    muralRecado = new MuralRecado();
                    String nome = JOptionPane.showInputDialog("Digite seu nome:", gerador.gerarNome());
                    if (!ValidaInput.string(nome)) {
                        return; // Volta ao menu se cancelar ou fechar
                    }
                    String recado = JOptionPane.showInputDialog(null, "Digite seu recado (até 4000 caracteres):",
                            "Deixar Recado", JOptionPane.PLAIN_MESSAGE);
                    if (!ValidaInput.string(recado)) {
                        return;
                    }
                    if (recado.length() > 4000) {
                        recado = recado.substring(0, 4000); // Trunca o recado para 4000 caracteres
                    }
                    muralRecado.setNome(nome);
                    muralRecado.setRecado(recado);
                    confirmacao = JOptionPane.showConfirmDialog(null, "Confirma deixar recado?", "Confirmar", JOptionPane.YES_NO_OPTION);

                    if (confirmacao == JOptionPane.YES_OPTION) {
                        muralRecadoDatabase.create(muralRecado);
                        JOptionPane.showMessageDialog(null, "Recado deixado com sucesso!\n");
                    } else {
                        JOptionPane.showMessageDialog(null, "Falha ao deixar Recado!\n");
                    }
                    return;

            }

        }
    }
//FIM MENU NAO LOGADO

    private boolean deixarRecado() throws HeadlessException {
        muralRecado = new MuralRecado();
        String recado = JOptionPane.showInputDialog(null, "Digite seu recado (até 4000 caracteres):",
                "Deixar Recado", JOptionPane.PLAIN_MESSAGE);
        if (!ValidaInput.string(recado)) {
            return false;
        }
        if (recado.length() > 4000) {
            recado = recado.substring(0, 4000); // Trunca o recado para 4000 caracteres
        }
        muralRecado.setRecado(recado);
        for (Usuario u : todosUsuarios) {
            if (u.isLogado()) {
                Pessoa p = u.getPessoa();
                muralRecado.setNome(p.getNome());
            }
        }
        muralRecadoDatabase.create(muralRecado);
        return true;
    }

    private boolean registraUsuario() {
        pessoa = new Pessoa();

        // Validação de nome
        String nome = JOptionPane.showInputDialog("Digite seu nome:", gerador.gerarNome());
        if (!ValidaInput.string(nome)) {
            return false; // Volta ao menu se cancelar ou fechar
        }
        pessoa.setNome(nome);

        // Validação de telefone
        String telefone = JOptionPane.showInputDialog("Digite seu telefone:", gerador.gerarTelefone());
        if (!ValidaInput.string(telefone)) {
            return false; // Volta ao menu se cancelar ou fechar
        }
        pessoa.setTelefone(telefone);

        // Validação de nascimento
        String nascimento = JOptionPane.showInputDialog("Digite o seu nascimento (dd/MM/yyyy):", gerador.gerarDataNascimento());
        if (!ValidaInput.string(nascimento)) {
            return false; // Volta ao menu se cancelar ou fechar
        }
        try {
            pessoa.setNascimento(utils.formatDate(nascimento));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data inválida");
            return false;
        }

        usuario = new Usuario();

        String login = JOptionPane.showInputDialog("Digite o login do usuário:");
        if (!ValidaInput.string(login)) {
            return false; // Volta ao menu se cancelar ou fechar
        }
        usuario.setLogin(login);

        String senha = JOptionPane.showInputDialog("Digite a senha do usuário:");
        if (!ValidaInput.string(senha)) {
            return false; // Volta ao menu se cancelar ou fechar
        }
        usuario.setSenha(senha);

        String tipo = JOptionPane.showInputDialog("Digite o tipo do usuário (0 - Administrador | 1 - Usuario):", "1");
        if (!ValidaInput.string(tipo) || !ValidaInput.stringEhInt(tipo) || (!"0".equals(tipo) && !"1".equals(tipo))) {
            return false; // Volta ao menu se cancelar ou fechar
        }
        if ("0".equals(tipo)) {
            todosUsuarios = usuariosDatabase.getAll();
            for (Usuario u : todosUsuarios) {
                if (u.getTipo() == 0) {
                    JOptionPane.showMessageDialog(null, "Já existe Administrador cadastrado", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            }
        }
        usuario.setTipo(Integer.parseInt(tipo));
        if (usuario.getTipo() == 0) {
            usuario.setAdmin(true);
        } else {
            usuario.setAdmin(false);
        }

        pessoasDatabase.create(pessoa);
        pessoa = pessoasDatabase.getById(pessoa.getID());
        usuario.setPessoa(pessoa);

        // Criar usuário no banco de dados
        usuariosDatabase.create(usuario);
        JOptionPane.showMessageDialog(null, "Usuário incluído com sucesso!");
        return false;
    }
}
