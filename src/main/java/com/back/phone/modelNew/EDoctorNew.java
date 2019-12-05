package com.back.phone.modelNew;

import com.back.phone.model.EDoctor;

import java.util.List;

public class EDoctorNew {
    private List EDoctorList;

    private EDoctor eDoctor;

//    private List tfBusinessReportList;
//
//    private List tfBusinessListList;


    public List getEDoctorList(List<EDoctor> eDoctors) {
        return EDoctorList;
    }

    public void setEDoctorList(List EDoctorList) {
        this.EDoctorList = EDoctorList;
    }

    public EDoctor geteDoctor() {
        return eDoctor;
    }

    public void seteDoctor(EDoctor eDoctor) {
        this.eDoctor = eDoctor;
    }
}
