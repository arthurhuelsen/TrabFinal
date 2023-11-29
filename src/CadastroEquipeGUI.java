import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class CadastroEquipeGUI extends JFrame {
    private JFrame frame;
    private JTextField txtCodinome;
    private JTextField txtQuantidadeMembros;
    private JTextField txtLatitude;
    private JTextField txtLongitude;
    private JTextArea textAreaMensagens;
    private List<Equipe> equipesCadastradas;

    public CadastroEquipeGUI() {
        equipesCadastradas = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 750, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Cria um painel para os campos de entrada e define o layout
        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Adiciona componentes ao painel de campos
        panelCampos.add(new JLabel("Codinome:"));
        txtCodinome = new JTextField(10);
        panelCampos.add(txtCodinome);

        panelCampos.add(new JLabel("Quantidade de Membros:"));
        txtQuantidadeMembros = new JTextField(10);
        panelCampos.add(txtQuantidadeMembros);

        panelCampos.add(new JLabel("Latitude:"));
        txtLatitude = new JTextField(10);
        panelCampos.add(txtLatitude);

        panelCampos.add(new JLabel("Longitude:"));
        txtLongitude = new JTextField(10);
        panelCampos.add(txtLongitude);

        frame.getContentPane().add(panelCampos); // Adiciona o painel de campos à janela

        // Cria um painel para os botões e define o layout
        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Adiciona botões ao painel de botões
        JButton btnCadastrar = new JButton("Cadastrar Equipe");
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarEquipe();
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

        JButton btnMostrar = new JButton("Mostrar");
        btnMostrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarEquipes();
            }
        });
        panelBotoes.add(btnMostrar);

        frame.getContentPane().add(panelBotoes); // Adiciona o painel de botões à janela

        // Área de texto para exibir mensagens ao usuário
        textAreaMensagens = new JTextArea(5, 20);
        textAreaMensagens.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaMensagens); // Adiciona barra de rolagem à área de texto
        frame.getContentPane().add(scrollPane);

    }

    public void showGUI() {
        frame.setVisible(true);
    }

    private void cadastrarEquipe() {
        String codinome = txtCodinome.getText();
        try {
            int quantidadeMembros = Integer.parseInt(txtQuantidadeMembros.getText());
            double latitude = Double.parseDouble(txtLatitude.getText());
            double longitude = Double.parseDouble(txtLongitude.getText());

            Equipe novaEquipe = new Equipe(codinome, quantidadeMembros, latitude, longitude);

            if (equipesCadastradas.stream().noneMatch(e -> e.getCodinome().equalsIgnoreCase(codinome))) {
                equipesCadastradas.add(novaEquipe);
                textAreaMensagens.setText("Equipe cadastrada com sucesso!");
            } else {
                textAreaMensagens.setText("Erro: Codinome já existente.");
            }
        } catch (NumberFormatException ex) {
            textAreaMensagens.setText("Erro ao cadastrar equipe: Verifique os dados inseridos.");
        } catch (Exception ex) {
            textAreaMensagens.setText("Erro ao cadastrar equipe: " + ex.getMessage());
        }
    }

    private void limparCampos() {
        txtCodinome.setText("");
        txtQuantidadeMembros.setText("");
        txtLatitude.setText("");
        txtLongitude.setText("");
        textAreaMensagens.setText("");
    }

    private void mostrarEquipes() {
        // Ordena a lista de equipes em ordem crescente de codinome antes de exibir
        Collections.sort(equipesCadastradas, (e1, e2) -> e1.getCodinome().compareTo(e2.getCodinome()));

        StringBuilder sb = new StringBuilder();
        for (Equipe equipe : equipesCadastradas) {
            sb.append("Codinome: ").append(equipe.getCodinome())
                    .append(", Membros: ").append(equipe.getQuantidadeMembros())
                    .append(", Latitude: ").append(equipe.getLatitude())
                    .append(", Longitude: ").append(equipe.getLongitude())
                    .append("\n");
        }
        textAreaMensagens.setText(sb.toString());
    }

    public List<Equipe> getEquipesCadastradas() {
        return equipesCadastradas;
    }

    public void setEquipesCadastradas(List<Equipe> equipesCadastradas) {
        this.equipesCadastradas = equipesCadastradas;
    }

}
