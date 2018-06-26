package UI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;

public class MainForm extends JFrame {

    private JMenuBar menuBar;
    private JMenu fileMenu;
    GridBagLayout leftMainLayout;

    JPanel leftPanel;
    JButton btnAddDirectory;
    JButton btnAddFile;

    JPanel centerPanel;
    JLabel lblMusicas;
    JLabel lblPlaylist;
    JScrollPane scrollPaneMusicas;
    JScrollPane scrollPanePlaylists;

    public MainForm() {
        initUI();
    }

    private void initUI() {
        this.setTitle("MP3 Player");
        this.setLayout(new BorderLayout());

        leftMainLayout = new GridBagLayout();

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.8;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;

        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        fileMenu.setName("Aplicação");
        menuBar.add(fileMenu);

        this.setJMenuBar(menuBar);

        initLeftPane();
        initCenterPane();

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);

    }

    private void initLeftPane() {

        leftPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        btnAddDirectory = new JButton("Adicionar diretório");

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weighty = 0.2;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.insets = new Insets(20, 20, 0, 0);
        c.gridx = 0;
        c.gridy = 0;

        leftPanel.add(btnAddDirectory, c);

        btnAddFile = new JButton("Adicionar música");

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weighty = 0.2;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(20, 20, 100, 0);
        c.gridx = 0;
        c.gridy = 1;

        leftPanel.add(btnAddFile, c);

        this.add(leftPanel);

    }

    private void initCenterPane() {
        centerPanel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        lblMusicas = new JLabel("Músicas");
        lblPlaylist = new JLabel("Playlist");

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(20, 20, 30, 20);
        c.weighty = 0.2;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_START;

        centerPanel.add(lblMusicas, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(20, 20, 30, 20);
        c.weighty = 0.2;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_START;

        centerPanel.add(lblPlaylist, c);

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(20, 20, 30, 20);
        c.weighty = 0.8;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;

        centerPanel.add(scrollPaneMusicas, c);

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(20, 20, 30, 20);
        c.weighty = 0.8;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;

        centerPanel.add(scrollPanePlaylists, c);

        this.add(centerPanel);
    }

    public void valueChanged(ListSelectionEvent e) {
        //TODO
    }


}
