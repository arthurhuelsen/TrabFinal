import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AlterarSituacaoAtendimentoGUI extends JFrame {
    private JTextField txtCodigoAtendimento;
    private JTextArea textAreaDetalhesAtendimento;
    private JComboBox<String> comboSituacao;
    private JButton btnBuscarAtendimento;
    private JButton btnAlterarSituacao;
    private List<Atendimento> atendimentos;

    public AlterarSituacaoAtendimentoGUI(List<Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
        initialize();
    }

    private void initialize() {
        setTitle("Alterar Situação de Atendimento");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Criação dos componentes
        JPanel panelCodigo = new JPanel(new FlowLayout());
        txtCodigoAtendimento = new JTextField(10);
        btnBuscarAtendimento = new JButton("Buscar Atendimento");
        panelCodigo.add(new JLabel("Código do Atendimento:"));
        panelCodigo.add(txtCodigoAtendimento);
        panelCodigo.add(btnBuscarAtendimento);

        textAreaDetalhesAtendimento = new JTextArea();
        textAreaDetalhesAtendimento.setEditable(false);

        JPanel panelSituacao = new JPanel(new FlowLayout());
        comboSituacao = new JComboBox<>(new String[] { "Pendente", "Em Andamento", "Finalizado", "Cancelado" });
        btnAlterarSituacao = new JButton("Alterar Situação");
        panelSituacao.add(new JLabel("Nova Situação:"));
        panelSituacao.add(comboSituacao);
        panelSituacao.add(btnAlterarSituacao);

        add(panelCodigo, BorderLayout.NORTH);
        add(new JScrollPane(textAreaDetalhesAtendimento), BorderLayout.CENTER);
        add(panelSituacao, BorderLayout.SOUTH);

        // Configuração dos eventos (listeners)
        btnBuscarAtendimento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarAtendimento();
            }
        });

        btnAlterarSituacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarSituacaoAtendimento();
            }
        });

        setVisible(true);
    }

    private void buscarAtendimento() {
        String codigoStr = txtCodigoAtendimento.getText();
        try {
            int codigo = Integer.parseInt(codigoStr);
            Atendimento atendimentoEncontrado = null;

            // Suponha que existe uma lista de atendimentos chamada 'atendimentos'
            for (Atendimento atendimento : atendimentos) {
                if (atendimento.getCod() == codigo) {
                    atendimentoEncontrado = atendimento;
                    break;
                }
            }

            if (atendimentoEncontrado != null) {
                textAreaDetalhesAtendimento.setText(atendimentoEncontrado.toString());
                comboSituacao.setSelectedItem(atendimentoEncontrado.getStatus());
            } else {
                textAreaDetalhesAtendimento.setText("Atendimento não encontrado.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um código válido.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void alterarSituacaoAtendimento() {
        String codAtendimento = txtCodigoAtendimento.getText(); // Corrigido para corresponder ao campo declarado
        Atendimento atendimentoEncontrado = null;

        // Buscar o atendimento
        for (Atendimento atendimento : atendimentos) {
            if (atendimento.getCod() == Integer.parseInt(codAtendimento)) {
                atendimentoEncontrado = atendimento;
                break;
            }
        }

        if (atendimentoEncontrado == null) {
            JOptionPane.showMessageDialog(this, "Atendimento não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (atendimentoEncontrado.getStatus().equals("FINALIZADO")) {
            JOptionPane.showMessageDialog(this, "O atendimento já está finalizado e não pode ser alterado.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String novaSituacao = (String) comboSituacao.getSelectedItem();
        atendimentoEncontrado.setStatus(novaSituacao);

        JOptionPane.showMessageDialog(this, "Situação do atendimento alterada com sucesso.", "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Outros métodos conforme necessário
}
