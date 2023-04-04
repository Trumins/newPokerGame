import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
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

public class RegisterFrame extends JFrame implements MouseListener {

    ImageIcon registerIcon = new ImageIcon("image/login/register.png");
    JButton registerButton = new JButton(registerIcon);
    ImageIcon backIcon = new ImageIcon("image/login/back.png");
    JButton backButton = new JButton(backIcon);

    JTextField usernameInput = new JTextField();
    JPasswordField passwordInput = new JPasswordField();
    JPasswordField passwordReInput = new JPasswordField();

    HashMap<String, String> userMap = new HashMap<>();
    Container ct = this.getContentPane();

    public RegisterFrame() throws IOException {
        Tools.getUserInfo(userMap);

        /* 1.窗口布局 */
        this.setSize(633, 423);
        this.setTitle("登录与注册");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);

        // 2.1注册按钮
        registerButton.setBounds(280, 320, registerIcon.getIconWidth(), registerIcon.getIconHeight());
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        ct.add(registerButton);

        // 2.2返回按钮
        backButton.setBounds(20, 20, backIcon.getIconWidth(), backIcon.getIconHeight());
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        ct.add(backButton);

        // 3.1 添加用户名输入框
        usernameInput.setBounds(270, 55, 160, 30);
        ct.add(usernameInput);

        // 3.2添加用户名文字
        Font characterFont = new Font(null, 1, 16);
        JLabel usernameText = new JLabel("用户名");
        usernameText.setForeground(Color.white);
        usernameText.setFont(characterFont);
        usernameText.setBounds(200, 55, 55, 22);
        ct.add(usernameText);

        // 4.1 添加密码输入框
        passwordInput.setColumns(10);
        passwordInput.setBounds(270, 100, 160, 30);
        ct.add(passwordInput);

        // 4.2 添加密码文字
        JLabel passwordText = new JLabel("密码");
        passwordText.setForeground(Color.white);
        passwordText.setFont(characterFont);
        passwordText.setBounds(200, 100, 55, 22);
        ct.add(passwordText);

        // 5.1 添加确认密码输入框
        passwordReInput.setColumns(10);
        passwordReInput.setBounds(270, 145, 160, 30);
        ct.add(passwordReInput);

        // 5.2 添加确认密码文字
        JLabel passwordReText = new JLabel("确认密码");
        passwordReText.setForeground(Color.white);
        passwordReText.setFont(characterFont);
        passwordReText.setBounds(200, 145, 110, 22);
        ct.add(passwordReText);

        // 背景图片
        ImageIcon bkgIcon = new ImageIcon("image/login/background.png");
        JLabel bkgLabel = new JLabel(bkgIcon);
        bkgLabel.setBounds(0, 0, bkgIcon.getIconWidth(), bkgIcon.getIconHeight());
        ct.add(bkgLabel);

        registerButton.addMouseListener(this);
        backButton.addMouseListener(this);

    }

    // 返回注册是否成功
    private boolean register() throws IOException {
        String nameStr = usernameInput.getText();
        String pwdStr = String.valueOf(passwordInput.getPassword());
        String pwdReStr = String.valueOf(passwordReInput.getPassword());

        // 先判断用户名以及密码是否空
        if (nameStr.equals("") || pwdStr.equals("")) {
            JOptionPane.showMessageDialog(null, "用户名或密码为空", "提示", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!pwdStr.equals(pwdReStr)) {
            JOptionPane.showMessageDialog(null, "两次密码不一致", "提示", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // 用userMap查找是否存在，存在则提示用户名已存在
        System.out.println(nameStr);
        if (userMap.containsKey(nameStr)) {
            System.out.println("exist");
            JOptionPane.showMessageDialog(null, "用户名已存在", "提示", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // 对密码做强度判断
        if (!pwdStr.matches("\\S*(?=\\S{6,})(?=\\S*\\d)(?=\\S*[a-zA-Z])\\S*")) {
            JOptionPane.showMessageDialog(null, "密码至少6位，至少包含一个字母和一个数字", "提示", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // 将用户名及密码写入文件
        BufferedWriter bw = new BufferedWriter(new FileWriter("userInfo.txt", true));
        bw.write("username=" + nameStr + "&" + "password=" + pwdStr);
        bw.newLine();
        bw.close();
        JOptionPane.showMessageDialog(null, "注册成功！", "提示", JOptionPane.PLAIN_MESSAGE);
        return true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source == registerButton) {
            try {
                // 注册成功则关闭注册界面，打开登录界面
                if (register()) {
                    usernameInput.setText("");
                    passwordInput.setText("");
                    passwordReInput.setText("");

                    Entrance.rf.setVisible(false);
                    Tools.getUserInfo(Entrance.lf.userMap);
                    Tools.getUserInfo(Entrance.rf.userMap);
                    Entrance.lf.setVisible(true);
                } else {
                    passwordInput.setText("");
                    passwordReInput.setText("");
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else if (source == backButton) {
            usernameInput.setText("");
            passwordInput.setText("");
            passwordReInput.setText("");

            Entrance.rf.setVisible(false);
            Entrance.lf.setVisible(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object source = e.getSource();
        if (source == registerButton) {
            registerButton.setIcon(new ImageIcon("image/login/registerDown.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object source = e.getSource();
        if (source == registerButton) {
            registerButton.setIcon(new ImageIcon("image/login/register.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
