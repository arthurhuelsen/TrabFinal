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

    private JTextField txtMagnitude; // Terremoto
    private JTextField txtEstiagem; // Seca
    private JTextField txtVelocidade; // Ciclone
    private JTextField txtPrecipitacao; // Ciclone

    private List<Evento> eventosCadastrados;
    private SimpleDateFormat formatadorData;

    public CadastroEventoGUI() {
        eventosCadastrados = new ArrayList<>();
        formatadorData = new SimpleDateFormat("dd/MM/yyyy");
        initialize();
    }

    private void initialize() {
        setTitle("Cadastro de Evento");
        setBounds(100, 100, 500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Adiciona os componentes de entrada
        add(createEntryPanel("Código:", txtCodigo = new JTextField(10)));
        add(createEntryPanel("Data (dd/MM/yyyy):", txtData = new JTextField(10)));
        add(createEntryPanel("Latitude:", txtLatitude = new JTextField(10)));
        add(createEntryPanel("Longitude:", txtLongitude = new JTextField(10)));

        // Adiciona campos específicos de cada tipo de evento
        add(createEntryPanel("Magnitude (Terremoto):", txtMagnitude = new JTextField(10)));
        add(createEntryPanel("Estiagem (Seca):", txtEstiagem = new JTextField(10)));
        add(createEntryPanel("Velocidade do Vento (Ciclone):", txtVelocidade = new JTextField(10)));
        add(createEntryPanel("Precipitação (Ciclone):", txtPrecipitacao = new JTextField(10)));

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
            Date data = formatadorData.parse(dataTexto);
            double latitude = Double.parseDouble(txtLatitude.getText());
            double longitude = Double.parseDouble(txtLongitude.getText());

            // Verifica se mais de um tipo de evento específico está sendo cadastrado
            int countTiposPreenchidos = 0;
            if (!txtMagnitude.getText().isEmpty())
                countTiposPreenchidos++;
            if (!txtEstiagem.getText().isEmpty())
                countTiposPreenchidos++;
            if (!txtVelocidade.getText().isEmpty())
                countTiposPreenchidos++;

            if (countTiposPreenchidos > 1) {
                textAreaMensagens.setText("Erro: Preencha somente os campos de um tipo de evento.");
                return;
            }

            // Cadastra o tipo de evento com base nos campos preenchidos
            if (!txtMagnitude.getText().isEmpty()) {
                double magnitude = Double.parseDouble(txtMagnitude.getText());
                // Adiciona o Terremoto à lista
                // eventosCadastrados.add(new Terremoto(codigo, data, latitude, longitude,
                // magnitude));
                textAreaMensagens.setText("Terremoto cadastrado com sucesso!");
            } else if (!txtEstiagem.getText().isEmpty()) {
                int estiagem = Integer.parseInt(txtEstiagem.getText());
                // Adiciona a Seca à lista
                // eventosCadastrados.add(new Seca(codigo, data, latitude, longitude,
                // estiagem));
                textAreaMensagens.setText("Seca cadastrada com sucesso!");
            } else if (!txtVelocidade.getText().isEmpty()) {
                double velocidade = Double.parseDouble(txtVelocidade.getText());
                double precipitacao = Double.parseDouble(txtPrecipitacao.getText());
                // Adiciona o Ciclone à lista
                // eventosCadastrados.add(new Ciclone(codigo, data, latitude, longitude,
                // velocidade, precipitacao));
                textAreaMensagens.setText("Ciclone cadastrado com sucesso!");
            } else {
                textAreaMensagens.setText("Erro: Tipo de evento não especificado.");
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
        txtMagnitude.setText("");
        txtEstiagem.setText("");
        txtVelocidade.setText("");
        txtPrecipitacao.setText("");
        textAreaMensagens.setText("");
    }
}
