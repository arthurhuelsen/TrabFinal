import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class GerenciadorDeAlocacoesGUI extends JFrame {
    private JTextArea textAreaResultados;
    private List<Equipe> equipes;
    private List<Atendimento> atendimentos;
    private List<Evento> eventosCadastrados;
    private Queue<Atendimento> filaAtendimentosPendentes; // Adiciona esta linha

    public GerenciadorDeAlocacoesGUI() {
        this.filaAtendimentosPendentes = new LinkedList<>();
        this.atendimentos = new ArrayList<>(); // Inicializa a lista de atendimentos
        initialize();
    }

    private void initialize() {
        setTitle("Gerenciador de Alocações");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JButton btnAlocarAtendimentos = new JButton("Alocar Atendimentos");
        btnAlocarAtendimentos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alocarAtendimentos();
            }
        });

        textAreaResultados = new JTextArea();
        textAreaResultados.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaResultados);

        add(btnAlocarAtendimentos, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = equipes;
    }

    public void setAtendimentos(List<Atendimento> atendimentos) {
        this.atendimentos = atendimentos; // Atualiza a lista de atendimentos
    }

    public void setEventosCadastrados(List<Evento> eventosCadastrados) {
        this.eventosCadastrados = eventosCadastrados;
    }

    // Método atualizado para alocar atendimentos
    // Método para alocar atendimentos às equipes
    public void alocarAtendimentos() {
        for (Atendimento atendimento : atendimentos) {
            if (atendimento.getStatus().equals("Pendente")) {
                Equipe equipeAlocada = encontrarEquipeParaAtendimento(atendimento);

                if (equipeAlocada != null) {
                    // Atribuindo a equipe ao atendimento
                    atendimento.setEquipe(equipeAlocada);

                    // Atualizando o status do atendimento para 'Alocado'
                    atendimento.setStatus("Alocado");

                    // Adicionando informação ao textArea
                    textAreaResultados.append("Atendimento " + atendimento.getCod() + " alocado para a equipe "
                            + equipeAlocada.getCodinome() + ".\n");
                } else {
                    // Se não encontrar uma equipe, verifica se existe alguma equipe próxima
                    if (existeEquipeProxima(atendimento)) {
                        // Se houver uma equipe próxima, mantém o status como "Pendente"
                        atendimento.setStatus("Pendente");
                        textAreaResultados.append("Atendimento " + atendimento.getCod()
                                + " mantido como pendente, aguardando equipe disponível.\n");
                    } else {
                        // Se não houver equipe próxima, o atendimento é cancelado
                        atendimento.setStatus("Cancelado");
                        textAreaResultados
                                .append("Atendimento " + atendimento.getCod() + " cancelado por falta de equipe.\n");
                    }
                }
            }
        }
    }

    private Equipe encontrarEquipeParaAtendimento(Atendimento atendimento) {
        Evento eventoAtendimento = obterEventoPorCodigo(atendimento.getCodigoEvento());
        if (eventoAtendimento == null)
            return null;

        for (Equipe equipe : equipes) {
            if (!equipeEstaAlocada(equipe) && equipe.podeAtender(eventoAtendimento)) {
                return equipe;
            }
        }
        return null;
    }

    private boolean equipeEstaAlocada(Equipe equipe) {
        for (Atendimento atendimento : atendimentos) {
            if (atendimento.getEquipe() != null && atendimento.getEquipe().equals(equipe)
                    && !atendimento.getStatus().equals("Pendente") && !atendimento.getStatus().equals("Cancelado")) {
                return true;
            }
        }
        return false;
    }

    private Evento obterEventoPorCodigo(String codigoEvento) {
        for (Evento evento : eventosCadastrados) {
            if (evento.getCodigo().equals(codigoEvento)) {
                return evento;
            }
        }
        return null;
    }

    private boolean existeEquipeProxima(Atendimento atendimento) {
        Evento eventoAtendimento = obterEventoPorCodigo(atendimento.getCodigoEvento());
        if (eventoAtendimento == null)
            return false;

        for (Equipe equipe : equipes) {
            if (equipe.podeAtender(eventoAtendimento)) {
                return true;
            }
        }
        return false;
    }

    // Getters e Setters conforme necessário
}
