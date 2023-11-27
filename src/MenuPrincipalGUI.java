import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MenuPrincipalGUI {
    private JFrame frame;
    private CadastroEventoGUI cadastroEventoGUI;

    public MenuPrincipalGUI() {
        cadastroEventoGUI = new CadastroEventoGUI();
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

    private void openCadastroEquipe() {
        CadastroEquipeGUI cadastroEquipeGUI = new CadastroEquipeGUI();
        cadastroEquipeGUI.setVisible(true);
    }

    private void openCadastroEvento() {
        this.cadastroEventoGUI.setVisible(true);
    }

    private void openCadastroEquipamento() {
        CadastroEquipamentoGUI cadastroEquipamentoGUI = new CadastroEquipamentoGUI();
        cadastroEquipamentoGUI.setVisible(true);
    }

    private void openCadastroAtendimento() {
        List<Evento> eventosCadastrados = cadastroEventoGUI.obterTodosOsEventos();
        CadastroAtendimentoGUI cadastroAtendimentoGUI = new CadastroAtendimentoGUI(eventosCadastrados);
        cadastroAtendimentoGUI.setVisible(true);
    }

    private void showRelatorioGeral() {
        // Implementar exibição do Relatório Geral
    }

    private void vincularEquipamentoEquipe() {
        // Implementar lógica para Vincular Equipamento à Equipe
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
