public class Ask implements Comparable<Ask> {
    private Integer count;                  // Количество акций, выставленных на продажу
    private Double prise;                   // Цена, по которой продаются акции
    private Integer id;                     // Идентефикатор
    private static Integer counter = 0;

    // Конструктор класса.
    Ask(int count, double prise) {
        this.count = new Integer(count);
        this.prise = new Double(prise);
        Ask.counter++;
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
    public int compareTo(Ask obj) {
        if (!this.prise.equals(obj.prise)) {
            return -this.prise.compareTo(obj.prise);
        } else if (!this.count.equals(obj.count)) {
            return -this.count.compareTo(obj.count);
        } else if (!this.id.equals(obj.id)) {
            return this.id.compareTo(obj.id);
        } else {
            return 0;
        }

    }
}
