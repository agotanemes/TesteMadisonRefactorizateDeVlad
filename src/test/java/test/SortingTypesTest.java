package test;

import com.firestarters.steps.ui.HomepageSteps;
import com.firestarters.steps.ui.SortingTypesSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class SortingTypesTest extends BaseTest{
    @Steps
    HomepageSteps homepageSteps;
    @Steps
    SortingTypesSteps sortingTypesSteps;
    @Test
    public void sortingTypesTest(){
        homepageSteps.clickOnSubcategoryOfACategory("New Arrivals", "Women");
        sortingTypesSteps.selectSortingType("Name");
        sortingTypesSteps.verifyProductListAfterNameSort();
        sortingTypesSteps.selectSortingType("Price");
        sortingTypesSteps.verifyProductListAfterPriceSort();

    }
}
