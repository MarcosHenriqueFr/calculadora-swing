package main.calc.visao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class BotaoRedondo extends JButton {
    private boolean mouseOver = false;
    private boolean mousePressed = false;

    public BotaoRedondo(String texto){
        super(texto);
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);

        MouseAdapter mouseListener = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(contains(e.getX(), e.getY())) {
                    mousePressed = true;
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mousePressed = false;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseOver = false;
                mousePressed = false;
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseOver = contains(e.getX(), e.getY());
                repaint();
            }
        };

        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }

    private int getDiametro(){
        return Math.min(getWidth(), getHeight());
    }

    @Override
    public Dimension getPreferredSize(){
        FontMetrics metrics = getGraphics().getFontMetrics(getFont());
        int diametroMin = 10 + Math.max(metrics.stringWidth(getText()), metrics.getHeight());
        return new Dimension(diametroMin, diametroMin);
    }

    @Override
    public boolean contains(int x, int y){
        int raio = getDiametro()/2;
        return Point2D.distance(x, y, getWidth()/2, getHeight()/2) < raio;
    }

    @Override
    public void paintComponent(Graphics g){

        int diametro = getDiametro();
        int raio = diametro/2;

        if(mousePressed){
            g.setColor(Color.LIGHT_GRAY);
        } else {
            g.setColor(Color.GRAY);
        }

        g.fillOval(getWidth()/2 - raio, getHeight()/2 - raio, diametro, diametro);

        if(mouseOver){
            g.setColor(Color.BLUE);
        } else {
            g.setColor(Color.BLACK);
        }

        g.drawOval(getWidth()/2 - raio, getHeight()/2 - raio, diametro, diametro);

        g.setColor(Color.WHITE);
        g.setFont(getFont());
        FontMetrics metrics = g.getFontMetrics(getFont());
        int stringLargura = metrics.stringWidth(getText());
        int stringAltura = metrics.getHeight();
        g.drawString(getText(), getWidth()/2 - stringLargura/2, getHeight()/2 + stringAltura/4);
    }
}
