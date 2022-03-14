public class IndeksertListe<T> extends Lenkeliste<T> {
    
    public void leggTil(int pos, T x){
        if(pos >= 0 && pos <= stoerrelse()){
            Node nyNode = new Node(x);
            storrelse ++;
            if(forste == null){
                forste = nyNode;
                return;
            }
            Node tmp = forste;
            for(int i = 0; i < (pos - 1); i++){
                tmp = tmp.neste;
            }
            if(pos == 0){
                nyNode.neste = forste;
                forste.forrige = nyNode;
                forste = nyNode;
                return;
            }
            else{
                nyNode.forrige = tmp;
            }
            if(tmp.neste != null){
                nyNode.neste = tmp.neste;
            }
            else{
                nyNode.neste = null; 
            }
            tmp.neste = nyNode;
            tmp = tmp.neste.neste;
            if(tmp != null){
                tmp.forrige = nyNode;
            }            
        }
        else{
            throw new UgyldigListeindeks(pos);
        }
    }

    public void sett(int pos, T x){
        if(pos >= 0 && pos < stoerrelse()){
            Node tmp = forste;
            for(int i = 0; i < pos; i++){
                tmp = tmp.neste;
            }
            tmp.x = x;
        }
        else{
            throw new UgyldigListeindeks(pos); 
        }       
    }

    public T hent(int pos){
        if(pos >= 0 && pos < stoerrelse()){
            Node tmp = forste;
            for(int i = 0; i < pos; i++){
                tmp = tmp.neste;
            }
            return tmp.x;
        }
        else{
            throw new UgyldigListeindeks(pos); 
        }
    }

    public T fjern(int pos) {
        if (pos >= stoerrelse() || pos < 0 || forste == null) {
            throw new UgyldigListeindeks(pos);
        }
        Node tmp = forste;
        Node forrige = null;
        storrelse --;
        
        if (pos == 0 && tmp != null) {
            forste = tmp.neste;
            return tmp.x;
        }
        int teller = 0;
        while (tmp != null) {
            if (teller == pos) {
                forrige.neste = tmp.neste;
                return tmp.x;
        } else {
            forrige = tmp;
            tmp = tmp.neste;
            teller ++;
        }
        }
        if (tmp == null) {
            System.out.println(pos + " element ikke funnet.");
        }
        return null;
    }
}