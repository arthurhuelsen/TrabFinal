import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class VincularEquipamentoEquipeGUI extends JFrame {
    private JComboBox<Equipamento> comboEquipamentos;
    private JComboBox<Equipe> comboEquipes;
    private JTextArea textAreaMensagens;

    public VincularEquipamentoEquipeGUI(List<Equipamento> equipamentos, List<Equipe> equipes) {
        setTitle("Vincular Equipamento à Equipe");
        setSize(500, 400); // Aumenta o tamanho da janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout()); // Usa GridBagLayout para melhor organização

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        comboEquipamentos = new JComboBox<>(new DefaultComboBoxModel<>(equipamentos.toArray(new Equipamento[0])));
        comboEquipes = new JComboBox<>(new DefaultComboBoxModel<>(equipes.toArray(new Equipe[0])));
        JButton btnVincular = new JButton("Vincular");
        btnVincular.addActionListener(this::vincularEquipamentoEquipe);

        textAreaMensagens = new JTextArea(5, 20);
        textAreaMensagens.setEditable(false);

        add(comboEquipamentos, gbc);
        add(comboEquipes, gbc);
        add(btnVincular, gbc);
        add(new JScrollPane(textAreaMensagens), gbc);

        pack(); // Ajusta o tamanho da janela para se adequar aos componentes
        setLocationRelativeTo(null); // Centraliza a janela
        setVisible(true);
    }

    private void vincularEquipamentoEquipe(ActionEvent e) {
        Equipamento equipamentoSelecionado = (Equipamento) comboEquipamentos.getSelectedItem();
        Equipe equipeSelecionada = (Equipe) comboEquipes.getSelectedItem();

        if (equipamentoSelecionado != null && equipeSelecionada != null) {
            if (!equipamentoSelecionado.estaVinculado()) {
                equipamentoSelecionado.vincularEquipe(equipeSelecionada);
                equipeSelecionada.adicionarEquipamento(equipamentoSelecionado);
                textAreaMensagens.setText("Equipamento " + equipamentoSelecionado.getNome() +
                        " vinculado com sucesso à equipe " + equipeSelecionada.getCodinome() + ".");
            } else {
                textAreaMensagens.setText("Erro: Equipamento já está vinculado a outra equipe.");
            }
        } else {
            textAreaMensagens.setText("Erro: Selecione um equipamento e uma equipe válidos.");
        }
    }

}
