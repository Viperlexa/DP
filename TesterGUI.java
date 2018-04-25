package example;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TesterGUI extends JFrame {

    JLabel lbl = new JLabel("Рекомендация по тестированию");
    JLabel lbl1 = new JLabel("Список файлов");
    JButton jfile = new JButton("Указать путь");
    JButton pasre = new JButton("Сканировать файл");
    JButton parseAll = new JButton("Сканировать все");
    JButton save = new JButton("Сохранить результат");
    JTextField recom = new JTextField();

    DefaultListModel model = new DefaultListModel();
    final JList clist = new JList(model);
    JScrollPane scroll = new JScrollPane(clist);

    Object[] headers = {"Class", "Method", "SubMethod"};

    Object[][] data = {
            {"Chooser", "createChooser()", "setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)"},
            {"Chooser", "createChooser()", "setSize(200,200)"},
            {"Chooser", "createChooser()", "setLocationRelativeTo(null)"},
            {"Chooser", "createChooser()", "add(check1,c)"},
            {"Chooser", "createChooser()", "add(check2,c)"},
            {"Chooser", "createChooser()", "add(check3,c)"},
            {"Chooser", "createChooser()", "add(bt,c)"},
            {"Chooser", "actionPerformed", "setVisible(false)"},
            {"Chooser", "actionPerformed", "isSelected()"},
            {"Chooser", "actionPerformed", "isSelected()"},
            {"Chooser", "actionPerformed", "isSelected()"},
            {"Chooser", "actionPerformed", "add(panel)"},};

    Object[][] data1 = {
            {"", "", ""},};

    JTable table = new JTable(data, headers);
    JScrollPane tbl = new JScrollPane(table);

    JTable table1 = new JTable(data1, headers);
    JScrollPane tbl1 = new JScrollPane(table1);

    public TesterGUI() {
        setLayout(null);
        setTitle("Форма тестирования");
        setBounds(150, 150, 1000, 610);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tbl1.setBounds(20, 20, 660, 300);
        tbl1.setVisible(true);
        add(tbl1);
        tbl.setBounds(20, 20, 660, 300);
        tbl.setVisible(false);
        add(tbl);
        lbl.setBounds(20, 330, 190, 20);
        lbl.setVisible(true);
        add(lbl);
        recom.setBounds(20, 350, 660, 150);
        recom.setVisible(true);
        add(recom);
        jfile.setBounds(20, 510, 150, 45);
        jfile.setVisible(true);
        add(jfile);
        pasre.setBounds(190, 510, 150, 45);
        pasre.setVisible(true);
        add(pasre);
        parseAll.setBounds(360, 510, 150, 45);
        parseAll.setVisible(true);
        add(parseAll);
        save.setBounds(530, 510, 170, 45);
        save.setVisible(true);
        add(save);
        lbl1.setBounds(700, 20, 200, 30);
        lbl1.setVisible(true);
        add(lbl1);
        scroll.setLocation(700, 50);
        scroll.setSize(280, 450);
        scroll.setVisible(true);
        add(scroll);
        jfile.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        model.clear();
                                        JFileChooser chooser = new JFileChooser();
                                        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                                        chooser.setAcceptAllFileFilterUsed(false);
                                        int ret = chooser.showDialog(null, "Указать директорию");
                                        boolean chck = true;
                                        if (ret == JFileChooser.APPROVE_OPTION) {
                                            File myFolder = new File((chooser.getSelectedFile()).toString());
                                            File[] files = myFolder.listFiles();
                                            for (File f : files) {
                                                if (f.isDirectory() == chck) {
                                                    File[] dfiles = f.listFiles();
                                                    for (File r : dfiles) {
                                                        model.addElement(r);
                                                    }
                                                } else {
                                                    model.addElement(f);
                                                }
                                            }
                                        }
                                    }
                                }
        );
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Данные успешно сохранены", "Сохранение", 1);
            }
        });
        pasre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (model.size() > 0) {
                        Parser.parseClass(clist.getSelectedValue().toString());
                        Parser.parseMethod(clist.getSelectedValue().toString());
                        recom.setText("В ходе анализа было выявлено, что класс Chooser необходимо начинать тестировать с метода CreateChooser()");
                        tbl.setVisible(true);
                        tbl1.setVisible(false);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(TesterGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        parseAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int size = model.getSize();
                for (int i = 0; i < size; i++) {
                    try {
                        Parser.parseClass(model.get(i).toString());
                        Parser.parseMethod(model.get(i).toString());
                    } catch (IOException ex) {
                        Logger.getLogger(TesterGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        TesterGUI app = new TesterGUI();
        app.setVisible(true);
    }
}
