 package live;
 
 import java.awt.Color;
 import java.awt.EventQueue;
 import java.awt.Font;
 import java.awt.SystemColor;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.util.Date;
 import javax.swing.JButton;
 import javax.swing.JFrame;
 import javax.swing.JLabel;
 import javax.swing.JPanel;
 import javax.swing.JTextField;
 import javax.swing.Timer;
 import javax.swing.border.BevelBorder;
 import javax.swing.border.TitledBorder;
 
 
 
 
 
 
 
 public class KeepLiveWindow
   extends JFrame
 {
   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   private JTextField textField;
   private KeepRunningThread hal;
   private Timer timer;
   
   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable() {
       public void run() {
         try {
            KeepLiveWindow frame = new KeepLiveWindow();
            frame.setVisible(true);
         } catch (Exception e) {
            e.printStackTrace();
         }
       }
     });
   }
   
 
 
   public KeepLiveWindow()
   {
      setTitle("Meow : System Aliver");
      setDefaultCloseOperation(3);
      setBounds(100, 100, 291, 132);
      contentPane = new JPanel();
      contentPane.setBackground(new Color(255, 228, 225));
      contentPane.setForeground(Color.BLACK);
      setContentPane(contentPane);
      contentPane.setLayout(null);
   
      JPanel panel = new JPanel();
      panel.setBackground(new Color(211, 211, 211));
      panel.setBorder(new TitledBorder(new BevelBorder(1, null, null, null, null), "Lock Preventer", 4, 2, null, new Color(0, 0, 0)));
     panel.setBounds(0, 0, 275, 79);
     contentPane.add(panel);
     panel.setLayout(null);
  
     final JButton btnStart = new JButton("Start");
     btnStart.setFont(new Font("Tahoma", 0, 11));
     btnStart.setBounds(119, 48, 68, 23);
     panel.add(btnStart);
     btnStart.setToolTipText("Start system aliver..");
     btnStart.setBackground(SystemColor.activeCaptionBorder);
  
     final JButton btnStop = new JButton("Stop");
     btnStop.setEnabled(false);
     btnStop.setFont(new Font("Tahoma", 0, 11));
     btnStop.setBounds(197, 48, 68, 23);
     panel.add(btnStop);
     btnStop.setToolTipText("Stop system aliver..");
     btnStop.setBackground(SystemColor.activeCaptionBorder);
  
     JLabel lblWatch = new JLabel("Timmer:");
     lblWatch.setFont(new Font("Tahoma", 0, 11));
     lblWatch.setBounds(10, 28, 46, 14);
     panel.add(lblWatch);
     lblWatch.setForeground(Color.BLACK);
     lblWatch.setLabelFor(textField);
  
     textField = new JTextField();
     textField.setBounds(57, 24, 208, 21);
     panel.add(textField);
     textField.setFont(new Font("Tahoma", 1, 12));
     textField.setEditable(false);
     textField.setColumns(10);
  
     JLabel lblNewLabel = new JLabel("Developed By: SuKu (Copyright\u00A9, All rights reserved)");
     lblNewLabel.setBounds(0, 80, 275, 11);
     contentPane.add(lblNewLabel);
     lblNewLabel.setVerticalAlignment(1);
     lblNewLabel.setHorizontalAlignment(0);
     lblNewLabel.setFont(new Font("Tahoma", 0, 9));
     lblNewLabel.putClientProperty("html", null);
  

     btnStop.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
         btnStop.setEnabled(false);
         btnStart.setEnabled(true);
         hal.shutdown();
         hal = null;
         timer.stop();
         textField.setText("");
    }
    

     });
     btnStart.addActionListener(new ActionListener()
     {
       public void actionPerformed(ActionEvent e) {
         btnStart.setEnabled(false);
         btnStop.setEnabled(true);
         if (hal == null) {
           hal = new KeepRunningThread();
           Thread t = new Thread(hal);
           t.start();
           timerActionListener();
         }
       }
       
       private void timerActionListener()
       {
         int timeDelay = 1000;
         
         ActionListener time = new ActionListener()
         {
           public void actionPerformed(ActionEvent evt)
           {
             textField.setText(new Date().toString());
 
 
           }
           
 
 
 
         };
         timer = new Timer(timeDelay, time);
         timer.start();
       }
     });
   }
 }

