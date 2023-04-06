// import java.util.Scanner;
import java.util.Arrays;

class Card {
    enum Suit {
        hearts, spades, clubs, diamonds, King;
    }
    
    static final int cardsNum = 54;
    public int number;
    public Suit suit;

    Card(int num, int suit) {
        number = num;
        this.suit = Suit.values()[suit];
    }
}

public class CurrentCard {

    public static Card[] cards;
    public int currentCardCount;
    public final int MaxCardCount = 20;
    public int[] initCardIndex;
    public int[] currentCard;
    
    {//发牌：给予卡牌索引 + 卡牌数字转化
        int counter = 0;
        cards = new Card[Card.cardsNum];
        for(int i = 1; i <= (Card.cardsNum - 2) / 4; i++) {
            for(int j = 0; j < 4; j++) {
                cards[counter++] = new Card(i,j);
            }
        }
        cards[counter++] = new Card(14,5);
        cards[counter] = new Card(15,5);
    }

    CurrentCard(int[] cardIndex) {
        initCardIndex = cardIndex;
        currentCardCount = cardIndex.length;
        indexToCard();
    }
    private void indexToCard() {//索引转卡牌数字
        if(initCardIndex != null) {
            for(int i = 0; i < currentCardCount; i++) {
                currentCard[i] = cards[initCardIndex[i]].number;
            }
        }    
    }

    class choicedCard {
        public int[] choicedCard;
        public int choicedCardNumber;
        CardSet choicedCardType;

        private CardSet cardToSet() {
            switch(choicedCardNumber){
                case 1:
                    return CardSet.Single;
                break;
                case 2:

                break;
                case 3:
                break;
                case 4:
                break;
                case 5:
                break;
                case 6:
                break;
                case 7:
                break;
                case 8:
                break;
                case 9
            }
        }
        choicedCard(int[] choiCards) {
            choicedCard = choiCards;
            choicedCardNumber = choiCards.length;
            Arrays.sort(choicedCard);
        }
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
    },
    None{
        public boolean compare() {
            return false;
        }
    }
    public abstract boolean compare();  //同类型才能进行比较
}

