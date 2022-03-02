package duties;

import exceptions.AlreadyAppliedException;
import model.Applicant;
import model.Store;

public class ApplicationService {

    public void apply(Applicant applicant, Store store){
        if(check(applicant, store)) {
            store.getApplicantList().add(applicant);
        }else throw new AlreadyAppliedException("This applicant Already Applied");
    }

    public boolean check(Applicant applicant, Store store){
        if(store.getApplicantList().isEmpty()) return true;
        else{
            for(Applicant apply: store.getApplicantList()){
                if(apply.getEmail().equals(applicant.getEmail()) && apply.getFirstName().equals(applicant.getFirstName())){
                    return false;
                }
            }
        }
        return true;
    }
}
