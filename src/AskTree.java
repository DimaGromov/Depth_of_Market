import java.util.*;

public class AskTree {
    Scanner scanner = new Scanner(System.in);
    static SortedSet<Ask> askTreeSet;

    // Конструктор, в котором создается TreeSet для хранения объектов Ask.
    AskTree() {
        askTreeSet = new TreeSet<Ask>();
    }

    // Создание новой заявки на продажу.
    void createNewAsk() {
        // Пользователь вводит данные в консоль
        System.out.println("\t=Новая заявка на продажу=");

        System.out.print("\tЦена: ");
        double prise = scanner.nextDouble();

        System.out.print("\tКолличество: ");
        int count = scanner.nextInt();

        /*
            Необходимо проверить дерево покупателей.
            Если дерево не пустое, необходимо проверить цену первой заявки на покупку(Bid)(максимальная цена покупки).
            Если цена первой заявки на покупку ниже нашей, создается новая заявка на продажу.
            В противном случае продаем акции по цене в первом Bid.
                Если мы покрыли всю заявку на покупку, она удаляется.
                Если у нас остались еще акции на продажу, и цена в верхнем Bid выше нашей, продаем остальные акции, и тд.
         */
        count = bay(prise, count);


        /*
            Если метод bay() вернул значение отличное от 0, значит нужно создать новый элемент. Но сначало нужно проверить,
            есть ли в дереве элемент с такой же ценой, если есть, их поля count объединяются.
         */
        if(count>0){
            if(!askTreeSet.isEmpty()){

                for (Ask ask:askTreeSet) {
                    if(ask.getPrise().doubleValue() == prise){
                        ask.setCount(ask.getCount().intValue() + count);
                        return;
                    }
                }
                askTreeSet.add(new Ask(count, prise));
            } else {
                askTreeSet.add(new Ask(count, prise));
            }
        }
    }

    // Получение последнего элемента.
    static Ask getLast(){
        return askTreeSet.last();
    }

    // Получение первого элемента.
    static Ask getFirst(){
        return askTreeSet.first();
    }

    // Получение итератора.
    static Iterator<Ask> getIterator(){
        return askTreeSet.iterator();
    }

    /*
        Метод проверяет есть ли в TreeSet элементы.
        - Если он пуст, возвращает false.
        - В обратном случае возвращает true.
    */
    static boolean isNotEmpty() {
        return !askTreeSet.isEmpty();
    }

    /*
        Метод, осуществляющий продажу.
        1) Проверка есть ли в BidTree предложения о покупке, если нет, метод возвращает исходное значение count, т.е. продажа не состоялась.
        2) Если в BidTree есть предложения о покупке, смотрим цену верхнего предложения. Если она ниже, возвращаем исходное значение count.
        3) Если она выше нашей цены продажи, продаем акции. Если мы полностью покрыли заявку на покупку, заявка удаляется из BidTree, возвращаем 0.
        3) Если после продажи, у нас остаются акции, продаем оставшиеся в соответствии с пунктом 2.
        4) Если у нас остались акции, а верхняя цена в Bid ниже, возвращаем текущее значение count.
     */
    private int bay(double prise, int count) {
        String dealInfo = "Сделка состоялась.\nДетали сделки:\n"; // Сообщение, информирующее о количестве и цене проданных акций.
        int bayCount = 0;   // Количество проданных акций
        double bayPrise = 0.0;  // Цена проданных акций
        if (BidTree.isNotEmpty() && BidTree.getFirst().getPrise().doubleValue() >= prise) {
            while (BidTree.getFirst().getPrise() >= prise) {
                if (BidTree.getFirst().getCount() > count) {
                    bayPrise = BidTree.getFirst().getPrise();
                    int lost = BidTree.getFirst().getCount() - count;
                    BidTree.getFirst().setCount(lost);
                    bayCount = count;
                    dealInfo += "Проданно " + bayCount + " по цене " + bayPrise;
                    System.out.println(dealInfo);
                    return 0;
                } else if (BidTree.getFirst().getCount() <= count) {
                    bayCount = BidTree.getFirst().getCount();
                    count -= bayCount;
                    bayPrise = BidTree.getFirst().getPrise();
                    dealInfo += "Проданно " + bayCount + " по цене " + bayPrise + "\n";
                    BidTree.removeFirst();
                }
            }
            System.out.println(dealInfo);
            if (count > 0) {
                return count;
            } else {
                return 0;
            }
        } else return count;
    }

    // Удаляет первый элемент в ThreeSet если он есть.
    static boolean remove(Ask ask){
        if(isNotEmpty()){
            askTreeSet.remove(ask);
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        String str = "";
        Iterator<Ask> iterator = getIterator();
        while (iterator.hasNext()){
            Ask current = iterator.next();

            str += current.getPrise() +"\t" + current.getCount() + "\n";
        }
        return str;
    }
}
