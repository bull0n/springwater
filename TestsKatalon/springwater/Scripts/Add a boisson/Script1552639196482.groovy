import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('http://'+System.getenv( 'local_addr' )+"/")

WebUI.click(findTestObject('Object Repository/Page_/a_Ajouter une boisson'))

WebUI.setText(findTestObject('Object Repository/Page_/input_Nom_nom'), 'Salut')

WebUI.setText(findTestObject('Object Repository/Page_/textarea_Description_description'), 'Comment')

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_/select_Russian drinkHard spiritSoft drinkPirate drinkCraft beerWhite wineRed wineTea'),
    '3', true)

WebUI.setText(findTestObject('Object Repository/Page_/input_Nom_nom'), 'Sprite')

WebUI.setText(findTestObject('Object Repository/Page_/textarea_Description_description'), '')

WebUI.click(findTestObject('Object Repository/Page_/button_Save'))

WebUI.closeBrowser()
