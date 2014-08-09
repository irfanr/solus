/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.qeela.controller;

import com.mascova.qeela.entity.Product;
import com.mascova.qeela.service.ProductService;
import com.mascova.qeela.ui.ProductDetail;
import com.mascova.qeela.ui.ProductMaster;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author irfan
 */
public class ProductDetailController extends FormController {

    private ApplicationContext ac = null;
    private ProductService productService = null;
    private ProductMaster productMaster = null;
    private ProductDetail productDetail = null;
    private long pk = -1;
    private Product product;

    public ProductDetailController(ProductMaster productMaster, long pk,
            ApplicationContext ac) {
        this.productMaster = productMaster;
        this.pk = pk;
        this.ac = ac;

        productService = ac.getBean(ProductService.class);

        productDetail = new ProductDetail(this);
        productDetail.setTitle("Product Detail");

        if (pk != -1) {
            productDetail.setTitle("Product Detail");
            productDetail.getMainPanel().setMode(Consts.READONLY);
            productDetail.getMainPanel().reload();
            MDIFrame.add(productDetail, true);
        } else {
            productDetail.setTitle("Create Product");
            productDetail.getMainPanel().setMode(Consts.INSERT);
            productDetail.setUniqueInstance(true);
            MDIFrame.add(productDetail, true);
        }


    }

    public ProductMaster getProductMaster() {
        return productMaster;
    }

    @Override
    public Response loadData(Class valueObjectClass) {

        int row = productMaster.getGrid().getSelectedRow();

        if (row != -1) {

            product = (Product) productMaster.getGrid().getVOListTableModel()
                    .getObjectForRow(row);
            pk = product.getId();

            // Reload from db
            product = productService.find(pk);

//            // repopulate product picture
//            if (product.getPicture() != null) {
//                if (product.getPicture().getFile() != null) {
//                    product.setFile(product.getPicture().getFile());
//                }
//            }
        }

        return new VOResponse(product);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {

        Product newProduct = (Product) newPersistentObject;

        // insert product picture
//        if (newProduct.getFile() != null) {
//            newProduct.setPicture(new Image());
//            newProduct.getPicture().setFile(newProduct.getFile());
//            imageService.saveImage(newProduct.getPicture());
//        }

        productService.save(newProduct);

        return new VOResponse(newProduct);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {

        Product modifiedProduct = (Product) persistentObject;
        Product oldProduct = (Product) oldPersistentObject;

//        if (oldProduct.getPicture() != null) {
//
//            Image productImage = imageService.findImage(
//                    oldProduct.getPicture().getId());
//            
//            // update product picture
//            if (modifiedProduct.getFile() != null) {
//
//                // update image
//                productImage.setFile(modifiedProduct.getFile());
//                imageService.updateImage(productImage);
//
//            } else {
//
//                // delete image
//                imageService.deleteImage(productImage);
//            }
//        } else {
//
//            // insert product picture
//            if (modifiedProduct.getFile() != null) {
//                
//                modifiedProduct.setPicture(new Image());
//                modifiedProduct.getPicture().setFile(modifiedProduct.getFile());
//                imageService.saveImage(modifiedProduct.getPicture());
//            }
//        }



        productService.update(product);

        return new VOResponse(modifiedProduct);
    }

    @Override
    public Response deleteRecord(ValueObject persistentObject) throws Exception {

        Product deletedProduct = (Product) persistentObject;
        productService.delete(product);

        return new VOResponse(deletedProduct);
    }

    /**
     * @return the productDetail
     */
    public ProductDetail getProductDetail() {
        return productDetail;
    }
}
