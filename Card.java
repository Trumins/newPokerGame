// import java.util.Scanner;
// import java.util.Arrays;

public class Card {
    public int currentCardCount;
    public final int MaxCardCount = 17;
    public char[] currentCard;
    class choicedCard {
        public char[] choicedCard;
        public int choicedCardNumber;
        CardSet choicedCardType;
    }
}

enum CardSet {
    Single{
        public boolean compare() {
            //...
            return true;
        }
    }, 
    Pair{
        public boolean compare() {
            //...
            return true;
        }
    }, 
    Trible{
        public boolean compare() {
            //...
            return true;
        }
    }, 
    straight{
        public boolean compare() {
            //...
            return true;
        }
    }, 
    Quartet{
        public boolean compare() {
            //...
            return true;
        }
    }, 
    Bomp{
        public boolean compare() {
            //...
            return true;
        }
    };
    public abstract boolean compare();  //同类型才能进行比较
}

