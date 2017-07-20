package technician.inteq.com.ugshdd.model.technicalRequestBean;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

/**
 * Created by Patyal on 7/18/2017.
 */

public class TechnicalRequests implements Parent<TechnicalRequestsDetails> {

    String technicalRequest;
    List<TechnicalRequestsDetails> technicalRequestsDetails;

    public TechnicalRequests(String technicalRequest, List<TechnicalRequestsDetails> technicalRequestsDetails) {
        this.technicalRequest = technicalRequest;
        this.technicalRequestsDetails = technicalRequestsDetails;
    }

    @Override
    public List<TechnicalRequestsDetails> getChildList() {
        return technicalRequestsDetails;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public String getTechnicalRequest() {
        return technicalRequest;
    }
}

