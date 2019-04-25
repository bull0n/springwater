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

WebUI.click(findTestObject('Object Repository/Page_SpringWater/a_2'))

WebUI.setText(findTestObject('Object Repository/Page_SpringWater/input_Terme recherch_boisson'), 'Water')

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_SpringWater/select_No catgorieRussian drinkHard spiritSoft drinkPirate drinkCraft beerWhite wineRed wineTeaOther alcoolic beverage'), 
    '-1', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_SpringWater/select_No catgorieRussian drinkHard spiritSoft drinkPirate drinkCraft beerWhite wineRed wineTeaOther alcoolic beverage'), 
    '1', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_SpringWater/select_No catgorieRussian drinkHard spiritSoft drinkPirate drinkCraft beerWhite wineRed wineTeaOther alcoolic beverage'), 
    '-1', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_SpringWater/select_No catgorieRussian drinkHard spiritSoft drinkPirate drinkCraft beerWhite wineRed wineTeaOther alcoolic beverage'), 
    '-1', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_SpringWater/select_No catgorieRussian drinkHard spiritSoft drinkPirate drinkCraft beerWhite wineRed wineTeaOther alcoolic beverage'), 
    '-1', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_SpringWater/select_No catgorieRussian drinkHard spiritSoft drinkPirate drinkCraft beerWhite wineRed wineTeaOther alcoolic beverage'), 
    '-1', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_SpringWater/select_No catgorieRussian drinkHard spiritSoft drinkPirate drinkCraft beerWhite wineRed wineTeaOther alcoolic beverage'), 
    '-1', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_SpringWater/select_No catgorieRussian drinkHard spiritSoft drinkPirate drinkCraft beerWhite wineRed wineTeaOther alcoolic beverage'), 
    '-1', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_SpringWater/select_No catgorieRussian drinkHard spiritSoft drinkPirate drinkCraft beerWhite wineRed wineTeaOther alcoolic beverage'), 
    '-1', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_SpringWater/select_No catgorieRussian drinkHard spiritSoft drinkPirate drinkCraft beerWhite wineRed wineTeaOther alcoolic beverage'), 
    '-1', true)

WebUI.click(findTestObject('Object Repository/Page_SpringWater/button_Rechercher'))

WebUI.closeBrowser()

