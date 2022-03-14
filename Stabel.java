public class Stabel<T> extends Lenkeliste<T>{;
    @Override
    public void leggTil(T x){
        Node nyNode = new Node(x);
        nyNode.neste = forste;
        forste = nyNode;
        storrelse++;
    }
}