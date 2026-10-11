import java.util.Collection;
import java.util.HashSet;

public class CompteurHashSet<E> extends HashSet<E> {
    private int nbAjouts = 0;

    @Override public boolean add(E e) {
        nbAjouts++;
        return super.add(e);
    }

    @Override public boolean addAll(Collection<? extends E> c) {
        nbAjouts += c.size();
        return super.addAll(c);
    }

    public int getNbAjouts() { return nbAjouts; }
}