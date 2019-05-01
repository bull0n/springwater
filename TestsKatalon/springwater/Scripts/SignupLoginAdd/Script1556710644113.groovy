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

WebUI.openBrowser('')

WebUI.navigateToUrl('http://localhost:8080/boisson/')

WebUI.click(findTestObject('Page_SpringWater/a_Senregistrer'))

WebUI.setText(findTestObject('Page_SpringWater/input_Nom_nomUtilisateur'), 'lucas')

WebUI.setEncryptedText(findTestObject('Page_SpringWater/input_Mot de passe_motDePasse'), 'mvMLieaRys7hPWVHYH5DBQ==')

WebUI.setEncryptedText(findTestObject('Page_SpringWater/input_Confirmation du mot de passe_motDePasseConfirmation'), 'mvMLieaRys7hPWVHYH5DBQ==')

WebUI.click(findTestObject('Page_SpringWater/button_Sauvegarder'))

WebUI.click(findTestObject('Page_SpringWater/a_Login'))

WebUI.setText(findTestObject('Page_Please sign in/input_Username_username'), 'lucas')

WebUI.setEncryptedText(findTestObject('Page_Please sign in/input_Password_password'), 'mvMLieaRys7hPWVHYH5DBQ==')

WebUI.click(findTestObject('Page_Please sign in/button_Sign in'))

WebUI.click(findTestObject('Page_SpringWater/a_Ajouter une boisson'))

WebUI.setText(findTestObject('Page_SpringWater/input_Nom_nom'), 'thé')

WebUI.setText(findTestObject('Page_SpringWater/textarea_Description_description'), 'thé')

WebUI.click(findTestObject('Page_SpringWater/button_Save'))

WebUI.closeBrowser()

