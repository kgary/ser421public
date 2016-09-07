class Counter {
    int count = 0;

    public int incCounter() {return ++count;}
    public int getCounter() {return count;}

    public String toString() {return Integer.toString(count);}
}
