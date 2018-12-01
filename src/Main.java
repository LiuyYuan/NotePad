import javafx.stage.FileChooser;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import java.io.File;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.io.*;
import java.awt.event.MouseEvent;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main extends JFrame implements Printable {

    static String s = new String();
    private static String npath ;
    private static String filenameTemp;

    public static void init() {
        Main main = new Main();

        //FileNameExtensionFilter
        FileNameExtensionFilter filter=new FileNameExtensionFilter("*.txt","txt");

        //JPopupMenu
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem popupMenu_Undo = new JMenuItem("撤销(U)");
        JMenuItem popupMenu_Cut = new JMenuItem("剪切(T)");
        JMenuItem popupMenu_Copy = new JMenuItem("复制(C)");
        JMenuItem popupMenu_Paste = new JMenuItem("粘帖(P)");
        JMenuItem popupMenu_Delete = new JMenuItem("删除(D)");
        JMenuItem popupMenu_SelectAll = new JMenuItem("全选(A)");

        //Frame
        JFrame MainFrame = new JFrame();
        JTextArea jTextArea = new JTextArea();

        //JMenuBar
        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu1 = new JMenu("文件", true);
        JMenu jMenu2 = new JMenu("帮助", true);
        JMenuItem item1 = new JMenuItem("新建");//ok
        JMenuItem item2 = new JMenuItem("打开");//ok
        JMenuItem item3 = new JMenuItem("保存");//ok
        JMenuItem item4 = new JMenuItem("另存为");
        JMenuItem item5 = new JMenuItem("打印");
        JMenuItem item6 = new JMenuItem("背景");
        JMenuItem item7 = new JMenuItem("查看帮助");


        JScrollPane jScrollPane = new JScrollPane();


        //JScrollPane
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        //字体
        //字体，字号
        jMenu1.setFont(new Font("宋体", Font.CENTER_BASELINE, 17));
        jMenu2.setFont(new Font("宋体", Font.CENTER_BASELINE, 17));
        item1.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
        item2.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
        item3.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
        item4.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
        item5.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
        item6.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
        item7.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
        jTextArea.setFont(new Font("宋体", Font.CENTER_BASELINE, 20));

        //添加

        jMenu1.add(item1);
        jMenu1.addSeparator();
        jMenu1.add(item2);
        jMenu1.add(item3);
        jMenu1.add(item4);
        jMenu1.addSeparator();
        jMenu1.add(item5);
        jMenu1.add(item6);

        jMenu2.add(item7);

        jMenuBar.add(jMenu1);
        jMenuBar.add(jMenu2);


        jPopupMenu.add(popupMenu_Undo);
        jPopupMenu.addSeparator();
        jPopupMenu.add(popupMenu_Cut);
        jPopupMenu.add(popupMenu_Copy);
        jPopupMenu.add(popupMenu_Paste);
        jPopupMenu.add(popupMenu_Delete);
        jPopupMenu.addSeparator();
        jPopupMenu.add(popupMenu_SelectAll);


        MainFrame.add(jTextArea);
        MainFrame.add(jScrollPane);
        MainFrame.add(jScrollPane);
        jScrollPane.setViewportView(jTextArea);


        //TextArea
        jTextArea.setWrapStyleWord(true);//设置单词在一行不足容纳时换行
        jTextArea.setLineWrap(true);//设置文本编辑区自动换行默认为true,即会"自动换行"

        //Frame参数
        MainFrame.setTitle("记事本");
        MainFrame.setVisible(true);
        MainFrame.setSize(1319, 850);
        MainFrame.setLocation(320, 100);
        MainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MainFrame.setJMenuBar(jMenuBar);

        //禁用
        item5.setEnabled(false);

        //监听
        //item1
        item1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser1 = new JFileChooser();
                jFileChooser1.setFileFilter(filter);
                jFileChooser1.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);// 设置能选择文件夹和文件
                jFileChooser1.setMultiSelectionEnabled(false);// 禁止选择多个文件夹
                int result = jFileChooser1.showDialog(null,"新建");// 打开文件选择器
                if (result == JFileChooser.APPROVE_OPTION) {
                    filenameTemp = jFileChooser1.getSelectedFile().getPath();
                    try {
                        // 文件路径
                        File file = new File(filenameTemp);
                        // 将文件读入输入流
                        file.createNewFile();
                        FileInputStream fis = new FileInputStream(file);
                        InputStreamReader isr = new InputStreamReader(fis);
                        BufferedReader br = new BufferedReader(isr);
                        StringBuffer buf = new StringBuffer();
                    } catch (FileNotFoundException eee) {
                        System.out.println("Error: Specific file not found!");
                    } catch (IOException fff) {
                        System.out.println("Error: Failed to read file!");
                    } catch (Exception ggg) {
                        System.out.println("Error: " + ggg);
                    }
                }
            }
        });

        //item2
        item2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser2 = new JFileChooser();
                jFileChooser2.setFileFilter(filter);
                jFileChooser2.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);// 设置能选择文件夹和文件
                jFileChooser2.setMultiSelectionEnabled(false);// 禁止选择多个文件夹
                int result = jFileChooser2.showDialog(null, null);// 打开文件选择器
                if(result == JFileChooser.APPROVE_OPTION){
                    File[] listFiles = jFileChooser2.getSelectedFile().listFiles(new java.io.FileFilter(){
                        public boolean accept(File pathname){
                            if(pathname.getName().endsWith("txt")){
                                return true;
                            }else {
                                return false;
                            }
                        }
                    });
                }
                File selectedFile = jFileChooser2.getSelectedFile();  //取得选中的文件
                s = selectedFile.getPath();   //取得路径
                //尝试读入文件
                try {
                    //读入文件
                    FileReader fReader = new FileReader(s);
                    StringBuilder passage = new StringBuilder();
                    BufferedReader bReader = new BufferedReader(fReader);
                    String line = "";
                    while ((line = bReader.readLine()) != null) {
                        passage.append(line);
                        passage.append("\n");
                    }
                    jTextArea.setText(new String(passage.toString().getBytes(fReader.getEncoding())));
                    //搜的
                    jTextArea.paintImmediately(jTextArea.getBounds());
                } catch (FileNotFoundException eee) {
                    System.out.println("Error: Specific file not found!");
                } catch (IOException fff) {
                    System.out.println("Error: Failed to read file!");
                } catch (Exception ggg) {
                    System.out.println("Error: " + ggg);
                }

            }
        });

        //item3
        item3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    OutputStream oStream = new FileOutputStream(s);
                    oStream.write(jTextArea.getText().getBytes(StandardCharsets.UTF_8));
                } catch (FileNotFoundException eee) {
                    System.out.println("Error: Specific file not found!");
                } catch (IOException fff) {
                    System.out.println("Error: Failed to read file!");
                } catch (Exception ggg) {
                    System.out.println("Error: " + ggg);
                }
            }
        });

        //item4
        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser3 = new JFileChooser();
                jFileChooser3.setFileFilter(filter);
                jFileChooser3.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);// 设置能选择文件夹和文件
                jFileChooser3.setMultiSelectionEnabled(false);// 禁止选择多个文件夹
                int result = jFileChooser3.showDialog(null, "另存为");// 打开文件选择器
                String a = jFileChooser3.getSelectedFile().getPath();
                try {
                    jFileChooser3.getSelectedFile().createNewFile();
                    OutputStream oStream = new FileOutputStream(a);
                    oStream.write(jTextArea.getText().getBytes(StandardCharsets.UTF_8));
                } catch (FileNotFoundException eee) {
                    System.out.println("Error: Specific file not found!");
                } catch (IOException fff) {
                    System.out.println("Error: Failed to read file!");
                } catch (Exception ggg) {
                    System.out.println("Error: " + ggg);
                }
            }
        });

        //item5
        item5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPrintable(main);
                try {
                    job.print();
                } catch (PrinterException eeeeee) {
                    Dialog.errDialog(MainFrame, "错误", "无法执行打印请求，出现未知错误");
                }
            }
        });

        //item6
        item6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = new JColorChooser().showDialog(MainFrame, "选取颜色", null);
                if (color == null) {
                    return;
                }
                jTextArea.setBackground(color);

            }
        });

        //item7
        item7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dialog.infoDialog(MainFrame,"帮助","就我这个水平的垃圾程序员能写个什么垃圾帮助，瞎鸡儿看吧哈哈哈哈哈哈");
            }
        });

        //JTextArea
            jTextArea.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    {
                        if (e.isPopupTrigger())//返回此鼠标事件是否为该平台的弹出菜单触发事件
                        {
                            jPopupMenu.show(e.getComponent(), e.getX(), e.getY());//在组件调用者的坐标空间中的位置 X、Y 显示弹出菜单
                        }
                    }
                }
            });
        }
        public static void main(String[] args){

            init();

        }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        return 0;
    }
}
class Dialog extends JOptionPane {
    public static void warnDialog(Component parent, String title, String content) {
        JOptionPane.showMessageDialog(parent, content, title, JOptionPane.WARNING_MESSAGE);
    }
    public static void errDialog(Component parent, String title, String content) {
        JOptionPane.showMessageDialog(parent, content, title, JOptionPane.ERROR_MESSAGE);
    }
    public static void infoDialog(Component parent, String title, String content) {
        JOptionPane.showMessageDialog(parent, content, title, JOptionPane.INFORMATION_MESSAGE);
    }
}

//Google的类，后来发现了一波骚操作，就把它入土了。
//Frame
