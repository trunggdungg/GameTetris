package tetris;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GameArea extends JPanel {

    private int gridRows; // (số dòng), chiều cao của lưới  
    private int gridColumns;// (số cột), chiều rộng của lưới
    private int gridCellSize;// kích thước ô lưới

    private TetrisBlock block;

    public GameArea(JPanel placeholder, int columns) {

        placeholder.setVisible(false);
        this.setBounds(placeholder.getBounds());// khu vực game, kích thước  bằng với kích thước của placeholder.
        this.setBackground(placeholder.getBackground());// backgr
        this.setBorder(placeholder.getBorder());

        gridColumns = columns;
        gridCellSize = this.getBounds().width / gridColumns; // kích thước của 1 ô lưới = chiều rộng/ số cột
        gridRows = this.getBounds().height / gridCellSize;// tính được số ô theo chiều dọc

        spawBlock();

    }
        // hàm vẽ  các khối, kích thước, màu
    public void spawBlock() {
        block = new TetrisBlock(new int[][]{{1, 0}, {1, 0}, {1, 1}}, Color.red);
    }
// hàm di chuyển các khối
    public void moveBlockDown() {
        block.moveDown();
      repaint();
    }
// vẽ hình
    private void drawBlock(Graphics g) {
        int h = block.getHeight();
        int w = block.getWidth();
        Color c = block.getColor();
        int shape[][] = block.getShape();

        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                if (shape[row][col] == 1) {
                    // tính toạ độ x , y
                    int x = (block.getX() + col) * gridCellSize;
                    int y = (block.getY() + row) * gridCellSize;
                    //
                    g.setColor(c);
                    g.fillRect(x, y, gridCellSize, gridCellSize);
                    g.setColor(Color.black);
                    g.drawRect(x, y, gridCellSize, gridCellSize);
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        for (int y = 0; y < gridRows; y++) {//  vòng này vẽ ô lưới theo chiều dọc
//            for (int x = 0; x < gridColumns; x++) {
//                g.drawRect(x * gridCellSize, y * gridCellSize, gridCellSize, gridCellSize);//  vẽ kích thước ô lưới theo hàng 
//            }
//        }
        drawBlock(g);
    }

}
