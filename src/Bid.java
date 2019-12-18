public class Bid implements Comparable<Bid> {
    private Integer count;                  // Количество акций, которые требуется купить
    private Double prise;                   // Цена, по которой необходимо купить акции
    private Integer id;                     // Идентификатор
    private static Integer counter = 0;

    // Конструктор класса.
    Bid(int count, double prise) {
        this.count = new Integer(count);
        this.prise = new Double(prise);
        Bid.counter++;
        this.id = counter;
    }

    // Функции доступа к полям.
    public Integer getCount() {
        return count;
    }

    public Double getPrise() {
        return prise;
    }

    // Функция установки значения поля count.
    public void setCount(int count) {
        this.count = count;
    }

    // Компаратор класса. В начало ставится элемент с наибольшей ценой.
    @Override
    public int compareTo(Bid bid) {
        if (!(this.prise.equals(bid.prise))) {
            return -(this.prise.compareTo(bid.prise));
        } else if (!(this.count.equals(bid.count))) {
            return -(this.count.compareTo(bid.count));
        } else {
            return this.id.compareTo(bid.id);
        }

    }
}
