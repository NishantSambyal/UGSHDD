package technician.inteq.com.ugshdd.model.PendingCaseBean;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

import technician.inteq.com.ugshdd.model.PendingCasesBean;

/**
 * Created by Nishant Sambyal on 13-Jul-17.
 */

public class Outlets implements Parent<PendingCasesBean> {

    private List<PendingCasesBean> casesBeanList;
    private String mName;

    public Outlets(String name, List<PendingCasesBean> ingredients) {
        this.casesBeanList = ingredients;
        this.mName = name;
    }

    @Override
    public List<PendingCasesBean> getChildList() {
        return casesBeanList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public String getmName() {
        return mName;
    }

    public List<PendingCasesBean> getCasesBeanList() {
        return casesBeanList;
    }
}
