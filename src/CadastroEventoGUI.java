import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CadastroEventoGUI extends JFrame {
    private JTextField txtCodigo;
    private JTextField txtData;
    private JTextField txtLatitude;
    private JTextField txtLongitude;
    private JTextArea textAreaMensagens;

    private List<Evento> eventosCadastrados;
    private SimpleDateFormat formatadorData;

    public CadastroEventoGUI() {
        eventosCadastrados = new ArrayList<>();
        formatadorData = new SimpleDateFormat("dd/MM/yyyy");
        initialize();
    }

    private void initialize() {
        setTitle("Cadastro de Evento");
        setBounds(100, 100, 400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Adiciona os componentes de entrada
        add(createEntryPanel("Código:", txtCodigo = new JTextField(10)));
        add(createEntryPanel("Data (dd/MM/yyyy):", txtData = new JTextField(10)));
        add(createEntryPanel("Latitude:", txtLatitude = new JTextField(10)));
        add(createEntryPanel("Longitude:", txtLongitude = new JTextField(10)));

        // Adiciona os botões e a área de mensagens
        add(createButtonPanel());
        add(createMessageArea());

        setVisible(true);
    }

    private JPanel createEntryPanel(String label, JTextField textField) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(label));
        panel.add(textField);
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        JButton btnCadastrar = new JButton("Cadastrar Evento");
        btnCadastrar.addActionListener(e -> cadastrarEvento());
        panel.add(btnCadastrar);

        JButton btnLimpar = new JButton("Limpar Campos");
        btnLimpar.addActionListener(e -> limparCampos());
        panel.add(btnLimpar);

        return panel;
    }

    private JScrollPane createMessageArea() {
        textAreaMensagens = new JTextArea(4, 20);
        textAreaMensagens.setEditable(false);
        return new JScrollPane(textAreaMensagens);
    }

    private void cadastrarEvento() {
        String codigo = txtCodigo.getText();
        String dataTexto = txtData.getText();
        try {
            // Verifica o formato da data
            Date data = formatadorData.parse(dataTexto);
            double latitude = Double.parseDouble(txtLatitude.getText());
            double longitude = Double.parseDouble(txtLongitude.getText());

            // Verifica se o código do evento já existe
            if (eventosCadastrados.stream().noneMatch(e -> e.getCodigo().equalsIgnoreCase(codigo))) {
                Evento novoEvento = new Evento(codigo, dataTexto, latitude, longitude);
                eventosCadastrados.add(novoEvento);
                textAreaMensagens.setText("Evento cadastrado com sucesso!");
            } else {
                textAreaMensagens.setText("Erro: Código do evento já existente.");
            }
        } catch (NumberFormatException ex) {
            textAreaMensagens.setText("Erro ao cadastrar evento: Verifique os dados numéricos.");
        } catch (java.text.ParseException ex) {
            textAreaMensagens.setText("Erro ao cadastrar evento: Formato de data inválido.");
        } catch (IllegalArgumentException ex) {
            textAreaMensagens.setText("Erro ao cadastrar evento: " + ex.getMessage());
        } catch (Exception ex) {
            textAreaMensagens.setText("Erro inesperado: " + ex.getMessage());
        }
    }

    private void limparCampos() {
        txtCodigo.setText("");
        txtData.setText("");
        txtLatitude.setText("");
        txtLongitude.setText("");
        textAreaMensagens.setText("");
    }
}
