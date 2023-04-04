import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Tools {
    
    static boolean findInfo(String dstStr, BufferedReader br, int i) throws IOException {
        // 文件不存在，由于事先新建了一个文件，不存在的情况不会出现
        if (br == null) {
            return false;
        }

        // 读取文件，判断是否存在
        String line = null;
        while ((line = br.readLine()) != null) {
            if (dstStr.equals(i == 0 ? line.split(" ")[i] : line)) {
                return true;
            }
        }

        return false;
    }

    static void getUserInfo(HashMap<String, String> userMap) throws IOException {
        List<String> lines = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("userInfo.txt"));
        String str = null;
        while((str = br.readLine()) != null){
            lines.add(str);
        }
        br.close();
        // 遍历，分割，存储
        for (String tmp : lines) {
            String[] split = tmp.split("&");
            userMap.put(split[0].split("=")[1], split[1].split("=")[1]);
        }
        System.out.println(userMap);

    }

}
