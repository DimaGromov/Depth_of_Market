import java.util.*;

public class Depth_of_Market {
    public static void main(String[] args) {
        AskTree askTree = new AskTree();
        BidTree bidTree = new BidTree();

        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.print("Ввод:");
            String str = scanner.next();

            switch (str){
                case "1":
                    askTree.createNewAsk();
                    System.out.println("=========");
                    System.out.println(askTree);
                    System.out.println("---------");
                    System.out.println(bidTree);
                    System.out.println("=========");
                    break;
                case "2":
                    bidTree.createBid();
                    System.out.println(askTree);
                    System.out.println("=========");
                    System.out.println(bidTree);
                    break;
                case "3":
                    System.out.println("\f");
                default:
                    System.out.println("Повторите ввод.");
                    break;
            }
        }

    }
}
