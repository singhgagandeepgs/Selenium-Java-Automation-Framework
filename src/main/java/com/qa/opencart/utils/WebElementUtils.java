package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exceptions.WebElementExceptions;

public class WebElementUtils {

	private WebDriver driver;
	private Actions actions;
	private WebDriverWait wait;

	public WebElementUtils(WebDriver driver) {
		this.driver = driver;
		this.actions = new Actions(driver);
	}

	public WebElement getElement(By locator) {
		WebElement el = driver.findElement(locator);
		return el;
	}

	public List<WebElement> getElements(By locator) {
		List<WebElement> elements = driver.findElements(locator);
		return elements;
	}

	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}

	// Incredibly useful method to check if an element is present or not. We can use
	// this method in our waitForElementPresent() method in WaitUtils class.
	public boolean isElementPresent(By locator) {
		return getElementsCount(locator) > 0;
	}

	public boolean isElementPresent(By locator, int expectedCount) {
		return getElementsCount(locator) == expectedCount;
	}

	public List<String> getElementsTextList(By locator) {
		List<WebElement> elements = getElements(locator);
		List<String> textList = new ArrayList<>();
		for (WebElement e : elements) {
			String text = e.getText();
			if (!text.isEmpty()) {
				textList.add(text);
			}
		}
		return textList;
	}

	public List<String> getElementsAttributeList(By locator, String attrName) {
		List<WebElement> elements = getElements(locator);
		List<String> attrList = new ArrayList<>();
		for (WebElement e : elements) {
			String attrValue = e.getAttribute(attrName);
			if (attrValue != null && !attrValue.isEmpty()) {
				attrList.add(attrValue);
			}
		}
		return attrList;
	}

	public void clickSingleElementFromList(By locator, String textToClick) {
		List<WebElement> elements = getElements(locator);
		for (WebElement e : elements) {
			String text = e.getText();
			System.out.println("Checking element text: " + text);
			if (text.equals(textToClick)) {
				e.click();
				break; // It is very important to break the loop after clicking the desired element to
						// avoid unnecessary iterations and potential StaleElementReferenceException if
						// the page changes after the click.
			}
		}
	}

	public void doSendKeys(By locator, String value) {
		if (value == null) {
			throw new WebElementExceptions("Value for sendKeys cannot be null. Please provide a valid string.");
		} else {
			System.out.println("Sending keys: " + value);
			getElement(locator).sendKeys(value);
		}
	}
	
	public void clearFieldAndDoSendKeys(By locator, String value) {
		if (value == null) {
			throw new WebElementExceptions("Value for sendKeys cannot be null. Please provide a valid string.");
		} else {
			System.out.println("Sending keys: " + value);
			getElement(locator).clear();
			getElement(locator).sendKeys(value);
		}
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String getPageTitle() {
		String title = driver.getTitle();
		// System.out.println("Page title is: " + title);
		return title;
	}

	public String getElementText(By locator) {
		return getElement(locator).getText();
	}
	
	public String getElementText(WebElement ele) {
		return ele.getText();
	}

	public String getElementAttribute(By locator, String attrName) {
		return getElement(locator).getAttribute(attrName);
	}

	public String getElementDomAttribute(By locator, String attrName) {
		return getElement(locator).getDomAttribute(attrName);
	}

	public String getElementDomProperty(By locator, String propName) {
		return getElement(locator).getDomProperty(propName);
	}

	public boolean isElementDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
			return false;
		}
	}

	public boolean isElementEnabled(By locator) {
		try {
			return getElement(locator).isEnabled();
		} catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
			return false;
		}
	}

	public void doSelectByIndex(By locator, int index) {
		WebElement dropdown = getElement(locator);
		Select select = new Select(dropdown);
		select.selectByIndex(index);
	}

	public void doSelectByValue(By locator, String value) {
		WebElement dropdown = getElement(locator);
		Select select = new Select(dropdown);
		select.selectByValue(value);
	}

	public void doSelectByVisibleText(By locator, String visibleText) {
		WebElement dropdown = getElement(locator);
		Select select = new Select(dropdown);
		select.selectByVisibleText(visibleText);
	}

	public void doDeselectByIndex(By locator, int index) {
		WebElement dropdown = getElement(locator);
		Select select = new Select(dropdown);
		select.deselectByIndex(index);
	}

	public void doDeselectByValue(By locator, String value) {
		WebElement dropdown = getElement(locator);
		Select select = new Select(dropdown);
		select.deselectByValue(value);
	}

	public void doDeselectByVisibleText(By locator, String visibleText) {
		WebElement dropdown = getElement(locator);
		Select select = new Select(dropdown);
		select.deselectByVisibleText(visibleText);
	}

	public int getSelectDropdownOptionsCount(By locator) {
		WebElement dropdown = getElement(locator);
		Select select = new Select(dropdown);
		return select.getOptions().size();
	}

	public List<WebElement> getSelectDropdownOptions(By locator) {
		WebElement dropdown = getElement(locator);
		Select select = new Select(dropdown);
		return select.getOptions();
	}

	public void selectDropdownOption(By locator, String value) {
		WebElement dropdown = getElement(locator);
		Select select = new Select(dropdown);
		List<WebElement> options = select.getOptions();
		for (WebElement option : options) {
			if (option.getText().equals(value)) {
				option.click();
				break;
			}
		}
	}

	public String getSelectedOptionText(By locator) {
		WebElement dropdown = getElement(locator);
		Select select = new Select(dropdown);
		return select.getFirstSelectedOption().getText();
	}

	public List<String> getAllSelectedOptionsText(By locator) {
		WebElement dropdown = getElement(locator);
		Select select = new Select(dropdown);
		List<WebElement> selectedOptions = select.getAllSelectedOptions();
		List<String> selectedTextList = new ArrayList<>();
		for (WebElement option : selectedOptions) {
			selectedTextList.add(option.getText());
		}
		return selectedTextList;
	}

	public boolean isDropdownMultiple(By locator) {
		WebElement dropdown = getElement(locator);
		Select select = new Select(dropdown);
		return select.isMultiple();
	}

	public boolean isElementChecked(By locator) {
		try {
			return getElement(locator).isSelected();
		} catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
			return false;
		}
	}

	public boolean isElementUnChecked(By locator) {
		try {
			return !getElement(locator).isSelected();
		} catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
			return false;
		}
	}

	public boolean isElementSelected(By locator) {
		try {
			return getElement(locator).isSelected();
		} catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
			return false;
		}
	}

	public void menuSubMenuClick(By mainMenuLocator, By subMenuLocator) {
		WebElement mainMenu = getElement(mainMenuLocator);
		WebElement subMenu = getElement(subMenuLocator);
		actions.moveToElement(mainMenu).pause(1000).click(subMenu).build().perform();
	}

	public void doDragAndDrop(By sourceLocator, By targetLocator) {
		WebElement source = getElement(sourceLocator);
		WebElement target = getElement(targetLocator);
		actions.clickAndHold(source).pause(1000).moveToElement(target).release().perform();
		// OR actions.dragAndDrop(source, target).build().perform();
	}

	public void doRightClick(By locator) {
		WebElement element = getElement(locator);
		actions.contextClick(element).perform();
	}

	public void scrollDownPartiallyUsingPageDown() {
		actions.sendKeys(Keys.PAGE_DOWN).perform();
	}

	public void scrollUpPartiallyUsingPageUp() {
		actions.sendKeys(Keys.PAGE_UP).perform();
	}

	public void scrollDownCompletelyToBottomOfPage() {
		actions.sendKeys(Keys.CONTROL, Keys.END).perform();
		// OR actions.sendKeys(Keys.chord(Keys.CONTROL, Keys.END)).perform();
		// OR actions.sendKeys(Keys.CONTROL).sendKeys(Keys.END).perform();

		// Note: The above line of code sends the Control + End keys to the browser,
		// which is a common keyboard shortcut to scroll to the bottom of the page.
		// However, please be aware that this may not work in all browsers or on all web
		// pages, especially if there are fixed elements or infinite scrolling
		// implemented. In such cases, you may need to use JavaScriptExecutor to scroll
		// to the bottom of the page more reliably.
		// Example using JavaScriptExecutor:
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	public void scrollUpCompletelyToTopOfPage() {
		actions.sendKeys(Keys.FN, Keys.HOME).perform();
	}

	public void doScrollToElement(By locator) {
		WebElement element = getElement(locator);
		actions.scrollToElement(element).perform();
	}

	public void doScrollToElementAndClick(By locator) {
		WebElement element = getElement(locator);
		actions.scrollToElement(element).click().perform();
	}

	public void doSendKeysWithActions(By locator, String value) {
		WebElement element = getElement(locator);
		actions.sendKeys(element, value).perform();
	}

	public void doClickWithActions(By locator) {
		WebElement element = getElement(locator);
		actions.click(element).perform();
	}

	// Alert APIs:
	public String getAlertText() {
		Alert alert = driver.switchTo().alert();
		return alert.getText();

		// OR return driver.switchTo().alert().getText();
	}

	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	public void dismissAlert() {
		driver.switchTo().alert().dismiss();
	}

	public void sendKeysToAlert(String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	public String getAlertTextAndAccept(String value) {
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		alert.accept();
		return text;
	}

	public String getAlertTextAndDismiss(String value) {
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		alert.dismiss();
		return text;
	}

	public String getAlertTextAndSendKeysAndAccept(String value) {
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		alert.sendKeys(value);
		alert.accept();
		return text;
	}

	public void doAcceptAlertIfPresent() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (NoSuchElementException e) {
			System.out.println("No alert present to accept.");
		}
	}

	public void doDismissAlertIfPresent() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		} catch (NoSuchElementException e) {
			System.out.println("No alert present to dismiss.");
		}
	}

	public void doFileUpload(By locator, String filePath) {
		getElement(locator).sendKeys(filePath);
	}

	public void doSwitchToFrameByIndex(int index) {
		driver.switchTo().frame(index);
	}

	public void doSwitchToFrameByNameOrId(String nameOrId) {
		driver.switchTo().frame(nameOrId);
	}

	public void doSwitchToFrameByLocator(By locator) {
		WebElement frameElement = getElement(locator);
		driver.switchTo().frame(frameElement);
	}

	public void doSwitchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	public void doSwitchToParentFrame() {
		driver.switchTo().parentFrame();
	}

	// Wait methods:

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public WebElement waitForElementPresence(By locator, int timeout) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return ele;
	}
	
	public List<WebElement> waitForElementsPresence(By locator, int timeout) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		List<WebElement> ele = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		return ele;
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public WebElement waitForElementVisibility(By locator, int timeout) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return ele;
	}
	
	public List<WebElement> waitForElementsVisibility(By locator, int timeout) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		List<WebElement> ele = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		return ele;
	}

	public String waitForAlertPresenceAndGetText(int timeout) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		String text = alert.getText();
		return text;
	}

	public void waitForAlertPresenceAndAccept(int timeout) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
	}

	public void waitForAlertPresenceAndDismiss(int timeout) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.dismiss();
	}

	public void waitForAlertPresenceAndSendKeys(int timeout, String value) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.sendKeys(value);
	}

	public String waitForTitleContains(String expectedTitleFragment, int timeout) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.titleContains(expectedTitleFragment));
		} catch (Exception e) {
			System.out.println("Actual title doesn't contain the expected title fragment");
			e.printStackTrace();
		}
		return driver.getTitle();
	}

	public String waitForExactTitle(String expectedTitle, int timeout) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.titleIs(expectedTitle));
		} catch (Exception e) {
			System.out.println("Actual title is not equal to the expected title");
			e.printStackTrace();
		}
		return driver.getTitle();
	}

	public String waitForUrlContains(String expectedUrlFragment, int timeout) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		//boolean isExpectedURL = 
		//return isExpectedURL;
		wait.until(ExpectedConditions.urlContains(expectedUrlFragment));
		String url = driver.getCurrentUrl();
		return url;
	}

	public boolean waitForNumberOfWindowsToBe(int expectedNumOfWindows, int timeout) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		boolean isExpectedNumOfWindows = wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumOfWindows));
		return isExpectedNumOfWindows;
	}

	public void waitForFrameAndSwitchToIt(By frameLocator, int timeout) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}
}
