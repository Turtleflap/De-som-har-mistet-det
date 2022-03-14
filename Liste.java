import java.util.Iterator;

interface Liste <T> extends Iterable<T>{
    int stoerrelse ();
    void leggTil (T x);
    T hent ();
    T fjern ();
}  
