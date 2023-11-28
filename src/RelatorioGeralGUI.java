import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RelatorioGeralGUI extends JFrame {
    private JTextArea textArea;

    public RelatorioGeralGUI(List<Evento> eventos, List<Equipamento> equipamentos, List<Equipe> equipes,
            List<Atendimento> atendimentos) {
        setTitle("Relatório Geral");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        StringBuilder relatorio = new StringBuilder();

        // Adicionar eventos ao relatório
        if (!eventos.isEmpty()) {
            relatorio.append("Eventos:\n");
            for (Evento evento : eventos) {
                relatorio.append(evento.toString()).append("\n");
            }
        } else {
            relatorio.append("Não há eventos cadastrados.\n");
        }

        // Adicionar equipamentos ao relatório
        if (!equipamentos.isEmpty()) {
            relatorio.append("\nEquipamentos:\n");
            for (Equipamento equipamento : equipamentos) {
                relatorio.append(equipamento.toString()).append("\n");
            }
        } else {
            relatorio.append("Não há equipamentos cadastrados.\n");
        }

        // Adicionar equipes ao relatório
        if (!equipes.isEmpty()) {
            relatorio.append("\nEquipes:\n");
            for (Equipe equipe : equipes) {
                relatorio.append(equipe.toString()).append("\n");
            }
        } else {
            relatorio.append("Não há equipes cadastradas.\n");
        }

        // Adicionar atendimentos ao relatório
        if (!atendimentos.isEmpty()) {
            relatorio.append("\nAtendimentos:\n");
            for (Atendimento atendimento : atendimentos) {
                relatorio.append(atendimento.toString()).append("\n");
            }
        } else {
            relatorio.append("Não há atendimentos cadastrados.\n");
        }

        textArea.setText(relatorio.toString());
    }
}
