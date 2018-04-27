/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package editorish;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;

/**
 * Tämän luokan tarkoitus on toimia sellaisena joka lyhentää rivimäärää
 * EditoriGUI -luokassa ja ehkä myös selkeyttää koodin lukemista.
 *
 * @author Raul Becker
 *
 * @since 6.5.2014
 */
public class GuiCreator {

    public GuiCreator() {
    }

    /**
     *
     *
     * @param text valintaa kuvaava teksti.
     * @param actionCommand tapahtumakomento jonka tämän valinta aiheuttaa.
     * @return JMenuItem
     */
    public static JMenuItem getMenuItem(String text, String actionCommand) {

        JMenuItem menuItem = new JMenuItem(text);
        menuItem.setActionCommand(actionCommand);


        return menuItem;
    }

    /**
     *
     * @param value alkuarvo mistä progressbar aloittaa.
     * @param stringPainted näytetäänkö prosentit vai ei.
     * @param sizeX leveyden määrittely
     * @param sizeY korkeuden määrittely
     * @param top yläreunus
     * @param left vasen reuna
     * @param bottom alareunus
     * @param right oikea reuna
     * @return JProgressBar
     */
    public static JProgressBar getProgressBar(int value,
            boolean stringPainted,
            int sizeX,
            int sizeY,
            int top,
            int left,
            int bottom,
            int right) {
        JProgressBar progressBar = new JProgressBar(value);

        progressBar.setStringPainted(stringPainted);
        progressBar.setSize(sizeX, sizeY);
        progressBar.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
        return progressBar;
    }
}
