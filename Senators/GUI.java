import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GUI {
    List<Senator> senators;
    JFrame frame;
    JLabel image = new JLabel();
    JLabel info = new JLabel();
    
    public GUI(InputStream in) throws IOException {
        this.senators = SenatorListReader.readSenatorList(in);
        this.frame = new JFrame("Senators v2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
        GridLayout buttonLayout = new GridLayout(0, 1);
        BorderLayout superLayout = new BorderLayout();
        BorderLayout infoLayout = new BorderLayout();
        
        JPanel senButtons = new JPanel(buttonLayout);
        JPanel infoScreen = new JPanel(infoLayout);
        JPanel superPanel = new JPanel(superLayout);
        
        infoScreen.setPreferredSize(new Dimension(600, 800));
        frame.setContentPane(superPanel);
        JScrollPane scroller = new JScrollPane(senButtons, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        for (Senator senator : senators) {
            JButton button = new JButton(senator.getFullName());
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setSize(500, 200);
            button.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent e) {
                    String infoString = String.format("%s (%s-%s)", senator.getFullName(), senator.getParty(), senator.getState());
                    info.setText(infoString);
                    ImageIcon icon = null;
                    ImageIcon errorIcon = null;
                    try {
                        ImageIcon tempIcon = new ImageIcon(ImageIO.read(new URL("https://png.pngtree.com/png-vector/20210227/ourlarge/pngtree-error-404-glitch-effect-png-image_2943478.jpg")));
                        errorIcon = scaledIcon(tempIcon, 500, 500);
                    } catch (IOException ex3) {   
                    }
                    if (senator.getPhotoURL() == null) {
                        icon = errorIcon;
                    } else {
                        try {
                            ImageIcon tempIcon = new ImageIcon(ImageIO.read(senator.getPhotoURL()));
                            double ratio;
                            if (tempIcon.getIconHeight() > 500 || tempIcon.getIconHeight() < 400) {
                                ratio = tempIcon.getIconHeight() / 500.0;
                                int newWidth = (int)(tempIcon.getIconWidth() / ratio);
                                tempIcon = scaledIcon(tempIcon, newWidth, 500);
                            }
                            icon = tempIcon;
                        } catch (IOException ex) {
                            icon = errorIcon;
                        } catch (NullPointerException ex2) {
                            icon = errorIcon;
                        }
                    }
                    image.setIcon(icon);
                }   
            } );
            senButtons.add(button);
        }
        
        infoScreen.add(image, BorderLayout.NORTH);
        infoScreen.add(info, BorderLayout.CENTER);
        superPanel.add(infoScreen, BorderLayout.CENTER);
        superPanel.add(scroller, BorderLayout.WEST);
    }
    
    public void display() {
        frame.pack();
        frame.setVisible(true);
    }
    
    private ImageIcon scaledIcon(ImageIcon icon, int w, int h) {
        Image tempImage = icon.getImage();
        Image scaledImage = tempImage.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
