/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openswing.swing.permissions.client;

import java.awt.Dimension;
import java.util.Properties;
import javax.swing.JFrame;
import org.openswing.swing.permissions.client.LoginController;
import org.openswing.swing.permissions.java.CryptUtils;

/**
 *
 * @author irfan
 */
public class MascovaLoginDialog extends org.openswing.swing.permissions.client.LoginDialog {
    
    public MascovaLoginDialog(JFrame parentFrame, boolean changeLogin,
            LoginController loginController) {
        this(parentFrame, changeLogin, loginController, "Logon", "Login", 'L',
                "Exit", 'E', null, null);
    }

    /**
     * Constructor: it shows a username + password fields. A "store account"
     * check box is showed only if "appId" and "storeAccount" arguments are not
     * null. No password encoding/decoding task is performed. No multiple
     * language is supported in this login dialog.
     *
     * @param parentFrame parent frame to use as parent of dialog window; could
     * be set to null
     * @param changeLogin flag used to indicate that the login dialog is opened
     * inside the application: if user will click on "Exit" button then the
     * application will not be closed
     * @param loginController login controller
     * @param title window title
     * @param loginButtonText text to show in login button
     * @param loginButtonMnemonic text to show in login button
     * @param cancelButtonText text to show in exit button
     * @param cancelButtonMnemonic text to show in exit button
     * @param storeAccount store account text label
     * @param appId used to identify the application: for each distinct appId it
     * will be stored a specific account
     */
    public MascovaLoginDialog(
            JFrame parentFrame,
            boolean changeLogin,
            LoginController loginController,
            String title,
            String loginButtonText,
            char loginButtonMnemonic,
            String exitButtonText,
            char exitButtonMnemonic,
            String storeAccount,
            String appId) {
        this(
                parentFrame,
                changeLogin,
                loginController,
                title,
                loginButtonText,
                loginButtonMnemonic,
                exitButtonText,
                exitButtonMnemonic,
                storeAccount,
                appId,
                null);
    }

    /**
     * Constructor: it shows a username + password fields. A "store account"
     * check box is showed only if "appId" and "storeAccount" arguments are not
     * null. No multiple language is supported in this login dialog.
     *
     * @param parentFrame parent frame to use as parent of dialog window; could
     * be set to null
     * @param changeLogin flag used to indicate that the login dialog is opened
     * inside the application: if user will click on "Exit" button then the
     * application will not be closed
     * @param loginController login controller
     * @param title window title
     * @param loginButtonText text to show in login button
     * @param loginButtonMnemonic text to show in login button
     * @param cancelButtonText text to show in exit button
     * @param cancelButtonMnemonic text to show in exit button
     * @param storeAccount store account text label
     * @param appId used to identify the application: for each distinct appId it
     * will be stored a specific account
     * @param cipher optional cipher that can be used to encode and decode the
     * password field; if this argument is null then no password
     * encoding/decoding task is performed
     */
    public MascovaLoginDialog(
            JFrame parentFrame,
            boolean changeLogin,
            LoginController loginController,
            String title,
            String loginButtonText,
            char loginButtonMnemonic,
            String exitButtonText,
            char exitButtonMnemonic,
            String storeAccount,
            String appId,
            CryptUtils cipher) {
        this(
                parentFrame,
                changeLogin,
                loginController,
                title,
                loginButtonText,
                loginButtonMnemonic,
                exitButtonText,
                exitButtonMnemonic,
                storeAccount,
                appId,
                cipher,
                null,
                null);
    }

    /**
     * Constructor: it shows a username + password fields. A "store account"
     * check box is showed only if "appId" and "storeAccount" arguments are not
     * null. A combo-box for language selection is showed, if
     * "supportedLanguageIds" argument is not null. Username label is prefilled
     * with "Username" text. Password label is prefilled with "Password" text.
     *
     * @param parentFrame parent frame to use as parent of dialog window; could
     * be set to null
     * @param changeLogin flag used to indicate that the login dialog is opened
     * inside the application: if user will click on "Exit" button then the
     * application will not be closed
     * @param loginController login controller
     * @param title window title
     * @param loginButtonText text to show in login button
     * @param loginButtonMnemonic text to show in login button
     * @param cancelButtonText text to show in exit button
     * @param cancelButtonMnemonic text to show in exit button
     * @param storeAccount store account text label
     * @param appId used to identify the application: for each distinct appId it
     * will be stored a specific account
     * @param cipher optional cipher that can be used to encode and decode the
     * password field; if this argument is null then no password
     * encoding/decoding task is performed
     * @param supportedLanguageIds supported languages, i.e. collection of pairs
     * <language id,language description>; may be null
     * @param currentLanguageIdentifier current language identifier; may be null
     */
    public MascovaLoginDialog(
            JFrame parentFrame,
            boolean changeLogin,
            LoginController loginController,
            String title,
            String loginButtonText,
            char loginButtonMnemonic,
            String exitButtonText,
            char exitButtonMnemonic,
            String storeAccount,
            String appId,
            CryptUtils cipher,
            Properties supportedLanguageIds,
            String currentLanguageIdentifier) {
        this(
                parentFrame,
                changeLogin,
                loginController,
                title,
                loginButtonText,
                loginButtonMnemonic,
                exitButtonText,
                exitButtonMnemonic,
                storeAccount,
                appId,
                cipher,
                supportedLanguageIds,
                currentLanguageIdentifier,
                "Username",
                "Password");
    }

    /**
     * Constructor: it shows a username + password fields. A "store account"
     * check box is showed only if "appId" and "storeAccount" arguments are not
     * null. A combo-box for language selection is showed, if
     * "supportedLanguageIds" argument is not null.
     *
     * @param parentFrame parent frame to use as parent of dialog window; could
     * be set to null
     * @param changeLogin flag used to indicate that the login dialog is opened
     * inside the application: if user will click on "Exit" button then the
     * application will not be closed
     * @param loginController login controller
     * @param title window title
     * @param loginButtonText text to show in login button
     * @param loginButtonMnemonic text to show in login button
     * @param cancelButtonText text to show in exit button
     * @param cancelButtonMnemonic text to show in exit button
     * @param storeAccount store account text label
     * @param appId used to identify the application: for each distinct appId it
     * will be stored a specific account
     * @param cipher optional cipher that can be used to encode and decode the
     * password field; if this argument is null then no password
     * encoding/decoding task is performed
     * @param supportedLanguageIds supported languages, i.e. collection of pairs
     * <language id,language description>; may be null
     * @param currentLanguageIdentifier current language identifier; may be null
     * @param usernameTextLabel text to show in username label
     * @param passwordTextLabel text to show in password label
     */
    public MascovaLoginDialog(
            JFrame parentFrame,
            boolean changeLogin,
            LoginController loginController,
            String title,
            String loginButtonText,
            char loginButtonMnemonic,
            String exitButtonText,
            char exitButtonMnemonic,
            String storeAccount,
            String appId,
            CryptUtils cipher,
            Properties supportedLanguageIds,
            String currentLanguageIdentifier,
            String usernameTextLabel,
            String passwordTextLabel) {
        this(
                parentFrame,
                changeLogin,
                loginController,
                title,
                loginButtonText,
                loginButtonMnemonic,
                exitButtonText,
                exitButtonMnemonic,
                storeAccount,
                appId,
                cipher,
                supportedLanguageIds,
                currentLanguageIdentifier,
                usernameTextLabel,
                passwordTextLabel,
                null);
    }

    /**
     * Constructor: it shows a username + password fields. A "store account"
     * check box is showed only if "appId" and "storeAccount" arguments are not
     * null. A combo-box for language selection is showed, if
     * "supportedLanguageIds" argument is not null.
     *
     * @param parentFrame parent frame to use as parent of dialog window; could
     * be set to null
     * @param changeLogin flag used to indicate that the login dialog is opened
     * inside the application: if user will click on "Exit" button then the
     * application will not be closed
     * @param loginController login controller
     * @param title window title
     * @param loginButtonText text to show in login button
     * @param loginButtonMnemonic text to show in login button
     * @param cancelButtonText text to show in exit button
     * @param cancelButtonMnemonic text to show in exit button
     * @param storeAccount store account text label
     * @param appId used to identify the application: for each distinct appId it
     * will be stored a specific account
     * @param cipher optional cipher that can be used to encode and decode the
     * password field; if this argument is null then no password
     * encoding/decoding task is performed
     * @param supportedLanguageIds supported languages, i.e. collection of pairs
     * <language id,language description>; may be null
     * @param currentLanguageIdentifier current language identifier; may be null
     * @param usernameTextLabel text to show in username label
     * @param passwordTextLabel text to show in password label
     * @param size window size; if not specified, the size is automatically
     * setted by this class
     */
    public MascovaLoginDialog(
            JFrame parentFrame,
            boolean changeLogin,
            LoginController loginController,
            String title,
            String loginButtonText,
            char loginButtonMnemonic,
            String exitButtonText,
            char exitButtonMnemonic,
            String storeAccount,
            String appId,
            CryptUtils cipher,
            Properties supportedLanguageIds,
            String currentLanguageIdentifier,
            String usernameTextLabel,
            String passwordTextLabel,
            Dimension size) {
        super(parentFrame, changeLogin, loginController, title, loginButtonText, loginButtonMnemonic, exitButtonText, exitButtonMnemonic, storeAccount, appId, cipher, supportedLanguageIds, currentLanguageIdentifier, usernameTextLabel, passwordTextLabel, size);
    }
    
    public void setWarnMessage(String warnMessage){
        warnLabel.setText(warnMessage);
    }
}
