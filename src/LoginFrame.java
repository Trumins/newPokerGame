import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class LoginFrame extends JFrame implements MouseListener {

    ImageIcon loginIcon = new ImageIcon("image/login/login.png");
    JButton loginButton = new JButton(loginIcon);

    ImageIcon registerIcon = new ImageIcon("image/login/register.png");
    JButton registerButton = new JButton(registerIcon);

    JTextField usernameInput = new JTextField();
    JPasswordField passwordInput = new JPasswordField();
    JTextField checkCodeInput = new JTextField();

    HashMap<String, String> userMap = new HashMap<>();

    // 方便获取容器
    Container ct = this.getContentPane();

    public LoginFrame() throws IOException {

        Tools.getUserInfo(userMap);

        /* 1.设置窗口布局 */
        this.setSize(633, 423);
        this.setTitle("登录与注册");
        // 设置居中
        this.setLocationRelativeTo(null);
        // 设置关闭模式，关闭窗口时隐藏
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 修改缺省布局，使图片按照坐标显示
        this.setLayout(null);

        /* 2.登录按钮 */
        // 设置图片的位置
        loginButton.setBounds(120, 320, loginIcon.getIconWidth(), loginIcon.getIconHeight());
        // 去除图片边框
        loginButton.setBorderPainted(false);
        // 去除图片背景
        loginButton.setContentAreaFilled(false);
        // 添加到容器中
        ct.add(loginButton);

        // 3.注册按钮
        registerButton.setBounds(350, 320, registerIcon.getIconWidth(), registerIcon.getIconHeight());
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        ct.add(registerButton);

        // 4.1 添加用户名输入框
        usernameInput.setBounds(260, 55, 160, 30);
        ct.add(usernameInput);

        // 4.2添加用户名文字
        Font characterFont = new Font(null, 1, 16);
        JLabel usernameText = new JLabel("用户名");
        usernameText.setForeground(Color.white);
        usernameText.setFont(characterFont);
        usernameText.setBounds(200, 55, 55, 22);
        ct.add(usernameText);

        // 5.1 添加密码输入框
        passwordInput.setColumns(10);
        passwordInput.setBounds(260, 100, 160, 30);
        ct.add(passwordInput);

        // 5.2 添加密码文字
        JLabel passwordText = new JLabel("密码");
        passwordText.setForeground(Color.white);
        passwordText.setFont(characterFont);
        passwordText.setBounds(200, 100, 55, 22);
        ct.add(passwordText);

        // 6.1添加验证码输入框
        checkCodeInput.setBounds(260, 150, 160, 30);
        ct.add(checkCodeInput);

        // 6.2添加验证码文字
        JLabel checkCodeText = new JLabel("验证码");
        checkCodeText.setForeground(Color.white);
        checkCodeText.setFont(characterFont);
        checkCodeText.setBounds(200, 150, 55, 22);
        ct.add(checkCodeText);

        // 6.3 添加验证码图片
        ImageIcon checkCodeIcon = new ImageIcon("image/login/login.png");
        JLabel checkCodeLabel = new JLabel(checkCodeIcon);
        checkCodeLabel.setBounds(260, 180, checkCodeIcon.getIconWidth(), checkCodeIcon.getIconHeight());
        ct.add(checkCodeLabel);

        // 背景图片
        ImageIcon bkgIcon = new ImageIcon("image/login/background.png");
        JLabel bkgLabel = new JLabel(bkgIcon);
        bkgLabel.setBounds(0, 0, bkgIcon.getIconWidth(), bkgIcon.getIconHeight());
        ct.add(bkgLabel);

        /*
         * 登录注册按钮添加点击事件
         */
        loginButton.addMouseListener(this);
        registerButton.addMouseListener(this);

    }

    // 登录成功则调用游戏界面
    private boolean login() throws IOException {
        // 获取用户名和密码
        String nameStr = usernameInput.getText();
        String pwdStr = String.valueOf(passwordInput.getPassword());

        // 先判断用户名以及密码是否空
        if (nameStr.equals("") || pwdStr.equals("")) {
            JOptionPane.showMessageDialog(null, "用户名或密码为空，请重新输入", "提示", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 先判断验证码
        String checkCode = checkCodeInput.getText();

        if (userMap.containsKey(nameStr) && userMap.get(nameStr).equals(pwdStr)) {
            JOptionPane.showMessageDialog(null, "登录成功", "提示", JOptionPane.PLAIN_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "用户名或密码错误", "提示", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // 登录注册按钮点击事件
    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        boolean success = false;
        if (source == loginButton) {
            try {
                success = login();
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                // 登录成功，隐藏当前窗口，新建游戏窗口
                if (success) {
                    Entrance.lf.setVisible(false);
                    Entrance.gf.setVisible(true);

                }
            }
        } else if (source == registerButton) {
            Entrance.lf.setVisible(false);
            Entrance.rf.setVisible(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == loginButton) {
            loginButton.setIcon(new ImageIcon("image/login/loginDown.png"));
        } else if (obj == registerButton) {
            registerButton.setIcon(new ImageIcon("image/login/registerDown.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == loginButton) {
            loginButton.setIcon(new ImageIcon("image/login/login.png"));
        } else if (obj == registerButton) {
            registerButton.setIcon(new ImageIcon("image/login/register.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // 啥事也不做
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // 啥事也不做
    }
}
