package editorish;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingWorker.StateValue;
import static javax.swing.SwingWorker.StateValue.DONE;
import static javax.swing.SwingWorker.StateValue.PENDING;
import static javax.swing.SwingWorker.StateValue.STARTED;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Raul Becker
 *
 * @version 1.0
 *
 * @since 17.3.2014
 *
 * @see Editorish
 * @see FileOpener
 */
public class EditoriGUI extends JFrame implements ActionListener, ListSelectionListener {

    private static final long serialVersionUID = 1L;
    private static JLabel filesScannedLabel,
            filesScannedLabelQty,
            fileCount,
            wordsScannedLabel,
            wordsScannedLabelQty,
            statusLabel1,
            statusLabel1Value,
            statusLabel2,
            statusLabel2Value;
    static JList searchResults;
    private static Editorish editorishObject;

    /**
     * Tällä metodilla
     *
     * @throws InterruptedException
     */
    private static void gui() throws InterruptedException {
        //luodaan ja naytetaan ikkuna

        EditoriGUI gui = new EditoriGUI();

        gui.setTitle("Editorish");
        gui.pack();
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Käynnistetään luokan jaettu metodi gui();
     */
    public static void main(String[] args) throws InterruptedException {
        EditoriGUI.gui();
    }

    /* Käyttöliittymän komponentit: */
    private JFileChooser directoryChooser;
    private JFrame popupWindow;
    private JProgressBar progressBar;
    private JButton searchButton, selectDirButton;
    private JTextField searchField;
    private JTextPane fileViewer;
    private JMenuBar menuBar;
    private JMenu menu, submenu;
    private JMenuItem menuItem;
    private JRadioButtonMenuItem rbMenuItem;
    private JCheckBoxMenuItem cbMenuItem;
    private DefaultListModel<String> listModel;
    private Vector<FileSummary> tempList;
    private FileOpener fileWorker;
    private String searchWord;
    /*
     * Avaa ikkunan koko näytön resoluution mukaan miinus windowsin 
     * taskbar.
     */
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int WINDOWWIDTH = (int) screenSize.getWidth();
    private final int WINDOWHEIGHT = (int) screenSize.getHeight() - 30;

    /**
     * Pohjustaa käyttöliittymän elementit ja asettelee ne paikoilleen, sekä
     * käynnistää lopuksi metodin newEditorishObject.
     *
     * @throws InterruptedException
     */
    private EditoriGUI() throws InterruptedException {

        // JFramen tyyli ja koko
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(WINDOWWIDTH, WINDOWHEIGHT));


        /*
         * Paneelit määritellään järjestyksessä ylhäältä alas.
         *  - Paneeli 1 sisältää hakukentän ja hakunapin
         *  - Paneeli 2 sisältää hakutuloslistauksen eli JListin sekä
         *    teksitiedoston sisältöä näyttävän JTextArean
         *  - Paneeli 3 sisältää tiedostojen läpikäyntiä indikoivan
         *    edistymismittarin
         */

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.setPreferredSize(new Dimension(WINDOWWIDTH - 30,
                (int) ((double) WINDOWHEIGHT / 1.1)));

        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.setPreferredSize(new Dimension(WINDOWWIDTH - 30, 20));

        JPanel panel3SubPanel1 = new JPanel(new GridLayout(1, 2));
        panel3SubPanel1.setPreferredSize(new Dimension(200, 20));

        JPanel panel3SubPanel2 = new JPanel(new GridLayout(1, 4));
        panel3SubPanel2.setPreferredSize(new Dimension((WINDOWWIDTH / 6) - 15, 20));

        JPanel panel3SubPanel21 = new JPanel(new GridBagLayout());
        panel3SubPanel21.setPreferredSize(new Dimension((WINDOWWIDTH / 6) - 15, 20));

        // Hakukenttä ja hakunappi. Molemmat voi
        searchField = new JTextField("", 60);
        searchField.setActionCommand("HakuToiminto");
        searchField.addActionListener(this);
        searchButton = new JButton("Hae");
        searchButton.setActionCommand("HakuToiminto");
        searchButton.addActionListener(this);

        // Valikon määrittely
        menuBar = new JMenuBar();
        menu = new JMenu("Valinnat");

        // Valikkoryhmä
        menuItem = GuiCreator.getMenuItem("Valitse hakemisto", "AvaaHakemisto");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = GuiCreator.getMenuItem("Ohjeet", "AvaaOhjeet");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = GuiCreator.getMenuItem("Sulje ohjelma", "SuljeOhjelma");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menu.addActionListener(this);

        menuBar.add(menu);
        menuBar.add(searchField);
        menuBar.add(searchButton, BorderLayout.EAST);

        this.setJMenuBar(menuBar);

        filesScannedLabelQty = new JLabel("0");
        filesScannedLabel = new JLabel(" / ");
        fileCount = new JLabel("0");

        progressBar = GuiCreator.getProgressBar(0, true, 180, 40, 3, 3, 3, 3);


        panel3SubPanel21.add(filesScannedLabelQty);
        panel3SubPanel21.add(filesScannedLabel);

        panel3SubPanel21.add(fileCount);
        panel3SubPanel2.add(panel3SubPanel21);
        panel3SubPanel2.add(progressBar);

        panel3.add(panel3SubPanel1, BorderLayout.WEST);
        panel3.add(panel3SubPanel2, BorderLayout.EAST);


        wordsScannedLabelQty = new JLabel("0");
        panel3SubPanel1.add(wordsScannedLabelQty);

        searchResults = new JList();
        searchResults.setBackground(Color.white);
        searchResults.setSize(new Dimension(WINDOWWIDTH - 30,
                (int) ((double) WINDOWHEIGHT / 2) - 30));
        searchResults.addListSelectionListener(this);

        fileViewer = new JTextPane();
        fileViewer.setEditable(false);
        fileViewer.setBackground(Color.white);
        fileViewer.setSize(new Dimension(WINDOWWIDTH - 30,
                (int) ((double) WINDOWHEIGHT / 2) - 30));

        panel2.add(new JScrollPane(searchResults), BorderLayout.NORTH);
        panel2.add(new JScrollPane(fileViewer), BorderLayout.CENTER);

        this.add(panel2);
        this.add(panel3);

        newEditorishObject();
        editorishObject.execute();

    }

    /**
     * Toiminto jolla käyttäjälle aukeaa erillinen ikkuna josta voi valita
     * haluamansa hakemiston.
     */
    private void directorySelector() {

        JPanel dirPanel = new JPanel(new GridBagLayout());

        directoryChooser = new JFileChooser();
        directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        directoryChooser.addActionListener(this);
        directoryChooser.setAcceptAllFileFilterUsed(false);
        dirPanel.add(directoryChooser);

        popupWindow = new JFrame();
        popupWindow.setSize(600, 400);
        popupWindow.setVisible(true);
        popupWindow.setLayout(new FlowLayout());
        popupWindow.add(dirPanel);

    }

    /**
     * Tämän oli tarkoitus olla ohjelman sisäinen informaatioikkuna ja
     * ohjeistus.
     */
    private void aboutWindow() {
        JPanel label = new JPanel();
        label.add(new JLabel("Ohjelmasta tietoja ja ohjeita"));

        popupWindow = new JFrame();
        popupWindow.setSize(600, 400);
        popupWindow.setVisible(true);
        popupWindow.setLayout(new FlowLayout());
        popupWindow.add(label, BorderLayout.CENTER);
    }

    /**
     * Tapahtumien käsittely. Tapahtumakomennon mukaan käynnistetään haluttu
     * toiminto.
     *
     * @param e Tapahtuma
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case "HakuToiminto":
                searchWord = searchField.getText();
                searchList();
                break;
            case "AvaaHakemisto":
                this.directorySelector();
                break;
            case "ApproveSelection":
                // tyhjätään textarea...
                fileViewer.removeAll();
                popupWindow.dispose();
                newEditorishObject();
                editorishObject.setDirectory(directoryChooser.getSelectedFile().toString());
                editorishObject.execute();
                break;
            case "CancelSelection":
                popupWindow.dispose();
                break;
            case "SuljeOhjelma":
                System.exit(0);
                break;
            case "AvaaOhjeet":
                aboutWindow();
                break;
        }
        this.repaint();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

        JList list = (JList) e.getSource();
        int index = list.getSelectedIndex();

        if (index == -1) {
        } else {
            this.repaint();
            try {
                fileWorker.cancel(true);
            } catch (NullPointerException ex) {
            } finally {
                fileWorker = new FileOpener(fileViewer, tempList, index, searchWord);
                fileWorker.execute();
            }
            this.repaint();
        }
    }

    /**
     * Käyttää luokan hakusanojen merkkijonoa ja toteuttaa haun, sekä päivittää
     * hakutuloslistan.
     */
    private void searchList() {
        String listaus = "", hakutuloksia = "(";
        String[] words = searchWord.split(" ");
        listModel = new DefaultListModel();
        tempList = editorishObject.searchWords(words);
        DecimalFormat df = new DecimalFormat("0.0#");

        for (int i = 0; i < tempList.size(); i++) {
            hakutuloksia = "";
            for (int j = 0; j < words.length; j++) {
                hakutuloksia += words[j] + " " + tempList.get(i).getWord(words[j]) + ", ";
            }
            listModel.addElement(tempList.get(i).getFilename() + "   ---------->  Pisteytys: " + df.format(tempList.get(i).getTotalScore())
                    + "   ----------> Hakusanojen tulokset: " + hakutuloksia.replaceAll(", $", ""));

        }
        searchResults.removeAll();
        searchResults.setModel(listModel);
    }

    /**
     * Luo uuden ilmentymän Editorish -luokasta, mutta ei vielä käynnistä
     * SwingWorkeria.
     */
    private void newEditorishObject() {
        editorishObject = new Editorish(filesScannedLabelQty, fileCount, wordsScannedLabelQty, progressBar);
        editorishObject.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent event) {

                switch (event.getPropertyName()) {
                    case "progress":
                        break;
                    case "state":
                        switch ((StateValue) event.getNewValue()) {
                            case DONE:
                                editorishObject.cancel(true);
                                break;
                            case STARTED:
                                break;
                            case PENDING:
                                break;
                        }
                        break;
                }
            }
        });
    }
}
