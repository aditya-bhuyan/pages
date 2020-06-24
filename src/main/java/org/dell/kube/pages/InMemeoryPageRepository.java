package org.dell.kube.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemeoryPageRepository  implements  IPageRepository{
    Map<Long,Page> repo = new HashMap<Long,Page>();
    long counter ;

    @Override
    public Page create(Page page) {
        page.setId(++counter);
        repo.put(page.getId(),page);
        return repo.get(page.getId());
    }

    @Override
    public Page read(long id) {
        return repo.get(id);
    }

    @Override
    public List<Page> list() {
        return new ArrayList<Page>(repo.values());
    }

    @Override
    public Page update(Page page, long id) {
        Page temp = repo.get(id);
        if(temp != null){
            temp.setId(id);
            repo.put(temp.getId(),page);
        }
        return temp;
    }

    @Override
    public void delete(long id) {
            repo.remove(id);
    }
}
