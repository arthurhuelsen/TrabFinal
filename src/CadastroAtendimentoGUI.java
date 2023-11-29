import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CadastroAtendimentoGUI extends JFrame {
    private JFrame frame;
    private JComboBox<Evento> comboEventos;
    private JTextField txtCodAtendimento;
    private JTextField txtDataInicio;
    private JTextField txtDuracao;
    private JTextArea textAreaMensagens;

    private List<Evento> eventosCadastrados;
    private List<Atendimento> atendimentos; // Lista de todos os atendimentos

    private GerenciadorDeAlocacoesGUI gerenciadorDeAlocacoes;

    public CadastroAtendimentoGUI(List<Evento> eventosCadastrados) {
        this.eventosCadastrados = eventosCadastrados;
        this.atendimentos = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Cadastro de Atendimento");
        frame.setBounds(100, 100, 750, 450);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Painel para seleção do evento
        JPanel panelEvento = new JPanel();
        panelEvento.setLayout(new FlowLayout(FlowLayout.LEFT));
        comboEventos = new JComboBox<>(eventosCadastrados.toArray(new Evento[0]));
        panelEvento.add(new JLabel("Selecione o Evento:"));
        panelEvento.add(comboEventos);
        frame.getContentPane().add(panelEvento);

        // Painel para entrada de dados do atendimento
        JPanel panelDados = new JPanel();
        panelDados.setLayout(new GridLayout(0, 2, 10, 10)); // Grid layout para organizar os labels e campos

        // Campos de texto e seus labels
        txtCodAtendimento = new JTextField(10);
        panelDados.add(new JLabel("Código do Atendimento:"));
        panelDados.add(txtCodAtendimento);

        txtDataInicio = new JTextField(10);
        panelDados.add(new JLabel("Data de Início:"));
        panelDados.add(txtDataInicio);

        txtDuracao = new JTextField(10);
        panelDados.add(new JLabel("Duração:"));
        panelDados.add(txtDuracao);

        frame.getContentPane().add(panelDados);

        // Painel para os botões
        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton btnCadastrar = new JButton("Cadastrar Atendimento");
        btnCadastrar.addActionListener(e -> cadastrarAtendimento());
        panelBotoes.add(btnCadastrar);

        JButton btnLimpar = new JButton("Limpar Campos");
        btnLimpar.addActionListener(e -> limparCampos());
        panelBotoes.add(btnLimpar);

        frame.getContentPane().add(panelBotoes);

        // Área de texto para exibir mensagens ao usuário
        textAreaMensagens = new JTextArea(5, 20);
        textAreaMensagens.setEditable(false);
        JScrollPane scrollPaneMensagens = new JScrollPane(textAreaMensagens);
        frame.getContentPane().add(scrollPaneMensagens);

    }

    public void showGUI() {
        frame.setVisible(true);
    }

    private void cadastrarAtendimento() {
        Evento eventoSelecionado = (Evento) comboEventos.getSelectedItem();
        int codAtendimento = Integer.parseInt(txtCodAtendimento.getText());
        String dataInicio = txtDataInicio.getText();
        int duracao = Integer.parseInt(txtDuracao.getText());

        if (eventoSelecionado != null && !existeAtendimentoParaEvento(eventoSelecionado)) {
            String codigoEvento = eventoSelecionado.getCodigo(); // Obter o código do evento
            Atendimento novoAtendimento = new Atendimento(codAtendimento, dataInicio, duracao,
                    "Pendente", codigoEvento);
            atendimentos.add(novoAtendimento);

            textAreaMensagens.setText("Atendimento cadastrado e adicionado à fila de pendências.");
        } else {
            textAreaMensagens.setText("Erro: O evento selecionado já possui um atendimento ou não é válido.");
        }
    }

    private boolean existeAtendimentoParaEvento(Evento evento) {
        String codigoEvento = evento.getCodigo();
        for (Atendimento atendimento : atendimentos) {
            if (atendimento.getCodigoEvento().equals(codigoEvento)) {
                return true; // Retorna verdadeiro se encontrar um atendimento associado ao evento
            }
        }
        return false; // Retorna falso se nenhum atendimento for encontrado para o evento
    }

    private void limparCampos() {
        // Redefine os campos de texto
        txtCodAtendimento.setText("");
        txtDataInicio.setText("");
        txtDuracao.setText("");
        textAreaMensagens.setText("");

    }

    public List<Atendimento> getAtendimentosCadastrados() {
        return atendimentos; // pendentes
    }

    public void setAtendimentos(List<Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
    }
}