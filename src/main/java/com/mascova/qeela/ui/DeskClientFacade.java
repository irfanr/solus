package com.mascova.qeela.ui;



import com.mascova.qeela.controller.CustomerDetailController;
import com.mascova.qeela.controller.CustomerMasterController;
import com.mascova.qeela.controller.DistrictDetailController;
import com.mascova.qeela.controller.DistrictMasterController;
import com.mascova.qeela.controller.EmployeeMasterController;
import com.mascova.qeela.controller.LoginDetailController;
import com.mascova.qeela.controller.LoginMasterController;
import com.mascova.qeela.controller.PersonDetailController;
import com.mascova.qeela.controller.PersonMasterController;
import com.mascova.qeela.controller.ProductDetailController;
import com.mascova.qeela.controller.ProductMasterController;
import com.mascova.qeela.controller.ProvinceDetailController;
import com.mascova.qeela.controller.ProvinceMasterController;
import com.mascova.qeela.controller.SalesDetailController;
import com.mascova.qeela.controller.SalesMasterController;
import com.mascova.qeela.controller.ServiceDetailController;
import com.mascova.qeela.controller.ServiceMasterController;
import com.mascova.qeela.controller.SubDistrictDetailController;
import com.mascova.qeela.controller.SubDistrictMasterController;
import com.mascova.qeela.controller.VillageDetailController;
import com.mascova.qeela.controller.VillageMasterController;
import com.mascova.qeela.util.SqlUtil;
import org.openswing.swing.mdi.client.*;
import org.springframework.context.ApplicationContext;

/**
 * <p>Title: OpenSwing Framework</p>
 * <p>Description: Client Facade, called by the MDI Tree.</p>
 * <p>Copyright: Copyright (C) 2006 Mauro Carniel</p>
 * <p> </p>
 *
 * @author Mauro Carniel
 * @version 1.0
 */
public class DeskClientFacade implements ClientFacade {

    private ApplicationContext ac = null;

    DeskClientFacade(ApplicationContext ac) {
        this.ac = ac;
    }

    public void getPersonMaster() {
        new PersonMasterController(ac);
    }

    public void getPersonDetail() {
        new PersonDetailController(null, -1, ac);
    }

    public void getLoginMaster() {
        new LoginMasterController(ac);
    }

    public void getLoginDetail() {
        new LoginDetailController(null, -1, ac);
    }

    public void getProvinceMaster() {
        new ProvinceMasterController(ac);
    }

    public void getProvinceDetail() {
        new ProvinceDetailController(null, -1, ac);
    }

    public void getDistrictMaster() {
        new DistrictMasterController(ac);
    }

    public void getDistrictDetail() {
        new DistrictDetailController(null, -1, ac);
    }

    public void getSubDistrictMaster() {
        new SubDistrictMasterController(ac);
    }

    public void getSubDistrictDetail() {
        new SubDistrictDetailController(null, -1, ac);
    }

    public void getVillageMaster() {
        new VillageMasterController(ac);
    }

    public void getVillageDetail() {
        new VillageDetailController(null, -1, ac);
    }

    public void exportToCsv() {
        SqlUtil.exportCsvData();
    }

    public void importFromCsv() {
        SqlUtil.importCsvData();
    }
    
    public void getCustomerMaster() {
        new CustomerMasterController(ac);
    }

    public void getCustomerDetail() {
        new CustomerDetailController(null, -1, ac);
    }    
    
    public void getEmployeeMaster() {
        new EmployeeMasterController(ac);
    }

    public void getEmployeeDetail() {
//        new EmployeeDetailController(null, -1, ac);
    }        
    
    public void getSalesMaster() {
        new SalesMasterController(ac);
    }

    public void getSalesDetail() {
        new SalesDetailController(null, -1, ac);
    }     
    
    public void getProductMaster() {
        new ProductMasterController(ac);
    }

    public void getProductDetail() {
        new ProductDetailController(null, -1, ac);
    }        
    
    public void getServiceMaster() {
        new ServiceMasterController(ac);
    }

    public void getServiceDetail() {
        new ServiceDetailController(null, -1, ac);
    }        
}
