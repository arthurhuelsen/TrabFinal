import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MenuPrincipalGUI {
    private JFrame frame;
    private CadastroEventoGUI cadastroEventoGUI;
    private CadastroEquipamentoGUI cadastroEquipamentoGUI;
    private CadastroEquipeGUI cadastroEquipeGUI;
    private CadastroAtendimentoGUI cadastroAtendimentoGUI;
    private VincularEquipamentoEquipeGUI vincularEquipamentoEquipeGUI;

    public MenuPrincipalGUI() {

        initialize();

    }

    private void initialize() {
        frame = new JFrame("Sistema de Gerenciamento de Desastres");
        frame.setBounds(100, 100, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(0, 1));

        addButton("Cadastro de Equipe", e -> openCadastroEquipe());
        addButton("Cadastro de Evento", e -> openCadastroEvento());
        addButton("Cadastro de Equipamento", e -> openCadastroEquipamento());
        addButton("Cadastrar Novo Atendimento", e -> openCadastroAtendimento());
        addButton("Mostrar Relatório Geral", e -> showRelatorioGeral());
        addButton("Vincular Equipamento à Equipe", e -> vincularEquipamentoEquipe());
        addButton("Consultar Todos os Atendimentos", e -> consultarAtendimentos());
        addButton("Alterar a Situação de um Atendimento", e -> alterarSituacaoAtendimento());

        frame.setVisible(true);
    }

    private void addButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o botão
        button.setMaximumSize(new Dimension(300, 30)); // Configura o tamanho máximo do botão
        button.addActionListener(actionListener);
        frame.getContentPane().add(button);
        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento entre os botões
    }

    private void openCadastroEvento() {
        cadastroEventoGUI = new CadastroEventoGUI();
        cadastroEventoGUI.setVisible(true);
    }

    private void openCadastroEquipamento() {
        cadastroEquipamentoGUI = new CadastroEquipamentoGUI();
        cadastroEquipamentoGUI.setVisible(true);
    }

    private void openCadastroEquipe() {
        cadastroEquipeGUI = new CadastroEquipeGUI();
        cadastroEquipeGUI.setVisible(true);
    }

    private void openCadastroAtendimento() {
        cadastroAtendimentoGUI = new CadastroAtendimentoGUI(cadastroEventoGUI.obterTodosOsEventos());
        cadastroAtendimentoGUI.setVisible(true);
    }

    private void showRelatorioGeral() {
        List<Evento> eventos;
        if (cadastroEventoGUI != null) {
            eventos = cadastroEventoGUI.obterTodosOsEventos();
        } else {
            eventos = new ArrayList<>(); // Se cadastroEventoGUI é null, usa uma lista vazia
        }

        List<Equipamento> equipamentos;
        if (cadastroEquipamentoGUI != null) {
            equipamentos = cadastroEquipamentoGUI.obterTodosOsEquipamentos();
        } else {
            equipamentos = new ArrayList<>(); // Se cadastroEquipamentoGUI é null, usa uma lista vazia
        }

        List<Equipe> equipes;
        if (cadastroEquipeGUI != null) {
            equipes = cadastroEquipeGUI.getEquipesCadastradas(); // Substitua por seu método real
        } else {
            equipes = new ArrayList<>(); // Se cadastroEquipeGUI é null, usa uma lista vazia
        }

        List<Atendimento> atendimentos;
        // Supondo que você tenha uma variável ou método para obter atendimentos
        // Substitua 'suaVariavelOuMetodo' pelo nome real da sua variável ou método
        if (cadastroAtendimentoGUI != null) {
            atendimentos = cadastroAtendimentoGUI.getAtendimentosCadastrados();
        } else {
            atendimentos = new ArrayList<>(); // Se a fonte de atendimentos é null, usa uma lista vazia
        }

        RelatorioGeralGUI relatorioGeralGUI = new RelatorioGeralGUI(eventos, equipamentos, equipes, atendimentos);
        relatorioGeralGUI.setVisible(true);
    }

    private void vincularEquipamentoEquipe() {
        List<Equipe> equipes = cadastroEquipeGUI.getEquipesCadastradas();
        List<Equipamento> equipamentos = cadastroEquipamentoGUI.obterTodosOsEquipamentos();
        vincularEquipamentoEquipeGUI = new VincularEquipamentoEquipeGUI(equipamentos, equipes);
    }

    private void consultarAtendimentos() {
        // Implementar consulta de Todos os Atendimentos
    }

    private void alterarSituacaoAtendimento() {
        // Implementar alteração da Situação de um Atendimento
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new MenuPrincipalGUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
