package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import selenium.WebDriverWrapper;
import utils.Log4Test;

import java.util.List;

/**
 * Created by Illya
 */
public class DrivePage extends BasePage {

    @FindBy(xpath = "//div[@data-target='selectionArea']//div[@data-target='itemUploadDrop']")
    private List<WebElement> allFoldersList;

    @FindBy(xpath = "//div[@id='drive_main_page']//div[@class='j-Ta-pb f-e-og-aa']")
    private WebElement createBtn;

    @FindBy(xpath = "//div[@class='j-A j-A-Nj' and @role='menu']//div[@role='menuitem'][1]")
    private WebElement newFolderBtn;

    @FindBy(xpath = "//div[@role='dialog' and @class='kb-r Da-n-r']//input")
    private WebElement newFolderNameInput;

    @FindBy(xpath = "//div[@role='menu' and @class='j-A j-A-Nj a-nf-A' and @tabindex>=0]/div[@role='menuitem'][last()]")
    private WebElement contextDeleteBtn;

    @FindBy(xpath = "//div[@class='kb-r Da-n-r']//button[@name='ok']")
    private WebElement createFolderBtn;


    public DrivePage(WebDriverWrapper driver) {
        super(driver);
    }

        //click on 'Create' button
    public DrivePage clickCreateBtn() {
        createBtn.click();
        return this;
    }

        //choose 'Folder' from CreateMenu
    public DrivePage clickNewFolderBtn() {
        newFolderBtn.click();
        return this;
    }

        //Type name into newFolderName field
    public DrivePage enterNewFolderName(String name) {
        newFolderNameInput.clear();
        newFolderNameInput.sendKeys(name);
        Log4Test.info("Creating folder with name '" + name + "'");
        return this;
    }

        //click on 'CreateFolder' button
    public DrivePage clickCreateFolderBtn() {
        createFolderBtn.click();
        return this;
    }

        //check if folder with 'name' is displayed
    public boolean isFolderCreated(String name) {
        if (getParticularFolder(name) != null) {
            Log4Test.info("Folder '" + name + "' is successfully created");
            return true;
        }else {
            Log4Test.info("Folder '" + name + "' is not found");
            return false;
        }
    }

        //delete folder with 'name'
    public void deleteFolderWithName(String name) {
        if (getParticularFolder(name) != null) {
            getParticularFolder(name).click();
            Actions builder = new Actions(driver.getOriginalDriver());
            builder.contextClick().perform();
            contextDeleteBtn.click();
            Log4Test.info(String.format("Deleting folder '%s'", name));
        } else {
            Log4Test.info("Nothing to delete - folder is not found");
        }

    }
        //return folder with 'name' from allFolder List
    private WebElement getParticularFolder(String name) {
        if (!allFoldersList.isEmpty()) {
            for (WebElement folder : allFoldersList) {
                if (folder.getAttribute("aria-label").contains(name)) {
                    return folder;
                }
            }
        }
        return null;
    }

}
