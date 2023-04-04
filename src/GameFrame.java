import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GameFrame extends JFrame {

    public GameFrame() {
        /* 1.设置窗口布局 */
        this.setSize(633, 423);
        this.setTitle("欢乐斗地主");
        // 设置居中
        this.setLocationRelativeTo(null);
        // 设置关闭模式，关闭窗口时隐藏
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 修改缺省布局，使图片按照坐标显示
        this.setLayout(null);

        this.setVisible(true);

    }
}
