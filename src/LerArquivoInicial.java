import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LerArquivoInicial extends JFrame {
    private MenuPrincipalGUI menuPrincipal;
    private JTextField txtNomeArquivo;
    private JButton btnCarregarDados;
    List<Equipe> equipesCarregadas = new ArrayList<>(); // Lista para manter as equipes carregadas
    List<Evento> eventosCarregados = new ArrayList<>();// Lista para manter os eventos carregados
    private List<Equipamento> equipamentosCarregados;

    public LerArquivoInicial(MenuPrincipalGUI menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
        initialize();
    }

    public void initialize() {
        setTitle("Carregar Dados de Arquivo");
        setSize(400, 120);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        txtNomeArquivo = new JTextField(20);
        btnCarregarDados = new JButton("Carregar Dados");

        add(new JLabel("Nome do Arquivo:"));
        add(txtNomeArquivo);
        add(btnCarregarDados);

        // Evento do botão
        btnCarregarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeArquivo = txtNomeArquivo.getText();
                carregarDados(nomeArquivo);
            }
        });

        System.out.println("Diretório de trabalho atual: " + System.getProperty("user.dir"));
        setVisible(true);
    }

    private void carregarDados(String nomeArquivo) {
        // Verificando no diretório 'src/Data'
        File arquivo = new File("src/Data/" + nomeArquivo);
        if (!arquivo.exists()) {
            JOptionPane.showMessageDialog(this, "O arquivo não existe.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            // Chamando os métodos de carregamento com base no nome do arquivo
            if (nomeArquivo.contains("EQUIPES")) {
                carregarEquipes(arquivo.getAbsolutePath());
            } else if (nomeArquivo.contains("EQUIPAMENTOS")) {
                carregarEquipamentos(arquivo.getAbsolutePath(), equipesCarregadas, menuPrincipal);
            } else if (nomeArquivo.contains("EVENTOS")) {
                carregarEventos(arquivo.getAbsolutePath(), menuPrincipal);
            } else if (nomeArquivo.contains("ATENDIMENTOS")) {
                carregarAtendimentos(arquivo.getAbsolutePath(), eventosCarregados, menuPrincipal);
            } else {
                JOptionPane.showMessageDialog(this, "Tipo de arquivo desconhecido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(this, "Dados carregados com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao carregar os dados: " + e.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarEquipes(String nomeArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha = br.readLine(); // Ler o cabeçalho e ignorá-lo
            while ((linha = br.readLine()) != null) {
                String[] dadosEquipe = linha.split(";");
                if (dadosEquipe.length == 4) {
                    String codinome = dadosEquipe[0].trim();
                    int quantidade = Integer.parseInt(dadosEquipe[1].trim());
                    double latitude = Double.parseDouble(dadosEquipe[2].trim());
                    double longitude = Double.parseDouble(dadosEquipe[3].trim());
                    Equipe equipe = new Equipe(codinome, quantidade, latitude, longitude);
                    equipesCarregadas.add(equipe); // Adicionar à lista
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(equipesCarregadas);
        // Após carregar, você pode atualizar as equipes no MenuPrincipalGUI
        menuPrincipal.atualizarEquipes(equipesCarregadas);
    }

    private void carregarEquipamentos(String nomeArquivo, List<Equipe> listaDeEquipes, MenuPrincipalGUI menuPrincipal) {
        List<Equipamento> equipamentosCarregados = new ArrayList<>();
        List<Barco> barcos = new ArrayList<>();
        List<CaminhaoTanque> caminhoesTanque = new ArrayList<>();
        List<Escavadeira> escavadeiras = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha = br.readLine(); // Pula o cabeçalho
            while ((linha = br.readLine()) != null) {
                try {
                    String[] dadosEquipamento = linha.split(";");

                    if (dadosEquipamento.length >= 6) {
                        int id = Integer.parseInt(dadosEquipamento[0].trim());
                        String nome = dadosEquipamento[1].trim();
                        double custoDia = Double.parseDouble(dadosEquipamento[2].trim());
                        String codinomeEquipe = dadosEquipamento[3].trim();
                        int tipo = Integer.parseInt(dadosEquipamento[4].trim());

                        Equipamento equipamento = null;
                        Equipe equipeVinculada = listaDeEquipes.stream()
                                .filter(equipe -> equipe.getCodinome().equals(codinomeEquipe))
                                .findFirst()
                                .orElse(null);

                        if (equipeVinculada == null) {
                            System.out.println("Equipe não encontrada para o equipamento: " + nome);
                            continue; // Se não encontrar a equipe, pula para a próxima linha
                        }

                        switch (tipo) {
                            case 1: // Barco
                                int capacidadePassageiro = Integer.parseInt(dadosEquipamento[5].trim());
                                Barco barco = new Barco(id, nome, custoDia, capacidadePassageiro);
                                barcos.add(barco);
                                break;
                            case 2: // Caminhão Tanque
                                double capacidade = Double.parseDouble(dadosEquipamento[5].trim());
                                CaminhaoTanque caminhaoTanque = new CaminhaoTanque(id, nome, custoDia, capacidade);
                                caminhoesTanque.add(caminhaoTanque);
                                break;
                            case 3: // Escavadeira
                                String combustivel = dadosEquipamento[5].trim();
                                double carga = Double.parseDouble(dadosEquipamento[6].trim());
                                Escavadeira escavadeira = new Escavadeira(id, nome, custoDia, combustivel, carga);
                                escavadeiras.add(escavadeira);
                                break;
                        }

                        if (equipamento != null) {
                            equipamento.vincularEquipe(equipeVinculada);
                            equipeVinculada.adicionarEquipamento(equipamento);
                            equipamentosCarregados.add(equipamento);
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro ao converter números na linha: " + linha);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Erro ao acessar dados da linha: " + linha);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Atualiza as listas no MenuPrincipalGUI
        menuPrincipal.atualizarBarcos(barcos);
        menuPrincipal.atualizarCaminhoesTanque(caminhoesTanque);
        menuPrincipal.atualizarEscavadeiras(escavadeiras);
    }

    private void carregarEventos(String nomeArquivo, MenuPrincipalGUI menuPrincipal) {
        List<Ciclone> ciclonesCarregados = new ArrayList<>();
        List<Terremoto> terremotosCarregados = new ArrayList<>();
        List<Seca> secasCarregadas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha = br.readLine(); // Pula o cabeçalho
            while ((linha = br.readLine()) != null) {
                String[] dadosEvento = linha.split(";");

                String codigo = dadosEvento[0].trim();
                String data = dadosEvento[1].trim();
                double latitude = Double.parseDouble(dadosEvento[2].trim());
                double longitude = Double.parseDouble(dadosEvento[3].trim());
                int tipo = Integer.parseInt(dadosEvento[4]);

                if (tipo == 1 && dadosEvento.length >= 7) {
                    double velocidade = Double.parseDouble(dadosEvento[5].trim());
                    double precipitacao = Double.parseDouble(dadosEvento[6].trim());
                    ciclonesCarregados.add(new Ciclone(codigo, data, latitude, longitude, velocidade, precipitacao));
                } else if (tipo == 2 && dadosEvento.length >= 6) {
                    double magnitude = Double.parseDouble(dadosEvento[5].trim());
                    terremotosCarregados.add(new Terremoto(codigo, data, latitude, longitude, magnitude));
                } else if (tipo == 3 && dadosEvento.length >= 6) {
                    int estiagem = Integer.parseInt(dadosEvento[5].trim());
                    secasCarregadas.add(new Seca(codigo, data, latitude, longitude, estiagem));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Atualiza as listas de eventos no MenuPrincipalGUI
        menuPrincipal.atualizarCiclones(ciclonesCarregados);
        menuPrincipal.atualizarTerremotos(terremotosCarregados);
        menuPrincipal.atualizarSecas(secasCarregadas);
    }

    private void carregarAtendimentos(String nomeArquivo, List<Evento> listaDeEventos, MenuPrincipalGUI menuPrincipal) {
        List<Atendimento> atendimentosCarregados = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha = br.readLine(); // Pula o cabeçalho
            while ((linha = br.readLine()) != null) {
                String[] dadosAtendimento = linha.split(";");

                if (dadosAtendimento.length >= 5) {
                    int cod = Integer.parseInt(dadosAtendimento[0].trim());
                    String dataInicio = dadosAtendimento[1].trim();
                    int duracao = Integer.parseInt(dadosAtendimento[2].trim());
                    String status = dadosAtendimento[3].trim();
                    String codigoEvento = dadosAtendimento[4].trim();

                    // Procurando o evento correspondente
                    Evento evento = listaDeEventos.stream()
                            .filter(e -> e.getCodigo().equals(codigoEvento))
                            .findFirst()
                            .orElse(null);

                    if (evento != null) {
                        Atendimento atendimento = new Atendimento(cod, dataInicio, duracao, status, codigoEvento);
                        atendimentosCarregados.add(atendimento);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Atualiza a lista de atendimentos no MenuPrincipalGUI
        menuPrincipal.atualizarAtendimentos(atendimentosCarregados);
    }
    // Método principal para testar a interface

}
