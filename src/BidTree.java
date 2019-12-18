import java.util.*;

public class BidTree {
    Scanner scanner = new Scanner(System.in);
    static SortedSet<Bid> bidTreeSet;

    // Конструктор, в котором создается TreeSet для хранения объектов Bid.
    BidTree() {
        bidTreeSet = new TreeSet<>();
    }

    // Функция доступа к первому элементу
    static Bid getFirst(){
        return bidTreeSet.first();
    }

    // Создание новой заявки на покупку.
    void createBid(){
        // Пользователь вводит данные в консоль
        System.out.println("\t=Новая заявка на покупку=");

        System.out.print("\tЦена: ");
        double prise = scanner.nextDouble();

        System.out.print("\tКолличество: ");
        int count = scanner.nextInt();

        count = bay(prise, count);

        if(count>0){
            if(count>0){
                if(!bidTreeSet.isEmpty()){
                    for (Bid bid:bidTreeSet) {
                        if(bid.getPrise().doubleValue() == prise){
                            bid.setCount(bid.getCount().intValue() + count);
                            return;
                        }
                    }
                    bidTreeSet.add(new Bid(count, prise));
                } else {
                    bidTreeSet.add(new Bid(count, prise));
                }
            }
        }
    }

    // Удаляет первый элемент в ThreeSet если он есть.
    static boolean removeFirst(){
        if(isNotEmpty()){
            bidTreeSet.remove(bidTreeSet.first());
            return true;
        }
        return false;
    }

    /*
        Метод проверяет есть ли в TreeSet элементы.
        - Если он пуст, возвращает false.
        - В обратном случае возвращает true.
    */
    static boolean isNotEmpty() {
        return !bidTreeSet.isEmpty();
    }


    private int bay(double prise, int count) {
        String dealInfo = "Сделка состоялась.\nДетали сделки:\n"; // Сообщение, информирующее о количестве и цене проданных акций.
        int bayCount = 0;   // Количество проданных акций
        double bayPrise = 0.0;  // Цена проданных акций

        if (AskTree.isNotEmpty() && AskTree.getLast().getPrise().doubleValue() < prise) {
            Iterator<Ask> iterator = AskTree.getIterator();
            List<Ask> list = new ArrayList<>();
            while (iterator.hasNext()) {
                Ask current = iterator.next();
                if (current.getPrise().doubleValue() < prise) {
                    if (current.getCount().intValue() > count) {
                        bayCount = count;
                        bayPrise = prise;
                        current.setCount(current.getCount() - count);
                        dealInfo += "Проданно " + bayCount + " по цене " + bayPrise;
                        count = 0;
                    } else {
                        bayCount = current.getCount();
                        bayPrise = current.getPrise();
                        count -= bayCount;
                        dealInfo += "Проданно " + bayCount + " по цене " + bayPrise + "\n";
                        list.add(current);
                    }
                }
            }
            for (Ask ask : list) {
                AskTree.remove(ask);
            }
            System.out.println(dealInfo);

        }
        return count;
    }

    @Override
    public String toString() {
        String str = "";
        for (Bid bid: bidTreeSet) {
            str+= bid.getPrise() + "\t" + bid.getCount() + "\n";
        }
        return str;
    }
}
