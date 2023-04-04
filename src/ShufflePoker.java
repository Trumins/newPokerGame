import java.util.*;


public class ShufflePoker{

    static String[] color = {"红桃", "方片", "梅花", "黑桃"};
    static String[] number = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "k", "A", "2"};
    static ArrayList<String> allPoker = new ArrayList<String>();
    static List<Integer> index = new ArrayList<Integer>();
    List<Integer> lord = new ArrayList<Integer>();
    List<Integer> player1 = new ArrayList<Integer>();
    List<Integer> player2 = new ArrayList<Integer>();
    List<Integer> player3 = new ArrayList<Integer>();

    //洗牌只要一次，选择静态函数，随类的加载而加载，每个类只有一个
    // public static void preparePoker(){
    static{
        //以下将牌的花色与数字连接
        StringBuilder sb = new StringBuilder();
        allPoker.add("小王");
        allPoker.add("大王");
        for (int index2 = number.length - 1; index2 >= 0; index2--) {
            for (int index1 = color.length - 1; index1 >= 0; index1--) {
                sb.append(color[index1]);
                sb.append(number[index2]);
                allPoker.add(sb.toString());
                sb.delete(0, 4);
            }
        }

        //以下初始化牌的序号
        for(int i = 0; i < 54; i++){
            index.add(i);
        }

    }//end of preparePoker()


    //构造函数
    ShufflePoker(){

        // preparePoker();
        
        //打乱牌序，用一个序号数组对应牌，打乱数组后分发数组即可按照索引找牌
        Collections.shuffle(index);
        
        //发牌
        for(int i = 0; i < 54; i++){
            if(i < 3){
                lord.add(index.get(i));
            }
            else if(i % 3 == 0){
                player1.add(index.get(i));
            }
            else if(i % 3 == 1){
                player2.add(index.get(i));
            }
            else{
                player3.add(index.get(i));
            }
        }//end of 发牌

        
        //给牌的序号排序
        Collections.sort(lord);
        Collections.sort(player1);
        Collections.sort(player2);
        Collections.sort(player3);
        

        
        System.out.println("地主");
        for(int i = 0; i < lord.size(); i++){
            System.out.print(allPoker.get(lord.get(i)));
            System.out.print(" ");
        }
        System.out.println();
        System.out.println("玩家1");
        for(int i = 0; i < player1.size(); i++){
            System.out.print(allPoker.get(player1.get(i)));
            System.out.print(" ");
        }
        System.out.println();
        System.out.println("玩家2");
        for(int i = 0; i < player2.size(); i++){
            System.out.print(allPoker.get(player2.get(i)));
            System.out.print(" ");
        }
        System.out.println();
        System.out.println("玩家3");
        for(int i = 0; i < player3.size(); i++){
            System.out.print(allPoker.get(player3.get(i)));
            System.out.print(" ");
        }
        System.out.println();
    }//end ofShufflePoker() 

}//end of define ShufflePoker