import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Propagation {

    private Rules rules;

    public Propagation(){
        this.rules = new Rules();
    }
    

    public Pair forwardChecking(ArrayList<ArrayList<ArrayList<String>>> domain) {
        Pair pair = new Pair(true, null);
        int n = domain.size();
        ArrayList<ArrayList<ArrayList<String>>> domainCopy = rules.copyDomain(domain);


        for (int i=0 ; i<n ; i++) {
            for (int j=0 ; j<n ; j++) {

                int x;
                int y;
                ArrayList<String> nodeDomain = domainCopy.get(i).get(j);

                    x = i;
                    y = j;
                    pair = rules.updateVariableDomain(domainCopy, x, y);
                    if(pair.flag()) {
                        domainCopy = pair.domain();
                    }
                    else{
                        break;
                    }

//                if(!hasVariable(nodeDomain)){
//                    x = i;
//                    y = j;
//                    pair = rules.updateVariableDomain(domainCopy, x, y);
//                    if(pair.flag()) {
//                        domainCopy = pair.domain();
//                    }
//                    else{
//                        break;
//                    }
//                }
            }
            if (!pair.flag()) break;
        }

        if (pair.flag()) {
            return new Pair(true, domainCopy);
        }
        else{
            return new Pair(false, null);
        }

    }

    public boolean hasVariable(ArrayList<String> domain) {

        for (String s : domain) {
            if (s.equals("w") || s.equals("b")) {
                return true;
            }
        }
        return false;
    }


    public Pair AC3(ArrayList<ArrayList<ArrayList<String>>> domain, Node variable){
        ArrayList<ArrayList<ArrayList<String>>> domainCopy = rules.copyDomain(domain);
        Queue<Node> queue = new LinkedList<>();
        queue.add(variable);
        Pair pair = new Pair(true);

        while(queue.size() > 0){
            System.out.println("a " + queue);
            Node var = queue.remove();
            pair = addNeighbors(domainCopy, var, queue);
            System.out.println("b " + queue);
            System.out.println();
            if(pair.domain()!=null){
                domainCopy = rules.copyDomain(pair.domain());
            }
            if(!pair.flag()){
                break;
            }
        }
        if(pair.flag()) {
            return new Pair(true, domainCopy);
        }
        else {
            return new Pair(false, true);
        }
    }



    public Pair addNeighbors(ArrayList<ArrayList<ArrayList<String>>> domain, Node node, Queue<Node> queue){
        int x = node.getX();
        int y = node.getY();
        ArrayList<ArrayList<ArrayList<String>>> domainCopy = rules.copyDomain(domain);


        if(x>=1) {
            ArrayList<String> beChecked = domain.get(x-1).get(y);

            if(!hasVariable(beChecked,"W") && !hasVariable(beChecked,"B")){
                Pair pair = domain_changed(domain, new Node(x-1,y));
                domainCopy = pair.domain();
                if(pair.flag()){
                    queue.add(new Node(x-1,y));
                }
                if (!pair.flag() && pair.isDomainNull()){
                    return pair;
                }
            }
        }


        if (x>=2) {
            ArrayList<String> beChecked = domain.get(x-2).get(y);

            if(!hasVariable(beChecked,"W") && !hasVariable(beChecked,"B")){
                Pair pair = domain_changed(domain, new Node(x-2, y));
                domainCopy = pair.domain();
                if(pair.flag()){
                    queue.add(new Node(x-2,y));
                }
                if (!pair.flag() && pair.isDomainNull()){
                    return pair;
                }
            }
        }

        if(x <= (domain.size()-2)){
            ArrayList<String> beChecked = domain.get(x+1).get(y);

            if(!hasVariable(beChecked,"W") && !hasVariable(beChecked,"B")){
                Pair pair = domain_changed(domainCopy, new Node(x+1,y));
                domainCopy = pair.domain();
                if(pair.flag()){
                    queue.add(new Node(x+1,y));
                }
                if(!pair.flag() && pair.isDomainNull()){
                    return pair;
                }
            }
        }

        if(x <= (domain.size()-3)) {
            ArrayList<String> beChecked = domain.get(x+2).get(y);

            if(!hasVariable(beChecked,"W") && !hasVariable(beChecked,"B")){
                Pair pair = domain_changed(domainCopy, new Node(x+2,y));
                domainCopy = pair.domain();
                if(pair.flag()){
                    queue.add(new Node(x+2,y));
                }
                if(!pair.flag() && pair.isDomainNull()){
                    return pair;
                }
            }
        }



        if(y>=1) {
            ArrayList<String> beChecked = domain.get(x).get(y-1);

            if(!hasVariable(beChecked,"W") && !hasVariable(beChecked,"B")){
                Pair pair = domain_changed(domainCopy, new Node(x,y-1));
                domainCopy = pair.domain();
                if(pair.flag()){
                    queue.add(new Node(x,y-1));
                }
                if(!pair.flag() && pair.isDomainNull()){
                    return pair;
                }
            }
        }

        if(y>=2) {
            ArrayList<String> beChecked = domain.get(x).get(y-2);

            if(!hasVariable(beChecked,"W") && !hasVariable(beChecked,"B")){
                Pair pair = domain_changed(domainCopy, new Node(x,y-2));
                domainCopy = pair.domain();
                if(pair.flag()){
                    queue.add(new Node(x,y-2));
                }
                if(!pair.flag() && pair.isDomainNull()){
                    return pair;
                }
            }
        }

        if(y<= domain.size()-2) {
            ArrayList<String> beChecked = domain.get(x).get(y+1);

            if(!hasVariable(beChecked,"W") && !hasVariable(beChecked,"B")){
                Pair pair = domain_changed(domainCopy, new Node(x,y+1));
                domainCopy = pair.domain();
                if(pair.flag()){
                    queue.add(new Node(x,y+1));
                }
                if(!pair.flag() && pair.isDomainNull()){
                    return pair;
                }
            }
        }


        if(y<= domain.size()-3){
            ArrayList<String> beChecked = domain.get(x).get(y+2);

            if(!hasVariable(beChecked,"W") && !hasVariable(beChecked,"B")){
                Pair pair = domain_changed(domainCopy, new Node(x,y+2));
                domainCopy = pair.domain();
                if(pair.flag()){
                    queue.add(new Node(x,y+2));
                }
                if(!pair.flag() && pair.isDomainNull()){
                    return pair;
                }
            }
        }



        return new Pair(true, domainCopy);

    }

    public Pair domain_changed(ArrayList<ArrayList<ArrayList<String>>> domain, Node node){
        int x = node.getX();
        int y = node.getY();
        ArrayList<String> nodeDomainBefore = domain.get(x).get(y);
//        System.out.println("be " + nodeDomainBefore);
        ArrayList<String> nodeDomainAfter ;

        Pair pair = rules.updateVariableDomain(domain,x,y);
        if(pair.flag()){
            nodeDomainAfter = pair.domain().get(x).get(y);
//            System.out.println("af " + nodeDomainAfter);
            if(!nodeDomainBefore.equals(nodeDomainAfter)) {
                return new Pair(true, pair.domain());
            }
            else{
                return new Pair(false, pair.domain());
            }
        }
        else{
            return new Pair(false, true);
        }
    }

    public boolean hasVariable(ArrayList<String> list, String value){
        for (String s : list) {
            if (s.equals(value)) {
                return true;
            }
        }
        return false;
    }





}
