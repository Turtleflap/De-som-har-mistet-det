public class Koe<T> extends Lenkeliste<T>{
    @Override
    public void leggTil(T x){
        Node nyNode = new Node(x);
        storrelse ++;
        if(siste == null){
            forste = nyNode;
            siste = nyNode;
            return;
        }
        siste.neste = nyNode;
        nyNode.forrige = siste;
        siste = nyNode;
    }
    @Override
    public T fjern(){
        if(forste == null){
            throw new UgyldigListeindeks(-1);
        }
        if(forste != null){
            storrelse --;
            Node tmp = forste;
            forste = forste.neste;
            if(forste != null){
                forste.forrige = null;
            }
            else{
                siste = null;
            }
            return tmp.x;
        }
        return null;
    }
}
