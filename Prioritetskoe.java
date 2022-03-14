public class Prioritetskoe<T extends Comparable<T> > extends Lenkeliste <T>{

    @Override
    public   void  leggTil (T x){
        Node nyNode = new Node(x);
        Node tmp = forste;

        if(stoerrelse() == 0){
            forste = nyNode;
            siste = nyNode;
            storrelse++;
            return;
        }
        else if(tmp.x.compareTo(nyNode.x) > 0){
            nyNode.neste = tmp;
            tmp.forrige = nyNode;
            forste = nyNode;
            storrelse++;
            return;
        }
        while(tmp != null){
            if(tmp.neste == null){
                tmp.neste = nyNode;
                nyNode.forrige = tmp;
                siste = nyNode;
                storrelse++;
                return;
            } 
            else if(tmp.neste.x.compareTo(nyNode.x) > 0){
                nyNode.forrige = tmp;
                nyNode.neste = tmp.neste;
                tmp.neste.forrige = nyNode;
                tmp.neste = nyNode;
                storrelse++;
                return;
            } 
            else {
                tmp = tmp.neste;
            }
        }
    }

    @Override
    public T hent(){  
        if(stoerrelse() == 0){
            throw new UgyldigListeindeks(0);
        }
        return forste.x;
    }

    @Override
    public T fjern(){
        Node tmp = forste;

        if(stoerrelse() == 0){
            throw new UgyldigListeindeks(0);
        } 
        else if(forste == siste){
            forste = null;
            siste = null;
        }
        else {
            forste = forste.neste;
            if (forste != null){
                forste.forrige = null;
            }
        }
        storrelse--;
        return tmp.x;
    }
}