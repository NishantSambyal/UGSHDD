package technician.inteq.com.ugshdd.model;

import java.util.ArrayList;
import java.util.List;

import technician.inteq.com.ugshdd.model.PendingCaseBean.Case;
import technician.inteq.com.ugshdd.model.materialRequest.MaterialRequest;
import technician.inteq.com.ugshdd.model.materialRequest.MaterialRequestTransaction;

/**
 * Created by Nishant Sambyal on 13-Sep-17.
 */

public class HddUpload {

    private List<Case> cases;
    private List<MaterialRequest> materialRequests;
    private List<MaterialRequestTransaction> materialRequestsTransactions;

    public HddUpload() {
        this.cases = new ArrayList<>();
        this.materialRequests = new ArrayList<>();
        this.materialRequestsTransactions = new ArrayList<>();
    }

    public List<MaterialRequestTransaction> getMaterialRequestsTransactions() {
        return materialRequestsTransactions;
    }

    public void setMaterialRequestsTransactions(List<MaterialRequestTransaction> materialRequestsTransactions) {
        this.materialRequestsTransactions = materialRequestsTransactions;
    }

    public List<Case> getCases() {
        return cases;
    }

    public void setCases(List<Case> cases) {
        this.cases = cases;
    }

    public List<MaterialRequest> getMaterialRequests() {
        return materialRequests;
    }

    public void setMaterialRequests(List<MaterialRequest> materialRequests) {
        this.materialRequests = materialRequests;
    }
}
