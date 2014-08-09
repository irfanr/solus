package com.mascova.solus.ui;

import com.mascova.solus.entity.Login;
import com.mascova.solus.entity.SystemLookup;
import com.mascova.solus.service.AuthService;
import com.mascova.solus.service.LoginService;
import com.mascova.solus.service.MenuService;
import com.mascova.solus.service.SystemLookupService;
import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openswing.swing.mdi.client.*;
import org.openswing.swing.util.client.ClientSettings;
import org.openswing.swing.permissions.client.*;
import javax.swing.*;
import org.openswing.swing.internationalization.java.Language;
import javax.swing.tree.DefaultTreeModel;
import org.openswing.swing.client.SplashScreen;
import org.openswing.swing.domains.java.Domain;
import org.openswing.swing.internationalization.java.EnglishOnlyResourceFactory;
import org.openswing.swing.lookup.client.LookupController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>Title: OpenSwing Demo</p>
 * <p>Description: Used to start application from main method: it creates an MDI
 * Frame app with 5 functions viewed in the menu tree and in the menubar</p>
 * <p>Copyright: Copyright (C) 2006 Mauro Carniel</p>
 * <p> </p>
 *
 * @author Mauro Carniel
 * @version 1.0
 */
public class ClientApplication implements MDIController, LoginController {

    private DeskClientFacade clientFacade = null;
    private ApplicationContext ac = null;
    private LoginService loginService = null;
    private AuthService authService = null;
    private SystemLookupService systemLookupService = null;
    private MascovaLoginDialog loginDialog = null;

    public ClientApplication(String[] argv) {

        System.out.println(System.getProperty("user.dir"));

        ac = new ClassPathXmlApplicationContext(
                "/META-INF/spring/applicationContext-jpa.xml",
                "/META-INF/spring/applicationContext.xml");
        loginService = ac.getBean(LoginService.class);
        authService = ac.getBean(AuthService.class);
        systemLookupService = ac.getBean(SystemLookupService.class);
        clientFacade = new DeskClientFacade(ac);

        // BEGIN Create dummy data

//        SqlUtil.importCsvData();

//        DataGenerator dg = ac.getBean(DataGenerator.class);
//        PersonDataOnDemand pdod = ac.getBean(PersonDataOnDemand.class);
//        dg.insertMenuData();
//        pdod.init();

        // END Create dummy data        

        Hashtable domains = new Hashtable();

        List<SystemLookup> genderSystemLookup =
                systemLookupService.findByType("GNDR");
        Domain genderDomain = new Domain("GNDR");
        for (SystemLookup systemLookup : genderSystemLookup) {
            genderDomain.addDomainPair(systemLookup.getCode(), systemLookup.getLiteral());
        }

        List<SystemLookup> statusSystemLookup =
                systemLookupService.findByType("STATUS");
        Domain statusDomain = new Domain("STATUS");
        for (SystemLookup systemLookup : statusSystemLookup) {
            statusDomain.addDomainPair(systemLookup.getType(), systemLookup.getLiteral());
        }

        domains.put(
                genderDomain.getDomainId(),
                genderDomain);
        domains.put(
                statusDomain.getDomainId(),
                statusDomain);

        Properties licenseProp = new Properties();
        licenseProp.put("systemTextFont", "Arial PLAIN 8");
        licenseProp.put("controlTextFont", "Arial PLAIN 8");
        licenseProp.put("menuTextFont", "Arial PLAIN 8");
        licenseProp.put("userTextFont", "Arial PLAIN 8");
        licenseProp.put("subTextFont", "Arial PLAIN 8");

        ClientSettings clientSettings = new ClientSettings(
                new EnglishOnlyResourceFactory("E", licenseProp, true),
                domains);
//        ClientSettings clientSettings = new ClientSettings(
//                new IndonesiaOnlyResourceFactory("E", licenseProp, true),
//                domains);

//    Enumeration k = System.getProperties().keys();
//    while(k.hasMoreElements()) {
//      String kk = k.nextElement().toString();
//      System.out.println(kk+"="+System.getProperty(kk));
//    }
//    System.out.println(ClientSettings.LOOK_AND_FEEL_CLASS_NAME);

//        ClientSettings.LOOK_AND_FEEL_CLASS_NAME =
//                "javax.swing.plaf.nimbus.NimbusLookAndFeel";
        ClientSettings.LOOK_AND_FEEL_CLASS_NAME =
                "com.jtattoo.plaf.aluminium.AluminiumLookAndFeel";

        Properties props = new Properties();
        props.put("logoString", "Solus");
        String color = "220 220 220";
        props.put("selectionBackgroundColor", color);
        props.put("menuSelectionBackgroundColor", color);
        props.put("backgroundColor", "232 232 232");
        props.put("disabledBackgroundColor", color);
        props.put("systemTextFont", "Arial PLAIN 11");
        props.put("controlTextFont", "Arial PLAIN 11");
        props.put("menuTextFont", "Arial PLAIN 11");
        props.put("userTextFont", "Arial PLAIN 11");
        props.put("subTextFont", "Arial PLAIN 11");
        try {
            try {
                try {
                    Class.forName(ClientSettings.LOOK_AND_FEEL_CLASS_NAME).getMethod(
                            "setCurrentTheme", new Class[]{Properties.class}).invoke(null,
                            new Object[]{props});
                } catch (NoSuchMethodException ex) {
                    Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            UIManager.setLookAndFeel(ClientSettings.LOOK_AND_FEEL_CLASS_NAME);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
        }


        ClientSettings.BACKGROUND = "background3.jpg";
        ClientSettings.TREE_BACK = "treeback2.jpg";
        ClientSettings.ICON_FILENAME = "appicon1.png";
        ClientSettings.AUTO_EXPAND_TREE_MENU = true;
        ClientSettings.MIN_MENU_WIDTH = 300;
        ClientSettings.FILTER_PANEL_ON_GRID = true;
        ClientSettings.SHOW_NAVIGATOR_BAR_IN_LOOKUP = true;
        ClientSettings.LOOKUP_FRAME_CONTENT = LookupController.GRID_AND_FILTER_FRAME;
//    ClientSettings.AUTO_EXPAND_SUBTREE_MENU = "Folder3";
//    ClientSettings.MAX_MENU_WIDTH = 300;
//    ClientSettings.MENU_WIDTH = 300;        

        if (argv.length == 1) {
            ClientSettings.LOOK_AND_FEEL_CLASS_NAME = argv[0];
        }

//        instantiateLoginDialog();
        MDIFrame mdi = new MDIFrame(this);

    }

    /**
     * Method called after MDI creation.
     */
    public void afterMDIcreation(MDIFrame frame) {
//        frame.addButtonToToolBar("new.gif", "New Record");
//        frame.addButtonToToolBar("edit.gif", "Edit Record");
//        frame.addButtonToToolBar("reload.gif", "Undo/Refresh Record");
//        frame.addButtonToToolBar("save.gif", "Save Record");
//        frame.addButtonToToolBar("del.gif", "Delete Record");
        frame.setBorderPainterOnToolBar(false);
        frame.setFloatableOnToolBar(false);
        frame.setRolloverOnToolBar(false);
        new SplashScreen(frame, "mas3-256x256.png", getMDIFrameTitle(), 5);
        frame.getMenuItem("F2").setMnemonic('2');

//    JPanel rightPanel = new JPanel();
//    rightPanel.setBorder(BorderFactory.createTitledBorder("Right Panel"));
//    rightPanel.setPreferredSize(new Dimension(150,frame.getHeight()));
//    frame.getContentPane().add(rightPanel,BorderLayout.EAST);

    }

    /**
     * @see JFrame getExtendedState method
     */
    public int getExtendedState() {
        return JFrame.MAXIMIZED_BOTH;
    }

    /**
     * @return client facade, invoked by the MDI Frame tree/menu
     */
    public ClientFacade getClientFacade() {
        return clientFacade;
    }

    /**
     * Method used to destroy application.
     */
    public void stopApplication() {
        System.exit(0);
    }

    /**
     * Defines if application functions must be viewed inside a tree panel of
     * MDI Frame.
     *
     * @return <code>true</code> if application functions must be viewed inside
     * a tree panel of MDI Frame, <code>false</code> no tree is viewed
     */
    public boolean viewFunctionsInTreePanel() {
        return true;
    }

    /**
     * Defines if application functions must be viewed in the menubar of MDI
     * Frame.
     *
     * @return <code>true</code> if application functions must be viewed in the
     * menubar of MDI Frame, <code>false</code> otherwise
     */
    public boolean viewFunctionsInMenuBar() {
        return true;
    }

    /**
     * @return <code>true</code> if the MDI frame must show a login menu in the
     * menubar, <code>false</code> no login menu item will be added
     */
    public boolean viewLoginInMenuBar() {
        return true;
    }

    /**
     * @return application title
     */
    public String getMDIFrameTitle() {
        return "Solus";
    }

    /**
     * @return text to view in the about dialog window
     */
    public String getAboutText() {
        return "<html><body><p style='font-family: Arial,sans-serif;font-size:12'>This is an MDI Frame demo application<br>"
                + "<br>"
                + "Copyright: Copyright (C) 2013 Mascova<br>"
                + "<a href='http://www.mascova.com'>Mascova home page</a><br>"
                + "Author: Irfan Romadona</body></html>";
    }

    /**
     * @return image name to view in the about dialog window
     */
    public String getAboutImage() {
        return "mas3-256x256.png";
    }

    /**
     * @param parentFrame parent frame
     * @return a dialog window to logon the application; the method can return
     * null if viewLoginInMenuBar returns false
     */
    public JDialog viewLoginDialog(JFrame parentFrame) {
        return instantiateLoginDialog();
    }

    /**
     * @return maximum number of failed login
     */
    public int getMaxAttempts() {
        return 3;
    }

    /**
     * Method called by MDI Frame to authenticate the user.
     *
     * @param loginInfo login information, like username, password, ...
     * @return <code>true</code> if user is correcly * * * * * * *
     * authenticated, <code>false</code> otherwise
     */
    public boolean authenticateUser(Map loginInfo) throws Exception {
        String username = (String) loginInfo.get("username");
        String password = (String) loginInfo.get("password");

        Login login = authService.login(username, password);

        return true;

    }

    public static void main(String[] argv) {
        new ClientApplication(argv);
    }

    /**
     * Method called by MascovaLoginDialog to notify the sucessful login.
     *
     * @param loginInfo login information, like username, password, ...
     */
    public void loginSuccessful(Map loginInfo) {

        String loginName = loginInfo.get("username").toString().toUpperCase();

        MDIFrame mdi = new MDIFrame(this);
    }

    /**
     * @return <code>true</code> if the MDI frame must show a change language
     * menu in the menubar, <code>false</code> no change language menu item will
     * be added
     */
    public boolean viewChangeLanguageInMenuBar() {
        return false;
    }

    /**
     * @return list of languages supported by the application
     */
    public ArrayList getLanguages() {
        ArrayList list = new ArrayList();
        list.add(new Language("EN", "English"));
        return list;
    }

    /**
     * @return application functions (ApplicationFunction objects), organized as
     * a tree
     */
    public DefaultTreeModel getApplicationFunctions() {

        MenuService menuService = ac.getBean(MenuService.class);

        return menuService.getTreeMenu();
    }

    /**
     * @return <code>true</code> if the MDI frame must show a panel in the
     * bottom, containing last opened window icons, <code>false</code> no panel
     * is showed
     */
    public boolean viewOpenedWindowIcons() {
        return true;
    }

    /**
     * @return <code>true</code> if the MDI frame must show the "File" menu in
     * the menubar of the frame, <code>false</code> to hide it
     */
    public boolean viewFileMenu() {
        return true;
    }

    private MascovaLoginDialog instantiateLoginDialog() {
        loginDialog = new MascovaLoginDialog(null, false, this, "Logon", "Login", 'L',
                "Exit", 'E', null, null, null, null, null, "username", "password",
                new Dimension(476, 200));
        return loginDialog;
    }
}
