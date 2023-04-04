import java.io.IOException;

public class Entrance {

    static LoginFrame lf;

    static {
        try {
            lf = new LoginFrame();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static RegisterFrame rf;

    static {
        try {
            rf = new RegisterFrame();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static GameFrame gf = new GameFrame();

    public static void main(String[] args) {

        // 调用登录界面，内部调用注册界面
        // 登录完成后，调用主界面开始游戏
        // 存储用户信息，键为用户名，值为密码

        lf.setVisible(true);
        rf.setVisible(false);
        gf.setVisible(false);
    }

}