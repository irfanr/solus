/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.qeela.controller;

import com.mascova.qeela.entity.Person;
import com.mascova.qeela.service.ImageService;
import com.mascova.qeela.service.PersonService;
import com.mascova.qeela.ui.PersonDetail;
import com.mascova.qeela.ui.PersonMaster;
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
public class PersonDetailController extends FormController {

    private ApplicationContext ac = null;
    private PersonService personService = null;
    private ImageService imageService = null;
    private PersonMaster personMaster = null;
    private PersonDetail personDetail = null;
    private long pk = -1;
    private Person person;
    private VillageLookupController vlc = null;

    public PersonDetailController(PersonMaster personMaster, long pk,
            ApplicationContext ac) {
        this.personMaster = personMaster;
        this.pk = pk;
        this.ac = ac;

        personService = ac.getBean(PersonService.class);
        imageService = ac.getBean(ImageService.class);
        vlc = new VillageLookupController(ac);
        
        personDetail = new PersonDetail(this, vlc);

        MDIFrame.add(personDetail, true);

        if (pk != -1) {
            personDetail.setTitle("Person Detail");
            personDetail.getMainPanel().setMode(Consts.READONLY);
            personDetail.getMainPanel().reload();

        } else {
            personDetail.setTitle("Create Person");
            personDetail.getMainPanel().setMode(Consts.INSERT);

        }
    }

    public PersonMaster getPersonMaster() {
        return personMaster;
    }

    @Override
    public Response loadData(Class valueObjectClass) {

        int row = personMaster.getGrid().getSelectedRow();

        if (row != -1) {

            person = (Person) personMaster.getGrid().getVOListTableModel()
                    .getObjectForRow(row);
            pk = person.getId();

            // Reload from db
            person = personService.find(pk);

//            // repopulate person picture
//            if (person.getPicture() != null) {
//                if (person.getPicture().getFile() != null) {
//                    person.setFile(person.getPicture().getFile());
//                }
//            }
        }

        return new VOResponse(person);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {

        Person newPerson = (Person) newPersistentObject;

        // insert person picture
//        if (newPerson.getFile() != null) {
//            newPerson.setPicture(new Image());
//            newPerson.getPicture().setFile(newPerson.getFile());
//            imageService.saveImage(newPerson.getPicture());
//        }

        personService.save(newPerson);

        return new VOResponse(newPerson);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {

        Person modifiedPerson = (Person) persistentObject;
        Person oldPerson = (Person) oldPersistentObject;

//        if (oldPerson.getPicture() != null) {
//
//            Image personImage = imageService.findImage(
//                    oldPerson.getPicture().getId());
//            
//            // update person picture
//            if (modifiedPerson.getFile() != null) {
//
//                // update image
//                personImage.setFile(modifiedPerson.getFile());
//                imageService.updateImage(personImage);
//
//            } else {
//
//                // delete image
//                imageService.deleteImage(personImage);
//            }
//        } else {
//
//            // insert person picture
//            if (modifiedPerson.getFile() != null) {
//                
//                modifiedPerson.setPicture(new Image());
//                modifiedPerson.getPicture().setFile(modifiedPerson.getFile());
//                imageService.saveImage(modifiedPerson.getPicture());
//            }
//        }



        personService.update(person);

        return new VOResponse(modifiedPerson);
    }

    @Override
    public Response deleteRecord(ValueObject persistentObject) throws Exception {

        Person deletedPerson = (Person) persistentObject;
        personService.delete(person);

        personMaster.getGrid().reloadData();
        personDetail.closeFrame();

        return new VOResponse(deletedPerson);
    }
}
