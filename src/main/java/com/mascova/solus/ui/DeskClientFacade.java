package com.mascova.solus.ui;



import com.mascova.solus.controller.CustomerDetailController;
import com.mascova.solus.controller.CustomerMasterController;
import com.mascova.solus.controller.DistrictDetailController;
import com.mascova.solus.controller.DistrictMasterController;
import com.mascova.solus.controller.EmployeeMasterController;
import com.mascova.solus.controller.LoginDetailController;
import com.mascova.solus.controller.LoginMasterController;
import com.mascova.solus.controller.PersonDetailController;
import com.mascova.solus.controller.PersonMasterController;
import com.mascova.solus.controller.ProductDetailController;
import com.mascova.solus.controller.ProductMasterController;
import com.mascova.solus.controller.ProvinceDetailController;
import com.mascova.solus.controller.ProvinceMasterController;
import com.mascova.solus.controller.SalesDetailController;
import com.mascova.solus.controller.SalesMasterController;
import com.mascova.solus.controller.ServiceDetailController;
import com.mascova.solus.controller.ServiceMasterController;
import com.mascova.solus.controller.SubDistrictDetailController;
import com.mascova.solus.controller.SubDistrictMasterController;
import com.mascova.solus.controller.VillageDetailController;
import com.mascova.solus.controller.VillageMasterController;
import com.mascova.solus.util.SqlUtil;
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
