class Medicine {
    private String name;
    private double rate;
    private int quantity;

    public Medicine(String name, int quantity, double rate) {
        this.name = name;
        this.rate = rate;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Medicine: " +
                "\nName='" + name +
                "\nrate=" + rate +
                "\nquantity=" + quantity;
    }
}