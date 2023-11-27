import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CadastroEquipamentoGUI extends JFrame {
    private JFrame frame;
    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtCustoDia;
    private JTextField txtCapacidadeBarco; // Barco
    private JTextField txtCapacidadeCaminhao; // Caminhão Tanque
    private JComboBox<String> comboCombustivel; // Escavadeira
    private JTextField txtCargaEscavadeira; // Escavadeira
    private JTextArea textAreaMensagens;

    private List<Barco> barcosCadastrados;
    private List<CaminhaoTanque> caminhoesTanqueCadastrados;
    private List<Escavadeira> escavadeirasCadastradas;

    public CadastroEquipamentoGUI() {
        barcosCadastrados = new ArrayList<>();
        caminhoesTanqueCadastrados = new ArrayList<>();
        escavadeirasCadastradas = new ArrayList<>();

        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 750, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Painel para campos de entrada
        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new BoxLayout(panelCampos, BoxLayout.Y_AXIS));

        // Adicionando componentes de entrada para atributos comuns
        JPanel panelId = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtId = new JTextField(10);
        panelId.add(new JLabel("ID:"));
        panelId.add(txtId);
        panelCampos.add(panelId);

        JPanel panelNome = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtNome = new JTextField(10);
        panelNome.add(new JLabel("Nome:"));
        panelNome.add(txtNome);
        panelCampos.add(panelNome);

        JPanel panelCustoDia = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtCustoDia = new JTextField(10);
        panelCustoDia.add(new JLabel("Custo por Dia:"));
        panelCustoDia.add(txtCustoDia);
        panelCampos.add(panelCustoDia);

        // Adicionando componentes de entrada para atributos específicos
        JPanel panelCapacidadeBarco = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtCapacidadeBarco = new JTextField(10);
        panelCapacidadeBarco.add(new JLabel("Capacidade (Barco):"));
        panelCapacidadeBarco.add(txtCapacidadeBarco);
        panelCampos.add(panelCapacidadeBarco);

        JPanel panelCapacidadeCaminhao = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtCapacidadeCaminhao = new JTextField(10);
        panelCapacidadeCaminhao.add(new JLabel("Capacidade (Caminhão Tanque):"));
        panelCapacidadeCaminhao.add(txtCapacidadeCaminhao);
        panelCampos.add(panelCapacidadeCaminhao);

        JPanel panelCombustivel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        comboCombustivel = new JComboBox<>(new String[] { "", "DIESEL", "GASOLINA", "ALCOOL" });
        panelCombustivel.add(new JLabel("Combustível (Escavadeira):"));
        panelCombustivel.add(comboCombustivel);
        panelCampos.add(panelCombustivel);

        JPanel panelCargaEscavadeira = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtCargaEscavadeira = new JTextField(10);
        panelCargaEscavadeira.add(new JLabel("Carga (Escavadeira):"));
        panelCargaEscavadeira.add(txtCargaEscavadeira);
        panelCampos.add(panelCargaEscavadeira);

        frame.getContentPane().add(panelCampos);

        // Painel para botões
        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Adicionando botões
        JButton btnCadastrar = new JButton("Cadastrar Equipamento");
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarEquipamento();
            }
        });
        panelBotoes.add(btnCadastrar);

        JButton btnLimpar = new JButton("Limpar Campos");
        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
        panelBotoes.add(btnLimpar);

        frame.getContentPane().add(panelBotoes);

        // Área de texto para mensagens
        textAreaMensagens = new JTextArea(5, 20);
        textAreaMensagens.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaMensagens);
        frame.getContentPane().add(scrollPane);

        frame.setVisible(true);
    }

    private void cadastrarEquipamento() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nome = txtNome.getText();
            double custoDia = Double.parseDouble(txtCustoDia.getText());

            // Verifica se mais de um tipo de equipamento específico está sendo cadastrado
            int countTiposPreenchidos = 0;
            if (!txtCapacidadeBarco.getText().isEmpty())
                countTiposPreenchidos++;
            if (!txtCapacidadeCaminhao.getText().isEmpty())
                countTiposPreenchidos++;
            if (!txtCargaEscavadeira.getText().isEmpty())
                countTiposPreenchidos++;

            if (countTiposPreenchidos > 1) {
                textAreaMensagens.setText("Erro: Preencha somente os campos de um tipo de equipamento.");
                return;
            }

            // Cadastra o tipo de equipamento com base nos campos preenchidos
            if (!txtCapacidadeBarco.getText().isEmpty()) {
                int capacidade = Integer.parseInt(txtCapacidadeBarco.getText());
                Barco barco = new Barco(id, nome, custoDia, capacidade);
                barcosCadastrados.add(barco);
                textAreaMensagens.setText("Barco cadastrado com sucesso!");

            } else if (!txtCapacidadeCaminhao.getText().isEmpty()) {
                double capacidade = Double.parseDouble(txtCapacidadeCaminhao.getText());
                CaminhaoTanque caminhao = new CaminhaoTanque(id, nome, custoDia, capacidade);
                caminhoesTanqueCadastrados.add(caminhao);
                textAreaMensagens.setText("Caminhão Tanque cadastrado com sucesso!");

            } else if (!txtCargaEscavadeira.getText().isEmpty()) {
                String combustivel = (String) comboCombustivel.getSelectedItem();
                double carga = Double.parseDouble(txtCargaEscavadeira.getText());
                Escavadeira escavadeira = new Escavadeira(id, nome, custoDia, combustivel, carga);
                escavadeirasCadastradas.add(escavadeira);
                textAreaMensagens.setText("Escavadeira cadastrada com sucesso!");
            } else {
                textAreaMensagens.setText("Erro: Tipo de equipamento não especificado.");
            }

        } catch (NumberFormatException ex) {
            textAreaMensagens.setText("Erro ao cadastrar equipamento: Verifique os dados numéricos.");
        } catch (Exception ex) {
            textAreaMensagens.setText("Erro inesperado: " + ex.getMessage());
        }
    }

    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtCustoDia.setText("");
        txtCapacidadeBarco.setText("");
        txtCapacidadeCaminhao.setText("");
        comboCombustivel.setSelectedIndex(0);
        txtCargaEscavadeira.setText("");
        textAreaMensagens.setText("");
    }
}
