import java.util.Iterator;

class Lenkeliste<T> implements Liste<T>{
    protected Node forste, siste;
    protected int storrelse;

    class Node {
        Node neste, forrige;
        T x;
        public Node(T x){
            this.x = x;
            neste = null;
            forrige = null;
        }
    }

    class LenkelisteIterator implements Iterator<T> {
        private Node tmp = forste;
        public boolean hasNext() {
            if (tmp != null) {
                return true;
            } 
            return false;
        }
        public T next() {
            T x = tmp.x;
            tmp = tmp.neste;
            return x;
        }
    }

    @Override
    public int stoerrelse(){
        return storrelse;
    }
    @Override
    public void leggTil(T x){
        Node nyNode = new Node(x);
        storrelse++;
        if(forste == null){
            forste = nyNode;
        }
        else{
            Node tmp = forste;
            while(tmp.neste != null){
                tmp = tmp.neste;
            }
        tmp.neste = nyNode;
        }

    }
    @Override
    public T hent(){
        if(forste != null){
            return forste.x;
        }
        return null;
    }
    @Override
    public T fjern(){
        if(forste != null){
            Node tmp = forste;
            forste = forste.neste;
            storrelse --;
            return tmp.x;
        }
        throw new UgyldigListeindeks(-1);
    }
    @Override
    public String toString(){
        String string = "";
        Node tmp = forste;

        while(tmp != null){
            if(tmp.neste != null){
                string += tmp.x;
            }
            else{
                string += tmp.x;
            }
            tmp = tmp.neste;
        }
        return string;
    }
    @Override
    public Iterator<T> iterator() {
        // Oppretter og returnerer et nytt LenkeListeIterator-objekt
        return new LenkelisteIterator();
    }
}