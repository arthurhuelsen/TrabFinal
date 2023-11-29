import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ConsultarAtendimentosGUI extends JFrame {
    private JTextArea textAreaAtendimentos;

    public ConsultarAtendimentosGUI() {
        initialize();
    }

    private void initialize() {
        setTitle("Consultar Todos os Atendimentos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        textAreaAtendimentos = new JTextArea();
        textAreaAtendimentos.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textAreaAtendimentos);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    public void exibirAtendimentos(List<Atendimento> listaDeAtendimentos, List<Evento> listaDeEventos,
            List<Equipe> listaDeEquipes) {
        StringBuilder informacoes = new StringBuilder();

        if (listaDeAtendimentos.isEmpty()) {
            informacoes.append("Não há atendimentos cadastrados.\n");
        } else {
            for (Atendimento atendimento : listaDeAtendimentos) {
                informacoes.append(atendimento.toString()).append("\n");

                // Adicionando informações sobre o evento
                Evento evento = obterEventoPorCodigo(atendimento.getCodigoEvento(), listaDeEventos);
                if (evento != null) {
                    informacoes.append("Evento: ").append(evento.toString()).append("\n");
                }

                // Adicionando informações da equipe alocada e dos equipamentos
                Equipe equipe = atendimento.getEquipe();
                if (equipe != null) {
                    System.out.println("teste2");
                    informacoes.append("Equipe Alocada: ").append(equipe.toString()).append("\n");

                    // Adicionando informações dos equipamentos
                    for (Equipamento equip : equipe.getEquipamentos()) {
                        informacoes.append("Equipamento: ").append(equip.toString()).append("\n");
                    }

                    // Calculando a distância entre a equipe e o evento
                    double distancia = equipe.calcularDistancia(equipe.getLatitude(), equipe.getLongitude(),
                            evento.getLatitude(), evento.getLongitude());

                    // Calculando o custo do atendimento
                    double custo = atendimento.calculaCusto(distancia); // Agora passando a distância calculada
                    System.out.println("teste1");
                    informacoes.append("Custo do Atendimento: ").append(String.format("%.2f", custo)).append("\n");
                }

                informacoes.append("\n");
            }
        }

        textAreaAtendimentos.setText(informacoes.toString());
    }

    private Evento obterEventoPorCodigo(String codigoEvento, List<Evento> listaDeEventos) {
        for (Evento evento : listaDeEventos) {
            if (evento.getCodigo().equals(codigoEvento)) {
                return evento;
            }
        }
        return null;
    }

    // Métodos adicionais conforme necessário

    // Por exemplo, um método para obter um evento por código pode ser necessário,
    // dependendo de como você está gerenciando os eventos em seu sistema.
    // private Evento obterEventoPorCodigo(String codigoEvento) {
    // // Implemente a busca pelo evento aqui
    // }
}
